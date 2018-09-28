package wsHomologador;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;





public class ReadXMLFile {
	
		public static String $PATH_FIRMADOS="";
		public static String $PATH_RESPUESTA="";
		public static String $FILE_PATH_NAME_XML="";
		public static String $FILE_NAME="";
		public static String $FILE_NAME_TO_READ="";
	  
	  
		public static void get_respuesta(String xml_file_name) {
			
		  String raya="------------------------------------------------";
		  try {

			  
			  
			  File fXmlFile = new File(xml_file_name);
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(fXmlFile);

			  //optional, but recommended
			  //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			  doc.getDocumentElement().normalize();
			  
			  
			  
			  
			  
			  
			  
			// cbc:ID	
			  NodeList nList_id = doc.getElementsByTagName("cbc:ID");
			  Node nNode_id = nList_id.item(0);
			  System.out.println("" + nNode_id.getNodeName());
			  System.out.println("Numero de Ticket:   " + nNode_id.getTextContent());
			  System.out.println(raya);
			  
			  // cbc:IssueDate
			  NodeList nList_IssueDate = doc.getElementsByTagName("cbc:IssueDate");
			  Node nNode_IssueDate = nList_IssueDate.item(0);
			  System.out.println("" + nNode_IssueDate.getNodeName());
			  System.out.println("Fecha de Envio:    " + nNode_IssueDate.getTextContent());
			  		  
			  //cbc:IssueTime
			  NodeList nList_IssueTime = doc.getElementsByTagName("cbc:IssueTime");
			  Node nNode_IssueTime = nList_IssueTime.item(0);
			  //System.out.println("" + nNode_IssueTime.getNodeName());
			  System.out.println("Hora de Envio:     " + nNode_IssueTime.getTextContent());
			  
			  
			  
			  // cbc:ResponseDate
			  NodeList nList_ResponseDate = doc.getElementsByTagName("cbc:ResponseDate");
			  Node nNode_ResponseDate = nList_ResponseDate.item(0);
			  System.out.println("" + nNode_ResponseDate.getNodeName());
			  System.out.println("Fecha de Recepcion:" + nNode_ResponseDate.getTextContent());
			  		  
			  //cbc:ResponseTime
			  NodeList nList_ResponseTime = doc.getElementsByTagName("cbc:ResponseTime");
			  Node nNode_ResponseTime = nList_ResponseTime.item(0);
			  //System.out.println("" + nNode_IssueTime.getNodeName());
			  System.out.println("Hora de Recepcion: " + nNode_ResponseTime.getTextContent());
			  
			  
			  System.out.println(raya);

			  
			  
			  System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			  
			  
			  
			  
			  
			  NodeList nList = doc.getElementsByTagName("cac:DocumentResponse");
			  
			  
			 
			  
			  System.out.println(raya);

			  for (int temp_Signature = 0; temp_Signature < nList.getLength(); temp_Signature++) {

				  Node nNode = nList.item(temp_Signature);
				  System.out.println("Nodo de Firma:        " + nNode.getNodeName());

				  
				  
				  if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					  Element eElement = (Element) nNode;
					  //cbc:ReferenceID		  
					  System.out.println("Id de Referencia:     " + eElement.getElementsByTagName("cbc:ReferenceID").item(0).getTextContent());
					  
					  //cbc:ResponseCode
					  System.out.println("Codigo de Respuesta:  " + eElement.getElementsByTagName("cbc:ResponseCode").item(0).getTextContent());
					  
					  //cbc:Description
					  System.out.println("Descripcion:          " + eElement.getElementsByTagName("cbc:Description").item(0).getTextContent());
					  
					  
					  
		//			  NodeList SignatoryParty = eElement.getElementsByTagName("cac:SignatoryParty");
					 // 		NodeList PartyIdentification = eElement.getElementsByTagName("cac:PartyIdentification");	
					  
				  }
			  }
		  	} catch (Exception e) {
		  		e.printStackTrace();
	    	}
	  }

}
	
	

