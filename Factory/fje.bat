@echo off
javac -encoding UTF-8 -d bin src/*.java
cd bin
java FunnyJSONExplorer %1 %2 %3 %4 %5 %6
cd ../
pause
