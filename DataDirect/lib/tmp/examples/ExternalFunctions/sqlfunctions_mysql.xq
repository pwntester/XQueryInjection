(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :) 
(: This module contains function declarations for a number of SQLServer functions :) 
module namespace ddtek-sql = "http://www.datadirect.com/xquery/sql-function"; 

(: aggregate functions :)
declare function ddtek-sql:AVG($p1 as xs:decimal*) as xs:decimal? external;             
declare function ddtek-sql:BIT_AND($p1 as xs:int*) as xs:int external; 
declare function ddtek-sql:BIT_OR($p1 as xs:int*) as xs:int external; 
declare function ddtek-sql:BIT_XOR($p1 as xs:int*) as xs:int external; 
declare function ddtek-sql:COUNT($p1 as xs:string*) as xs:int? external;     
declare function ddtek-sql:GROUP_CONCAT($p1 as xs:string*) as xs:string? external;   
declare function ddtek-sql:MAX($p1 as xs:string*) as xs:string? external;   
declare function ddtek-sql:MIN($p1 as xs:string*) as xs:string? external;   
declare function ddtek-sql:STDDEV($p1 as xs:decimal*) as xs:float? external; 
declare function ddtek-sql:STD($p1 as xs:decimal*) as xs:float? external; 
declare function ddtek-sql:STDDEV_POP($p1 as xs:decimal*) as xs:float? external; 
declare function ddtek-sql:STDDEV_SAMP($p1 as xs:decimal*) as xs:float? external; 
declare function ddtek-sql:SUM($p1 as xs:decimal*) as xs:decimal? external;   
declare function ddtek-sql:VAR_POP($p1 as xs:decimal*) as xs:float? external;     
declare function ddtek-sql:VAR_SAMP($p1 as xs:decimal*) as xs:float? external;     
declare function ddtek-sql:VARIANCE($p1 as xs:decimal*) as xs:float? external;     

(: datetime functions :) 
declare function ddtek-sql:ADDDATE($p1 as xs:dateTime?, $p2 as xs:int?) as xs:dateTime? external;
declare function ddtek-sql:ADDTIME($p1 as xs:dateTime?, $p2 as xs:dateTime?) as xs:dateTime? external;
declare function ddtek-sql:CURDATE() as xs:dateTime external;
declare function ddtek-sql:CURRENT_DATE() as xs:dateTime external;
declare function ddtek-sql:CURTIME() as xs:dateTime external;
declare function ddtek-sql:CURRENT_TIME() as xs:dateTime external;
declare function ddtek-sql:DATEDIFF($p1 as xs:dateTime?, $p2 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:DATE_FORMAT($p1 as xs:dateTime?, $p2 as xs:string?) as xs:string? external;
declare function ddtek-sql:DAY($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:DAYNAME($p1 as xs:dateTime?) as xs:string? external;
declare function ddtek-sql:DAYOFMONTH($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:DAYOFWEEK($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:DAYOFYEAR($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:FROM_DAYS($p1 as xs:int?) as xs:dateTime? external;
declare function ddtek-sql:FROM_UNIXTIME($p1 as xs:int?) as xs:dateTime? external;
declare function ddtek-sql:FROM_UNIXTIME($p1 as xs:int?, $p2 as xs:string?) as xs:dateTime? external;
declare function ddtek-sql:HOUR($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:LAST_DAY($p1 as xs:dateTime?) as xs:dateTime? external;
declare function ddtek-sql:LOCALTIME() as xs:dateTime external;
declare function ddtek-sql:LOCALTIMESTAMP() as xs:dateTime external;
declare function ddtek-sql:MAKEDATE($p1 as xs:int?, $p2 as xs:int?) as xs:dateTime? external;
declare function ddtek-sql:MAKETIME($p1 as xs:int?, $p2 as xs:int?, $p3 as xs:int?) as xs:dateTime? external;
declare function ddtek-sql:MICROSECOND($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:MINUTE($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:MONTH($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:MONTHNAME($p1 as xs:dateTime?) as xs:string? external;
declare function ddtek-sql:NOW() as xs:dateTime external;
declare function ddtek-sql:PERIOD_ADD($p1 as xs:int?, $p2 as xs:int?) as xs:int? external;
declare function ddtek-sql:PERIOD_DIFF($p1 as xs:int?, $p2 as xs:int?) as xs:int? external;
declare function ddtek-sql:QUARTER($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:SECOND($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:SEC_TO_TIME($p1 as xs:int?) as xs:dateTime? external;
declare function ddtek-sql:STR_TO_DATE($p1 as xs:string?, $p2 as xs:string?) as xs:dateTime? external;
declare function ddtek-sql:SUBDATE($p1 as xs:dateTime?, $p2 as xs:int?) as xs:dateTime? external;
declare function ddtek-sql:SUBTIME($p1 as xs:dateTime?, $p2 as xs:dateTime?) as xs:dateTime? external;
declare function ddtek-sql:SYSDATE() as xs:string external;
declare function ddtek-sql:TIME($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:TIMEDIFF($p1 as xs:dateTime?, $p2 as xs:dateTime?) as xs:dateTime? external;
declare function ddtek-sql:TIMESTAMP($p1 as xs:dateTime?) as xs:dateTime? external;
declare function ddtek-sql:TIMESTAMP($p1 as xs:dateTime?, $p2 as xs:dateTime?) as xs:dateTime? external;
declare function ddtek-sql:TIME_FORMAT($p1 as xs:dateTime?, $p2 as xs:string?) as xs:string? external;
declare function ddtek-sql:TIME_TO_SEC($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:TO_DAYS($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:UNIX_TIMESTAMP() as xs:int external;
declare function ddtek-sql:UNIX_TIMESTAMP($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:UTC_TIME() as xs:string external;
declare function ddtek-sql:UTC_TIMESTAMP() as xs:string external;
declare function ddtek-sql:WEEK($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:WEEK($p1 as xs:dateTime?, $p2 as xs:int?) as xs:int? external;
declare function ddtek-sql:WEEKDAY($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:WEEKOFYEAR($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:YEAR($p1 as xs:dateTime?) as xs:int? external; 
declare function ddtek-sql:YEARWEEK($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:YEARWEEK($p1 as xs:dateTime?, $p2 as xs:int?) as xs:int? external;

(: Metadata functions :)


(: numeric functions :) 
declare function ddtek-sql:ABS($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:ACOS($p1 as xs:float?) as xs:float? external;
declare function ddtek-sql:ASIN($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:ATAN($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:ATN2($p1 as xs:float?, $p2 as xs:float?) as xs:float? external; 
declare function ddtek-sql:CEILING($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:COS($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:COT($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:CRC32($p1 as xs:string?) as xs:int? external;
declare function ddtek-sql:DEGREES($p1 as xs:decimal?) as xs:decimal? external;
declare function ddtek-sql:EXP($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:FLOOR($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:LN($p1 as xs:float?) as xs:float? external;
declare function ddtek-sql:LOG($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:LOG($p1 as xs:float?, $p2 as xs:float?) as xs:float? external;  
declare function ddtek-sql:LOG2($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:LOG10($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:PI() as xs:float? external; 
declare function ddtek-sql:POW($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:POWER($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:RADIANS($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:RAND() as xs:float external; 
declare function ddtek-sql:RAND($p1 as xs:int?) as xs:float external; 
declare function ddtek-sql:ROUND($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:ROUND($p1 as xs:decimal?, $p2 as xs:int?) as xs:decimal? external; 
declare function ddtek-sql:SIGN($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:SIN($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:SQUARE($p1 as xs:float?) as xs:float? external;
declare function ddtek-sql:SQRT($p1 as xs:float?) as xs:float? external;
declare function ddtek-sql:TAN($p1 as xs:float?) as xs:float? external;
declare function ddtek-sql:TRUNCATE($p1 as xs:decimal?, $p2 as xs:int?) as xs:decimal? external; 

(: security functions :)

(: string functions :) 
declare function ddtek-sql:ASCII($p1 as xs:string?) as xs:int? external; 
declare function ddtek-sql:CHAR($p1 as xs:int?) as xs:string? external;        
declare function ddtek-sql:CHARINDEX($p1 as xs:string?, $p2 as xs:string?) as xs:int? external;   
declare function ddtek-sql:CHARINDEX($p1 as xs:string?, $p2 as xs:string?, $p3 as xs:int?) as xs:int? external;   
declare function ddtek-sql:DIFFERENCE($p1 as xs:string?, $p2 as xs:string?) as xs:int? external;  
declare function ddtek-sql:LEFT($p1 as xs:string?,$p2 as xs:int?) as xs:string? external;        
declare function ddtek-sql:LEN($p1 as xs:string?) as xs:int? external;         
declare function ddtek-sql:LOWER($p1 as xs:string?) as xs:string? external;
declare function ddtek-sql:LTRIM($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:NCHAR($p1 as xs:int?) as xs:string? external;      
declare function ddtek-sql:PATINDEX($p1 as xs:string?, $p2 as xs:string?) as xs:int? external;    
declare function ddtek-sql:REPLACE($p1 as xs:string?,$p2 as xs:string?,$p3 as xs:string?) as xs:string? external;     
declare function ddtek-sql:QUOTENAME($p1 as xs:string?) as xs:string? external;
declare function ddtek-sql:QUOTENAME($p1 as xs:string?,$p2 as xs:string?) as xs:string? external;
declare function ddtek-sql:REPLICATE($p1 as xs:string?,$p2 as xs:int?) as xs:string? external;
declare function ddtek-sql:REVERSE($p1 as xs:string?) as xs:string? external;     
declare function ddtek-sql:RIGHT($p1 as xs:string?,$p2 as xs:int?) as xs:string? external;       
declare function ddtek-sql:RTRIM($p1 as xs:string?) as xs:string? external;        
declare function ddtek-sql:SOUNDEX($p1 as xs:string?) as xs:string? external;             
declare function ddtek-sql:SPACE($p1 as xs:int?) as xs:string? external;       
declare function ddtek-sql:STR($p1 as xs:float?) as xs:string? external;         
declare function ddtek-sql:STR($p1 as xs:float?,$p2 as xs:int?) as xs:string? external;         
declare function ddtek-sql:STR($p1 as xs:float?,$p2 as xs:int?,$p3 as xs:int?) as xs:string? external;         
declare function ddtek-sql:STUFF($p1 as xs:string?,$p2 as xs:int?,$p3 as xs:int?,$p4 as xs:string?) as xs:string? external;       
declare function ddtek-sql:SUBSTRING($p1 as xs:string?,$p2 as xs:int?,$p3 as xs:int?) as xs:string? external;   
declare function ddtek-sql:UNICODE($p1 as xs:string?) as xs:int? external;     
declare function ddtek-sql:UPPER($p1 as xs:string?) as xs:string? external;       

(: system functions :)
declare function ddtek-sql:CHARSET($p1 as xs:string?) as xs:string? external;
declare function ddtek-sql:COLLATION($p1 as xs:string?) as xs:string? external;
declare function ddtek-sql:DATABASE() as xs:string external;
declare function ddtek-sql:USER() as xs:string external;
declare function ddtek-sql:VERSION() as xs:string? external;
declare function ddtek-sql:SLEEP($p1 as xs:int) as xs:int? external;
declare function ddtek-sql:UUID() as xs:string external;
