import com.ddtek.xquery3.XQResultSequence;
import com.ddtek.xquery3.xqj.DDXQDataSource;
import com.ddtek.xquery3.XQConnection;
import com.ddtek.xquery3.XQPreparedExpression;
import com.ddtek.xquery3.XQException;
 
 
public class XQueryInjection_DataDirect {
    
    public static void main(String[] args) throws XQException {
        
        DDXQDataSource xqds = new DDXQDataSource();
        xqds.setBaseUri("data");
        XQConnection con = xqds.getConnection(); 
        // args0 should be a valid XQuery statement like {1 + 1}
        String query = "<hello-world>" + args[0] + "</hello-world>";
        XQPreparedExpression expr = con.prepareExpression(query); 
        XQResultSequence result = expr.executeQuery();
        System.out.println(result.getSequenceAsString(null));
        
        result.close();
        expr.close();
        con.close();
    }
    
}