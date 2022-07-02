package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
				System.out.println("opcion invalida, ingrese nuevamente");
				
			}
			
			cli.limpiarConsola();
			switch(input) {
			   case 1 :
				   datos.add(String.valueOf(input));
				   for(String s: cli.getDatosProducto()) {
					   System.out.println(s);
					   datos.add(scan.next());
				   }
				   break;   
				   
			   case 2 :
				   datos.add(String.valueOf(input));
				   for(String s : cli.getDatosVendedor()) {
					   System.out.println(s);
					   datos.add(scan.next());
				   }
				   break;
			   
			   case 3:
				   datos.add(String.valueOf(input));
				   for(String s : cli.getDatosVenta()) {
					   System.out.println(s);
					   datos.add(scan.next());
				   }
				   break;
				   
			   case 4 :
				   datos.add(String.valueOf(input));
				   cli.getMenuBuscarProducto();
				   datos.add(scan.next());
				   System.out.println("Ingrese el dato a buscar");
				   datos.add(scan.next());
				   break;

			   case 5:
				   datos.add(String.valueOf(input));
				   System.out.println("opcion 5");
				   break;
				   
			   case 6:
				   cli.finalizar();
				   break;
				   
			   default : 
			     System.out.println("algo salio mal que llego al default");
			    
			}
			
			if(!cli.comprobarDatos(datos)) {
				try {
					System.out.println("Datos erroneos, volviendo al menu principal...");
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				cli.ejecutar(datos);			
			}
			System.out.println("volviendo al menu principal...");
		}
		
		scan.close();
		
    }
}