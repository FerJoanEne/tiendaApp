package persistencia.transacciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;

import modelo.ProductosVendidos;
import persistencia.conexion.Conexion;

public class ProductosVendidosSQL {
	
	public static boolean insert(ProductosVendidos prodVendidos) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulEntry = false;

		try {
			entity.getTransaction().begin();
			entity.persist(prodVendidos);
			entity.getTransaction().commit();
			successfulEntry = true;
			entity.close();						///ver si falla
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
	
	public static boolean update(ProductosVendidos prodVendidos) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulUpdate = false;
		if( entity.find(ProductosVendidos.class,prodVendidos.getIDVenta()) != null) {
			try {
				entity.getTransaction().begin();
				entity.merge(prodVendidos);
				entity.getTransaction().commit();
				successfulUpdate = true;
				entity.close();							///ver si falla
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
	
	public static boolean remove(ProductosVendidos prodVendidos) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulRemoval = false;
		if( entity.find(ProductosVendidos.class,prodVendidos.getIDVenta()) != null) {
			try {
				entity.getTransaction().begin();
				entity.remove(prodVendidos);
				entity.getTransaction().commit();
				successfulRemoval = true;
				entity.close();					///ver si falla
			} catch(HibernateException hibernateEx) {
				try {
					entity.getTransaction().rollback();
					System.out.println(hibernateEx);
				} catch (RuntimeException runtimeEx) {
					System.err.printf("No se pudo revertir la transaccion: ", runtimeEx);
				}
			}
		}
		
		return successfulRemoval; 
		
	}
	
	//busqueda por ID, por nombre, por categoria, por precio
	@SuppressWarnings("unchecked")
	public static List<ProductosVendidos> searchProducts(String tipoDeBusqueda, Object dato) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		List<ProductosVendidos> prodVendidos = new ArrayList<ProductosVendidos>();
		String datoAbuscar = dato.toString();
		if( tipoDeBusqueda.trim().equals("IDVenta".trim())) {
			ProductosVendidos prodVendidoEncontrado = entity.find(ProductosVendidos.class,Integer.parseInt(datoAbuscar));
			if( prodVendidoEncontrado != null) {
				prodVendidos.add(prodVendidoEncontrado);
			}
		}
		
		entity.close();
		return prodVendidos;
	}
	
}
