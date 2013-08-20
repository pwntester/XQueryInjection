(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)
declare option ddtek:serialize "indent=yes";

(: An example with user-defined functions :)

(: Local function declarations :)

(: Returns the user when user has 'Lucent' stock :)
declare function local:userHasLucent($user as element()) as element()? {
  $user[fn:collection('holdings')/holdings[userid eq $user/userid]
                                          [stockticker eq 'LU']]
};
(: Formats a user first and last name as F. LastName :)
declare function local:formatName($firstName as xs:string, 
                                  $lastName as xs:string) as xs:string {
  fn:concat(fn:substring($firstName,1,1), '. ', $lastName)
};

(: Main module - invoke the user-defined functions :)
for $u in fn:collection('users')/users
let $hasLucent := local:userHasLucent($u) 
where $hasLucent
return <hasLucent>{local:formatName($u/firstname, $u/lastname)}</hasLucent>
