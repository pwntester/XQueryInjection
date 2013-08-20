(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :) 

(: Import a module with a number of MySQL SQL functions declared as external XQuery functions :) 
import module namespace ddtek-sql = "http://www.datadirect.com/xquery/sql-function" at "sqlfunctions_mysql.xq";

(: This example uses the MySQL-function CRC32 :) 
<crctest>{ddtek-sql:CRC32('XQuery')}</crctest>
