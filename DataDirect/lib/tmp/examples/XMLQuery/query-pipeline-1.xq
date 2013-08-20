(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: Group all tickers in users.xml, and list holdings for each of them :)
<holdings> {
  for $ticker in distinct-values(doc("users.xml")//holding/ticker)
  order by $ticker
  return
	<holding>
		<ticker>{$ticker}</ticker>
		<users> {
			for $holding in doc("users.xml")//user/holdings/holding[ticker=$ticker]
			return
				<user>
					{$holding/../../id}
					<shares>{$holding/quantity/text()}</shares>
				</user>
		} </users>
	</holding>
} </holdings>
