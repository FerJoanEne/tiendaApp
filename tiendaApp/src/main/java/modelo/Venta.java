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
	private int IDventa;
	
	@Column
	private int codVendedor;

	@Column
	private String nombreProducto;
	
	@Column
	private double precioProducto;
	
	public Venta() {
		
	}
	
	public Venta(int idVenta, int codVendedor, String nombreProducto, double precioProducto) {
		this.IDventa = idVenta;
		this.codVendedor = codVendedor;
		this.nombreProducto = nombreProducto;
		this.precioProducto = precioProducto;
	}

	///////////////////////////////////////////////////////////////////

	public int getIDventa() {
		return IDventa;
	}

	public void setIDventa(int iDventa) {
		IDventa = iDventa;
	}

	public int getCodVendedor() {
		return codVendedor;
	}

	public void setCodVendedor(int codVendedor) {
		this.codVendedor = codVendedor;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}
	
	
	
}
