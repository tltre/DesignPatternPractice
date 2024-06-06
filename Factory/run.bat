@echo off
javac -encoding UTF-8 -d bin src/*.java
cd bin
java FunnyJSONExplorer -- -f ../../test.json -s rec -i test
cd ../
pause