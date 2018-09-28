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

// para documentos de facturas gratuitas.
public class crearXML_gr {
 
	public static String[] myArrayCab = new String[1];
	public static int _counterCab=1;
	
	public static String[] myArrayCab_Aca = new String[1];
	public static int _counterCab_aca=1;
	
	
	public static String[] myArrayCab_ley = new String[1];
	public static int _counterCab_ley=1;
	
	public static String[] myArrayDet = new String[100];
	public static int _counterDet=1; 
	
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
			
	//String $PATH_XMLS_SIN_FIRMA
	


	
	public static void c_XML(DataHandler dh_det, DataHandler dh_cab, DataHandler dh_aca, DataHandler dh_ley, String $FILE_NAME, parametros misParametros) throws Exception  {
	//public static void c_XML(DataHandler dh_det, DataHandler dh_cab, String $FILE_NAME) throws Exception  {
		
		
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
        		        		
        		if (_num==1) {myCabecera.set_tipo_op(_cadena);}
            	if (_num==2) {myCabecera.set_fecha(_cadena);}
            	if (_num==3) {myCabecera.set_dom_fiscal(_cadena);}
            	if (_num==4) {myCabecera.set_ident(_cadena);}
            	if (_num==5) {myCabecera.set_num_ident (_cadena);}
            	if (_num==6) {myCabecera.set_nombre (_cadena);}
            	if (_num==7) {myCabecera.set_moneda (_cadena);}
            	if (_num==8) {myCabecera.set_desc_glo (Double.parseDouble(_cadena));}
           // 	if (_num==9) {myCabecera.set_sum_cargos (Double.parseDouble(_cadena));}
           // 	if (_num==10) {myCabecera.set_tot_desc (Double.parseDouble(_cadena));}
          //  	if (_num==11) {myCabecera.set_tot_vta_gra (Double.parseDouble(_cadena));}
          //  	if (_num==12) {myCabecera.set_tot_vta_in (Double.parseDouble(_cadena));}
            	if (_num==13) {myCabecera.set_tot_vta_exo (Double.parseDouble(_cadena));}
         //   	if (_num==14) {myCabecera.set_sum_igv (Double.parseDouble(_cadena));}
          //  	if (_num==15) {myCabecera.set_sum_isc (Double.parseDouble(_cadena));}
           // 	if (_num==16) {myCabecera.set_sum_otros (Double.parseDouble(_cadena));}
           // 	if (_num==17) {myCabecera.set_importe_tot (Double.parseDouble(_cadena));}
            	_cadena="";
            	
        	}
        }
        
        System.out.println("Cabecera-> Tipo de Operacion:        "+myCabecera.get_tipo_op());
        System.out.println("Cabecera-> Fecha de Emision:         "+myCabecera.get_fecha());
    //    System.out.println("Cabecera-> Tipo de Dom Fiscal:       "+myCabecera.get_dom_fiscal());
    //    System.out.println("Cabecera-> Tipo de Identificacion:   "+myCabecera.get_ident());
    //    System.out.println("Cabecera-> Numero de Identidad:      "+myCabecera.get_num_ident());
    //    System.out.println("Cabecera-> Nombre:                   "+myCabecera.get_nombre());
    //    System.out.println("Cabecera-> Moneda:                   "+myCabecera.get_moneda());
    //    System.out.println("Cabecera-> Descuento Global:         "+myCabecera.get_desc_glo());
    //    System.out.println("Cabecera-> Suma de Cargos:           "+myCabecera.get_sum_cargos());
    //    System.out.println("Cabecera-> Total Descuentos:         "+myCabecera.get_tot_desc());
    //    System.out.println("Cabecera-> Total Vta Gravada:        "+myCabecera.get_tot_vta_gra());
    //    System.out.println("Cabecera-> Total Vta Inafectadas:    "+myCabecera.get_tot_vta_in());
    //    System.out.println("Cabecera-> Total Vta Exonerada:      "+myCabecera.get_tot_vta_exo());
    //    System.out.println("Cabecera-> Total Suma IGV:           "+myCabecera.get_sum_igv());
    //    System.out.println("Cabecera-> Total Suma ISC:           "+myCabecera.get_sum_isc());
    //    System.out.println("Cabecera-> Total Suma otros:         "+myCabecera.get_sum_otros());
    //    System.out.println("Cabecera-> Importe Total:            "+myCabecera.get_importe_tot());
		
        
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

	    Firma_Documento.firmar($PATH_SIN_FIRMA,$PATH_CON_FIRMA,$FILE_NAME,misParametros);
	    H_main.conectar($FILE_NAME,misParametros);
	
	    
	//    Firma_Documento.firmar($PATH_SIN_FIRMA,$PATH_FIRMADOS,$FILE_NAME);
	//    H_main.conectar($FILE_NAME);
		
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
		Element element = document.createElement("Invoice");
		document.appendChild(element);
		
		
		// creamos los atributos de la cabecera
	
		Attr attr_xmlns = document.createAttribute("xmlns");
		attr_xmlns.setValue("urn:oasis:names:specification:ubl:schema:xsd:Invoice-2");
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
						
					// sac:AdditionalMonetaryTotal DESCUENTOS
			//		Element AdditionalMonetaryTotal_DESC = document.createElement("sac:AdditionalMonetaryTotal");
			//		AdditionalInformation.appendChild(AdditionalMonetaryTotal_DESC);
					
						// cbc:ID  VENTAS DESCUENTOS
					//	Element ID_UBL_DESC = document.createElement("cbc:ID");
					//	ID_UBL_DESC.appendChild(document.createTextNode("2005"));
					//		AdditionalMonetaryTotal_DESC.appendChild(ID_UBL_DESC);
				
						// cbc:PayableAmount
			//			Element PayableAmount_UBL_DESC = document.createElement("cbc:PayableAmount");
			//			PayableAmount_UBL_DESC.appendChild(document.createTextNode(""+myCabecera.get_desc_glo()));
			//			AdditionalMonetaryTotal_DESC.appendChild(PayableAmount_UBL_DESC);
						
						// currencyID
			//			Attr currencyID_UBL_DESC = document.createAttribute("currencyID");
			//			currencyID_UBL_DESC.setValue("PEN");
			//			PayableAmount_UBL_DESC.setAttributeNode(currencyID_UBL_DESC);
						
						
					
					
		
					
					// sac:AdditionalMonetaryTotal al parecer es para lo gratis
					Element AdditionalMonetaryTotal_gr = document.createElement("sac:AdditionalMonetaryTotal");
					AdditionalInformation.appendChild(AdditionalMonetaryTotal_gr);
					
						// cbc:ID  VENTAS GRAVADAS
						Element ID_UBL_gr = document.createElement("cbc:ID");
						ID_UBL_gr.appendChild(document.createTextNode("1004"));
						AdditionalMonetaryTotal_gr.appendChild(ID_UBL_gr);
				
						// cbc:PayableAmount
						Element PayableAmount_UBL_gr = document.createElement("cbc:PayableAmount");
						PayableAmount_UBL_gr.appendChild(document.createTextNode(""+myCabecera.get_tot_vta_exo()));
						AdditionalMonetaryTotal_gr.appendChild(PayableAmount_UBL_gr);
						
						// currencyID
						Attr currencyID_UBL_gr = document.createAttribute("currencyID");
						currencyID_UBL_gr.setValue(myCabecera.get_moneda());
						PayableAmount_UBL_gr.setAttributeNode(currencyID_UBL_gr);
						
	
					
					
					
					
						// sac:AdditionalMonetaryTotal VENTAS GRAVADAS
						Element AdditionalMonetaryTotal = document.createElement("sac:AdditionalMonetaryTotal");
						AdditionalInformation.appendChild(AdditionalMonetaryTotal);
						
							// cbc:ID  VENTAS GRAVADAS
							Element ID_UBL = document.createElement("cbc:ID");
							ID_UBL.appendChild(document.createTextNode("1001"));
							AdditionalMonetaryTotal.appendChild(ID_UBL);
					
							// cbc:PayableAmount
							Element PayableAmount_UBL = document.createElement("cbc:PayableAmount");
							PayableAmount_UBL.appendChild(document.createTextNode("0.00"));
							AdditionalMonetaryTotal.appendChild(PayableAmount_UBL);
							
							// currencyID
							Attr currencyID_UBL = document.createAttribute("currencyID");
							currencyID_UBL.setValue(myCabecera.get_moneda());
							PayableAmount_UBL.setAttributeNode(currencyID_UBL);
							
							
							
						// sac:AdditionalMonetaryTotal VENTAS INAFECTADAS
			//			Element AdditionalMonetaryTotal_INAFE = document.createElement("sac:AdditionalMonetaryTotal");
			//			AdditionalInformation.appendChild(AdditionalMonetaryTotal_INAFE);	
							
							
							// cbc:ID  VENTAS INAFECTADAS
			//				Element ID_UBL_ID_UBL_INAFE = document.createElement("cbc:ID");
			//				ID_UBL_ID_UBL_INAFE.appendChild(document.createTextNode("1002"));
			//				AdditionalMonetaryTotal_INAFE.appendChild(ID_UBL_ID_UBL_INAFE);
					
							// cbc:PayableAmount
			//				Element PayableAmount_ID_VALUE = document.createElement("cbc:Value");
			//				PayableAmount_ID_VALUE.appendChild(document.createTextNode("Articulos Gratuitos"));
			//				AdditionalMonetaryTotal_INAFE.appendChild(PayableAmount_ID_VALUE);
							
							// currencyID
							//Attr currencyID_UBL_INAFE = document.createAttribute("currencyID");
							//currencyID_UBL_INAFE.setValue("PEN");
							//PayableAmount_UBL_INAFE.setAttributeNode(currencyID_UBL_INAFE);
							
								
							// sac:AdditionalMonetaryTotal VENTAS EXONERADAS
			//				Element AdditionalMonetaryTotal_EXONE = document.createElement("sac:AdditionalMonetaryTotal");
			//				AdditionalInformation.appendChild(AdditionalMonetaryTotal_EXONE);	
								
								
								// cbc:ID  VENTAS EXONERADAS
			//					Element ID_UBL_ID_UBL_EXONE = document.createElement("cbc:ID");
			//					ID_UBL_ID_UBL_EXONE.appendChild(document.createTextNode("1003"));
			//					AdditionalMonetaryTotal_EXONE.appendChild(ID_UBL_ID_UBL_EXONE);
						
								// cbc:PayableAmount
			//					Element PayableAmount_UBL_EXONE= document.createElement("cbc:PayableAmount");
			//					PayableAmount_UBL_EXONE.appendChild(document.createTextNode(""+myCabecera.get_tot_vta_exo()));
			//					AdditionalMonetaryTotal_EXONE.appendChild(PayableAmount_UBL_EXONE);
								
								// currencyID
			//					Attr currencyID_UBL_EXONE = document.createAttribute("currencyID");
			//					currencyID_UBL_EXONE.setValue("PEN");
			//					PayableAmount_UBL_EXONE.setAttributeNode(currencyID_UBL_EXONE);
								
							
								
								
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
						Element AdditionalProperty = document.createElement("sac:AdditionalProperty");
						AdditionalInformation.appendChild(AdditionalProperty);
							
							// cbc:ID
							Element ID_PROP = document.createElement("cbc:ID");
							ID_PROP.appendChild(document.createTextNode("1002"));
							AdditionalProperty.appendChild(ID_PROP);
							
							// cbc:Value
							Element Value_PROP = document.createElement("cbc:Value");
							Value_PROP.appendChild(document.createTextNode("Articulos Gratuitos"));
							AdditionalProperty.appendChild(Value_PROP);
							
				
							
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
		fecEmision.appendChild(document.createTextNode(myCabecera.get_fecha()));
		element.appendChild(fecEmision);
									
		///////////////////////////////////////////////// FIN NUMERO 1 -- fecEmision	
		
		
		/// NODO NUMERO 7  TIPO DEL DOCUMENTO -- (Factura)    /Invoice/cbc:InvoiceTypeCode  
		 
		// cbc:InvoiceTypeCode			
		Element InvoiceTypeCode = document.createElement("cbc:InvoiceTypeCode");
		InvoiceTypeCode.appendChild(document.createTextNode(myCabecera.get_tipo_op()));
		element.appendChild(InvoiceTypeCode);
									
		///////////////////////////////////////////////// FIN NUMERO 7 -- TIPO DE DOCUMENTO
		
		
		
		//cbc:DocumentCurrencyCode
		Element DocumentCurrencyCode = document.createElement("cbc:DocumentCurrencyCode");
		DocumentCurrencyCode.appendChild(document.createTextNode(myCabecera.get_moneda()));
		element.appendChild(DocumentCurrencyCode);
		
							
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
	
			
		
										
						
						
			
						
		///  NUMERO 3 -- Apellidos y nombres, denominación o razón social 
		/// -- /Invoice/cac:AccountingSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName
						//3 4 5 6 
						// Apellidos y nombres, denominación o razón social Nombre Comercial Domicilio fiscal Número de RUC 
						
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
							
					
		
		
		
		
					
		
		
		
		/// NODO NUMERO 9 Y 19 Tipo y número de documento de identidad del adquirente o usuario   
		 
		// cac:AccountingCustomerParty
		
		Element AccountingCustomerParty = document.createElement("cac:AccountingCustomerParty");
		element.appendChild(AccountingCustomerParty);
		
			// cbc:CustomerAssignedAccountID
			Element CustomerAssignedAccountID2 = document.createElement("cbc:CustomerAssignedAccountID");
			CustomerAssignedAccountID2.appendChild(document.createTextNode(myCabecera.get_num_ident()));
			AccountingCustomerParty.appendChild(CustomerAssignedAccountID2);
			
		    // cbc:AdditionalAccountID
			Element AdditionalAccountID2 = document.createElement("cbc:AdditionalAccountID");
			AdditionalAccountID2.appendChild(document.createTextNode(myCabecera.get_ident()));
			AccountingCustomerParty.appendChild(AdditionalAccountID2);
			
			// cac:Party
			Element Party = document.createElement("cac:Party");
			AccountingCustomerParty.appendChild(Party);
			
			
				// cac:PartyLegalEntity
				Element PartyLegalEntity2 = document.createElement("cac:PartyLegalEntity");
				Party.appendChild(PartyLegalEntity2);
				
					// cbc:RegistrationName
					Element RegistrationName2 = document.createElement("cbc:RegistrationName");
					RegistrationName2.appendChild(document.createTextNode(myCabecera.get_nombre()+" Direccion:"+myAca.get_desDireccionCliente()));
					PartyLegalEntity2.appendChild(RegistrationName2);
					
				
					
					
					
					
					///////
					
					// cac:PartyName
		//			Element PartyName_Cus = document.createElement("cac:PartyName");
		//			Party.appendChild(PartyName_Cus);
								
					// Name
		//			Element Name_Cus = document.createElement("cbc:Name");
		//			Name_Cus.appendChild(document.createTextNode(myCabecera.get_nombre()));
		//			PartyName_Cus.appendChild(Name_Cus);
							
						// cac:PostalAddress
		//				Element PostalAddress_Cus = document.createElement("cac:PostalAddress");
		//				Party.appendChild(PostalAddress_Cus);	
								
								// cbc:ID
		//						Element id_postal_Cus = document.createElement("cbc:ID");
		//						id_postal_Cus.appendChild(document.createTextNode(myAca.get_codUbigeoCliente()));
		//						PostalAddress_Cus.appendChild(id_postal_Cus);
								
								// cbc:StreetName
		//						Element StreetName_Cus = document.createElement("cbc:StreetName");
		//						StreetName_Cus.appendChild(document.createTextNode(myAca.get_desDireccionCliente()));
		//						PostalAddress_Cus.appendChild(StreetName_Cus);
								
								// cbc:CityName
		//						Element CityName_Cus = document.createElement("cbc:CityName");
			
		//						CityName_Cus.appendChild(document.createTextNode(myAca.get_guia()));
		//						PostalAddress_Cus.appendChild(CityName_Cus);
								
								// cbc:CountrySubentity
		//						Element CountrySubentity_Cus = document.createElement("cbc:CountrySubentity");
			
		//						CountrySubentity_Cus.appendChild(document.createTextNode(myAca.get_condiciones()));
		//						PostalAddress_Cus.appendChild(CountrySubentity_Cus);
								
								// cbc:District
								// Element District = document.createElement("cbc:District");
								// District.appendChild(document.createTextNode("Distroto"));
								// PostalAddress.appendChild(District);
								
								// cac:Country
		//						Element Country_Cus = document.createElement("cac:Country");
		//						PostalAddress_Cus.appendChild(Country_Cus);
								
								// cbc:IdentificationCode
		//							Element pais_Cus = document.createElement("cbc:IdentificationCode");
		//							pais_Cus.appendChild(document.createTextNode(myAca.get_codPaisCliente()));
		//							Country_Cus.appendChild(pais_Cus);
							
						
					
					
									// cac:PartyLegalEntity
			//						Element PartyLegalEntity2 = document.createElement("cac:PartyLegalEntity");
			//						Party.appendChild(PartyLegalEntity2);
									
										// cbc:RegistrationName
			//							Element RegistrationName2 = document.createElement("cbc:RegistrationName");
			//							RegistrationName2.appendChild(document.createTextNode(myCabecera.get_nombre()));
			//							PartyLegalEntity2.appendChild(RegistrationName2);
								
				
				
									
					
					//////
					
					
					
					
					
					
				
			
			
		
		
		
											
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
					
					
		// cac:TaxTotal total de impuestos
		Element TaxTotal = document.createElement("cac:TaxTotal");
		element.appendChild(TaxTotal);
			
			// cbc:TaxAmount
			Element TaxAmount = document.createElement("cbc:TaxAmount");
			TaxAmount.appendChild(document.createTextNode(""+$SUM_IGV));
			
			TaxTotal.appendChild(TaxAmount);
			
				// currencyID
				Attr attr_currencyID = document.createAttribute("currencyID");
				attr_currencyID.setValue(myCabecera.get_moneda());
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
					attr_currencyID2.setValue(myCabecera.get_moneda());
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
					
		
						
						
		/// cac:LegalMonetaryTotal
		Element LegalMonetaryTotal = document.createElement("cac:LegalMonetaryTotal");
		element.appendChild(LegalMonetaryTotal);				
						
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
			PayableAmount.appendChild(document.createTextNode("0.00"));
			LegalMonetaryTotal.appendChild(PayableAmount);	
			
				// currencyID
				Attr attr_currencyID5 = document.createAttribute("currencyID");
				attr_currencyID5.setValue(myCabecera.get_moneda());
				PayableAmount.setAttributeNode(attr_currencyID5);
			
			
		

		for (int linea=1; linea<_counterDet; linea++) {	
		
			// cac:InvoiceLine
			Element InvoiceLine = document.createElement("cac:InvoiceLine");
			element.appendChild(InvoiceLine);		
			
				// cbc:ID
				Element ID_Item = document.createElement("cbc:ID");
				ID_Item.appendChild(document.createTextNode(""+linea));
				InvoiceLine.appendChild(ID_Item);		
				
				// cbc:InvoicedQuantity
				Element InvoicedQuantity = document.createElement("cbc:InvoicedQuantity");
				InvoicedQuantity.appendChild(document.createTextNode(""+myDetalle[linea].get_cantidad()));
				InvoiceLine.appendChild(InvoicedQuantity);	
			
					// unitCode
					Attr attr_unitCode = document.createAttribute("unitCode");
					attr_unitCode.setValue(myDetalle[linea].get_unidad_med());
					InvoicedQuantity.setAttributeNode(attr_unitCode);					
		
			
					// cbc:LineExtensionAmount
					Element LineExtensionAmount = document.createElement("cbc:LineExtensionAmount");
					LineExtensionAmount.appendChild(document.createTextNode("0.00"));
					InvoiceLine.appendChild(LineExtensionAmount);		
			
					// currencyID
					Attr attr_currencyID_ITEM = document.createAttribute("currencyID");
					attr_currencyID_ITEM.setValue(myCabecera.get_moneda());
					LineExtensionAmount.setAttributeNode(attr_currencyID_ITEM);					
	
			
					// cac:PricingReference
					Element PricingReference = document.createElement("cac:PricingReference");
					InvoiceLine.appendChild(PricingReference);
			
					// cac:AlternativeConditionPrice
					Element AlternativeConditionPrice = document.createElement("cac:AlternativeConditionPrice");
					PricingReference.appendChild(AlternativeConditionPrice);
				
						// cbc:PriceAmount
						Element PriceAmount = document.createElement("cbc:PriceAmount");
						PriceAmount.appendChild(document.createTextNode("0.00"));
						AlternativeConditionPrice.appendChild(PriceAmount);
				
							// currencyID_ALTER
							Attr attr_currencyID_ITEM_ALTER = document.createAttribute("currencyID");
							attr_currencyID_ITEM_ALTER.setValue(myCabecera.get_moneda());
							PriceAmount.setAttributeNode(attr_currencyID_ITEM_ALTER);	
						
						// cbc:PriceTypeCode
						Element PriceTypeCode = document.createElement("cbc:PriceTypeCode");
						PriceTypeCode.appendChild(document.createTextNode("01"));
						AlternativeConditionPrice.appendChild(PriceTypeCode);	
			
						
						
						// cac:AlternativeConditionPrice
						Element AlternativeConditionPrice_gr = document.createElement("cac:AlternativeConditionPrice");
						PricingReference.appendChild(AlternativeConditionPrice_gr);
					
							// cbc:PriceAmount
							Element PriceAmount_gr = document.createElement("cbc:PriceAmount");
							PriceAmount_gr.appendChild(document.createTextNode(""+myDetalle[linea].get_valor_unit()));
							AlternativeConditionPrice_gr.appendChild(PriceAmount_gr);
					
								// currencyID_ALTER
								Attr attr_currencyID_ITEM_ALTER_gr = document.createAttribute("currencyID");
								attr_currencyID_ITEM_ALTER_gr.setValue(myCabecera.get_moneda());
								PriceAmount_gr.setAttributeNode(attr_currencyID_ITEM_ALTER_gr);	
							
							// cbc:PriceTypeCode
							Element PriceTypeCode_gr = document.createElement("cbc:PriceTypeCode");
							PriceTypeCode_gr.appendChild(document.createTextNode("02"));
							AlternativeConditionPrice_gr.appendChild(PriceTypeCode_gr);	
																
						
						
						
					// cac:AllowanceCharge
		//			Element AllowanceCharge = document.createElement("cac:AllowanceCharge");
		//			InvoiceLine.appendChild(AllowanceCharge);
			
					// cbc:ID
		//			Element ID_AllowanceCharge = document.createElement("cbc:ID");
		//			ID_AllowanceCharge.appendChild(document.createTextNode("2005"));
		//			AllowanceCharge.appendChild(ID_AllowanceCharge);
			
					// cbc:ChargeIndicator
		//			Element ChargeIndicator = document.createElement("cbc:ChargeIndicator");
		//			ChargeIndicator.appendChild(document.createTextNode("false"));
		//			AllowanceCharge.appendChild(ChargeIndicator);
			
					// cbc:Amount
		//			Element Amount_AllowanceCharge = document.createElement("cbc:Amount");
		//			Amount_AllowanceCharge.appendChild(document.createTextNode("0.00"));
		//			AllowanceCharge.appendChild(Amount_AllowanceCharge);
			
						// currencyID
		//				Attr attr_currencyAllowanceCharge = document.createAttribute("currencyID");
		//				attr_currencyAllowanceCharge.setValue("PEN");
		//				Amount_AllowanceCharge.setAttributeNode(attr_currencyAllowanceCharge);		
			
					
					// cac:TaxTotal
					Element Invoiced_TaxTotal = document.createElement("cac:TaxTotal");
					InvoiceLine.appendChild(Invoiced_TaxTotal);			
					
					// cbc:TaxAmount
					Element TaxAmount3 = document.createElement("cbc:TaxAmount");
					TaxAmount3.appendChild(document.createTextNode(""+myDetalle[linea].get_igv_unit()));
					Invoiced_TaxTotal.appendChild(TaxAmount3);	
				
						// currencyID
						Attr attr_currencyTaxAmount3 = document.createAttribute("currencyID");
						attr_currencyTaxAmount3.setValue(myCabecera.get_moneda());
						TaxAmount3.setAttributeNode(attr_currencyTaxAmount3);		
	
			
					// cac:TaxSubtotal
					Element TaxSubtotal2 = document.createElement("cac:TaxSubtotal");
					Invoiced_TaxTotal.appendChild(TaxSubtotal2);	
			
					// cbc:TaxableAmount
					Element TaxableAmount = document.createElement("cbc:TaxAmount");
					TaxableAmount.appendChild(document.createTextNode(""+myDetalle[linea].get_igv_unit()));
					TaxSubtotal2.appendChild(TaxableAmount);	
				
						// currencyID
						Attr attr_TaxableAmount = document.createAttribute("currencyID");
						attr_TaxableAmount.setValue(myCabecera.get_moneda());
						TaxableAmount.setAttributeNode(attr_TaxableAmount);	
				
				
					// cbc:TaxAmount
			//		Element TaxAmount4 = document.createElement("cbc:TaxAmount");
			//		TaxAmount4.appendChild(document.createTextNode(""+myDetalle[linea].get_igv_unit()));
			//		TaxSubtotal2.appendChild(TaxAmount4);	
					
					
				
						// currencyID
			//			Attr attr_TaxAmount4 = document.createAttribute("currencyID");
			//			attr_TaxAmount4.setValue("PEN");
			//			TaxAmount4.setAttributeNode(attr_TaxAmount4);
					
				
				
			
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
						
						
						
				
				// cac:Item
				Element Item = document.createElement("cac:Item");
				InvoiceLine.appendChild(Item);				
				
			
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
					InvoiceLine.appendChild(Price);
				
						// cbc:PriceAmount
						Element Item_PriceAmount = document.createElement("cbc:PriceAmount");
						Item_PriceAmount.appendChild(document.createTextNode("0.00"));
						Price.appendChild(Item_PriceAmount);
					
				
							// currencyID
							Attr attr_PriceAmount = document.createAttribute("currencyID");
							attr_PriceAmount.setValue(myCabecera.get_moneda());
							Item_PriceAmount.setAttributeNode(attr_PriceAmount);
							
							
							
						
				}
		
		
				
					
				
				
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		DOMSource source = new DOMSource(document);
		
		StreamResult streamResult = new StreamResult(new File($FILE_NAME_XML));
		
		transformer.transform(source, streamResult);
		
		
		
	}

	
	
	
}