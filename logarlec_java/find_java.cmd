@echo off
setlocal

set "JAVA_EXE=java.exe"
set "JAVA_HOME="

for /f "tokens=*" %%i in ('where %JAVA_EXE%') do (
    set "JAVA_HOME=%%~dpi.."
)

if not "%JAVA_HOME%"=="" (
    echo Java is installed at: %JAVA_HOME%
    setx JAVA_HOME "%JAVA_HOME%" /m
) else (
    echo Java is not found on this system.
)

endlocal