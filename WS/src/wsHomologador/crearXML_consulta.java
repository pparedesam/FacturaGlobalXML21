package wsHomologador;

import java.io.File;

import javax.activation.DataHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class crearXML_consulta {
 
	
	public static String[] myArrayCab = new String[1];
	public static int _counterCab=1;
	
	
	public static String $PATH="";
	
	public static String $FILE_NAME="";
	
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
	
	
	public static double $SUM_IGV=0;
	
	
	public static String $SERIE="";
	public static String $NUMERO="";
	public static String $TIPO_DOCUMENTO="";
	
	// declaracion de objeto tipo cabecera
	public static cabecera myCabecera = new cabecera();
	public static aca myAca = new aca();
	public static ley myLey = new ley();
	public static detalle[] myDetalle = new detalle[100];
	public static documentos_relacionados[] mydocumentos_relacionados = new documentos_relacionados[100];
	
			
	//String $PATH_XMLS_SIN_FIRMA
	
	public static void c_XML(DataHandler dh_con, String $FILE_NAME, parametros misParametros) throws Exception  {
		
		
		$FILE_NAME=misParametros.get_file_name();
		
		
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
		
		
		$FILE_NAME_XML = $PATH_SIN_FIRMA+$FILE_NAME+".xml";
		
		
		
		
			
		$SERIE=$FILE_NAME.substring(15,19);
		int _tam = $FILE_NAME.length(); 
		$NUMERO=$FILE_NAME.substring(20,_tam);
		
			
		String _cadena="";
		String _car="";
		int _num=0;
		
		
		// leemos el archivo plano cabecera
		
		readPlainTextCon(dh_con);
        
        // separa los campos
        int _tam_cabecera = myArrayCab[0].length();
    
        
        for(int i=0; i<_tam_cabecera; i++) {
        	_car = myArrayCab[0].substring(i,i+1);
        	      	
        	if (!"|".equals(_car)) {
        		
        		_cadena=_cadena+_car;
        		//System.out.println(_car);
        		
        		
        	} else {
        		
        		_num++;
        		        		
        		if (_num==1) {myCabecera.set_tipo_op(_cadena);}
            	if (_num==2) {myCabecera.set_fecha(_cadena);}
            	if (_num==3) {myCabecera.set_dom_fiscal(_cadena);}
            	if (_num==4) {myCabecera.set_ident(_cadena);}
            	if (_num==5) {myCabecera.set_num_ident (_cadena);}
            	if (_num==6) {myCabecera.set_nombre (_cadena);}
            	if (_num==7) {myCabecera.set_moneda (_cadena);}
            	if (_num==8) {myCabecera.set_desc_glo (Double.parseDouble(_cadena));}
            	if (_num==9) {myCabecera.set_sum_cargos (Double.parseDouble(_cadena));}
            	if (_num==10) {myCabecera.set_tot_desc (Double.parseDouble(_cadena));}
            	if (_num==11) {myCabecera.set_tot_vta_gra (Double.parseDouble(_cadena));}
            	if (_num==12) {myCabecera.set_tot_vta_in (Double.parseDouble(_cadena));}
            	if (_num==13) {myCabecera.set_tot_vta_exo (Double.parseDouble(_cadena));}
            	if (_num==14) {myCabecera.set_sum_igv (Double.parseDouble(_cadena));}
            	if (_num==15) {myCabecera.set_sum_isc (Double.parseDouble(_cadena));}
            	if (_num==16) {myCabecera.set_sum_otros (Double.parseDouble(_cadena));}
            	if (_num==17) {myCabecera.set_importe_tot (Double.parseDouble(_cadena));}
            	_cadena="";
            	
        	}
        }
        
        System.out.println("Cabecera-> Tipo de Operacion:        "+myCabecera.get_tipo_op());
        System.out.println("Cabecera-> Fecha de Emision:         "+myCabecera.get_fecha());
        System.out.println("Cabecera-> Tipo de Dom Fiscal:       "+myCabecera.get_dom_fiscal());
        System.out.println("Cabecera-> Tipo de Identificacion:   "+myCabecera.get_ident());
        System.out.println("Cabecera-> Numero de Identidad:      "+myCabecera.get_num_ident());
        System.out.println("Cabecera-> Nombre:                   "+myCabecera.get_nombre());
        System.out.println("Cabecera-> Moneda:                   "+myCabecera.get_moneda());
        System.out.println("Cabecera-> Descuento Global:         "+myCabecera.get_desc_glo());
        System.out.println("Cabecera-> Suma de Cargos:           "+myCabecera.get_sum_cargos());
        System.out.println("Cabecera-> Total Descuentos:         "+myCabecera.get_tot_desc());
        System.out.println("Cabecera-> Total Vta Gravada:        "+myCabecera.get_tot_vta_gra());
        System.out.println("Cabecera-> Total Vta Inafectadas:    "+myCabecera.get_tot_vta_in());
        System.out.println("Cabecera-> Total Vta Exonerada:      "+myCabecera.get_tot_vta_exo());
        System.out.println("Cabecera-> Total Suma IGV:           "+myCabecera.get_sum_igv());
        System.out.println("Cabecera-> Total Suma ISC:           "+myCabecera.get_sum_isc());
        System.out.println("Cabecera-> Total Suma otros:         "+myCabecera.get_sum_otros());
        System.out.println("Cabecera-> Importe Total:            "+myCabecera.get_importe_tot());
		
        
        // leemos el arcivo plano de aca
        
        
			        
	    
	    
	    writeXML($FILE_NAME_XML);
	  	    
	    // Firma_Documento.firmar($PATH_SIN_FIRMA,$PATH_CON_FIRMA,$FILE_NAME,misParametros);
	    H_main_consulta.conectar($FILE_NAME,misParametros);
		
	}
	
	
	public static void separaCab() {
		// Str1.length()
		
	}
	
	
	public static void readPlainTextCon(DataHandler dh) throws Exception {
			Object content = dh.getContent();
			
			BufferedReader br = null;
			
			try {

				String sCurrentLine;
				br = new BufferedReader(new InputStreamReader((InputStream) content));

				while ((sCurrentLine = br.readLine()) != null) {
					myArrayCab[0]=sCurrentLine;
					
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

		}
	
	
	
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static void writeXML(String $FILE_NAME) throws Exception {
	 
		DocumentBuilderFactory documentBuilderFctory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFctory.newDocumentBuilder();
 
		Document document = documentBuilder.newDocument();
		
		
		// agregamos la nombre pricipal dentro de este iran todos los elementos
		Element element = document.createElement("soapenv:Envelope");
		document.appendChild(element);
		
		
		// creamos los atributos de la cabecera
	
		Attr attr_xmlns = document.createAttribute("xmlns:ser");
		attr_xmlns.setValue("http://service.sunat.gob.pe");
		element.setAttributeNode(attr_xmlns);
		
		Attr attr_xmlns_cac = document.createAttribute("xmlns:soapenv");
		attr_xmlns_cac.setValue("http://schemas.xmlsoap.org/soap/envelope/");
		element.setAttributeNode(attr_xmlns_cac);
		
		Attr attr_xmlns_cbc = document.createAttribute("xmlns:wsse");
		attr_xmlns_cbc.setValue("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
		element.setAttributeNode(attr_xmlns_cbc);
		
		
	/// body
		
		Element Body = document.createElement("soapenv:Body");
		element.appendChild(Body);
		
			/// getStatus
			Element getStatus = document.createElement("ser:getStatus");
			Body.appendChild(getStatus);
		
				/// rucComprobante
				Element rucComprobante = document.createElement("rucComprobante");
				getStatus.appendChild(rucComprobante);
		

				/// tipoComprobante
				Element tipoComprobante = document.createElement("tipoComprobante");
				getStatus.appendChild(tipoComprobante);

				
				/// serieComprobante
				Element serieComprobante = document.createElement("serieComprobante");
				getStatus.appendChild(serieComprobante);

					
				/// numeroComprobante
				Element numeroComprobante = document.createElement("numeroComprobante");
				getStatus.appendChild(numeroComprobante);
				
											
						
			
		
				
					
				
				
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		DOMSource source = new DOMSource(document);
		
		StreamResult streamResult = new StreamResult(new File($FILE_NAME_XML));
		
		transformer.transform(source, streamResult);
		
		
		
	}

	
	
	
}