package main;

import presentacion.MenuPrincipalCLI;

public class Main 
{

	public static void main(String[] args) {
		MenuPrincipalCLI cli = new MenuPrincipalCLI();
		cli.getMenuPrincipal();
		String input = cli.obtenerInput();
		while(!cli.esInputValido(input)) {
			cli.limpiarConsola();
			cli.getMenuPrincipal();
			input = cli.obtenerInput();
		}
		
    }
}