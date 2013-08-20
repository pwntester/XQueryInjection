(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: Library module used by MainModule.xq :)
module namespace example = "http://www.datadirect.com/examples/";

declare function example:namefromuser($user as node()?) as xs:string {
	fn:concat( $user/firstname , " ", $user/lastname )
};

declare function example:stockfromuser($userid as xs:string) as node()* {
    for $h in collection('holdings')/holdings
    where $h/userid = $userid
    return 	
      <stock>
       {
       	$h/stockticker,
       	$h/shares
       }
      </stock>
};
