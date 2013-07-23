#!/bin/bash

CLASSPATH=.
for JAR in `ls ../lib/*.jar`; do
    CLASSPATH=$CLASSPATH:$JAR;
done;

$JAVA_HOME/bin/java -classpath $CLASSPATH com.jdevelop.jpicasa.Main ${*}
