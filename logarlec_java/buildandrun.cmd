@echo off
setlocal

rem Check if JAVA_HOME is already set
if "%JAVA_HOME%"=="" (
    rem If JAVA_HOME is not set, call the script to find it
    call find_java.cmd
) else (
    echo JAVA_HOME is already set to: %JAVA_HOME%
)

rem Run other commands here...

endlocal
rem Make .jar file
call mvnw.cmd clean install

rem Execute
call mvnw.cmd exec:java