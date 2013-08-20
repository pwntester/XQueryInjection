(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: Retrieve all user IDs in users.xml file accessed through the $allUsers external variable :)
declare variable $allUsers as document-node(element(*, xs:untyped)) external;

<users> {
  for $user in $allUsers/users/user
  order by $user/last-name
  return
	$user/id
} </users>

