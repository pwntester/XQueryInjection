(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :) 
(: This module contains function declarations for a number of SQLServer functions :) 
module namespace ddtek-sql = "http://www.datadirect.com/xquery/sql-function"; 

(: aggregate functions :)
declare function ddtek-sql:AVG($p1 as xs:decimal*) as xs:decimal? external;             
declare function ddtek-sql:BINARY_CHECKSUM($p1 as xs:decimal?,$p2 as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:CHECKSUM($p1 as xs:decimal?,$p2 as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:CHECKSUM_AGG($p1 as xs:decimal*) as xs:string? external;     
declare function ddtek-sql:COUNT($p1 as xs:string*) as xs:int? external;     
declare function ddtek-sql:COUNT_BIG($p1 as xs:string*) as xs:integer? external;
declare function ddtek-sql:MAX($p1 as xs:string*) as xs:string? external;   
declare function ddtek-sql:MIN($p1 as xs:string*) as xs:string? external;   
declare function ddtek-sql:SUM($p1 as xs:decimal*) as xs:decimal? external;   
declare function ddtek-sql:STDEV($p1 as xs:decimal*) as xs:float? external; 
declare function ddtek-sql:VAR($p1 as xs:decimal*) as xs:float? external;     

(: datetime functions :) 
declare function ddtek-sql:DAY($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:GETDATE() as xs:dateTime? external;
declare function ddtek-sql:GETUTCDATE() as xs:dateTime? external;
declare function ddtek-sql:MONTH($p1 as xs:dateTime?) as xs:int? external;
declare function ddtek-sql:YEAR($p1 as xs:dateTime?) as xs:int? external; 

(: Metadata functions :)
declare function ddtek-sql:COL_LENGTH($p1 as xs:string?,$p2 as xs:string?) as xs:int? external;               
declare function ddtek-sql:COL_NAME($p1 as xs:int?,$p2 as xs:int?) as xs:string? external;                 
declare function ddtek-sql:COLUMNPROPERTY($p1 as xs:int?,$p2 as xs:string?,$p3 as xs:string?) as xs:int? external;           
declare function ddtek-sql:DATABASEPROPERTY($p1 as xs:string?,$p2 as xs:string?) as xs:int? external;       
declare function ddtek-sql:DB_ID($p1 as xs:string?) as xs:int? external;                   
declare function ddtek-sql:DB_NAME($p1 as xs:int?) as xs:string? external;                 
declare function ddtek-sql:FILE_ID($p1 as xs:string?) as xs:short? external;                 
declare function ddtek-sql:FILE_NAME($p1 as xs:short?) as xs:string? external;               
declare function ddtek-sql:FILEGROUP_ID($p1 as xs:string?) as xs:short? external;            
declare function ddtek-sql:FILEGROUP_NAME($p1 as xs:short?) as xs:string? external;          
declare function ddtek-sql:FILEGROUPPROPERTY($p1 as xs:string?,$p2 as xs:string?) as xs:int? external;       
declare function ddtek-sql:FILEPROPERTY($p1 as xs:string?,$p2 as xs:string?) as xs:int? external;   
declare function ddtek-sql:FULLTEXTCATALOGPROPERTY($p1 as xs:string?,$p2 as xs:string?) as xs:int? external;
declare function ddtek-sql:FULLTEXTSERVICEPROPERTY($p1 as xs:string?) as xs:int? external;
declare function ddtek-sql:INDEX_COL($p1 as xs:string?,$p2 as xs:int?,$p3 as xs:int?) as xs:string? external;              
declare function ddtek-sql:INDEXKEY_PROPERTY($p1 as xs:int?,$p2 as xs:int?,$p3 as xs:int?,$p4 as xs:string?) as xs:int? external;     
declare function ddtek-sql:INDEXPROPERTY($p1 as xs:int?,$p2 as xs:string?,$p3 as xs:string?) as xs:int? external;          
declare function ddtek-sql:OBJECT_ID($p1 as xs:string?) as xs:int? external;              
declare function ddtek-sql:OBJECT_NAME($p1 as xs:int?) as xs:string? external;            
declare function ddtek-sql:OBJECTPROPERTY($p1 as xs:int?, $p2 as xs:string?) as xs:int? external;         
declare function ddtek-sql:TYPEPROPERTY($p1 as xs:string?,$p2 as xs:string?) as xs:int? external;           

(: numeric functions :) 
declare function ddtek-sql:ABS($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:ACOS($p1 as xs:float?) as xs:float? external;
declare function ddtek-sql:ASIN($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:ATAN($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:ATN2($p1 as xs:float?, $p2 as xs:float?) as xs:float? external; 
declare function ddtek-sql:CEILING($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:COS($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:COT($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:DEGREES($p1 as xs:decimal?) as xs:decimal? external;
declare function ddtek-sql:EXP($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:FLOOR($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:LOG($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:LOG10($p1 as xs:float?) as xs:float? external;  
declare function ddtek-sql:PI() as xs:float? external; 
declare function ddtek-sql:POWER($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:RADIANS($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:RAND($p1 as xs:int?) as xs:float external; 
declare function ddtek-sql:ROUND($p1 as xs:decimal?, $p2 as xs:int?) as xs:decimal? external; 
declare function ddtek-sql:SIGN($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:SIN($p1 as xs:float?) as xs:float? external; 
declare function ddtek-sql:SQUARE($p1 as xs:float?) as xs:float? external;
declare function ddtek-sql:SQRT($p1 as xs:float?) as xs:float? external;
declare function ddtek-sql:TAN($p1 as xs:float?) as xs:float? external;

(: security functions :)
declare function ddtek-sql:HAS_DBACCESS($p1 as xs:string?) as xs:int? external;
declare function ddtek-sql:IS_MEMBER($p1 as xs:string?) as xs:int? external;   
declare function ddtek-sql:IS_SRVROLEMEMBER($p1 as xs:string?) as xs:int? external;   
declare function ddtek-sql:IS_SRVROLEMEMBER($p1 as xs:string?,$p2 as xs:string?) as xs:int? external;   
declare function ddtek-sql:SUSER_SID() as xs:string? external;      
declare function ddtek-sql:SUSER_SID($p1 as xs:string?) as xs:string? external;      
declare function ddtek-sql:SUSER_SNAME() as xs:string? external;    
declare function ddtek-sql:SUSER_SNAME($p1 as xs:string?) as xs:string? external;    
declare function ddtek-sql:USER_ID() as xs:short? external;       
declare function ddtek-sql:USER_ID($p1 as xs:string?) as xs:short? external;       

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
declare function ddtek-sql:APP_NAME() as xs:string? external;
declare function ddtek-sql:COALESCE($p1 as xs:string?,$p2 as xs:string?) as xs:string? external;
declare function ddtek-sql:GETANSINULL() as xs:int? external;
declare function ddtek-sql:GETANSINULL($p1 as xs:string?) as xs:int? external;
declare function ddtek-sql:HOST_ID() as xs:string? external;
declare function ddtek-sql:HOST_NAME() as xs:string? external;
declare function ddtek-sql:ISDATE($p1 as xs:string?) as xs:int? external;
declare function ddtek-sql:ISNULL($p1 as xs:string?,$p2 as xs:string?) as xs:string? external;
declare function ddtek-sql:NULLIF($p1 as xs:string?,$p2 as xs:string?) as xs:string? external;
declare function ddtek-sql:PARSENAME($p1 as xs:string?,$p2 as xs:int?) as xs:string? external;
declare function ddtek-sql:PERMISSIONS() as xs:int? external;
declare function ddtek-sql:PERMISSIONS($p1 as xs:string?) as xs:int? external;
declare function ddtek-sql:PERMISSIONS($p1 as xs:string?,$p2 as xs:string?) as xs:int? external;
declare function ddtek-sql:USER_NAME() as xs:string? external;
declare function ddtek-sql:USER_NAME($p1 as xs:int?) as xs:string? external;
