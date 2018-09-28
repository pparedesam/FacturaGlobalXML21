package wsHomologador;

public class cabecera {
	public String _tipo_op;      // 1 Tipo de operación
	public String _fecha;        // 2 Fecha de emisión
	public String _dom_fiscal; 	 // 3 Código del domicilio fiscal o de local anexo del emisor
	public String _ident;		 // 4 Tipo de documento de identidad del adquirente o usuario
	public String _num_ident ; 	 // 5 Número de documento de identidad del adquirente o usuario
	public String _nombre;		 // 6 Apellidos y nombres, denominación o razón social del adquirente o usuario 
	public String _moneda;		 // 7 Tipo de moneda en la cual se emite la factura electrónica
	public double _desc_glo;	 // 8 Descuentos Globales
	public double  _sum_cargos;	 // 9 Sumatoria otros Cargos
	public double  _tot_desc;	 // 10 Total descuentos
	public double  _tot_vta_gra;   // 11 Total valor de venta - Operaciones gravadas
	public double  _tot_vta_in;	   // 12 Total valor de venta - Operaciones inafectas
	public double  _tot_vta_exo;   // 13 Total valor de venta - Operaciones exoneradas
	public double  _sum_igv;	   // 14 Sumatoria IGV
	public double  _sum_isc;	   // 15 Sumatoria ISC
	public double  _sum_otros;	   // 16 Sumatoria otros tributos
	public double  _importe_tot;   // 17 Importe total de la venta, cesión en uso o del servicio prestado
	public double  _desc_detalle;
	public String _tipo_comprobante;
	public String _guia;
	public String _orden_de_compra;
	public String _email;
	
	
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String get_orden_de_compra() {
		return _orden_de_compra;
	}
	public void set_orden_de_compra(String _orden_de_compra) {
		this._orden_de_compra = _orden_de_compra;
	}
	public String get_email() {
		return _email;
	}
	public void set_email(String _email) {
		this._email = _email;
	}
	public String get_guia() {
		return _guia;
	}
	public void set_guia(String _guia) {
		this._guia = _guia;
	}
	public String get_tipo_comprobante() {
		return _tipo_comprobante;
	}
	public void set_tipo_comprobante(String _tipo_comprobante) {
		this._tipo_comprobante = _tipo_comprobante;
	}
	public double get_desc_detalle() {
		return _desc_detalle;
	}
	public void set_desc_detalle(double _desc_detalle) {
		this._desc_detalle = _desc_detalle;
	}
	public String get_tipo_op() {
		return _tipo_op;
	}
	public void set_tipo_op(String _tipo_op) {
		this._tipo_op = _tipo_op;
	}
	public String get_fecha() {
		return _fecha;
	}
	public void set_fecha(String _fecha) {
		this._fecha = _fecha;
	}
	public String get_dom_fiscal() {
		return _dom_fiscal;
	}
	public void set_dom_fiscal(String _dom_fiscal) {
		this._dom_fiscal = _dom_fiscal;
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
	public double get_desc_glo() {
		return _desc_glo;
	}
	public void set_desc_glo(double _desc_glo) {
		this._desc_glo = _desc_glo;
	}
	public double get_sum_cargos() {
		return _sum_cargos;
	}
	public void set_sum_cargos(double _sum_cargos) {
		this._sum_cargos = _sum_cargos;
	}
	public double get_tot_desc() {
		return _tot_desc;
	}
	public void set_tot_desc(double _tot_desc) {
		this._tot_desc = _tot_desc;
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