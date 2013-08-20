(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :) 

(: This example uses the sql-function RTRIM :) 

(: First step declare the function as external, belonging to predefined name-space ddtek-sql :)
declare function ddtek-sql:RTRIM($namevalue as xs:string) as xs:string external;

for $username in collection('users')//lastname
(: create a value with trailing spaces and use the SQL RTRIM function to remove them :)
let $value := fn:concat($username, '    ')
return <last_name value="{$value}">{ddtek-sql:RTRIM($value)}</last_name>
