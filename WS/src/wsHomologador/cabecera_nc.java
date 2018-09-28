package wsHomologador;

public class cabecera_nc {
	
	public String _fecha;         // 1 Fecha de emisión
	public String _tipo_nota_cre; // 2 Tipo de Nota de Credito
	public String _motivo;        // 3 Descripcion del Motivo
	public String _tipo_op;       // 4 Tipo de operación
	public String _num_doc_afec;  // 5 Numero de Documento Afectado
	public String _ident;		  // 6 Tipo de documento de identidad del adquirente o usuario
	public String _num_ident ; 	  // 7 Numero de documento de identidad del adquirente o usuario
	public String _nombre;		  // 8 Apellidos y nombres, denominación o razón social del adquirente o usuario 
	public String _moneda;		  // 9 Tipo de moneda en la cual se emite la factura electrónica
	public double  _sum_cargos;	  // 10 Sumatoria otros Cargos
	public double  _tot_vta_gra;   // 11 Total valor de venta - Operaciones gravadas
	public double  _tot_vta_in;	   // 12 Total valor de venta - Operaciones inafectas
	public double  _tot_vta_exo;   // 13 Total valor de venta - Operaciones exoneradas
	public double  _sum_igv;	   // 14 Sumatoria IGV
	public double  _sum_isc;	   // 15 Sumatoria ISC
	public double  _sum_otros;	   // 16 Sumatoria otros tributos
	public double  _importe_tot;   // 17 Importe total de la venta, cesión en uso o del servicio prestado
	public String get_fecha() {
		return _fecha;
	}
	public void set_fecha(String _fecha) {
		this._fecha = _fecha;
	}
	public String get_tipo_nota_cre() {
		return _tipo_nota_cre;
	}
	public void set_tipo_nota_cre(String _tipo_nota_cre) {
		this._tipo_nota_cre = _tipo_nota_cre;
	}
	public String get_motivo() {
		return _motivo;
	}
	public void set_motivo(String _motivo) {
		this._motivo = _motivo;
	}
	public String get_tipo_op() {
		return _tipo_op;
	}
	public void set_tipo_op(String _tipo_op) {
		this._tipo_op = _tipo_op;
	}
	public String get_num_doc_afec() {
		return _num_doc_afec;
	}
	public void set_num_doc_afec(String _num_doc_afec) {
		this._num_doc_afec = _num_doc_afec;
	}
	public String get_ident() {
		return _ident;
	}
	public void set_ident(String _ident) {
		this._ident = _ident;
	}
	public String get_num_ident() {
		return _num_ident;
	}
	public void set_num_ident(String _num_ident) {
		this._num_ident = _num_ident;
	}
	public String get_nombre() {
		return _nombre;
	}
	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}
	public String get_moneda() {
		return _moneda;
	}
	public void set_moneda(String _moneda) {
		this._moneda = _moneda;
	}
	public double get_sum_cargos() {
		return _sum_cargos;
	}
	public void set_sum_cargos(double _sum_cargos) {
		this._sum_cargos = _sum_cargos;
	}
	public double get_tot_vta_gra() {
		return _tot_vta_gra;
	}
	public void set_tot_vta_gra(double _tot_vta_gra) {
		this._tot_vta_gra = _tot_vta_gra;
	}
	public double get_tot_vta_in() {
		return _tot_vta_in;
	}
	public void set_tot_vta_in(double _tot_vta_in) {
		this._tot_vta_in = _tot_vta_in;
	}
	public double get_tot_vta_exo() {
		return _tot_vta_exo;
	}
	public void set_tot_vta_exo(double _tot_vta_exo) {
		this._tot_vta_exo = _tot_vta_exo;
	}
	public double get_sum_igv() {
		return _sum_igv;
	}
	public void set_sum_igv(double _sum_igv) {
		this._sum_igv = _sum_igv;
	}
	public double get_sum_isc() {
		return _sum_isc;
	}
	public void set_sum_isc(double _sum_isc) {
		this._sum_isc = _sum_isc;
	}
	public double get_sum_otros() {
		return _sum_otros;
	}
	public void set_sum_otros(double _sum_otros) {
		this._sum_otros = _sum_otros;
	}
	public double get_importe_tot() {
		return _importe_tot;
	}
	public void set_importe_tot(double _importe_tot) {
		this._importe_tot = _importe_tot;
	}
	
	
	
	
}