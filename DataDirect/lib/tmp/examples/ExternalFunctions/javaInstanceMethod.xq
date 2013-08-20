(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :) 

(: Declare an XQuery namespace that maps to the SimpleDateFormat Java class :)
declare namespace sdf = "ddtekjava:java.text.SimpleDateFormat";

(: Declare constructor for SimpleDateFormat as external XQuery function :)
declare function sdf:SimpleDateFormat($pattern as xs:string) 
  as ddtek:javaObject external;
(: Declare parse method of SimpleDateFormat as external XQuery function :)
declare function sdf:parse($t as ddtek:javaObject,$f as xs:string) 
  as ddtek:javaObject external;
(: Declare format method of SimpleDateFormat as external XQuery function :)
declare function sdf:format($t as ddtek:javaObject,$d as ddtek:javaObject) 
  as xs:string external;

declare option ddtek:serialize "indent=yes";

(: A local user defined helper function that invokes the Java external instance method :)
declare function local:formatDate($p as xs:string) as xs:string {
  sdf:format(
    sdf:SimpleDateFormat("EEE MMM d, ''yy"),
    sdf:parse(sdf:SimpleDateFormat("yyyy-MM-dd"),$p))
};

for $his in collection('historical')/historical[ticker='PRGS'][volume ge 200000]
let $date := local:formatDate(xs:string($his/datetraded))
let $volume := data($his/volume)
order by $his/datetraded descending
return
  <progress-trades volume='{$volume}' date='{$date}'/>
