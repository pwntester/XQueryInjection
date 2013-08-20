(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :) 

(: Import a module with a number of SQL Server SQL functions declared as external XQuery functions :) 
import module namespace ddtek-sql = "http://www.datadirect.com/xquery/sql-function" at "sqlfunctions_sqlserver.xq";

(: This example uses the sql-function POWER :) 
<power>{"POWER(2,3) =", ddtek-sql:POWER(2,3)}</power>
