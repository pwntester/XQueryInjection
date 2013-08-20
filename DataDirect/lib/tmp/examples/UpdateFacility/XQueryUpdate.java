/*
 * Copyright(c) 2003-2009 Progress Software Corporation. All rights reserved. 
 *
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.Properties;

import javax.xml.namespace.QName;

import com.ddtek.xquery.xqj.DDXQDataSource;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQSequence;
import javax.xml.xquery.XQStaticContext;
import javax.xml.xquery.XQConstants;
import javax.xml.xquery.XQItemType;

/**
 * Basic exampels about use of the XQuery Update Facility. 
 *
 * Usage:<br>
 * <pre>
 * java XQueryUpdate xqueryFile
 *     Executes the XQuery expression from the specified xqueryFile
 * 
 * java XQueryUpdate
 *     Presents the user with a list of XQuery files to execute
 * </pre>
 *
 * <br>When XMLQuery is invoked with a single argument, the argument has to 
 * point to an existing file. The results of the XQuery expressions 
 * are written to the standard output.
 *
 * <br>When invoked without arguments, the user can select an XQuery file from one
 * of the provided XQuery files. 
 *
 * The following XQuery example files are provided:
 * <ul>
 * <li>rename-nodes.xq: rename "UserId" elements to be "ID"  and save the result to a new XML document.</li>
 * <li>change-values.xq: grant 20% more shares to a user and save the result to a new XML document.</li>
 * <li>insert-nodes.xq: grant all users new shares (creating or updating entries); save the result to a new XML document.</li>
 * <li>transform-change-values.xq: grant 20% more shares to a user transforming the result returned by the XQuery.</li>
 * <li>transform-insert-nodes.xq: grant all users new shares (creating or updating entries); return the changes as the XQuery result.</li>
 * </ul>
 * 
 * 
 */
public class XQueryUpdate {
  
  static String exampleFolder = new String("");
  /**
   * Execute an XQuery expression to perform XML-only querying
   */
  public static void main(String[] args) throws IOException {
    if (args != null && args.length == 1) {	// Argument contains folder for these examples
		exampleFolder = args[0];
	}
	selectAndRun();
  }
  
  /**
   * Executes an XQuery expression from a file. 
   * The XQuery expression is passed to XQJ as a FileReader. 
   * 
   * @param xqueryFile the filename containing the XQuery expression
   */
  public static void runXQuery(String xqueryFile) {
    
    XQConnection xqConnection = null;
    
    try {
      // Create a datasource
      DDXQDataSource xqDataSource = new DDXQDataSource();
	  xqDataSource.setOptions("serialize=indent=yes");

      // Create connection from the datasource
      xqConnection = xqDataSource.getConnection();

      // Create an XQJ XQuery expression based on the connection
      XQExpression xqExpression = null;

      // User feedback - write expression that will be executed to the standard output
      Util.logXQueryFromFile(xqueryFile);
      xqExpression = xqConnection.createExpression();

      // Execute XQuery expression from a FileReader.  this generates an XQuery sequence
      XQSequence xqSequence = xqExpression.executeQuery(new FileReader(xqueryFile));

      // User feedback - write result to the standard output
      String output = xqSequence.getSequenceAsString(new Properties());
      Util.logResult(output);
	  if(xqueryFile == "rename-nodes.xq")
		  System.out.println("An updated \"new-users.xml\" has been created in the current directory.");
	  else if(xqueryFile == "change-values.xq")
		  System.out.println("An updated \"more-shares.xml\" has been created in the current directory.");
	  else if(xqueryFile == "insert-nodes.xq")
		  System.out.println("An updated \"more-ddtek-holdings.xml\" has been created in the current directory.");
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
  

  // Filenames of provided example queries
  private static String xqueryInputFiles[] = { 
    "rename-nodes.xq",
    "change-values.xq", 
    "insert-nodes.xq", 
    "transform-change-values.xq",
    "transform-insert-nodes.xq"};

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
          // Run one of the provided examples
          runXQuery(xqueryInputFiles[choice-1]);
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
