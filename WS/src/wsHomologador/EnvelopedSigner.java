package wsHomologador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class EnvelopedSigner {
	//private static String docToSign = "message.xml";
	//private static String outputFileName = "signedmessage.xml";

	
	// RUTAS Y ARCHIVOS
			public static String $PATH_ARCHIVOS_PLANOS="";
			public static String $PATH_SIN_FIRMA="c:\\temp\\20366410610-01-FF50-00000001.xml";
			public static String $PATH_CON_FIRMA="c:\\temp\\firmado-20366410610-01-FF50-00000001.xml";
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
			public static String $KEYSTORE="C:\\TEMP\\ServiciosExcelentes.jks";
			public static String $PASSWORD_KEYSTORE="pascola07";
			public static String $PASSWORD_CERTIFICADO="8msZHbdnz9R98DKF";
			public static String $ALIAS_CERTIFICADO="(racer_pfvp_365_pe_sw_kpsc)_20366410610_05271164";
		
	
//	public static void main(String[] args) {
		
		public static void  main(String[] args) {
				//String $PATH_XMLS_SIN_FIRMA, String $PATH_XMLS_CON_FIRMA, String $FILE_NAME, parametros misParametros) {
					
			
			
	//		$PATH_ARCHIVOS_PLANOS=misParametros.get_ruta_base();
	//		$PATH_SIN_FIRMA=misParametros.get_ruta_xml_sin_firma();
	//		$PATH_CON_FIRMA=misParametros.get_ruta_xml_con_firma();
	//		$PATH_RESPUESTAS=misParametros.get_ruta_respuestas_status();
	//		$PATH_PDFS=misParametros.get_ruta_pdfs();
	//		$PATH_TICKETS=misParametros.get_ruta_tickets();
	//		$PATH_RESPUESTAS_STATUS=misParametros.get_ruta_respuestas_status();
	//		$PATH_CERTIFICADOS=misParametros.get_ruta_certificados();
			
	//		$RUC=misParametros.get_ruc();
	//		$RAZON_SOCIAL=misParametros.get_razon_social();
	//		$CODIGO_POSTAL=misParametros.get_codigo_postal();
	//		$DIRECCION=misParametros.get_direccion();
	//		$CIUDAD=misParametros.get_ciudad();
	//		$PAIS=misParametros.get_pais();
			
	//		$KEYSTORE=misParametros.get_keystore();
	//		$PASSWORD_KEYSTORE=misParametros.get_password_keystore();
	//		$PASSWORD_CERTIFICADO=misParametros.get_password_certificado();
	//		$ALIAS_CERTIFICADO=misParametros.get_alias_certificado();
			
			
		try{
			
		//	System.out.println("Path del xml sin Firma: "+$PATH_XMLS_SIN_FIRMA+$FILE_NAME+".xml");
			System.out.println("Path del xml sin Firma: "+$PATH_SIN_FIRMA);
	//		System.out.println("Path del xml con Firma: "+$PATH_XMLS_CON_FIRMA+$FILE_NAME+".xml");
			System.out.println("Path del xml con Firma: "+$PATH_CON_FIRMA);
			System.out.println("Key Store: "+$KEYSTORE);
			System.out.println("Password Key Store: "+$PASSWORD_KEYSTORE);
			System.out.println("Alias Key Store: "+$ALIAS_CERTIFICADO);
			
			
			//load the XML doc to sign
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document xmlDoc = docBuilder.parse(new FileInputStream($PATH_SIN_FIRMA));
			
			//Load the keystore containing the keys
			KeyStore keystore = KeyStore.getInstance("jks");
			char[] password = $PASSWORD_KEYSTORE.toCharArray();
			keystore.load(new FileInputStream($KEYSTORE), password);
			
			String _llave = keystore.toString();
			System.out.println("llave: "+_llave);
			
			Enumeration<String> _alias = keystore.aliases();
			System.out.println("alias: "+_alias);
			
			 Key key = keystore.getKey($ALIAS_CERTIFICADO, password);
			 
	//		KeyPair key = getKeyPair(keystore, $ALIAS_CERTIFICADO, password);
			
			
			
	//		String _llave = key.getc().toString(); //    getPrivate().toString();
			
	
	//		System.out.println("llave: "+_llave);
			
			
			
			
			//Create the signing context
	//		DOMSignContext dsc = new DOMSignContext
	//		  (key.getPrivate(), xmlDoc.getDocumentElement());
			
			//Manufacture a signer object
			XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
			
			//Specify the Reference attributes
			Reference ref = fac.newReference
			  ("", fac.newDigestMethod(DigestMethod.SHA256, null),
			    Collections.singletonList
			      (fac.newTransform(Transform.ENVELOPED,
			        (TransformParameterSpec) null)), null, null);
			
			//Specify the SignedInfo attributes
			SignedInfo si = fac.newSignedInfo
			  (fac.newCanonicalizationMethod
			    (CanonicalizationMethod.INCLUSIVE,
			      (C14NMethodParameterSpec) null),
			    fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
			    Collections.singletonList(ref));
			
			//Setup the KeyInfo attributes
			KeyInfoFactory kif = fac.getKeyInfoFactory(); 
			ArrayList<Certificate> certificateList = new ArrayList<Certificate>();
			certificateList.add(keystore.getCertificate($ALIAS_CERTIFICADO));
			X509Data kv = kif.newX509Data(certificateList);
			KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kv));
			
			//Sign the document
			XMLSignature signature = fac.newXMLSignature(si, ki);
//			signature.sign(dsc);
			
			//Save the new signed XML file
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer trans = tf.newTransformer();
			trans.transform(new DOMSource(xmlDoc), new StreamResult(new FileOutputStream($PATH_CON_FIRMA)));
			
		} catch (Exception e){
			System.err.println("Signing error: " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	   public static KeyPair getKeyPair(KeyStore keystore, String alias, char[] password) {
	        try {
	        	
	            // Get private key
	            Key key = keystore.getKey(alias, password);
	    //        if (key instanceof PrivateKey) {
	                // Get certificate of public key
	                Certificate cert = keystore.getCertificate(alias);
	    
	                // Get public key
	                PublicKey publicKey = cert.getPublicKey();
	                System.out.println("si entro ");
	               
	                
	                
	                // Return a key pair
	                return new KeyPair(publicKey, (PrivateKey)key);
	      //      }
	        } catch (UnrecoverableKeyException e) {
	        } catch (NoSuchAlgorithmException e) {
	        } catch (KeyStoreException e) {
	        	System.err.println("key error: " + e.getMessage());
	        	e.printStackTrace();
	        }
	        return null;
	    }	
}