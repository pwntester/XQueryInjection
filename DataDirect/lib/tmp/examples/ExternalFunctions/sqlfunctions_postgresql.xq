(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :) 
(: This module contains function declarations for a number of Postgresql functions :) 
module namespace ddtek-sql = "http://www.datadirect.com/xquery/sql-function"; 

(: aggregate functions :)
declare function ddtek-sql:AVG($p1 as xs:decimal*) as xs:decimal? external;  
declare function ddtek-sql:BIT_AND($p1 as xs:decimal*) as xs:decimal? external;
declare function ddtek-sql:BIT_OR($p1 as xs:decimal*) as xs:decimal? external;
declare function ddtek-sql:BOOL_AND($p1 as xs:boolean+) as xs:boolean external;
declare function ddtek-sql:BOOL_OR($p1 as xs:boolean+) as xs:boolean external;
declare function ddtek-sql:COUNT($p1 as xs:string*) as xs:int external;   
declare function ddtek-sql:EVERY($p1 as xs:boolean+) as xs:boolean external;
declare function ddtek-sql:MAX($p1 as xs:decimal*) as xs:decimal? external;   
declare function ddtek-sql:MIN($p1 as xs:decimal*) as xs:decimal? external;   
declare function ddtek-sql:SUM($p1 as xs:decimal*) as xs:decimal? external;   

(: datetime functions :) 
declare function ddtek-sql:DATE_PART($p1 as xs:string, $p2 as xs:dateTime) as xs:double external;
declare function ddtek-sql:DATE_TRUNC($p1 as xs:string, $p2 as xs:dateTime) as xs:dateTime external;
declare function ddtek-sql:ISFINITE($p1 as xs:dateTime) as xs:boolean external;
declare function ddtek-sql:NOW() as xs:dateTime external;
declare function ddtek-sql:STATEMENT_TIMESTAMP() as xs:dateTime external;
declare function ddtek-sql:TIMEOFDAY() as xs:string external;
declare function ddtek-sql:TRANSACTION_TIMESTAMP() as xs:dateTime external;  

(: numeric functions :) 
declare function ddtek-sql:ABS($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:CBRT($p1 as xs:double?) as xs:souble? external;
declare function ddtek-sql:CEIL($p1 as xs:double?) as xs:double? external; 
declare function ddtek-sql:CEILING($p1 as xs:double?) as xs:double? external; 
declare function ddtek-sql:DEGREES($p1 as xs:double?) as xs:double? external;
declare function ddtek-sql:EXP($p1 as xs:double?) as xs:double? external; 
declare function ddtek-sql:FLOOR($p1 as xs:double?) as xs:double? external; 
declare function ddtek-sql:LN($p1 as xs:double?) as xs:double? external;
declare function ddtek-sql:LOG($p1 as xs:double?) as xs:double? external; 
declare function ddtek-sql:LOG($p1 as xs:double?, $p2 as xs:double?) as xs:double? external; 
declare function ddtek-sql:MOD($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:PI() as xs:double? external; 
declare function ddtek-sql:POWER($p1 as xs:double?, $p2 as xs:double?) as xs:double? external; 
declare function ddtek-sql:RADIANS($p1 as xs:double?) as xs:double? external; 
declare function ddtek-sql:RANDOM() as xs:double? external;
declare function ddtek-sql:ROUND($p1 as xs:double?) as xs:double? external; 
declare function ddtek-sql:ROUND($p1 as xs:decimal?, $p2 as xs:int?) as xs:decimal? external; 
declare function ddtek-sql:SETSEED($p1 as xs:double) as xs:int external;
declare function ddtek-sql:SIGN($p1 as xs:double?) as xs:double? external;
declare function ddtek-sql:SQRT($p1 as xs:double?) as xs:double? external;
declare function ddtek-sql:TRUNC($p1 as xs:double?) as xs:double? external;
declare function ddtek-sql:TRUNC($p1 as xs:decimal?, $p2 as xs:integer) as xs:decimal? external;
declare function ddtek-sql:WIDTH_BUCKET($p1 as xs:decimal?, $p2 as xs:decimal?, $p3 as xs:decimal?, $p4 as xs:integer?) as xs:integer external;   

(: string functions :) 
declare function ddtek-sql:BIT_LENGTH($p1 as xs:string?) as xs:int? external;
declare function ddtek-sql:CHAR_LENGTH($p1 as xs:string?) as xs:int? external;
declare function ddtek-sql:LOWER($p1 as xs:string?) as xs:int? external;
declare function ddtek-sql:OCTET_LENGTH($p1 as xs:string?) as xs:int? external;
declare function ddtek-sql:UPPER($p1 as xs:string?) as xs:int? external;
declare function ddtek-sql:ASCII($p1 as xs:string?) as xs:int? external; 
declare function ddtek-sql:BTRIM($p1 as xs:string?, $p2 as xs:string?) as xs:string? external; 
declare function ddtek-sql:CHR($p1 as xs:int?) as xs:string? external;  
declare function ddtek-sql:CONVERT($p1 as xs:string?, $p2 as xs:string?) as xs:string? external;  
declare function ddtek-sql:CONVERT($p1 as xs:string?, $p2 as xs:string?, $p3 as xs:string?) as xs:string? external;
declare function ddtek-sql:DECODE($p1 as xs:string?, $p2 as xs:string?) as xs:hexBinary? external;
declare function ddtek-sql:ENCODE($p1 as xs:hexBinary?, $p2 as xs:string?) as xs:string? external;
declare function ddtek-sql:INITCAP($p1 as xs:string?) as xs:string? external;
declare function ddtek-sql:LENGTH($p1 as xs:string?) as xs:int? external;      
declare function ddtek-sql:LPAD($p1 as xs:string?, $p2 as xs:int?) as xs:string? external; 
declare function ddtek-sql:LPAD($p1 as xs:string?, $p2 as xs:int?, $p3 as xs:string?) as xs:string? external; 
declare function ddtek-sql:LTRIM($p1 as xs:string?) as xs:string? external;
declare function ddtek-sql:LTRIM($p1 as xs:string?, $p2 as xs:string?) as xs:string? external;   
declare function ddtek-sql:MD5($p1 as xs:string?) as xs:string? external;
declare function ddtek-sql:PG_CLIENT_ENCODING() as xs:string? external;  
declare function ddtek-sql:QUOTE_IDENT($p1 as xs:string?) as xs:string? external;
declare function ddtek-sql:QUOTE_LITERAL($p1 as xs:string?) as xs:string? external;
declare function ddtek-sql:REGEXP_REPLACE($p1 as xs:string?, $p2 as xs:string?, $p3 as xs:string?) as xs:string? external;
declare function ddtek-sql:REGEXP_REPLACE($p1 as xs:string?, $p2 as xs:string?, $p3 as xs:string?, $p4 as xs:string?) as xs:string? external;
declare function ddtek-sql:REPEAT($p1 as xs:string?, $p2 as xs:int?) as xs:string? external;
declare function ddtek-sql:REPLACE($p1 as xs:string?, $p2 as xs:string?, $p3 as xs:string?) as xs:string? external;
declare function ddtek-sql:RPAD($p1 as xs:string?, $p2 as xs:int?) as xs:string? external; 
declare function ddtek-sql:RPAD($p1 as xs:string?, $p2 as xs:int?, $p3 as xs:string?) as xs:string? external; 
declare function ddtek-sql:RTRIM($p1 as xs:string?) as xs:string? external;
declare function ddtek-sql:RTRIM($p1 as xs:string?, $p2 as xs:string?) as xs:string? external;   
declare function ddtek-sql:SPLIT_PART($p1 as xs:string?, $p2 as xs:string?, $p3 as xs:int?) as xs:string? external;
declare function ddtek-sql:STRPOS($p1 as xs:string?, $p2 as xs:string?) as xs:int? external;
declare function ddtek-sql:SUBSTR($p1 as xs:string?, $p2 as xs:int?) as xs:string? external;
declare function ddtek-sql:SUBSTR($p1 as xs:string?, $p2 as xs:int?, $p3 as xs:int?) as xs:string? external;
declare function ddtek-sql:TO_ASCII($p1 as xs:string?) as xs:string? external;
declare function ddtek-sql:TO_ASCII($p1 as xs:string?, $p2 as xs:string?) as xs:string? external;
declare function ddtek-sql:TO_HEX($p1 as xs:decimal?) as xs:string? external;
declare function ddtek-sql:TRANSLATE($p1 as xs:string?, $p2 as xs:string?, $p3 as xs:string?) as xs:string? external;

(: system functions :)
declare function ddtek-sql:CURRENT_DATABASE() as xs:string? external;
declare function ddtek-sql:CURRENT_SCHEMA() as xs:string? external;
declare function ddtek-sql:CURRENT_SCHEMAS($p1 as xs:boolean?) as xs:string* external;
declare function ddtek-sql:CURRENT_USER() as xs:string? external;
declare function ddtek-sql:PG_POSTMASTER_START_TIME() as xs:dateTime? external;
declare function ddtek-sql:VERSION() as xs:string? external;

