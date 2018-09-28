package wsHomologador;

import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class codigoHash {
	
	
	public static void get(String xml_file_name, String hash_file_name) {
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
			//  NodeList nList_id = doc.getElementsByTagName("ext:UBLExtensions");
			//  Node nNode_id = nList_id.item(0);
		//	  System.out.println("" + nNode_id.getNodeName());
		//	  System.out.println("Numero de Ticket:   " + nNode_id.getTextContent());
			  System.out.println(raya);
			  
			  // DigestValue
			  NodeList nList_IssueDate = doc.getElementsByTagName("DigestValue");
			  Node nNode_IssueDate = nList_IssueDate.item(0);
			  System.out.println("" + nNode_IssueDate.getNodeName());
			  System.out.println("Codigo Hash:    " + nNode_IssueDate.getTextContent());
			  		  
		
			  File archivo_hash=new File(hash_file_name);
			  archivo_hash.delete();
			  FileWriter chanel_write=new FileWriter(archivo_hash,true);
			  chanel_write.write(nNode_IssueDate.getTextContent());
			  chanel_write.close();
			  
			  
		  } catch (Exception e) {
		  		e.printStackTrace();
	    	}
	}

}
