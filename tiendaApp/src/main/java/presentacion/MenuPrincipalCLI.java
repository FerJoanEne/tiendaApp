package presentacion;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

<<<<<<< HEAD
import persistencia.conexion.Conexion;
=======
import modelo.Producto;
import modelo.Vendedor;
import persistencia.transacciones.ProductoSQL;
import persistencia.transacciones.VendedorSQL;
>>>>>>> branch 'master' of https://github.com/FerJoanEne/tiendaApp.git


public class MenuPrincipalCLI {
	
<<<<<<< HEAD
	public MenuPrincipalCLI() {

		String input = "";
=======
	private String input;
	
	public void mostrarMenu() {
>>>>>>> branch 'master' of https://github.com/FerJoanEne/tiendaApp.git
		Scanner sc = new Scanner(System.in);
<<<<<<< HEAD
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
=======
		System.out.println("Elija una opcion [1 - 10]");
		System.out.println("---------------------:");
		System.out.println("1. Registrar producto");
		System.out.println("2. Registrar vendedor");
		System.out.println("3. Registrar venta");
		System.out.println("---------------------");
		System.out.println("4. Buscar productos por categoria");
		System.out.println("5. Buscar productos por nombre");
		System.out.println("6. Buscar productos por codigo");
		System.out.println("7. Buscar productos por precio");
		System.out.println("----------------------------------");
		System.out.println("8. Calcular comision por vendedor");
		System.out.println("----------------------------------");
		System.out.println("9. Obtener BBDD");
		System.out.println("----------------------------------");
		System.out.println("10. FINALIZAR");
		this.input = sc.nextLine();
		limpiarConsola();
		ejecutarOpcion(input);
		sc.close();
>>>>>>> branch 'master' of https://github.com/FerJoanEne/tiendaApp.git
	}
	

<<<<<<< HEAD
	private static void mostrarMenu(int input) {
		switch(input) {
=======
	private void ejecutarOpcion(String input) {
		Scanner  sc = new Scanner(System.in);
		while(!esValido(input)) {
			System.out.println("Por favor ingrese una opcion valida [1 - 10]");
			input = sc.nextLine();
			this.input = input;
		}
		sc.close();
		
		switch(Integer.parseInt(input)) {
>>>>>>> branch 'master' of https://github.com/FerJoanEne/tiendaApp.git
		   case 1 :
			   registrarProducto();
			   break;
		   
		   case 2 :
			   registrarVendedor();
			   break;
		   
		   case 3:
<<<<<<< HEAD
			  new MenuVentasCLI();
			  break;
=======
			   registrarVenta();
			   break;
>>>>>>> branch 'master' of https://github.com/FerJoanEne/tiendaApp.git
			   
		   case 4 :
			   buscarProductoPorCategoria();
			   break;
			   
		   case 5:
			   buscarProductoPorNombre();
				  break;
				  
		   case 6 :
			   buscarProductoPorCodigo();
			   break;
					   
		   case 7:
			   buscarProductoPorPrecio();
			   break;
			   
		   case 8:
			   calcularComision();
			   break;
			   
		   case 9:
			   persistirBBDD();
			   break;
			   
		   case 10:
			   finalizar();
			   break;
		   default : 
		     System.out.println("algo salio mal que llego al default");
		}
		
	}

	private void registrarVendedor() {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese nombre del vendedor");
		String inputNombre = scan.nextLine();
		
		System.out.println("Ingrese apellido del vendedor");
		String inputApellido = scan.nextLine();
		
		System.out.println("Ingrese el sueldo del vendedor");
		String inputSueldo = scan.nextLine();
		
		while(!validarDouble(inputSueldo)) {
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
		scan.close();

	}


	private static void registrarProducto() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese nombre del producto");
		String inputNombre = scan.nextLine();

		System.out.println("Ingrese categoria del producto");
		String inputCategoria = scan.nextLine();

		System.out.println("Ingrese el precio del producto");
		String inputPrecio = scan.nextLine();
		
		while(!validarDouble(inputPrecio)) {
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
		
	}
	
	private String obtenerInput() {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();
		return input;
	}


	private static boolean esValido(String input) {
		String dato = input.toString();
		Pattern pat = Pattern.compile("^[1-10]{1}");
	    Matcher mat = pat.matcher(dato);                                                                           
	    return mat.matches();
	}
	
	private static boolean validarDouble(String dato) {
		try {
		    double precio = Double.parseDouble(dato);
		    return true;
		}	catch(NumberFormatException e) 
		  {
		    return false;
		  }
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

	private static void buscarProductoPorCodigo() {
		Scanner sc = new Scanner(System.in);
		String inputCodigoProducto = sc.next();
		
		List<Producto> productos = ProductoSQL.searchProducts("ID", inputCodigoProducto);
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
	
	private static void buscarProductoPorPrecio() {
		Scanner sc = new Scanner(System.in);
		String inputPrecioProducto = sc.next();
		
		List<Producto> productos = ProductoSQL.searchProducts("precio", inputPrecioProducto);
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

	private static boolean validarId(String id) {
		try {
			Integer.parseInt(id);
		    return true;
		} catch(NumberFormatException e) {
			return false;
		}
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