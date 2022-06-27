package presentacion;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.util.SystemPropertiesPropertySource;

import modelo.Producto;
import persistencia.transacciones.ProductoSQL;

public class MenuProductosCLI {
	
	public MenuProductosCLI() {
		String input = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU PRODUCTOS");
		System.out.println("1. INSERTAR PRODUCTO");
		System.out.println("2. BUSCAR PRODUCTO");
		System.out.println("3. ELIMINAR PRODUCTO");
		System.out.println("4. volver al menu anterior");
		System.out.println("------------------------------------------------------------------");
		input = sc.nextLine();
		if(validarInput(input)) {
			ejecutarOpcion(input);
		};

	}
	
	
	private static void ejecutarOpcion(String input) {
		int opcion = Integer.parseInt(input);
		MenuPrincipalCLI.limpiarConsola();
		switch(opcion) {
		   case 1 :
			  crearProducto();
		      break;
		   
		   case 2 :
		      eliminarProdutcto();
		      break;
		   
		   case 3:
			   buscarProducto();
			   break;
			   
		   case 4:
			   new MenuPrincipalCLI();
			   break;
		   default : 
		     System.out.println("algo salio mal que llego al default");
		}
			
		
	}
	
	private static void buscarProducto() {
		// TODO Auto-generated method stub
		
	}


	private static void eliminarProdutcto() {
		// TODO Auto-generated method stub
		
	}


	private static void crearProducto() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese nombre del producto");
		String inputNombre = scan.nextLine();

		System.out.println("Ingrese categoria del producto");
		String inputCategoria = scan.nextLine();

		System.out.println("Ingrese el precio del producto");
		String inputPrecio = scan.nextLine();
		
		while(!validarPrecio(inputPrecio)) {
		    System.out.println("error, ingrese un precio valido");
		    System.out.println("Ingrese el precio del producto");
		    inputPrecio = scan.nextLine();
		}
		
		Producto producto = new Producto();
		producto.setNombre(inputNombre);
		producto.setCategoria(inputCategoria);
		producto.setPrecio(Double.parseDouble(inputPrecio));
		
		if(ProductoSQL.insert(producto)) {
			System.out.println("INGRESO EXITOSO");
		} else {

			System.out.println("hubo un error al ingresar el producto");
		}
		
		
	}
	
	private static boolean validarInput(String input) {
		String dato = input.toString();
		Pattern pat = Pattern.compile("^[1-4]{1}");
	    Matcher mat = pat.matcher(dato);                                                                           
	    return mat.matches();
	}
	
	private static boolean validarPrecio(String dato) {
		try {
		    double precio = Double.parseDouble(dato);
		    return true;
		}	catch(NumberFormatException e) 
		  {
		    return false;
		  }
	}
}
