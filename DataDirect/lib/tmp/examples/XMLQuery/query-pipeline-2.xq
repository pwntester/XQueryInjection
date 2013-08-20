(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: Create HTML to list all holdings :)
<html>
  <body>
    <table>
      <tbody> {
        for $holding in /holdings/holding
        return
          <tr>
            <td>{$holding/ticker/text()}</td>
            <td>
              <ul> {
                for $user in $holding/users/user
                return
                  <li>{concat($user/id/text(), ", ", $user/shares/text())}</li>
              } </ul>
            </td>
          </tr>
      } </tbody>
    </table>
  </body>
</html>
