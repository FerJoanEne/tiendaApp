package presentacion;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import persistencia.conexion.Conexion;


public class MenuPrincipalCLI {
	
	public MenuPrincipalCLI() {

		String input = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU PRINCIPAL");
		System.out.println("1. PRODUCTOS");
		System.out.println("2. VENDEDORES");
		System.out.println("3. INSERTAR UNA VENTA");
		System.out.println("4. FINALIZAR");
		System.out.println("------------------------------------------------------------------");
		input = sc.next();
		if(validarInput(input)) {
			limpiarConsola();
			mostrarMenu(Integer.parseInt(input));
		} else {
			new MenuPrincipalCLI();
		}
	}
	

	private static void mostrarMenu(int input) {
		switch(input) {
		   case 1 :
		      new MenuProductosCLI();
		      break;
		   
		   case 2 :
		      new MenuVendedoresCLI();
		      break;
		   
		   case 3:
			  new MenuVentasCLI();
			  break;
			   
		   case 4:
			   finalizar();
			   break;
		   default : 
		     System.out.println("algo salio mal que llego al default");
		}
			
		
	}

	private static boolean validarInput(Object input) {
		String dato = input.toString();
		Pattern pat = Pattern.compile("^[1-4]{1}");
	    Matcher mat = pat.matcher(dato);                                                                           
	    return mat.matches();
	}
	
	private static void finalizar() {
		Conexion.shutdown();
		System.out.println("Programa finalizado");
        System.exit(0);
	}

	public static void limpiarConsola(){
        try{
        	
        	if (System.getProperty("os.name").contains("Windows") || System.getProperty("os.name").contains("windows"))
            {
              new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else
            {
            	System.out.print("\033[H\033[2J");
            	System.out.flush();

            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}