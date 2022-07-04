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
		String input = "";
		while(true) {
			cli.getMenuPrincipal();
			input = scan.next();
			while(!cli.esInputValido(input)) {
				System.out.println("opcion invalida, ingrese nuevamente");
				input = scan.next();
			}
			int opcion = Integer.parseInt(input);
			cli.limpiarConsola();
			switch(opcion) {
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
				   String inputBuscarProductoPor = scan.next();
				   while(!cli.esInputValidoBuscarProducto(inputBuscarProductoPor)) {
						System.out.println("opcion invalida, ingrese nuevamente");
						inputBuscarProductoPor = scan.next();
				   }
				   datos.add(inputBuscarProductoPor);
				   System.out.println("Ingrese el dato a buscar");
				   datos.add(scan.next());
				   break;

			   case 5:
				   datos.add(String.valueOf(input));
				   System.out.println("opcion 5");
				   break;
				   
			   case 6:
				   scan.close();
				   cli.finalizar();
				   break;
				   
			   default : 
			     System.out.println("algo salio mal que llego al default");
			    
			}
			
			System.out.println("Comprobando datos...");
			for(String s : datos) {
				System.out.println(s);
			}
			if(cli.comprobaryEjecutar(datos)) {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("algo salio mal al ralentizar el servicio");
				}
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("algo salio mal al ralentizar el servicio");
				}
				cli.limpiarConsola();
			}
			input = "";
			datos.clear();
		}
		
		
		
    }
}