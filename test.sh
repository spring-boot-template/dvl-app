#!/bin/bash
mydir=$(dirname `readlink -f $0`)
me=$(readlink -f $0)

total_points=0
ok_points=0

ascii_code() {
  LC_CTYPE=C printf '%d' "'$1"
}

gprop() {
	grep "$1" $mydir/src/main/resources/application.properties | grep -v '#' | cut -d'=' -f2
}

linkedin_name=$(gprop 'linkedin.name')
port=$(gprop 'server.port')

name_1=$(ascii_code ${linkedin_name:0:1})
name_port="${name_1}80"

api_base_url="http://localhost:${port}/${linkedin_name:0:3}"
web_base_url="http://localhost:${port}"

echo "https://www.linkedin.com/in/${linkedin_name}/"
echo "$web_base_url"

result() {
	test="$1"
	msg="$2"
	extra="$3"
	
	echo "##############"
	echo "-- ${FUNCNAME[1]}: $msg $extra"
	echo "${test}?"
	
	#if [[ "$msg" == "OK" ]]; then
	#	ok_points=$((ok_points+1))		
	#fi
	
	if eval $test; then
		echo OK
		ok_points=$((ok_points+1))
	else
		echo FAIL
	fi
	
	total_points=$((total_points+1))
	echo "/" && echo
}

# criar uma propriedade custom no arquivo application.properties, com o nome linkedin.name e valor igual ao seu nome de usuário 
# (da forma como aparece na URL do LinkedIn). Por exemplo: 
# considerando a URL de perfil https://www.linkedin.com/in/joaozinho123/, a variável teria o valor de "joaozinho123".
test_0a_LINKEDIN() {
	result "[ -n \"$linkedin_name\" ]"
}

# modificar a aplicação para subir na porta HTTP considerando o valor ASCII do primeiro caractere da variável acima, 
# concatenado com "80". Por exemplo: utilizando o exemplo acima, a aplicação subiria na porta 10680.
test_0b_PORTA() {
	result "[ \"$port\" == \"$name_port\" ]"
}

# modificar a variável de prefixo REST (já existente) para ser igual aos três primeiros caracteres da variável definida no item a).
test_0c_API_PREFIX() {
	api_prefix=$(gprop 'dvl.rest.prefix')
	result "[[ \"${api_prefix:1}\" == \"${linkedin_name:0:3}\" ]]"
}

# modificar GET /skills para retornar ordenado por nome
test_1_SKILLS_BY_NAME() {
	#echo "$api_base_url/skills -d {\"name\": \"zzz\"}"
	#curl -s -X POST -H 'Content-Type: application/json' "$api_base_url/skills" -d "{\"name\": \"zzz\"}" && echo
	curl -s -X POST -H 'Content-Type: application/json' "$api_base_url/skills" -d "{\"name\": \"zzz\"}" > /dev/null
	
	#echo "$api_base_url/skills -d {\"name\": \"yyy\"}"
	#curl -s -X POST -H 'Content-Type: application/json' "$api_base_url/skills" -d "{\"name\": \"yyy\"}" && echo
	curl -s -X POST -H 'Content-Type: application/json' "$api_base_url/skills" -d "{\"name\": \"yyy\"}" > /dev/null
	
	#echo "$api_base_url/skills -d {\"name\": \"aaa\"}"
	#curl -s -X POST -H 'Content-Type: application/json' "$api_base_url/skills" -d "{\"name\": \"aaa\"}" && echo
	curl -s -X POST -H 'Content-Type: application/json' "$api_base_url/skills" -d "{\"name\": \"aaa\"}" > /dev/null
	
	first=$(curl -s "$api_base_url/skills" | python -m json.tool | python -c "import sys, json; print json.load(sys.stdin)[0]['name']")
	result "[[ \"$first\" == \"aaa\" ]]"
}

# modificar GET /stats para retornar ordenado por total (decrescente) e avg (decrescente)
test_2_STATS_ORDER() {
	#echo "$web_base_url/stats/list.htm"
	
	first=$(curl -s "$api_base_url/stats" | python -m json.tool | python -c "import sys, json; print json.load(sys.stdin)[0]['total']")
	last=$(curl -s "$api_base_url/stats" | python -m json.tool | python -c "import sys, json; print json.load(sys.stdin)[-1]['total']")
	
	result "[[ \"$last\" -gt \"$first\" ]]"
}

# corrigir GET /skills/like para filtrar por nome
test_3_SKILLS_LIKE() {
	like=$(curl -s "$api_base_url/skills/like?id=y" | python -m json.tool | python -c "import sys, json; print json.load(sys.stdin)[0]['name']")
	result "[ \"$like\" == yyy ]"
}

# mapear para /jobs
# suporte para GET, POST, DELETE
test_4_JOBS() {
	points=0
	
	GET_ALL=$(curl -s -I -X GET "$api_base_url/jobs" | head -n 1 |cut -d$' ' -f2)
	GET_ONE=$(curl -s -I -X GET "$api_base_url/jobs/1" | head -n 1 |cut -d$' ' -f2)
	POST=$(curl -s -I -X POST "$api_base_url/jobs" | head -n 1 |cut -d$' ' -f2)
	DEL=$(curl -s -I -X DELETE "$api_base_url/jobs/0" | head -n 1 |cut -d$' ' -f2)
	
	if [ "$GET_ALL" != 404 ]; then
		#echo "$GET_ALL != 404"
		points=$((points+1))
	fi
	
	if [ "$GET_ONE" != 404 ]; then
		#echo "$GET_ONE != 404"
		points=$((points+1))
	fi
	
	if [ "$POST" != 404 ]; then
		#echo "$POST != 404"
		points=$((points+1))
	fi
	
	if [ "$DEL" != 404 ]; then
		#echo "$DEL != 404"
		points=$((points+1))
	fi
	
	result "[ \$points == 4 ]"
}

# Criar card no index.html para visualização ou inclusão de Jobs 
# seguindo o padrão dos outros cards já existentes
test_4_1_JOBS_MENU() {
	class=$(find $mydir -name "JobService.java")
	annotation=$(grep -c '@MenuItem' "$class")
	result "[[ \"$annotation\" -gt 0 ]]"
}

# com página de listagem
# inserção/edição/remoção
# acessível pela página inicial
test_4_2_JOBS_LIST() {
	page=$(find $mydir -name "list.htm" | grep -c jobs)
	#find $mydir -name "list.htm" | grep jobs | grep -v "/target" | grep -v "/bin"
	#echo "$web_base_url/jobs/list.htm"
	
	result "[[ \"$page\" -gt 0 ]]"
}

# card de jobs aparecendo no index
test_4_3_JOBS_CONSTRAINT() {
	class=$(find $mydir -name "JobBean.java")
	annotation=$(grep -c '@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name") })' "$class")
	result "[[ \"$annotation\" -gt 0 ]]"
}

# Corrigir a classe RestAspect
test_5_ASPECT() {
	class=$(find $mydir -name "RestAspect.java")
	annotation=$(grep -c '@Aspect' "$class")
	result "[[ \"$annotation\" -gt 0 ]]"
}

# skill com o nome 
test_6_SKILL_BY_NAME() {
	name=aaa
	exists_a=$(curl -s "$api_base_url/skills/name/$name" | python -m json.tool | python -c "import sys, json; print json.load(sys.stdin)['name']")
	exists_b=$(curl -s "$api_base_url/skills?name=$name" | python -m json.tool | python -c "import sys, json; print json.load(sys.stdin)['name']")
	
	if [[ "$exists_a" != $name ]]; then
		exists_a=false
	fi
	
	if [[ "$exists_b" != $name ]]; then
		exists_b=false
	fi
	
	result "[[ \"$exists_a\" == $name || \"$exists_b\" == $name ]]"
}

# boolean indicando se bean existe com o nome 
test_6_1_SKILL_EXISTS() {
	name=aaa
	exists_a=$(curl -s "$api_base_url/skills/exists/name/$name")
	exists_b=$(curl -s "$api_base_url/skills/exists?name=$name")
	
	if [[ "$exists_a" != true ]]; then
		exists_a=false
	fi
	
	if [[ "$exists_b" != true ]]; then
		exists_b=false
	fi
	
	result "[[ \"$exists_a\" == true || \"$exists_b\" == true ]]"
}

echo && echo "calling test functions..."
while read func
do
	$func
done < <(grep test_ "$me" | grep -v 'done' | cut -d'(' -f1)

echo && echo "$ok_points/$total_points"