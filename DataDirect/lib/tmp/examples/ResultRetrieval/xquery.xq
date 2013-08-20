(: Copyright(c) 2003-2006 DataDirect Technologies. All rights reserved. :) 

(: Just an XQuery expression for demonstrating different ways to process an XQSequence :)

for $u in collection('users')/users
return 
  <user>
     { $u/userid/text(),
       for $h in collection('holdings')/holdings[userid eq $u/userid]
       return <share amount="{$h/shares}">{fn:data($h/stockticker)}</share> 
     }
  </user>
