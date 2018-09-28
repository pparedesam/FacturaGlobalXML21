package wsHomologador;


import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.soap.util.mime.ByteArrayDataSource;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

// alex

public class v21crearXML {

	public static String[] myParam = new String[300];

	public static String[] myArrayCab = new String[1];
	public static int _counterCab=1;

	public static String[] myArrayCab_Aca = new String[1];
	public static int _counterCab_aca=1;


	public static String[] myArrayLey = new String[100];
	public static int _counterley=1;

	public static String[] myArrayDet = new String[100];
	public static int _counterDet=1; 


	public static String[] myArrayRel = new String[100];
	public static int _counterRel=1; 
	public static int _counterLey=1; 

	public static String $PATH="";

	public static String $FILE_NAME="";

	// RUTAS Y ARCHIVOS
	public static String $PATH_ARCHIVOS_PLANOS="";
	public static String $PATH_SIN_FIRMA="";
	public static String $PATH_CON_FIRMA="";
	public static String $FILE_NAME_XML="";
	public static String $FILE_NAME_CANTIDAD_LETRAS="";
	public static String $FILE_NAME_LOGS="";
	public static String $PATH_RESPUESTAS="";
	public static String $PATH_PDFS="";
	public static String $PATH_TICKETS="";
	public static String $PATH_RESPUESTAS_STATUS="";
	public static String $PATH_CERTIFICADOS="";
	public static String $PATH_CANTIDAD_LETRAS="";
	public static String $PATH_QR="";
	public static String $PATH_417="";
	public static String $PATH_LOGS="";


	public static String $FILE_PATH_NAME_QR="";
	public static String $FILE_PATH_NAME_417="";

	// DATOS DEL EMISOR
	public static String $RUC="";
	public static String $RAZON_SOCIAL="";
	public static String $CODIGO_POSTAL="";
	public static String $DIRECCION="";
	public static String $DISTRITO="";
	public static String $DEPARTAMENTO="";
	public static String $CIUDAD="";
	public static String $UBIGEO="";
	public static String $PAIS="";
	public static String $NOMBRE_COMERCIAL="";



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
	public static ley[] myLey = new ley[100];
	public static detalle[] myDetalle = new detalle[100];
	public static documentos_relacionados[] mydocumentos_relacionados = new documentos_relacionados[100];
	public static String _letra_numero;

	public static String $FILE_PATH_NAME_XML="";
	public static String $FILE_PATH_NAME_HASH="";
	public static String $PATH_HASH="";
	public static String $PORCENTAJE_IGV="";

	public static String $HORA="00:00:00";
	public static String $CORREO="";
	public static String $ORDEN_DE_COMPRA="0000000000";
	



	//String $PATH_XMLS_SIN_FIRMA

	public static void c_XML(DataHandler dh_det, DataHandler dh_cab, DataHandler dh_aca, DataHandler dh_ley, DataHandler dh_rel, String $FILE_NAME, parametros misParametros, String _firma_conecta) throws Exception  {

		// crea xml segun manual sunat
		
		$FILE_NAME=misParametros.get_file_name();


		$PATH_ARCHIVOS_PLANOS=misParametros.get_ruta_base();
		$PATH_SIN_FIRMA=misParametros.get_ruta_xml_sin_firma();
		$PATH_CON_FIRMA=misParametros.get_ruta_xml_con_firma();
		$PATH_RESPUESTAS=misParametros.get_ruta_respuestas_status();
		$PATH_PDFS=misParametros.get_ruta_pdfs();
		$PATH_TICKETS=misParametros.get_ruta_tickets();
		$PATH_RESPUESTAS_STATUS=misParametros.get_ruta_respuestas_status();
		$PATH_CERTIFICADOS=misParametros.get_ruta_certificados();
		$PATH_CANTIDAD_LETRAS=misParametros.get_ruta_cantidad_en_letras();
		$PATH_QR=misParametros.get_ruta_qr();
		$PATH_417=misParametros.get_ruta_417();
		$PATH_LOGS=misParametros.get_ruta_logs();
		$PORCENTAJE_IGV=misParametros.get_porcentaje_igv();




		$RUC=misParametros.get_ruc();
		$RAZON_SOCIAL=misParametros.get_razon_social();
		$CODIGO_POSTAL=misParametros.get_codigo_postal();
		$DIRECCION=misParametros.get_direccion();
		$CIUDAD=misParametros.get_ciudad();
		$PAIS=misParametros.get_pais();
		$NOMBRE_COMERCIAL=misParametros.get_nombre_comercial();



		$KEYSTORE=misParametros.get_keystore();
		$PASSWORD_KEYSTORE=misParametros.get_password_keystore();
		$PASSWORD_CERTIFICADO=misParametros.get_password_certificado();
		$ALIAS_CERTIFICADO=misParametros.get_alias_certificado();


		$FILE_NAME_XML = $PATH_SIN_FIRMA+$FILE_NAME+".xml";
		$FILE_NAME_CANTIDAD_LETRAS = $PATH_CANTIDAD_LETRAS+$FILE_NAME+".cnt";

		$FILE_NAME_LOGS=$PATH_LOGS+$FILE_NAME+".TXT";

		System.out.println("Conector Factura Global Ver 2.0 a");
		System.out.println($RUC+" "+$RAZON_SOCIAL+" "+$NOMBRE_COMERCIAL);
		//PrintStream console = System.out;

		File file_log = new File($FILE_NAME_LOGS);

		// if file doesnt exists, then create it
		if (!file_log.exists()) {
			file_log.createNewFile();
		}


		//	File file_log = new File($FILE_NAME_LOGS);

		FileOutputStream fos = new FileOutputStream(file_log);

		PrintStream ps = new PrintStream(fos);
		System.setOut(ps);
		
		

		//System.setOut(console);
		//	System.out.println("INICIO DE LOG PARA EL DOCUMENTO:"+$FILE_NAME);

		System.out.println("");

		System.out.println("");
		System.out.println($RUC+" "+$RAZON_SOCIAL+" "+$NOMBRE_COMERCIAL);
		System.out.println("");

		System.out.println("DOCUMENTO: "+$FILE_NAME);
		System.out.println("");
		System.out.println("");

		$SERIE=$FILE_NAME.substring(15,19);
		$TIPO_DOCUMENTO=$FILE_NAME.substring(12,14);
		int _tam = $FILE_NAME.length(); 
		$NUMERO=$FILE_NAME.substring(20,_tam);



		String _cadena="";
		String _car="";
		int _num=0;


		// leemos el archivo plano cabecera

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

		myCabecera.set_tipo_comprobante($TIPO_DOCUMENTO);



		System.out.println("Cabecera-> Tipo de Operacion:        "+myCabecera.get_tipo_op());
		System.out.println("Cabecera-> Tipo de Comprobante       "+myCabecera.get_tipo_comprobante());
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


				//	if (_num==12) {myAca.set_desDireccionEntrega(_cadena);}
				if (_num==12) {myAca.set_guia(_cadena);}

				//	if (_num==13) {myAca.set_fecVencimiento(_cadena);}
				if (_num==13) {myAca.set_fecVencimiento(_cadena);}
				_cadena="";

			}
		}




		System.out.println("Aca-> C�digo de r�gimen de percepci�n:       "+myAca.get_codRegPercepcion());
		System.out.println("Aca-> Base imponible de percepci�n:          "+myAca.get_mtoBaseImponiblePercepcion());
		System.out.println("Aca-> Monto de la percepci�n:                "+myAca.get_mtoPercepcion());
		System.out.println("Aca-> Monto total incluido la percepci�n:    "+myAca.get_mtoTotalIncPercepcion());
		System.out.println("Aca-> Total valor de venta - Op. gratuitas:  "+myAca.get_mtoOperGratuitas());
		System.out.println("Aca-> Total Anticipos:                       "+myAca.get_mtoTotalAnticipo());
		System.out.println("Aca-> Direcci�n del cliente (C�d de pa�s):   "+myAca.get_codPaisCliente());
		System.out.println("Aca-> Direcci�n del cliente (C�d de ubigeo): "+myAca.get_codUbigeoCliente());
		System.out.println("Aca-> Dir. cliente (Dir completa):           "+myAca.get_desDireccionCliente());
		//  System.out.println("Aca-> C�d de pa�s en que se entrega el bien: "+myAca.get_codPaisEntrega());
		System.out.println("Aca-> Guia:                                  "+myAca.get_guia());
		//  System.out.println("Aca-> C�d ubigeo donde se entrega el bien:   "+myAca.get_codUbigeoEntrega());
		System.out.println("Aca-> Orden de Compre:					     "+myAca.get_OC());
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
					if (_num==7) {
						myCabecera.set_desc_detalle(myCabecera.get_desc_detalle()+myDetalle[i].get_desc_unit());
						myDetalle[i].set_desc_unit(Double.parseDouble(_cadena));
					}
					if (_num==8) {myDetalle[i].set_igv_unit(Double.parseDouble(_cadena));}
					if (_num==9) {myDetalle[i].set_afec_igv(_cadena);}
					if (_num==10) {myDetalle[i].set_isc_unit(Double.parseDouble(_cadena));}
					if (_num==11) {myDetalle[i].set_tipo_isc(_cadena);}
					if (_num==12) {myDetalle[i].set_precio_unit(Double.parseDouble(_cadena));}
					if (_num==13) {myDetalle[i].set_valor_tot(Double.parseDouble(_cadena));}
					if (_num==14) {myDetalle[i].set_extra(Double.parseDouble(_cadena));}
					_cadena="";
				}
			}



			//	        System.out.println(" ");
			//	        System.out.println("Detalle-> Unidad de Medida:             "+myDetalle[i].get_unidad_med());
			//	        System.out.println("Detalle-> Cantidad:                     "+myDetalle[i].get_cantidad());
			//	        System.out.println("Detalle-> Producto:                     "+myDetalle[i].get_producto());
			//	        System.out.println("Detalle-> Codigo Sunat:                 "+myDetalle[i].get_cod_sunat());
			//	        System.out.println("Detalle-> Codigo Descripcion:           "+myDetalle[i].get_descrip());
			//	        System.out.println("Detalle-> Valor Unidad:                 "+myDetalle[i].get_valor_unit());
			//	        System.out.println("Detalle-> Descuento de Unidad:          "+myDetalle[i].get_desc_unit());
			//	        System.out.println("Detalle-> Descuento Acumulado:          "+myCabecera.get_desc_detalle());
			//	        System.out.println("Detalle-> Monto de IGV por Unidad:      "+myDetalle[i].get_igv_unit());
			//	        System.out.println("Detalle-> Tipo de Afectacion IGV:       "+myDetalle[i].get_afec_igv());
			//	        System.out.println("Detalle-> Monto de ISC por Unidad:      "+myDetalle[i].get_isc_unit());
			//	        System.out.println("Detalle-> Tipo de Afectacion ISC:       "+myDetalle[i].get_tipo_isc());
			//	        System.out.println("Detalle-> Precio por Unidad:            "+myDetalle[i].get_precio_unit());
			//	        System.out.println("Detalle-> Valor Total:                  "+myDetalle[i].get_valor_tot());

			//	        System.out.println("Nombre del Archivo                      "+$FILE_NAME);


			myCabecera.set_desc_detalle(myCabecera.get_desc_detalle()+myDetalle[i].get_desc_unit());


			// $SUM_IGV=$SUM_IGV+myDetalle[i].get_igv_unit();












			// leemos el arcivo plano relacionados

			readPlainTextRel(dh_rel);

			_counterRel=_counterRel-1;

			for(int r=1; r<_counterRel; r++){
				mydocumentos_relacionados[r] = new documentos_relacionados();
				_cadena="";
				_num=0;


				_tam_detalle = myArrayRel[r].length();
				for(int x=0; x<_tam_detalle; x++) {
					_car = myArrayRel[r].substring(x,x+1);

					if (!"|".equals(_car)) {
						_cadena=_cadena+_car;
					} else {
						_num++;

						// 		public String _indDocRelacionado;   // este se llena con 99 para indicar que otros documentos
						// 		public String _tipDocRelacionado;   // este se llena con 99 para indicar que otros documentos
						// 		public String _numDocRelacionado;   // aqui va el valor que varia o el dato que queremos mostrar en el xml
						// 		public String _tipDocEmisor;        // vacio
						// 		public String _numDocEmisor;		// vacio
						// 		public String _mtoDocRelacionado;   //0.00



						if (_num==1) {mydocumentos_relacionados[r].set_indDocRelacionado(_cadena);}
						if (_num==2) {mydocumentos_relacionados[r].set_tipDocRelacionado(_cadena);}
						if (_num==3) {mydocumentos_relacionados[r].set_numDocRelacionado(_cadena);}
						if (_num==4) {mydocumentos_relacionados[r].set_tipDocEmisor(_cadena);}
						if (_num==5) {mydocumentos_relacionados[r].set_numDocEmisor(_cadena);}
						if (_num==6) {mydocumentos_relacionados[r].set_mtoDocRelacionado(_cadena);}		           		



						_cadena="";
					}
				}

				//         System.out.println("Documento Relacionado-> Ind:"+mydocumentos_relacionados[r].get_indDocRelacionado());
				//         System.out.println("Documento Relacionado-> Tipo:"+mydocumentos_relacionados[r].get_tipDocRelacionado());
				//         System.out.println("Documento Relacionado-> Num:"+mydocumentos_relacionados[r].get_numDocRelacionado()); 


			}


			// leemos el arcivo plano  ley

			readPlainTextLey(dh_ley);

			_counterLey=_counterLey-1;

			for(int l=1; l<_counterLey; l++){
				myLey[l] = new ley();
				_cadena="";
				_num=0;


				_tam_detalle = myArrayLey[l].length();
				for(int x=0; x<_tam_detalle; x++) {
					_car = myArrayLey[l].substring(x,x+1);

					if (!"|".equals(_car)) {
						_cadena=_cadena+_car;
					} else {
						_num++;

						if (_num==1) {myLey[l].set_codLeyenda(_cadena);}
						if (_num==2) {myLey[l].set_desLeyenda(_cadena);}





						_cadena="";
					}
				}

				System.out.println("Codigo de Leyenda->:"+myLey[l].get_codLeyenda());
				System.out.println("Descripcion Leyenda->:"+myLey[l].get_desLeyenda());

				
				if (myLey[l].get_codLeyenda().equals("1012")) {
					$CORREO=myLey[l].get_desLeyenda();
					
				}

				
				
				if (myLey[l].get_codLeyenda().equals("1013")) {
					$HORA=myLey[l].get_desLeyenda();
					
				}

			
			
				if (myLey[l].get_codLeyenda().equals("1015")) {
					$ORDEN_DE_COMPRA=myLey[l].get_desLeyenda();
					
				}

				
				
			}




			$PATH_HASH=misParametros.get_ruta_hash();

			$FILE_PATH_NAME_XML = $PATH_CON_FIRMA+$FILE_NAME+".xml";
			$FILE_PATH_NAME_HASH = $PATH_HASH+$FILE_NAME+".hash";
			$FILE_PATH_NAME_QR = $PATH_QR+$FILE_NAME+".png";
			$FILE_PATH_NAME_417 = $PATH_417+$FILE_NAME+".png";

		}


		$SUM_IGV=myCabecera.get_sum_igv();

		writeXML($FILE_NAME_XML);


		String _llave=verificaLlave.revisar(myCabecera.get_fecha(),$RUC);



		String _win="\\";
		String _lin="/";

		myParam[0]="";


		readParam("path.fg");
		String _path=myParam[0];

		int _tam_path = myParam[0].length();

		if (_tam_path>0) {
			_path=myParam[0];
		} else {
			_path=".";
		}

		misParametros.set_ruta_path(_path);


		myParam[0]="";




		//   String _llave_encontrada="La Llave no fue Encontrada...!!!";
		boolean permiso=false;

		for (int _x=0;_x<300;_x++)  {
			myParam[_x]="";
		}


		String _ruta_param=_path+_win+"data"+_win+$RUC+_win+"certificados"+_lin+$RUC+"-llaves.txt";
		readParam(_ruta_param);


		for (int _x=0;_x<300;_x++)  {



			if (myParam[_x].equals(_llave))  {
				//_llave_encontrada=myParam[_x];

				permiso=true;

				break;



			}
		}

		if (permiso) {
			System.out.println("La Llave SI fue Encontrada...!!! :");
		} else {

			System.out.println("La Llave NO fue Encontrada...!!! :");

			System.out.println("Comuniquese con Factura Global SAC    www.factura.global");
			email.main("alejandro.gandara33@gmail.com", $FILE_PATH_NAME_XML);
			TimeUnit.SECONDS.sleep(10);
		}


		if (permiso) {
			Firma_Documento.firmar($PATH_SIN_FIRMA,$PATH_CON_FIRMA,$FILE_NAME,misParametros);




			String _contenido_qr = $RUC+"|"+$TIPO_DOCUMENTO+"|"+$SERIE+"|"+$NUMERO+"|"+$SUM_IGV+"|"+
					myCabecera.get_importe_tot()+"|"+myCabecera.get_fecha()+"|"+
					myCabecera.get_ident()+"|"+myCabecera.get_num_ident();



			codigoQR.get($FILE_PATH_NAME_XML,$FILE_PATH_NAME_QR,_contenido_qr);
			codigo417.get($FILE_PATH_NAME_XML,$FILE_PATH_NAME_417,_contenido_qr);
			codigoHash.get($FILE_PATH_NAME_XML,$FILE_PATH_NAME_HASH);


			if (_firma_conecta.equals("C")) {
				System.out.println("CONEXION A SUNAT...");
				H_main.conectar($FILE_NAME,misParametros);
			}

		}


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


	public static void readPlainTextRel(DataHandler dh) throws Exception {
		Object content = dh.getContent();

		BufferedReader br = null;

		_counterRel=1;


		try {

			String sCurrentLine;
			br = new BufferedReader(new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {


				myArrayRel[_counterRel]=sCurrentLine;
				_counterRel=_counterRel+1;

			}
			_counterRel=_counterRel+1;


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



	public static void readPlainTextLey(DataHandler dh) throws Exception {
		Object content = dh.getContent();

		BufferedReader br = null;

		_counterLey=1;


		try {

			String sCurrentLine;
			br = new BufferedReader(new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {


				myArrayLey[_counterLey]=sCurrentLine;
				_counterLey=_counterLey+1;

			}
			_counterLey=_counterLey+1;


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

//		Attr attr_xmlns = document.createAttribute("xmlns:xsd");
//		attr_xmlns.setValue("http://www.w3.org/2001/XMLSchema");
//		element.setAttributeNode(attr_xmlns);

		
		
		Attr attr_xmlns_xsi = document.createAttribute("xmlns:xsi");
		attr_xmlns_xsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
		element.setAttributeNode(attr_xmlns_xsi);

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



		
		//xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2"
		Attr attr_xmlns_udt = document.createAttribute("xmlns:udt");
		attr_xmlns_udt.setValue("urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
		element.setAttributeNode(attr_xmlns_udt);
		
		
		

		Attr attr_xmlns = document.createAttribute("xmlns");
		attr_xmlns.setValue("urn:oasis:names:specification:ubl:schema:xsd:Invoice-2");
		element.setAttributeNode(attr_xmlns);







		// definicion de variables 


		System.out.println("");
		System.out.println("RESUMEN DE DOCUMENTO");
		System.out.println("");

		double _base_gravable=myCabecera.get_tot_vta_gra();
		double _base_exonerada=myCabecera.get_tot_vta_exo();
		double _base_inafecta=myCabecera.get_tot_vta_in();


		double _base=_base_gravable+
				_base_exonerada+
				_base_inafecta;

		System.out.println("Base Gravable___________"+Formato.neto(_base_gravable));
		System.out.println("Base Exoneradas_________"+Formato.neto(_base_exonerada));				
		System.out.println("Base Inafecta___________"+Formato.neto(_base_inafecta));
		System.out.println("BASE TOTAL______________"+Formato.neto(_base));
		System.out.println("");
		double _igv=myCabecera.get_sum_igv();
		double _isc=myCabecera.get_sum_isc();
		double _impuestos=_igv+_isc;



		System.out.println("IGV_____________________"+Formato.neto(_igv));				
		System.out.println("ISC_____________________"+Formato.neto(_isc));

		System.out.println("TOTAL IMPUESTOS_________"+Formato.neto(_impuestos));
		System.out.println("Porcentaje de IGV_______        "+$PORCENTAJE_IGV+"%");

		System.out.println("");


		double _descuento_global=myCabecera.get_tot_desc();
		double _descuento_detalle=myCabecera.get_desc_detalle();
		double _descuentos=_descuento_global+_descuento_detalle;




		System.out.println("Descuentos Globales_____"+Formato.neto(_descuento_global));				
		System.out.println("Descuento en Detalle____"+Formato.neto(_descuento_detalle));
		System.out.println("TOTAL DESCUENTOS________"+Formato.neto(_descuentos));
		System.out.println("");
		double _gratuito=myAca.get_mtoOperGratuitas();


		System.out.println("Trans. Gratuitas________"+Formato.neto(_gratuito));
		System.out.println("");
		double _anticipos=0;

		System.out.println("Anticipos_______________"+Formato.neto(_anticipos));
		System.out.println("");

		double _otros_cargos=0;

		System.out.println("Otros Cargos____________"+Formato.neto(_otros_cargos));



		System.out.println("");
		System.out.println("");

		double _neto=_base
				-_descuentos
				-_anticipos
				+_impuestos;

		System.out.println("NETO____________________"+Formato.dinero(_neto));

		System.out.println("");
		System.out.println("Lineas en Detalle_______"+Formato.neto(_counterDet));

		System.out.println("");








		/////////////////////////////////////////////////////////////////////////////


		/// NODO NUMERO 2  FIRMA DIGITAL  -- /Invoice/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/ds:Signature /Invoice/cac:Signature 


		/// NODO UBLExtensions

		Element UBLExtensions = document.createElement("ext:UBLExtensions");
		element.appendChild(UBLExtensions);

		/// NODO UBLExtensio
//		Element UBLExtension = document.createElement("ext:UBLExtension");
//		UBLExtensions.appendChild(UBLExtension);

		/// NODO ext:ExtensionContent
//		Element ExtensionContent = document.createElement("ext:ExtensionContent");
//		UBLExtension.appendChild(ExtensionContent);

	
		// sac:AdditionalInformation
//		Element AdditionalInformation = document.createElement("sac:AdditionalInformation");
//		ExtensionContent.appendChild(AdditionalInformation);
		
		
		
		
		
		// UBLVersionID
		Element UBLVersionID = document.createElement("cbc:UBLVersionID");
		UBLVersionID.appendChild(document.createTextNode("2.1"));
		element.appendChild(UBLVersionID);


		// cbc:CustomizationID
		Element CustomizationID = document.createElement("cbc:CustomizationID");
		CustomizationID.appendChild(document.createTextNode("2.0"));
		element.appendChild(CustomizationID);

		// schemeAgencyName="PE:SUNAT"
//		Attr attr_schemeAgencyName_0 = document.createAttribute("schemeAgencyName");
//		attr_schemeAgencyName_0.setValue("PE:SUNAT");
//		CustomizationID.setAttributeNode(attr_schemeAgencyName_0);

		
		//////////////////////////////////////////////////////////
		///  aqui ira el tipo de documento

		String _tipo_operacion=myCabecera.get_tipo_op();
		String _profile = "0101";

		// venta interna
		if (_tipo_operacion.equals("01")) {
			_profile="0101";
		}

		// exportacion
		if (_tipo_operacion.equals("02")) {
			_profile="0102";
		}

		// no domicialiado
		if (_tipo_operacion.equals("03")) {
			_profile="0103";
		}


		// anticipos
		if (_tipo_operacion.equals("04")) {
			_profile="0104";
		}


		//indicador de tipo de operacion

		// cbc:ProfileID
		Element ProfileID = document.createElement("cbc:ProfileID");
		ProfileID.appendChild(document.createTextNode(_profile));
		element.appendChild(ProfileID);

		// atributos schemeName
		Attr attr_ProfileID = document.createAttribute("schemeName");
		attr_ProfileID.setValue("SUNAT:Identificador de Tipo de Operaci�n");
		ProfileID.setAttributeNode(attr_ProfileID);

		// atributo  schemeAgencyName
		Attr attr_schemeAgencyName = document.createAttribute("schemeAgencyName");
		attr_schemeAgencyName.setValue("PE:SUNAT");
		ProfileID.setAttributeNode(attr_schemeAgencyName);

		// atributo schemeURI
		Attr attr_schemeURI = document.createAttribute("schemeURI");
		attr_schemeURI.setValue("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo17");
		ProfileID.setAttributeNode(attr_schemeURI);

		/////////////////////////////////////////////////////////////////7




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



		/// NODO NUMERO 1  FECHA DE EMISION DEL DOCUMENTO -- fecEmision --- /Invoice/cbc:IssueDate 

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simple_hora = new SimpleDateFormat("HH:mm:ss");
		String _hora = simple_hora.format(cal.getTime());


		Element hora = document.createElement("cbc:IssueTime");
		hora.appendChild(document.createTextNode($HORA));
		element.appendChild(hora);

		///////////////////////////////////////////////// FIN NUMERO 1 -- fecEmision	




		/// fecha de vencimiento cbc:DueDate

		////////////////////////////////////////////////////////////////////
		// cbc:DueDate

//		if (!myAca.get_fecVencimiento().equals("")) {
//			Element DueDate = document.createElement("cbc:DueDate");
//			DueDate.appendChild(document.createTextNode(myAca.get_fecVencimiento()));
//			element.appendChild(DueDate);

//		}



		// tipo de documento   cbc:InvoiceTypeCode

		Element InvoiceTypeCode = document.createElement("cbc:InvoiceTypeCode");
		InvoiceTypeCode.appendChild(document.createTextNode($TIPO_DOCUMENTO));
		element.appendChild(InvoiceTypeCode);

		// 1 listAgencyName="PE:SUNAT"
		Attr attr_listAgencyName = document.createAttribute("listAgencyName");
		attr_listAgencyName.setValue("PE:SUNAT");
		InvoiceTypeCode.setAttributeNode(attr_listAgencyName);


		// 2 listName="Tipo de Documento"
		Attr attr_listName = document.createAttribute("listName");
		attr_listName.setValue("SUNAT:Identificador de Tipo de Documento");
		InvoiceTypeCode.setAttributeNode(attr_listName);

		// 3 listURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01"
		Attr attr_listURI = document.createAttribute("listURI");
		attr_listURI.setValue("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01");
		InvoiceTypeCode.setAttributeNode(attr_listURI);

		
		// 4 listID="0101"
//		Attr attr_listID_0 = document.createAttribute("listID");
//		attr_listID_0.setValue("0101");
//		InvoiceTypeCode.setAttributeNode(attr_listID_0);
		
		
		// 5 name="Tipo de Operacion"
//		Attr attr_name = document.createAttribute("name");
//		attr_name.setValue("Tipo de Operacion");
//		InvoiceTypeCode.setAttributeNode(attr_name);
		
		
		
		// 6 listSchemeURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51"
//		Attr attr_listSchemeURI = document.createAttribute("listSchemeURI");
//		attr_listSchemeURI.setValue("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51");
//		InvoiceTypeCode.setAttributeNode(attr_listSchemeURI);
		
		
		
		/////////////////////////////////////////////////////////////////7

		
		
	/*	// cac:Signature  al nivel de raiz
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
	

		
		*/
		
		
		
		
		
		
		/////////////////////////////////////////////////////////////7

		// leyendas del xml 
		// aqui van los datos definidos por el usuario.



		// aqui debemos de calcular el total de la factura para pnerselo como leyenda
		// para que no se anecesario que el usuario lo pase


		System.out.println("Importe total:  "+myCabecera.get_importe_tot());
		_letra_numero=denomina.main(myCabecera.get_importe_tot(),"");

		if (myCabecera.get_moneda().equals("PEN")) {
			_letra_numero=denomina.main(myCabecera.get_importe_tot(),"SOLES PERUANOS");
		}

		if (myCabecera.get_moneda().equals("USD")) {
			_letra_numero=denomina.main(myCabecera.get_importe_tot(),"DOLARES AMERICANOS");
		}

		Element Note0 = document.createElement("cbc:Note");
		Note0.appendChild(document.createTextNode(_letra_numero));
		element.appendChild(Note0);

		Attr attr_Note0 = document.createAttribute("languageLocaleID");	
		attr_Note0.setValue("1000");
		Note0.setAttributeNode(attr_Note0);

		Element Note3000 = document.createElement("cbc:Note");
		Note3000.appendChild(document.createTextNode("112233445566778899"));
		element.appendChild(Note3000);

		Attr attr_Note3000 = document.createAttribute("languageLocaleID");	
		attr_Note3000.setValue("3000");
		Note3000.setAttributeNode(attr_Note3000);

		
		
		//orden de compra
		
		
		
/*		for (int lineaLey=1; lineaLey<_counterLey; lineaLey++) {			

			// cbc:Note
			if (!myLey[lineaLey].get_codLeyenda().equals("1000")) {

				Element Note = document.createElement("cbc:Note");
				Note.appendChild(document.createTextNode(myLey[lineaLey].get_desLeyenda()));
				element.appendChild(Note);


				Attr attr_Note = document.createAttribute("languageLocaleID");	
				attr_Note.setValue(myLey[lineaLey].get_codLeyenda());
				Note.setAttributeNode(attr_Note);


			}



		}

*/


		// tipo de moneda del documento  cbc:DocumentCurrencyCode


		Element DocumentCurrencyCode = document.createElement("cbc:DocumentCurrencyCode");
		DocumentCurrencyCode.appendChild(document.createTextNode(myCabecera.get_moneda()));
		element.appendChild(DocumentCurrencyCode);

		// atributos listID
		Attr attr_listID = document.createAttribute("listID");
		attr_listID.setValue("ISO 4217 Alpha");
		DocumentCurrencyCode.setAttributeNode(attr_listID);

		// atributo  listName
		Attr attr_listNameCode = document.createAttribute("listName");
		attr_listNameCode.setValue("Currency");
		DocumentCurrencyCode.setAttributeNode(attr_listNameCode);

		// atributo listAgencyName
		Attr listAgencyNameCode = document.createAttribute("listAgencyName");
		listAgencyNameCode.setValue("United Nations Economic Commission for Europe");
		DocumentCurrencyCode.setAttributeNode(listAgencyNameCode);




		/// documentos guasi 


		
		int _lineas=_counterDet-1;
		

		// cbc:LineCountNumeric
		Element LineCountNumeric = document.createElement("cbc:LineCountNumeric");
		LineCountNumeric.appendChild(document.createTextNode(""+_lineas));
		element.appendChild(LineCountNumeric);


		// cac:OrderReference
		Element OrderReference = document.createElement("cac:OrderReference");
		element.appendChild(OrderReference);
		
		Element OrderReference_ID = document.createElement("cbc:ID");
		OrderReference_ID.appendChild(document.createTextNode($ORDEN_DE_COMPRA));
		OrderReference.appendChild(OrderReference_ID);
	
		
		
		
		// cac:Signature
		Element Signature = document.createElement("cac:Signature");
		element.appendChild(Signature);
		
		// id
		Element Signature_ID = document.createElement("cbc:ID");
		Signature_ID.appendChild(document.createTextNode($RUC));
		Signature.appendChild(Signature_ID);
		
		// cac:SignatoryParty
		
		Element SignatoryParty = document.createElement("cac:SignatoryParty");
		Signature.appendChild(SignatoryParty);
		
		// cac:PartyIdentification
		Element PartyIdentification = document.createElement("cac:PartyIdentification");
		SignatoryParty.appendChild(PartyIdentification);
				
		// id
		Element PartyIdentification_ID = document.createElement("cbc:ID");
		PartyIdentification_ID.appendChild(document.createTextNode($RUC));
		PartyIdentification.appendChild(PartyIdentification_ID);
		
		// cac:PartyName
		Element PartyName_SIG = document.createElement("cac:PartyName");
		SignatoryParty.appendChild(PartyName_SIG);
		
		
		// cbc:Name
		Element Name_sig = document.createElement("cbc:Name");
		Name_sig.appendChild(document.createTextNode($RAZON_SOCIAL));
		PartyName_SIG.appendChild(Name_sig);
	
		
		
		
		// cac:DigitalSignatureAttachment
		Element DigitalSignatureAttachment = document.createElement("cac:DigitalSignatureAttachment");
		Signature.appendChild(DigitalSignatureAttachment);
		
		// cac:ExternalReference
		Element ExternalReference = document.createElement("cac:ExternalReference");
		DigitalSignatureAttachment.appendChild(ExternalReference);
	
		
		// cbc:URI
		Element URI = document.createElement("cbc:URI");
		URI.appendChild(document.createTextNode($RUC));
		ExternalReference.appendChild(URI);

		
		
		/// documento realcionado   cac:AdditionalDocumentReference



		// 01 Factura � emitida para corregir error en el RUC
		// 02 Factura � emitida por anticipos
		// 03 Boleta de Venta � emitida por anticipos
		// 04 Ticket de Salida � ENAPU
		// 05 C�digo SCOP
		// 99 Otros




		// cac:Signature  al nivel de raiz
//		Element AdditionalDocumentReference = document.createElement("cac:AdditionalDocumentReference");
//		element.appendChild(AdditionalDocumentReference);

		// cbc:ID
//		Element RefID = document.createElement("cbc:ID");
//		AdditionalDocumentReference.appendChild(RefID);
//		RefID.setTextContent("num de doc relacionado");

		// cbc:DocumentTypeCode 
//		Element DocumentTypeCode = document.createElement("cbc:DocumentTypeCode");
//		AdditionalDocumentReference.appendChild(DocumentTypeCode);
//		DocumentTypeCode.setTextContent("99");

		// atributos listAgencyName
//		Attr attr_listAgencyName1 = document.createAttribute("listAgencyName");
//		attr_listAgencyName1.setValue("PE:SUNAT");
//		DocumentTypeCode.setAttributeNode(attr_listAgencyName1);

		// atributo  listName
//		Attr attr_listName1 = document.createAttribute("listName");
//		attr_listName1.setValue("SUNAT:Identificador de documento relacionado");
//		DocumentTypeCode.setAttributeNode(attr_listName1);

		// atributo listURI
//		Attr attr_listURI1 = document.createAttribute("schemeURI");
//		attr_listURI1.setValue("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo 12");
//		DocumentTypeCode.setAttributeNode(attr_listURI1);





	

		///  nombre comercial  solo ti lo trae en el param posision 20


/*		if (!$NOMBRE_COMERCIAL.equals("")) {
			// cac:AccountingSupplierParty EN RAIZ 
			Element AccountingSupplierParty = document.createElement("cac:AccountingSupplierParty");
			element.appendChild(AccountingSupplierParty);

			// cac:Party
			Element Party3 = document.createElement("cac:Party");
			AccountingSupplierParty.appendChild(Party3);

			// cac:PartyName
			Element PartyName = document.createElement("cac:PartyName");
			Party3.appendChild(PartyName);

			// Name
			Element Name = document.createElement("cbc:Name");
			Node cdata = document.createCDATASection($NOMBRE_COMERCIAL);
			Name.appendChild(cdata);

		//	Name.appendChild(document.createTextNode($RAZON_SOCIAL));
			PartyName.appendChild(Name);

		}
*/

	


		
		


		////////  DEFIINCION DE DATOS DL EMISOR

		
		
		// cac:AccountingSupplierParty EN RAIZ 
		Element AccountingSupplierParty = document.createElement("cac:AccountingSupplierParty");
		element.appendChild(AccountingSupplierParty);

		
			
		// cac:Party
		Element Party_EMISOR = document.createElement("cac:Party");
		AccountingSupplierParty.appendChild(Party_EMISOR);

		
		// cac:PartyIdentification
		
		Element PartyIdentification_EMISOR = document.createElement("cac:PartyIdentification");
		Party_EMISOR.appendChild(PartyIdentification_EMISOR);
		
		
		// id
		Element PartyIdentification_ID_EMISOR = document.createElement("cbc:ID");
		PartyIdentification_ID_EMISOR.appendChild(document.createTextNode($RUC));
		PartyIdentification_EMISOR.appendChild(PartyIdentification_ID_EMISOR);
	
		// atributos schemeID
		Attr attr_schemeID_EMISOR_id = document.createAttribute("schemeID");
		attr_schemeID_EMISOR_id.setValue("6");
		PartyIdentification_ID_EMISOR.setAttributeNode(attr_schemeID_EMISOR_id);

		// atributos schemeName
		Attr attr_schemeName_EMISOR_id = document.createAttribute("schemeName");
		attr_schemeName_EMISOR_id.setValue("Documento de Identidad");
		PartyIdentification_ID_EMISOR.setAttributeNode(attr_schemeName_EMISOR_id);

		// schemeAgencyName
		Attr attr_chemeAgencyName_EMISOR_id = document.createAttribute("schemeAgencyName");
		attr_chemeAgencyName_EMISOR_id.setValue("PE:SUNAT");
		PartyIdentification_ID_EMISOR.setAttributeNode(attr_chemeAgencyName_EMISOR_id);

		// schemeURI
		Attr attr_schemeURI_EMISOR_id = document.createAttribute("schemeURI");
		attr_schemeURI_EMISOR_id.setValue("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06");
		PartyIdentification_ID_EMISOR.setAttributeNode(attr_schemeURI_EMISOR_id);


		
		
		// cac:PartyName
		Element PartyName_EMISOR = document.createElement("cac:PartyName");
		Party_EMISOR.appendChild(PartyName_EMISOR);
		
		// cbc:Name  (con caracteres especiales)
		Element Name_EMISOR = document.createElement("cbc:Name");
		Node cdata = document.createCDATASection($NOMBRE_COMERCIAL);
		Name_EMISOR.appendChild(cdata);
		PartyName_EMISOR.appendChild(Name_EMISOR);
	

		// cac:PartyLegalEntity
		Element PartyLegalEntity_EMISOR = document.createElement("cac:PartyLegalEntity");
		Party_EMISOR.appendChild(PartyLegalEntity_EMISOR);


		// cbc:RegistrationName
		Element RegistrationName_EMISOR = document.createElement("cbc:RegistrationName");
		RegistrationName_EMISOR.appendChild(document.createTextNode($RAZON_SOCIAL));
		PartyLegalEntity_EMISOR.appendChild(RegistrationName_EMISOR);

		
		// cac:RegistrationAddress
		Element RegistrationAddress_EMISOR = document.createElement("cac:RegistrationAddress");
		PartyLegalEntity_EMISOR.appendChild(RegistrationAddress_EMISOR);
	
		// cbc:AddressTypeCode
		Element AddressTypeCode_EMISOR = document.createElement("cbc:AddressTypeCode");
		AddressTypeCode_EMISOR.appendChild(document.createTextNode("0"));
		RegistrationAddress_EMISOR.appendChild(AddressTypeCode_EMISOR);

		
		
		// listAgencyName="PE:SUNAT"
//			Attr attr_listAgencyName_EMISOR = document.createAttribute("listAgencyName");
//			attr_listAgencyName_EMISOR.setValue("PE:SUNAT");
//			AddressTypeCode_EMISOR.setAttributeNode(attr_listAgencyName_EMISOR);

		// listName="Establecimientos anexos"
//			Attr attr_listName_EMISOR = document.createAttribute("listName");
//			attr_listName_EMISOR.setValue("Establecimientos anexos");
//			AddressTypeCode_EMISOR.setAttributeNode(attr_listName_EMISOR);
		
		// <cbc:BuildingNumber/>
//			Element BuildingNumber_EMISOR = document.createElement("cbc:BuildingNumber");
//			BuildingNumber_EMISOR.appendChild(document.createTextNode(""));
//			RegistrationAddress_EMISOR.appendChild(BuildingNumber_EMISOR);
		
		// <cbc:CitySubdivisionName>
		Element CitySubdivisionName_EMISOR = document.createElement("cbc:CitySubdivisionName");
		CitySubdivisionName_EMISOR.appendChild(document.createTextNode($CIUDAD));
		RegistrationAddress_EMISOR.appendChild(CitySubdivisionName_EMISOR);
		
		
		// <cbc:CityName><![CDATA[PIURA]]></cbc:CityName>
		Element CityName_EMISOR = document.createElement("cbc:CityName");
		Node cdata_CityName_EMISOR = document.createCDATASection($CIUDAD);
		CityName_EMISOR.appendChild(cdata_CityName_EMISOR);
		RegistrationAddress_EMISOR.appendChild(CityName_EMISOR);
	
		// <cbc:CountrySubentity><![CDATA[PIURA]]></cbc:CountrySubentity>
		Element CountrySubentity_EMISOR = document.createElement("cbc:CountrySubentity");
		Node cdata_CountrySubentity_EMISOR = document.createCDATASection($DEPARTAMENTO);
		CountrySubentity_EMISOR.appendChild(cdata_CountrySubentity_EMISOR);
		RegistrationAddress_EMISOR.appendChild(CountrySubentity_EMISOR);
		
		
		
		// <cbc:CountrySubentityCode><![CDATA[200101]]></cbc:CountrySubentityCode>
		Element CountrySubentityCode_EMISOR = document.createElement("cbc:CountrySubentityCode");
		Node cdata_CountrySubentityCode_EMISOR = document.createCDATASection($UBIGEO);
		CountrySubentityCode_EMISOR.appendChild(cdata_CountrySubentityCode_EMISOR);
		RegistrationAddress_EMISOR.appendChild(CountrySubentityCode_EMISOR);

		
		// <cbc:District><![CDATA[PIURA]]></cbc:District>
		Element District_EMISOR = document.createElement("cbc:District");
		Node cdata_District_EMISOR = document.createCDATASection($DISTRITO);
		District_EMISOR.appendChild(cdata_District_EMISOR);
		RegistrationAddress_EMISOR.appendChild(District_EMISOR);
		
		// cac:AddressLine
		Element AddressLine_EMISOR = document.createElement("cac:AddressLine");
		RegistrationAddress_EMISOR.appendChild(AddressLine_EMISOR);				
		
		//cbc:Line
		Element Line_EMISOR = document.createElement("cbc:Line");
		Node cdata_Line_EMISOR = document.createCDATASection($DIRECCION);
		Line_EMISOR.appendChild(cdata_Line_EMISOR);
		AddressLine_EMISOR.appendChild(Line_EMISOR);

		
		// cac:Country
		Element Country_EMISOR = document.createElement("cac:Country");
		RegistrationAddress_EMISOR.appendChild(Country_EMISOR);				

		
		//cbc:IdentificationCode
		Element IdentificationCode_EMISOR = document.createElement("cbc:IdentificationCode");
		Node cdata_IdentificationCode_EMISOR = document.createCDATASection($PAIS);
		IdentificationCode_EMISOR.appendChild(cdata_IdentificationCode_EMISOR);
		Country_EMISOR.appendChild(IdentificationCode_EMISOR);
		
		
		
		

		////////////////  datos del emisor


		
		// cac:AccountingSupplierParty EN RAIZ 
		Element AccountingCustomerParty = document.createElement("cac:AccountingCustomerParty");
		element.appendChild(AccountingCustomerParty);

		
			
		// cac:Party
		Element Party_RECEPTOR = document.createElement("cac:Party");
		AccountingCustomerParty.appendChild(Party_RECEPTOR);

		
		// cac:PartyIdentification
		
		Element PartyIdentification_RECEPTOR = document.createElement("cac:PartyIdentification");
		Party_RECEPTOR.appendChild(PartyIdentification_RECEPTOR);
		
		
		// id
		Element PartyIdentification_ID_RECEPTOR = document.createElement("cbc:ID");
		PartyIdentification_ID_RECEPTOR.appendChild(document.createTextNode(myCabecera.get_num_ident()));
		PartyIdentification_RECEPTOR.appendChild(PartyIdentification_ID_RECEPTOR);
	
		// atributos schemeID
		Attr attr_schemeID_RECEPTOR_id = document.createAttribute("schemeID");
		attr_schemeID_RECEPTOR_id.setValue(myCabecera.get_ident() );
		PartyIdentification_ID_RECEPTOR.setAttributeNode(attr_schemeID_RECEPTOR_id);

		// atributos schemeName
		Attr attr_schemeName_RECEPTOR_id = document.createAttribute("schemeName");
		attr_schemeName_RECEPTOR_id.setValue("Documento de Identidad");
		PartyIdentification_ID_RECEPTOR.setAttributeNode(attr_schemeName_RECEPTOR_id);

		// schemeAgencyName
		Attr attr_chemeAgencyName_RECEPTOR_id = document.createAttribute("schemeAgencyName");
		attr_chemeAgencyName_RECEPTOR_id.setValue("PE:SUNAT");
		PartyIdentification_ID_RECEPTOR.setAttributeNode(attr_chemeAgencyName_RECEPTOR_id);

		// schemeURI
		Attr attr_schemeURI_RECEPTOR_id = document.createAttribute("schemeURI");
		attr_schemeURI_RECEPTOR_id.setValue("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06");
		PartyIdentification_ID_RECEPTOR.setAttributeNode(attr_schemeURI_RECEPTOR_id);


		
		

		// cac:PartyLegalEntity
		Element PartyLegalEntity_RECEPTOR = document.createElement("cac:PartyLegalEntity");
		Party_RECEPTOR.appendChild(PartyLegalEntity_RECEPTOR);


		// cbc:RegistrationName
		Element RegistrationName_RECEPTOR = document.createElement("cbc:RegistrationName");
		RegistrationName_RECEPTOR.appendChild(document.createTextNode(myCabecera.get_nombre()));
		PartyLegalEntity_RECEPTOR.appendChild(RegistrationName_RECEPTOR);

	
		
		
		
			
		// cac:SellerSupplierParty
		Element SellerSupplierParty = document.createElement("cac:SellerSupplierParty");
		element.appendChild(SellerSupplierParty);

		
			
		// cac:Party
		Element SellerSupplierParty_Party = document.createElement("cac:Party");
		SellerSupplierParty.appendChild(SellerSupplierParty_Party);

		// cac:PostalAddress
		Element PostalAddress_Party_RECEPTOR = document.createElement("cac:PostalAddress");
		SellerSupplierParty_Party.appendChild(PostalAddress_Party_RECEPTOR);
		
		
		
		// cbc:ID
		Element ID_RECEPTOR = document.createElement("cbc:ID");
		ID_RECEPTOR.appendChild(document.createTextNode("NUMERO DE UBIGEO"));
		PostalAddress_Party_RECEPTOR.appendChild(ID_RECEPTOR);
	
		
		
	
		// <cbc:CitySubdivisionName>
		Element CitySubdivisionName_RECEPTOR = document.createElement("cbc:CitySubdivisionName");
		CitySubdivisionName_RECEPTOR.appendChild(document.createTextNode(""));
		PostalAddress_Party_RECEPTOR.appendChild(CitySubdivisionName_RECEPTOR);
		
		
		// <cbc:CityName><![CDATA[PIURA]]></cbc:CityName>
		Element CityName_RECEPTOR = document.createElement("cbc:CityName");
		Node cdata_CityName_RECEPTOR = document.createCDATASection($CIUDAD);
		CityName_RECEPTOR.appendChild(cdata_CityName_RECEPTOR);
		PostalAddress_Party_RECEPTOR.appendChild(CityName_RECEPTOR);
	
		// <cbc:CountrySubentity><![CDATA[PIURA]]></cbc:CountrySubentity>
		Element CountrySubentity_RECEPTOR = document.createElement("cbc:CountrySubentity");
		Node cdata_CountrySubentity_RECEPTOR = document.createCDATASection($DEPARTAMENTO);
		CountrySubentity_RECEPTOR.appendChild(cdata_CountrySubentity_RECEPTOR);
		PostalAddress_Party_RECEPTOR.appendChild(CountrySubentity_RECEPTOR);
		
		
		// <cbc:District><![CDATA[PIURA]]></cbc:District>
		Element District_RECEPTOR = document.createElement("cbc:District");
		Node cdata_District_RECEPTOR = document.createCDATASection($DISTRITO);
		District_EMISOR.appendChild(cdata_District_RECEPTOR);
		PostalAddress_Party_RECEPTOR.appendChild(District_RECEPTOR);
		
		// cac:AddressLine
		Element AddressLine_RECEPTOR = document.createElement("cac:AddressLine");
		PostalAddress_Party_RECEPTOR.appendChild(AddressLine_RECEPTOR);				
		
		//cbc:Line
		Element Line_RECEPTOR = document.createElement("cbc:Line");
		Node cdata_Line_RECEPTOR = document.createCDATASection($DIRECCION);
		Line_RECEPTOR.appendChild(cdata_Line_RECEPTOR);
		AddressLine_RECEPTOR.appendChild(Line_RECEPTOR);

		
		// cac:Country
		Element Country_RECEPTOR = document.createElement("cac:Country");
		PostalAddress_Party_RECEPTOR.appendChild(Country_RECEPTOR);				

		
		//cbc:IdentificationCode
		Element IdentificationCode_RECEPTOR = document.createElement("cbc:IdentificationCode");
		Node cdata_IdentificationCode_RECEPTOR = document.createCDATASection($PAIS);
		IdentificationCode_RECEPTOR.appendChild(cdata_IdentificationCode_RECEPTOR);
		Country_RECEPTOR.appendChild(IdentificationCode_RECEPTOR);
		
		

		
		
		
		
		
		
		
		
		/////////////////////////////////////////////////////////////////////////// DEFICNION DEL EMISOR






		// descuento globales
		//Invoice/cac:AllowanceCharge

//		if (myCabecera.get_desc_glo()>0) {

			// determinar base del descuento
			double _base_descuento=myCabecera.get_tot_vta_exo()+
					myCabecera.get_tot_vta_gra()+
					myCabecera.get_tot_vta_in();

			double _porcentaje_descuento=myCabecera.get_desc_glo()/_base_descuento;


			Element AllowanceCharge_header = document.createElement("cac:AllowanceCharge");
			element.appendChild(AllowanceCharge_header);


			// cbc:ChargeIndicator
			Element ChargeIndicator_Header = document.createElement("cbc:ChargeIndicator");
			ChargeIndicator_Header.appendChild(document.createTextNode("False"));
			AllowanceCharge_header.appendChild(ChargeIndicator_Header);


			// cbc:AllowanceChargeReasonCode
			Element AllowanceChargeReasonCode = document.createElement("cbc:AllowanceChargeReasonCode");
			AllowanceChargeReasonCode.appendChild(document.createTextNode("00"));
			AllowanceCharge_header.appendChild(AllowanceChargeReasonCode);

			
			// listName="Cargo/descuento" 
			Attr attr_AllowanceChargeReasonCode_listName = document.createAttribute("listName");	
			attr_AllowanceChargeReasonCode_listName.setValue("Cargo/descuento");
			AllowanceChargeReasonCode.setAttributeNode(attr_AllowanceChargeReasonCode_listName);

			
			// listAgencyName="PE:SUNAT"
			Attr attr_AllowanceChargeReasonCode_listAgencyName = document.createAttribute("listAgencyName");	
			attr_AllowanceChargeReasonCode_listAgencyName.setValue("PE:SUNAT");
			AllowanceChargeReasonCode.setAttributeNode(attr_AllowanceChargeReasonCode_listAgencyName);
			
			
			//listURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo53"
			Attr attr_AllowanceChargeReasonCode_listURI = document.createAttribute("listURI");	
			attr_AllowanceChargeReasonCode_listURI.setValue("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo53");
			AllowanceChargeReasonCode.setAttributeNode(attr_AllowanceChargeReasonCode_listURI);
					


			//cbc:MultiplierFactorNumeric
			Element MultiplierFactorNumeric = document.createElement("cbc:MultiplierFactorNumeric");
			MultiplierFactorNumeric.appendChild(document.createTextNode(""+_porcentaje_descuento));
			AllowanceCharge_header.appendChild(MultiplierFactorNumeric);



			// cbc:Amount
			Element Amount_Descuento = document.createElement("cbc:Amount");
			Amount_Descuento.appendChild(document.createTextNode(""+myCabecera.get_desc_glo()));
			AllowanceCharge_header.appendChild(Amount_Descuento);


			// currencyID="PEN"
			
			Attr attr_Amount_Descuento_Moneda = document.createAttribute("currencyID");	
			attr_Amount_Descuento_Moneda.setValue(myCabecera.get_moneda());
			Amount_Descuento.setAttributeNode(attr_Amount_Descuento_Moneda);


			
			// cbc:BaseAmount
			Element Amount_Base_Descuento = document.createElement("cbc:BaseAmount");
			Amount_Base_Descuento.appendChild(document.createTextNode(""+_base_descuento));
			AllowanceCharge_header.appendChild(Amount_Base_Descuento);

			
			// currencyID="PEN"
			Attr attr_Amount_Base_Descuento_Moneda = document.createAttribute("currencyID");	
			attr_Amount_Base_Descuento_Moneda.setValue(myCabecera.get_moneda());
			Amount_Base_Descuento.setAttributeNode(attr_Amount_Base_Descuento_Moneda);


//		}



		// //Invoice/cac:TaxTotal/cbc:TaxAmount

		// sacar la totallidad de los impouestos
		double _tax_total=myCabecera.get_sum_igv()+myCabecera.get_sum_isc();


		// cac:TaxTotal

		Element TaxTotal_Header = document.createElement("cac:TaxTotal");
		TaxTotal_Header.appendChild(document.createTextNode(""+_tax_total));
		element.appendChild(TaxTotal_Header);

		// cbc:TaxAmount
		Element TaxAmount_Header = document.createElement("cbc:TaxAmount");
		TaxAmount_Header.appendChild(document.createTextNode(""+_impuestos));
		TaxTotal_Header.appendChild(TaxAmount_Header);

		//alex

		Attr attr_Moneda = document.createAttribute("currencyID");	
		attr_Moneda.setValue(myCabecera.get_moneda());
		TaxAmount_Header.setAttributeNode(attr_Moneda);



		if (_gratuito==0) {

			//////////////////////
			// OPERACIONES GRAVADAS

			//Invoice/cac:TaxTotal/cac:TaxSubtotal/cbc:TaxableAmount @currencyID
			// cac:TaxSubtotal


			// cac:TaxSubtotal
			Element TaxSubtotal_Header_Gra = document.createElement("cac:TaxSubtotal");
			TaxSubtotal_Header_Gra.appendChild(document.createTextNode(""+_tax_total));
			TaxTotal_Header.appendChild(TaxSubtotal_Header_Gra);


			// cbc:TaxableAmount
			Element TaxableAmount_header_Gra = document.createElement("cbc:TaxableAmount");
			TaxableAmount_header_Gra.appendChild(document.createTextNode(""+_base_gravable));
			TaxSubtotal_Header_Gra.appendChild(TaxableAmount_header_Gra);

			Attr Atr_TaxableAmount_header_Gra = document.createAttribute("currencyID");	
			Atr_TaxableAmount_header_Gra.setValue(myCabecera.get_moneda());
			TaxableAmount_header_Gra.setAttributeNode(Atr_TaxableAmount_header_Gra);


			// cbc:TaxAmount 
			Element TaxAmount_header_Gra = document.createElement("cbc:TaxAmount");
			TaxAmount_header_Gra.appendChild(document.createTextNode(""+_igv));
			TaxSubtotal_Header_Gra.appendChild(TaxAmount_header_Gra);


			Attr Atr_TaxAmount_header_Gra = document.createAttribute("currencyID");	
			Atr_TaxAmount_header_Gra.setValue(myCabecera.get_moneda());
			TaxAmount_header_Gra.setAttributeNode(Atr_TaxAmount_header_Gra);



			// cac:TaxCategory
			Element TaxCategory_header_Gra = document.createElement("cac:TaxCategory");
			TaxSubtotal_Header_Gra.appendChild(TaxCategory_header_Gra);


			// id
			Element TaxCategory_header_ID0_Gra = document.createElement("cbc:ID");
			TaxCategory_header_ID0_Gra.appendChild(document.createTextNode("S"));
			TaxCategory_header_Gra.appendChild(TaxCategory_header_ID0_Gra);



			// schemeID
			Attr Atr_TaxCategory_schemeID_Gra = document.createAttribute("schemeID");	
			Atr_TaxCategory_schemeID_Gra.setValue("UN/ECE 5305");
			TaxCategory_header_ID0_Gra.setAttributeNode(Atr_TaxCategory_schemeID_Gra);

			//schemeName
			Attr Atr_TaxCategory_schemeName_Gra = document.createAttribute("schemeName");	
			Atr_TaxCategory_schemeName_Gra.setValue("Tax Category Identifier");
			TaxCategory_header_ID0_Gra.setAttributeNode(Atr_TaxCategory_schemeName_Gra);

			//schemeAgencyName
			Attr Atr_TaxCategory_schemeAgencyName_Gra = document.createAttribute("schemeAgencyName");	
			Atr_TaxCategory_schemeAgencyName_Gra.setValue("United Nations Economic Commission for Europe");
			TaxCategory_header_ID0_Gra.setAttributeNode(Atr_TaxCategory_schemeAgencyName_Gra);




			//cac:TaxScheme


			Element TaxScheme_header_Gra = document.createElement("cac:TaxScheme");
			TaxCategory_header_Gra.appendChild(TaxScheme_header_Gra);

			// id
			Element TaxScheme_header_id_Gra = document.createElement("cbc:ID");
			TaxScheme_header_id_Gra.appendChild(document.createTextNode("1000"));
			TaxScheme_header_Gra.appendChild(TaxScheme_header_id_Gra);

			//schemeAgencyName
			Attr Atr_TaxScheme_header_schemeID_Gra = document.createAttribute("schemeID");	
			Atr_TaxScheme_header_schemeID_Gra.setValue("UN/ECE 5153");
			TaxScheme_header_id_Gra.setAttributeNode(Atr_TaxScheme_header_schemeID_Gra);


			// schemeAgencyID
			Attr Atr_TaxScheme_header_schemeAgencyID_Gra = document.createAttribute("schemeAgencyID");	
			Atr_TaxScheme_header_schemeAgencyID_Gra.setValue("6");
			TaxScheme_header_id_Gra.setAttributeNode(Atr_TaxScheme_header_schemeAgencyID_Gra);



			// cbc:Name
			Element TaxScheme_header_Name_Gra = document.createElement("cbc:Name");
			TaxScheme_header_Name_Gra.appendChild(document.createTextNode("IGV"));
			TaxScheme_header_Gra.appendChild(TaxScheme_header_Name_Gra);


			// cbc:TaxTypeCode
			Element TaxScheme_header_TaxTypeCode_Gra = document.createElement("cbc:TaxTypeCode");
			TaxScheme_header_TaxTypeCode_Gra.appendChild(document.createTextNode("VAT"));
			TaxScheme_header_Gra.appendChild(TaxScheme_header_TaxTypeCode_Gra);



			///////////////////////////////////////////////////////////


			//////////////////////
			// OPERACIONES EXONERADAS

			///////////////////////////////////////////////////////////

			// cac:TaxSubtotal
			Element TaxSubtotal_Header_Exo = document.createElement("cac:TaxSubtotal");
			TaxSubtotal_Header_Exo.appendChild(document.createTextNode(""+_tax_total));
			TaxTotal_Header.appendChild(TaxSubtotal_Header_Exo);


			// cbc:TaxableAmount
			Element TaxableAmount_header_Exo = document.createElement("cbc:TaxableAmount");
			TaxableAmount_header_Exo.appendChild(document.createTextNode(""+_base_exonerada));
			TaxSubtotal_Header_Exo.appendChild(TaxableAmount_header_Exo);

			Attr Atr_TaxableAmount_header_Exo = document.createAttribute("currencyID");	
			Atr_TaxableAmount_header_Exo.setValue(myCabecera.get_moneda());
			TaxableAmount_header_Exo.setAttributeNode(Atr_TaxableAmount_header_Exo);


			// cbc:TaxAmount 
			Element TaxAmount_header_Exo = document.createElement("cbc:TaxAmount");
			TaxAmount_header_Exo.appendChild(document.createTextNode("0"));
			TaxSubtotal_Header_Exo.appendChild(TaxAmount_header_Exo);


			Attr Atr_TaxAmount_header_Exo = document.createAttribute("currencyID");	
			Atr_TaxAmount_header_Exo.setValue(myCabecera.get_moneda());
			TaxAmount_header_Exo.setAttributeNode(Atr_TaxAmount_header_Exo);



			// cac:TaxCategory
			Element TaxCategory_header_Exo = document.createElement("cac:TaxCategory");
			TaxSubtotal_Header_Exo.appendChild(TaxCategory_header_Exo);


			// id
			Element TaxCategory_header_ID0_Exo = document.createElement("cbc:ID");
			TaxCategory_header_ID0_Exo.appendChild(document.createTextNode("E"));
			TaxCategory_header_Exo.appendChild(TaxCategory_header_ID0_Exo);



			// schemeID
			Attr Atr_TaxCategory_schemeID_Exo = document.createAttribute("schemeID");	
			Atr_TaxCategory_schemeID_Exo.setValue("UN/ECE 5305");
			TaxCategory_header_ID0_Exo.setAttributeNode(Atr_TaxCategory_schemeID_Exo);

			//schemeName
			Attr Atr_TaxCategory_schemeName_Exo = document.createAttribute("schemeName");	
			Atr_TaxCategory_schemeName_Exo.setValue("Tax Category Identifier");
			TaxCategory_header_ID0_Exo.setAttributeNode(Atr_TaxCategory_schemeName_Exo);

			//schemeAgencyName
			Attr Atr_TaxCategory_schemeAgencyName_Exo = document.createAttribute("schemeAgencyName");	
			Atr_TaxCategory_schemeAgencyName_Exo.setValue("United Nations Economic Commission for Europe");
			TaxCategory_header_ID0_Exo.setAttributeNode(Atr_TaxCategory_schemeAgencyName_Exo);




			//cac:TaxScheme


			Element TaxScheme_header_Exo = document.createElement("cac:TaxScheme");
			TaxCategory_header_Exo.appendChild(TaxScheme_header_Exo);

			// id
			Element TaxScheme_header_id_Exo = document.createElement("cbc:ID");
			TaxScheme_header_id_Exo.appendChild(document.createTextNode("9997"));
			TaxScheme_header_Exo.appendChild(TaxScheme_header_id_Exo);

			//schemeAgencyName
			Attr Atr_TaxScheme_header_schemeID_Exo = document.createAttribute("schemeID");	
			Atr_TaxScheme_header_schemeID_Exo.setValue("UN/ECE 5153");
			TaxScheme_header_id_Exo.setAttributeNode(Atr_TaxScheme_header_schemeID_Exo);


			// schemeAgencyID
			Attr Atr_TaxScheme_header_schemeAgencyID_Exo = document.createAttribute("schemeAgencyID");	
			Atr_TaxScheme_header_schemeAgencyID_Exo.setValue("6");
			TaxScheme_header_id_Exo.setAttributeNode(Atr_TaxScheme_header_schemeAgencyID_Exo);



			// cbc:Name
			Element TaxScheme_header_Name_Exo = document.createElement("cbc:Name");
			TaxScheme_header_Name_Exo.appendChild(document.createTextNode("EXONERADO"));
			TaxScheme_header_Exo.appendChild(TaxScheme_header_Name_Exo);


			// cbc:TaxTypeCode
			Element TaxScheme_header_TaxTypeCode_Exo = document.createElement("cbc:TaxTypeCode");
			TaxScheme_header_TaxTypeCode_Exo.appendChild(document.createTextNode("VAT"));
			TaxScheme_header_Exo.appendChild(TaxScheme_header_TaxTypeCode_Exo);







			///////////////////////////////////////////////////////////

			/// inafectas




			//Invoice/cac:TaxTotal/cac:TaxSubtotal/cbc:TaxableAmount @currencyID
			// cac:TaxSubtotal
			///////////////////////////////////////////////////////////	


			// cac:TaxSubtotal
			Element TaxSubtotal_Header_Ina = document.createElement("cac:TaxSubtotal");
			TaxSubtotal_Header_Ina.appendChild(document.createTextNode(""+_tax_total));
			TaxTotal_Header.appendChild(TaxSubtotal_Header_Ina);


			// cbc:TaxableAmount
			Element TaxableAmount_header_Ina = document.createElement("cbc:TaxableAmount");
			TaxableAmount_header_Ina.appendChild(document.createTextNode(""+_base_inafecta));
			TaxSubtotal_Header_Ina.appendChild(TaxableAmount_header_Ina);

			Attr Atr_TaxableAmount_header_Ina = document.createAttribute("currencyID");	
			Atr_TaxableAmount_header_Ina.setValue(myCabecera.get_moneda());
			TaxableAmount_header_Ina.setAttributeNode(Atr_TaxableAmount_header_Ina);


			// cbc:TaxAmount 
			Element TaxAmount_header_Ina = document.createElement("cbc:TaxAmount");
			TaxAmount_header_Ina.appendChild(document.createTextNode("0"));
			TaxSubtotal_Header_Ina.appendChild(TaxAmount_header_Ina);


			Attr Atr_TaxAmount_header_Ina = document.createAttribute("currencyID");	
			Atr_TaxAmount_header_Ina.setValue(myCabecera.get_moneda());
			TaxAmount_header_Ina.setAttributeNode(Atr_TaxAmount_header_Ina);



			// cac:TaxCategory
			Element TaxCategory_header_Ina = document.createElement("cac:TaxCategory");
			TaxSubtotal_Header_Ina.appendChild(TaxCategory_header_Ina);


			// id
			Element TaxCategory_header_ID0_Ina = document.createElement("cbc:ID");
			TaxCategory_header_ID0_Ina.appendChild(document.createTextNode("O"));
			TaxCategory_header_Ina.appendChild(TaxCategory_header_ID0_Ina);



			// schemeID
			Attr Atr_TaxCategory_schemeID_Ina = document.createAttribute("schemeID");	
			Atr_TaxCategory_schemeID_Ina.setValue("UN/ECE 5305");
			TaxCategory_header_ID0_Ina.setAttributeNode(Atr_TaxCategory_schemeID_Ina);

			//schemeName
			Attr Atr_TaxCategory_schemeName_Ina = document.createAttribute("schemeName");	
			Atr_TaxCategory_schemeName_Ina.setValue("Tax Category Identifier");
			TaxCategory_header_ID0_Ina.setAttributeNode(Atr_TaxCategory_schemeName_Ina);

			//schemeAgencyName
			Attr Atr_TaxCategory_schemeAgencyName_Ina = document.createAttribute("schemeAgencyName");	
			Atr_TaxCategory_schemeAgencyName_Ina.setValue("United Nations Economic Commission for Europe");
			TaxCategory_header_ID0_Ina.setAttributeNode(Atr_TaxCategory_schemeAgencyName_Ina);




			//cac:TaxScheme


			Element TaxScheme_header_Ina = document.createElement("cac:TaxScheme");
			TaxCategory_header_Ina.appendChild(TaxScheme_header_Ina);

			// id
			Element TaxScheme_header_id_Ina = document.createElement("cbc:ID");
			TaxScheme_header_id_Ina.appendChild(document.createTextNode("9998"));
			TaxScheme_header_Ina.appendChild(TaxScheme_header_id_Ina);

			//schemeAgencyName
			Attr Atr_TaxScheme_header_schemeID_Ina = document.createAttribute("schemeID");	
			Atr_TaxScheme_header_schemeID_Ina.setValue("UN/ECE 5153");
			TaxScheme_header_id_Ina.setAttributeNode(Atr_TaxScheme_header_schemeID_Ina);


			// schemeAgencyID
			Attr Atr_TaxScheme_header_schemeAgencyID_Ina = document.createAttribute("schemeAgencyID");	
			Atr_TaxScheme_header_schemeAgencyID_Ina.setValue("6");
			TaxScheme_header_id_Ina.setAttributeNode(Atr_TaxScheme_header_schemeAgencyID_Ina);



			// cbc:Name
			Element TaxScheme_header_Name_Ina = document.createElement("cbc:Name");
			TaxScheme_header_Name_Ina.appendChild(document.createTextNode("INAFECTO"));
			TaxScheme_header_Ina.appendChild(TaxScheme_header_Name_Ina);


			// cbc:TaxTypeCode
			Element TaxScheme_header_TaxTypeCode_Ina = document.createElement("cbc:TaxTypeCode");
			TaxScheme_header_TaxTypeCode_Ina.appendChild(document.createTextNode("FRE"));
			TaxScheme_header_Ina.appendChild(TaxScheme_header_TaxTypeCode_Ina);






			///////////////////////////////////////////////////////////


		}






		////// gratuitas...//////////////////////////////77
		// myAca.get_mtoOperGratuitas()



		if (_gratuito>0) {



			// cac:TaxSubtotal
			Element TaxSubtotal_Header_Gratis = document.createElement("cac:TaxSubtotal");
			TaxSubtotal_Header_Gratis.appendChild(document.createTextNode(""+_tax_total));
			TaxTotal_Header.appendChild(TaxSubtotal_Header_Gratis);


			// cbc:TaxableAmount
			Element TaxableAmount_header_Gratis = document.createElement("cbc:TaxableAmount");
			TaxableAmount_header_Gratis.appendChild(document.createTextNode(""+_gratuito));
			TaxSubtotal_Header_Gratis.appendChild(TaxableAmount_header_Gratis);

			Attr Atr_TaxableAmount_header_Gratis = document.createAttribute("currencyID");	
			Atr_TaxableAmount_header_Gratis.setValue(myCabecera.get_moneda());
			TaxableAmount_header_Gratis.setAttributeNode(Atr_TaxableAmount_header_Gratis);


			// cbc:TaxAmount 
			Element TaxAmount_header_Gratis = document.createElement("cbc:TaxAmount");
			TaxAmount_header_Gratis.appendChild(document.createTextNode("0"));
			TaxSubtotal_Header_Gratis.appendChild(TaxAmount_header_Gratis);


			Attr Atr_TaxAmount_header_Gratis = document.createAttribute("currencyID");	
			Atr_TaxAmount_header_Gratis.setValue(myCabecera.get_moneda());
			TaxAmount_header_Gratis.setAttributeNode(Atr_TaxAmount_header_Gratis);



			// cac:TaxCategory
			Element TaxCategory_header_Gratis = document.createElement("cac:TaxCategory");
			TaxSubtotal_Header_Gratis.appendChild(TaxCategory_header_Gratis);


			// id
			Element TaxCategory_header_ID0_Gratis = document.createElement("cbc:ID");
			TaxCategory_header_ID0_Gratis.appendChild(document.createTextNode("O"));
			TaxCategory_header_Gratis.appendChild(TaxCategory_header_ID0_Gratis);



			// schemeID
			Attr Atr_TaxCategory_schemeID_Gratis = document.createAttribute("schemeID");	
			Atr_TaxCategory_schemeID_Gratis.setValue("UN/ECE 5305");
			TaxCategory_header_ID0_Gratis.setAttributeNode(Atr_TaxCategory_schemeID_Gratis);

			//schemeName
			Attr Atr_TaxCategory_schemeName_Gratis = document.createAttribute("schemeName");	
			Atr_TaxCategory_schemeName_Gratis.setValue("Tax Category Identifier");
			TaxCategory_header_ID0_Gratis.setAttributeNode(Atr_TaxCategory_schemeName_Gratis);

			//schemeAgencyName
			Attr Atr_TaxCategory_schemeAgencyName_Gratis = document.createAttribute("schemeAgencyName");	
			Atr_TaxCategory_schemeAgencyName_Gratis.setValue("United Nations Economic Commission for Europe");
			TaxCategory_header_ID0_Gratis.setAttributeNode(Atr_TaxCategory_schemeAgencyName_Gratis);




			//cac:TaxScheme


			Element TaxScheme_header_Gratis = document.createElement("cac:TaxScheme");
			TaxCategory_header_Gratis.appendChild(TaxScheme_header_Gratis);

			// id
			Element TaxScheme_header_id_Gratis = document.createElement("cbc:ID");
			TaxScheme_header_id_Gratis.appendChild(document.createTextNode("9998"));
			TaxScheme_header_Gratis.appendChild(TaxScheme_header_id_Gratis);

			//schemeAgencyName
			Attr Atr_TaxScheme_header_schemeID_Gratis = document.createAttribute("schemeID");	
			Atr_TaxScheme_header_schemeID_Gratis.setValue("UN/ECE 5153");
			TaxScheme_header_id_Gratis.setAttributeNode(Atr_TaxScheme_header_schemeID_Gratis);


			// schemeAgencyID
			Attr Atr_TaxScheme_header_schemeAgencyID_Gratis = document.createAttribute("schemeAgencyID");	
			Atr_TaxScheme_header_schemeAgencyID_Gratis.setValue("6");
			TaxScheme_header_id_Gratis.setAttributeNode(Atr_TaxScheme_header_schemeAgencyID_Gratis);



			// cbc:Name
			Element TaxScheme_header_Name_Gratis = document.createElement("cbc:Name");
			TaxScheme_header_Name_Gratis.appendChild(document.createTextNode("INAFECTO"));
			TaxScheme_header_Gratis.appendChild(TaxScheme_header_Name_Gratis);


			// cbc:TaxTypeCode
			Element TaxScheme_header_TaxTypeCode_Gratis = document.createElement("cbc:TaxTypeCode");
			TaxScheme_header_TaxTypeCode_Gratis.appendChild(document.createTextNode("FRE"));
			TaxScheme_header_Gratis.appendChild(TaxScheme_header_TaxTypeCode_Gratis);







			///////////////////////////////////////////////////////////





		}





		// total de la venta ////////////
		///Invoice/cac:LegalMonetaryTotal/cbc:LineExtensionAmount

		// cac:LegalMonetaryTotal
		Element LegalMonetaryTotal_Header = document.createElement("cac:LegalMonetaryTotal");
		element.appendChild(LegalMonetaryTotal_Header);




		// cbc:LineExtensionAmount   total sin impuestos ni descuentos
		Element LineExtensionAmount_Header = document.createElement("cbc:LineExtensionAmount");
		LineExtensionAmount_Header.appendChild(document.createTextNode(""+_base));
		LegalMonetaryTotal_Header.appendChild(LineExtensionAmount_Header);

		Attr Atr_LineExtensionAmount_Header = document.createAttribute("currencyID");	
		Atr_LineExtensionAmount_Header.setValue(myCabecera.get_moneda());
		LineExtensionAmount_Header.setAttributeNode(Atr_LineExtensionAmount_Header);



		// 31 cbc:TaxInclusiveAmount  cantidad tal como se cobra. (neto)
		Element TaxInclusiveAmount_Header = document.createElement("cbc:TaxInclusiveAmount");
		TaxInclusiveAmount_Header.appendChild(document.createTextNode(""+_neto));
		LegalMonetaryTotal_Header.appendChild(TaxInclusiveAmount_Header);

		Attr Atr_TaxInclusiveAmount = document.createAttribute("currencyID");	
		Atr_TaxInclusiveAmount.setValue(myCabecera.get_moneda());
		TaxInclusiveAmount_Header.setAttributeNode(Atr_TaxInclusiveAmount);



	//	if (_descuentos>0) {

			// 32 cbc:AllowanceTotalAmount  descuentos
			Element AllowanceTotalAmount = document.createElement("cbc:AllowanceTotalAmount");
			AllowanceTotalAmount.appendChild(document.createTextNode(""+_descuentos));
			LegalMonetaryTotal_Header.appendChild(AllowanceTotalAmount);

			Attr Atr_AllowanceTotalAmount = document.createAttribute("currencyID");	
			Atr_AllowanceTotalAmount.setValue(myCabecera.get_moneda());
			AllowanceTotalAmount.setAttributeNode(Atr_AllowanceTotalAmount);

	//	}


		// cbc:ChargeTotalAmount
			
			Element ChargeTotalAmount = document.createElement("cbc:ChargeTotalAmount");
			ChargeTotalAmount.appendChild(document.createTextNode(""));
			LegalMonetaryTotal_Header.appendChild(ChargeTotalAmount);

			Attr Atr_ChargeTotalAmount = document.createAttribute("currencyID");	
			Atr_ChargeTotalAmount.setValue(myCabecera.get_moneda());
			ChargeTotalAmount.setAttributeNode(Atr_ChargeTotalAmount);
		
			
			

		double _PayableAmount= _neto+_descuentos+_anticipos;


		// cbc:PayableAmount
		Element PayableAmount = document.createElement("cbc:PayableAmount");
		PayableAmount.appendChild(document.createTextNode(""+_neto));
		LegalMonetaryTotal_Header.appendChild(PayableAmount);

		Attr Atr_PayableAmount = document.createAttribute("currencyID");	
		Atr_PayableAmount.setValue(myCabecera.get_moneda());
		PayableAmount.setAttributeNode(Atr_PayableAmount);




		/*
		for (int linea=1; linea<_counterRel; linea++) {	

			try {
				String _status = mydocumentos_relacionados[linea].get_indDocRelacionado();

				if (mydocumentos_relacionados[linea].get_indDocRelacionado().equals("2")) {
					// cac:DespatchDocumentReference
					////////////////////////////////////////////////////////////////////


					// cac:InvoiceLine




					// cac:PrepaidPayment
					Element PrepaidPayment = document.createElement("cac:PrepaidPayment");
					element.appendChild(PrepaidPayment);

					// PrepaidPaymentID
					Element PrepaidPaymentID = document.createElement("cbc:ID");
					PrepaidPaymentID.appendChild(document.createTextNode(mydocumentos_relacionados[linea].get_numDocRelacionado()));
					PrepaidPayment.appendChild(PrepaidPaymentID);

					// cbc:PaidAmount
					Element PaidAmount_rel = document.createElement("cbc:PaidAmount");
					PaidAmount_rel.appendChild(document.createTextNode(mydocumentos_relacionados[linea].get_mtoDocRelacionado()));
					PrepaidPayment.appendChild(PaidAmount_rel);


					// currencyID
					Attr currencyID_Pre = document.createAttribute("currencyID");
					currencyID_Pre.setValue(myCabecera.get_moneda());
					PaidAmount_rel.setAttributeNode(currencyID_Pre);

					// cbc:InstructionID
					Element InstructionID = document.createElement("cbc:InstructionID");
					InstructionID.appendChild(document.createTextNode(mydocumentos_relacionados[linea].get_numDocEmisor()));
					PrepaidPayment.appendChild(InstructionID);





				}
			} catch (Exception e) {

				//	e.printStackTrace();
			}

		}		
		///////



		 */


		

		for (int linea=1; linea<_counterDet; linea++) {	

			// cac:InvoiceLine
			Element InvoiceLine = document.createElement("cac:InvoiceLine");
			element.appendChild(InvoiceLine);		

			// cbc:ID
			Element ID_Item = document.createElement("cbc:ID");
			ID_Item.appendChild(document.createTextNode(""+linea));
			InvoiceLine.appendChild(ID_Item);		



			// CANTIDAD Y UNIDAD DE MEDIDA
			// cbc:InvoicedQuantity 
			Element InvoicedQuantity = document.createElement("cbc:InvoicedQuantity");
			InvoicedQuantity.appendChild(document.createTextNode(""+myDetalle[linea].get_cantidad()));
			InvoiceLine.appendChild(InvoicedQuantity);

			// unitCode
			Attr Atr_unitCode = document.createAttribute("unitCode");	
			Atr_unitCode.setValue(myDetalle[linea].get_unidad_med());
			InvoicedQuantity.setAttributeNode(Atr_unitCode);

			// unitCodeListID
			Attr Atr_unitCodeListID = document.createAttribute("unitCodeListID");	
			Atr_unitCodeListID.setValue("UN/ECE rec 20");
			InvoicedQuantity.setAttributeNode(Atr_unitCodeListID);

			// unitCodeListAgencyName
			Attr Atr_unitCodeListAgencyName = document.createAttribute("unitCodeListAgencyName");	
			Atr_unitCodeListAgencyName.setValue("United Nations Economic Commission for Europe");
			InvoicedQuantity.setAttributeNode(Atr_unitCodeListAgencyName);



			// cbc:LineExtensionAmount
			Element LineExtensionAmount = document.createElement("cbc:LineExtensionAmount");
			LineExtensionAmount.appendChild(document.createTextNode(Formato.GranDinero(myDetalle[linea].get_valor_tot())));
			InvoiceLine.appendChild(LineExtensionAmount);

			// currencyID
			Attr Atr_currencyID = document.createAttribute("currencyID");	
			Atr_currencyID.setValue(myCabecera.get_moneda());
			LineExtensionAmount.setAttributeNode(Atr_currencyID);




			// 38 Precio de venta unitario por �tem y c�digo. 
			// Obligatorio. Dentro  del 
			// �mbito  tributario,  es  el  monto  correspondiente  al  precio  unitario 
			// facturado  del  bien  vendido  o  servicio  vendido.  Este  monto  es  la  suma  total  que  queda 
			//obligado a pagar el adquirente o usuario por cada bien o servicio. Esto incluye los tributos 
			// (IGV,  ISC  y otros  Tributos)  y  la  deducci�n  de  descuentos  por  �tem.

			// cac:PricingReference
			Element PricingReference = document.createElement("cac:PricingReference");
			InvoiceLine.appendChild(PricingReference);

			// cac:AlternativeConditionPrice
			Element AlternativeConditionPrice = document.createElement("cac:AlternativeConditionPrice");
			PricingReference.appendChild(AlternativeConditionPrice);


			// cbc:PriceAmount 
			Element PriceAmount_Detail = document.createElement("cbc:PriceAmount");
			PriceAmount_Detail.appendChild(document.createTextNode(Formato.GranDinero(myDetalle[linea].get_valor_tot())));
			AlternativeConditionPrice.appendChild(PriceAmount_Detail);

			// currencyID
			Attr Atr_PriceAmount_Detail = document.createAttribute("currencyID");	
			Atr_PriceAmount_Detail.setValue(myCabecera.get_moneda());
			PriceAmount_Detail.setAttributeNode(Atr_PriceAmount_Detail);


			String _codigo_precio="01";
			if (_gratuito>0) {
				_codigo_precio="02";
			}

			// cbc:PriceTypeCode
			Element PriceTypeCode = document.createElement("cbc:PriceTypeCode");
			PriceTypeCode.appendChild(document.createTextNode(_codigo_precio));
			AlternativeConditionPrice.appendChild(PriceTypeCode);



			// listName
			Attr Atr_listName = document.createAttribute("listName");	
			Atr_listName.setValue("SUNAT:Indicador de Tipo de Precio");
			PriceTypeCode.setAttributeNode(Atr_listName);

			// listAgencyName
			Attr Atr_listAgencyName = document.createAttribute("listAgencyName");	
			Atr_listAgencyName.setValue("PE:SUNAT");
			PriceTypeCode.setAttributeNode(Atr_listAgencyName);

			//listURI
			Attr Atr_listURI = document.createAttribute("listURI");	
			Atr_listURI.setValue("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo16");
			PriceTypeCode.setAttributeNode(Atr_listURI);


			///////////////////////////////////////////////////////
			// 40 Descuentos por �tem
			// cac:AllowanceCharge

			if (myDetalle[linea].get_desc_unit()>0) {

				Element AllowanceCharge_Detail = document.createElement("cac:AllowanceCharge");
				InvoiceLine.appendChild(AllowanceCharge_Detail);

				// cbc:ChargeIndicator
				Element ChargeIndicator = document.createElement("cbc:ChargeIndicator");
				ChargeIndicator.appendChild(document.createTextNode("False"));
				AllowanceCharge_Detail.appendChild(ChargeIndicator);

				// cbc:AllowanceChargeReasonCode
				Element AllowanceChargeReasonCode_01 = document.createElement("cbc:AllowanceChargeReasonCode");
				AllowanceChargeReasonCode_01.appendChild(document.createTextNode("00"));
				AllowanceCharge_Detail.appendChild(AllowanceChargeReasonCode_01);


				double _MultiplierFactorNumeric=myDetalle[linea].get_desc_unit()/myDetalle[linea].get_valor_tot();


				// cbc:MultiplierFactorNumeric
				Element MultiplierFactorNumeric_01 = document.createElement("cbc:MultiplierFactorNumeric");
				MultiplierFactorNumeric_01.appendChild(document.createTextNode(Formato._xml(_MultiplierFactorNumeric)));
				AllowanceCharge_Detail.appendChild(MultiplierFactorNumeric_01);

				// cbc:Amount 
				Element Amount_Detail = document.createElement("cbc:Amount");
				Amount_Detail.appendChild(document.createTextNode(""+myDetalle[linea].get_desc_unit()));
				AllowanceCharge_Detail.appendChild(Amount_Detail);

				// currencyID
				Attr Atr_descuento_Detail = document.createAttribute("currencyID");	
				Atr_descuento_Detail.setValue(myCabecera.get_moneda());
				Amount_Detail.setAttributeNode(Atr_descuento_Detail);

				// cbc:BaseAmount 
				Element BaseAmount_Detail = document.createElement("cbc:BaseAmount");
				BaseAmount_Detail.appendChild(document.createTextNode(""+myDetalle[linea].get_valor_tot()));
				AllowanceCharge_Detail.appendChild(BaseAmount_Detail);

				// currencyID
				Attr Atr_Base_descuento_Detail = document.createAttribute("currencyID");	
				Atr_Base_descuento_Detail.setValue(myCabecera.get_moneda());
				BaseAmount_Detail.setAttributeNode(Atr_Base_descuento_Detail);

				
				
			}
				
				
				
				
				
				
				
				// //Invoice/cac:TaxTotal/cbc:TaxAmount

				// sacar la totallidad de los impouestos
				double _igv_detalle=myDetalle[linea].get_igv_unit();
				double _base_gravable_detalle=myDetalle[linea].get_valor_tot();
				String _tipo_igv=myDetalle[linea].get_afec_igv();
				
				


				// cac:TaxTotal

				Element TaxTotal_Detalle = document.createElement("cac:TaxTotal");
				//TaxTotal_Detalle.appendChild(document.createTextNode(""+_igv_detalle));
				InvoiceLine.appendChild(TaxTotal_Detalle);

				// cac:TaxAmount
				Element TaxAmount_Detalle = document.createElement("cac:TaxAmount");
				TaxAmount_Detalle.appendChild(document.createTextNode(""+_igv_detalle));
				TaxTotal_Detalle.appendChild(TaxAmount_Detalle);

				//alex

				Attr attr_Moneda_Detalle = document.createAttribute("currencyID");	
				attr_Moneda_Detalle.setValue(myCabecera.get_moneda());
				TaxAmount_Detalle.setAttributeNode(attr_Moneda_Detalle);



				

					//////////////////////
					// OPERACIONES GRAVADAS

					//Invoice/cac:TaxTotal/cac:TaxSubtotal/cbc:TaxableAmount @currencyID
					// cac:TaxSubtotal


					// cac:TaxSubtotal
					Element TaxSubtotal_detalle_Gra = document.createElement("cac:TaxSubtotal");
			//		TaxSubtotal_detalle_Gra.appendChild(document.createTextNode(""+_igv_detalle));
					TaxTotal_Detalle.appendChild(TaxSubtotal_detalle_Gra);


					// cbc:TaxableAmount
					Element TaxableAmount_detalle_Gra = document.createElement("cbc:TaxableAmount");
					TaxableAmount_detalle_Gra.appendChild(document.createTextNode(""+_base_gravable_detalle));
					TaxSubtotal_detalle_Gra.appendChild(TaxableAmount_detalle_Gra);

					Attr Atr_TaxableAmount_detalle_Gra = document.createAttribute("currencyID");	
					Atr_TaxableAmount_detalle_Gra.setValue(myCabecera.get_moneda());
					TaxableAmount_detalle_Gra.setAttributeNode(Atr_TaxableAmount_detalle_Gra);


					// cbc:TaxAmount 
					Element TaxAmount_detalle_Gra = document.createElement("cbc:TaxAmount");
					TaxAmount_detalle_Gra.appendChild(document.createTextNode(""+_igv_detalle));
					TaxSubtotal_detalle_Gra.appendChild(TaxAmount_detalle_Gra);


					Attr Atr_TaxAmount_detalle_Gra = document.createAttribute("currencyID");	
					Atr_TaxAmount_detalle_Gra.setValue(myCabecera.get_moneda());
					TaxAmount_detalle_Gra.setAttributeNode(Atr_TaxAmount_detalle_Gra);



					// cac:TaxCategory
					Element TaxCategory_detalle_Gra = document.createElement("cac:TaxCategory");
					TaxSubtotal_detalle_Gra.appendChild(TaxCategory_detalle_Gra);


					// id
					Element TaxCategory_detalle_Gra_ID = document.createElement("cbc:ID");
					TaxCategory_detalle_Gra_ID.appendChild(document.createTextNode("S"));
					TaxCategory_detalle_Gra.appendChild(TaxCategory_detalle_Gra_ID);



					// schemeID
					Attr Atr_TaxCategory_schemeID_Gra = document.createAttribute("schemeID");	
					Atr_TaxCategory_schemeID_Gra.setValue("UN/ECE 5305");
					TaxCategory_detalle_Gra_ID.setAttributeNode(Atr_TaxCategory_schemeID_Gra);

					//schemeName
					Attr Atr_TaxCategory_schemeName_Gra = document.createAttribute("schemeName");	
					Atr_TaxCategory_schemeName_Gra.setValue("Tax Category Identifier");
					TaxCategory_detalle_Gra_ID.setAttributeNode(Atr_TaxCategory_schemeName_Gra);

					//schemeAgencyName
					Attr Atr_TaxCategory_schemeAgencyName_Gra = document.createAttribute("schemeAgencyName");	
					Atr_TaxCategory_schemeAgencyName_Gra.setValue("United Nations Economic Commission for Europe");
					TaxCategory_detalle_Gra_ID.setAttributeNode(Atr_TaxCategory_schemeAgencyName_Gra);


					// cbc:Percent
					
					Element Percent_igv = document.createElement("cbc:Percent");
					Percent_igv.appendChild(document.createTextNode($PORCENTAJE_IGV));
					TaxCategory_detalle_Gra.appendChild(Percent_igv);

					// cbc:TaxExemptionReasonCode
					Element TaxExemptionReasonCode = document.createElement("cbc:TaxExemptionReasonCode");
					TaxExemptionReasonCode.appendChild(document.createTextNode(_tipo_igv));
					TaxCategory_detalle_Gra.appendChild(TaxExemptionReasonCode);

					
					// listAgencyName
					Attr Atr_TaxExemptionReasonCode_slistAgencyName_Gra = document.createAttribute("listAgencyName");	
					Atr_TaxExemptionReasonCode_slistAgencyName_Gra.setValue("PE:SUNAT");
					TaxExemptionReasonCode.setAttributeNode(Atr_TaxExemptionReasonCode_slistAgencyName_Gra);

					//slistName
					Attr Atr_TaxExemptionReasonCode_listName_Gra = document.createAttribute("listName");	
					Atr_TaxExemptionReasonCode_listName_Gra.setValue("SUNAT:Codigode Tipo de Afectaci�n del IGV");
					TaxExemptionReasonCode.setAttributeNode(Atr_TaxExemptionReasonCode_listName_Gra);

					//listUR
					Attr Atr_TaxExemptionReasonCode_listUR_Gra = document.createAttribute("listURI");	
					Atr_TaxExemptionReasonCode_listUR_Gra.setValue("United Nations Economic Commission for Europe");
					TaxExemptionReasonCode.setAttributeNode(Atr_TaxExemptionReasonCode_listUR_Gra);

					
					
					//cac:TaxScheme
					Element TaxScheme_detail = document.createElement("cac:TaxScheme");
					TaxScheme_detail.appendChild(document.createTextNode(_tipo_igv));
					TaxCategory_detalle_Gra.appendChild(TaxScheme_detail);

					// cbc:ID
					Element TaxScheme_detalle_id_Gra = document.createElement("cbc:ID");
					TaxScheme_detalle_id_Gra.appendChild(document.createTextNode("1000"));
					TaxScheme_detail.appendChild(TaxScheme_detalle_id_Gra);


					// schemeID="UN/ECE 5153"
					Attr Atr_id_schemeID_Gra = document.createAttribute("schemeID");	
					Atr_id_schemeID_Gra.setValue("UN/ECE 5153");
					TaxScheme_detalle_id_Gra.setAttributeNode(Atr_id_schemeID_Gra);

					
					
					//  schemeName="Tax Scheme Identifier"
					Attr Atr_id_schemeName_Gra = document.createAttribute("schemeName");	
					Atr_id_schemeName_Gra.setValue("Tax Scheme Identifier");
					TaxScheme_detalle_id_Gra.setAttributeNode(Atr_id_schemeName_Gra);
					
					
					
					// schemeAgencyName="United Nations Economic Commission for Europe"
					Attr Atr_id_schemeAgencyName_Gra = document.createAttribute("schemeAgencyName");	
					Atr_id_schemeAgencyName_Gra.setValue("Tax Scheme IdentifierUnited Nations Economic Commission for Europe");
					TaxScheme_detalle_id_Gra.setAttributeNode(Atr_id_schemeAgencyName_Gra);
					
					
					
					
					// cbc:Name
					Element TaxScheme_detalle_Name_Gra = document.createElement("cbc:Name");
					TaxScheme_detalle_Name_Gra.appendChild(document.createTextNode("IGV"));
					TaxScheme_detail.appendChild(TaxScheme_detalle_Name_Gra);


					// cbc:TaxTypeCode
					Element TaxScheme_detalle_TaxTypeCode_Gra = document.createElement("cbc:TaxTypeCode");
					TaxScheme_detalle_TaxTypeCode_Gra.appendChild(document.createTextNode("VAT"));
					TaxScheme_detail.appendChild(TaxScheme_detalle_TaxTypeCode_Gra);








					///////////////////////////////////////////////////////////

				
				
					// cac:Item
					
					Element Item = document.createElement("cac:Item");
					InvoiceLine.appendChild(Item);

					// cbc:Description
					Element Description = document.createElement("cbc:Description");
					Description.appendChild(document.createTextNode(myDetalle[linea].get_descrip()));
					Item.appendChild(Description);

					// cac:SellersItemIdentification
					Element SellersItemIdentification = document.createElement("cac:SellersItemIdentification");
					//SellersItemIdentification.appendChild(document.createTextNode(""));
					Item.appendChild(SellersItemIdentification);
			
					//cbc:ID
					Element SellersItemIdentification_ID = document.createElement("cbc:ID");
					SellersItemIdentification_ID.appendChild(document.createTextNode(myDetalle[linea].get_producto()));
					SellersItemIdentification.appendChild(SellersItemIdentification_ID);

					// cac:AdditionalItemProperty
					Element AdditionalItemProperty = document.createElement("cac:AdditionalItemProperty");
					AdditionalItemProperty.appendChild(document.createTextNode(""));
					Item.appendChild(AdditionalItemProperty);
			
					
					// cbc:Name
					Element Name_item = document.createElement("cbc:Name");
					Name_item.appendChild(document.createTextNode(myDetalle[linea].get_producto()));
					AdditionalItemProperty.appendChild(Name_item);
			

					//cbc:NameCode
					Element NameCode_item = document.createElement("cbc:NameCode");
					NameCode_item.appendChild(document.createTextNode("70000"));
					AdditionalItemProperty.appendChild(NameCode_item);
					
					
					// listName="Propiedad del item"
					Attr Atr_listName_detalle = document.createAttribute("listName");	
					Atr_listName_detalle.setValue("NameCode_item");
					NameCode_item.setAttributeNode(Atr_listName_detalle);
					

					//listAgencyName="PE:SUNAT"
					Attr Atr_listNamelistAgencyName_detalle = document.createAttribute("listAgencyName");	
					Atr_listNamelistAgencyName_detalle.setValue("PE:SUNAT");
					NameCode_item.setAttributeNode(Atr_listNamelistAgencyName_detalle);
		
					
					//listURI="urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo55"
					Attr Atr_listURI_detalle = document.createAttribute("listURI");	
					Atr_listURI_detalle.setValue("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo5");
					NameCode_item.setAttributeNode(Atr_listURI_detalle);
		
					
					//cbc:Value
					Element Value_item = document.createElement("cbc:Value");
					Value_item.appendChild(document.createTextNode("RRRTTT            "));
					AdditionalItemProperty.appendChild(Value_item);
					
					
					
					// cac:Price
					Element Price_item = document.createElement("cac:Price");
					InvoiceLine.appendChild(Price_item);
			
					// cbc:PriceAmount
					Element PriceAmount_item = document.createElement("cbc:PriceAmount");
					PriceAmount_item.appendChild(document.createTextNode(Formato.GranDinero(myDetalle[linea].get_valor_unit())));
					Price_item.appendChild(PriceAmount_item);
		
					
					

					Attr Atr_PriceAmount_item = document.createAttribute("currencyID");	
					Atr_PriceAmount_item.setValue(myCabecera.get_moneda());
					PriceAmount_item.setAttributeNode(Atr_PriceAmount_item);

					
					
					
			// 41 Cargos por �tem
			// cac:AllowanceCharge

			// esta dato falta de implementa
			// si tienes tienes tiempo terminalo





			/////////////////////////
			// IGV DEL DETALLE
			/////////////////////////

			

	


		}




	//	TransformerFactory tf = TransformerFactory.newInstance();
	//	Transformer transformer = tf.newTransformer();
	//	transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");


		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		DOMSource source = new DOMSource(document);
		StreamResult streamResult = new StreamResult(new File($FILE_NAME_XML));
		transformer.transform(source, streamResult);
		
		
		File archivo_letras=new File($FILE_NAME_CANTIDAD_LETRAS);
		archivo_letras.delete();
		FileWriter chanel_write=new FileWriter(archivo_letras,true);
		chanel_write.write(_letra_numero);
		chanel_write.close();
		
		
		




		// _letra_numero	

	}

	public static void readParam(String _file_parametros) throws Exception {

		InputStream is_param = new FileInputStream(_file_parametros);
		DataSource ds_param = new ByteArrayDataSource(is_param,"application/octet-stream");
		DataHandler dhandler_param = new DataHandler(ds_param);

		Object content = dhandler_param.getContent();

		BufferedReader br = null;
		int _cont=0;

		try {

			String sCurrentLine;
			br = new BufferedReader(new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {

				myParam[_cont]=sCurrentLine;
				_cont++;

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



}