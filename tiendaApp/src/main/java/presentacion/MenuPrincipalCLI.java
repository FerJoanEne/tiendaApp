package presentacion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.Producto;
import modelo.Vendedor;
import modelo.Venta;
import persistencia.transacciones.ProductoSQL;
import persistencia.transacciones.VendedorSQL;
import persistencia.transacciones.VentaSQL;


public class MenuPrincipalCLI {
	
	private StringBuilder menuPrincipal;
	private StringBuilder menuBuscarProducto;
	private StringBuilder menuBuscarVendedor;

	
	public MenuPrincipalCLI() {
		this.menuPrincipal = new StringBuilder();
		this.menuBuscarProducto = new StringBuilder();
		this.menuBuscarVendedor = new StringBuilder();
		
		this.menuPrincipal.append("Elija una opcion [1 - 7] \n");
		this.menuPrincipal.append("------------------- \n");
		this.menuPrincipal.append("1. Registrar producto \n");
		this.menuPrincipal.append("2. Registrar vendedor \n");
		this.menuPrincipal.append("3. Registrar venta \n");
		this.menuPrincipal.append("------------------- \n");
		this.menuPrincipal.append("4.Buscar producto \n");
		this.menuPrincipal.append("5.Buscar vendedor \n");
		this.menuPrincipal.append("------------------- \n");
		this.menuPrincipal.append("6. Calcular comision por vendedor \n");
		this.menuPrincipal.append("------------------- \n");
		this.menuPrincipal.append("7. FINALIZAR \n");
		
		this.menuBuscarProducto.append("1. Buscar por codigo \n");
		this.menuBuscarProducto.append("2. Buscar por categoria \n");
		this.menuBuscarProducto.append("3. Buscar por nombre \n");
		this.menuBuscarProducto.append("4. Buscar por precio \n");
		
		this.menuBuscarVendedor.append("1. Buscar vendedor por nombre \n");
		this.menuBuscarVendedor.append("2. Buscar vendedor por codigo \n");
	}
	
	
	public void getMenuPrincipal() {
		System.out.println(this.menuPrincipal.toString());
	}

	public void getMenuBuscarProducto() {
		System.out.println(this.menuBuscarProducto.toString());
	}

	public void getMenuBuscarVendedor() {
		System.out.println(this.menuBuscarVendedor.toString());
	}


	public void ejecutarOpcion(ArrayList<String> datos) {
		switch(Integer.parseInt(datos.get(0))) {
		   case 1 :
			   registrarProducto(datos);
			   break;   
			   
		   case 2 :
			   registrarVendedor(datos);
			   break;
		   
		   case 3:
			   registrarVenta(datos);
			   break;
			   
		   case 4 :
			   buscarProducto(datos);
			   break;

		   case 5:
			   buscarVendedor(datos);
			   break;
			   
		   case 6:
			   calcularComision(datos);
			   break;
			   
		   case 7:
			   finalizar();
			   break;
			   
		   default : 
		     System.out.println("algo salio mal que llego al default");
		}
		
	}
	

	private boolean registrarVenta(ArrayList<String> datos) {
		return VentaSQL.insert(codVendedor, nombreProducto, precio);
	}
	
	private boolean registrarVendedor(ArrayList<String> datos) {
		return VendedorSQL.insert(nombre, sueldo);
	}

	private boolean registrarProducto(ArrayList<String> datos) {
		return ProductoSQL.insert(nombre, precio, categoria);
	}
	
	private static void buscarProducto(ArrayList<String> datos) {
		List<Producto> productos = ProductoSQL.searchProducts(tipoDeBusqueda, datoIngresado);
		
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
		
	}
	
	public String obtenerInput() {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		return input;
	}


	public boolean esInputValido(String input) {
		String dato = input.toString();
		Pattern pat = Pattern.compile("^[1-7]{1}");
	    Matcher mat = pat.matcher(dato);                                                                           
	    return mat.matches();
	}
	
	public boolean validarDouble(String dato) {
		try {
		    double precio = Double.parseDouble(dato);
		    return true;
		}	catch(NumberFormatException e) 
		  {
		    return false;
		  }
	}

	
	public boolean validarInteger(String id) {
		try {
			Integer.parseInt(id);
		    return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	public void finalizar() {
		System.out.println("Programa finalizado");
        System.exit(0);
	}

	public void limpiarConsola(){
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
