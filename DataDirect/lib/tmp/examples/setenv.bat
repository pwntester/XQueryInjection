@echo off
rem set your environment variables here

rem this is a an example of the jars needed for 
rem the data loader application and the example applications to run

rem # To run the JNDI example, you'll need to install the JNDI libraries, and put something like:
rem %PATH2LIBS%\providerutil.jar;%PATH2LIBS%\fscontext.jar in your classpath variable below

rem # using relative path to the libs directory
set PATH2LIBS=..\..\lib

rem # adding the product jars
set CLASSPATH=.;%PATH2LIBS%\ddxq.jar;%PATH2LIBS%\jsr173_1.0_api.jar;

rem # add postgresql jar to classpath

rem # Adding examples directories to classpath: contains a Utility-class
set CLASSPATH=%CLASSPATH%;..\XQJExecute

rem # examples of jdbc urls
rem set JDBCURL=jdbc:xquery:sqlserver://hostname_or_ip:1433;databaseName=example_db_name;user=example_user;password=example_password
rem set JDBCURL=jdbc:xquery:oracle://hostname_or_ip:1521;SID=orcl_sid_name;user=example_user;password=example_password
rem set JDBCURL=jdbc:xquery:db2://hostname_or_ip:50000;databaseName=example_db_name;user=example_user;password=example_password
rem set JDBCURL=jdbc:xquery:db2://hostname_or_ip:446;locationName=my_400_machine_location;PackageCollection=the_package_collection_name;user=example_user;password=example_password
rem set JDBCURL=jdbc:xquery:sybase://hostname_or_ip:4100;databaseName=example_db_name;user=example_user;password=example_password
rem set JDBCURL=jdbc:xquery:informix://hostname_or_ip:1526;informixserver=example_server;databaseName=example_db_name;user=example_user;password=example_password
rem set JDBCURL=jdbc:xquery:mysql://hostname_or_ip;databaseName=example_db_name;user=example_user;password=example_password
rem set JDBCURL="jdbc:postgresql://hostname_or_ip:5432/example_db_name?user=example_user&password=example_password"

set JDBCURL=configure_jdbc_url_in_setenv_bat
