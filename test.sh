#!/bin/bash
mydir=$(dirname `readlink -f $0`)

ascii_code() {
  LC_CTYPE=C printf '%d' "'$1"
}

gprop() {
	grep "$1" $mydir/src/main/resources/application.properties | cut -d'=' -f2
}

linkedin_name=$(gprop 'linkedin.name')
port=$(gprop 'server.port')

name_1=$(ascii_code ${linkedin_name:0:1})
name_port="${name_1}80"

base_url="http://localhost:${name_port}/${linkedin_name:0:3}"

test_0a() {
	if [ -n "$linkedin_name" ]; then
		echo "0 a: OK"
	else
		echo "0 a: FAIL"
	fi
}

test_0b() {
	if [ "$port" == "$name_port" ]; then
		echo "0 b: OK"
	else
		echo "0 b: FAIL"
	fi
}

test_0c() {
	if [ $(gprop "linkedin.name") == ${linkedin_name:0:3} ]; then
		echo "0 c: OK"
	else
		echo "0 c: FAIL"
	fi
}

test_1() {
	curl -X POST "$base_url/skills -d {\"name\": \"zzz\"}"
	curl -X POST "$base_url/skills -d {\"name\": \"yyy\"}"
	curl -X POST "$base_url/skills -d {\"name\": \"aaa\"}"
	
	first=$(curl "$base_url/skills" | python -m json.tool | python -c "import sys, json; print json.load(sys.stdin)[0]['name']")
	
	if [ $first == "aaa" ]; then
		echo "1: OK"
	else
		echo "1: FAIL"
	fi
}

test_2() {
	echo "2: não implementado"
}

test_3() {
	like=$(curl "$base_url/skills/like?id=y" | python -m json.tool | python -c "import sys, json; print json.load(sys.stdin)[0]['name']")
	if [ "$like" == yyy ]; then
		echo "3: OK"
	else
		echo "3: FAIL"
	fi
}

test_4() {
	points=0
	
	GET_ALL=$(curl -I -X GET "$base_url/jobs" | head -n 1 |cut -d$' ' -f2)
	GET_ONE=$(curl -I -X GET "$base_url/jobs/1" | head -n 1 |cut -d$' ' -f2)
	POST=$(curl -I -X POST "$base_url/jobs" | head -n 1 |cut -d$' ' -f2)
	DEL=$(curl -I -X DELETE "$base_url/jobs/0" | head -n 1 |cut -d$' ' -f2)
	
	if [ "$GET_ALL" != 404 ]; then
		points=$((points+1))
	fi
	
	if [ "$GET_ONE" != 404 ]; then
		points=$((points+1))
	fi
	
	if [ "$POST" != 404 ]; then
		points=$((points+1))
	fi
	
	if [ "$DEL" != 404 ]; then
		points=$((points+1))
	fi
	
	if [ $points == 4 ]; then
		echo "4: OK"
	else
		echo "4: FAIL ($points/4)"
	fi
}

test_4_1() {
	echo "4.1: não implementado"
}

test_4_2() {
	page=$(find $mydir -name "list.htm" | grep -c jobs)
	if [[ "$page" -gt 0 ]]; then
		echo "4.2: OK"
	else
		echo "4.2: FAIL"
	fi
}

test_0a
test_0b
test_0c
test_1
test_2
test_3
test_4
test_4_1
test_4_2
