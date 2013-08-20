(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

declare option ddtek:serialize "indent=yes";

(: Grant all users 1000 shares of "DDTEK"; if they already own them, increase the number
   Save the result to a new XML document
:)

for $user in doc("../xml/users.xml")/table/users
let $ddtekShares := doc("../xml/holdings.xml")/table/holdings[UserId eq $user/UserId and StockTicker eq "DDTEK"]
return
	if( $ddtekShares ) then
		replace value of node $ddtekShares/Shares with $ddtekShares/Shares + 1000
	else
		insert node
			<holdings>
				<UserId>{$user/UserId/text()}</UserId>
				<StockTicker>DDTEK</StockTicker>
				<Shares>1000</Shares>
			</holdings>
		as last into doc("../xml/holdings.xml")/table,
put(doc("../xml/holdings.xml"), "more-ddtek-holdings.xml")
