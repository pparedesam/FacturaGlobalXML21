package wsHomologador;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class v21codigoQR {
	
	
	public static void get(String xml_file_name, String qr_file_name, String qr_value) {
	//	String raya="------------------------------------------------";
		  try {

			  
			  
			  File fXmlFile = new File(xml_file_name);
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(fXmlFile);

			  doc.getDocumentElement().normalize();
			  
			  
		//	  System.out.println(raya);
			  
			  // DigestValue
			  NodeList nList_IssueDate = doc.getElementsByTagName("ds:DigestValue");
			  Node nNode_IssueDate = nList_IssueDate.item(0);
			  System.out.println("" + nNode_IssueDate.getNodeName());
			  System.out.println("Generando Codigo QR.");
			  		  
	 		String filePath = qr_file_name;
	  		int size = 500;
	  		String fileType = "png";
	  		File qrFile = new File(filePath);
	  		String _contenido_qr=qr_value+"|"+nNode_IssueDate.getTextContent();
	  	//	String _contenido_qr=qr_value;
	  		createQRImage(qrFile, _contenido_qr, size, fileType);
	 // 		System.out.println("QR CODE WAS GENERATE. "+qr_file_name);

			 
			
			  
			  
		  } catch (Exception e) {
		  		e.printStackTrace();
	    	}
	}

	
	
	private static void createQRImage(File qrFile, String qrCodeText, int size,
			String fileType) throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable hintMap = new Hashtable();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,
				BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
				BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
	}	

	
	
}
