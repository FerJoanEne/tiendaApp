package main;

import modelo.Vendedor;
import persistencia.transacciones.ProductoSQL;
import persistencia.transacciones.VendedorSQL;


public class Main 
{

	public static void main(String[] args) 
	{
		//CLI cli = new CLI();
		/*System.out.println(ProductoSQL.searchProducts("ID", 1));
		System.out.println(ProductoSQL.searchProducts("nombre", "gaseosa"));
		System.out.println(ProductoSQL.searchProducts("categoria", "alimento"));
		System.out.println(ProductoSQL.searchProducts("precio", "20.0"));
		*/
//		Vendedor v = new Vendedor();
//		v.setNombre("Carlos Bianchi");
//		v.setSueldo(10000.00);
//		System.out.println(VendedorSQL.insert(v));
		Vendedor v2 = new Vendedor();
		v2.setCodigo(3);
		v2.setNombre("Carlos Bianchi");
		v2.setSueldo(10000.00);
		System.out.println(VendedorSQL.remove(v2));
	}
}
