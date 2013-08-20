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
 * A basic XQJ example. 
 *
 * Execute an XQuery expression and return the result.
 *
 * Usage:<br>
 * <pre>
 * java XQJExecute xqueryFile
 *     Executes the XQuery expression from the specified xqueryFile
 * 
 * java XQJExecute
 *     Presents the user with a list of XQuery files to execute or allows entering
 *     an expression 'manually'
 * </pre>
 *
 * <br>When XQJExecute is invoked with a single argument, the argument has to 
 * point to an existing file. The file needs to contain a valid XQuery 
 * expression that will be executed. The result of the XQuery expression 
 * is written to the standard output.
 *
 * <br>When invoked without arguments, the user can select an XQuery file from one
 * of the provided XQuery files or 'manually' enter an XQuery expression. 
 * The expression in the file or the manually entered expression will be 
 * executed and the result written to the standard output.
 *
 * The following XQuery example files are provided:
 * <ul>
 * <li>collection-holdings.xq: table 'holdings'.</li>
 * <li>collection-users.xq: table 'users'.</li>
 * <li>flwor.xq: an example flwor-xquery.</li>
 * <li>JoinXMLToRelational.xq: a xquery joining an XML doc with relational data.</li>
 * <li>function.xq: an example with user-defined functions.</li>
 * <li>portfolioHtml.xq: an example generating HTML.</li>
 * <li>mainModule.xq: an example using user-defined functions from a module.</li>
 * <li>nodeId.xq: an example showing node-identity.</li>
 * </ul>
 * 
 * 
 */
public class XQJExecute {
  
  /**
   * Execute an XQuery expression and return the result.
   * 
   * @param args - optional single file with an XQuery expression to be executed.
   */
  public static void main(String[] args) throws IOException {

    // Pick up system setting JDBCURL: e.g. set by setenv and should contain a valid JDBC connection URL.
    String jdbcUrl = System.getProperty("JDBCURL");
    
    if (args != null && args.length == 1) {
      // User supplied an argument: run the XQuery expression in the file
      runXQuery(jdbcUrl, args[0]); 
    }
    else {
      // No argument supplied: ask user which XQuery expression to execute and execute it.
      selectAndRun(jdbcUrl);
    }
    
  }
  
  /**
   * Executes an XQuery expression from a file. 
   * The XQuery expression is passed to XQJ as a FileReader. 
   * 
   * @param jdbcUrl the JDBC connection url
   * @param xqueryFile the filename containing the XQuery expression
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
      // User feedback - write JDBC URL to standard output.
      Util.logConnectInfo(jdbcUrl);
      
      // Create connection from the datasource
      xqConnection = xqDataSource.getConnection();

      // Create an XQJ XQuery expression based on the connection
      XQExpression xqExpression = xqConnection.createExpression();

      // User feedback - write expression that will be executed to the standard output
      Util.logXQueryFromFile(xqueryFile);
      
      // Execute XQuery expression from a FileReader.  this generates an XQuery sequence
      XQSequence xqSequence = xqExpression.executeQuery(new FileReader(xqueryFile));

      // User feedback - write result to the standard output
      String output = xqSequence.getSequenceAsString(new Properties());
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
          // Close xqConnection (also closes any open xqExpressions)
          xqConnection.close();
        }
        catch (XQException ex) {
          System.out.print("Close of xqConnection failed: ");
          System.out.println(ex.getMessage());
        }
      }
    }
  }
  
  /**
   * Executes an XQuery expression from a String argument. 
   * 
   * @param jdbcUrl the JDBC connection url
   * @param xquery the XQuery expression 
   */
  public static void runXQueryString(String jdbcUrl, String xquery) {

    XQConnection xqConnection = null;

    try {
      // Create a datasource
      XQDataSource xqDataSource = new com.ddtek.xquery.xqj.DDXQDataSource();

      if (jdbcUrl != null && jdbcUrl.length() > 0) {
        // Supply the database connection information
        xqDataSource.setProperty(com.ddtek.xquery.xqj.DDXQDataSource.JDBCURL, jdbcUrl);
      }
      // User feedback - write JDBC URL to standard output.
      Util.logConnectInfo(jdbcUrl);
      
      // Create connection from the datasource
      xqConnection = xqDataSource.getConnection();

      // Create an XQJ XQuery expression based on the connection
      XQExpression xqExpression = xqConnection.createExpression();

      // Execute expression from the String argument. Result is an XQSequence.
      Util.logXQuery(xquery);
      XQSequence xqSequence = xqExpression.executeQuery(xquery);

      String output = xqSequence.getSequenceAsString(new Properties());
      Util.logResult(output);
    }
    catch (XQException ex) {
      System.out.print("XQuery execution failed: ");
      System.out.println(ex.getMessage());
    }
    finally {
      if (xqConnection != null) {
        try {
          // Close xqConnection: also closes any open xqExpressions
          xqConnection.close();
        }
        catch (XQException ex) {
          System.out.print("Close of xqConnection failed: ");
          System.out.println(ex.getMessage());
        }
      }
    }
  }
  
  // Filenames of provided example queries
  private static String xqueryInputFiles[] = { 
    "collection-users.xq",
    "collection-holdings.xq", 
    "flwor.xq", 
    "JoinXMLToRelational.xq", 
    "function.xq",
    "portfolioHtml.xq",
    "MainModule.xq", 
    "nodeId.xq" }; 

  /**
   * Request user which one of the example queries to execute and execute it or
   * allow user to enter XQuery expression 'manually'.
   */
  public static void selectAndRun(String jdbcUrl) throws IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    String typeInXquery = String.valueOf(xqueryInputFiles.length + 1);
    while (true) {
      // Display list of available example queries
      System.out.println();
      System.out.println("Please enter the query to execute:");
      for (int i=0; i<xqueryInputFiles.length; i++) {
        System.out.println((i+1) + " - " + xqueryInputFiles[i]);
      }
      // Option allowing the user to manually enter an XQuery expression
      System.out.println(typeInXquery + " - type in an xquery");
      // The 'quit' option
      System.out.println("q - quit this example");
      System.out.println();
      System.out.print("Enter your choice:");
      System.out.flush();
      // Get users input
      String userInput = input.readLine();
      userInput = userInput.trim();
      System.out.println();
      // Figure out which option the user has selected
      if (userInput.equals(typeInXquery)) {
        // User choose to manually enter an XQuery expression
        enterAndRun(input, jdbcUrl);
      }
      else if (userInput.equals("q")) {
        // User wants to quit
        System.out.println("Exiting example program.");
        System.exit(1);
      }
      else {
        int choice = 0;
        try {
          choice = Integer.valueOf(userInput).intValue();
        }
        catch (NumberFormatException ex) {
          // User entry not valid - ignore
        }
        if (choice > 0 && choice <= xqueryInputFiles.length) {
          // Run one of the provided examples
          runXQuery(jdbcUrl, xqueryInputFiles[choice-1]);
          System.out.println();
          System.out.print("Press enter to continue, q to quit...");
          System.out.flush();
          userInput = input.readLine();
          if (userInput.trim().equals("q")) {
            // User wants to quit
            System.out.println();
            System.out.println("Exiting example program.");
            System.exit(1);
          }
        }
        else {
          // User entry not valid - ignore
          System.out.println("Invalid choice.");
          System.out.println();
        }
      }
    }
  }

  /**
   * Read XQuery expression from console input and execute it.
   */
  public static void enterAndRun(BufferedReader input, String jdbcUrl) throws IOException {
    // Get newline character for 'current platform'
    String newLine = System.getProperty("line.separator");
    // Ask user for an XQuery expression
    System.out.println();
    System.out.println("Enter your XQuery expression");
    System.out.println("Type \";\" on a separate line to execute");
    System.out.println("Type \"exit\" on a separate line to terminate without executing");

    // Read XQuery expression
    StringBuffer xqueryStrBuff = new StringBuffer();
    while (true) {
      System.out.println();
      
      xqueryStrBuff.setLength(0);
      String readLine = "";
      do {
        System.out.print(">> ");
        System.out.flush();
        readLine = input.readLine();
        if (readLine.trim().equals(";")) {
          // User has finished entering his query
          break;
        }
        if (readLine.trim().equals("exit")) {
          // User wants to terminate
          return;
        }
        xqueryStrBuff.append(readLine);
        xqueryStrBuff.append(newLine);
      } while (true);
      System.out.println();
      // Execute entered query
      String xquery = xqueryStrBuff.toString();
      runXQueryString(jdbcUrl, xquery);
    }
  }
}
