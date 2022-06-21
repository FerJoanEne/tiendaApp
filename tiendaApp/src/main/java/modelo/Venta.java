package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="venta")
public class Venta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long IDventa;
	
	@Column
	private Long codVendedor;
	
	/////////////////////////////////////////////////

	public Long getIDventa() {
		return IDventa;
	}

	public void setIDventa(Long iDventa) {
		IDventa = iDventa;
	}

	public Long getCodVendedor() {
		return codVendedor;
	}

	public void setCodVendedor(Long codVendedor) {
		this.codVendedor = codVendedor;
	}
	
}
