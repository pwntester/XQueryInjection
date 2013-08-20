(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: Rename "UserId" elements to be "ID"  and save the result to a new XML document :)

for $user in doc("../xml/users.xml")/table/users
return
	rename node $user/UserId as QName("", "ID"),
put(doc("../xml/users.xml"), "new-users.xml")

