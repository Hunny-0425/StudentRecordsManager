@echo off
if not exist bin mkdir bin
(for /r src %%f in (*.java) do @echo %%f)>sources.txt
javac -d bin @sources.txt
java -cp bin edu.studentmanager.cli.Main
