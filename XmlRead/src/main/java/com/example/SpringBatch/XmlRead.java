package com.example.SpringBatch;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;  
import org.w3c.dom.Element;  
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlRead {

	
	
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		
		
		File file = new File("C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\XmlRead\\src\\main\\resources\\Invoice.xml"); 
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();  
		
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.parse(file);
		
		
		document.getDocumentElement().normalize(); 
		
		System.out.println("Root element: " + document.getDocumentElement().getNodeName()); 
		
		NodeList nodeList = document.getElementsByTagName("Invoice");  
		
		
		
		
		for (int itr = 0; itr < nodeList.getLength(); itr++)   
		{  
		Node node = nodeList.item(itr);  
		System.out.println("\nNode Name :" + node.getNodeName());  
		if (node.getNodeType() == Node.ELEMENT_NODE)   
		{  
		Element eElement = (Element) node;  
		System.out.println("invoice_No: "+ eElement.getElementsByTagName("invoice_No").item(0).getTextContent());  
		System.out.println("product_No: "+ eElement.getElementsByTagName("product_No").item(0).getTextContent());  
		System.out.println("amount: "+ eElement.getElementsByTagName("amount").item(0).getTextContent());  
		System.out.println("seller_Id: "+ eElement.getElementsByTagName("seller_Id").item(0).getTextContent());  
		System.out.println("order_Date: "+ eElement.getElementsByTagName("order_Date").item(0).getTextContent());  
		}  
		}  
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
