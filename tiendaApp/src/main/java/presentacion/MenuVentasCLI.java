package presentacion;

import java.util.Scanner;

public class MenuVentasCLI {
	
	public MenuVentasCLI() {
		// TODO Auto-generated method stub
		int inputVentas = 0;
		Scanner scan = new Scanner(System.in);
		inputVentas = scan.nextInt();
		System.out.println("MENU VENTAS");
		System.out.println("1. INSERTAR VENTA");
		System.out.println("2. BUSCAR VENTA POR ID");
		System.out.println("3. BUSCAR VENTAS POR VENDEDOR");
		System.out.println("4. Volver al menu anterior");
		System.out.println("------------------------------------------------------------------");
		inputVentas = scan.nextInt();
		
	}
	
	
	
}
