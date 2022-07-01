package main;

import java.util.ArrayList;
import java.util.Scanner;

import presentacion.MenuPrincipalCLI;

public class Main 
{

	public static void main(String[] args) {
		MenuPrincipalCLI cli = new MenuPrincipalCLI();
		ArrayList<String> datos = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		while(true) {
			cli.limpiarConsola();
			cli.getMenuPrincipal();
			int input = scan.nextInt();
			while(!cli.esInputValido(input)) {
				System.out.println("Por favor ingrese una opcion valida");
				input = scan.nextInt();
			}
			switch(input) {
			   case 1 :
				   cli.getDatosProducto();
				   break;   
				   
			   case 2 :
				   cli.getDatosVendedor();
				   break;
			   
			   case 3:
				   cli.getDatosVenta();
				   break;
				   
			   case 4 :
				   cli.getMenuBuscarProducto();
				   break;

			   case 5:
				   cli.calcularComision(datos);
				   break;
				   
			   case 6:
				   cli.finalizar();
				   break;
				   
			   default : 
			     System.out.println("algo salio mal que llego al default");
			}
		}
		
    }
}