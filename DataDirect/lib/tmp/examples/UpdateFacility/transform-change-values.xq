(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

declare option ddtek:serialize "indent=yes";

(: Grant 20% more shares to "Minollo" transforming the result returned by the XQuery :)

<table> {
	for $holding in doc("../xml/holdings.xml")/table/holdings
	return
		if( $holding/UserId eq 'Minollo' ) then
			copy $updatedHolding := $holding
			modify
				replace value of node $updatedHolding/Shares with xs:integer($updatedHolding/Shares * 1.2)
			return $updatedHolding
		else
			$holding
} </table>
