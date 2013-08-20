(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)
declare option ddtek:serialize "indent=yes";

(: Custom document URI resolver example :)
<root>{ 
  for $i in doc("dir://../xml/")/directory/file[@size < 2000]
  return 
   <child>
     {$i}
     {doc( fn:concat("../xml/" , xs:string($i))) }
   </child>
}</root>
