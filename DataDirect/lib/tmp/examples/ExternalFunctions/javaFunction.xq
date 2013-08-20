(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :) 

(: Invoke a Java static method from an XQuery expression :)

(: Declare a namespace prefix that maps to the java.lang.System class :)
declare namespace java-System ="ddtekjava:java.lang.System";

declare option ddtek:serialize "indent=yes";

(: Declare the getProperty method the java.lang.System class as an external XQuery function :)
declare function java-System:getProperty($key as xs:string) as xs:string? external;

(: An example XQuery expression that uses the getProperty method :)
element java 
  { element javaInstallDir {java-System:getProperty('java.home')},
    element javaVersion {java-System:getProperty('java.version')}
  }
,
element os 
  { element osName {java-System:getProperty('os.name')},
    element osVersion {java-System:getProperty('os.version')}
  }