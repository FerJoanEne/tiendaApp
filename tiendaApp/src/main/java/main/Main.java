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
		int input = 0;
		while(input!=6) {
			cli.getMenuPrincipal();
			input = scan.nextInt();
			while(!cli.esInputValido(input)) {
				System.out.println("Por favor ingrese una opcion valida");
				input = scan.nextInt();
			}
			
			cli.limpiarConsola();
			switch(input) {
			   case 1 :
				   for(String s: cli.getDatosProducto()) {
					   System.out.println(s);
					   datos.add(scan.next());
				   }
				   break;   
				   
			   case 2 :
				   for(String s : cli.getDatosVendedor()) {
					   System.out.println(s);
					   datos.add(scan.next());
				   }
				   break;
			   
			   case 3:
				   for(String s : cli.getDatosVenta()) {
					   System.out.println(s);
					   datos.add(scan.next());
				   }
				   break;
				   
			   case 4 :
				   cli.getMenuBuscarProducto();
				   datos.add(scan.next());
				   System.out.println("Ingrese el dato a buscar");
				   datos.add(scan.next());
				   break;

			   case 5:
				   System.out.println("opcion 5");
				   break;
				   
			   case 6:
				   cli.finalizar();
				   break;
				   
			   default : 
			     System.out.println("algo salio mal que llego al default");
			    
			}
			
			for(String s: datos) {
				System.out.println(s);
			}
			
		}
		
		scan.close();
		
    }
}