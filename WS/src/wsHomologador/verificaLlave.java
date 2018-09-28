package wsHomologador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.*;
import javax.xml.crypto.dsig.keyinfo.*;
import javax.xml.crypto.dsig.spec.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;



public class verificaLlave {

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


	public static String  revisar(String _fecha, String _RUC) {

		System.out.println(_fecha);
		System.out.println(_RUC);
		

		String _Dia = "";
		String _Mes = "";
		String _Ano = "";
		_Dia = _fecha.substring(8, 10);  //2016.09.17  2016-11-30
		_Mes = _fecha.substring(5, 7);  //2016.09.17  0123456789
		_Ano = _fecha.substring(0, 4);             // 1234567890

		String _cadena_mes=_RUC+_Ano+_Mes;
		String _resultado="";
		String _car="";

		String _cadena_encriptada="";

		int _tam=17;
		int _bandera=0;
		int _total_ascii=0;
		

		for (int _x = 0; _x < _tam; _x++) 
		{


			_car=_cadena_mes.substring(_x,_x+1);

			char character = _car.charAt(0);    
			int ascii = (int) character;

			int _corre = 0;

			if (_bandera==0)  {
				_corre=38;

			}

			if (_bandera==1)  {
				_corre=27;

			}

			if (_bandera==2)  {
				_corre=18;

			}

			if (_bandera==3)  {
				_corre=-4;

			}

			if (_bandera==4)  {

				_bandera=0;
			}

			_bandera++;

			int _ascii_corre=ascii+_corre;
			_total_ascii=_total_ascii+ascii;
			
			char char_nuevo = (char) _ascii_corre;


			if (char_nuevo==',') {
				char_nuevo='+';

			}

			if (char_nuevo=='.') {
				char_nuevo='*';

			}

			_cadena_encriptada=_cadena_encriptada+char_nuevo;


		}

	//	System.out.println("Cadena Encriptada 1:"+_cadena_encriptada);


		_cadena_mes=_cadena_encriptada;

		_cadena_encriptada="";

		int promedio = (int) _total_ascii/17;
		
		for (int _x = 0; _x < _tam; _x++) 
		{


			_car=_cadena_mes.substring(_x,_x+1);

			char character = _car.charAt(0);    
			int ascii = (int) character;

			
			
			double _div=(_x/22)+promedio-(_x*2);
			int entero = (int)_div;
			
			
			int _ascii_corre=ascii+entero-45;
			char char_nuevo = (char) _ascii_corre;

		

			_cadena_encriptada=_cadena_encriptada+char_nuevo;


		
		}

		//System.out.println("Cadena Encriptada 2:"+_cadena_encriptada);

		return _cadena_encriptada;
		

	}

}