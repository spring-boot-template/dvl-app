#!/bin/bash
# depends on https://github.com/ulisseslima/bash-utils.git
mydir=$(dirname `readlink -f $0`)
me=$(readlink -f $0)
app=dvl-app

if [[ -n "$1" ]]; then
	altdir="$(readlink -f $1)"
	if [[ ! -d "$altdir/$app" ]]; then
		echo "invalid dir: $altdir"
		exit 1
	fi
	mydir="$altdir/$app"
fi

echo && echo "################################"
echo "dir: $mydir"

total_points=0
ok_points=0

ascii_code() {
  LC_CTYPE=C printf '%d' "'$1"
}

gprop() {
	f="$mydir/src/main/resources/application.properties"
	if [[ -f "$f" ]]; then
		>&2 echo "analyzing: $f"
		grep "$1" $mydir/src/main/resources/application.yml | grep -v '#' | cut -d'=' -f2
	else
		>&2 echo "not a file: $f"
		exit 1
	fi
}

linkedin_name=$(gprop 'linkedin.name')
port=$(gprop 'server.port')

name_1=$(ascii_code ${linkedin_name:0:1})
name_port="${name_1}80"

echo && echo "linkedin:"
echo "https://www.linkedin.com/in/${linkedin_name}/"

api_base_url="http://localhost:${port}/${linkedin_name:0:3}"
web_base_url="http://localhost:${port}"

if [[ -n "${port}" ]]; then
	logs=/tmp/${app}-test.out
	cd $mydir
	nohup ./mvnw spring-boot:run &> $logs&
	pid=$!
	echo "starting server. pid: $pid, logs: $logs"

	while [[ -n "$(assert-up.sh $web_base_url 2>&1)" ]]
	do
		echo "waiting for $web_base_url ... ctrl+c to skip"
		tail $logs
		sleep 5
	done
fi

#echo && echo "local server:"
#echo "$web_base_url"
#if [[ -n "$(assert-up.sh $web_base_url 2>&1)" ]]; then
#	echo "local server is not running, press any key to ignore, ctrl+c to stop"
#	read any
#fi

POST() {
	if [[ ! -n "$api_base_url" ]]; then
		echo "api_base_url is undefined"
		exit 1
	fi

	endpoint="${1/\//}"
	json="$2"
	curl -s -X POST -H 'Content-Type: application/json' "$api_base_url/$endpoint" -d "$json" > /dev/null
}

GET() {
	if [[ ! -n "$api_base_url" ]]; then
		echo "api_base_url is undefined"
		exit 1
	fi

	endpoint="${1/\//}"
	json="$2"
	curl -s "$api_base_url/$endpoint"
}

result() {
	test="$1"
	msg="$2"
	extra="$3"
	
	echo "##############"
	echo "-- ${FUNCNAME[1]}: $msg $extra"
	echo "${test}?"
	
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
	# >&2 echo "posting new objects ..."
	POST /skills '{"name": "zzz"}'
	POST /skills '{"name": "yyy"}'
	POST /skills '{"name": "aaa"}'
	
	response=$(GET '/skills')
	first=$(echo "$response" | jprop.sh "[0]['name']")
	result "[[ \"$first\" == \"aaa\" ]]"
}

# modificar GET /stats para retornar ordenado por total (decrescente) e avg (decrescente)
test_2_STATS_ORDER() {
	#echo "$web_base_url/stats/list.htm"
	
	response=$(GET '/stats')
	echo "$response" > /tmp/stats
	first=$(echo "$response" | jprop.sh "[0]['total']")
	last=$(echo "$response" | jprop.sh "[-1]['total']")
	
	result "[[ \"$last\" -lt \"$first\" ]]"
}

# corrigir GET /skills/like para filtrar por nome
test_3_SKILLS_LIKE() {
	like_search=y

	response=$(GET "/skills/like?id=$like_search")
	like=$(echo "$response" | jprop.sh "[0]['name']")

	if [[ ! -n "$like" ]]; then
		response=$(GET "/skills/like?name=$like_search")
		like=$(echo "$response" | jprop.sh "[0]['name']")
	fi

	echo "$response" > /tmp/skills.like
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
	exists_a=$(GET "/skills/name/$name" | jprop.sh "['name']")
	exists_b=$(GET "/skills?name=$name" | jprop.sh "['name']")
	
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
	exists_a=$(GET "/skills/exists/name/$name")
	exists_b=$(GET "/skills/exists?name=$name")
	
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

min=$((total_points/2+1))

result=FAILED
[[ $ok_points -eq $min ]] && result=LOW
[[ $ok_points -gt $min ]] && result=PASSED
[[ $ok_points -eq $total_points ]] && result=PERFECT

echo && echo "${result}: $ok_points/$total_points"

if [[ -n "$pid" ]]; then
	echo "press any key to terminate $web_base_url"
	read anyKey
	kill $pid
fi