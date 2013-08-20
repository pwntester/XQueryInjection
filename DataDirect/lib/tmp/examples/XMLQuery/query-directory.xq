(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: Retrieve all user IDs in all .xml files available in current folder :)
declare variable $folder as xs:string external;

<users> {
(: make sure current folder specified as argument uses a clean URL format :)
  let $cleanFolder := replace(if(substring($folder,1,1)='/') then $folder else concat('/',$folder),'\\','/')
(: fetch all files matching *.xml from the specified folder :)
  for $user in collection(concat("file://",$cleanFolder,"?select=*.xml"))/users/user
  order by $user/last-name
  return
	$user/id
} </users>

