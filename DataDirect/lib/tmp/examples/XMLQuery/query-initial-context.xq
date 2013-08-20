(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: Retrieve all user IDs in users.xml file set as initial document context :)
<users> {
  for $user in /users/user
  order by $user/last-name
  return
	$user/id
} </users>

