import javax.xml.xquery.*;
import javax.xml.namespace.QName;
import net.xqj.sedna.SednaXQDataSource;

public class Test2
{
  public static void main(String[] args) throws XQException
  {
    XQDataSource xqs = new SednaXQDataSource();
    xqs.setProperty("serverName", "localhost");
    xqs.setProperty("databaseName", "test");

    XQConnection conn = xqs.getConnection("SYSTEM", "MANAGER");

    // args0 should be a valid XQuery statement like {$x + 1}
    String query = "<hello-world>" + args[0] + "</hello-world>";

    // [RuleTest] XQuery Injection
    XQPreparedExpression xqpe = conn.prepareExpression(query);

    XQResultSequence rs = xqpe.executeQuery();

    while(rs.next()) {
      // [RuleTest] Persistent Cross-Site scripting  
      sink(rs.getSequenceAsString(null));
    }

    // [RuleTest] Connection not closed 
    // [RuleTest] Result not closed 
    // [RuleTest] expression not closed 
  }
  // Sink for PXSS
  public static void sink(String s) {
    System.out.println(s);
  }
}