package main;

import persistencia.transacciones.ProductoSQL;


public class Main 
{

	public static void main(String[] args) 
	{
		//CLI cli = new CLI();
		System.out.println(ProductoSQL.searchProducts("ID", 1));
		System.out.println(ProductoSQL.searchProducts("nombre", "gaseosa"));
		System.out.println(ProductoSQL.searchProducts("categoria", "alimento"));
		System.out.println(ProductoSQL.searchProducts("precio", "20.0"));
	}
}
