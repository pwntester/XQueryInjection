import javax.xml.xquery.*;
import javax.xml.namespace.QName;
import net.xqj.sedna.SednaXQDataSource;

public class Test3
{
  public static void main(String[] args) throws XQException
  {
    XQDataSource xqs = new SednaXQDataSource();
    xqs.setProperty("serverName", "localhost");
    xqs.setProperty("databaseName", "test");

    XQConnection conn = xqs.getConnection("SYSTEM", "MANAGER");

    XQExpression expr = conn.createExpression();
    // [RuleTest] XQuery Injection
    expr.executeCommand(args[0]);

    conn.close();
    // [RuleTest] expression not closed 
  }
}