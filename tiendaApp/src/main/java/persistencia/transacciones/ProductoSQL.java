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
				System.err.printf("No se pudo revertir la transacci�n: ", runtimeEx);
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
					System.err.printf("No se pudo revertir la transaccion: ", runtimeEx);
				}
			}
		}
		
		return successfulUpdate; 
		
	}
	//busqueda por ID, por nombre, por categoria, por precio
	@SuppressWarnings("unchecked")
	public static List<Producto> searchProducts(String tipoDeBusqueda, Object dato) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		List<Producto> productos = new ArrayList<Producto>();
		String datoAbuscar = dato.toString();
		if( tipoDeBusqueda.trim().equals("ID".trim())) {
			Producto productoEncontrado = entity.find(Producto.class,Long.parseLong(datoAbuscar));
			if( productoEncontrado != null) {
				productos.add(productoEncontrado);
			}
		}
		
		if( tipoDeBusqueda.trim().equals("nombre".trim())) {
			Query query = entity.createQuery("from Producto producto where producto.nombre='"+datoAbuscar+"' ");
			productos = query.getResultList();
		}
		
		if( tipoDeBusqueda.trim().equals("precio".trim())) {
			Query query = entity.createQuery("from Producto producto where producto.precio='"+Double.parseDouble(datoAbuscar)+"' ");
			productos = query.getResultList();
		}
		
		if ( tipoDeBusqueda.trim().equals("categoria".trim())) {
			Query query = entity.createQuery("from Producto producto where producto.categoria='"+datoAbuscar+"' ");
			productos = query.getResultList();
		}
		
		return productos;
	}
	
}
