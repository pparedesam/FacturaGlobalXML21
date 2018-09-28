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

 
public class crearXML_bj2 {
 
	
	public static String[] myArrayCab = new String[1];
	public static int _counterCab=1;
	
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

	
	
	
	
	// declaracion de objeto tipo cabecera
	public static cabecera_bj myCabecera_bj = new cabecera_bj();
	public static detalle_bj[] myDetalle_bj = new detalle_bj[100];
			
	//String $PATH_XMLS_SIN_FIRMA
	
	public static void c_XML(DataHandler dh_det, DataHandler dh_cab, String $FILE_NAME, parametros misParametros) throws Exception  {
		
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
		
	
		
		
		
		//$PATH_XMLS_SIN_FIRMA = "r:\\WSconector\\02_xmls_sin_firma\\";
	//	$PATH_SIN_FIRMA=".\\data\\20525937195\\02_xmls_sin_firma\\";
	//	$PATH_CON_FIRMA= ".\\data\\20525937195\\03_xmls_con_firma\\";
	//	$PATH_ARCHIVOS_PLANOS=".\\data\\20525937195\\01_archivos_planos\\";
	//	//$FILE_NAME = "20525719953-01-FF11-00000080";
	//	$FILE_NAME_XML = $PATH_SIN_FIRMA+$FILE_NAME+".xml";
		
		$RUC=$FILE_NAME.substring(0,11);
		//$SERIE=$FILE_NAME.substring(18,20);
		$SERIE="RA";
		int _tam = $FILE_NAME.length(); 
		$NUMERO=$FILE_NAME.substring(15,_tam);
		
			
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
        		        		
        		if (_num==1) {myCabecera_bj.set_fecha(_cadena);}
            	if (_num==2) {myCabecera_bj.set_fecha_hoy(_cadena);}
            	if (_num==3) {myCabecera_bj.set_tipo_doc(_cadena);}
            	if (_num==4) {myCabecera_bj.set_serie(_cadena);}
            	if (_num==5) {myCabecera_bj.set_motivo(_cadena);}
            	_cadena="";
            	
        	}
        }
        
        System.out.println("Cabecera-> Serie que se da Baja:        "+myCabecera_bj.get_serie());
        System.out.println("Cabecera-> Fecha de Emision:            "+myCabecera_bj.get_fecha());
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
	    	myDetalle_bj[i] = new detalle_bj();
	        _cadena="";
	        _num=0;
	        	
	            
	        _tam_detalle = myArrayDet[i].length();
	        for(int x=0; x<_tam_detalle; x++) {
	           	_car = myArrayDet[i].substring(x,x+1);
	            	      	
	           	if (!"|".equals(_car)) {
	           		_cadena=_cadena+_car;
	        	} else {
	        		_num++;
	            		
	        		//if (_num==1) {myDetalle.set_fecha(_cadena);}
	            	//if (_num==2) {myCabecera_bj.set_fecha_hoy(_cadena);}
	            	//if (_num==3) {myCabecera_bj.set_tipo_doc(_cadena);}
	            	//if (_num==4) {myCabecera_bj.set_serie(_cadena);}
	            	//if (_num==5) {myCabecera_bj.set_motivo(_cadena);}
	        		
	           		if (_num==1) {myDetalle_bj[i].set_fecha(_cadena);}
	           		if (_num==2) {myDetalle_bj[i].set_fecha_hoy(_cadena);}
	           		if (_num==3) {myDetalle_bj[i].set_tipo_doc(_cadena);}
	           		if (_num==4) {myDetalle_bj[i].set_serie(_cadena);}
	           		if (_num==5) {myDetalle_bj[i].set_motivo(_cadena);}
	           			
	               	_cadena="";
	        	}
	        }
	            
	        System.out.println(" ");
	        System.out.println("Detalle-> Fecha de la Baja:          "+myDetalle_bj[i].get_fecha());
	        System.out.println("Detalle-> Fecha del Docto:           "+myDetalle_bj[i].get_fecha_hoy());
	        System.out.println("Detalle-> Tipo del Doscuento:        "+myDetalle_bj[i].get_tipo_doc());
	        System.out.println("Detalle-> Codigo Serie:              "+myDetalle_bj[i].get_serie());
	        System.out.println("Detalle-> Codigo Motivo:             "+myDetalle_bj[i].get_motivo());
	           
	        //$SUM_IGV=$SUM_IGV+myDetalle_bj[i].get_igv_unit();
	        
	        //$SUM_IGV=round($SUM_IGV,2);
	        
	    	}
				
		
	    
	    writeXML($FILE_NAME);
	    
	    System.out.println("Archivo Generado...: "+$FILE_NAME_XML);
	    
	    Firma_Documento.firmar($PATH_SIN_FIRMA,$PATH_CON_FIRMA,$FILE_NAME,misParametros);
	    H_main_bj.conectar($FILE_NAME,myCabecera_bj.get_tipo_doc(),myCabecera_bj.get_serie(),misParametros);
		
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
		Element element = document.createElement("VoidedDocuments");
		document.appendChild(element);
		
		
		
	
		// creamos los atributos de la cabecera
		
		Attr attr_xmlns = document.createAttribute("xmlns");
		attr_xmlns.setValue("urn:sunat:names:specification:ubl:peru:schema:xsd:VoidedDocuments-1");
		element.setAttributeNode(attr_xmlns);
		
		Attr attr_xmlns_cac = document.createAttribute("xmlns:cac");
		attr_xmlns_cac.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
		element.setAttributeNode(attr_xmlns_cac);
		
		Attr attr_xmlns_cbc = document.createAttribute("xmlns:cbc");
		attr_xmlns_cbc.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
		element.setAttributeNode(attr_xmlns_cbc);
		
	//	Attr attr_xmlns_ccts = document.createAttribute("xmlns:ccts");
	//	attr_xmlns_ccts.setValue("urn:un:unece:uncefact:documentation:2");
	//	element.setAttributeNode(attr_xmlns_ccts);
		
		Attr attr_xmlns_ds = document.createAttribute("xmlns:ds");
		attr_xmlns_ds.setValue("http://www.w3.org/2000/09/xmldsig#");
		element.setAttributeNode(attr_xmlns_ds);
		
		Attr attr_xmlns_ext = document.createAttribute("xmlns:ext");
		attr_xmlns_ext.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
		element.setAttributeNode(attr_xmlns_ext);
		
	//	Attr attr_xmlns_qdt = document.createAttribute("xmlns:qdt");
	//	attr_xmlns_qdt.setValue("urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
	//	element.setAttributeNode(attr_xmlns_qdt);
		
		Attr attr_xmlns_sac = document.createAttribute("xmlns:sac");
		attr_xmlns_sac.setValue("urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
		element.setAttributeNode(attr_xmlns_sac);
		
				
	//	Attr attr_xmlns_udt = document.createAttribute("xmlns:udt");
	//	attr_xmlns_udt.setValue("urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
	//	element.setAttributeNode(attr_xmlns_udt);
		
		
		Attr attr_xmlns_xsi = document.createAttribute("xmlns:xsi");
		attr_xmlns_xsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
		element.setAttributeNode(attr_xmlns_xsi);
		
		
		
		/// NODO NUMERO 2  FIRMA DIGITAL  -- /Invoice/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/ds:Signature /Invoice/cac:Signature 
		
	/// NODO UBLExtensions
		
		Element UBLExtensions = document.createElement("ext:UBLExtensions");
		element.appendChild(UBLExtensions);
		
			/// NODO UBLExtensio
		//	Element UBLExtension = document.createElement("ext:UBLExtension");
		//	UBLExtensions.appendChild(UBLExtension);
		
				/// NODO ext:ExtensionContent
		//		Element ExtensionContent = document.createElement("ext:ExtensionContent");
		//		UBLExtension.appendChild(ExtensionContent);
	

		
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
		//cbc:ReferenceDate					
		Element ReferenceDate = document.createElement("cbc:ReferenceDate");
		ReferenceDate.appendChild(document.createTextNode(myCabecera_bj.get_fecha()));
		element.appendChild(ReferenceDate);
		
		
									
		///////////////////////////////////////////////// FIN NUMERO 1 -- fecEmision	
		
		
		/// NODO NUMERO 7  TIPO DEL DOCUMENTO -- (Factura)    /Invoice/cbc:InvoiceTypeCode  
		 
		// cbc:IssueDate			
		Element IssueDate = document.createElement("cbc:IssueDate");
		IssueDate.appendChild(document.createTextNode(myCabecera_bj.get_fecha_hoy()));
		element.appendChild(IssueDate);
									
		///////////////////////////////////////////////// FIN NUMERO 7 -- TIPO DE DOCUMENTO
		
		
		
		//cbc:DocumentCurrencyCode
	//	Element DocumentCurrencyCode = document.createElement("cbc:DocumentCurrencyCode");
	//	DocumentCurrencyCode.appendChild(document.createTextNode(myCabecera.get_moneda()));
	//	element.appendChild(DocumentCurrencyCode);
		
							
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
	//			Element PartyName = document.createElement("cac:PartyName");
	//			Party3.appendChild(PartyName);
					
					// Name
	//				Element Name = document.createElement("cbc:Name");
	//				Name.appendChild(document.createTextNode($RAZON_SOCIAL));
	//				PartyName.appendChild(Name);
			
				// cac:PostalAddress
	//			Element PostalAddress = document.createElement("cac:PostalAddress");
	//			Party3.appendChild(PostalAddress);	
					
					// cbc:ID
	//				Element id_postal = document.createElement("cbc:ID");
	//				id_postal.appendChild(document.createTextNode($CODIGO_POSTAL));
	//				PostalAddress.appendChild(id_postal);
					
					// cbc:StreetName
	//				Element StreetName = document.createElement("cbc:StreetName");
	//				StreetName.appendChild(document.createTextNode($NOMBRE_CALLE));
	//				PostalAddress.appendChild(StreetName);
					
					// cbc:CityName
	//				Element CityName = document.createElement("cbc:CityName");
	//				CityName.appendChild(document.createTextNode($NOMBRE_CIUDAD));
	//				PostalAddress.appendChild(CityName);
					
					// cbc:CountrySubentity
	//				Element CountrySubentity = document.createElement("cbc:CountrySubentity");
	//				CountrySubentity.appendChild(document.createTextNode("CountrySubentity"));
	//				PostalAddress.appendChild(CountrySubentity);
					
					// cbc:District
					// Element District = document.createElement("cbc:District");
					// District.appendChild(document.createTextNode("Distroto"));
					// PostalAddress.appendChild(District);
					
					// cac:Country
	//				Element Country = document.createElement("cac:Country");
	//				PostalAddress.appendChild(Country);
					
						// cbc:IdentificationCode
		//				Element pais = document.createElement("cbc:IdentificationCode");
		//				pais.appendChild(document.createTextNode($PAIS));
		//				Country.appendChild(pais);
						
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
		
	//	Element AccountingCustomerParty = document.createElement("cac:AccountingCustomerParty");
	//	element.appendChild(AccountingCustomerParty);
		
			// cbc:CustomerAssignedAccountID
	//		Element CustomerAssignedAccountID2 = document.createElement("cbc:CustomerAssignedAccountID");
	//		CustomerAssignedAccountID2.appendChild(document.createTextNode(myCabecera.get_num_ident()));
	//		AccountingCustomerParty.appendChild(CustomerAssignedAccountID2);
			
		    // cbc:AdditionalAccountID
	//		Element AdditionalAccountID2 = document.createElement("cbc:AdditionalAccountID");
	//		AdditionalAccountID2.appendChild(document.createTextNode(myCabecera.get_ident()));
	//		AccountingCustomerParty.appendChild(AdditionalAccountID2);
			
			// cac:Party
	//		Element Party = document.createElement("cac:Party");
	//		AccountingCustomerParty.appendChild(Party);
			
			
				// cac:PartyLegalEntity
	//			Element PartyLegalEntity2 = document.createElement("cac:PartyLegalEntity");
	//			Party.appendChild(PartyLegalEntity2);
				
					// cbc:RegistrationName
	//				Element RegistrationName2 = document.createElement("cbc:RegistrationName");
	//				RegistrationName2.appendChild(document.createTextNode(myCabecera.get_nombre()));
	//				PartyLegalEntity2.appendChild(RegistrationName2);
					
				
				
			
			
		
		
		
											
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
//					AddressTypeCode.appendChild(document.createTextNode("0"));
	//				PostalAddress2.appendChild(AddressTypeCode);
					
					
		////////////////////////////////////////////////////////////////////
					
					
		// cac:TaxTotal total de impuestos
	//	Element TaxTotal = document.createElement("cac:TaxTotal");
	//	element.appendChild(TaxTotal);
			
			// cbc:TaxAmount
	//		Element TaxAmount = document.createElement("cbc:TaxAmount");
	//		TaxAmount.appendChild(document.createTextNode(""+$SUM_IGV));
			
	//		TaxTotal.appendChild(TaxAmount);
			
				// currencyID
	//			Attr attr_currencyID = document.createAttribute("currencyID");
	//			attr_currencyID.setValue("PEN");
	//			TaxAmount.setAttributeNode(attr_currencyID);
				
			// cac:TaxSubtotal
		//	Element TaxSubtotal = document.createElement("cac:TaxSubtotal");
		//	TaxTotal.appendChild(TaxSubtotal);
			
				// cbc:TaxAmount
		//		Element TaxAmount2 = document.createElement("cbc:TaxAmount");
		//		TaxAmount2.appendChild(document.createTextNode(""+$SUM_IGV));
		//		TaxSubtotal.appendChild(TaxAmount2);
				
					// currencyID
		//			Attr attr_currencyID2 = document.createAttribute("currencyID");
		//			attr_currencyID2.setValue("PEN");
		//			TaxAmount2.setAttributeNode(attr_currencyID2);
					
				// cac:TaxCategory
		//		Element TaxCategory = document.createElement("cac:TaxCategory");
		//		TaxSubtotal.appendChild(TaxCategory);
				
					// cac:TaxScheme
		//			Element TaxScheme = document.createElement("cac:TaxScheme");
		//			TaxCategory.appendChild(TaxScheme);
						
						// cbc:ID
		//				Element ID = document.createElement("cbc:ID");
		//				ID.appendChild(document.createTextNode("1000"));
		//				TaxScheme.appendChild(ID);
						
						// cbc:Name
		//				Element Name_igv = document.createElement("cbc:Name");
		//				Name_igv.appendChild(document.createTextNode("IGV"));
		//				TaxScheme.appendChild(Name_igv);
						
						// cbc:TaxTypeCode
		//				Element TaxTypeCode = document.createElement("cbc:TaxTypeCode");
		//				TaxTypeCode.appendChild(document.createTextNode("VAT"));
		//				TaxScheme.appendChild(TaxTypeCode);
				
						
		////////////////////////////////////////////////////////////////////
					
		
						
						
		/// cac:LegalMonetaryTotal
//		Element LegalMonetaryTotal = document.createElement("cac:LegalMonetaryTotal");
//		element.appendChild(LegalMonetaryTotal);				
						
			// cbc:AllowanceTotalAmount	
	//		Element AllowanceTotalAmount = document.createElement("cbc:AllowanceTotalAmount");
	//		AllowanceTotalAmount.appendChild(document.createTextNode("0.00"));
	//		LegalMonetaryTotal.appendChild(AllowanceTotalAmount);	
			
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
		//	Element PayableAmount = document.createElement("cbc:PayableAmount");
		//	PayableAmount.appendChild(document.createTextNode(""+myCabecera.get_importe_tot()));
		//	LegalMonetaryTotal.appendChild(PayableAmount);	
			
				// currencyID
		//		Attr attr_currencyID5 = document.createAttribute("currencyID");
		//		attr_currencyID5.setValue("PEN");
		//		PayableAmount.setAttributeNode(attr_currencyID5);
			
			
		

		for (int linea=1; linea<_counterDet; linea++) {	
	        System.out.println(" "+linea);
			// cac:InvoiceLine
			Element VoidedDocumentsLine = document.createElement("sac:VoidedDocumentsLine");
			element.appendChild(VoidedDocumentsLine);		
			
				// cbc:LineID
				Element ID_Item = document.createElement("cbc:LineID");
				ID_Item.appendChild(document.createTextNode(""+linea));
				VoidedDocumentsLine.appendChild(ID_Item);		
				
				// cbc:DocumentTypeCode
				Element DocumentTypeCode = document.createElement("cbc:DocumentTypeCode");
				DocumentTypeCode.appendChild(document.createTextNode(myDetalle_bj[linea].get_tipo_doc()));
				VoidedDocumentsLine.appendChild(DocumentTypeCode);
				
				
				System.out.println(" "+myDetalle_bj[linea].get_serie());
				
				String Serie =myDetalle_bj[linea].get_serie().substring(0,4);
				
				
				
				int _tam = myDetalle_bj[linea].get_serie().length();
				
				int numero_serie = Integer.parseInt(myDetalle_bj[linea].get_serie().substring(5,_tam));
				//String Serie ="FF11";						
				//String Numero ="177";
				
				
				//sac:DocumentSerialID			
				Element DocumentSerialID = document.createElement("sac:DocumentSerialID");
				DocumentSerialID.appendChild(document.createTextNode(Serie));
				VoidedDocumentsLine.appendChild(DocumentSerialID);	
				
				
				
				
				//sac:DocumentNumberID			
		    	Element DocumentNumberID = document.createElement("sac:DocumentNumberID");
				DocumentNumberID.appendChild(document.createTextNode(""+numero_serie));
				VoidedDocumentsLine.appendChild(DocumentNumberID);				
				
				//sac:VoidReasonDescription
				Element VoidReasonDescription = document.createElement("sac:VoidReasonDescription");
				VoidReasonDescription.appendChild(document.createTextNode(myDetalle_bj[linea].get_motivo()));
				VoidedDocumentsLine.appendChild(VoidReasonDescription);	
				
										
						
				}
		
		
				
					
				
				
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		DOMSource source = new DOMSource(document);
		
		StreamResult streamResult = new StreamResult(new File($FILE_NAME_XML));
		
		transformer.transform(source, streamResult);
		
		
		
	}

	
	
	
}