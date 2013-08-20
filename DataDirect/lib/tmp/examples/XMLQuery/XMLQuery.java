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
 * Basic exampels about running XQueries against XML-only files. 
 *
 * Usage:<br>
 * <pre>
 * java XMLQuery xqueryFile
 *     Executes the XQuery expression from the specified xqueryFile
 * 
 * java XMLQuery
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
 * <li>query-initial-context.xq: simple query of the initial context document.</li>
 * <li>query-doc-function.xq: simple query of a document accessed using the fn:doc() function.</li>
 * <li>query-external-variable.xq: simple query of a document accessed as an external variable.</li>
 * <li>query-directory.xq: simple query of all XML files in a folder.</li>
 * <li>query-pipeline.xq: simple pipelining of two XQueries (output of first XQuery used as input of second one).</li>
 * </ul>
 * 
 * 
 */
public class XMLQuery {
  
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
      
      if(xqueryFile.equals("query-initial-context.xq"))	// Set initial context type and document
	  {
         XQStaticContext cntxt = xqConnection.getStaticContext();
         cntxt.setContextItemStaticType(xqConnection.createDocumentElementType(xqConnection.createElementType(null, XQItemType.XQBASETYPE_UNTYPED)));
         xqExpression = xqConnection.createExpression(cntxt);
         xqExpression.bindDocument(XQConstants.CONTEXT_ITEM, new FileInputStream("users.xml"), null, null);
	  }
	  else if(xqueryFile.equals("query-external-variable.xq"))
	  {
         xqExpression = xqConnection.createExpression();
         xqExpression.bindDocument(new QName("allUsers"), new FileInputStream("users.xml"), null, null);
	  }
	  else if(xqueryFile.equals("query-directory.xq"))
	  {
         xqExpression = xqConnection.createExpression();
         xqExpression.bindAtomicValue(new QName("folder"), exampleFolder, xqConnection.createAtomicType(XQItemType.XQBASETYPE_STRING));
	  }
	  else
      {
         xqExpression = xqConnection.createExpression();
      }

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
   * Executes an XQuery expression from a file. 
   * The XQuery expression is passed to XQJ as a FileReader. 
   * 
   * @param xqueryFile the filename containing the XQuery expression
   */
  public static void runXQueryPipeline() {
    XQConnection xqConnection = null;
    
    try {
      // Create a datasource
      XQDataSource xqDataSource = new com.ddtek.xquery.xqj.DDXQDataSource();

      // Create connection from the datasource
      xqConnection = xqDataSource.getConnection();

      // Create an XQJ XQuery expression based on the connection
      XQExpression xqExpression1 = xqConnection.createExpression();

      // User feedback - write expression that will be executed to the standard output
      Util.logXQueryFromFile("query-pipeline-1.xq");

	  // Execute XQuery expression from a FileReader.  this generates an XQuery sequence
      XQSequence xqSequence1 = xqExpression1.executeQuery(new FileReader("query-pipeline-1.xq"));

      // Set static context type for second expression
      XQStaticContext cntxt = xqConnection.getStaticContext();
      cntxt.setContextItemStaticType(xqConnection.createDocumentType());
      XQExpression xqExpression2 = xqConnection.createExpression(cntxt);

	  // Bind result of first expression into second expression's default context through StAX
      xqExpression2.bindDocument(XQConstants.CONTEXT_ITEM, xqSequence1.getSequenceAsStream(), null);

      // User feedback - write expression that will be executed to the standard output
      Util.logXQueryFromFile("query-pipeline-2.xq");

      // Execute second XQuery expression from a FileReader.  this generates an XQuery sequence
      XQSequence xqSequence2 = xqExpression2.executeQuery(new FileReader("query-pipeline-2.xq"));

	  // User feedback - write result to the standard output
      String output = xqSequence2.getSequenceAsString(new Properties());
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

  // Filenames of provided example queries
  private static String xqueryInputFiles[] = { 
    "query-initial-context.xq",
    "query-doc-function.xq", 
    "query-external-variable.xq", 
    "query-directory.xq", 
    "query-pipeline-1.xq"};

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
		  if(xqueryInputFiles[choice-1].equals("query-pipeline-1.xq"))
			  runXQueryPipeline();
		  else
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
