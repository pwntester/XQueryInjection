/*
 * Copyright(c) 2003-2009 Progress Software Corporation. All rights reserved. 
 * 
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;
/*
 * Connect example
 * 
 * The Connect example class illustrates advanced ways of establishing an XQJ connection
 * See the runConnect method for details
 *  
 */
public class Connect{

  /*
   * Main method
   */
  public static void main(String[] args) throws IOException {

    System.out.println();
    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    System.out.println("Please check config_basic.xml and config_advanced.xml");
    System.out.println("before running this example.");
    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    System.out.println();
    //Actual example
    runConnect();
  }

  /*
   * runConnect
   * Contains the actual code for this example
   */
  private static void runConnect() throws IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      System.out.println();
      System.out.println("Please specify the example to run:");
      System.out.println("1 - Connect using config file 'config_basic.xml'");
      System.out.println("2 - Connect using config file 'config_advanced.xml'. Alias for 'users' table.");
      System.out.println("3 - Connect using config file 'config_advanced.xml'. Target namespace for 'holdings' table.");
      System.out.println("4 - Connect using config file 'config_advanced.xml'. Hidden columns in 'historical' table.");
      System.out.println("5 - Connect using config file 'config_advanced.xml'. baseUri set");
      System.out.println("6 - Log XQJ calls using XQJ spy");
      System.out.println("q - Quit");
      System.out.println();
      System.out.print("Enter your choice:");
      System.out.flush();
      String userInput = input.readLine();
      System.out.println();
      userInput = userInput.trim();
      if (userInput.equals("1")) {
        connectWithConfigFile("config_basic.xml");
      } else if (userInput.equals("2")) {
        connectWithConfigFileTableAlias("config_advanced.xml");
      } else if (userInput.equals("3")) {
        connectWithConfigFileTableTargetNameSpace("config_advanced.xml");
      } else if (userInput.equals("4")) {
        connectWithConfigFileTableLimitedColumns("config_advanced.xml");
      } else if (userInput.equals("5")) {
        connectWithConfigFileBaseUri("config_advanced.xml");
      } else if (userInput.equals("6")) {
        spyAttributes();
      } else if (userInput.equals("q")) {
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
   * Use a basic configuration file to create an XQJ 
   * datasource and connection.
   */
  private static void connectWithConfigFile(String configFile) {
    XQConnection xqConnection = null;
    try {
      System.out.println("Creating a dataSource based on the config-file " + configFile);
      System.out.println();
      
      // Create a datasource using a config-file
      FileInputStream configFileStr = new FileInputStream(configFile);
      XQDataSource dataSource = new com.ddtek.xquery.xqj.DDXQDataSource(configFileStr);
      xqConnection = dataSource.getConnection();
      
      // XQuery - select the first element of table 'users', 'holdings' and 'historical'
      String xquery = 
        "declare option ddtek:serialize 'indent=yes';" + newLine + 
        "  fn:collection('users')/*[1]" + newLine + 
        ", fn:collection('holdings')/*[1]" + newLine + 
        ", fn:collection('historical')/*[1]";
      
      // User feedback - write expression to standard output
      Util.logXQuery(xquery);
      
      // Prepare the expression
      XQPreparedExpression preparedExpression = xqConnection.prepareExpression(xquery);
      
      // Execute prepared expression and write XQuery output to standard output
      Util.logResult(preparedExpression.executeQuery().getSequenceAsString(new Properties()));
    } catch (FileNotFoundException ex) {
      System.out.print("XQuery execution failed: config file not found: ");
      System.out.println("FileNotFoundException: " + ex.getMessage());
    } catch (XQException ex) {
      System.out.print("XQuery execution failed: ");
      System.out.println("XQException: " + ex.getMessage());
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
   * Illustrates the use of a table alias in the source configuration file
   */
  private static void connectWithConfigFileTableAlias(String configFile) {
   
    XQConnection xqConnection = null;
    try {
      System.out.println("Creating a dataSource based on the config-file " + configFile);
      System.out.println();
      
      // Create a datasource using a config-file
      FileInputStream configFileStr = new FileInputStream(configFile);
      XQDataSource dataSource = new com.ddtek.xquery.xqj.DDXQDataSource(configFileStr);
      xqConnection = dataSource.getConnection();
      
      // First attempt - use the original table name. This will fail.
      XQExpression xqExpression = xqConnection.createExpression();
      String xquery1 = "fn:collection('users')/*[1]";
      try {
        Util.logXQuery(xquery1);
        xqExpression.executeQuery(xquery1);
      } catch (XQException ex) {
        System.out.println("Expected exception:");
        System.out.println("XQException: " + ex.getMessage());
        System.out.println();
      }
      
      // Second attempt - use the alias name. This will succeed.
      String xquery2 = "fn:collection('MY_USERS_TABLE')/*[1]";
      Util.logXQuery(xquery2);
      Util.logResult(xqExpression.executeQuery(xquery2).getSequenceAsString(new Properties()));
    } catch (FileNotFoundException ex) {
      System.out.print("XQuery execution failed: config file not found: ");
      System.out.println(ex.getMessage());
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
   * Illustrates the use of a target namespace for a table
   */
  private static void connectWithConfigFileTableTargetNameSpace(String configFile) {

    XQConnection xqConnection = null;
    try {
      System.out.println("Creating a dataSource based on the config-file " + configFile);
      System.out.println();
      
      // Create a datasource and associated connection using a config file
      FileInputStream configFileStr = new FileInputStream(configFile);
      XQDataSource dataSource = new com.ddtek.xquery.xqj.DDXQDataSource(configFileStr);
      xqConnection = dataSource.getConnection();
      
      // Create an XQJ expression
      XQExpression xqExpression = xqConnection.createExpression();
      
      // Retrieve the first element from the table 'holdings'
      String xquery = "fn:collection('holdings')/*[1]";
      Util.logXQuery(xquery);
      Util.logResult(xqExpression.executeQuery(xquery).getSequenceAsString(new Properties()));
      
      // Try a path expression on the first element from the table 'holdings'
      // This will fail because the query does not use the correct namespace
      String xquery2 = 
        "for $h in fn:collection('holdings')/holdings[1]" + newLine + 
        "return $h/stockticker";
      try {
        Util.logXQuery(xquery2);
        XQSequence xqSquence = xqExpression.executeQuery(xquery2);
        Util.logResult(xqSquence.getSequenceAsString(new Properties()));
      } catch (XQException ex) {
        System.out.println("Expected exception:");
        System.out.println("XQException: " + ex.getMessage());
        System.out.println();
      }
      
      // Try a path expression on the first element from the table 'holdings'
      // Now use the correct namespace
      String xquery3 = 
        "declare namespace ddtekex = 'http://www.datadirect.com/xquery/examples';" + newLine + 
        "for $h in fn:collection('holdings')/ddtekex:holdings[1] " + newLine +
        "return $h/ddtekex:stockticker";
      Util.logXQuery(xquery3);
      Util.logResult(xqExpression.executeQuery(xquery3).getSequenceAsString(new Properties()));
    } catch (FileNotFoundException ex) {
      System.out.print("XQuery execution failed: config file not found: ");
      System.out.println(ex.getMessage());
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
   * Illustrates the use of a source configuration file to restrict the table columns 
   * that are visible from within an XQuery expression.
   */
  private static void connectWithConfigFileTableLimitedColumns(String configFile) {

    XQConnection xqConnection = null;
    try {
      System.out.println("Creating a dataSource based on the config-file " + configFile);
      System.out.println();
      
      // Create a datasource and connection using a config file
      FileInputStream configFileStr = new FileInputStream(configFile);
      XQDataSource dataSource = new com.ddtek.xquery.xqj.DDXQDataSource(configFileStr);
      xqConnection = dataSource.getConnection();
      
      // Create an XQJ expression
      XQExpression xqExpression = xqConnection.createExpression();
      
      // Retrieve the first element from the table 'historical'
      String xquery = "fn:collection('historical')/*[1]";
      Util.logXQuery(xquery);
      Util.logResult(xqExpression.executeQuery(xquery).getSequenceAsString(new Properties()));
      
      // Try a path expression on the the first element from the table 'historical':
      // referring a column that is not in the includes in the configuration file.
      // This will fail.
      String xquery2 = 
        "for $h in fn:collection('historical')/*[1]" + newLine + 
        "where $h/adjustedclose ge 20" + newLine + 
        "return $h";
      try {
        Util.logXQuery(xquery2);
        XQSequence xqSquence = xqExpression.executeQuery(xquery2);
        Util.logResult(xqSquence.getSequenceAsString(new Properties()));
      } catch (XQException ex) {
        System.out.println("Expected exception:");
        System.out.println("XQException: " + ex.getMessage());
        System.out.println();
      }
    } catch (FileNotFoundException ex) {
      System.out.print("XQuery execution failed: config file not found: ");
      System.out.println(ex.getMessage());
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
   * Example of using the DDXQ XQJ Spy logger
   */
  private static void spyAttributes() {
    // Pick up system setting JDBCURL: e.g. set by setenv and should contain a valid JDBC connection URL.
    String jdbcUrl = System.getProperty("JDBCURL");
    XQConnection xqConnection = null;
    try {
      // Create a datasource
      XQDataSource dataSource = new com.ddtek.xquery.xqj.DDXQDataSource();
      if (jdbcUrl != null && jdbcUrl.length() > 0) {
        // Supply the database connection information
        dataSource.setProperty(com.ddtek.xquery.xqj.DDXQDataSource.JDBCURL, jdbcUrl);
      }
      
      // Set option: log xqj calls to local file log.txt
      System.out.println("Logging xqj calls to local file log.txt");
      System.out.println();
      dataSource.setProperty(com.ddtek.xquery.xqj.DDXQDataSource.SPYATTRIBUTES, "log=(file)log.txt");
      
      // User feed-back. Write JDBC URL to standard output.
      Util.logConnectInfo(jdbcUrl);
      
      // Create XQJ connection.
      xqConnection = dataSource.getConnection();
      
      // Execute XQuery, retrieving all userid's
      String xquery = "for $u in collection('users')/* return $u/userid";
      XQPreparedExpression preparedExpression = xqConnection.prepareExpression(xquery);
      
      // User feed-back - Write XQuery expression to standard output.
      Util.logXQuery(xquery);
      
      // Execute XQuery expression and write result to standard output.
      Util.logResult(preparedExpression.executeQuery().getSequenceAsString(new Properties()));
      
      // User feed-back - inform user XQJ calls where logged.
      System.out.println("XQJ calls were logged to local file log.txt");
      System.out.println();
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
   * Illustrates the use of the baseURI element in the configuration file.
   */
  private static void connectWithConfigFileBaseUri(String configFile) {

    XQConnection xqConnection = null;
    try {
      System.out.println("Creating a dataSource based on the config-file " + configFile);
      System.out.println();
      
      // Create a datasource and connection using a config file
      FileInputStream configFileStr = new FileInputStream(configFile);
      XQDataSource dataSource = new com.ddtek.xquery.xqj.DDXQDataSource(configFileStr);
      xqConnection = dataSource.getConnection();
      
      // Create an XQJ expression
      XQExpression xqExpression = xqConnection.createExpression();
      
      // Retrieve the first element of 'users.xml'
      // The file 'users.xml' should be found because "config_advanced contains a baseURI setting.
      String xquery = "fn:doc('users.xml')";
      Util.logXQuery(xquery);
      Util.logResult(xqExpression.executeQuery(xquery).getSequenceAsString(new Properties()));
    } catch (FileNotFoundException ex) {
      System.out.print("XQuery execution failed: config file not found: ");
      System.out.println(ex.getMessage());
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
  // Platform dependent newline character.
  private static String newLine = System.getProperty("line.separator");
}
