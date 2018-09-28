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


public class crearXML_rd {


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






	// public static String 

	public static String $SERIE="";
	public static String $NUMERO="";
	public static String $TIPO_DOCUMENTO="";

	// declaracion de objeto tipo cabecera
	public static resumen myResumen = new resumen();
	public static resumen[] myDetalle = new resumen[100];

	//String $PATH_XMLS_SIN_FIRMA

	public static void c_XML(DataHandler dh_det, DataHandler dh_cab, String $FILE_NAME, parametros misParametros) throws Exception  {





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



		$FILE_NAME_XML = $PATH_SIN_FIRMA+$FILE_NAME+".xml";

		$RUC=$FILE_NAME.substring(0,11);

		int _tam = $FILE_NAME.length(); 
		$SERIE=$FILE_NAME.substring(12,_tam);
		$NUMERO=$FILE_NAME.substring(12,_tam);


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

				if (_num==1) {myResumen.set_ruc(_cadena);}
				if (_num==2) {myResumen.set_nombre_emp(_cadena);}
				if (_num==3) {myResumen.set_fecha_emision(_cadena);}
				if (_num==4) {myResumen.set_tipo_doc(_cadena);}
				if (_num==5) {myResumen.set_serie(_cadena);}
				if (_num==6) {myResumen.set_numero_inicio(_cadena);}
				if (_num==7) {myResumen.set_numero_final(_cadena);}
				if (_num==8) {myResumen.set_ventas_gravadas(Double.parseDouble(_cadena));}
				if (_num==9) {myResumen.set_ventas_exoneradas (Double.parseDouble(_cadena));}
				if (_num==10) {myResumen.set_ventas_inafectas(Double.parseDouble(_cadena));}
				if (_num==11) {myResumen.set_sumatoria_otros_cargos(Double.parseDouble(_cadena));}
				if (_num==12) {myResumen.set_sumatoria_igv (Double.parseDouble(_cadena));}
				if (_num==13) {myResumen.set_sumatoria_isc (Double.parseDouble(_cadena));}
				if (_num==14) {myResumen.set_importe_venta (Double.parseDouble(_cadena));}
				_cadena="";

			}
		}

		System.out.println("Cabecera-> Tipo de Operacion:        "+myResumen.get_tipo_doc());
		System.out.println("Cabecera-> Fecha de Emision:         "+myResumen.get_fecha_emision());
		System.out.println("Cabecera-> Serie:                    "+myResumen.get_serie());
		System.out.println("Cabecera-> Numero Inicio:            "+myResumen.get_numero_inicio());
		System.out.println("Cabecera-> Numero Final:             "+myResumen.get_numero_final());
		System.out.println("Cabecera-> Ventas Gravadas:          "+myResumen.get_ventas_gravadas());
		System.out.println("Cabecera-> Ventas Exoneradas:        "+myResumen.get_ventas_exoneradas());
		System.out.println("Cabecera-> Ventas Inafectas:         "+myResumen.get_ventas_inafectas());
		System.out.println("Cabecera-> Sumatoria de IGV:         "+myResumen.get_sumatoria_igv());
		System.out.println("Cabecera-> Sumatoria ISC:            "+myResumen.get_sumatoria_isc());
		System.out.println("Cabecera-> Importe de Venta:         "+myResumen.get_importe_venta());
		System.out.println("...");

		// leemos el arcivo plano detalle
		//readPlainTextDet($PATH_ARCHIVOS_PLANOS+$FILE_NAME+".det",dh_det);
		readPlainTextDet(dh_det);

		_counterDet=_counterDet-1;

		for(int i=1; i<_counterDet; i++){
			myDetalle[i] = new resumen();
			_cadena="";
			_num=0;


			_tam_detalle = myArrayDet[i].length();
			for(int x=0; x<_tam_detalle; x++) {
				_car = myArrayDet[i].substring(x,x+1);

				if (!"|".equals(_car)) {
					_cadena=_cadena+_car;
				} else {
					_num++;


					if (_num==1) {myDetalle[i].set_ruc(_cadena);}
					if (_num==2) {myDetalle[i].set_nombre_emp(_cadena);}
					if (_num==3) {myDetalle[i].set_fecha_emision(_cadena);}
					if (_num==4) {myDetalle[i].set_tipo_doc(_cadena);}
					if (_num==5) {myDetalle[i].set_serie(_cadena);}
					if (_num==6) {myDetalle[i].set_numero_inicio(_cadena);}
					if (_num==7) {myDetalle[i].set_numero_final(_cadena);}
					if (_num==8) {myDetalle[i].set_ventas_gravadas(Double.parseDouble(_cadena));}
					if (_num==9) {myDetalle[i].set_ventas_exoneradas (Double.parseDouble(_cadena));}
					if (_num==10) {myDetalle[i].set_ventas_inafectas(Double.parseDouble(_cadena));}
					if (_num==11) {myDetalle[i].set_sumatoria_otros_cargos(Double.parseDouble(_cadena));}
					if (_num==12) {myDetalle[i].set_sumatoria_igv (Double.parseDouble(_cadena));}
					if (_num==13) {myDetalle[i].set_sumatoria_isc (Double.parseDouble(_cadena));}
					if (_num==14) {myDetalle[i].set_importe_venta (Double.parseDouble(_cadena));}	  
					if (_num==15) {myDetalle[i].set_ruc_receptor(_cadena);}
					if (_num==16) {myDetalle[i].set_tipo_ident(_cadena);}	
					if (_num==17) {myDetalle[i].set_status(_cadena);}
					if (_num==18) {myDetalle[i].set_total(Double.parseDouble(_cadena));}
					if (_num==19) {myDetalle[i].set_moneda(_cadena);}
					if (_num==20) {myDetalle[i].set_serie_rel(_cadena);}
					if (_num==21) {myDetalle[i].set_folio_rel(_cadena);}	            	
					if (_num==22) {myDetalle[i].set_tipo_grabamen(_cadena);}	            	



					_cadena="";
				}
			}

			System.out.println("Detalle-> Tipo de Operacion:        "+myDetalle[i].get_tipo_doc());
			System.out.println("Detalle-> Fecha de Emision:         "+myDetalle[i].get_fecha_emision());
			System.out.println("Detalle-> Serie:                    "+myDetalle[i].get_serie());
			System.out.println("Detalle-> Numero Inicio:            "+myDetalle[i].get_numero_inicio());
			System.out.println("Detalle-> Numero Final:             "+myDetalle[i].get_numero_final());
			System.out.println("Detalle-> Ventas Gravadas:          "+myDetalle[i].get_ventas_gravadas());
			System.out.println("Detalle-> Ventas Exoneradas:        "+myDetalle[i].get_ventas_exoneradas());
			System.out.println("Detalle-> Ventas Inafectas:         "+myDetalle[i].get_ventas_inafectas());
			System.out.println("Detalle-> Sumatoria de IGV:         "+myDetalle[i].get_sumatoria_igv());
			System.out.println("Detalle-> Sumatoria ISC:            "+myDetalle[i].get_sumatoria_isc());
			System.out.println("Detalle-> Importe de Venta:         "+myDetalle[i].get_importe_venta());
			System.out.println("Detalle-> Ruc Emisor:               "+myDetalle[i].get_ruc_receptor());
			System.out.println("Detalle-> Tipo Ident:               "+myDetalle[i].get_tipo_ident());
			System.out.println("Detalle-> Status:                   "+myDetalle[i].get_status());
			System.out.println("Detalle-> Total:                    "+myDetalle[i].get_total());	
			System.out.println("Detalle-> Moneda:                   "+myDetalle[i].get_moneda());	        
			System.out.println("...");

			//    $SUM_IGV=$SUM_IGV+myDetalle[i].get_igv_unit();

			//    $SUM_IGV=round($SUM_IGV,2);

		}



		writeXML($FILE_NAME);
		System.out.println("Sumatoria IGV          : "+$SUM_IGV);
		System.out.println("Archivo Generado    : "+$FILE_NAME_XML);

		Firma_Documento.firmar($PATH_SIN_FIRMA,$PATH_CON_FIRMA,$FILE_NAME,misParametros);
		H_main_bj.conectar($FILE_NAME,"",myResumen.get_serie(),misParametros);
		//H_main_bj.conectar($FILE_NAME);

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
		Element element = document.createElement("SummaryDocuments");
		document.appendChild(element);


		// creamos los atributos de la cabecera

		Attr attr_xmlns = document.createAttribute("xmlns");
		attr_xmlns.setValue("urn:sunat:names:specification:ubl:peru:schema:xsd:SummaryDocuments-1");
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

		//		Attr attr_xmlns_qdt = document.createAttribute("xmlns:qdt");7
		//	    attr_xmlns_qdt.setValue("urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
		//		element.setAttributeNode(attr_xmlns_qdt);

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


		///////////////////////////////////////////////// FIN UBLVersionID

		// UBLVersionID
		Element UBLVersionID = document.createElement("cbc:UBLVersionID");
		UBLVersionID.appendChild(document.createTextNode("2.0"));
		element.appendChild(UBLVersionID);


		// cbc:CustomizationID
		Element CustomizationID = document.createElement("cbc:CustomizationID");
		CustomizationID.appendChild(document.createTextNode("1.1"));
		element.appendChild(CustomizationID);


		/// NODO NUMERO 8  SERIE + NUMERO CORRELATIVO -- (Factura)    /Invoice/cbc:InvoiceTypeCode  

		// cbc:ID			
		Element SERIE = document.createElement("cbc:ID");
		SERIE.appendChild(document.createTextNode($SERIE));
		element.appendChild(SERIE);

		///////////////////////////////////////////////// FIN NUMERO 7 -- TIPO DE DOCUMENTO

		/// NODO NUMERO 1  FECHA DE EMISION DEL DOCUMENTO -- fecEmision --- /Invoice/cbc:IssueDate 

		Element fecEmision = document.createElement("cbc:ReferenceDate");
		fecEmision.appendChild(document.createTextNode(myResumen.get_fecha_emision()));
		element.appendChild(fecEmision);

		Element fecEmision_issue = document.createElement("cbc:IssueDate");
		fecEmision_issue.appendChild(document.createTextNode(myResumen.get_fecha_emision()));
		element.appendChild(fecEmision_issue);

		///////////////////////////////////////////////// FIN NUMERO 1 -- fecEmision	


		/// NODO NUMERO 7  TIPO DEL DOCUMENTO -- (Factura)    /Invoice/cbc:InvoiceTypeCode  

		// cbc:InvoiceTypeCode			
		//	Element InvoiceTypeCode = document.createElement("cbc:InvoiceTypeCode");
		//	InvoiceTypeCode.appendChild(document.createTextNode(myCabecera.get_tipo_op()));
		//	element.appendChild(InvoiceTypeCode);

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
		//	Element PartyName = document.createElement("cac:PartyName");
		//	Party3.appendChild(PartyName);

		// Name
		//		Element Name = document.createElement("cbc:Name");
		//		Name.appendChild(document.createTextNode($RAZON_SOCIAL));
		//		PartyName.appendChild(Name);

		// cac:PostalAddress
		//	Element PostalAddress = document.createElement("cac:PostalAddress");
		//	Party3.appendChild(PostalAddress);	

		// cbc:ID
		//		Element id_postal = document.createElement("cbc:ID");
		//		id_postal.appendChild(document.createTextNode($CODIGO_POSTAL));
		//		PostalAddress.appendChild(id_postal);

		// cbc:StreetName
		//		Element StreetName = document.createElement("cbc:StreetName");
		//		StreetName.appendChild(document.createTextNode($NOMBRE_CALLE));
		//		PostalAddress.appendChild(StreetName);

		// cbc:CityName
		//		Element CityName = document.createElement("cbc:CityName");
		//		CityName.appendChild(document.createTextNode($NOMBRE_CIUDAD));
		//		PostalAddress.appendChild(CityName);

		// cbc:CountrySubentity
		//		Element CountrySubentity = document.createElement("cbc:CountrySubentity");
		//		CountrySubentity.appendChild(document.createTextNode("CountrySubentity"));
		//		PostalAddress.appendChild(CountrySubentity);

		// cbc:District
		// Element District = document.createElement("cbc:District");
		// District.appendChild(document.createTextNode("Distroto"));
		// PostalAddress.appendChild(District);

		// cac:Country
		//		Element Country = document.createElement("cac:Country");
		//		PostalAddress.appendChild(Country);

		// cbc:IdentificationCode
		//			Element pais = document.createElement("cbc:IdentificationCode");
		//			pais.appendChild(document.createTextNode($PAIS));
		//			Country.appendChild(pais);

		// cac:PartyLegalEntity
		Element PartyLegalEntity = document.createElement("cac:PartyLegalEntity");
		Party3.appendChild(PartyLegalEntity);	

		// cbc:RegistrationName
		Element RegistrationName = document.createElement("cbc:RegistrationName");
		RegistrationName.appendChild(document.createTextNode($RAZON_SOCIAL));
		PartyLegalEntity.appendChild(RegistrationName);


		/////////////////////////////////////////////////////////////////////////// FIN DE NUMERO  3 4 5 6










		/// NODO NUMERO 9 Y 19 Tipo y número de documento de identidad del adquirente o usuario   




		for (int linea=1; linea<_counterDet; linea++) {	

			// sac:SummaryDocumentsLine
			Element SummaryDocumentsLine = document.createElement("sac:SummaryDocumentsLine");
			element.appendChild(SummaryDocumentsLine);		

			// cbc:ID
			Element ID_Item = document.createElement("cbc:LineID");
			ID_Item.appendChild(document.createTextNode(""+linea));
			SummaryDocumentsLine.appendChild(ID_Item);		


			// aqui me quede alex

			// cbc:DocumentTypeCode
			Element DocumentTypeCode = document.createElement("cbc:DocumentTypeCode");
			DocumentTypeCode.appendChild(document.createTextNode(""+myDetalle[linea].get_tipo_doc()));
			SummaryDocumentsLine.appendChild(DocumentTypeCode);	

			//sac:DocumentSerialID
			Element DocumentSerialID = document.createElement("cbc:ID");
			DocumentSerialID.appendChild(document.createTextNode(""+myDetalle[linea].get_serie()+"-"+myDetalle[linea].get_numero_inicio()));
			SummaryDocumentsLine.appendChild(DocumentSerialID);	


			// cac:AccountingCustomerParty

			Element AccountingCustomerParty = document.createElement("cac:AccountingCustomerParty");
			element.appendChild(AccountingCustomerParty);

			// cbc:CustomerAssignedAccountID
			Element CustomerAssignedAccountID2 = document.createElement("cbc:CustomerAssignedAccountID");
			CustomerAssignedAccountID2.appendChild(document.createTextNode(myDetalle[linea].get_ruc_receptor()));
			AccountingCustomerParty.appendChild(CustomerAssignedAccountID2);

			// cbc:AdditionalAccountID
			Element AdditionalAccountID2 = document.createElement("cbc:AdditionalAccountID");
			AdditionalAccountID2.appendChild(document.createTextNode(myDetalle[linea].get_tipo_ident()));
			AccountingCustomerParty.appendChild(AdditionalAccountID2);

			SummaryDocumentsLine.appendChild(AccountingCustomerParty);	



			if (!myDetalle[linea].get_folio_rel().equals("-")) {

				// 	cac:BillingReference
				Element BillingReference = document.createElement("cac:BillingReference");
				element.appendChild(BillingReference);
				SummaryDocumentsLine.appendChild(BillingReference);
				// cac:InvoiceDocumentReference

				//	cac:InvoiceDocumentReference
				Element InvoiceDocumentReference = document.createElement("cac:InvoiceDocumentReference");
				element.appendChild(InvoiceDocumentReference);
				BillingReference.appendChild(InvoiceDocumentReference);

				Element ID_ref = document.createElement("cbc:ID");
				ID_ref.appendChild(document.createTextNode(""+myDetalle[linea].get_serie_rel()+"-"+myDetalle[linea].get_folio_rel()));
				InvoiceDocumentReference.appendChild(ID_ref);	

				Element TypeCode = document.createElement("cbc:DocumentTypeCode");
				TypeCode.appendChild(document.createTextNode("03"));
				InvoiceDocumentReference.appendChild(TypeCode);	



			}
			
			
			// cac:Status

			Element Status = document.createElement("cac:Status");
			element.appendChild(Status);

			// cbc:ConditionCode
			Element ConditionCode = document.createElement("cbc:ConditionCode");
			ConditionCode.appendChild(document.createTextNode(myDetalle[linea].get_status()));
			Status.appendChild(ConditionCode);

			SummaryDocumentsLine.appendChild(Status);		


	 

				// sac:TotalAmount 
				Element TotalAmount = document.createElement("sac:TotalAmount");
				TotalAmount.appendChild(document.createTextNode(Formato._xml(myDetalle[linea].get_total())));
				SummaryDocumentsLine.appendChild(TotalAmount);		

				// currencyID
				Attr attr_TotalAmount = document.createAttribute("currencyID");
				attr_TotalAmount.setValue("PEN");
				TotalAmount.setAttributeNode(attr_TotalAmount);					

				
			if (myDetalle[linea].get_tipo_grabamen().equals("01")) {		
				
				/// VENTAS GRAVADAS 01 
				//sac:BillingPayment
				Element BillingPayment01 = document.createElement("sac:BillingPayment");
				SummaryDocumentsLine.appendChild(BillingPayment01);

				// cbc:PaidAmount
				Element PriceAmount01 = document.createElement("cbc:PaidAmount");
				PriceAmount01.appendChild(document.createTextNode(Formato._xml(myDetalle[linea].get_ventas_gravadas())));
				BillingPayment01.appendChild(PriceAmount01);

				// currency01
				Attr attr_currency01 = document.createAttribute("currencyID");
				attr_currency01.setValue("PEN");
				PriceAmount01.setAttributeNode(attr_currency01);	

				// cbc:InstructionID
				Element InstructionID_01 = document.createElement("cbc:InstructionID");
				InstructionID_01.appendChild(document.createTextNode("01"));
				BillingPayment01.appendChild(InstructionID_01);	
				

			}



			if (myDetalle[linea].get_tipo_grabamen().equals("02")) { 


				/// VENTAS EXONERADAS 02 
				//sac:BillingPayment
				Element BillingPayment02 = document.createElement("sac:BillingPayment");
				SummaryDocumentsLine.appendChild(BillingPayment02);

				// cbc:PaidAmount
				Element PriceAmount02 = document.createElement("cbc:PaidAmount");
				PriceAmount02.appendChild(document.createTextNode(Formato._xml(myDetalle[linea].get_ventas_exoneradas())));
				BillingPayment02.appendChild(PriceAmount02);

				// currency02
				Attr attr_currency02 = document.createAttribute("currencyID");
				attr_currency02.setValue("PEN");
				PriceAmount02.setAttributeNode(attr_currency02);	

				// cbc:InstructionID
				Element InstructionID_02 = document.createElement("cbc:InstructionID");
				InstructionID_02.appendChild(document.createTextNode("02"));
				BillingPayment02.appendChild(InstructionID_02);	


			}


			if (myDetalle[linea].get_tipo_grabamen().equals("03")) { 

				/// VENTAS INAFECTAS 03 
				//sac:BillingPayment
				Element BillingPayment03 = document.createElement("sac:BillingPayment");
				SummaryDocumentsLine.appendChild(BillingPayment03);

				// cbc:PaidAmount
				Element PriceAmount03 = document.createElement("cbc:PaidAmount");
				PriceAmount03.appendChild(document.createTextNode(Formato._xml(myDetalle[linea].get_ventas_inafectas())));
				BillingPayment03.appendChild(PriceAmount03);

				// currency03
				Attr attr_currency03 = document.createAttribute("currencyID");
				attr_currency03.setValue("PEN");
				PriceAmount03.setAttributeNode(attr_currency03);	

				// cbc:InstructionID
				Element InstructionID_03 = document.createElement("cbc:InstructionID");
				InstructionID_03.appendChild(document.createTextNode("03"));
				BillingPayment03.appendChild(InstructionID_03);	


			}


			
				//cac:AllowanceCharge				
				Element AllowanceCharge = document.createElement("cac:AllowanceCharge");
				SummaryDocumentsLine.appendChild(AllowanceCharge);				

					// cbc:ChargeIndicator
					Element ChargeIndicator = document.createElement("cbc:ChargeIndicator");
					ChargeIndicator.appendChild(document.createTextNode("true"));
					AllowanceCharge.appendChild(ChargeIndicator);			

					// cbc:Amount
					Element Amount = document.createElement("cbc:Amount");
					Amount.appendChild(document.createTextNode(Formato._xml(myDetalle[linea].get_total())));
					AllowanceCharge.appendChild(Amount);		

						// currencyID
						Attr attr_Amount = document.createAttribute("currencyID");
						attr_Amount.setValue("PEN");
						Amount.setAttributeNode(attr_Amount);	





				/// tax _isc		
				//cac:TaxTotal
				Element TaxTotal01_isc = document.createElement("cac:TaxTotal");
				SummaryDocumentsLine.appendChild(TaxTotal01_isc);

					// cbc:TaxAmount
					Element TaxAmount01_isc = document.createElement("cbc:TaxAmount");
					TaxAmount01_isc.appendChild(document.createTextNode(""+myDetalle[linea].get_sumatoria_isc()));
					TaxTotal01_isc.appendChild(TaxAmount01_isc);		

					// currencyID
					Attr attr_TaxAmount01_isc = document.createAttribute("currencyID");
					attr_TaxAmount01_isc.setValue("PEN");
					TaxAmount01_isc.setAttributeNode(attr_TaxAmount01_isc);


				//cac:TaxSubtotal
				Element TaxSubtotal_isc = document.createElement("cac:TaxSubtotal");
				TaxTotal01_isc.appendChild(TaxSubtotal_isc);

					// cbc:TaxAmount
					Element SubtotalAmount_isc = document.createElement("cbc:TaxAmount");
					SubtotalAmount_isc.appendChild(document.createTextNode(""+myDetalle[linea].get_sumatoria_isc()));
					TaxSubtotal_isc.appendChild(SubtotalAmount_isc);		

					// currencyID
					Attr attr_SubtotalAmount_isc = document.createAttribute("currencyID");
					attr_SubtotalAmount_isc.setValue("PEN");
					SubtotalAmount_isc.setAttributeNode(attr_SubtotalAmount_isc);		


					// cac:TaxCategory
					Element TaxCategory_isc = document.createElement("cac:TaxCategory");
					TaxSubtotal_isc.appendChild(TaxCategory_isc);		


						// cac:TaxScheme
						Element TaxScheme_isc = document.createElement("cac:TaxScheme");
						TaxCategory_isc.appendChild(TaxScheme_isc);		


						// cbc:ID
						Element ID_isc = document.createElement("cbc:ID");
						ID_isc.appendChild(document.createTextNode("2000"));
						TaxScheme_isc.appendChild(ID_isc);	

						// cbc:Name
						Element Name_isc = document.createElement("cbc:Name");
						Name_isc.appendChild(document.createTextNode("ISC"));
						TaxScheme_isc.appendChild(Name_isc);		


						// cbc:TaxTypeCode
						Element TaxTypeCode_isc = document.createElement("cbc:TaxTypeCode");
						TaxTypeCode_isc.appendChild(document.createTextNode("EXC"));
						TaxScheme_isc.appendChild(TaxTypeCode_isc);		










						//// tax
						
						
						
						/// tax _igv		
						//cac:TaxTotal
						Element TaxTotal01_igv = document.createElement("cac:TaxTotal");
						SummaryDocumentsLine.appendChild(TaxTotal01_igv);

						// cbc:TaxAmount
						Element TaxAmount01_igv = document.createElement("cbc:TaxAmount");
						TaxAmount01_igv.appendChild(document.createTextNode(""+myDetalle[linea].get_sumatoria_igv()));
						TaxTotal01_igv.appendChild(TaxAmount01_igv);		

						// currencyID
						Attr attr_TaxAmount01_igv = document.createAttribute("currencyID");
						attr_TaxAmount01_igv.setValue("PEN");
						TaxAmount01_igv.setAttributeNode(attr_TaxAmount01_igv);


						//cac:TaxSubtotal
						Element TaxSubtotal_igv = document.createElement("cac:TaxSubtotal");
						TaxTotal01_igv.appendChild(TaxSubtotal_igv);

						// cbc:TaxAmount
						Element SubtotalAmount_igv = document.createElement("cbc:TaxAmount");
						SubtotalAmount_igv.appendChild(document.createTextNode(""+myDetalle[linea].get_sumatoria_igv()));
						TaxSubtotal_igv.appendChild(SubtotalAmount_igv);		
						// currencyID
						Attr attr_SubtotalAmount_igv = document.createAttribute("currencyID");
						attr_SubtotalAmount_igv.setValue("PEN");
						SubtotalAmount_igv.setAttributeNode(attr_SubtotalAmount_igv);		


						// cac:TaxCategory
						Element TaxCategory_igv = document.createElement("cac:TaxCategory");
						TaxSubtotal_igv.appendChild(TaxCategory_igv);		


						// cac:TaxScheme
						Element TaxScheme_igv = document.createElement("cac:TaxScheme");
						TaxCategory_igv.appendChild(TaxScheme_igv);		


						// cbc:ID
						Element ID_igv = document.createElement("cbc:ID");
						ID_igv.appendChild(document.createTextNode("1000"));
						TaxScheme_igv.appendChild(ID_igv);	

						// cbc:Name
						Element Name_igv = document.createElement("cbc:Name");
						Name_igv.appendChild(document.createTextNode("IGV"));
						TaxScheme_igv.appendChild(Name_igv);		


						// cbc:TaxTypeCode
						Element TaxTypeCode_igv = document.createElement("cbc:TaxTypeCode");
						TaxTypeCode_igv.appendChild(document.createTextNode("VAT"));
						TaxScheme_igv.appendChild(TaxTypeCode_igv);		



						





	
		}






		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		DOMSource source = new DOMSource(document);

		StreamResult streamResult = new StreamResult(new File($FILE_NAME_XML));

		transformer.transform(source, streamResult);



	}




}