package main;

import presentacion.MenuPrincipalCLI;

public class Main 
{

	public static void main(String[] args) {
		MenuPrincipalCLI cli = new MenuPrincipalCLI();
		String input = cli.obtenerInput();
		while(!cli.esValido(input)) {
			cli.limpiarConsola();
			cli.mostrar();
			input = cli.obtenerInput();
		}
		
    }
}