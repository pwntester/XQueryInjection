/*
 * Copyright(c) 2003-2009 Progress Software Corporation. All rights reserved. 
 * 
 */
import java.io.File;
import java.io.FileFilter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * DataDirect XQuery custom URI resolver.
 * 
 * Accepts a URI structured as: dir://<path> and
 * returns an XML document listing all XML files in the directory
 * indicated by path.
 */
public class CustomDocumentURIResolverImpl implements URIResolver {
  /**
   * Resolves a custom URI. If the href contains the dir service, it takes over.
   * Otherwise return null and the default URI resolver will try to resolve the 
   * URI.
   * 
   * The returned XML is
   * 
   * <directory>
   *   <file size='...'>...</file>
   *   ...
   * </directory>
   * 
   * In other words, a top level directory element, and for each XML 
   * file found in the directory a file child element with a size attribute 
   * (file size in bytes) and as content the filename of the XML file 
   * in the directory.
   * 
   */
  public Source resolve(String href, String base) throws TransformerException {
    try {
      if (href.startsWith("dir://")) {
        return new DOMSource(createDirectoryDOM(href.substring("dir://".length())));
      }
    }
    catch (ParserConfigurationException pe) {
      throw new TransformerException(pe.getMessage());
    }

    return null;

  }
  
  /**
   * Creates a DOM document containing directory and file information.
   * 
   * @param dirPath path to the directory
   * @return DOM document node with directory and file information.
   * @throws ParserConfigurationException, TransformerException
   */
  private Node createDirectoryDOM(String dirPath) throws TransformerException,
      ParserConfigurationException {
    // Create File from path and check it is a directory
    File directory = new File(dirPath);
    if (!directory.isDirectory()) {
      throw new TransformerException("Path is not a directory");
    }
    //Build list of .xml files in the directory
    File[] files = 
      directory.listFiles(
        new FileFilter() {
          public boolean accept(File file) {
            return file.getName().toLowerCase().endsWith(".xml");
          }
        }
      );
    // Standard DOM API calls to create Document node
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();
    // Create the root element and add it to the document
    Element root = doc.createElement("directory");
    doc.appendChild(root);
    // Iterate through the XML files and add them to the directory element
    for (int i = 0; i < files.length; i++) {
      File file = files[i];
      // Only consider files (no directories)
      if (!file.isFile()) {
        continue;
      }
      // Create file element with size attribute, file name 
      // and add it to the root directory element.
      Element fileElement = doc.createElement("file");
      fileElement.setAttribute("size", String.valueOf(file.length()));
      Text fileName = doc.createTextNode(file.getName());
      fileElement.appendChild(fileName);
      root.appendChild(fileElement);
    }
    return doc;
  }

}