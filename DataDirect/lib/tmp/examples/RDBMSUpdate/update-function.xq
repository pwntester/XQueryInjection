(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: A generic function deleting all $ticker holdings for user $userID :)
declare updating function local:deleteHoldings($userID as xs:string, $ticker as xs:string)
{
let $holdings := collection("holdings")/holdings[userid = $userID and stockticker = $ticker]
return
    if ($holdings) then
        ddtek:sql-delete($holdings)
    else
        error(QName("", "error"),"No records found",())
};

(: Delete all TIVO stock holdings for Minollo :)
local:deleteHoldings("Minollo", "TIVO")

