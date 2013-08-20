(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)
declare option ddtek:serialize "indent=yes";

(: This examples demonstrates the elimination of duplicate nodes during path expression evaluation :)

let $users := for $h in fn:collection('holdings')/holdings
              for $u in fn:collection('users')/*[userid eq $h/userid]
              return $u
return (<result1>{"Nr of items in sequence:", fn:count($users)}</result1>,
        <result2>{"Nr of items in sequence after a path-expression:", fn:count($users/.)}</result2>)
