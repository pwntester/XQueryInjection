(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)
declare option ddtek:serialize "indent=yes";

(: Nested FWLOR's joining data from two relational tables :)
(: First iterate over every row in the users table :)
for $u in fn:collection('users')/users
return
  <user>
   <name>{
    	$u/firstname,
    	$u/lastname
    }</name>
   { 
   (: Join users table with holdings table :)
    for $h in collection('holdings')/holdings
    where $h/userid = $u/userid
    return 	
      <stock>{
       	$h/stockticker,
       	$h/shares
       }</stock>
  }</user>
