package persistencia.transacciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.HibernateException;

import modelo.Producto;
import persistencia.conexion.Conexion;

public class ProductoSQL
{	
	
	public static boolean insert(Producto producto) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulEntry = false;

		try {
			entity.getTransaction().begin();
			entity.persist(producto);
			entity.getTransaction().commit();
			successfulEntry = true;
			entity.close();
			Conexion.shutdown(); 							///ver si falla
		} catch(HibernateException hibernateEx) {
			try {
				entity.getTransaction().rollback();
				System.out.println(hibernateEx);
			} catch (RuntimeException runtimeEx) {
				System.err.printf("No se pudo revertir la transacción: ", runtimeEx);
			}
		}
		
		return successfulEntry; 
		
	}
	
	public static boolean update(Producto producto) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulUpdate = false;
		if( entity.find(Producto.class,producto.getCodigo()) != null) {
			try {
				entity.getTransaction().begin();
				entity.merge(producto);
				entity.getTransaction().commit();
				successfulUpdate = true;
				entity.close();
				Conexion.shutdown(); 							///ver si falla
			} catch(HibernateException hibernateEx) {
				try {
					entity.getTransaction().rollback();
					System.out.println(hibernateEx);
				} catch (RuntimeException runtimeEx) {
					System.err.printf("No se pudo revertir la transacción: ", runtimeEx);
				}
			}
		}
		
		return successfulUpdate; 
		
	}
	//busqueda por ID, por nombre, por categoria, por precio
	public static List<Producto> searchProducts(String tipoDeBusqueda, Object dato) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		String datoAbuscar = dato.toString();
		if( tipoDeBusqueda.trim().equals("ID".trim())) {
			Producto productoEncontrado = entity.find(Producto.class,Integer.parseInt(datoAbuscar));
			if( productoEncontrado != null) {
				productos.add(productoEncontrado);
			}
		}
		
		if( tipoDeBusqueda.trim().equals("nombre".trim())) {
			
		}
		
		return productos;
	}
	
}
