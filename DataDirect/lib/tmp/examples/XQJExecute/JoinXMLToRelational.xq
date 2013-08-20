(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)
declare option ddtek:serialize "indent=yes";

(: Joins data from an XML file (request.xml) with data from a database table (holdings :)
let $request := doc('request.xml')/request
for $user in $request/performance
return
 <portfolio UserID="{$user/UserId}">
  { $request }
  { for $h in collection('holdings')/holdings
    where $h/userid eq $user/UserId
    return 	
      <stock>
       {
       	$h/stockticker,
       	$h/shares
       }
      </stock>
  }  
 </portfolio>