/*
 * Copyright(c) 2003-2009 Progress Software Corporation. All rights reserved.
 * 
 * A few utility methods for the DataDirect XQuery example programs. 
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;

public class Util {
  
  static void logConnectInfo(String jdbcUrl) {
    System.out.println("Connecting:");
    System.out.println((jdbcUrl != null) ? "JdbcUrl:" + jdbcUrl : "No database specified: only doc()'s supported.");
    System.out.println();
  }

  static void logXQuery(String xquery) {
    System.out.println("XQuery:");
    System.out.println(xquery);
    System.out.println();
  }
  
  static void logXQueryFromFile(String xqueryFileName) {
    System.out.println("XQuery: file " + xqueryFileName);
    logFile(xqueryFileName);
    System.out.println();
  }
  
  static void logResult(String result) {
    System.out.println("Output:");
    System.out.println(result);
    System.out.println();
  }
  
  static void logFile(String fileName) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      String newLine;
      while ((newLine = reader.readLine()) != null) {
        System.out.println(newLine);
      }
    }
    catch (Exception ex) {
      System.out.print("Error dumping file ");
      System.out.print(fileName);
      System.out.print(": ");
      System.out.print(ex.getMessage());
    }
    System.out.println();
  }  
}