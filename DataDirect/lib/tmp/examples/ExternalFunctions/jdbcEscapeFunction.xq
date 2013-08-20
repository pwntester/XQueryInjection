(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :) 
declare option ddtek:serialize "indent=yes";

(: First step declare the function as external, belonging to predefined name-space ddtek-sql-jdbc :)
declare function ddtek-sql-jdbc:SIGN($i as xs:integer) as xs:integer external;

(: This example uses the sql-jdbc-escape function SIGN :)
for $i in (-5, -2, 0, 100)
return element SIGN {attribute value {$i}, ddtek-sql-jdbc:SIGN($i)}
