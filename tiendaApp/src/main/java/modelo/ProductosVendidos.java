package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="productosVendidos")
public class ProductosVendidos {
	
	@Id
	@Column
	private int IDVenta;
	
	@Column
	private int codProducto;
	
	@Column
	private int cantidad;

	/////////////////////////////////////////////
	
	public int getIDVenta() {
		return IDVenta;
	}
	

	public void setIDVenta(int iDVenta) {
		IDVenta = iDVenta;
	}

	public int getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
