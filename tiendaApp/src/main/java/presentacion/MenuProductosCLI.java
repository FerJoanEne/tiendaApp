package presentacion;

import java.util.Scanner;

import modelo.Producto;
import persistencia.transacciones.ProductoSQL;

public class MenuProductosCLI {
	
	public MenuProductosCLI() {
		int input = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("1. INSERTAR PRODUCTO");
		System.out.println("2. BUSCAR PRODUCTO");
		System.out.println("3. ELIMINAR PRODUCTO");
		System.out.println("4. volver al menu anterior");
		System.out.println("------------------------------------------------------------------");
		input = sc.nextInt();
		ejecutarOpcion(input);
		//mostrarMenu(input);
			/*if(validarInput(input)) {
				mostrarOpcion(input);
			};*/

	}
	
	
	private static void ejecutarOpcion(int input) {
		MenuPrincipalCLI.limpiarConsola();
	    System.out.println("La opcion elegida es: " + input);
		switch(input) {
		   case 1 :
			  crearProducto();
		      break;
		   
		   case 2 :
		      new MenuVendedoresCLI();
		      break;
		   
		   case 3:
			   new MenuVentasCLI();
			   break;
			   
		   case 4:
			   MenuPrincipalCLI.limpiarConsola();
			   break;
		   default : 
		     System.out.println("algo salio mal que llego al default");
		}
			
		
	}
	
	private static void crearProducto() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese nombre del producto");
		String inputNombre = scan.nextLine();

		System.out.println("Ingrese categoria del producto");
		String inputCategoria = scan.nextLine();

		System.out.println("Ingrese el precio del producto");
		double inputPrecio = scan.nextDouble();
		
		Producto producto = new Producto();
		producto.setNombre(inputNombre);
		producto.setCategoria(inputCategoria);
		producto.setPrecio(inputPrecio);
		
		if(ProductoSQL.insert(producto)) {
			System.out.println("INGRESO EXITOSO");
		}
		
		System.out.println("hubo un error al ingresar el producto");
		
	}
	

}
