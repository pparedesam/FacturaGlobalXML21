package wsHomologador;

public class resumen {
	
	public String _ruc;  			// 1 campo 1 numero de ruc emimos
	public String _nombre_emp;  	// 2 razon social
	public String _fecha_emision; 	// 3 fecha de emision del docto
	public String _tipo_doc;	  	// 4 tipo de documento
	public String _serie; 			// 5 serie de documento
	public String _numero_inicio;   // 6 numero inicio
	public String _numero_final;	// 7 numero final
	public double _ventas_gravadas; // 8 ventas gravadas
	public double _ventas_exoneradas;// 9 ventas exonradas
	public double _ventas_inafectas; // 10 ventas inafectas
	public double _ventas_gratuitas; // 10 ventas inafectas
	public double _sumatoria_otros_cargos; // 11 sumatoria otrs cargos
	public double _sumatoria_igv; 	 // 11 sumatoria_igv
	public double _sumatoria_isc; 	 // 12 sumaoria_isc
	public double _importe_venta; 	 // 14 importe venta
	public String _ruc_receptor;
	public String _tipo_ident;
	public String _status;
	public double _total;
	public String _moneda;
	public String _serie_rel;
	public String _folio_rel;
	public String _tipo_grabamen;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public double get_ventas_gratuitas() {
		return _ventas_gratuitas;
	}
	public void set_ventas_gratuitas(double _ventas_gratuitas) {
		this._ventas_gratuitas = _ventas_gratuitas;
	}
	public String get_tipo_grabamen() {
		return _tipo_grabamen;
	}
	public void set_tipo_grabamen(String _tipo_grabamen) {
		this._tipo_grabamen = _tipo_grabamen;
	}
	public String get_serie_rel() {
		return _serie_rel;
	}
	public void set_serie_rel(String _serie_rel) {
		this._serie_rel = _serie_rel;
	}
	public String get_folio_rel() {
		return _folio_rel;
	}
	public void set_folio_rel(String _folio_rel) {
		this._folio_rel = _folio_rel;
	}
	public String get_moneda() {
		return _moneda;
	}
	public void set_moneda(String _moneda) {
		this._moneda = _moneda;
	}
	public double get_total() {
		return _total;
	}
	public void set_total(double _total) {
		this._total = _total;
	}
	public String get_status() {
		return _status;
	}
	public void set_status(String _status) {
		this._status = _status;
	}
	public String get_ruc_receptor() {
		return _ruc_receptor;
	}
	public void set_ruc_receptor(String _ruc_receptor) {
		this._ruc_receptor = _ruc_receptor;
	}
	public String get_tipo_ident() {
		return _tipo_ident;
	}
	public void set_tipo_ident(String _tipo_ident) {
		this._tipo_ident = _tipo_ident;
	}
	public double get_sumatoria_otros_cargos() {
		return _sumatoria_otros_cargos;
	}
	public void set_sumatoria_otros_cargos(double _sumatoria_otros_cargos) {
		this._sumatoria_otros_cargos = _sumatoria_otros_cargos;
	}
	public String get_ruc() {
		return _ruc;
	}
	public void set_ruc(String _ruc) {
		this._ruc = _ruc;
	}
	public String get_nombre_emp() {
		return _nombre_emp;
	}
	public void set_nombre_emp(String _nombre_emp) {
		this._nombre_emp = _nombre_emp;
	}
	public String get_fecha_emision() {
		return _fecha_emision;
	}
	public void set_fecha_emision(String _fecha_emision) {
		this._fecha_emision = _fecha_emision;
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
	public String get_numero_inicio() {
		return _numero_inicio;
	}
	public void set_numero_inicio(String _numero_inicio) {
		this._numero_inicio = _numero_inicio;
	}
	public String get_numero_final() {
		return _numero_final;
	}
	public void set_numero_final(String _numero_final) {
		this._numero_final = _numero_final;
	}
	public double get_ventas_gravadas() {
		return _ventas_gravadas;
	}
	public void set_ventas_gravadas(double _ventas_gravadas) {
		this._ventas_gravadas = _ventas_gravadas;
	}
	public double get_ventas_exoneradas() {
		return _ventas_exoneradas;
	}
	public void set_ventas_exoneradas(double _ventas_exoneradas) {
		this._ventas_exoneradas = _ventas_exoneradas;
	}
	public double get_ventas_inafectas() {
		return _ventas_inafectas;
	}
	public void set_ventas_inafectas(double _ventas_inafectas) {
		this._ventas_inafectas = _ventas_inafectas;
	}
	public double get_sumatoria_igv() {
		return _sumatoria_igv;
	}
	public void set_sumatoria_igv(double _sumatoria_igv) {
		this._sumatoria_igv = _sumatoria_igv;
	}
	public double get_sumatoria_isc() {
		return _sumatoria_isc;
	}
	public void set_sumatoria_isc(double _sumatoria_isc) {
		this._sumatoria_isc = _sumatoria_isc;
	}
	public double get_importe_venta() {
		return _importe_venta;
	}
	public void set_importe_venta(double _importe_venta) {
		this._importe_venta = _importe_venta;
	}
	
}
