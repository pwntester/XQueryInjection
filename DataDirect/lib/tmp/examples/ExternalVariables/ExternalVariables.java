/*
 * Copyright(c) 2003-2009 Progress Software Corporation. All rights reserved. 
 * 
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQItem;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;
import javax.xml.xquery.XQQueryException;

/**
 * This example demonstrates the use of external variables in XQJ and XQuery.<p>
 * 
 * The following is illustrated
 * <ul>
 * <li>binding an xs:int variable</li>
 * <li>binding an xs:string variable</li>
 * <li>binding a DOM node to an external variable</li>
 * <li>binding an XQItem to an external variable</li>
 * <li>binding an XQSequence to an external variable</li>
 * </ul>
 * 
 * Usage<br>
 * <pre>
 * java ExternalVariables
 * </pre>
 * 
 * <br>If the xquery you want to run contains collection calls, you must specify a
 * valid JDBCURL through a system-setting: <b>JDBCURL</b>.
 */
public class ExternalVariables extends Object {
  
  /**
   * Main method
   */
  public static void main(String[] args) throws IOException {

    // Pick up system setting JDBCURL: e.g. set by setenv and should contain a valid JDBC connection URL.
    String jdbcUrl = System.getProperty("JDBCURL");
    
    XQConnection xqConnection = null;
    try {
      // Create an XQJ datasource
      XQDataSource dataSource = new com.ddtek.xquery.xqj.DDXQDataSource();
      if (jdbcUrl != null && jdbcUrl.length() > 0) {
        // Set the database connection information
        dataSource.setProperty(com.ddtek.xquery.xqj.DDXQDataSource.JDBCURL, jdbcUrl);
      }
      // Set option datasource option to pretty print XML output
      dataSource.setProperty(com.ddtek.xquery.xqj.DDXQDataSource.OPTIONS, "serialize=indent=yes");
      // User feedback - write JDBC URL to standard output.
      Util.logConnectInfo(jdbcUrl);

      // Create connection
      xqConnection = dataSource.getConnection();
      
      // Run Example
      runExample(xqConnection);
    } catch (XQException ex) {
      System.out.print("XQuery execution failed: ");
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

  /*
   * Run example
   */
  private static void runExample(XQConnection xqConnection) throws IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    // Ask user which of the bind methods to run
    while (true) {
      System.out.println();
      System.out.println("Please specify the example to run:");
      System.out.println("1- bind an xs:int external variable");
      System.out.println("2- bind an xs:string external variable");
      System.out.println("3- bind a DOM Node to an external variable");
      System.out.println("4- bind an XQItem to an external variable");
      System.out.println("5- bind an XQSequence to an external variable");
      System.out.println("q- quit this example");
      System.out.println();
      System.out.print("Enter your choice:");
      System.out.flush();
      String userInput = input.readLine();
      System.out.println();
      if (userInput.trim().equals("1")) {
        runExampleInt(xqConnection);
      } else if (userInput.trim().equals("2")) {
        runExampleString(xqConnection);
      } else if (userInput.trim().equals("3")) {
        runExampleDOM(xqConnection);
      } else if (userInput.trim().equals("4")) {
        runExampleXQItem(xqConnection);
      } else if (userInput.trim().equals("5")) {
        runExampleXQSequence(xqConnection);
      } else if (userInput.trim().equals("q")) {
        System.out.println("Exiting example program.");
        return;
      } else {
        System.out.println();
        System.out.println("Invalid choice.");
        System.out.println();
        continue;
      }
      System.out.print("Press enter to continue, q to quit...");
      System.out.flush();
      userInput = input.readLine();
      if (userInput.trim().equals("q")) {
        System.out.println();
        System.out.println("Exiting example program.");
        System.exit(1);
      }
    }
  }
  
  /*
   * Bind external variable as xs:int
   */
  private static void runExampleInt(XQConnection xqConnection) {
    String xquery = 
      "declare variable $var as xs:int external;" + newLine +
      "doc('ducks.xml')//duck[shoesize/length=$var]";
    try {
      // Prepare the xquery
      XQPreparedExpression preparedExpression = xqConnection.prepareExpression(xquery);
      
      // User feedback - write XQuery expression
      Util.logXQuery(xquery);
      
      // Bind variable $var to the integer 6 and execute
      System.out.println();
      System.out.println("Binding:");
      System.out.println("$var: 6");
      System.out.println();
      preparedExpression.bindInt(new QName("var"), 6, xqConnection.createAtomicType(XQItemType.XQBASETYPE_INT));
      
      // Execute the XQuery and log the results
      XQSequence xqSequence = preparedExpression.executeQuery();
      Util.logResult(xqSequence.getSequenceAsString(new Properties()));
      
      // Bind variable $var to the integer 12 and re-execute
      System.out.println();
      System.out.println("Binding:");
      System.out.println("$var: 12");
      System.out.println();
      preparedExpression.bindInt(new QName("var"), 12, xqConnection.createAtomicType(XQItemType.XQBASETYPE_INT));
      
      // Execute the XQuery and log the results
      xqSequence = preparedExpression.executeQuery();
      Util.logResult(xqSequence.getSequenceAsString(new Properties()));
      
      // Close/Free resources
      xqSequence.close();
      preparedExpression.close();
    } catch (XQQueryException ex) {
      System.out.println("XQQueryException: " + ex.getErrorCode().toString());
	  if(ex.getModuleURI() != null)
	      System.out.println("                  " + ex.getModuleURI() + "; line: " + ex.getLineNumber());
      System.out.println(ex.getMessage());
    } catch (XQException ex) {
      System.out.println("XQException: " + ex.getMessage());
    }
  }

  /*
   * Bind external variable using a Java DOM instance
   */
  private static void runExampleDOM(XQConnection xqConnection) {
    String xquery = 
      "declare variable $d as document-node(element(*,xs:untyped)) external;" + newLine +
      "$d//duck[name='Donald']";
    String xmlDataFile = "BindDomToVariable.xml";
    try {
      // Create a direct statement and dump it to the standard output
      XQExpression xqExpression = xqConnection.createExpression();
      Util.logXQuery(xquery);

      // Create a DOM-node
      Node node = createDOMNode(xmlDataFile);
      // Bind the DOM node to the external variable $d and execute
      System.out.println();
      System.out.println("Binding:");
      System.out.println("$d: contents " + xmlDataFile);
      Util.logFile(xmlDataFile);
      System.out.println();
      xqExpression.bindNode(new QName("d"), node, xqConnection.createDocumentType());
      
      // Execute the XQuery and log the results
      XQSequence xqSequence = xqExpression.executeQuery(xquery);
      Util.logResult(xqSequence.getSequenceAsString(new Properties()));
      
      // Close/free resources
      xqSequence.close();
      xqExpression.close();
    } catch (XQQueryException ex) {
      System.out.println("XQQueryException: " + ex.getErrorCode().toString());
	  if(ex.getModuleURI() != null)
	      System.out.println("                  " + ex.getModuleURI() + "; line: " + ex.getLineNumber());
      System.out.println(ex.getMessage());
    } catch (XQException ex) {
      System.out.println("XQException: " + ex.getMessage());
    } catch (FileNotFoundException ex) {
      System.out.println("FileNotFoundException: " + ex.getMessage());
    } catch (ParserConfigurationException ex) {
      System.out.println("ParserConfigurationException: " + ex.getMessage());
    } catch (SAXException ex) {
      System.out.println("SAXException: " + ex.getMessage());
    } catch (IOException ex) {
      System.out.println("IOException: " + ex.getMessage());
    }
  }
  
  /*
   * Create a DOM node from an XML file
   */
  private static Node createDOMNode(String fileName) throws ParserConfigurationException, SAXException, IOException {
    // Standard DOM programming. Get a parser and parse the file
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder parser = factory.newDocumentBuilder();
    Document document = parser.parse(fileName);
    // Return result to caller
    return document;
  }

  /*
   * Bind external xs:string variable
   */
  private static void runExampleString(XQConnection xqConnection) {
    // XQuery getting holdings for a given userid
    String xquery = 
      "declare variable $u as xs:string external;" + newLine +
      "collection('holdings')/*[userid eq $u][shares ge 4000]";
    try {
      // Prepare the XQuery and log to standard output
      XQExpression xqExpression = xqConnection.createExpression();
      Util.logXQuery(xquery);
      
      // Bind variable $u to the value 'Jonathan'
      System.out.println();
      System.out.println("Binding:");
      System.out.println("$u: 'Jonathan'");
      System.out.println();
      xqExpression.bindString(new QName("u"), "Jonathan", xqConnection.createAtomicType(XQItemType.XQBASETYPE_STRING));
      
      // Execute the XQuery and log the results to standard output
      XQSequence xqSequence = xqExpression.executeQuery(xquery);
      Util.logResult(xqSequence.getSequenceAsString(new Properties()));
      
      // Bind variable $u to the value 'Minollo'
      System.out.println();
      System.out.println("Binding:");
      System.out.println("$u: 'Minollo'");
      System.out.println();
      xqExpression.bindString(new QName("u"), "Minollo", xqConnection.createAtomicType(XQItemType.XQBASETYPE_STRING));
      
      // Re-execute the XQuery and log the results to standard output
      xqSequence = xqExpression.executeQuery(xquery);
      Util.logResult(xqSequence.getSequenceAsString(new Properties()));
      
      // Close/Free resources
      xqSequence.close();
      xqExpression.close();
    } catch (XQQueryException ex) {
      System.out.println("XQQueryException: " + ex.getErrorCode().toString());
	  if(ex.getModuleURI() != null)
	      System.out.println("                  " + ex.getModuleURI() + "; line: " + ex.getLineNumber());
      System.out.println(ex.getMessage());
    } catch (XQException ex) {
      System.out.println("XQException: " + ex.getMessage());
    }
  }
  
  /*
   * Bind an XQItem object to an external variable
   * 
   * Note - The following example can (and for performance reasons should) be 
   *        written as a single XQuery expression. It is only written as two dependent
   *        XQuery expression for the sake of this example.
   *  
   */
  private static void runExampleXQItem(XQConnection xqConnection) {
    // XQuery expression retrieving all userid's
    String xquery1 = 
       "for $u in collection('users')/* return fn:data($u/userid)";
    // XQuery retrieving holdings for a given userid
    String xquery2 = 
        "declare variable $u as xs:string external;" + newLine +
        "collection('holdings')/*[userid eq $u][shares ge 4000]";
    try {
      // Prepare the first xquery
      XQPreparedExpression preparedExpression1 = xqConnection.prepareExpression(xquery1);
      Util.logXQuery(xquery1);

      // Prepare the second xquery
      XQPreparedExpression preparedExpression2 = xqConnection.prepareExpression(xquery2);
      Util.logXQuery(xquery2);

      // Execute firstQuery
      XQSequence xqSequence1 = preparedExpression1.executeQuery();
      
      // Loop over results of first XQuery, binding the output as input for the second XQuery
      while (xqSequence1.next()) {
        System.out.println("Getting next item of output from xquery1.");
        XQItem currItem = xqSequence1.getItem();

        // Bind variable $u and execute
        System.out.println();
        System.out.println("Binding output xquery1 as input for xquery2:");
        System.out.print("$u: ");
        System.out.println(currItem.getItemAsString(new Properties()));
        System.out.println();
        preparedExpression2.bindItem(new QName("u"), currItem);

        // Execute the XQuery and log the results
        XQSequence xqSequence2 = preparedExpression2.executeQuery();
        Util.logResult(xqSequence2.getSequenceAsString(new Properties()));
        xqSequence2.close();
      }
      // Close/Free resources
      xqSequence1.close();
      preparedExpression1.close();
      preparedExpression2.close();
    } catch (XQQueryException ex) {
      System.out.println("XQQueryException: " + ex.getErrorCode().toString());
	  if(ex.getModuleURI() != null)
	      System.out.println("                  " + ex.getModuleURI() + "; line: " + ex.getLineNumber());
      System.out.println(ex.getMessage());
    } catch (XQException ex) {
      System.out.println("XQException: " + ex.getMessage());
    }
  }
  
  /*
   * Bind an XQSequence object to an external variable
   * 
   * Note - The following example can (and for performance reasons should) be 
   *        written as a single XQuery expression. It is only written as two dependent
   *        XQuery expression for the sake of this example.
   *  
   */
  private static void runExampleXQSequence(XQConnection xqConnection) {
    // XQuery returning all userid's
    String xquery1 = "for $u in collection('users')/* return fn:data($u/userid)";
    // XQuery getting holdings for a sequence of userid's
    String xquery2 = "declare variable $u as xs:string* external;" + newLine +
      "collection('holdings')/*[userid = $u][shares ge 4000]";

    try {
      // Prepare the first XQuery
      XQPreparedExpression preparedExpression1 = xqConnection.prepareExpression(xquery1);
      Util.logXQuery(xquery1);

      // Prepare the second XQuery
      XQPreparedExpression preparedExpression2 = xqConnection.prepareExpression(xquery2);
      Util.logXQuery(xquery2);

      // Execute first XQuery expression
      System.out.println("Getting output xquery1.");
      XQSequence xqSequence1 = preparedExpression1.executeQuery();

      // Bind variable $u and execute
      System.out.println();
      System.out.println("Binding output xquery1 as input for xquery2.");
      System.out.println();
      preparedExpression2.bindSequence(new QName("u"), xqSequence1);

      // Execute the second XQuery expression and log the results
      XQSequence xqSequence2 = preparedExpression2.executeQuery();
      Util.logResult(xqSequence2.getSequenceAsString(new Properties()));
      xqSequence2.close();

      // Close/Free resources
      xqSequence1.close();
      preparedExpression1.close();
      preparedExpression2.close();
    } catch (XQQueryException ex) {
      System.out.println("XQQueryException: " + ex.getErrorCode().toString());
	  if(ex.getModuleURI() != null)
	      System.out.println("                  " + ex.getModuleURI() + "; line: " + ex.getLineNumber());
      System.out.println(ex.getMessage());
    } catch (XQException ex) {
      System.out.println("XQException: " + ex.getMessage());
    }
  }

  // Newline character. Used to format XQuery expressions. 
  private static String newLine = System.getProperty("line.separator");
}
