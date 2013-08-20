/*
 * Copyright(c) 2003-2009 Progress Software Corporation. All rights reserved. 
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQSequence;
import com.ddtek.xquery.xqj.DDXQDataSource;

/**
 * Custom document URI example.
 *
 * Register a custom URI resolver and execute an XQuery expression.
 * 
 * Usage is:<br>
 *   java CustomURIResolver [<path to XQJ query file>]
 */
public class CustomDocumentURIResolver {
  /**
   * Executes an XQuery expression and logs output to standard output.
   * 
   * @param args - a list of three strings that represent the path to the
   * config file, the path to an XQuery source file, and the path to a well-formed
   * XML data file. The third argument is optional - if left off, no file will be created
   * and written, and the output will go to the current System.out.
   */
  public static void main(String[] args) throws XQException, FileNotFoundException, IOException {

    String xquerySourceFile = checkArgs(args);

    XQConnection xqConnection = null;
    
    try {
      // Create datasource
      DDXQDataSource xqDataSource = new com.ddtek.xquery.xqj.DDXQDataSource();
      // Register the custom URI resolver !!!!!
      String uriResolver = "CustomDocumentURIResolverImpl";
      System.out.println("Registering URIResolver " + uriResolver);
      System.out.println();
      xqDataSource.setDocumentUriResolver(uriResolver);
      // Create connection
      xqConnection = xqDataSource.getConnection();
      // Create an XQuery expression
      XQExpression xqExpression = xqConnection.createExpression();
      // User feedback - write XQuery expression to standard output
      Util.logXQueryFromFile(xquerySourceFile);
      // Execute XQuery expression from file
      XQSequence xqSequence = xqExpression.executeQuery(new FileReader(xquerySourceFile));
      // User feedback - write XQuery result to standard output. 
      Util.logResult(xqSequence.getSequenceAsString(new Properties()));
    } catch (XQException ex) {
      System.out.print("XQuery execution failed: ");
      System.out.println(ex.getMessage());
    } finally {
      if (xqConnection != null) {
        try {
          // Close xqConnection (also closes any open xqExpressions)
          xqConnection.close();
        } catch (XQException ex) {
          System.out.print("Close of xqConnection failed: ");
          System.out.println(ex.getMessage());
        }
      }
    }
  }
  
  /*
   * Check example usage
   */
  private static String checkArgs(String[] args) {
    // Check number of arguments
    if (args.length > 1) {
      String usage = "Usage: java CustomDocumentURIResolver [queryfile]";
      System.out.println(usage);
      System.out.println("Exiting CustomDocumentURIResolver ...");
      System.exit(1);
    }
    return (args.length >= 1) ? args[0] : "CustomDocumentURIResolver.xq";
  }

}
