(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: Retrieve all user IDs in users.xml file accessed through the fn:doc() function :)
<users> {
  for $user in doc("users.xml")/users/user
  order by $user/last-name
  return
	$user/id
} </users>

