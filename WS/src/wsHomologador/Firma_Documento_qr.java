package wsHomologador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;





import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.*;
import javax.xml.crypto.dsig.keyinfo.*;
import javax.xml.crypto.dsig.spec.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;



public class Firma_Documento_qr {
	
	// RUTAS Y ARCHIVOS
		public static String $PATH_ARCHIVOS_PLANOS="";
		public static String $PATH_SIN_FIRMA="";
		public static String $PATH_CON_FIRMA="";
		public static String $FILE_NAME_XML="";
		public static String $PATH_RESPUESTAS="";
		public static String $PATH_PDFS="";
		public static String $PATH_TICKETS="";
		public static String $PATH_RESPUESTAS_STATUS="";
		public static String $PATH_CERTIFICADOS="";
		
		// DATOS DEL EMISOR
		public static String $RUC="";
		public static String $RAZON_SOCIAL="";
		public static String $CODIGO_POSTAL="";
		public static String $DIRECCION="";
		public static String $CIUDAD="";
		public static String $PAIS="";
		
		// DATOS DE LA LLAVE
		public static String $KEYSTORE="";
		public static String $PASSWORD_KEYSTORE="";
		public static String $PASSWORD_CERTIFICADO="";
		public static String $ALIAS_CERTIFICADO="";
		

	public static void firmar(String $PATH_XMLS_SIN_FIRMA, String $PATH_XMLS_CON_FIRMA, String $FILE_NAME, parametros misParametros) {
		
		
		$PATH_ARCHIVOS_PLANOS=misParametros.get_ruta_base();
		$PATH_SIN_FIRMA=misParametros.get_ruta_xml_sin_firma();
		$PATH_CON_FIRMA=misParametros.get_ruta_xml_con_firma();
		$PATH_RESPUESTAS=misParametros.get_ruta_respuestas_status();
		$PATH_PDFS=misParametros.get_ruta_pdfs();
		$PATH_TICKETS=misParametros.get_ruta_tickets();
		$PATH_RESPUESTAS_STATUS=misParametros.get_ruta_respuestas_status();
		$PATH_CERTIFICADOS=misParametros.get_ruta_certificados();
		
		$RUC=misParametros.get_ruc();
		$RAZON_SOCIAL=misParametros.get_razon_social();
		$CODIGO_POSTAL=misParametros.get_codigo_postal();
		$DIRECCION=misParametros.get_direccion();
		$CIUDAD=misParametros.get_ciudad();
		$PAIS=misParametros.get_pais();
		
		$KEYSTORE=misParametros.get_keystore();
		$PASSWORD_KEYSTORE=misParametros.get_password_keystore();
		$PASSWORD_CERTIFICADO=misParametros.get_password_certificado();
		$ALIAS_CERTIFICADO=misParametros.get_alias_certificado();
		
		
		try {
				// Create a DOM XMLSignatureFactory that will be used to
				// generate the enveloped signature.
				XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

				// Create a Reference to the enveloped document (in this case,
				// you are signing the whole document, so a URI of "" signifies
				// that, and also specify the SHA1 digest algorithm and
				// the ENVELOPED Transform.
				Reference ref;
				
					ref = fac.newReference
					("", fac.newDigestMethod(DigestMethod.SHA1, null),
					 Collections.singletonList
					  (fac.newTransform
					   (Transform.ENVELOPED, (TransformParameterSpec) null)),
					    null, null);

				// Create the SignedInfo.
				SignedInfo si = fac.newSignedInfo
				(fac.newCanonicalizationMethod
				 (CanonicalizationMethod.INCLUSIVE,
				  (C14NMethodParameterSpec) null),
				   fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
				    Collections.singletonList(ref));

				
				
				// Load the KeyStore and get the signing key and certificate.
				KeyStore ks = KeyStore.getInstance("jks");
				char[] pass=$PASSWORD_KEYSTORE.toCharArray();
				
				// SERVICIOS EXCELENTES
				ks.load(new FileInputStream($KEYSTORE),pass);
				
				// GEANCARLO
			//	KeyStore.PrivateKeyEntry keyEntry =
			//	   (KeyStore.PrivateKeyEntry) ks.getEntry
			//	          ("1", new KeyStore.PasswordProtection("EXRUA86ABX7W".toCharArray()));
				
				// SERVICIOS EXCELENTES
				KeyStore.PrivateKeyEntry keyEntry =
						   (KeyStore.PrivateKeyEntry) ks.getEntry
								    ($ALIAS_CERTIFICADO, new KeyStore.PasswordProtection($PASSWORD_CERTIFICADO.toCharArray()));
				
				X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

				// Create the KeyInfo containing the X509Data.
			    KeyInfoFactory kif = fac.getKeyInfoFactory();
			  	List x509Content = new ArrayList();
			 	x509Content.add(cert.getSubjectX500Principal().getName());
				x509Content.add(cert);
				X509Data xd = kif.newX509Data(x509Content);
				KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));
				

				// Instantiate the document to be signed.
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setNamespaceAware(true);
				Document doc = dbf.newDocumentBuilder().parse
				   (new FileInputStream($PATH_XMLS_SIN_FIRMA+$FILE_NAME+".xml"));

				
				
				Node content = doc.getElementsByTagName("ext:UBLExtensions").item(0);
				
				/// ext:UBLExtension
			    Element UBLExtension = doc.createElement("ext:UBLExtension");
			    content.appendChild(UBLExtension);	
				
				/// NODO ext:ExtensionContent
				Element ExtensionContent = doc.createElement("ext:ExtensionContent");
				UBLExtension.appendChild(ExtensionContent);
				
				
				// linea antigua		
				//	DOMSignContext dsc = new 
				//			DOMSignContext(keyEntry.getPrivateKey( ),doc.getDocumentElement());
				 
				DOMSignContext dsc = new 
				    	DOMSignContext(keyEntry.getPrivateKey( ),ExtensionContent);
				
		
				
				
				// Create the XMLSignature, but don't sign it yet.
			//	XMLSignature signature = fac.newXMLSignature(si, ki);
				XMLSignature signature = fac.newXMLSignature(si, ki,null,$RUC,null); 
				
				//Node content2 = doc.getElementsByTagName("Signature").item(0);
				
				
				
				// Marshal, generate, and sign the enveloped signature.
				signature.sign(dsc);


				// Output the resulting document.
				OutputStream os = new FileOutputStream($PATH_XMLS_CON_FIRMA+$FILE_NAME+".xml");
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer trans = tf.newTransformer();
				trans.transform(new DOMSource(doc), new StreamResult(os));
				
		//		comprimirXML.comprimir($PATH_XMLS_CON_FIRMA+$FILE_NAME+".zip",$PATH_XMLS_CON_FIRMA+$FILE_NAME+".xml",$FILE_NAME+".xml");
				
				System.out.println("El Archivo "+$FILE_NAME+" fue creado.");
				
				
				
				}catch (Exception e){
					e.printStackTrace();
				}

	}

}