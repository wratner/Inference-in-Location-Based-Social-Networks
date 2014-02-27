#!/bin/bash

RUN="/software/sun-jdk-1.6.0-latest-el6-x86_64/bin/java"
HAMCREST="./libs/hamcrest-core-1.3.jar"
JUNIT="./libs/junit-4.11.jar"
EKMEANS="./libs/ekmeans-0.3.jar"
OUTDIR="./bin"
CLASSPATH=".:$OUTDIR:$HAMCREST:$JUNIT"

EXTRA=""

MAINCLASS="uiuc/cs463sp14/mp1/MP1Test"

if [ ! -d "$OUTDIR" ]; then
    echo "Class files directory does not exist: run 'compile.sh' first, exiting...";
    exit 1;
fi


cmd=`echo "$RUN" "$EXTRA" -classpath "$CLASSPATH" "$MAINCLASS"`
echo "Run command: \"$cmd\" ";

echo "--------------------------";

$cmd

echo "--------------------------"; echo "";


TESTCLASS="uiuc/cs463sp14/mp1/test/MP1TestRunner"
cmd=`echo "$RUN" "$EXTRA" -classpath "$CLASSPATH" "$TESTCLASS"`
echo "Tests run command: \"$cmd\" ";

echo "--------------------------";

$cmd

echo "--------------------------"; echo "";
