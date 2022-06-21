package main;

import modelo.Producto;
import persistencia.transacciones.ProductoSQL;
import presentacion.*;

public class Main 
{

	public static void main(String[] args) 
	{
		//CLI cli = new CLI();
		System.out.println(ProductoSQL.searchProducts("nombre", "gaseosa"));
	}
}
