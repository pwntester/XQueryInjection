(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :) 

(: Import a module with a number of DB2/LUW SQL functions declared as external XQuery functions :) 
import module namespace ddtek-sql = "http://www.datadirect.com/xquery/sql-function" at "sqlfunctions_db2luw.xq";

(: This example uses the sql-function UPPER :) 
for $username in collection('users')//lastname
return <name value="{$username}">{ddtek-sql:UPPER($username)}</name>
