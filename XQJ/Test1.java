import javax.xml.xquery.*;
import javax.xml.namespace.QName;
import net.xqj.sedna.SednaXQDataSource;

public class Test1
{
  public static void main(String[] args) throws XQException
  {
    XQDataSource xqs = new SednaXQDataSource();
    xqs.setProperty("serverName", "localhost");
    xqs.setProperty("databaseName", "test");

    XQConnection conn = xqs.getConnection("SYSTEM", "MANAGER");

    XQPreparedExpression xqpe =
    conn.prepareExpression("declare variable $x as xs:string external; $x");

    // [RuleTest] Access Control: Database 
    xqpe.bindString(new QName("x"), args[0], null);

    XQResultSequence rs = xqpe.executeQuery();

    while(rs.next())
      System.out.println(rs.getItemAsString(null));

    conn.close();
    // [RuleTest] Result not closed 
    // [RuleTest] expression not closed 
  }
}