@echo off
rem Find and set JAVA_HOME
call setjavahome.cmd

rem Make .jar file
call mvnw.cmd clean install

rem Execute
call mvnw.cmd exec:java