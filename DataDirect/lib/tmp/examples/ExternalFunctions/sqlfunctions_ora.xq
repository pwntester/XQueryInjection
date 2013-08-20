(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :) 
(: This module defines the sql-functions for Oracle :) 
module namespace ddtek-sql = "http://www.datadirect.com/xquery/sql-function"; 

(: numeric functions :) 
declare function ddtek-sql:ABS($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:ACOS($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:ASIN($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:ATAN($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:ATAN2($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:BITAND($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:int? external; 
declare function ddtek-sql:CEIL($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:COS($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:COSH($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:EXP($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:FLOOR($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:LN($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:LOG($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:MOD($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:NANVL($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:POWER($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:REMAINDER($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:ROUND($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:ROUND($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:SIGN($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:SIN($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:SINH($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:SQRT($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:TAN($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:TANH($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:TRUNC($p1 as xs:decimal?, $p2 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:TRUNC($p1 as xs:decimal?) as xs:decimal? external; 
declare function ddtek-sql:WIDTH_BUCKET($p1 as xs:decimal?, $p2 as xs:decimal?, $p3 as xs:decimal?, $p4 as xs:decimal?) as xs:decimal? external; 

(: string functions :) 
declare function ddtek-sql:CHR($p1 as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:CONCAT($p1 as xs:string?, $p2 as xs:string?) as xs:string? external; 
declare function ddtek-sql:INITCAP($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:LOWER($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:LPAD($p1 as xs:string?, $p2 as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:LPAD($p1 as xs:string?, $p2 as xs:decimal?, $p3 as xs:string?) as xs:string? external; 
declare function ddtek-sql:LTRIM($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:LTRIM($p1 as xs:string?, $p2 as xs:string?) as xs:string? external; 
declare function ddtek-sql:NLS_INITCAP($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:NLS_INITCAP($p1 as xs:string?,$nls-param as xs:string?) as xs:string? external; 
declare function ddtek-sql:NLS_LOWER($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:NLS_LOWER($p1 as xs:string?, $nls-param as xs:string?) as xs:string? external; 
declare function ddtek-sql:NLSSORT($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:NLSSORT($p1 as xs:string?, $nls-param as xs:string?) as xs:string? external; 
declare function ddtek-sql:NLS_UPPER($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:NLS_UPPER($p1 as xs:string?, $nls-param as xs:string?) as xs:string? external; 
declare function ddtek-sql:REGEXP_REPLACE($p1 as xs:string?, $pattern as xs:string?) as xs:string? external; 
declare function ddtek-sql:REGEXP_REPLACE($p1 as xs:string?, $pattern as xs:string?, $replace-string as xs:string?) as xs:string? external; 
declare function ddtek-sql:REGEXP_REPLACE($p1 as xs:string?, $pattern as xs:string?, $replace-string as xs:string?, $occ as xs:integer) as xs:string? external; 
declare function ddtek-sql:REGEXP_REPLACE($p1 as xs:string?, $pattern as xs:string?, $replace-string as xs:string?, $occ as xs:integer, $match as xs:integer) as xs:string? external; 
declare function ddtek-sql:REGEXP_SUBSTR($p1 as xs:string?, $pattern as xs:string?) as xs:string? external; 
declare function ddtek-sql:REGEXP_SUBSTR($p1 as xs:string?, $pattern as xs:string?, $pos as xs:integer) as xs:string? external; 
declare function ddtek-sql:REGEXP_SUBSTR($p1 as xs:string?, $pattern as xs:string?, $pos as xs:integer, $occ as xs:integer) as xs:string? external; 
declare function ddtek-sql:REGEXP_SUBSTR($p1 as xs:string?, $pattern as xs:string?, $pos as xs:integer, $occ as xs:integer, $match as xs:integer) as xs:string? external; 
declare function ddtek-sql:REPLACE($p1 as xs:string?, $p2 as xs:string?) as xs:string? external; 
declare function ddtek-sql:REPLACE($p1 as xs:string?, $p2 as xs:string?, $p3 as xs:string?) as xs:string? external; 
declare function ddtek-sql:RPAD($p1 as xs:string?, $p2 as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:RPAD($p1 as xs:string?, $p2 as xs:decimal?, $p3 as xs:string?) as xs:string? external; 
declare function ddtek-sql:RTRIM($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:RTRIM($p1 as xs:string?, $p2 as xs:string?) as xs:string? external; 
declare function ddtek-sql:SOUNDEX($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:SUBSTR($p1 as xs:string?, $position as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:SUBSTR($p1 as xs:string?, $position as xs:decimal?, $substring-length as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:SUBSTRB($p1 as xs:string?, $position as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:SUBSTRB($p1 as xs:string?, $position as xs:decimal?, $substring-length as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:SUBSTRC($p1 as xs:string?, $position as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:SUBSTRC($p1 as xs:string?, $position as xs:decimal?, $substring-length as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:SUBSTR2($p1 as xs:string?, $position as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:SUBSTR2($p1 as xs:string?, $position as xs:decimal?, $substring-length as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:SUBSTR4($p1 as xs:string?, $position as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:SUBSTR4($p1 as xs:string?, $position as xs:decimal?, $substring-length as xs:decimal?) as xs:string? external; 
declare function ddtek-sql:TRANSLATE($p1 as xs:string?, $p2 as xs:string?, $p3 as xs:string?) as xs:string? external; 
declare function ddtek-sql:TRIM($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:UPPER($p1 as xs:string?) as xs:string? external; 

(: nls character functions? :) 
declare function ddtek-sql:NLS_CHARSET_DECL_LEN($bytecount as xs:integer, $charset as xs:string) as xs:int external; 
declare function ddtek-sql:NLS_CHARSET_ID($charsetid as xs:string) as xs:int external; 
declare function ddtek-sql:NLS_CHARSET_NAME($charset as xs:integer) as xs:string external; 

(: character functions returning number :) 
declare function ddtek-sql:ASCII($p1 as xs:string?) as xs:int? external; 
declare function ddtek-sql:INSTR($p1 as xs:string?, $substr as xs:string) as xs:integer? external; 
declare function ddtek-sql:INSTR($p1 as xs:string?, $substr as xs:string, $pos as xs:integer) as xs:integer? external; 
declare function ddtek-sql:INSTR($p1 as xs:string?, $substr as xs:string, $pos as xs:integer, $occ as xs:integer) as xs:integer? external; 
declare function ddtek-sql:INSTRB($p1 as xs:string?, $substr as xs:string) as xs:integer? external; 
declare function ddtek-sql:INSTRB($p1 as xs:string?, $substr as xs:string, $pos as xs:integer) as xs:integer? external; 
declare function ddtek-sql:INSTRB($p1 as xs:string?, $substr as xs:string, $pos as xs:integer, $occ as xs:integer) as xs:integer? external; 
declare function ddtek-sql:INSTRC($p1 as xs:string?, $substr as xs:string) as xs:integer? external; 
declare function ddtek-sql:INSTRC($p1 as xs:string?, $substr as xs:string, $pos as xs:integer) as xs:integer? external; 
declare function ddtek-sql:INSTRC($p1 as xs:string?, $substr as xs:string, $pos as xs:integer, $occ as xs:integer) as xs:integer? external; 
declare function ddtek-sql:INSTR2($p1 as xs:string?, $substr as xs:string) as xs:integer? external; 
declare function ddtek-sql:INSTR2($p1 as xs:string?, $substr as xs:string, $pos as xs:integer) as xs:integer? external; 
declare function ddtek-sql:INSTR2($p1 as xs:string?, $substr as xs:string, $pos as xs:integer, $occ as xs:integer) as xs:integer? external; 
declare function ddtek-sql:INSTR4($p1 as xs:string?, $substr as xs:string) as xs:integer? external; 
declare function ddtek-sql:INSTR4($p1 as xs:string?, $substr as xs:string, $pos as xs:integer) as xs:integer? external; 
declare function ddtek-sql:INSTR4($p1 as xs:string?, $substr as xs:string, $pos as xs:integer, $occ as xs:integer) as xs:integer? external; 
declare function ddtek-sql:LENGTH($p1 as xs:string?) as xs:integer? external; 
declare function ddtek-sql:LENGTHB($p1 as xs:string?) as xs:integer? external; 
declare function ddtek-sql:LENGTHC($p1 as xs:string?) as xs:integer? external; 
declare function ddtek-sql:LENGTH2($p1 as xs:string?) as xs:integer? external; 
declare function ddtek-sql:LENGTH4($p1 as xs:string?) as xs:integer? external; 
declare function ddtek-sql:REGEXP_INSTR($p1 as xs:string?, $pattern as xs:string?) as xs:string? external; 
declare function ddtek-sql:REGEXP_INSTR($p1 as xs:string?, $pattern as xs:string?, $pos as xs:integer) as xs:string? external; 
declare function ddtek-sql:REGEXP_INSTR($p1 as xs:string?, $pattern as xs:string?, $pos as xs:integer, $occ as xs:integer) as xs:string? external; 
declare function ddtek-sql:REGEXP_INSTR($p1 as xs:string?, $pattern as xs:string?, $pos as xs:integer, $occ as xs:integer, $return-option as xs:integer) as xs:string? external; 
declare function ddtek-sql:REGEXP_INSTR($p1 as xs:string?, $pattern as xs:string?, $pos as xs:integer, $occ as xs:integer, $return-option as xs:integer, $match as xs:integer) as xs:string? external; 

(: datetime functions :) 
declare function ddtek-sql:ADD_MONTHS($p1 as xs:dateTime?, $p2 as xs:decimal?) as xs:dateTime? external; 
declare function ddtek-sql:CURRENT_TIMESTAMP($p1 as xs:integer) as xs:dateTime external; 
declare function ddtek-sql:FROM_TZ($p1 as xs:dateTime?, $p2 as xs:string) as xs:dateTime? external; 
declare function ddtek-sql:LAST_DAY($p1 as xs:dateTime?) as xs:dateTime? external; 
declare function ddtek-sql:LOCALTIMESTAMP($p1 as xs:integer) as xs:dateTime? external; 
declare function ddtek-sql:MONTHS_BETWEEN($p1 as xs:dateTime?, $p2 as xs:dateTime?) as xs:decimal? external; 
declare function ddtek-sql:NEW_TIME($p1 as xs:dateTime?, $p2 as xs:string, $p3 as xs:string) as xs:dateTime? external; 
declare function ddtek-sql:NEXT_DAY($p1 as xs:dateTime?, $p2 as xs:string?) as xs:dateTime? external; 
declare function ddtek-sql:SYS_EXTRACT_UTC($p1 as xs:dateTime?) as xs:dateTime? external; 
declare function ddtek-sql:TZ_OFFSET($p1 as xs:string) as xs:string external; 

(: conversion functions :) 
declare function ddtek-sql:ASCIISTR($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:CHARTOROWID($p1 as xs:string) as xs:string? external; 
declare function ddtek-sql:COMPOSE($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:CONVERT($p1 as xs:string?, $dest-char-set as xs:string) as xs:string? external; 
declare function ddtek-sql:CONVERT($p1 as xs:string?, $dest-char-set as xs:string, $source-char-set as xs:string) as xs:string? external; 
declare function ddtek-sql:DECOMPOSE($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:HEXTORAW($p1 as xs:string?) as xs:hexBinary? external; 
declare function ddtek-sql:RAWTOHEX($p1 as xs:hexBinary?) as xs:string? external; 
declare function ddtek-sql:RAWTONHEX($p1 as xs:hexBinary?) as xs:string? external; 
declare function ddtek-sql:ROWIDTOCHAR($p1 as xs:string) as xs:string external; 
declare function ddtek-sql:ROWIDTONCHAR($p1 as xs:string) as xs:string external; 
declare function ddtek-sql:SCN_TO_TIMESTAMP($p1 as xs:decimal) as xs:dateTime external; 
declare function ddtek-sql:TIMESTAMP_TO_SCN($p1 as xs:dateTime) as xs:decimal external; 
declare function ddtek-sql:TO_BINARY_DOUBLE($p1 as xs:string?) as xs:double external; 
declare function ddtek-sql:TO_BINARY_DOUBLE($p1 as xs:string?, $fmt as xs:string) as xs:double external; 
declare function ddtek-sql:TO_BINARY_DOUBLE($p1 as xs:string?, $fmt as xs:string, $nlsparam as xs:string) as xs:double external; 
declare function ddtek-sql:TO_BINARY_FLOAT($p1 as xs:string?) as xs:float external; 
declare function ddtek-sql:TO_BINARY_FLOAT($p1 as xs:string?, $fmt as xs:string) as xs:float external; 
declare function ddtek-sql:TO_BINARY_FLOAT($p1 as xs:string?, $fmt as xs:string, $nlsparam as xs:string) as xs:float external; 
declare function ddtek-sql:TO_CHAR($p1 as xdt:anyAtomicType?) as xs:string external; 
declare function ddtek-sql:TO_CHAR($p1 as xdt:anyAtomicType?, $fmt as xs:string) as xs:string external; 
declare function ddtek-sql:TO_CHAR($p1 as xdt:anyAtomicType?, $fmt as xs:string, $nlsparam as xs:string) as xs:string external; 
declare function ddtek-sql:TO_CLOB($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:TO_DATE($p1 as xs:string?) as xs:dateTime external; 
declare function ddtek-sql:TO_DATE($p1 as xs:string?, $fmt as xs:string) as xs:dateTime external; 
declare function ddtek-sql:TO_DATE($p1 as xs:string?, $fmt as xs:string, $nlsparam as xs:string) as xs:dateTime external; 
declare function ddtek-sql:TO_LOB($p1 as xdt:anyAtomicType?) as xdt:anyAtomicType? external; 
declare function ddtek-sql:TO_MULTI_BYTE($p1 as xs:string?) as xs:string external; 
declare function ddtek-sql:TO_NCHAR($p1 as xdt:anyAtomicType?) as xs:string external; 
declare function ddtek-sql:TO_NCHAR($p1 as xdt:anyAtomicType?, $fmt as xs:string) as xs:string external; 
declare function ddtek-sql:TO_NCHAR($p1 as xdt:anyAtomicType?, $fmt as xs:string, $nlsparam as xs:string) as xs:string external; 
declare function ddtek-sql:TO_NCLOB($p1 as xs:string?) as xs:string? external; 
declare function ddtek-sql:TO_NUMBER($p1 as xdt:anyAtomicType?) as xs:decimal external; 
declare function ddtek-sql:TO_NUMBER($p1 as xdt:anyAtomicType?, $fmt as xs:string) as xs:decimal external; 
declare function ddtek-sql:TO_NUMBER($p1 as xdt:anyAtomicType?, $fmt as xs:string, $nlsparam as xs:string) as xs:decimal external; 
declare function ddtek-sql:TO_SINGLE_BYTE($p1 as xs:string?) as xs:string external; 
declare function ddtek-sql:TO_TIMESTAMP($p1 as xs:string?) as xs:dateTime? external; 
declare function ddtek-sql:TO_TIMESTAMP($p1 as xs:string?, $fmt as xs:string) as xs:dateTime? external; 
declare function ddtek-sql:TO_TIMESTAMP($p1 as xs:string?, $fmt as xs:string, $nlsparam as xs:string) as xs:dateTime? external; 
declare function ddtek-sql:TO_TIMESTAMP_TZ($p1 as xs:string?) as xs:dateTime? external; 
declare function ddtek-sql:TO_TIMESTAMP_TZ($p1 as xs:string?, $fmt as xs:string) as xs:dateTime? external; 
declare function ddtek-sql:TO_TIMESTAMP_TZ($p1 as xs:string?, $fmt as xs:string, $nlsparam as xs:string) as xs:dateTime? external; 
declare function ddtek-sql:UNISTR($p1 as xs:string?) as xs:string external; 

(: LOB functions :) 
declare function ddtek-sql:BFILENAME($p1 as xs:string?, $p2 as xs:string?) as xs:hexBinary? external; 
declare function ddtek-sql:EMPTY_BLOB() as xs:hexBinary external; 
declare function ddtek-sql:EMPTY_CLOB() as xs:string external; 

(: XML functions :) 
declare function ddtek-sql:APPENDCHILDXML($p1 as node(), $xpath as xs:string, $p3 as xs:string) as document-node() external; 
declare function ddtek-sql:APPENDCHILDXML($p1 as node(), $xpath as xs:string, $p3 as xs:string, $namespace-string as xs:string) as document-node() external; 
declare function ddtek-sql:DELETEXML($p1 as node(), $xpath as xs:string) as document-node() external; 
declare function ddtek-sql:DELETEXML($p1 as node(), $xpath as xs:string, $namespace-string as xs:string) as document-node() external; 
declare function ddtek-sql:DEPTH($p1 as xs:integer) as xs:int external; 
declare function ddtek-sql:EXTRACT($p1 as node(), $xpath as xs:string) as document-node() external; 
declare function ddtek-sql:EXTRACT($p1 as node(), $xpath as xs:string, $namespace-string as xs:string) as document-node() external; 
declare function ddtek-sql:EXISTSNODE($p1 as node(), $xpath as xs:string) as xs:boolean external; 
declare function ddtek-sql:EXISTSNODE($p1 as node(), $xpath as xs:string, $namespace-string as xs:string) as xs:boolean external; 
declare function ddtek-sql:EXTRACTVALUE($p1 as node(), $xpath as xs:string) as xdt:anyAtomicType? external; 
declare function ddtek-sql:EXTRACTVALUE($p1 as node(), $xpath as xs:string, $namespace-string as xs:string) as xdt:anyAtomicType? external; 
declare function ddtek-sql:INSERTCHILDXML($p1 as node(), $xpath as xs:string, $p3 as xs:string, $p4 as xs:string) as document-node() external; 
declare function ddtek-sql:INSERTCHILDXML($p1 as node(), $xpath as xs:string, $p3 as xs:string, $p4 as xs:string, $namespace-string as xs:string) as document-node() external; 
declare function ddtek-sql:INSERTXMLBEFORE($p1 as node(), $xpath as xs:string, $p3 as xs:string) as document-node() external; 
declare function ddtek-sql:INSERTXMLBEFORE($p1 as node(), $xpath as xs:string, $p3 as xs:string, $namespace-string as xs:string) as document-node() external; 
declare function ddtek-sql:PATH($p1 as xs:integer) as xs:string external; 
declare function ddtek-sql:UPDATEXML($p1 as node(), $xpath as xs:string, $p3 as xdt:anyAtomicType) as document-node() external; 
declare function ddtek-sql:UPDATEXML($p1 as node(), $xpath as xs:string, $p3 as xdt:anyAtomicType, $namespace-string as xs:string) as document-node() external; 
declare function ddtek-sql:SYS_DBURIGEN($p1 as xdt:anyAtomicType) as xs:string external; 
declare function ddtek-sql:SYS_XMLAGG($p1 as node()*) as document-node() external; 
declare function ddtek-sql:SYS_XMLAGG($p1 as node()*, $fmt as xs:string) as document-node() external; 
declare function ddtek-sql:SYS_XMLGEN($p1 as xdt:anyAtomicType) as document-node() external; 
declare function ddtek-sql:SYS_XMLGEN($p1 as xdt:anyAtomicType, $fmt as xs:string) as document-node() external; 
declare function ddtek-sql:XMLAGG($p1 as node()*) as document-node() external; 
declare function ddtek-sql:XMLCDATA($p1 as xs:string) as document-node() external; 
declare function ddtek-sql:XMLCOLATTVAL($p1 as xdt:anyAtomicType) as document-node() external; 
declare function ddtek-sql:XMLCOMMENT($p1 as xs:string) as document-node() external; 
declare function ddtek-sql:XMLCONCAT($p1 as node(), $p2 as node()) as document-node() external; 
declare function ddtek-sql:XMLFOREST($p1 as xdt:anyAtomicType) as document-node() external; 
(: the output of the table-function XMLSEQUENCE must be declared in the configuration :) 
declare function ddtek-sql:XMLSEQUENCE($p1 as node()) as document-node() external; 
declare function ddtek-sql:XMLTRANSFORM($p1 as node(), $p2 as node()) as document-node() external; 
declare function ddtek-sql:XMLTYPE($p1 as xs:string) as document-node() external; 
(: XMLQUERY is supported through the predefined function ddtek-sql:ora-xmlquery :) 

(: XML Conditions :) 
declare function ddtek-sql:EQUALS_PATH($p1 as node(), $path as xs:string) as xs:int external; 
declare function ddtek-sql:UNDER_PATH($p1 as node(), $path as xs:string) as xs:int external; 

(: encoding and decoding functions :) 
(: Just examples with string as input :) 
declare function ddtek-sql:DECODE($p1 as xs:string, $search1 as xs:string, $res1 as xs:string, $default as xs:string) as xs:string external; 
declare function ddtek-sql:DUMP($p1 as xdt:anyAtomicType) as xs:string external; 
declare function ddtek-sql:DUMP($p1 as xdt:anyAtomicType, $fmt as xs:string) as xs:string external; 
declare function ddtek-sql:DUMP($p1 as xdt:anyAtomicType, $fmt as xs:string, $pos as xs:integer) as xs:string external; 
declare function ddtek-sql:DUMP($p1 as xdt:anyAtomicType, $fmt as xs:string, $pos as xs:integer, $length as xs:integer) as xs:string external; 
declare function ddtek-sql:ORA_HASH($p1 as xdt:anyAtomicType) as xs:decimal external; 
declare function ddtek-sql:ORA_HASH($p1 as xdt:anyAtomicType, $max-bucket as xs:integer) as xs:decimal external; 
declare function ddtek-sql:ORA_HASH($p1 as xdt:anyAtomicType, $max-bucket as xs:integer, $seed-value as xs:integer) as xs:decimal external; 
declare function ddtek-sql:VSIZE($p1 as xdt:anyAtomicType) as xs:int external; 

(: NULL related functions :) 
(: Just examples with string as input :) 
declare function ddtek-sql:NULLIF($p1 as xs:string?, $p2 as xs:string?) as xs:string? external; 
declare function ddtek-sql:NVL($p1 as xs:string?, $p2 as xs:string) as xs:string external; 
declare function ddtek-sql:NVL2($p1 as xs:string?, $p2 as xs:string?, $p3 as xs:string?) as xs:string? external; 
declare function ddtek-sql:COALESCE($p1 as xs:string?, $p2 as xs:string?, $p3 as xs:string?) as xs:string? external; 

(: Environment and Identifier Functions :) 
declare function ddtek-sql:SYS_CONTEXT($namespace as xs:string, $parameter as xs:string) as xs:string external; 
declare function ddtek-sql:SYS_CONTEXT($namespace as xs:string, $parameter as xs:string, $length as xs:integer) as xs:string external; 
declare function ddtek-sql:SYS_GUID() as xs:hexBinary external; 
declare function ddtek-sql:USERENV($p1 as xs:string) as xs:string external; 

(: Aggregate functions :) 
declare function ddtek-sql:AVG($p1 as xs:decimal*) as xs:decimal external; 
declare function ddtek-sql:COUNT($p1 as xdt:anyAtomicType*) as xs:decimal external; 
declare function ddtek-sql:MAX($p1 as xs:decimal*) as xs:decimal external; 
declare function ddtek-sql:MEDIAN($p1 as xs:decimal*) as xs:decimal external; 
declare function ddtek-sql:MIN($p1 as xs:decimal*) as xs:decimal external; 
declare function ddtek-sql:STDDEV($p1 as xs:decimal*) as xs:decimal external; 
declare function ddtek-sql:STDDEV_POP($p1 as xs:decimal*) as xs:decimal external; 
declare function ddtek-sql:STDDEV_SAMP($p1 as xs:decimal*) as xs:decimal external; 
declare function ddtek-sql:SUM($p1 as xs:decimal*) as xs:decimal external; 
declare function ddtek-sql:VAR_POP($p1 as xs:decimal*) as xs:decimal external; 
declare function ddtek-sql:VAR_SAMP($p1 as xs:decimal*) as xs:decimal external; 
declare function ddtek-sql:VARIANCE($p1 as xs:decimal*) as xs:decimal external; 

(: general comparison functions :) 
(: Just an example with input three string parameters :) 
declare function ddtek-sql:GREATEST($p1 as xs:string, $p2 as xs:string, $p3 as xs:string) as xs:string external; 
declare function ddtek-sql:LEAST($p1 as xs:string, $p2 as xs:string, $p3 as xs:string) as xs:string external;
