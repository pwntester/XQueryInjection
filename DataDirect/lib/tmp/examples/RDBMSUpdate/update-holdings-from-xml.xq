(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: Update stock holdings based on activity described in activity.xml :)
for $activity in doc("activity.xml")/activity/*
let $previousHoldings := collection("holdings")/holdings[userid = $activity/userid and stockticker = $activity/ticker][1]
return
    if ($activity/name() = "sell") then (: delete or update existing row :) 
        if ($previousHoldings/shares = $activity/quantity) then	(: selling all stocks :)
            ddtek:sql-delete($previousHoldings)
        else (: selling some stocks :)
            ddtek:sql-update($previousHoldings, "shares", $previousHoldings/shares - xs:integer($activity/quantity))
    else (: create or update existing row :)
        if ($previousHoldings) then (: update existing holding :)
            ddtek:sql-update($previousHoldings, "shares", $previousHoldings/shares + xs:integer($activity/quantity))
        else (: create new holding :)
            ddtek:sql-insert("holdings", "userid", $activity/userid, "stockticker", $activity/ticker, "shares", $activity/quantity)
