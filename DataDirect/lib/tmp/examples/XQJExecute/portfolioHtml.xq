(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)
declare option ddtek:serialize "indent=yes";

(: Example that generates HTML :)
declare variable $user := 'Jonathan';
<html>
    <head>
        <title>Portfolio for {$user}</title>
    </head>
    <body>
        <h1>Portfolio for {$user}</h1>
        <table>
          <thead>
             <tr>
               <td>Ticker</td>
               <td>Shares</td>
             </tr>
          </thead>
          <tbody>{
              for $st in collection('holdings')/holdings
              where xs:string($st/userid) = xs:string($user)
              return
                <tr>
                  <td>{ data($st/stockticker) }</td>
                  <td>{ data($st/shares) }</td>
                </tr>
            }</tbody>                    
    </table>
  </body>
</html>
