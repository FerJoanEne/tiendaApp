package presentacion;

import java.util.Scanner;

public class CLI {
	
	public CLI() {
		int input = 0;
		Scanner sc = new Scanner(System.in);
		while(input != 5) {
			System.out.println("1. PRODUCTOS");
			System.out.println("2. VENDEDORES");
			System.out.println("3. VENTAS");
			System.out.println("4. FINALIZAR");
			input = sc.nextInt();
			mostrarOpcion(input);
			/*if(validarInput(input)) {
				mostrarOpcion(input);
			};*/
		}
	}
	

	private static void mostrarOpcion(int input) {
	    System.out.println("La opcion elegida es: " + input);
		switch(input) {
		   case 1 :
		      menuProductos();
		      break;
		   
		   case 2 :
		      menuVendedores();
		      break;
		   
		   case 3:
			   menuVentas();
			   break;
			   
		   case 4:
			   finalizar();
			   break;
		   default : 
		     System.out.println("algo salio mal que llego al default");
		}
			
		
	}


	private static void menuVentas() {
		// TODO Auto-generated method stub
		System.out.println("entraste al menu ventas");
		
	}


	private static void menuVendedores() {
		// TODO Auto-generated method stub
		System.out.println("entraste al menu vnededores");		
	}


	private static void menuProductos() {
		// TODO Auto-generated method stub
		System.out.println("entraste al menu productos");
	}


	private static boolean validarInput(Object input) {
		return false;
		
		
		
	}
	
	private static void finalizar() {
		System.out.println("Programa finalizado");
        System.exit(0);
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