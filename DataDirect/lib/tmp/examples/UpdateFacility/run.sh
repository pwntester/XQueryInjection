#!/bin/ksh

# SETUP ENVIRONMENT
. ../setenv.sh

# COMPILE 
# to recompile the example before running enable the next line
#javac *.java

# RUN
java XQueryUpdate $* `dirname \`which $0\``
