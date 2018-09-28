package wsHomologador;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;


import org.w3c.dom.*;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;


/**
 * Firma un documento XML
 * @author Carlos García. Autentia.
 * @see http://www.mobiletest.es
 */
public class CreateSignature   {

	private static String KEYSTORE_TYPE		  = "JKS";
	private static String KEYSTORE_FILE		  = "myKeyStore.jks";
	private static String KEYSTORE_PASSWORD	  = "abc12345";
	private static String PRIVATE_KEY_PASSWORD  = "abc1234";
	private static String PRIVATE_KEY_ALIAS	  = "mi_cert_ejemplo";
	private static String XML_ORIGEN	  = "";
	private static String XML_DESTINO	  = "";


	/**
	 * Punto de entrada al ejemplo
	 */
	public static void main(String _keystore_file, 
						    String _keystore_password, 
						    String _private_key_password, 
						    String _private_key_alias, 
						    String _xml_origen, 
						    String _xml_destino,
						    String _file_name) throws Exception {
		
		KEYSTORE_FILE=_keystore_file;
		KEYSTORE_PASSWORD=_keystore_password;
		PRIVATE_KEY_PASSWORD=_private_key_password;
		PRIVATE_KEY_ALIAS=_private_key_alias;
		XML_ORIGEN=_xml_origen+_file_name+".xml";
		XML_DESTINO=_xml_destino+_file_name+".xml";;
		
		System.out.println("--------------------------------------------------");
		System.out.println("KEYSTORE_FILE:"+KEYSTORE_FILE);
		System.out.println("KEYSTORE_PASSWORD:"+KEYSTORE_PASSWORD);
		System.out.println("PRIVATE_KEY_PASSWORD:"+PRIVATE_KEY_PASSWORD);
		System.out.println("PRIVATE_KEY_ALIAS:"+PRIVATE_KEY_ALIAS);
		System.out.println("XML_ORIGEN:"+XML_ORIGEN);
		System.out.println("XML_DESTINO:"+XML_DESTINO);
		System.out.println("--------------------------------------------------");
		
		
		
		
		org.apache.xml.security.Init.init();

		Document doc = DOMUtils.createSampleDocument();
		
		Constants.setSignatureSpecNSprefix("");	// Sino, pone por defecto como prefijo: "ns"


		// Cargamos el almacen de claves
		KeyStore ks  = KeyStore.getInstance(KEYSTORE_TYPE);
		ks.load(new FileInputStream(KEYSTORE_FILE), KEYSTORE_PASSWORD.toCharArray());

		// Obtenemos la clave privada, pues la necesitaremos para encriptar.
		PrivateKey privateKey = (PrivateKey) ks.getKey(PRIVATE_KEY_ALIAS, PRIVATE_KEY_PASSWORD.toCharArray());
		File	signatureFile = new File(XML_ORIGEN);
		File	signatureFile_oit = new File(XML_DESTINO);
		String	baseURI       = signatureFile.toURL().toString();	// BaseURI para las URL Relativas.
		
		// Instanciamos un objeto XMLSignature desde el Document. El algoritmo de firma será DSA
		XMLSignature xmlSignature = new XMLSignature(doc, baseURI, XMLSignature.ALGO_ID_SIGNATURE_DSA);

		 
		// Añadimos el nodo de la firma a la raiz antes de firmar.
		// Observe que ambos elementos pueden ser mezclados en una forma con referencias separadas
		doc.getDocumentElement().appendChild(xmlSignature.getElement());

		// Creamos el objeto que mapea: Document/Reference
		Transforms transforms = new Transforms(doc);
		transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
		
		// Añadimos lo anterior Documento / Referencia
		// ALGO_ID_DIGEST_SHA1 = "http://www.w3.org/2000/09/xmldsig#sha1";
		xmlSignature.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);

		// Añadimos el KeyInfo del certificado cuya clave privada usamos
		X509Certificate cert = (X509Certificate) ks.getCertificate(PRIVATE_KEY_ALIAS);
		xmlSignature.addKeyInfo(cert);
		xmlSignature.addKeyInfo(cert.getPublicKey());
		
		
		// Realizamos la firma
		xmlSignature.sign(privateKey);
		
		// Guardamos archivo de firma en disco
		DOMUtils.outputDocToFile(doc, signatureFile_oit);
	}
}