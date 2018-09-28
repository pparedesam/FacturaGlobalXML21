package wsHomologador;

public class CodigosErrores {
	
	
	public static String[] errores = new String[9000];
	
	
	public static  String main (int _error)  {
		llena_table();
		String _cadena="";
		_cadena=errores[_error];
		
		
		
		return _cadena;
		
	}


	private static void llena_table() {
		errores[306]="No se puede leer (parsear) el archivo XML";
		errores[2335]="El documento electrónico ingresado ha sido alterado ";
		errores[2074]="UBLVersionID - La versión del UBL no es correcta"; 
		
	}

	
	
}
