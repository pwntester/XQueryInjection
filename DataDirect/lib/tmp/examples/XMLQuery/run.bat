@echo off
rem SETUP ENVIRONMENT
call ../setenv.bat


rem COMPILE 
rem to recompile the example before running enable the next line
rem javac *.java

rem RUN
java XMLQuery %* %~dp0