@echo off
if "%JAVA_HOME%"=="" (
    call find_java.cmd
) else (
    echo JAVA_HOME is already set to: %JAVA_HOME%
)

call mvnw.cmd clean install
pause

