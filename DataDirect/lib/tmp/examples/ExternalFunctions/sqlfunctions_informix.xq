(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :) 
(: This module contains function declarations for a number of Informix functions :) 
module namespace ddtek-sql = "http://www.datadirect.com/xquery/sql-function"; 

(: aggregate functions :)
declare function ddtek-sql:AVG($p1 as xs:decimal*) as xs:decimal? external; 
declare function ddtek-sql:MAX($p1 as xs:decimal*) as xs:decimal? external; 
declare function ddtek-sql:MIN($p1 as xs:decimal*) as xs:decimal? external; 
declare function ddtek-sql:SUM($p1 as xs:decimal*) as xs:decimal? external; 
declare function ddtek-sql:RANGE($p1 as xs:decimal*) as xs:decimal? external; 
declare function ddtek-sql:STDEV($p1 as xs:decimal*) as xs:decimal? external; 
declare function ddtek-sql:VARIANCE($p1 as xs:decimal*) as xs:decimal? external; 

(: datetime functions :) 
declare function ddtek-sql:DATE($p1 as xs:string?) as xs:dateTime? external;
declare function ddtek-sql:DAY($p1 as xs:dateTime) as xs:integer external;
declare function ddtek-sql:MONTH($p1 as xs:dateTime) as xs:integer external;
declare function ddtek-sql:YEAR($p1 as xs:dateTime) as xs:integer external;

(: numeric functions :) 
declare function ddtek-sql:ABS($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:MOD($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external;
declare function ddtek-sql:POW($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external;
declare function ddtek-sql:ROOT($p1 as xs:decimal?) as xs:decimal? external;
declare function ddtek-sql:ROOT($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external;
declare function ddtek-sql:ROUND($p1 as xs:decimal?) as xs:decimal? external;
declare function ddtek-sql:ROUND($p1 as xs:decimal?, $p2 as xs:integer?) as xs:decimal? external;
declare function ddtek-sql:SQRT($p1 as xs:decimal?) as xs:decimal? external;
declare function ddtek-sql:TRUNC($p1 as xs:decimal?) as xs:decimal? external;
declare function ddtek-sql:TRUNC($p1 as xs:decimal?, $p2 as xs:integer?) as xs:decimal? external;
declare function ddtek-sql:EXP($p1 as xs:double?) as xs:double? external;
declare function ddtek-sql:LOGN($p1 as xs:double?) as xs:double? external;
declare function ddtek-sql:LOG10($p1 as xs:double?) as xs:double? external;

(: string functions :) 
declare function ddtek-sql:SUBSTR($p1 as xs:string?, $p2 as xs:integer?) as xs:string? external;
declare function ddtek-sql:SUBSTR($p1 as xs:string?, $p2 as xs:integer?, $p3 as xs:integer?) as xs:string? external;
declare function ddtek-sql:REPLACE($p1 as xs:string?, $p2 as xs:string?, $p3 as xs:string?) as xs:string? external;
declare function ddtek-sql:RPAD($p1 as xs:string?, $p2 as xs:integer?, $p3 as xs:string?) as xs:string? external;
declare function ddtek-sql:LPAD($p1 as xs:string?, $p2 as xs:integer?, $p3 as xs:string?) as xs:string? external;
declare function ddtek-sql:INITCAP($p1 as xs:string?) as xs:string? external;
declare function ddtek-sql:LOWER($p1 as xs:string?) as xs:string? external;
declare function ddtek-sql:UPPER($p1 as xs:string?) as xs:string? external;


