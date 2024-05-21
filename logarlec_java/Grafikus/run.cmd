@echo off
cd Controller
call mvnw.cmd exec:java
cd ..
pause