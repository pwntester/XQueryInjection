import javax.xml.xquery.*;
import javax.xml.namespace.QName;
import net.xqj.sedna.SednaXQDataSource;

public class Test1
{
  public static void main(String[] args) throws XQException {
    XQConnection conn = null;
    XQPreparedExpression xqpe = null;
    XQResultSequence rs = null;
    try {
      XQDataSource xqs = new SednaXQDataSource();
      xqs.setProperty("serverName", "localhost");
      xqs.setProperty("databaseName", "test");

      // [RuleTest]! Unrelease Resource Database
      conn = xqs.getConnection("SYSTEM", "MANAGER");

      // [RuleTest]! Unrelease Resource Database
      xqpe = conn.prepareExpression("declare variable $x as xs:string external; $x");

      // [RuleTest] Access Control: Database 
      xqpe.bindString(new QName("x"), args[0], null);

      // [RuleTest]! Unrelease Resource Database
      rs = xqpe.executeQuery();

      while(rs.next()) {
        System.out.println(rs.getItemAsString(null));
      }

    } catch (XQException e) {

    } finally {
	    if (rs != null) safeCloseSequence(rs);
	    if (xqpe != null) safeCloseExpression(xqpe);
    	if (conn != null) safeCloseConnection(conn);
    }
  
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
	public static void safeCloseExpression(XQPreparedExpression expr) {
	  if (expr != null) {
	    try {
	      expr.close();
	    } catch (XQException e) {
	      log(e);
	    }
	  }
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