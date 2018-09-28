package wsHomologador;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import pe.gob.sunat.service.StatusResponse;
import pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService;
import pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService_Service;

public class H_main_status {
	

	
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
	public static String $PATH_HASH="";
			
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

	public static String $FILE_PATH_NAME_XML="";
	public static String $FILE_PATH_NAME_RESPUESTA="";
	public static String $FILE_PATH_NAME_RESPUESTA_XML="";
	public static String $FILE_PATH_NAME_ZIP="";
	public static String $FILE_NAME_ZIP="";
	public static String $FILE_PATH_NAME_HASH="";
	public static String $FILE_NAME="";
	
	
	
	//public static  obj_get_status miStatus = new obj_get_status();
	
	
	public static void conectar(String ticket, StatusResponse StatusResponse, String file_name, parametros misParametros ) throws Exception {
		
		
		$FILE_NAME=file_name;
		
		$PATH_ARCHIVOS_PLANOS=misParametros.get_ruta_base();
		$PATH_SIN_FIRMA=misParametros.get_ruta_xml_sin_firma();
		$PATH_CON_FIRMA=misParametros.get_ruta_xml_con_firma();
		$PATH_RESPUESTAS=misParametros.get_ruta_respuestas();
		$PATH_PDFS=misParametros.get_ruta_pdfs();
		$PATH_TICKETS=misParametros.get_ruta_tickets();
		$PATH_RESPUESTAS_STATUS=misParametros.get_ruta_respuestas_status();
		$PATH_CERTIFICADOS=misParametros.get_ruta_certificados();
		$PATH_HASH=misParametros.get_ruta_hash();
		
		
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
		
		$FILE_PATH_NAME_XML = $PATH_CON_FIRMA+$FILE_NAME+".xml";
		$FILE_PATH_NAME_ZIP = $PATH_CON_FIRMA+$FILE_NAME+".zip";
		$FILE_PATH_NAME_HASH = $PATH_HASH+$FILE_NAME+".hash";
		
		$FILE_NAME_ZIP=$FILE_NAME+".zip";
		$FILE_PATH_NAME_RESPUESTA=$PATH_RESPUESTAS+"R-"+$FILE_NAME+".zip";
		$FILE_PATH_NAME_RESPUESTA_XML=$PATH_RESPUESTAS+"R-"+$FILE_NAME+".xml";

		
		
		//String ticket = "201600605314592";
		
		
	//	$PATH_RESPUESTA=".\\data\\20129561263\\04_respuestas\\";
	//	$PATH_TICKETS=".\\data\\20129561263\\06_tickets\\";
	//	$PATH_STATUS=".\\data\\20129561263\\07_respuestas_status\\";
		
		
		
		
	//	$PATH_FIRMADOS= ".\\data\\20129561263\\03_xmls_con_firma\\";
		//$FILE_NAME = "20525719953-01-FF11-00000083";
		
		
	//	$FILE_PATH_NAME_XML = $PATH_FIRMADOS+$FILE_NAME+".xml";
	//	$FILE_PATH_NAME_ZIP = $PATH_FIRMADOS+$FILE_NAME+".zip";
	//	$FILE_PATH_NAME_TXT = $PATH_TICKETS+$FILE_NAME+"-"+Serie+"-"+Folio+".txt";
	//	$FILE_PATH_NAME_STATUS = $PATH_STATUS+"RS"+$FILE_NAME+".zip";
		
		
		
	//	$FILE_NAME_ZIP=$FILE_NAME+".zip";
	//	$FILE_PATH_NAME_RESPUESTA=$PATH_STATUS+"R-"+file_name+".zip";
		
		//crearXML.c_XML($PATH_SIN_FIRMA,$FILE_NAME);
		//Firma_Documento.firmar($PATH_SIN_FIRMA,$PATH_FIRMADOS,$FILE_NAME);
		
		
		
		
		
		BillService_Service service = new BillService_Service();
		HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver();
		service.setHandlerResolver(handlerResolver);
		BillService port = service.getBillServicePort();
		
		
	//	InputStream is = new FileInputStream($FILE_PATH_NAME_ZIP);
		
	//	DataSource ds = new ByteArrayDataSource(is,"application/octet-stream");
		H_main_status objeto = new H_main_status();
	//	DataHandler contentFile = new DataHandler(ds);
		
		
		
		
		try{
			
			//byte[] resultado = port.sendBill($FILE_NAME_ZIP, contentFile);
			//obj_get_status miStatus = new obj_get_status();
			StatusResponse = port.getStatus(ticket);
			System.out.println("La Respuesta es:"+StatusResponse.getStatusCode());
			objeto.writeSmallBinaryFile(StatusResponse.getContent(),$FILE_PATH_NAME_RESPUESTA);
			
			UnZip.descomprimir($FILE_PATH_NAME_RESPUESTA,$PATH_RESPUESTAS);
			System.out.println("nombre del archivo:"+$FILE_PATH_NAME_RESPUESTA);
			System.out.println("se descomprimio en :"+$PATH_RESPUESTAS);
			
			
			} catch(javax.xml.ws.soap.SOAPFaultException soapFaultException){
			
			javax.xml.soap.SOAPFault fault = soapFaultException.getFault();
		    System.out.println("El error es: "+fault.getFaultCode());
		}
	}
	     
	void writeSmallBinaryFile(byte[] aBytes, String aFileName) throws IOException {
	    Path path = Paths.get(aFileName);
	    Files.write(path, aBytes);
	  }

}