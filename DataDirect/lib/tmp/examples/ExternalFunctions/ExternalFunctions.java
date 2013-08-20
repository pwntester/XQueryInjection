/*
 * Copyright(c) 2003-2009 Progress Software Corporation. All rights reserved. 
 * 
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQSequence;

/**
 * Example showing how to write and execute XQuery expressions that use 
 * external functions.
 *  
 * The XQuery expression is executed and the output is dumped to the standard output<p>
 *
 * Examples provided are
 * <ul>
 * <li>javaFunction.xq: calling a static Java methods.</li>
 * <li>javaInstanceMethod.xq: calling a Java instance methods.</li>
 * <li>sqlFunction.xq: calling a SQL function.</li>
 * <li>sqlFunctionFromModule_db2luw.xq: an example using the module containing some of the DB2/LUW SQL functions.</li>
 * <li>sqlFunctionFromModule_ora.xq: an example using the module containing some of the Oracle SQL functions.</li>
 * <li>sqlFunctionFromModule_sqlserver.xq: an example using the module containing some of the SQL Server SQL functions.</li>
 * <li>sqlFunctionFromModule_informix.xq: an example using the module containing some of the Informix SQL functions.</li>
 * <li>sqlFunctionFromModule_mysql.xq: an example using the module containing some of the MySql SQL functions.</li>
 * <li>sqlFunctionFromModule_postgresql.xq: an example using the module containing some of the Postgresql SQL functions.</li>
 * <li>jdbcEscapeFunction.xq: an example using a JDBC escape function.</li>
 * </ul>
 * 
 * Usage<br>
 * <pre>
 * java ExternalFunctions
 *     prompts the user for the example to execute
 * java ExternalFunctions xqueryFile
 *     runs the specified query
 * </pre>
 * 
 * If the XQuery you want to execute contains collection calls, you must specify a
 * valid JDBCURL through a system setting: <b>JDBCURL</b>.
 */
public class ExternalFunctions {

  /**
   * Executes an xquery and logs output to System.out.
   * 
   * @param args - an optional argument: an xqueryFile to be execute.
   */
  public static void main(String[] args) throws Exception {

    // Pick up system setting JDBCURL: e.g. set by setenv and should contain a valid JDBC connection URL.
    String jdbcUrl = System.getProperty("JDBCURL");
    
    if (args != null && args.length == 1) {
      // User supplied an argument: execute that query
      runXQuery(jdbcUrl, args[0]); 
    } else {
      // No argument supplied: prompt user asking which query to execute
      executeExample(jdbcUrl);
    }
  }
  
  /**
   * Executes an XQuery expression. 
   * 
   * The XQuery expression is read from the provided filename 
   * (xqueryFile) argument. 
   * 
   * @param jdbcUrl the JDBC connection url
   * @param xqueryFile the filename containing the XQuery to execute
   * 
   * @throws XQException
   * @throws FileNotFoundException if the queryFile is not found
   */
  public static void runXQuery(String jdbcUrl, String xqueryFile) {

    XQConnection xqConnection = null;
    try {
      // Create a datasource
      XQDataSource xqDataSource = new com.ddtek.xquery.xqj.DDXQDataSource();
      if (jdbcUrl != null && jdbcUrl.length() > 0) {
        // Supply the database connection information
        xqDataSource.setProperty(com.ddtek.xquery.xqj.DDXQDataSource.JDBCURL, jdbcUrl);
      }
      // User feedback - write the JDBC URL to the standard output.
      Util.logConnectInfo(jdbcUrl);
      // Create a connection from the XQDataSource object
      xqConnection = xqDataSource.getConnection();
      // Create an XQuery expression from the XQConnection
      XQExpression xqExpression = xqConnection.createExpression();
      // User feedback - write expression that will be execute to standard output
      Util.logXQueryFromFile(xqueryFile);
      // Execute the XQuery expression and retrieve the result as an XQJ XQSquence instance
      XQSequence xqSequence = xqExpression.executeQuery(new FileReader(xqueryFile));
      // Retrieve result as a Java String
      String output = xqSequence.getSequenceAsString(new Properties());
      // User feedback - write result to standard output
      Util.logResult(output);
    } catch (XQException ex) {
      System.out.print("XQuery execution failed: ");
      System.out.println(ex.getMessage());
    } catch (FileNotFoundException ex) {
      System.out.print("XQuery execution failed: input file not found: ");
      System.out.println(ex.getMessage());
    } finally {
      if (xqConnection != null) {
        try {
          // Close xqConnection: also closes any open xqExpressions
          xqConnection.close();
        } catch (XQException ex) {
          System.out.print("Close of xqConnection failed: ");
          System.out.println(ex.getMessage());
        }
      }
    }
  }

  // Provided test queries
  private static String tests[] = { 
        "javaFunction.xq", 
        "javaInstanceMethod.xq", 
        "sqlFunction.xq",
        "sqlFunctionFromModule_db2luw.xq", 
        "sqlFunctionFromModule_ora.xq", 
        "sqlFunctionFromModule_sqlserver.xq",
        "sqlFunctionFromModule_informix.xq",
        "sqlFunctionFromModule_mysql.xq",
        "sqlFunctionFromModule_postgresql.xq",
        "jdbcEscapeFunction.xq"}; 

  /**
   * Prompt user for query to execute and execute it
   */
  public static void executeExample(String jdbcUrl) throws IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      System.out.println();
      System.out.println("Please specify the example to run:");
      for (int i=0; i<tests.length; i++) {
        System.out.println((i+1) + " - " + tests[i]);
      }
      System.out.println("q - quit this example");
      System.out.println();
      System.out.print("Enter your choice:");
      System.out.flush();
      String userInput = input.readLine();
      userInput = userInput.trim();
      System.out.println();
      if (userInput.equals("q")) {
        System.out.println("Exiting example program.");
        System.exit(1);
      } else {
        int choice = 0;
        try {
          choice = Integer.valueOf(userInput).intValue();
        } catch (NumberFormatException ex) {
          // Ignore
        }
        if (choice > 0 && choice <= tests.length) {
          runXQuery(jdbcUrl, tests[choice-1]);
          System.out.println();
          System.out.print("Press enter to continue, q to quit...");
          System.out.flush();
          userInput = input.readLine();
          if (userInput.trim().equals("q")) {
            System.out.println();
            System.out.println("Exiting example program.");
            System.exit(1);
          }
        } else {
          System.out.println("Invalid choice.");
          System.out.println();
        }
      }
    }
  }
}
