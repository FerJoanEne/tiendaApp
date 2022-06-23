package presentacion;

import java.util.Scanner;

public class CLI {
	
	public static void mostrarMenuPrincipal() {
		int input = 0;
		Scanner sc = new Scanner(System.in);
		while(input != 5) {
			System.out.println("1. PRODUCTOS");
			System.out.println("2. VENDEDORES");
			System.out.println("3. VENTAS");
			System.out.println("4. FINALIZAR");
			input = sc.nextInt();
			comprobarOpcion(input);
		}
	}
	
	private static void comprobarOpcion(int input) {
		validarInput(input);
		
	}

	private static void validarInput(Object input) {
		
		
		
	}

	public static void limpiarConsola(){
        try{
        	
        	if (System.getProperty("os.name").contains("Windows"))
            {
              new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else
            {
              System.out.print(System.getProperty("os.name"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}