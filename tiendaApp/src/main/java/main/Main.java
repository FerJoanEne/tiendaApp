package main;

import java.util.ArrayList;

import presentacion.MenuPrincipalCLI;

public class Main 
{

	public static void main(String[] args) {
		MenuPrincipalCLI cli = new MenuPrincipalCLI();
		cli.getMenuPrincipal();
		ArrayList<String> datos = new ArrayList<String>();
		String input = cli.obtenerInput();
		while(!cli.esInputValido(input)) {
			cli.limpiarConsola();
			cli.getMenuPrincipal();
			input = cli.obtenerInput();
			switch(Integer.parseInt(datos.remove(0))) {
			   case 1 :
				   cli.registrarProducto(datos);
				   break;   
				   
			   case 2 :
				   cli.registrarVendedor(datos);
				   break;
			   
			   case 3:
				   cli.registrarVenta(datos);
				   break;
				   
			   case 4 :
				   cli.buscarProducto(datos);
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