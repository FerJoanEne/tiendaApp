package presentacion;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.util.SystemPropertiesPropertySource;

import modelo.Producto;
import persistencia.transacciones.ProductoSQL;

public class MenuProductosCLI {
	
	public MenuProductosCLI() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU PRODUCTOS");
		System.out.println("1. INSERTAR PRODUCTO");
		System.out.println("2. BUSCAR PRODUCTO");
		System.out.println("3. ELIMINAR PRODUCTO");
		System.out.println("4. volver al menu anterior");
		System.out.println("------------------------------------------------------------------");
		String input = sc.next();
		if(validarInput(input)) {
			ejecutarOpcion(input);
		} else {
			MenuPrincipalCLI.limpiarConsola();
			System.out.println("incorrecto, por favor ingrese una opción válida");
			new MenuProductosCLI();
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
		      eliminarProducto();
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
		
		MenuPrincipalCLI.limpiarConsola();
		Scanner sc = new Scanner(System.in);
		String op = sc.next();
		if(validarInput(op)) {
			System.out.println("Seleccione la opcion de busqueda [1 - 4]");
			System.out.println("1. Buscar por nombre");
			System.out.println("2. Buscar por categoria");
			System.out.println("3. volver al menu de productos");
			System.out.println("4. volver al menu principal");
			switch(Integer.parseInt(op)) {
			   case 1 :
				  buscarProductoPorNombre();
			      break;
			   
			   case 2 :
			      buscarProductoPorCategoria();
			      break;
			   
			   case 3:
				   MenuPrincipalCLI.limpiarConsola();
				   new MenuProductosCLI();
				   break;
				   
			   case 4:
				   MenuPrincipalCLI.limpiarConsola();
				   new MenuPrincipalCLI();
				   break;
			   default : 
			     System.out.println("algo salió mal que llegó al default");
			}
		} else {
			MenuPrincipalCLI.limpiarConsola();
			System.out.println("incorrecto, por favor ingrese una opción válida");
			buscarProducto();
		};
		sc.close();
		new MenuProductosCLI();
	}


	private static void buscarProductoPorCategoria() {
		Scanner sc = new Scanner(System.in);
		String inputCategoriaProducto = sc.next();
		
		List<Producto> productos = ProductoSQL.searchProducts("categoria", inputCategoriaProducto);
		if(productos.size()>0) {
			System.out.println("Cantidad de resultados obtenidos: "+productos.size());
			for(Producto p : productos) {
				System.out.println("-----------------------");
				System.out.println("codigo: " + p.getCodigo());
				System.out.println("Nombre: " + p.getNombre());
				System.out.println("Categoria: " + p.getCategoria());
				System.out.println("Precio: " + p.getPrecio());
			}
		} else {
			System.out.println("sin resultados");
		}
		sc.close();
		
	}


	private static void buscarProductoPorNombre() {
		Scanner sc = new Scanner(System.in);
		String inputNombreProducto = sc.next();
		
		List<Producto> productos = ProductoSQL.searchProducts("nombre", inputNombreProducto);
		if(productos.size()>0) {
			System.out.println("Cantidad de resultados obtenidos: "+productos.size());
			for(Producto p : productos) {
				System.out.println("-----------------------");
				System.out.println("codigo: " + p.getCodigo());
				System.out.println("Nombre: " + p.getNombre());
				System.out.println("Categoria: " + p.getCategoria());
				System.out.println("Precio: " + p.getPrecio());
			}
		} else {
			System.out.println("sin resultados");
		}
		sc.close();
		
	}


	private static void eliminarProducto() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el ID del producto a eliminar");
		
		String id = sc.nextLine();
		if(validarId(id)) {
			Producto producto = new Producto();
			producto.setCodigo(Integer.parseInt(id));
			System.out.println("eliminando.....");
			if(ProductoSQL.remove(producto)) {
				System.out.println("producto eliminado");
			}
		} else {
			System.out.println("ID no valido");
		}
		sc.close();
	}


	private static boolean validarId(String id) {
		try {
			Integer.parseInt(id);
		    return true;
		} catch(NumberFormatException e) {
			return false;
		}
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
		
		System.out.println("ingresando...");
		
		if(ProductoSQL.insert(producto)) {
			System.out.println("INGRESO EXITOSO");
		} else {
			System.out.println("hubo un error al ingresar el producto");
		}
		scan.close();
		MenuPrincipalCLI.limpiarConsola();
		new MenuProductosCLI();
		
	}
	
	private static boolean validarInput(String input) {
		String dato = input.toString();
		Pattern pat = Pattern.compile("^[1-4]{1}");
	    Matcher mat = pat.matcher(dato);                                                                           
	    return mat.matches();
	}
	
	private static boolean validarPrecio(String dato) {
		try {
			Double.parseDouble(dato);
		    return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
}
