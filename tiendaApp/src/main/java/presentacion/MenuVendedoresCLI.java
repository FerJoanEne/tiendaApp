package presentacion;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.Vendedor;
import persistencia.transacciones.VendedorSQL;

public class MenuVendedoresCLI {
	
	public MenuVendedoresCLI() {
		String input = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU VENDEDORES");
		System.out.println("1. INSERTAR VENDEDOR");
		System.out.println("2. ELIMINAR VENDEDOR");
		System.out.println("3. volver al menu anterior");
		System.out.println("------------------------------------------------------------------");
		input= sc.nextLine();
		if(validarInput(input)) {
				ejecutarOpcion(input);
		} else {
			new MenuVendedoresCLI();
		};

	}
	
	private static void ejecutarOpcion(String input) {
		int opcion = Integer.parseInt(input);
		MenuPrincipalCLI.limpiarConsola();
		switch(opcion) {
		   case 1 :
			  insertarVendedor();
		      break;
		   
		   case 2 :
		      eliminarVendedor();
		      break;
			   
		   case 3:
			   new MenuPrincipalCLI();
			   break;
		   default : 
		     System.out.println("algo salio mal que llego al default");
		}
			
	}

	private static void eliminarVendedor() {
		
	}

	private static void insertarVendedor() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese nombre del vendedor");
		String inputNombre = scan.nextLine();
		
		System.out.println("Ingrese apellido del vendedor");
		String inputApellido = scan.nextLine();
		
		System.out.println("Ingrese el sueldo del vendedor");
		String inputSueldo = scan.nextLine();
		
		while(!validarSueldo(inputSueldo)) {
		    System.out.println("error, ingrese un precio valido");
		    inputSueldo = scan.nextLine();
		}
		
		Vendedor vendedor = new Vendedor();
		vendedor.setNombre(inputNombre+" "+inputApellido);
		vendedor.setSueldo(Double.parseDouble(inputSueldo));
		
		System.out.println("insertando.....");
		
		if(VendedorSQL.insert(vendedor)) {
			System.out.println("INGRESO EXITOSO");
		} else {
			System.out.println("hubo un error al hacer el ingreso");
		}
		
	}

	private static boolean validarInput(String input) {
		String dato = input.toString();
		Pattern pat = Pattern.compile("^[1-3]{1}");
	    Matcher mat = pat.matcher(dato);                                                                           
	    return mat.matches();
	}
	
	private static boolean validarSueldo(String dato) {
		try {
		    double precio = Double.parseDouble(dato);
		    return true;
		}	catch(NumberFormatException e) 
		  {
		    return false;
		  }
	}
	
	
}
