import javax.xml.xquery.*;
import javax.xml.namespace.QName;
import net.xqj.sedna.SednaXQDataSource;

public class Test2
{
  public static void main(String[] args) throws XQException  {
    XQConnection conn = null;
    XQPreparedExpression xqpe = null;
    XQResultSequence rs = null;
    try {
      XQDataSource xqs = new SednaXQDataSource();
      xqs.setProperty("serverName", "localhost");
      xqs.setProperty("databaseName", "test");

      // [RuleTest]! Unreleased Resource Database
      conn = xqs.getConnection("SYSTEM", "MANAGER");

      // args0 should be a valid XQuery statement like {$x + 1}
      String query = "<hello-world>" + args[0] + "</hello-world>";

      // [RuleTest] XQuery Injection
      // [RuleTest]! Unrelease Resource Database
      xqpe = conn.prepareExpression(query);

      // [RuleTest]! Unrelease Resource Database
      rs = xqpe.executeQuery();

      while(rs.next()) {
        // [RuleTest] Sink  
        sink(rs.getSequenceAsString(null));
      }
    } catch (XQException e) {

    } finally {
    	if (conn != null) safeCloseConnection(conn);
    }


  
  }
  public static void sink(String s) {
    System.out.println(s);
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