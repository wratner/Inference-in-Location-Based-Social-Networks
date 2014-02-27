#!/bin/bash

COMP="/software/sun-jdk-1.6.0-latest-el6-x86_64/bin/javac"
HAMCREST="./libs/hamcrest-core-1.3.jar"
JUNIT="./libs/junit-4.11.jar"
CLASSPATH=".:$HAMCREST:$JUNIT"
OUTDIR="./bin"

EXTRA=""

FILES="src/uiuc/cs463sp14/mp1/*.java src/uiuc/cs463sp14/mp1/test/*.java"

mkdir "$OUTDIR" 2> /dev/null

cmd=`echo "$COMP" "$EXTRA" -classpath "$CLASSPATH" -d "$OUTDIR" "$FILES"`
echo "Compilation command: \"$cmd\" ";

echo "-------------------------------"

$cmd

if [ $? -eq 0 ]; then
	echo "Compilation succeeded!";
else
	echo "Compilation failed!";
fi
