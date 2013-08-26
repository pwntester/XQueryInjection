import javax.xml.xquery.*;
import javax.xml.namespace.QName;
import net.xqj.sedna.SednaXQDataSource;

public class Test4
{
  public static void main(String[] args) throws XQException  {
    XQConnection conn = null;
    XQExpression xqpe = null;
    XQResultSequence rs = null;
    try {
      XQDataSource xqs = new SednaXQDataSource();
      xqs.setProperty("serverName", "localhost");
      xqs.setProperty("databaseName", "test");

      // [RuleTest] Unreleased Resource Database
      conn = xqs.getConnection("SYSTEM", "MANAGER");

      // args0 should be a valid XQuery statement like {$x + 1}
      String query = "<hello-world>" + args[0] + "</hello-world>";

      // [RuleTest]! Unrelease Resource Database
      xqpe = conn.createExpression();

      // [RuleTest] XQuery Injection
      // [RuleTest]! Unrelease Resource Database
      rs = xqpe.executeQuery(args[0]);

      while (rs.next()) {
        // [RuleTest] Sink 
        sink(rs.getSequenceAsString(null));
      }
    } catch (XQException e) {


    } finally {
      if (rs != null) safeCloseSequence(rs);
      if (xqpe != null) safeCloseExpression(xqpe); 
    }

    

  }
  public static void sink(String s) {
    System.out.println(s);
  }
  public static void log(Object s) {
    System.out.println(s);
  }
  public static void safeCloseSequence(XQResultSequence rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (XQException e) {
        log(e);
      }
    }
  }
	public static void safeCloseExpression(XQExpression expr) {
	  if (expr != null) {
	    try {
	      expr.close();
	    } catch (XQException e) {
	      log(e);
	    }
	  }
	}
}