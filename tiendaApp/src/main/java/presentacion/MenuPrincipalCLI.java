package presentacion;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.Producto;
import persistencia.transacciones.ProductoSQL;
import persistencia.transacciones.VendedorSQL;
import persistencia.transacciones.VentaSQL;


public class MenuPrincipalCLI {
	
	private StringBuilder menuPrincipal;
	private StringBuilder menuBuscarProducto;
	private StringBuilder menuBuscarVendedor;
	
	private ArrayList<String> datosProducto;
	private ArrayList<String> datosVendedor;
	private ArrayList<String> datosVenta;

	
	public MenuPrincipalCLI() {
		this.menuPrincipal = new StringBuilder();
		this.menuBuscarProducto = new StringBuilder();
		this.menuBuscarVendedor = new StringBuilder();
		
		this.datosProducto = new ArrayList<String>();
		this.datosProducto.add("Ingrese nombre");
		this.datosProducto.add("Ingrese precio");
		this.datosProducto.add("Ingrese categoria");
		
		this.datosVendedor = new ArrayList<String>();
		this.datosVendedor.add("Ingrese nombre");
		this.datosVendedor.add("Ingrese precio");
		this.datosVendedor.add("Ingrese categoria");
		
		this.datosVenta = new ArrayList<String>();
		this.datosVenta.add("Ingrese codigo del vendedor");
		this.datosVenta.add("Ingrese nombre del producto");
		this.datosVenta.add("Ingrese el precio del producto");
		
		this.menuPrincipal.append("Elija una opcion [1 - 6] \n");
		this.menuPrincipal.append("------------------- \n");
		this.menuPrincipal.append("1. Registrar producto \n");
		this.menuPrincipal.append("2. Registrar vendedor \n");
		this.menuPrincipal.append("3. Registrar venta \n");
		this.menuPrincipal.append("------------------- \n");
		this.menuPrincipal.append("4.Buscar producto \n");
		this.menuPrincipal.append("------------------- \n");
		this.menuPrincipal.append("5. Calcular comision por vendedor \n");
		this.menuPrincipal.append("------------------- \n");
		this.menuPrincipal.append("6. FINALIZAR \n");
		
		this.menuBuscarProducto.append("1. Buscar por codigo \n");
		this.menuBuscarProducto.append("2. Buscar por categoria \n");
		this.menuBuscarProducto.append("3. Buscar por nombre \n");
		this.menuBuscarProducto.append("4. Buscar por precio \n");
		
		this.menuBuscarVendedor.append("1. Buscar vendedor por nombre \n");
		this.menuBuscarVendedor.append("2. Buscar vendedor por codigo \n");
	}
	
	public ArrayList<String> getDatosProducto() {
		return datosProducto;
	}

	public ArrayList<String> getDatosVendedor() {
		return datosVendedor;
	}

	public ArrayList<String> getDatosVenta() {
		return datosVenta;
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

	public boolean registrarVenta(ArrayList<String> datos) {
		return VentaSQL.insert(Integer.parseInt(datos.get(0)), datos.get(1), Double.parseDouble(datos.get(2)));
	}
	
	public boolean registrarVendedor(ArrayList<String> datos) {
		return VendedorSQL.insert(datos.get(0), Double.parseDouble(datos.get(1)));
	}

	public boolean registrarProducto(ArrayList<String> datos) {
		return ProductoSQL.insert(datos.get(0), Double.parseDouble(datos.get(1)), datos.get(2));
	}
	
	public void buscarProducto(ArrayList<String> datos) {
		List<Producto> productos = ProductoSQL.searchProducts(datos.get(0), datos.get(1));
		
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

	public boolean esInputValido(String input) {
		String dato = String.valueOf(input);
		Pattern pat = Pattern.compile("^[1 - 6]{1}");
	    Matcher mat = pat.matcher(dato.trim());                                                                           
	    return mat.matches();
	}
	
	public boolean esInputValidoBuscarProducto(String input) {
		String dato = String.valueOf(input);
		Pattern pat = Pattern.compile("^[1 - 4]{1}");
	    Matcher mat = pat.matcher(dato.trim());                                                                           
	    return mat.matches();
	}
	
	public boolean validarDouble(String dato) {
		try {
		    Double.parseDouble(dato);
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

	public boolean comprobaryEjecutar(ArrayList<String> datos) {
		boolean sonDatosValidos = false;
		boolean transaccionExitosa = false;
		int opcion = Integer.parseInt(datos.remove(0));
		switch(opcion) {
			case 1:
				if(validarInteger(datos.get(0)) && validarDouble(datos.get(2))) {
					sonDatosValidos = true;
					transaccionExitosa = registrarProducto(datos);
					
				}
				break;
			case 2:
				if(validarDouble(datos.get(2))) {
					sonDatosValidos = true;
					transaccionExitosa = registrarVendedor(datos);
				}
				break;
			case 3:
				if(validarInteger(datos.get(0)) && validarDouble(datos.get(2))) {
					sonDatosValidos = true;
					transaccionExitosa = registrarVenta(datos);
				}
				break;
			case 4:
				if(datos.get(0).equals("1") && validarInteger(datos.get(1))) {
					sonDatosValidos = true;
					datos.remove(0);
					buscarProducto(datos);
				}
				break;
			case 5:
				System.out.println("calcular comision por vendedor");
				break;
			default:
				System.out.println("Algo salio mal que llego al default");
		}
		
		if(sonDatosValidos) {
			if(transaccionExitosa) {
				System.out.println("Transaccion exitosa");
				return true;
			}
			else {
				System.out.println("No pudo concretarse la transaccion");
				return false;
			}
		}
		System.out.println("error en los datos, volviendo al menu principal...");
		return false;
		
	}
}
