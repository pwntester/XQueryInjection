(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: This example shows the use of a library module. :)
import module namespace example = "http://www.datadirect.com/examples/" at "LibraryModule.xq";
declare option ddtek:serialize "indent=yes";


for $u in collection('users')/users
return
  <user>
   <name> { example:namefromuser($u) } </name>
   { 
   	 example:stockfromuser($u/userid)
   }
  </user>
