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
			System.out.println("----------------------------------------------------------------");
			cli.getMenuPrincipal();
			input = scan.next();
			System.out.println(input);
			while(!cli.esInputValido(input)) {
				System.out.println("opcion invalida, ingrese nuevamente [1 - 6]");
				input = scan.next();
				System.out.println(input);
			}
			cli.limpiarConsola();
			
			switch(Integer.parseInt(input)) {
			   case 1 :
				   datos.add(String.valueOf(1));
				   for(String s: cli.getDatosProducto()) {
					   System.out.println(s);
					   datos.add(scan.next());
				   }
				   break;   
				   
			   case 2 :
				   datos.add(String.valueOf(2));
				   for(String s : cli.getDatosVendedor()) {
					   System.out.println(s);
					   datos.add(scan.next());
				   }
				   break;
			   
			   case 3:
				   datos.add(String.valueOf(3));
				   for(String s : cli.getDatosVenta()) {
					   System.out.println(s);
					   datos.add(scan.next());
				   }
				   break;
				   
			   case 4 :
				   datos.add(String.valueOf(4));
				   cli.getMenuBuscarProducto();
				   String inputBuscarProductoPor = scan.next();
				   while(!cli.esInputValidoBuscarProducto(inputBuscarProductoPor)) {
						System.out.println("(4) opcion invalida, ingrese nuevamente");
						inputBuscarProductoPor = scan.next();
				   }
				   datos.add(inputBuscarProductoPor);
				   System.out.println("Ingrese el dato a buscar");
				   datos.add(scan.next());
				   break;

			   case 5:
				   datos.add(String.valueOf(5));
				   System.out.println("Ingrese codigo del vendedor");
				   String inputCodVendedor = scan.next();
				   while(!cli.validarInteger(inputCodVendedor)) {
						System.out.println("(5) dato invalido, ingrese nuevamente");
						inputCodVendedor = scan.next();
				   }
				   datos.add(inputCodVendedor);
				   break;
				   
			   case 6:
				   scan.close();
				   cli.finalizar();
				   break;
				   
			   default : 
			     System.out.println("algo salio mal que llego al default");
			    
			}
			
			if(cli.comprobaryEjecutar(datos)) {
				datos.clear();
				input = "";
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("algo salio mal al ralentizar el servicio");
				}
			} else {

				input = "";
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("algo salio mal al ralentizar el servicio");
				}
				cli.limpiarConsola();
			}
			
		}
		
    }
}