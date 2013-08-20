#!/bin/ksh
# set your environment variables here

# this is a an example of the jars needed for 
# the data loader application and the example applications to run

# To run the JNDI example, you'll need to install the JNDI libraries, and put something like:
# $PATH2LIBS/providerutil.jar:$PATH2LIBS/fscontext.jar in your classpath variable below

# using relative path to the libs directory
PATH2LIBS="../../lib"

# adding the product jars
CLASSPATH=.:$PATH2LIBS/ddxq.jar:$PATH2LIBS/jsr173_1.0_api.jar

# add postgresql jar
#CLASSPATH=$CLASSPATH:postgresql.jar

# Adding examples directories to classpath: contains a Utility-class
CLASSPATH=$CLASSPATH:../XQJExecute

#examples of jdbc urls
#JDBCURL="jdbc:xquery:sqlserver://hostname_or_ip:1433;databaseName=example_db_name;user=example_user;password=example_password"
#JDBCURL="jdbc:xquery:oracle://hostname_or_ip:1521;SID=orcl_sid_name;user=example_user;password=example_password"
#JDBCURL="jdbc:xquery:db2://hostname_or_ip:50000;databaseName=example_db_name;user=example_user;password=example_password"
#JDBCURL="jdbc:xquery:db2://hostname_or_ip:446;locationName=my_400_machine_location;PackageCollection=the_package_collection;user=example_user;password=example_password"
#JDBCURL="jdbc:xquery:sybase://hostname_or_ip:4100;databaseName=example_db_name;user=example_user;password=example_password"
#JDBCURL="jdbc:xquery:mysql://hostname_or_ip;databaseName=example_db_name;user=example_user;password=example_password"
#JDBCURL="jdbc:xquery:informix://hostname_or_ip:1526;informixserver=example_server;databaseName=example_db_name;user=example_user;password=example_password"
#JDBCURL="jdbc:postgresql://hostname_or_ip:5432/databaseName=example_db_name?user=example_user&password=example_password"
JDBCURL="configure_jdbc_url_in_setenv_sh"

export CLASSPATH
export JDBCURL
