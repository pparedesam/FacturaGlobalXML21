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

 
public class crearXML_nd {
 
	
	public static String[] myArrayCab = new String[1];
	public static int _counterCab=1;

	
	public static String[] myArrayCab_Aca = new String[1];
	public static int _counterCab_aca=1;
	
	
	public static String[] myArrayCab_ley = new String[1];
	public static int _counterCab_ley=1;
	
	
	public static String[] myArrayDet = new String[100];
	public static int _counterDet=1; 
	
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
	
	
	// public static String 
	
	public static String $SERIE="";
	public static String $NUMERO="";
	public static String $TIPO_DOCUMENTO="";
	
	// declaracion de objeto tipo cabecera
	public static cabecera_nc myCabecera_nc = new cabecera_nc();
	public static aca myAca = new aca();
	public static ley myLey = new ley();	
	public static detalle[] myDetalle = new detalle[100];
			
	//String $PATH_XMLS_SIN_FIRMA
	public static void c_XML(DataHandler dh_det, DataHandler dh_cab, DataHandler dh_aca, DataHandler dh_ley, String $FILE_NAME, parametros misParametros) throws Exception  {
	//public static void c_XML(DataHandler dh_det, DataHandler dh_cab, String $FILE_NAME, parametros misParametros) throws Exception  {
		
		
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
		
		
				
		$RUC=$FILE_NAME.substring(0,11);
		$SERIE=$FILE_NAME.substring(15,19);
		int _tam = $FILE_NAME.length(); 
		$NUMERO=$FILE_NAME.substring(20,_tam);
		
			
		String _cadena="";
		String _car="";
		int _num=0;
		
		
		// leemos el archivo plano cabecera
		//readPlainTextCab($PATH_ARCHIVOS_PLANOS+$FILE_NAME+".cab",dh_cab);
		readPlainTextCab(dh_cab);
        
        // separa los campos
        int _tam_cabecera = myArrayCab[0].length();
        int _tam_detalle;
        
        
        for(int i=0; i<_tam_cabecera; i++) {
        	_car = myArrayCab[0].substring(i,i+1);
        	      	
        	if (!"|".equals(_car)) {
        		
        		_cadena=_cadena+_car;
        		//System.out.println(_car);
        		
        		
        	} else {
        		
        		_num++;
        		        		
        	//	
            	if (_num==1) {myCabecera_nc.set_fecha(_cadena);}
            	if (_num==2) {myCabecera_nc.set_tipo_nota_cre(_cadena);}
            	if (_num==3) {myCabecera_nc.set_motivo(_cadena);}
            	if (_num==4) {myCabecera_nc.set_tipo_op(_cadena);}
            	if (_num==5) {myCabecera_nc.set_num_doc_afec(_cadena);}
            	if (_num==6) {myCabecera_nc.set_ident(_cadena);}
            	if (_num==7) {myCabecera_nc.set_num_ident (_cadena);}
            	if (_num==8) {myCabecera_nc.set_nombre (_cadena);}
            	if (_num==9) {myCabecera_nc.set_moneda (_cadena);}
            	if (_num==10) {myCabecera_nc.set_sum_cargos (Double.parseDouble(_cadena));}
            	if (_num==11) {myCabecera_nc.set_tot_vta_gra (Double.parseDouble(_cadena));}
            	if (_num==12) {myCabecera_nc.set_tot_vta_in (Double.parseDouble(_cadena));}
            	if (_num==13) {myCabecera_nc.set_tot_vta_exo (Double.parseDouble(_cadena));}
            	if (_num==14) {myCabecera_nc.set_sum_igv (Double.parseDouble(_cadena));}
            	if (_num==15) {myCabecera_nc.set_sum_isc (Double.parseDouble(_cadena));}
            	if (_num==16) {myCabecera_nc.set_sum_otros (Double.parseDouble(_cadena));}
            	if (_num==17) {myCabecera_nc.set_importe_tot (Double.parseDouble(_cadena));}
            	_cadena="";
            	
        	}
        }
        
        System.out.println("Nombre del Archivos a Procesar:      "+$FILE_NAME);
        System.out.println("Cabecera-> Tipo de Operacion:        "+"Nota de Credito");
        System.out.println("Cabecera-> Fecha de Emision:         "+myCabecera_nc.get_fecha());
        System.out.println("Cabecera-> Tipo de Identificacion:   "+myCabecera_nc.get_ident());
        System.out.println("Cabecera-> Numero de Identidad:      "+myCabecera_nc.get_num_ident());
        System.out.println("Cabecera-> Nombre:                   "+myCabecera_nc.get_nombre());
        System.out.println("Cabecera-> Moneda:                   "+myCabecera_nc.get_moneda());
        System.out.println("Cabecera-> Suma de Cargos:           "+myCabecera_nc.get_sum_cargos());
        System.out.println("Cabecera-> Total Vta Gravada:        "+myCabecera_nc.get_tot_vta_gra());
        System.out.println("Cabecera-> Total Vta Inafectadas:    "+myCabecera_nc.get_tot_vta_in());
        System.out.println("Cabecera-> Total Vta Exonerada:      "+myCabecera_nc.get_tot_vta_exo());
        System.out.println("Cabecera-> Total Suma IGV:           "+myCabecera_nc.get_sum_igv());
        System.out.println("Cabecera-> Total Suma ISC:           "+myCabecera_nc.get_sum_isc());
        System.out.println("Cabecera-> Total Suma otros:         "+myCabecera_nc.get_sum_otros());
        System.out.println("Cabecera-> Importe Total:            "+myCabecera_nc.get_importe_tot());
	
        
        
        
        
        // leemos el arcivo plano de aca
        
        
 		readPlainTextAca(dh_aca);
         
         // separa los campos
         int _tam_cabecera_aca = myArrayCab_Aca[0].length();
         _num=0;
         
         
         
         
         
         for(int i=0; i<_tam_cabecera_aca; i++) {
         	_car = myArrayCab_Aca[0].substring(i,i+1);
         	      	
         	if (!"|".equals(_car)) {
         		
         		_cadena=_cadena+_car;
         		//System.out.println(_car);
         		
         		
         	} else {
         		
         		_num++;
         		// System.out.println(_cadena);        		
         		if (_num==1) {myAca.set_codRegPercepcion(_cadena);}
             	if (_num==2) {myAca.set_mtoBaseImponiblePercepcion(Double.parseDouble(_cadena));}
             	if (_num==3) {myAca.set_mtoPercepcion(Double.parseDouble(_cadena));}
             	if (_num==4) {myAca.set_mtoTotalIncPercepcion(Double.parseDouble(_cadena));}
             	if (_num==5) {myAca.set_mtoOperGratuitas(Double.parseDouble(_cadena));}
             	if (_num==6) {myAca.set_mtoTotalAnticipo(Double.parseDouble(_cadena));}
             	if (_num==7) {myAca.set_codPaisCliente(_cadena);}
             	if (_num==8) {myAca.set_codUbigeoCliente(_cadena);}
             	if (_num==9) {myAca.set_desDireccionCliente(_cadena);}
             	if (_num==10) {myAca.set_codPaisEntrega(_cadena);}
             	if (_num==11) {myAca.set_codUbigeoEntrega(_cadena);}
             	if (_num==12) {myAca.set_desDireccionEntrega(_cadena);}
             	if (_num==13) {myAca.set_fecVencimiento(_cadena);}
             	_cadena="";
             	
         	}
         }
         
         System.out.println("Aca-> C骴igo de r間imen de percepci髇:       "+myAca.get_codRegPercepcion());
         System.out.println("Aca-> Base imponible de percepci髇:          "+myAca.get_mtoBaseImponiblePercepcion());
         System.out.println("Aca-> Monto de la percepci髇:                "+myAca.get_mtoPercepcion());
         System.out.println("Aca-> Monto total incluido la percepci髇:    "+myAca.get_mtoTotalIncPercepcion());
         System.out.println("Aca-> Total valor de venta - Op. gratuitas:  "+myAca.get_mtoOperGratuitas());
         System.out.println("Aca-> Total Anticipos:                       "+myAca.get_mtoTotalAnticipo());
         System.out.println("Aca-> Direcci髇 del cliente (C骴 de pa韘):   "+myAca.get_codPaisCliente());
         System.out.println("Aca-> Direcci髇 del cliente (C骴 de ubigeo): "+myAca.get_codUbigeoCliente());
         System.out.println("Aca-> Dir. cliente (Dir completa):           "+myAca.get_desDireccionCliente());
         System.out.println("Aca-> C骴 de pa韘 en que se entrega el bien: "+myAca.get_codPaisEntrega());
         System.out.println("Aca-> C骴 ubigeo donde se entrega el bien:   "+myAca.get_codUbigeoEntrega());
         System.out.println("Aca-> Dir completa deonde se entrega el bien:"+myAca.get_desDireccionEntrega());
         System.out.println("Aca-> Fecha de vencimiento:                  "+myAca.get_fecVencimiento());
         
         

        

        
		// leemos el arcivo plano detalle
		//readPlainTextDet($PATH_ARCHIVOS_PLANOS+$FILE_NAME+".det",dh_det);
        readPlainTextDet(dh_det);
	
		_counterDet=_counterDet-1;
		
	    for(int i=1; i<_counterDet; i++){
	    	myDetalle[i] = new detalle();
	        _cadena="";
	        _num=0;
	        	
	            
	        _tam_detalle = myArrayDet[i].length();
	        for(int x=0; x<_tam_detalle; x++) {
	           	_car = myArrayDet[i].substring(x,x+1);
	            	      	
	           	if (!"|".equals(_car)) {
	           		_cadena=_cadena+_car;
	        	} else {
	        		_num++;
	            		        		
	           		if (_num==1) {myDetalle[i].set_unidad_med(_cadena);}
	           		if (_num==2) {myDetalle[i].set_cantidad(Double.parseDouble(_cadena));}
	           		if (_num==3) {myDetalle[i].set_producto(_cadena);}
	           		if (_num==4) {myDetalle[i].set_cod_sunat(_cadena);}
	           		if (_num==5) {myDetalle[i].set_descrip(_cadena);}
	           		if (_num==6) {myDetalle[i].set_valor_unit(Double.parseDouble(_cadena));}
	           		if (_num==7) {myDetalle[i].set_desc_unit(Double.parseDouble(_cadena));}
	           		if (_num==8) {myDetalle[i].set_igv_unit(Double.parseDouble(_cadena));}
	          		if (_num==9) {myDetalle[i].set_afec_igv(_cadena);}
	           		if (_num==10) {myDetalle[i].set_isc_unit(Double.parseDouble(_cadena));}
	           		if (_num==11) {myDetalle[i].set_tipo_isc(_cadena);}
	           		if (_num==12) {myDetalle[i].set_precio_unit(Double.parseDouble(_cadena));}
	           		if (_num==13) {myDetalle[i].set_valor_tot(Double.parseDouble(_cadena));}
	           		
	               	_cadena="";
	        	}
	        }
	            
	        System.out.println(" ");
	        System.out.println("Detalle-> Unidad de Medida:             "+myDetalle[i].get_unidad_med());
	        System.out.println("Detalle-> Cantidad:                     "+myDetalle[i].get_cantidad());
	        System.out.println("Detalle-> Producto:                     "+myDetalle[i].get_producto());
	        System.out.println("Detalle-> Codigo Sunat:                 "+myDetalle[i].get_cod_sunat());
	        System.out.println("Detalle-> Codigo Descripcion:           "+myDetalle[i].get_descrip());
	        System.out.println("Detalle-> Valor Unidad:                 "+myDetalle[i].get_valor_unit());
	        System.out.println("Detalle-> Descuento de Unidad:          "+myDetalle[i].get_desc_unit());
	        System.out.println("Detalle-> Monto de IGV por Unidad:      "+myDetalle[i].get_igv_unit());
	        System.out.println("Detalle-> Tipo de Afectacion IGV:       "+myDetalle[i].get_afec_igv());
	        System.out.println("Detalle-> Monto de ISC por Unidad:      "+myDetalle[i].get_isc_unit());
	        System.out.println("Detalle-> Tipo de Afectacion ISC:       "+myDetalle[i].get_tipo_isc());
	        System.out.println("Detalle-> Precio por Unidad:            "+myDetalle[i].get_precio_unit());
	        System.out.println("Detalle-> Valor Total:                  "+myDetalle[i].get_valor_tot());
	           
	        $SUM_IGV=$SUM_IGV+myDetalle[i].get_igv_unit();
	        
	        $SUM_IGV=round($SUM_IGV,2);
	        
	    	}
				
		
	    
	    writeXML($FILE_NAME_XML);
	    System.out.println("Sumatoria IGV          : "+$SUM_IGV);
	    System.out.println("Archivo Generado    : "+$FILE_NAME_XML);
	    
	   // System.out.println("Path sin firma    : "+$PATH_SIN_FIRMA);
	   // System.out.println("Path con firma    : "+$PATH_CON_FIRMA);
	   // System.out.println("fILE nAME    : "+$FILE_NAME);
	    
	    
	    Firma_Documento.firmar($PATH_SIN_FIRMA,$PATH_CON_FIRMA,$FILE_NAME,misParametros);
	    H_main.conectar($FILE_NAME,misParametros);
		
	}
	
	
	public static void separaCab() {
		// Str1.length()
		
	}
	
	
	public static void readPlainTextCab(DataHandler dh) throws Exception {
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
	
	public static void readPlainTextAca(DataHandler dh) throws Exception {
		Object content = dh.getContent();
		
		BufferedReader br = null;
		
		try {

			String sCurrentLine;
			br = new BufferedReader(new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {
				myArrayCab_Aca[0]=sCurrentLine;
				
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
	
	
	
	
	
	public static void readPlainTextDet(DataHandler dh) throws Exception {
		Object content = dh.getContent();
		
		BufferedReader br = null;
		
		
		try {

			String sCurrentLine;
			br = new BufferedReader(new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {
				
				
				myArrayDet[_counterDet]=sCurrentLine;
				_counterDet=_counterDet+1;
				
			}
			_counterDet=_counterDet+1;

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
		Element element = document.createElement("DebitNote");
		document.appendChild(element);
		
		
		// creamos los atributos de la cabecera
	
		Attr attr_xmlns = document.createAttribute("xmlns");
		attr_xmlns.setValue("urn:oasis:names:specification:ubl:schema:xsd:DebitNote-2");
		element.setAttributeNode(attr_xmlns);
		
		Attr attr_xmlns_cac = document.createAttribute("xmlns:cac");
		attr_xmlns_cac.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
		element.setAttributeNode(attr_xmlns_cac);
		
		Attr attr_xmlns_cbc = document.createAttribute("xmlns:cbc");
		attr_xmlns_cbc.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
		element.setAttributeNode(attr_xmlns_cbc);
		
		Attr attr_xmlns_ccts = document.createAttribute("xmlns:ccts");
		attr_xmlns_ccts.setValue("urn:un:unece:uncefact:documentation:2");
		element.setAttributeNode(attr_xmlns_ccts);
		
		Attr attr_xmlns_ds = document.createAttribute("xmlns:ds");
		attr_xmlns_ds.setValue("http://www.w3.org/2000/09/xmldsig#");
		element.setAttributeNode(attr_xmlns_ds);
		
		Attr attr_xmlns_ext = document.createAttribute("xmlns:ext");
		attr_xmlns_ext.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
		element.setAttributeNode(attr_xmlns_ext);
		
		Attr attr_xmlns_qdt = document.createAttribute("xmlns:qdt");
		attr_xmlns_qdt.setValue("urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
		element.setAttributeNode(attr_xmlns_qdt);
		
		Attr attr_xmlns_sac = document.createAttribute("xmlns:sac");
		attr_xmlns_sac.setValue("urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
		element.setAttributeNode(attr_xmlns_sac);
		
		Attr attr_xmlns_schema = document.createAttribute("xmlns:schemaLocation");
		attr_xmlns_schema.setValue("");
		element.setAttributeNode(attr_xmlns_schema);
			
		
		
		Attr attr_xmlns_udt = document.createAttribute("xmlns:udt");
		attr_xmlns_udt.setValue("urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
		element.setAttributeNode(attr_xmlns_udt);
		
		
		
		
		Attr attr_xmlns_xsi = document.createAttribute("xmlns:xsi");
		attr_xmlns_xsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
		element.setAttributeNode(attr_xmlns_xsi);
		
			
		
		/// NODO NUMERO 2  FIRMA DIGITAL  -- /Invoice/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/ds:Signature /Invoice/cac:Signature 
		
		
		/// NODO UBLExtensions
		
		Element UBLExtensions = document.createElement("ext:UBLExtensions");
		element.appendChild(UBLExtensions);
		
			/// NODO UBLExtensio
			Element UBLExtension = document.createElement("ext:UBLExtension");
			UBLExtensions.appendChild(UBLExtension);
		
				/// NODO ext:ExtensionContent
				Element ExtensionContent = document.createElement("ext:ExtensionContent");
				UBLExtension.appendChild(ExtensionContent);
		
					// sac:AdditionalInformation
					Element AdditionalInformation = document.createElement("sac:AdditionalInformation");
					ExtensionContent.appendChild(AdditionalInformation);
						
					
					
					
					
					
					
						// sac:AdditionalMonetaryTotal VENTAS GRAVADAS
						Element AdditionalMonetaryTotal = document.createElement("sac:AdditionalMonetaryTotal");
						AdditionalInformation.appendChild(AdditionalMonetaryTotal);
						
							// cbc:ID  VENTAS GRAVADAS
							Element ID_UBL = document.createElement("cbc:ID");
							ID_UBL.appendChild(document.createTextNode("1001"));
							AdditionalMonetaryTotal.appendChild(ID_UBL);
					
							// cbc:PayableAmount
							Element PayableAmount_UBL = document.createElement("cbc:PayableAmount");
							PayableAmount_UBL.appendChild(document.createTextNode(""+myCabecera_nc.get_tot_vta_gra()));
							AdditionalMonetaryTotal.appendChild(PayableAmount_UBL);
							
							// currencyID
							Attr currencyID_UBL = document.createAttribute("currencyID");
							currencyID_UBL.setValue(myCabecera_nc.get_moneda());
							PayableAmount_UBL.setAttributeNode(currencyID_UBL);
							
						// sac:AdditionalMonetaryTotal VENTAS EXONERADAS
						Element AdditionalMonetaryTotal_EXONE = document.createElement("sac:AdditionalMonetaryTotal");
						AdditionalInformation.appendChild(AdditionalMonetaryTotal_EXONE);	
								
												
							// cbc:ID  VENTAS EXONERADAS
							Element ID_UBL_ID_UBL_EXONE = document.createElement("cbc:ID");
							ID_UBL_ID_UBL_EXONE.appendChild(document.createTextNode("1003"));
							AdditionalMonetaryTotal_EXONE.appendChild(ID_UBL_ID_UBL_EXONE);
					
							// cbc:PayableAmount
							Element PayableAmount_UBL_EXONE= document.createElement("cbc:PayableAmount");
							PayableAmount_UBL_EXONE.appendChild(document.createTextNode(""+myCabecera_nc.get_tot_vta_exo()));
							AdditionalMonetaryTotal_EXONE.appendChild(PayableAmount_UBL_EXONE);
							
							// currencyID
							Attr currencyID_UBL_EXONE = document.createAttribute("currencyID");
							currencyID_UBL_EXONE.setValue(myCabecera_nc.get_moneda());
							PayableAmount_UBL_EXONE.setAttributeNode(currencyID_UBL_EXONE);
							
							
							
							
							// sac:direccion del cliente
							Element AdditionalProperty = document.createElement("sac:AdditionalProperty");
							AdditionalInformation.appendChild(AdditionalProperty);
								
							Element ID_Add = document.createElement("cbc:ID");
							ID_Add.appendChild(document.createTextNode("1000"));
							AdditionalProperty.appendChild(ID_Add);
							
							Element value_Add = document.createElement("cbc:Value");
							value_Add.appendChild(document.createTextNode(myAca.get_desDireccionCliente()));
							AdditionalProperty.appendChild(value_Add);
							
						// sac:AdditionalMonetaryTotal VENTAS INAFECTADAS
					//	Element AdditionalMonetaryTotal_INAFE = document.createElement("sac:AdditionalMonetaryTotal");
					//	AdditionalInformation.appendChild(AdditionalMonetaryTotal_INAFE);	
							
							
							// cbc:ID  VENTAS INAFECTADAS
					//		Element ID_UBL_ID_UBL_INAFE = document.createElement("cbc:ID");
					//		ID_UBL_ID_UBL_INAFE.appendChild(document.createTextNode("1002"));
					//		AdditionalMonetaryTotal_INAFE.appendChild(ID_UBL_ID_UBL_INAFE);
					
					//		// cbc:PayableAmount
					//		Element PayableAmount_UBL_INAFE = document.createElement("cbc:PayableAmount");
					//		PayableAmount_UBL_INAFE.appendChild(document.createTextNode(""+myCabecera_nc.get_tot_vta_in()));
					//		AdditionalMonetaryTotal_INAFE.appendChild(PayableAmount_UBL_INAFE);
							
							// currencyID
					//		Attr currencyID_UBL_INAFE = document.createAttribute("currencyID");
					//		currencyID_UBL_INAFE.setValue("PEN");
					//		PayableAmount_UBL_INAFE.setAttributeNode(currencyID_UBL_INAFE);
							
								
							// sac:AdditionalMonetaryTotal VENTAS EXONERADAS
					//		Element AdditionalMonetaryTotal_EXONE = document.createElement("sac:AdditionalMonetaryTotal");
					//		AdditionalInformation.appendChild(AdditionalMonetaryTotal_EXONE);	
								
								
								// cbc:ID  VENTAS EXONERADAS
					//			Element ID_UBL_ID_UBL_EXONE = document.createElement("cbc:ID");
					//			ID_UBL_ID_UBL_EXONE.appendChild(document.createTextNode("1003"));
					//			AdditionalMonetaryTotal_EXONE.appendChild(ID_UBL_ID_UBL_EXONE);
						
								// cbc:PayableAmount
					//			Element PayableAmount_UBL_EXONE= document.createElement("cbc:PayableAmount");
					//			PayableAmount_UBL_EXONE.appendChild(document.createTextNode(""+myCabecera_nc.get_tot_vta_exo()));
					//			AdditionalMonetaryTotal_EXONE.appendChild(PayableAmount_UBL_EXONE);
								
								// currencyID
					//			Attr currencyID_UBL_EXONE = document.createAttribute("currencyID");
					//			currencyID_UBL_EXONE.setValue("PEN");
					//			PayableAmount_UBL_EXONE.setAttributeNode(currencyID_UBL_EXONE);
								
							
								
								
								// sac:AdditionalMonetaryTotal VENTAS GRATUITAS
								// Element AdditionalMonetaryTotal_GRATIS = document.createElement("sac:AdditionalMonetaryTotal");
								// AdditionalInformation.appendChild(AdditionalMonetaryTotal_GRATIS);	
									
									
									// cbc:ID  VENTAS GRATUITAS
									//Element ID_UBL_ID_UBL_GRATIS = document.createElement("cbc:ID");
									//ID_UBL_ID_UBL_GRATIS.appendChild(document.createTextNode("1004"));
									//AdditionalMonetaryTotal_GRATIS.appendChild(ID_UBL_ID_UBL_GRATIS);
							
									// cbc:PayableAmount
									//Element PayableAmount_UBL_GRATIS= document.createElement("cbc:PayableAmount");
									//PayableAmount_UBL_GRATIS.appendChild(document.createTextNode(""));
									//AdditionalMonetaryTotal_GRATIS.appendChild(PayableAmount_UBL_GRATIS);
									
									// currencyID
									//Attr currencyID_UBL_GRATIS = document.createAttribute("currencyID");
									//currencyID_UBL_GRATIS.setValue("PEN");
									//PayableAmount_UBL_GRATIS.setAttributeNode(currencyID_UBL_GRATIS);
									
								
							
					
						// sac:AdditionalProperty
				//		Element AdditionalProperty = document.createElement("sac:AdditionalProperty");
				//		AdditionalInformation.appendChild(AdditionalProperty);
							
							// cbc:ID
				//			Element ID_PROP = document.createElement("cbc:ID");
				//			ID_PROP.appendChild(document.createTextNode("1000"));
				//			AdditionalProperty.appendChild(ID_PROP);
							
							// cbc:Value
				//			Element Value_PROP = document.createElement("cbc:Value");
				//			Value_PROP.appendChild(document.createTextNode("NUMERO EN LETRA"));
				//			AdditionalProperty.appendChild(Value_PROP);
							
				
							
				/// ext:UBLExtension
				//Element UBLExtension2 = document.createElement("ext:UBLExtension");
				//UBLExtensions.appendChild(UBLExtension2);	
				
					/// NODO ext:ExtensionContent
					//Element ExtensionContent2 = document.createElement("ext:ExtensionContent");
					//UBLExtension2.appendChild(ExtensionContent2);
				
						/// NODO Signature
						//Element Signature = document.createElement("Signature");
						//ExtensionContent2.appendChild(Signature);
						
						// ID
					//	Attr signature_ID = document.createAttribute("signature_ID");
					//	signature_ID.setValue("aqui va la firma");
					//	Signature.setAttributeNode(signature_ID);
				
							
		///////////////////////////////////////////////// FIN UBLVersionID

		// UBLVersionID
		Element UBLVersionID = document.createElement("cbc:UBLVersionID");
		UBLVersionID.appendChild(document.createTextNode("2.0"));
		element.appendChild(UBLVersionID);
							
							
		// cbc:CustomizationID
		Element CustomizationID = document.createElement("cbc:CustomizationID");
		CustomizationID.appendChild(document.createTextNode("1.0"));
		element.appendChild(CustomizationID);
		
		
		/// NODO NUMERO 8  SERIE + NUMERO CORRELATIVO -- (Factura)    /Invoice/cbc:InvoiceTypeCode  
		 
		// cbc:ID			
		Element SERIE = document.createElement("cbc:ID");
		SERIE.appendChild(document.createTextNode($SERIE+"-"+$NUMERO));
		element.appendChild(SERIE);
											
		///////////////////////////////////////////////// FIN NUMERO 7 -- TIPO DE DOCUMENTO
									
		/// NODO NUMERO 1  FECHA DE EMISION DEL DOCUMENTO -- fecEmision --- /Invoice/cbc:IssueDate 
							
		Element fecEmision = document.createElement("cbc:IssueDate");
		fecEmision.appendChild(document.createTextNode(myCabecera_nc.get_fecha()));
		element.appendChild(fecEmision);
									
		///////////////////////////////////////////////// FIN NUMERO 1 -- fecEmision	
		
		
		
		
		//cbc:DocumentCurrencyCode
		Element DocumentCurrencyCode = document.createElement("cbc:DocumentCurrencyCode");
		DocumentCurrencyCode.appendChild(document.createTextNode(myCabecera_nc.get_moneda()));
		element.appendChild(DocumentCurrencyCode);
		
		
		//cac:DiscrepancyResponse
		Element DiscrepancyResponse = document.createElement("cac:DiscrepancyResponse");
		//DiscrepancyResponse.appendChild(document.createTextNode(myCabecera_nc.get_moneda()));
		element.appendChild(DiscrepancyResponse);
				
			// cbc:ReferenceID
			Element ReferenceID = document.createElement("cbc:ReferenceID");
			DiscrepancyResponse.appendChild(ReferenceID);
			ReferenceID.setTextContent(myCabecera_nc.get_num_doc_afec());
		
			
			// cbc:ResponseCode
			Element ResponseCode = document.createElement("cbc:ResponseCode");
			DiscrepancyResponse.appendChild(ResponseCode);
			ResponseCode.setTextContent(myCabecera_nc.get_tipo_nota_cre());
					
			// cbc:Description
			Element Description = document.createElement("cbc:Description");
			DiscrepancyResponse.appendChild(Description);
			Description.setTextContent(myCabecera_nc.get_motivo());
						
			
		//cac:BillingReference
		Element BillingReference = document.createElement("cac:BillingReference");
		element.appendChild(BillingReference);
			
				//cac:InvoiceDocumentReference
				Element InvoiceDocumentReference = document.createElement("cac:InvoiceDocumentReference");
				BillingReference.appendChild(InvoiceDocumentReference);
				
					// cbc:ID
					Element ID_DocumentReference = document.createElement("cbc:ID");
					InvoiceDocumentReference.appendChild(ID_DocumentReference);
					ID_DocumentReference.setTextContent(myCabecera_nc.get_num_doc_afec());
				
					//cbc:DocumentTypeCode
					Element DocumentTypeCode = document.createElement("cbc:DocumentTypeCode");
					InvoiceDocumentReference.appendChild(DocumentTypeCode);
					DocumentTypeCode.setTextContent(myCabecera_nc.get_tipo_op());
				
					/// AQUI ME QUEDE 1
					
					
					
		// cac:Signature  al nivel de raiz
		Element cacSignature = document.createElement("cac:Signature");
		element.appendChild(cacSignature);
		
			// cbc:ID
			Element cbcID = document.createElement("cbc:ID");
			cacSignature.appendChild(cbcID);
			cbcID.setTextContent($RUC);
			
			// cac:SignatoryParty
			Element SignatoryParty = document.createElement("cac:SignatoryParty");
			cacSignature.appendChild(SignatoryParty);
			
				//	cac:PartyIdentification
				Element PartyIdentification = document.createElement("cac:PartyIdentification");
				SignatoryParty.appendChild(PartyIdentification);
			
					// cbc:ID
					Element cbcID2 = document.createElement("cbc:ID");
					PartyIdentification.appendChild(cbcID2);
					cbcID2.setTextContent($RUC);
					
					
				//	cac:PartyName
				Element cac_PartyName = document.createElement("cac:PartyName");
				SignatoryParty.appendChild(cac_PartyName);
					
					
					// cbc:Name
					Element cbc_Name = document.createElement("cbc:Name");
					cac_PartyName.appendChild(cbc_Name);
					cbc_Name.setTextContent($RAZON_SOCIAL);
					
		
					
					
			// cac:DigitalSignatureAttachment
			Element DigitalSignatureAttachment = document.createElement("cac:DigitalSignatureAttachment");
			cacSignature.appendChild(DigitalSignatureAttachment);	
				
				// cac:ExternalReference
				Element ExternalReference = document.createElement("cac:ExternalReference");
				DigitalSignatureAttachment.appendChild(ExternalReference);	
						
						// cbc:URI
						Element URI2 = document.createElement("cbc:URI");
						ExternalReference.appendChild(URI2);
						URI2.setTextContent($RUC);
							
		///////////////////////////////////////////////// FIN NUMERO 2 FIRMA DIGITAL  --				
	
			
		
										
						
						
			
						
		///  NUMERO 3 -- Apellidos y nombres, denominaci贸n o raz贸n social 
		/// -- /Invoice/cac:AccountingSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName
						//3 4 5 6 
						// Apellidos y nombres, denominaci贸n o raz贸n social Nombre Comercial Domicilio fiscal N煤mero de RUC 
						
		// cac:AccountingSupplierParty EN RAIZ 
		Element AccountingSupplierParty = document.createElement("cac:AccountingSupplierParty");
		element.appendChild(AccountingSupplierParty);
		
			// cbc:CustomerAssignedAccountID
			Element CustomerAssignedAccountID = document.createElement("cbc:CustomerAssignedAccountID");
			CustomerAssignedAccountID.appendChild(document.createTextNode($RUC));
			AccountingSupplierParty.appendChild(CustomerAssignedAccountID);
			
			
			// cbc:AdditionalAccountID
			Element AdditionalAccountID = document.createElement("cbc:AdditionalAccountID");
			AdditionalAccountID.appendChild(document.createTextNode("6"));
			AccountingSupplierParty.appendChild(AdditionalAccountID);
										
			// cac:Party
			Element Party3 = document.createElement("cac:Party");
			AccountingSupplierParty.appendChild(Party3);
			
				// cac:PartyName
				Element PartyName = document.createElement("cac:PartyName");
				Party3.appendChild(PartyName);
					
					// Name
					Element Name = document.createElement("cbc:Name");
					Name.appendChild(document.createTextNode($RAZON_SOCIAL));
					PartyName.appendChild(Name);
			
				// cac:PostalAddress
				Element PostalAddress = document.createElement("cac:PostalAddress");
				Party3.appendChild(PostalAddress);	
					
					// cbc:ID
					Element id_postal = document.createElement("cbc:ID");
					id_postal.appendChild(document.createTextNode($CODIGO_POSTAL));
					PostalAddress.appendChild(id_postal);
					
					// cbc:StreetName
					Element StreetName = document.createElement("cbc:StreetName");
					StreetName.appendChild(document.createTextNode($DIRECCION));
					PostalAddress.appendChild(StreetName);
					
					// cbc:CityName
					Element CityName = document.createElement("cbc:CityName");
					CityName.appendChild(document.createTextNode($CIUDAD));
					PostalAddress.appendChild(CityName);
					
					// cbc:CountrySubentity
					Element CountrySubentity = document.createElement("cbc:CountrySubentity");
					CountrySubentity.appendChild(document.createTextNode("CountrySubentity"));
					PostalAddress.appendChild(CountrySubentity);
					
					// cbc:District
					// Element District = document.createElement("cbc:District");
					// District.appendChild(document.createTextNode("Distroto"));
					// PostalAddress.appendChild(District);
					
					// cac:Country
					Element Country = document.createElement("cac:Country");
					PostalAddress.appendChild(Country);
					
						// cbc:IdentificationCode
						Element pais = document.createElement("cbc:IdentificationCode");
						pais.appendChild(document.createTextNode($PAIS));
						Country.appendChild(pais);
						
				// cac:PartyLegalEntity
				Element PartyLegalEntity = document.createElement("cac:PartyLegalEntity");
				Party3.appendChild(PartyLegalEntity);	
					
					// cbc:RegistrationName
					Element RegistrationName = document.createElement("cbc:RegistrationName");
					RegistrationName.appendChild(document.createTextNode($RAZON_SOCIAL));
					PartyLegalEntity.appendChild(RegistrationName);
					
					
		/////////////////////////////////////////////////////////////////////////// FIN DE NUMERO  3 4 5 6
							
					
		
		
		
		
					
		
		
		
		/// NODO NUMERO 9 Y 19 Tipo y n煤mero de documento de identidad del adquirente o usuario   
		 
		// cac:AccountingCustomerParty
		
		Element AccountingCustomerParty = document.createElement("cac:AccountingCustomerParty");
		element.appendChild(AccountingCustomerParty);
		
			// cbc:CustomerAssignedAccountID
			Element CustomerAssignedAccountID2 = document.createElement("cbc:CustomerAssignedAccountID");
			CustomerAssignedAccountID2.appendChild(document.createTextNode(myCabecera_nc.get_num_ident()));
			AccountingCustomerParty.appendChild(CustomerAssignedAccountID2);
			
		    // cbc:AdditionalAccountID
			Element AdditionalAccountID2 = document.createElement("cbc:AdditionalAccountID");
			AdditionalAccountID2.appendChild(document.createTextNode(myCabecera_nc.get_ident())); 
			AccountingCustomerParty.appendChild(AdditionalAccountID2);
			
			// cac:Party
			Element Party = document.createElement("cac:Party");
			AccountingCustomerParty.appendChild(Party);
			
			
				// cac:PartyLegalEntity
				Element PartyLegalEntity2 = document.createElement("cac:PartyLegalEntity");
				Party.appendChild(PartyLegalEntity2);
				
					// cbc:RegistrationName
					Element RegistrationName2 = document.createElement("cbc:RegistrationName");
					RegistrationName2.appendChild(document.createTextNode(myCabecera_nc.get_nombre()));
					PartyLegalEntity2.appendChild(RegistrationName2);
					
				
				
			
			
		
		
		
											
		///////////////////////////////////////////////// FIN NUMERO 7 -- TIPO DE DOCUMENTO
		
		
		// cac:SellerSupplierParty
					
	//	Element SellerSupplierParty = document.createElement("cac:SellerSupplierParty");
	//	element.appendChild(SellerSupplierParty);
		
			// cac:Party
	//		Element Party4 = document.createElement("cac:Party");
	//		SellerSupplierParty.appendChild(Party4);
				
				// cac:PostalAddress
	//			Element PostalAddress2 = document.createElement("cac:PostalAddress");
	//			Party4.appendChild(PostalAddress2);
					
					// cbc:AddressTypeCode
	//				Element AddressTypeCode = document.createElement("cbc:AddressTypeCode");
	//				AddressTypeCode.appendChild(document.createTextNode("0"));
	//				PostalAddress2.appendChild(AddressTypeCode);
					
					
		////////////////////////////////////////////////////////////////////
					
					
		// cac:TaxTotal total de impuestos "1"
		Element TaxTotal = document.createElement("cac:TaxTotal");
		element.appendChild(TaxTotal);
			
			// cbc:TaxAmount
			Element TaxAmount = document.createElement("cbc:TaxAmount");
			TaxAmount.appendChild(document.createTextNode(""+$SUM_IGV));
			
			TaxTotal.appendChild(TaxAmount);
			
				// currencyID
				Attr attr_currencyID = document.createAttribute("currencyID");
				attr_currencyID.setValue(myCabecera_nc.get_moneda());
				TaxAmount.setAttributeNode(attr_currencyID);
				
			// cac:TaxSubtotal
			Element TaxSubtotal = document.createElement("cac:TaxSubtotal");
			TaxTotal.appendChild(TaxSubtotal);
			
				// cbc:TaxAmount
				Element TaxAmount2 = document.createElement("cbc:TaxAmount");
				TaxAmount2.appendChild(document.createTextNode(""+$SUM_IGV));
				TaxSubtotal.appendChild(TaxAmount2);
				
					// currencyID
					Attr attr_currencyID2 = document.createAttribute("currencyID");
					attr_currencyID2.setValue(myCabecera_nc.get_moneda());
					TaxAmount2.setAttributeNode(attr_currencyID2);
					
				// cac:TaxCategory
				Element TaxCategory = document.createElement("cac:TaxCategory");
				TaxSubtotal.appendChild(TaxCategory);
				
					// cac:TaxScheme
					Element TaxScheme = document.createElement("cac:TaxScheme");
					TaxCategory.appendChild(TaxScheme);
						
						// cbc:ID
						Element ID = document.createElement("cbc:ID");
						ID.appendChild(document.createTextNode("1000"));
						TaxScheme.appendChild(ID);
						
						// cbc:Name
						Element Name_igv = document.createElement("cbc:Name");
						Name_igv.appendChild(document.createTextNode("IGV"));
						TaxScheme.appendChild(Name_igv);
						
						// cbc:TaxTypeCode
						Element TaxTypeCode = document.createElement("cbc:TaxTypeCode");
						TaxTypeCode.appendChild(document.createTextNode("VAT"));
						TaxScheme.appendChild(TaxTypeCode);
				
						
		////////////////////////////////////////////////////////////////////
						
						
						
		/// cac:RequestedMonetaryTotal
		Element RequestedMonetaryTotal = document.createElement("cac:RequestedMonetaryTotal");
		element.appendChild(RequestedMonetaryTotal);				
						
			// cbc:AllowanceTotalAmount	
		//	Element AllowanceTotalAmount = document.createElement("cbc:AllowanceTotalAmount");
		//	AllowanceTotalAmount.appendChild(document.createTextNode("0.00"));
		//	LegalMonetaryTotal.appendChild(AllowanceTotalAmount);	
			
				// currencyID
		//		Attr attr_currencyID3 = document.createAttribute("currencyID");
		//		attr_currencyID3.setValue("PEN");
		//		AllowanceTotalAmount.setAttributeNode(attr_currencyID3);
			
			// cbc:ChargeTotalAmount
		//	Element ChargeTotalAmount = document.createElement("cbc:ChargeTotalAmount");
		//	ChargeTotalAmount.appendChild(document.createTextNode("0.00"));
		//	LegalMonetaryTotal.appendChild(ChargeTotalAmount);	
			
				// currencyID
		//		Attr attr_currencyID4 = document.createAttribute("currencyID");
		//		attr_currencyID4.setValue("PEN");
		//		ChargeTotalAmount.setAttributeNode(attr_currencyID4);					
			
			// cbc:PayableAmount
			Element PayableAmount = document.createElement("cbc:PayableAmount");
			PayableAmount.appendChild(document.createTextNode(""+myCabecera_nc.get_importe_tot()));
			RequestedMonetaryTotal.appendChild(PayableAmount);	
			
				// currencyID
				Attr attr_currencyID5 = document.createAttribute("currencyID");
				attr_currencyID5.setValue(myCabecera_nc.get_moneda());
				PayableAmount.setAttributeNode(attr_currencyID5);
			
			
		

		for (int linea=1; linea<_counterDet; linea++) {	
		
			// cac:DebitNoteLine
			Element CreditNoteLine = document.createElement("cac:DebitNoteLine");
			element.appendChild(CreditNoteLine);		
			
				// cbc:ID
				Element ID_Item = document.createElement("cbc:ID");
				ID_Item.appendChild(document.createTextNode(""+linea));
				CreditNoteLine.appendChild(ID_Item);		
				
				// cbc:DebitedQuantity
				Element CreditedQuantity = document.createElement("cbc:DebitedQuantity");
				CreditedQuantity.appendChild(document.createTextNode(""+round(myDetalle[linea].get_cantidad(),2)));
				CreditNoteLine.appendChild(CreditedQuantity);	
			
					// unitCode
					Attr attr_unitCode = document.createAttribute("unitCode");
					attr_unitCode.setValue(myDetalle[linea].get_unidad_med());
					CreditedQuantity.setAttributeNode(attr_unitCode);					
		
			
					// cbc:LineExtensionAmount  //alejandro
					Element LineExtensionAmount = document.createElement("cbc:LineExtensionAmount");
					LineExtensionAmount.appendChild(document.createTextNode(""+myDetalle[linea].get_valor_tot()));
					CreditNoteLine.appendChild(LineExtensionAmount);		
			
					// currencyID
					Attr attr_currencyID_ITEM = document.createAttribute("currencyID");
					attr_currencyID_ITEM.setValue(myCabecera_nc.get_moneda());
					LineExtensionAmount.setAttributeNode(attr_currencyID_ITEM);					
	
			
					// cac:PricingReference
					Element PricingReference = document.createElement("cac:PricingReference");
					CreditNoteLine.appendChild(PricingReference);
			
					// cac:AlternativeConditionPrice
					Element AlternativeConditionPrice = document.createElement("cac:AlternativeConditionPrice");
					PricingReference.appendChild(AlternativeConditionPrice);
				
						// cbc:PriceAmount
						Element PriceAmount = document.createElement("cbc:PriceAmount");
						double _price_temp=round(myDetalle[linea].get_valor_unit()*myDetalle[linea].get_cantidad(),2);
						PriceAmount.appendChild(document.createTextNode(""+_price_temp));
						AlternativeConditionPrice.appendChild(PriceAmount);
				
							// currencyID_ALTER
							Attr attr_currencyID_ITEM_ALTER = document.createAttribute("currencyID");
							attr_currencyID_ITEM_ALTER.setValue(myCabecera_nc.get_moneda());
							PriceAmount.setAttributeNode(attr_currencyID_ITEM_ALTER);	
						
						// cbc:PriceTypeCode
						Element PriceTypeCode = document.createElement("cbc:PriceTypeCode");
						PriceTypeCode.appendChild(document.createTextNode("01"));
						AlternativeConditionPrice.appendChild(PriceTypeCode);	
			
					// cac:AllowanceCharge
				//	Element AllowanceCharge = document.createElement("cac:AllowanceCharge");
				//	CreditNoteLine.appendChild(AllowanceCharge);
			
					// cbc:ID
				//	Element ID_AllowanceCharge = document.createElement("cbc:ID");
				//	ID_AllowanceCharge.appendChild(document.createTextNode("2005"));
				//	AllowanceCharge.appendChild(ID_AllowanceCharge);
			
					// cbc:ChargeIndicator
				//	Element ChargeIndicator = document.createElement("cbc:ChargeIndicator");
				//	ChargeIndicator.appendChild(document.createTextNode("false"));
				//	AllowanceCharge.appendChild(ChargeIndicator);
			
					// cbc:Amount
				//	Element Amount_AllowanceCharge = document.createElement("cbc:Amount");
				//	Amount_AllowanceCharge.appendChild(document.createTextNode("0.00"));
				//	AllowanceCharge.appendChild(Amount_AllowanceCharge);
			
						// currencyID
				//		Attr attr_currencyAllowanceCharge = document.createAttribute("currencyID");
				//		attr_currencyAllowanceCharge.setValue("PEN");
				//		Amount_AllowanceCharge.setAttributeNode(attr_currencyAllowanceCharge);		
			
					/////////////////////////////
					// cac:TaxTotal  IGV
					Element Invoiced_TaxTotal = document.createElement("cac:TaxTotal");
					CreditNoteLine.appendChild(Invoiced_TaxTotal);			
					
					// cbc:TaxAmount
					Element TaxAmount3 = document.createElement("cbc:TaxAmount");
					TaxAmount3.appendChild(document.createTextNode(""+myDetalle[linea].get_igv_unit()));
					Invoiced_TaxTotal.appendChild(TaxAmount3);	
				
						// currencyID
						Attr attr_currencyTaxAmount3 = document.createAttribute("currencyID");
						attr_currencyTaxAmount3.setValue(myCabecera_nc.get_moneda());
						TaxAmount3.setAttributeNode(attr_currencyTaxAmount3);		
	

					
						
					// cac:TaxSubtotal
					Element TaxSubtotal2 = document.createElement("cac:TaxSubtotal");
					Invoiced_TaxTotal.appendChild(TaxSubtotal2);	
			
					// cbc:TaxAmount 
					Element TaxableAmount = document.createElement("cbc:TaxAmount");
					TaxableAmount.appendChild(document.createTextNode(""+myDetalle[linea].get_igv_unit()));
					TaxSubtotal2.appendChild(TaxableAmount);	
				
						// currencyID
						Attr attr_TaxableAmount = document.createAttribute("currencyID");
						attr_TaxableAmount.setValue(myCabecera_nc.get_moneda());
						TaxableAmount.setAttributeNode(attr_TaxableAmount);	
				
				
					// cbc:TaxAmount
				//	Element TaxAmount4 = document.createElement("cbc:TaxAmount");
				//	TaxAmount4.appendChild(document.createTextNode(""+myDetalle[linea].get_igv_unit()));
				//	TaxSubtotal2.appendChild(TaxAmount4);	
					
					
				
						// currencyID
				//		Attr attr_TaxAmount4 = document.createAttribute("currencyID");
				//		attr_TaxAmount4.setValue("PEN");
				//		TaxAmount4.setAttributeNode(attr_TaxAmount4);
					
				
				
			
					// cac:TaxCategory	
					Element TaxCategory2= document.createElement("cac:TaxCategory");
					TaxSubtotal2.appendChild(TaxCategory2);	
				
						// cbc:TaxExemptionReasonCode
						Element TaxExemptionReasonCode= document.createElement("cbc:TaxExemptionReasonCode");
						TaxExemptionReasonCode.appendChild(document.createTextNode(myDetalle[linea].get_afec_igv()));
						TaxCategory2.appendChild(TaxExemptionReasonCode);	
						
						// cac:TaxScheme
						Element TaxScheme2= document.createElement("cac:TaxScheme");
						TaxCategory2.appendChild(TaxScheme2);	
						
							// cbc:ID
							Element ID_Tax= document.createElement("cbc:ID");
							ID_Tax.appendChild(document.createTextNode("1000"));
							TaxScheme2.appendChild(ID_Tax);	
							
							// cbc:Name
							Element Name_Tax= document.createElement("cbc:Name");
							Name_Tax.appendChild(document.createTextNode("IGV"));
							TaxScheme2.appendChild(Name_Tax);	
							
							
							// cbc:TaxTypeCode
							Element TaxTypeCode2= document.createElement("cbc:TaxTypeCode");
							TaxTypeCode2.appendChild(document.createTextNode("VAT"));
							TaxScheme2.appendChild(TaxTypeCode2);	
						
				/// TERMINA PARA IGV		
									

							
							
							
										
							
							/////////////////////////////							
				
				// cac:Item
				Element Item = document.createElement("cac:Item");
				CreditNoteLine.appendChild(Item);				
				
			
					// <cbc:Description>
					Element Item_Description = document.createElement("cbc:Description");
					Item_Description.appendChild(document.createTextNode(myDetalle[linea].get_descrip()));
					Item.appendChild(Item_Description);	
				
					// cac:SellersItemIdentification
					Element SellersItemIdentification = document.createElement("cac:SellersItemIdentification");
					Item.appendChild(SellersItemIdentification);
				
						// cbc:ID
						Element Item_ID = document.createElement("cbc:ID");
						Item_ID.appendChild(document.createTextNode(myDetalle[linea].get_producto()));
						SellersItemIdentification.appendChild(Item_ID);	
					
					
					// cac:AdditionalItemIdentification
					Element AdditionalItemIdentification = document.createElement("cac:AdditionalItemIdentification");
					Item.appendChild(AdditionalItemIdentification);
					
						// cbc:ID
						Element Addi_Item_ID = document.createElement("cbc:ID");
						Addi_Item_ID.appendChild(document.createTextNode(""));
						AdditionalItemIdentification.appendChild(Addi_Item_ID);	
						
					
					// cac:Price
					Element Price = document.createElement("cac:Price");
					CreditNoteLine.appendChild(Price);
				
						// cbc:PriceAmount
						Element Item_PriceAmount = document.createElement("cbc:PriceAmount");
						Item_PriceAmount.appendChild(document.createTextNode(""+myDetalle[linea].get_valor_unit()));
						Price.appendChild(Item_PriceAmount);
					
				
							// currencyID
							Attr attr_PriceAmount = document.createAttribute("currencyID");
							attr_PriceAmount.setValue(myCabecera_nc.get_moneda());
							Item_PriceAmount.setAttributeNode(attr_PriceAmount);
							
							
							
						
				}
		
		
				
					
				
				
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		DOMSource source = new DOMSource(document);
		
		StreamResult streamResult = new StreamResult(new File($FILE_NAME_XML));
		
		transformer.transform(source, streamResult);
		
		
		
	}

	
	
	
}