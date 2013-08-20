@echo off
call ..\setenv.bat

rem run the data loader application
java -DJDBCURL=%JDBCURL% -cp dataloader.jar;%CLASSPATH% loader.ExampleDataLoader . ..\xml\companies.xml:..\xml\holdings.xml:..\xml\users.xml:..\xml\statistical.xml:..\xml\historical.xml
