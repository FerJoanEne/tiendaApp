package main;

import java.util.ArrayList;
import java.util.List;

import modelo.Vendedor;
import persistencia.transacciones.ProductoSQL;
import persistencia.transacciones.VendedorSQL;


public class Main 
{

	public static void main(String[] args) 
	{
		//CLI cli = new CLI();
		/*
		Vendedor v = new Vendedor();
		v.setCodigo(5);
		System.out.println(VendedorSQL.remove(v));*/
		
		System.out.print("Everything on the console will cleared");
        System.out.print("\033[H\033[2J");
        System.out.flush();
	}
}
