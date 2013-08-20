(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :) 

(: Import a module with a number of Informix functions declared as external XQuery functions :) 
import module namespace ddtek-sql = "http://www.datadirect.com/xquery/sql-function" at "sqlfunctions_informix.xq";

(: This example uses the sql-function REPLACE :) 
for $user in collection('users')//users
let $name := fn:concat($user/firstname, ' ', $user/lastname)
return <name>{ddtek-sql:REPLACE($name, 'Carlo', 'Mr.')}</name>
