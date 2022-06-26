package presentacion;

import java.util.Scanner;

public class MenuVendedoresCLI {
	
	public MenuVendedoresCLI() {
		int input = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU VENDEDORES");
		System.out.println("1. INSERTAR VENDEDOR");
		System.out.println("2. BUSCAR VENDEDOR");
		System.out.println("3. ELIMINAR VENDEDOR");
		System.out.println("4. volver al menu anterior");
		System.out.println("------------------------------------------------------------------");
		input = sc.nextInt();
		//mostrarMenu(input);
			/*if(validarInput(input)) {
				mostrarOpcion(input);
			};*/

	}
}
