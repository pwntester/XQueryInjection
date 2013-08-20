/*
 * Copyright(c) 2003-2009 Progress Software Corporation. All rights reserved. 
 * 
 */
import java.io.File;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import com.ddtek.xquery.xqj.DDXQDataSource;
/**
 * Example to illustrate how to save and load a DataDirect XQuery DDXQDataSource 
 * using a JNDI provider.
 * 
 * The example uses the Sun JNDI file system provider that can be downloaded from
 *    
 *   http://javashoplm.sun.com/ECom/docs/Welcome.jsp?StoreId=22&PartDetailId=7110-jndi-1.2.1-oth-JPR&SiteId=JSC&TransactionId=noreg
 * 
 * After downloading the fscontext-1_2-beta3.zip file, unzip it and 
 * add fscontext.jar and providerUtil.jar to the CLASSPATH before executing the example.
 * 
 */
public class JNDIDataSource {
  
  /*
   * Main 
   */
  public static void main(String[] args) {
    try {
      // Create the DDXQDataSource, setting the JDBCURL if it is provided (see setenv.bat)
      String jdbcUrl = System.getProperty("JDBCURL");
      DDXQDataSource dataSource1 = new DDXQDataSource();
      if (jdbcUrl != null) {
        dataSource1.setJdbcUrl(jdbcUrl);
      }

      // JNDI code - save datasource using file system provider
      Hashtable env = new Hashtable();
      env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
      Context context = new InitialContext(env);
      File curDirFile = new File("");
      String jndiName = curDirFile.getAbsolutePath() + File.separatorChar + "ddxq";

      // Note - use rebind to allow multiple executions of the example
      context.rebind(jndiName, dataSource1);

      // JNDI code - load the just saved datasource from JNDI 
      DDXQDataSource dataSource2 = (DDXQDataSource) context.lookup(jndiName);
      if (dataSource2 == null) {
        System.out.println("JNDI lookup of "  +  jndiName + " failed");
        System.exit(1);
      }

      // If a DDXQDataSource was successfully retrieved use it to create an XQJ connection
      XQConnection connection = dataSource2.getConnection();
      System.out.println("Connection succeeded");
      // Clean up
      if (connection != null) {
        try {
          // Close
          connection.close();
        } catch (XQException e) {
          e.printStackTrace();
        }
      }
    } catch (XQException e) {
      e.printStackTrace();
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }
}
