#!/bin/sh
mkdir -p bin
find src -name "*.java" > sources.txt
javac -d bin @sources.txt
java -cp bin edu.studentmanager.cli.Main
