import javax.xml.xquery.*;
import javax.xml.namespace.QName;
import net.xqj.sedna.SednaXQDataSource;

public class Test3
{
  public static void main(String[] args) throws XQException  {
    XQConnection conn = null;
    XQExpression expr = null;
    try {
      XQDataSource xqs = new SednaXQDataSource();
      xqs.setProperty("serverName", "localhost");
      xqs.setProperty("databaseName", "test");

      // [RuleTest]! Unreleased Resource Database
      conn = xqs.getConnection("SYSTEM", "MANAGER");
      
      // [RuleTest]! Unrelease Resource Database
      expr = conn.createExpression();
      // [RuleTest] XQuery Injection
      expr.executeCommand(args[0]);
    } catch (XQException e) {

    } finally {
    	if (conn != null) safeCloseConnection(conn);
    }

  }
  public static void log(Object s) {
    System.out.println(s);
  }
	public static void safeCloseConnection(XQConnection conn) {
		  if (conn != null) {
		    try {
		      conn.close();
		    } catch (XQException e) {
		      log(e);
		    }
		  }
		}
}