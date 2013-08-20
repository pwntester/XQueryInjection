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
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQSequence;
import com.ddtek.xquery.xqj.DDXQDataSource;

/**
 * A basic RDBMS update example. 
 *
 * - Execute an XQuery expression to check RDBMS content and return the result
 * - Execute an updating XQuery expression to modify RDBMS content
 * - Execute the same initial XQuery expression to check RDBMS changes
 *
 * Usage:<br>
 * <pre>
 * java RDBMSUpdate xqueryFile
 *     Executes the XQuery expression from the specified xqueryFile
 * 
 * java RDBMSUpdate
 *     Presents the user with a list of XQuery files to execute
 * </pre>
 *
 * <br>When RDBMSUpdate is invoked with a single argument, the argument has to 
 * point to an existing file containing an "updating" XQuery expression. A file
 * named "check-"xqueryFile needs to be also present to work as a check on RDBMS content
 * before and after the updating XQuery is executed. The results of the XQuery expressions 
 * are written to the standard output.
 *
 * <br>When invoked without arguments, the user can select an XQuery file from one
 * of the provided XQuery files. 
 *
 * The following XQuery example files are provided:
 * <ul>
 * <li>insert-holdings.xq: simple insert in 'holdings' table.</li>
 * <li>update-holdings.xq: simple update on data in 'holdings' table.</li>
 * <li>delete-holdings.xq: simple delete on data in 'holdings' table.</li>
 * <li>update-holdings-from-xml.xq: use data in an XML document to update 'holdings' table.</li>
 * <li>update-function.xq: simple updating user defined function.</li>
 * <li>shredding-xml.xq: an example shredding an XML documents into multiple tables.</li>
 * </ul>
 * 
 * 
 */
public class RDBMSUpdate {
  
  // The datasource
  static DDXQDataSource xqDataSource = new DDXQDataSource();

  static XQConnection xqConnection = null;
  /**
   * Execute an XQuery expression to show RDBMS content before update
   * Execute an XQuery expression to perform RDBMS update
   * Exectue an XQuery expression to show RDBMS content after update
   */
  public static void main(String[] args) throws IOException {

	// Pick up system setting JDBCURL: e.g. set by setenv and should contain a valid JDBC connection URL.
    String jdbcUrl = System.getProperty("JDBCURL");

	// User feedback - write JDBC URL to standard output.
    Util.logConnectInfo(jdbcUrl);

	try {
        if (jdbcUrl != null && jdbcUrl.length() > 0) {
          // Supply the database connection information
          xqDataSource.setProperty(com.ddtek.xquery.xqj.DDXQDataSource.JDBCURL, jdbcUrl);
        }
		xqDataSource.setOptions("serialize=indent=yes");
		// Create connection from the datasource
		xqConnection = xqDataSource.getConnection();
	} catch (XQException ex) {
	  System.out.print("Creation of xqConnection failed: ");
	  System.out.println(ex.getMessage());
	  System.exit(2);
	}
  
    if (args != null && args.length == 1) {
      // User supplied an argument: run the XQuery expression in the file
      runXQuery(args[0]); 
    }
    else {
      // No argument supplied: ask user which XQuery expression to execute and execute it.
      selectAndRun();
    }

    try {
      // Close xqConnection (also closes any open xqExpressions)
      xqConnection.close();
    } catch (XQException ex) {
      System.out.print("Close of xqConnection failed: ");
      System.out.println(ex.getMessage());
    }
  }
  
  /**
   * Executes an XQuery expression from a file. 
   * The XQuery expression is passed to XQJ as a FileReader. 
   * 
   * @param xqueryFile the filename containing the XQuery expression
   */
  public static void runXQuery(String xqueryFile) {
    
    try {
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
    }
  }
  

  // Filenames of provided example queries
  private static String xqueryInputFiles[] = { 
    "insert-holdings.xq",
    "update-holdings.xq", 
    "delete-holdings.xq", 
    "update-holdings-from-xml.xq", 
    "update-function.xq",
    "shredding-xml.xq"}; 

  /**
   * Request user which one of the example queries to execute and execute it or
   * allow user to enter XQuery expression 'manually'.
   */
  public static void selectAndRun() throws IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      // Display list of available example queries
      System.out.println();
      System.out.println("Please enter the query to execute:");
      for (int i=0; i<xqueryInputFiles.length; i++) {
        System.out.println((i+1) + " - " + xqueryInputFiles[i]);
      }
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
      if (userInput.equals("q")) {
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
          // Run one of the provided examples to fetch current RDBMS content
          runXQuery("check-"+xqueryInputFiles[choice-1]);
          // Run one of the provided examples
          runXQuery(xqueryInputFiles[choice-1]);
          // Run one of the provided examples to fetch current RDBMS content
          runXQuery("check-"+xqueryInputFiles[choice-1]);
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

}
