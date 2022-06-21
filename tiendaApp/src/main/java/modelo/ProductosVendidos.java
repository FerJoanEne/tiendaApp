package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="productosVendidos")
public class ProductosVendidos {
	
	
	@Column
	private Long IDVenta;
	
	@Column
	private Long codProducto;
	
	@Column
	private int cantidad;

	public Long getIDVenta() {
		return IDVenta;
	}
	
	/////////////////////////////////////////////

	public void setIDVenta(Long iDVenta) {
		IDVenta = iDVenta;
	}

	public Long getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(Long codProducto) {
		this.codProducto = codProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
