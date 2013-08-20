     Examples README 
     DataDirect XQuery Example Java Applications
     DataDirect XQuery, Release 5.0
     DataDirect Technologies
     October 2009


This README contains instructions for setting up and running the 
DataDirect XQuery example Java applications. 

CONTENTS

Required Software
Configuring Your Environment to Run the Examples
About the Examples


     Required Software

Database 
--------
The 5.0 release of the DataDirect XQuery examples supports specific 
versions of IBM DB2, Informix, Microsoft SQL Server, MySQL, Oracle, 
PostgreSQL, and Sybase. Refer to the DATADIRECT XQUERY USER'S GUIDE 
AND REFERENCE for information about supported database versions.

DataDirect XQuery
-----------------
You must install DataDirect XQuery 5.0 before using the DataDirect 
XQuery 5.0 examples. DataDirect XQuery requires J2SE 1.4.x or later.

    
     Configuring Your Environment to Run the Examples

Complete the following steps to configure your environment for use with 
the examples.

1. Configure a database instance to connect to and privileges to create 
and drop tables. You must have a user ID and password for an account 
that has permission to create tables, drop tables, and insert rows of 
data. The data loader provided with the examples will create and drop 
tables used by the examples.

IMPORTANT: The examples data loader drops and creates the following
database tables: "users", "companies", "historical", "holdings", and 
"statistical".

2. Modify the setenv.bat (Windows) or setenv.sh (UNIX/Linux) file. In 
addition, you must set the JDBCURL environment setting to the 
connection URL of the test database to which you want to connect. 
See the comments in the setenv file for examples.

Optionally, you may need to set:

* The PATH2LIBS environment setting to the DataDirect XQuery 
lib subdirectory:
 
<ddxq_install_directory>/lib

If you are executing the examples and dataloader from their respective 
directories, you do not need to modify this value.
       
* The PATH variable so that the Java executable is available from the 
command line. If you must recompile the examples, PATH should also 
contain the path that specifies the location of the Java compiler 
(javac) executable.
 
3. Populate the test database. To load the data, execute:

<ddxq_install_directory>/examples/DataLoader/load_example_data.bat 
(Windows) 

or
 
<ddxq_install_directory>/examples/DataLoader/load_example_data.sh 
(UNIX/Linux)

Creation of the database table and data upload may take as few as 
30 seconds; however, depending on the speed of your network, database 
server, and workstation, the process may take several minutes.

NOTES: 

* The data loader will create the following database tables: 
"users", "companies", "historical", "holdings", and "statistical".

* Using the data loader with a Java VM that was started with the 
-server option can crash the Java VM. This is caused by a Java bug that 
can occur with any supported JDK version and on different platforms. 
For information about workarounds for this issue, contact Technical 
Support.


     About the Examples

Examples are located in subdirectories of the examples directory:

<ddxq_install_directory>/examples/<name_of_directory>

For example:

ddxq/examples/xqjexecute

Each example subdirectory contains the following files:

* run.bat or run.sh to execute the example program. This file contains 
a commented line, that when uncommented, recompiles the example.

* Zero or more .xq files that contain the XQuery expressions that will 
be executed by the example program.

* Zero or more .xml files that contain XML files that will be queried 
by the XQuery expressions.

* One or more source and class files that contain the source and 
compiled source code for the example.

The following examples are provided with the product:

* XQJExecute is a simple XQJ example that shows how to execute an 
XQuery expression from a file or string and retrieve the results as a 
string.

* ResultRetrieval shows how you can retrieve the results of an XQuery 
expression as SAX, StAX, or DOM.

* ExternalVariables shows binding Java variables to external XQuery 
variables.

* ExternalFunctions shows different types of external functions 
supported by DataDirect XQuery and how they can be used.

* Connect demonstrates some of DataDirect XQuery's advanced connection 
options.

* CustomURIResolver shows how to implement a custom URI resolver.

* JNDIDataSource shows how to persist and load a DDXQDataSource using a 
JNDI provider.

* RDBMSUpdate shows how to insert, update, and delete data stored in a 
relational database.

* XMLQuery shows how to query data in XML documents.

* UpdateFacility shows how to insert, rename and change data of nodes stored in XML a document.

For information about how to use each of these examples, see the 
following sections.

XQJExecute
----------
NOTE: When you run this example, a library module is imported
(LibraryModule.xq). When installing on a z/OS system, this module
is installed in EBCDIC encoding without an explicit encoding
declaration. The example reads the encoding as UTF-8, which causes
a sun.io.MalformedInputException at sun.io.ByteToCharUTF8.convert().
To workaround this issue, add an encoding declaration to the library
module, for example, add a first line as:
xquery version "1.0" encoding "IBM1047";

From /examples/xqjexecute, enter the following command line to execute 
the example:

Windows:

run.bat [<xquery_file>]

UNIX:

run.sh [<xquery_file>]

where <xquery_file> is one of the following query files in the XQJExecute 
directory:

* collection-users.xq returns all data from the users table.
* collection-holdings.xq returns all data from the holdings table.
* flwor.xq joins data from two relational tables using a nested FLWOR 
expression.
* JoinXMLToRelational.xq joins data from an XML file with data from a 
relational table.
* function.xq uses local function declarations.
* portfolioHTML.xq serializes the query result as HTML.
* MainModule.xq and LibraryModule.xq use XQuery modules.
* nodeId.xq uses a path expression to eliminate duplicate XML nodes.

If you do not specify an XQuery file in the command line, a prompt
is displayed asking you to enter a number that corresponds to the
XQuery file you want to execute. Or, you can enter the number "9" and
then type in the text of your own query, which will be executed.

ResultRetrieval
---------------
From the ResultRetrieval directory, enter the following command line to 
execute the example:

Windows:

run.bat

UNIX:

run.sh

A prompt is displayed asking you to enter one of the following numbers 
that correspond to the type of retrieval method you want to use:

1 - SAX
2 - STAX
3 - DOM

The results of the retrieval method are displayed in the standard 
output.

ExternalVariables
-----------------
From the ExternalVariables directory, enter the following command line 
to execute the example:

Windows:

run.bat

UNIX:

run.sh

A prompt is displayed asking you to enter one of the following numbers
that correspond to the type of external variable you want to execute:

1 - Bind an xs:int external variable
2 - Bind an xs:string external variable
3 - Bind a DOM node to an external variable
4 - Bind an XQItem to an external variable
5 - Bind an XQSequence to an external variable

ExternalFunctions
-----------------
From the ExternalFunctions directory, enter the following command line 
to execute the example:

Windows:

run.bat [<xquery_file>]

UNIX:

run.sh [<xquery_file>]

where <xquery_file> is one of the following query files in the
ExternalFunctions directory:

* javaFunction.xq invokes a static Java method.

* javaInstanceMethod.xq invokes a Java instance method.

* sqlFunction.xq invokes a SQL function.

* sqlFunctionFromModule_db2luw.xq invokes a SQL function from a module 
that declares SQL functions for DB2 for Linux/UNIX/Windows.

* sqlFunctionFromModule_informix.xq invokes a SQL function from a 
module that declares SQL functions for Informix.

* sqlFunctionFromModule_mysql.xq invokes a SQL function from a module 
that declares SQL functions for MySQL.

* sqlFunctionFromModule_ora.xq, invokes a SQL function from a module 
that declares SQL functions for Oracle.

* sqlFunctionFromModule_postgresql.xq invokes a SQL function from a 
module that declares SQL functions for PostgreSQL.

* sqlFunctionFromModule_sqlserver.xq invokes a SQL function from a 
module that declares SQL functions for Microsoft SQL Server.

* jdbcEscapeFunction.xq invokes a JDBC escape function.

If you do not specify an XQuery file in the command line, a prompt is 
displayed asking you to enter a number that corresponds to the XQuery 
file you want to execute.

The result of the query is written to standard output.

Connect
-------
Before using this example, you must edit two source configuration 
located in the Connect directory:

* config_basic.xml 
* config_advanced.xml

You must specify the correct values for the following elements in these 
files: url, user, and password. In addition, in the config_advanced.xml 
file, you must enter the correct values for the name attributes for the 
catalog and schema elements. Refer to the DATADIRECT XQUERY USER'S 
GUIDE AND REFERENCE for more information about source configuration 
files.

From the Connect directory, enter the following command line to execute 
the example:

Windows:

run.bat

UNIX:

run.sh

A prompt is displayed asking you to enter one of the following numbers
That corresponds to the connection method you want to use:

1 - Connect using config_basic.xml source configuration file.
2 - Connect using config_advanced.xml source configuration file, 
    this example shows the use of a table alias.
3 - Connect using config_advanced.xml source configuration file,
    this example shows the use of a target namespace for a 
    database table.
4 - Connect using config_advanced.xml source configuration file,
    this example shows how to eliminate certain table columns from
    the SQL/XML view of the table.
5 - Connect using config_advanced.xml source configuration file,
    this example shows the use of a base URI.
6 - Connect using config_advanced.xml source configuration file,
    this example illustrates the use of DataDirect Spy for XQJ.

CustomDocumentURIResolver
-------------------------
From the CustomURIResolver directory, enter the following
command line to execute the example:

Windows:

run.bat [<xquery_file>]

UNIX:

run.sh [<xquery_file>]

where <xquery_file> is the XQuery file you want to execute. If you do not
specify a query file name, the XQuery file in the CustomURIResolver 
directory is executed. Optionally, you can write your own query, save 
it to a file, and execute that XQuery file by entering the file name as 
the argument to run.bat or run.sh.

This example returns an XML document that contains a top-level 
directory element and a child element named file that represents each 
XML file found in the specified directory. The file element has a size 
attribute with a value of the file size in bytes, and the value of the 
file element is the name of the XML file. For example:

<directory>
  <file size="10000">one.xml</file>
  <file size="15550">two.xml</file>
</directory>

The resulting XML structure can be used in other XQuery expressions,
as shown in the query of this example.

JNDIDataSource
--------------
Before using this example, you must have a JNDI provider on your 
machine. If you do not have one, you can download one from:

http://javashoplm.sun.com/ECom/docs/Welcome.jsp?StoreId=22&PartDetailId
=7110-jndi-1.2.1-oth-JPR&SiteId=JSC&TransactionId=noreg

You must place the providerutil.jar and fscontext.jar files in your
CLASSPATH. You can edit the setenv.bat (Windows) or setenv.sh 
(UNIX/Linux) files to do this.

From the JNDIDataSource directory, enter the following command line to 
execute the example:

Windows:

run.bat

UNIX:

run.sh

RDBMSUpdate
-----------
From the RDBMSUpdate directory, enter the following command line to 
execute the example:

Windows:

run.bat [<xquery_file>]

UNIX:

run.sh [<xquery_file>]

where <xquery_file> is one of the following query files in the 
RDBMSUpdate directory:

* insert-holdings.xq inserts data in the holdings table.
* update-holdings.xq updates data in the holdings table.
* delete-holdings.xq deletes data in the holdings table.
* update-holdings-from-xml.xq uses data provided in an XML document to 
  update data in the holdings table.
* update-function.xq updates data in the holdings table using a 
  user-defined function.
* shredding-xml.xq shreds data provided in an XML document into 
  multiple tables.

If you do not specify a query file name, a prompt appears asking you to 
enter the number that corresponds to the XQuery file you want to use:

1 - insert-holdings.xq
2 - update-holdings.xq
3 - delete-holdings.xq 
4 - update-holdings-from-xml.xq
5 - update-function.xq
6 - shredding-xml.xq

Optionally, you can write your own query, save it to a file, and 
execute that XQuery file by entering the file name as the argument to 
run.bat or run.sh.

XMLQuery
--------
From the XMLQuery directory, enter the following command line to 
execute the example:

Windows:

run.bat [<xquery_file>]

UNIX:

run.sh [<xquery_file>]

where <xquery_file> is one of the following query files in the 
RDBMSUpdate directory:

* query-initial-context.xq queries the initial context document.
* query-doc-function.xq queries an XML document using the 
  fn:doc() function.
* query-external-variable.xq queries an XML document using an 
  external variable.
* query-directory.xq queries multiple XML documents contained in the 
  same directory.
* query-pipeline-1.xq uses the result of a one XQuery to provide input
  into another XQuery.

If you do not specify a query file name, a prompt appears asking you to 
enter the number that corresponds to the XQuery file you want to use:

1 - query-initial-context.xq
2 - query-doc-function.xq
3 - query-external-variable.xq
4 - query-directory.xq
5 - query-pipeline-1.xq

Optionally, you can write your own query, save it to a file, and 
execute that XQuery file by entering the file name as the argument to 
run.bat or run.sh.

UpdateFacility
--------------
From the UpdateFacility directory, enter the following command line to 
execute the example:

Windows:

run.bat [<xquery_file>]

UNIX:

run.sh [<xquery_file>]

where <xquery_file> is one of the following query files in the 
RDBMSUpdate directory:

* change-values.xq grants 20% more shares to "Minollo" and save the result to a new XML document.
* insert-nodes.xq grants all users 1000 shares of "DDTEK"; if they already own them, increase the number. Save the result to a new XML document
* rename-nodes.xq renames UserId elements to "ID" elements and save the result to a new XML document.
* transform-change-values.xq grants 20% more shares to "Minollo" transforming the result returned by the XQuery.
* transform-insert-nodes.xq grants all users 1000 shares of "DDTEK"; if they already own them, increase the number. Return the new/modified nodes

If you do not specify a query file name, a prompt appears asking you to 
enter the number that corresponds to the XQuery file you want to use:

1 - change-values.xq
2 - insert-nodes.xq
3 - rename-nodes.xq
4 - transform-change-values.xq
5 - transform-insert-nodes.xq

Optionally, you can write your own query, save it to a file, and 
execute that XQuery file by entering the file name as the argument to 
run.bat or run.sh.


~~~~~~~~~~~
End of File
