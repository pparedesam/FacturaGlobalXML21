package wsHomologador;

public class documentos_relacionados21 {
	
	
public String _indDocRelacionado;   // Indicador de documento relacionado (1: Gu�a, 2: Anticipo, 3: Orden de compra, 98: Documentos afectados (m�ltiples) por una Nota de Cr�dito / D�bido,  99: Otros)
public String _numIdeAnticipo;       // 	 N�mero identificador del anticipo (solo para el Caso: 2 Anticipo). PREDETERMINADO "-"
public String _tipDocRelacionado;   //  Tipo de documento relacionado  Si es documento relacionado es: Gu�a / Documento Afectado: Cat�logo N� 1/
public String _numDocRelacionado;   // N�mero de documento relacionado aqui va el valor que varia o el dato que queremos mostrar en el xml
public String _tipDocEmisor;         //  Tipo de documento del emisor del documento relacionado  ( 1 6 )
public String _numDocEmisor;		// N�mero de documento del emisor del documento relacionado
public String _mtoDocRelacionado;    // Monto
	 		
	 		
			public String get_indDocRelacionado() {
				return _indDocRelacionado;
			}
			public void set_indDocRelacionado(String _indDocRelacionado) {
				this._indDocRelacionado = _indDocRelacionado;
			}
			public String get_numIdeAnticipo() {
				return _numIdeAnticipo;
			}
			public void set_numIdeAnticipo(String _numIdeAnticipo) {
				this._numIdeAnticipo = _numIdeAnticipo;
			}
			public String get_tipDocRelacionado() {
				return _tipDocRelacionado;
			}
			public void set_tipDocRelacionado(String _tipDocRelacionado) {
				this._tipDocRelacionado = _tipDocRelacionado;
			}
			public String get_numDocRelacionado() {
				return _numDocRelacionado;
			}
			public void set_numDocRelacionado(String _numDocRelacionado) {
				this._numDocRelacionado = _numDocRelacionado;
			}
			public String get_tipDocEmisor() {
				return _tipDocEmisor;
			}
			public void set_tipDocEmisor(String _tipDocEmisor) {
				this._tipDocEmisor = _tipDocEmisor;
			}
			public String get_numDocEmisor() {
				return _numDocEmisor;
			}
			public void set_numDocEmisor(String _numDocEmisor) {
				this._numDocEmisor = _numDocEmisor;
			}
			public String get_mtoDocRelacionado() {
				return _mtoDocRelacionado;
			}
			public void set_mtoDocRelacionado(String _mtoDocRelacionado) {
				this._mtoDocRelacionado = _mtoDocRelacionado;
			}

	 		
	 		
	 		
	 		
	
}


