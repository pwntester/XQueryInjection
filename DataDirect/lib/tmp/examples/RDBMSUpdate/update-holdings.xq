(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: Add 100 PRGS stocks to Minollo :)
let $x := collection("holdings")/holdings[userid = "Minollo" and stockticker = "PRGS"]
return
   if ($x) then
     ddtek:sql-update($x, "shares", $x/shares + 100)
   else
     ddtek:sql-insert("holdings", "userid", "Minollo", "stockticker", "PRGS", "shares", 100)
