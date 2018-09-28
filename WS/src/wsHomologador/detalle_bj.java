package wsHomologador;

public class detalle_bj {
	
	public String _fecha;        // 1 Fecha de generación del documento dado de baja
	public String _fecha_hoy;    // 2 Fecha de generación de la comunicación
	public String _tipo_doc; 	 // 3 3 Tipo de documento que se da de baja
	public String _serie;		 // 4 Número de documento de baja
	public String _motivo ; 	 // 5 Descripción de motivo de baja
	
	
	public String get_fecha() {
		return _fecha;
	}
	public void set_fecha(String _fecha) {
		this._fecha = _fecha;
	}
	public String get_fecha_hoy() {
		return _fecha_hoy;
	}
	public void set_fecha_hoy(String _fecha_hoy) {
		this._fecha_hoy = _fecha_hoy;
	}
	public String get_tipo_doc() {
		return _tipo_doc;
	}
	public void set_tipo_doc(String _tipo_doc) {
		this._tipo_doc = _tipo_doc;
	}
	public String get_serie() {
		return _serie;
	}
	public void set_serie(String _serie) {
		this._serie = _serie;
	}
	public String get_motivo() {
		return _motivo;
	}
	public void set_motivo(String _motivo) {
		this._motivo = _motivo;
	}
	
	
	
	
	
	
	

}