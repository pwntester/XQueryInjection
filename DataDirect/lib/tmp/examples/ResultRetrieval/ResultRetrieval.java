/*
 * Copyright(c) 2003-2009 Progress Software Corporation. All rights reserved. 
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;

/**
 * This example demonstrates different ways to process the result of an XQuery expression.
 *
 * <br>The different retrieval methods illustrated are:
 * <ul>
 * <li>DOM Nodes.</li>
 * <li>SAX Events.</li>
 * <li>StAX.</li>
 * </ul>
 * 
 * Usage<br>
 * <pre>
 * java ResultRetrieval [xqueryFile]
 *     runs the query in xqueryFile and launches an interface to process results
 *     the default xqueryFile is xquery.xq
 * </pre>
 * 
 * If the xquery you want to run contains collection-calls, you must specify a
 * valid JDBCURL through a system-setting: <b>JDBCURL</b>.
 */
public class ResultRetrieval extends Object {

  /**
   * Main method.
   */
  public static void main(String[] args) throws IOException {
    // Pick up system setting JDBCURL: e.g. set by setenv and should contain a valid JDBC connection URL.
    String jdbcUrl = System.getProperty("JDBCURL");
    // Input XQuery file either from command line or default (xquery.xq)
    String xquerySourceFile = (args != null && args.length == 1) ? args[0] : "xquery.xq";
    
    XQConnection xqConnection = null;
    try {
      // Create a datasource
      XQDataSource dataSource = new com.ddtek.xquery.xqj.DDXQDataSource();

      if (jdbcUrl != null && jdbcUrl.length() > 0) {
        // Supply the database connection information
        dataSource.setProperty(com.ddtek.xquery.xqj.DDXQDataSource.JDBCURL, jdbcUrl);
      }
      // User feedback - display the connection's jdbcURL
      Util.logConnectInfo(jdbcUrl);

      // Create connection from XQJ datasource
      xqConnection = dataSource.getConnection();
      // User feedback - 
      Util.logXQueryFromFile(xquerySourceFile);
      // Create an XQuery prepared expression from the XQJ connection
      XQPreparedExpression xqPrepExpression = xqConnection.prepareExpression(new FileReader(xquerySourceFile));

      // Execute query and ask user how to process the results
      runRetrievalTest(xqPrepExpression);
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
  
  /**
   * Executes the given prepared XQuery expression and asks the user how to retrieve the results. 
   * 
   * @param xqPrepExpression The previously prepared XQuery expression
   * 
   * @throws IOException
   * @throws XQException
   */
  private static void runRetrievalTest(XQPreparedExpression xqPrepExpression) throws IOException, XQException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      // Print a menu
      System.out.println();
      System.out.println("Please specify the retrieval method:");
      System.out.println("1- fetch as SAX.");
      System.out.println("2- fetch as StAX.");
      System.out.println("3- fetch DOM nodes.");
      System.out.println("q- quit this example");
      System.out.println();
      System.out.print("Enter your choice:");
      System.out.flush();
      // Get users input
      String userInput = input.readLine();
      System.out.println();
      if (userInput.trim().equals("1")) {
        // Execute the prepared XQuery expression 
        XQSequence xqSequence = xqPrepExpression.executeQuery();
        // Retrieve the results as a SAX stream
        xqSequenceAsSAXEvents(xqSequence);
      } else if (userInput.trim().equals("2")) {
        // Execute the prepared XQuery expression 
        XQSequence xqSequence = xqPrepExpression.executeQuery();
        // Retrieve the results as a StAX stream
        xqSequenceAsStAXStream(xqSequence);
      } else if (userInput.trim().equals("3")) {
        // Execute the prepared XQuery expression 
        XQSequence xqSequence = xqPrepExpression.executeQuery();
        // Retrieve the results as a DOM instance
        xqSequenceAsDOMNodes(xqSequence);
      } else if (userInput.trim().equals("q")) {
        System.out.println("Exiting example program.");
        return;
      }
      else {
        System.out.println();
        System.out.println("Invalid choice.");
        System.out.println();
        continue;
      }
      System.out.println();
      System.out.print("Press enter to continue, q to quit...");
      System.out.flush();
      userInput = input.readLine();
      if (userInput.trim().equals("q")) {
        System.out.println("Exiting example program.");
        System.exit(1);
      }
    }
  }
  
  /**
   * Retrieve XQuery result as StAX
   * 
   * @param xqSequence The result sequence 
   * 
   */
  private static void xqSequenceAsStAXStream(XQSequence xqSequence) {
    // Starting
    System.out.print("Start output StAX:");
    // indentLevel is used for output formatting
    int identLevel = 0;
    try {
      // Retrieve result as StAX stream
      XMLStreamReader reader = xqSequence.getSequenceAsStream();
      while(true) {
        // Loop over all StAX events and write event details to output
        int currEvent=reader.getEventType();
        switch (currEvent) {
          case XMLStreamConstants.START_ELEMENT:
            printNewLine(identLevel++);

            System.out.print("START ELEMENT: ");
            System.out.print(reader.getName());
            
            // Process the attributes
            int nrOfAttrs = reader.getAttributeCount();
            if (nrOfAttrs > 0) {
              System.out.print(" ATTRIBUTES:");
            }
            for (int i = 0; i < nrOfAttrs; i++) {
              System.out.print(" ");
              System.out.print(reader.getAttributeName(i));
              System.out.print("=\"");
              System.out.print(reader.getAttributeValue(i));
              System.out.print("\"");
            }
            break;

          case XMLStreamConstants.END_ELEMENT:
            printNewLine(--identLevel);
            System.out.print("END ELEMENT: ");
            System.out.print(reader.getName());
            break;

          case XMLStreamConstants.CHARACTERS:
            printNewLine(identLevel);
            System.out.print("CHARACTERS: ");
            if (reader.getText() != null) {
              System.out.print(reader.getText());
            }
            break;

          case XMLStreamConstants.CDATA:
            printNewLine(identLevel);
            System.out.print("CDATA: ");
            if (reader.getText() != null) {
              System.out.print(reader.getText());
            }
            break;

          case XMLStreamConstants.COMMENT:
            printNewLine(identLevel);
            System.out.print("COMMENT: ");
            if (reader.getText() != null) {
              System.out.print(reader.getText());
            }
            break;

          case XMLStreamConstants.START_DOCUMENT:
            printNewLine(identLevel++);
            System.out.print("START_DOCUMENT");
            break;

          case XMLStreamConstants.END_DOCUMENT:
            printNewLine(--identLevel);
            System.out.print("END_DOCUMENT");
          break;
          
          case XMLStreamConstants.DTD:
          case XMLStreamConstants.ENTITY_REFERENCE:
          case XMLStreamConstants.SPACE:
          case XMLStreamConstants.PROCESSING_INSTRUCTION:
          case XMLStreamConstants.ATTRIBUTE:
          case XMLStreamConstants.NAMESPACE:
          default:
            System.out.println("Ignored event(" + currEvent + ")");
            break;
        }
        // Find out whether there are more StAX events to deal with
        if(reader.hasNext()) {
          reader.next();
        } else {
          break;
        }
      }
    } catch (XQException ex) {
      System.out.println("XQException: " + ex.getMessage());
    } catch (XMLStreamException ex) {
      System.out.println("XMLStreamException: " + ex.getMessage());
    }
    System.out.println();
    System.out.println("End output StAX.");
  }

  /**
   * Retrieve XQuery result as SAX
   * 
   * Creates a SAX ContentHandler and uses it to process the result xqSequence.
   *  
   * @param xqSequence The result sequence 
   */
  private static void xqSequenceAsSAXEvents(XQSequence xqSequence) {
    // Starting
    System.out.print("Output SAX Events:");
    try {
      // Create a new SimpleSAXEventHandler that will process the SAX events
      SimpleSAXEventHandler anEventHandler = new SimpleSAXEventHandler();
      // Write the xqSequence to the SAX event handler
      xqSequence.writeSequenceToSAX(anEventHandler);
    } catch (XQException ex) {
      System.out.println("XQException: " + ex.getMessage());
    }
    System.out.println();
    System.out.println("End output SAX Events.");
  }
  
  /**
   * Retrieve XQuery result as DOM
   * 
   * @param xqSequence The result sequence 
   */
  private static void xqSequenceAsDOMNodes(XQSequence xqSequence) {
    // Starting
    System.out.print("Start Output DOM Nodes:");

    try {
      // Loop over top-level nodes
      while (xqSequence.next()) {
        // Get the current item as a DOM node
        Node domNode = xqSequence.getNode();
        // Display the DOM node
        printDOMNode(domNode, 0);
      }
    } catch (XQException ex) {
      System.out.println("XQException: " + ex.getMessage());
    }
    
    System.out.println();
    System.out.println("End Output DOM Nodes.");
  }

  /**
   * Display a DOM node
   * 
   * @param node the DOM node to display.
   * @param identLevel the current level of indentation.
   */
  private static void printDOMNode(Node node, int identLevel) {
    
    switch (node.getNodeType()) {

      case Node.DOCUMENT_NODE:
        printNewLine(identLevel++);
        System.out.print("DOCUMENT_NODE");
        // Process the child node of this document node
        printDOMNode(((Document) node).getDocumentElement(), identLevel);
        break;
        
      case Node.ELEMENT_NODE:
        printNewLine(identLevel);
        System.out.print("ELEMENT_NODE:");
        System.out.print(node.getNodeName());
        // Process the attributes of this element node
        NamedNodeMap attributeNodeMap = node.getAttributes();
        if (attributeNodeMap.getLength() > 0) {
          System.out.print(" ATTRIBUTES:");
        }
        for (int i = 0; i < attributeNodeMap.getLength(); i++) {
          System.out.print(" " + attributeNodeMap.item(i).getNodeName());
          System.out.print("=\"");
          System.out.print(attributeNodeMap.item(i).getNodeValue());
          System.out.print("\"");
        }
        // Process the children of this element node
        NodeList children = node.getChildNodes();
        if (children != null) {
          int len = children.getLength();
          for (int i = 0; i < len; i++) {
            printDOMNode(children.item(i), identLevel + 1);
          }
        }
        break;
        
      case Node.TEXT_NODE:
        printNewLine(identLevel);
        // Print the contents of this text node
        System.out.print("TEXT_NODE:");
        String nodeValue = node.getNodeValue();
        System.out.print(nodeValue);
        break;
    }
  }

  /* **************************************************************
   * Helpers to pretty-print output
   * **************************************************************/
  private static String SPACES = "                                                                                  ";

  static void printNewLine(int level) {
    System.out.println();
    if (level > 0) {
      int indent = level * 2;
      System.out.print(SPACES.substring(0, SPACES.length() > indent ? indent : SPACES.length()));
    }
  }
}

/**
 * Simple SAX event handler
 * Dumps each event to System.out.
 */
class SimpleSAXEventHandler implements ContentHandler {
  /** The current level of indentation to pretty-print the output. */
  private int identLevel;

  /**
   * Creates an SAX event handler. Initializes the eventHandler.
   */
  public SimpleSAXEventHandler() {
    identLevel = 0;
  }
  
  // Individual SAX event handlers
  public void startDocument() throws SAXException {
    ResultRetrieval.printNewLine(identLevel++);
    System.out.print("START DOCUMENT");
  }
  public void endDocument() throws SAXException {
    ResultRetrieval.printNewLine(--identLevel);
    System.out.print("END DOCUMENT");
  }
  public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
    ResultRetrieval.printNewLine(identLevel++);
    System.out.print("START ELEMENT:");
    System.out.print(localName);
    
    /* Process the attributes */
    if (atts.getLength() > 0) {
      System.out.print(" ATTRIBUTES:");
    }
    for (int i = 0; i < atts.getLength(); i++) {
      System.out.print(" ");
      System.out.print(atts.getLocalName(i));
      System.out.print("=\"");
      System.out.print(atts.getValue(i));
      System.out.print("\"");
    }
  }
  public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    ResultRetrieval.printNewLine(--identLevel);
    System.out.print("END ELEMENT: ");
    System.out.print(localName);
  }
  public void characters(char[] ch, int start, int length) throws SAXException {
    ResultRetrieval.printNewLine(identLevel);
    System.out.print("CHARACTERS: ");
    int runLength = length - start;
    for (int i = start; i < runLength; i++) {
      System.out.print(ch[i]);
    }
  }
  // Ignored SAX events
  public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException { }
  public void endPrefixMapping(String prefix) throws SAXException { }
  public void skippedEntity(String name) throws SAXException { }
  public void setDocumentLocator(Locator locator) { }
  public void processingInstruction(String target, String data) throws SAXException { }
  public void startPrefixMapping(String prefix, String uri) throws SAXException { }
  
}
