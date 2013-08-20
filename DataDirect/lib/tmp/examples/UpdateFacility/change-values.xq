(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: Grant 20% more shares to "Minollo" and save the result to a new XML document :)

for $holding in doc("../xml/holdings.xml")/table/holdings[UserId eq 'Minollo']
return
	replace value of node $holding/Shares with xs:integer($holding/Shares * 1.2),
put(doc("../xml/holdings.xml"), "more-shares.xml")
