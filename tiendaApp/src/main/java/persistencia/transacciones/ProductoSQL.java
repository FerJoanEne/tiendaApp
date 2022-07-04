package persistencia.transacciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import modelo.Producto;
import persistencia.conexion.Conexion;

public class ProductoSQL
{	
	
	public static boolean insert(String nombreProducto, double precio, String categoria) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulEntry = false;
		Producto producto = new Producto();
		producto.setNombre(nombreProducto);
		producto.setPrecio(precio);
		producto.setCategoria(categoria);

		try {
			entity.getTransaction().begin();
			entity.persist(producto);
			entity.getTransaction().commit();
			successfulEntry = true;
			entity.close();
		} catch(HibernateException hibernateEx) {
			try {
				entity.getTransaction().rollback();
				return successfulEntry;
			} catch (RuntimeException runtimeEx) {
				return successfulEntry;
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
			} catch(HibernateException hibernateEx) {
				try {
					entity.getTransaction().rollback();
					return successfulUpdate;
				} catch (RuntimeException runtimeEx) {
					return successfulUpdate;
				}
			}
		}
		
		return successfulUpdate; 
		
	}
	
	public static boolean remove(Producto producto) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulRemoval = false;
		if( entity.find(Producto.class,producto.getCodigo()) != null) {
			try {
				entity.getTransaction().begin();
				entity.remove(producto);
				entity.getTransaction().commit();
				successfulRemoval = true;
				entity.close(); 							///ver si falla
			} catch(HibernateException hibernateEx) {
				try {
					entity.getTransaction().rollback();
					return successfulRemoval;
				} catch (RuntimeException runtimeEx) {
					return successfulRemoval;
				}
			}
		}
		
		return successfulRemoval; 
		
	}
	
	//busqueda por ID, por nombre, por categoria, por precio
	@SuppressWarnings("unchecked")
	public static List<Producto> searchProducts(String tipoDeBusqueda, Object dato) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		List<Producto> productos = new ArrayList<Producto>();
		String datoAbuscar = dato.toString();
		if( tipoDeBusqueda.trim().equals("1".trim())) {
			Producto productoEncontrado = entity.find(Producto.class,Integer.parseInt(datoAbuscar));
			if( productoEncontrado != null) {
				productos.add(productoEncontrado);
			}
		}
		
		if ( tipoDeBusqueda.trim().equals("2".trim())) {
			Query query = entity.createQuery("from Producto producto where producto.categoria='"+datoAbuscar+"' ");
			productos = query.getResultList();
		}
		
		if( tipoDeBusqueda.trim().equals("3".trim())) {
			Query query = entity.createQuery("from Producto producto where producto.nombre='"+datoAbuscar+"' ");
			productos = query.getResultList();
		}
		
		if( tipoDeBusqueda.trim().equals("4".trim())) {
			Query query = entity.createQuery("from Producto producto where producto.precio='"+Double.parseDouble(datoAbuscar)+"' ");
			productos = query.getResultList();
		}
		
		entity.close();
		return productos;
	}
	
}
