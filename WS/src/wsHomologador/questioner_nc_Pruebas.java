package wsHomologador;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
//import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.activation.DataHandler;
import javax.activation.DataSource;

import org.apache.soap.util.mime.ByteArrayDataSource;




public class questioner_nc_Pruebas {
	public static String[] myParam = new String[1];
	public static String $FILE_NAME = "";
//	public static String $RUTA = "";
	public static String $FIRMA_CONECTA = "";
	public static parametros misParametros = new parametros();
	
	
	// RUTAS Y ARCHIVOS
	public static String $PATH_BASE="";
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
	public static String $RUC_INIT = "";
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
		
	// OTROS
	public static String $CODIGO_HASH="";
	public static param misParam = new param();	
	
	
	public static void main(String[] args) throws Exception {
		
		
		System.out.println("Conector Notas de Credito Version 2.0");
		
		
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

		
		String _win="\\";
	    String _lin="/";
	    $RUC_INIT=args[0].substring(0,11);
	    
	    
	    String _ruta_param=_path+_win+"data"+_win+$RUC_INIT+_win+"certificados"+_lin+$RUC_INIT+"-param.fg";
		readParam(_ruta_param);
	
		
		
		
		
		// separa los campos
        int _tam_param = myParam[0].length();
        String _car="";
        String _cadena="";
        int _num=0;
        
        
        for(int i=0; i<_tam_param; i++) {
        	_car = myParam[0].substring(i,i+1);
        	      	
        	if (!"|".equals(_car)) {
        		_cadena=_cadena+_car;
        	} else {
        		_num++;
        		        		
        		if (_num==1) {misParam.set_os_param(_cadena);}
        		if (_num==2) {misParam.set_ruc_param(_cadena);}
        		if (_num==3) {misParam.set_razon_social_param(_cadena);}
        		if (_num==4) {misParam.set_codigo_postal_param(_cadena);}
        		if (_num==5) {misParam.set_direccion_param(_cadena);}
        		if (_num==6) {misParam.set_ciudad_param(_cadena);}
        		if (_num==7) {misParam.set_pais_param(_cadena);}
        		if (_num==8) {misParam.set_keystore_param(_cadena);}
        		if (_num==9) {misParam.set_password_keystore_param(_cadena);}
        		if (_num==10) {misParam.set_password_certificado_param(_cadena);}
        		if (_num==11) {misParam.set_alias_param(_cadena);}
        		if (_num==12) {misParam.set_usuario_secundario_param(_cadena);}
        		if (_num==13) {misParam.set_password_usuario_secundario_param(_cadena);}
            	_cadena="";
            	
        	}
        }
        
   	
       
        String _ruta_archivos_planos="";
        String _ruta_xmls_sin_firma="";
        String _ruta_xmls_con_firma="";
        String _ruta_respuestas="";
        String _ruta_pdfs="";
        String _ruta_tickets="";
        String _ruta_respuestas_status="";
        String _ruta_certificados="";
        String _ruta_hash="";
        String _ruta_keystore="";
        String _ruta_cantidad_en_letras="";
        String _ruta_qrs="";
        String _ruta_417="";
//        String _usuario_secundario="";
//        String _password_usuario_secundario="";
        
        
        String _os=misParam.get_os_param();
        String _ruc_param=misParam.get_ruc_param();
        
  
        // armar la ruta para los _ruta_archivos_planos
        if (_os.equals("win")) {
        	_ruta_archivos_planos="."+_win+"data"+_win+_ruc_param+_win+"01_archivos_planos"+_win;
        }
        
        if (_os.equals("linux")) {
        	_ruta_archivos_planos="."+_lin+"data"+_lin+_ruc_param+_lin+"01_archivos_planos"+_lin;
        }
        misParametros.set_ruta_archivos_planos(_ruta_archivos_planos);
        
        // armar la ruta para los _ruta_xmls_sin_firma
        if (_os.equals("win")) {
        	_ruta_xmls_sin_firma="."+_win+"data"+_win+_ruc_param+_win+"02_xmls_sin_firma_Pruebas"+_win;
        }
     
        if (_os.equals("linux")) {
        	_ruta_xmls_sin_firma="."+_lin+"data"+_lin+_ruc_param+_lin+"02_xmls_sin_firma_Pruebas"+_lin;
        }
        misParametros.set_ruta_xml_sin_firma(_ruta_xmls_sin_firma);
		
        // armar la ruta para los _ruta_xmls_con_firma
        if (_os.equals("win")) {
        	_ruta_xmls_con_firma="."+_win+"data"+_win+_ruc_param+_win+"03_xmls_con_firma_Pruebas"+_win;
        }
        if (_os.equals("linux")) {
        	_ruta_xmls_con_firma="."+_lin+"data"+_lin+_ruc_param+_lin+"03_xmls_con_firma_Pruebas"+_lin;
        }
        misParametros.set_ruta_xml_con_firma(_ruta_xmls_con_firma);
		
        // armar la ruta para los _ruta_respuestas
        if (_os.equals("win")) {
        	_ruta_respuestas="."+_win+"data"+_win+_ruc_param+_win+"04_respuestas_Pruebas"+_win;
        }
        if (_os.equals("linux")) {
        	_ruta_respuestas="."+_lin+"data"+_lin+_ruc_param+_lin+"04_respuestas_Pruebas"+_lin;
        }
        misParametros.set_ruta_respuestas(_ruta_respuestas);

        // armar _ruta_pdfs
        if (_os.equals("win")) {
        	_ruta_pdfs="."+_win+"data"+_win+_ruc_param+_win+"05_pdfs_Pruebas"+_win;
        }
        if (_os.equals("linux")) {
        	_ruta_pdfs="."+_lin+"data"+_lin+_ruc_param+_lin+"05_pdfs_Pruebas"+_lin;
        }        
        misParametros.set_ruta_pdfs(_ruta_pdfs);
        
        // armar _ruta_tickets
        if (_os.equals("win")) {
        	_ruta_tickets="."+_win+"data"+_win+_ruc_param+_win+"06_tickets_Pruebas"+_win;
        }
        if (_os.equals("linux")) {
        	_ruta_tickets="."+_lin+"data"+_lin+_ruc_param+_lin+"06_tickets_Pruebas"+_lin;
        }        
        misParametros.set_ruta_tickets(_ruta_tickets);
        
        // armar _ruta_respuestas_status
        if (_os.equals("win")) {
        	_ruta_respuestas_status="."+_win+"data"+_win+_ruc_param+_win+"07_respuestas_status"+_win;
        }
        if (_os.equals("linux")) {
        	_ruta_respuestas_status="."+_lin+"data"+_lin+_ruc_param+_lin+"07_respuestas_status"+_lin;
        }        
        misParametros.set_ruta_respuestas_status(_ruta_respuestas_status);

        // armar _ruta_hash
        if (_os.equals("win")) {
        	_ruta_hash="."+_win+"data"+_win+_ruc_param+_win+"09_hash_Pruebas"+_win;
        }
        if (_os.equals("linux")) {
        	_ruta_hash="."+_lin+"data"+_lin+_ruc_param+_lin+"09_hash_Pruebas"+_lin;
        }        
        misParametros.set_ruta_hash(_ruta_hash);
        
        
        
        
        // armar __ruta_cantidad_en_letras
        if (_os.equals("win")) {
        	_ruta_cantidad_en_letras="."+_win+"data"+_win+_ruc_param+_win+"15_cantidad_en_letras"+_win;
        }
        if (_os.equals("linux")) {
        	_ruta_cantidad_en_letras="."+_lin+"data"+_lin+_ruc_param+_lin+"15_cantidad_en_letras"+_lin;
        }        
        misParametros.set_ruta_cantidad_en_letras(_ruta_cantidad_en_letras);
        
        
        // armar _RUTA_QR
        if (_os.equals("win")) {
        	_ruta_qrs="."+_win+"data"+_win+_ruc_param+_win+"20_qr"+_win;
        }
        if (_os.equals("linux")) {
        	_ruta_qrs="."+_lin+"data"+_lin+_ruc_param+_lin+"20_qr"+_lin;
        }        
        misParametros.set_ruta_qr(_ruta_qrs);


        

        // armar _RUTA_417
        if (_os.equals("win")) {
        	_ruta_417="."+_win+"data"+_win+_ruc_param+_win+"21_417"+_win;
        }
        if (_os.equals("linux")) {
        	_ruta_417="."+_lin+"data"+_lin+_ruc_param+_lin+"21_417"+_lin;
        }        
        misParametros.set_ruta_417(_ruta_417);

        
     
 
        // armar _ruta_certificados
        if (_os.equals("win")) {
        	_ruta_certificados="."+_win+"data"+_win+_ruc_param+_win+"certificados"+_win;
        }
        if (_os.equals("linux")) {
        	_ruta_certificados="."+_lin+"data"+_lin+_ruc_param+_lin+"certificados"+_lin;
        }            
        misParametros.set_ruta_certificados(_ruta_certificados);
		
        // armar _ruta_keystore  completa
        if (_os.equals("win")) {
        	_ruta_keystore=_ruta_certificados+_win+misParam.get_keystore_param();
        }
        if (_os.equals("linux")) {
        	_ruta_keystore=_ruta_certificados+_lin+misParam.get_keystore_param();
        }            
        misParametros.set_keystore(_ruta_keystore);

		misParametros.set_ruc(_ruc_param);
		misParametros.set_razon_social(misParam.get_razon_social_param());
		misParametros.set_codigo_postal(misParam.get_codigo_postal_param());
		misParametros.set_direccion(misParam.get_direccion_param());
		misParametros.set_ciudad(misParam.get_ciudad_param());
		misParametros.set_pais(misParam.get_pais_param());
		misParametros.set_password_keystore(misParam.get_password_keystore_param());
		misParametros.set_password_certificado(misParam.get_password_certificado_param());
		misParametros.set_alias_certificado(misParam.get_alias_param());
		
		misParametros.set_usuario_secundario(misParam.get_usuario_secundario_param());
		misParametros.set_password_usuario_secundario(misParam.get_password_usuario_secundario_param());

		misParametros.set_file_name(args[0]);
  	    
		$FILE_NAME=args[0];
		$FIRMA_CONECTA=args[1];
		
		$PATH_BASE=misParametros.get_ruta_base();
		$PATH_ARCHIVOS_PLANOS=misParametros.get_ruta_archivos_planos();
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
		
		
		
		
		
		// $FILE_NAME = "20366410610-07-BB14-00000011";
		$FILE_NAME=args[0];
		//$FILE_NAME = "20366410610-07-BB11-00000005";
		
		
		
		InputStream is_det = new FileInputStream($PATH_ARCHIVOS_PLANOS+$FILE_NAME+".det");
		DataSource ds_det = new ByteArrayDataSource(is_det,"application/octet-stream");
		DataHandler dhandler_det = new DataHandler(ds_det);
		
		InputStream is_cab = new FileInputStream($PATH_ARCHIVOS_PLANOS+$FILE_NAME+".not");
		DataSource ds_cab = new ByteArrayDataSource(is_cab,"application/octet-stream");
		DataHandler dhandler_cab = new DataHandler(ds_cab);
		
		InputStream is_aca = new FileInputStream($PATH_ARCHIVOS_PLANOS+$FILE_NAME+".aca");
		DataSource ds_aca = new ByteArrayDataSource(is_aca,"application/octet-stream");
		DataHandler dhandler_aca = new DataHandler(ds_aca);
		
		InputStream is_ley = new FileInputStream($PATH_ARCHIVOS_PLANOS+$FILE_NAME+".ley");
		DataSource ds_ley = new ByteArrayDataSource(is_ley,"application/octet-stream");
		DataHandler dhandler_ley = new DataHandler(ds_ley);
	
		
		InputStream is_rel = new FileInputStream($PATH_ARCHIVOS_PLANOS+$FILE_NAME+".rel");
		DataSource ds_rel = new ByteArrayDataSource(is_rel,"application/octet-stream");
		DataHandler dhandler_rel = new DataHandler(ds_rel);
		
		
		
		crearXML_nc.c_XML(dhandler_det, dhandler_cab, dhandler_aca, dhandler_ley, dhandler_rel, $FILE_NAME, misParametros, $FIRMA_CONECTA);
		// crearXML_nc.c_XML(dhandler_det, dhandler_cab, $FILE_NAME, misParametros);
	}
	
	
	public static void readParam(String _file_parametros) throws Exception {
		
		
		System.out.println("Cabecera");
		
		InputStream is_param = new FileInputStream(_file_parametros);
		DataSource ds_param = new ByteArrayDataSource(is_param,"application/octet-stream");
		DataHandler dhandler_param = new DataHandler(ds_param);
		
		Object content = dhandler_param.getContent();
		
		BufferedReader br = null;
		
		try {

			String sCurrentLine;
			br = new BufferedReader(new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {
				myParam[0]=sCurrentLine;
				
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
