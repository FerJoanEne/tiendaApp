package persistencia.transacciones;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;

import modelo.Venta;
import persistencia.conexion.Conexion;

public class VentaSQL {
	
	public static boolean insert(Venta venta) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulEntry = false;

		try {
			entity.getTransaction().begin();
			entity.persist(venta);
			entity.getTransaction().commit();
			successfulEntry = true;
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
		
		return successfulEntry; 
		
	}
	
	public static boolean update(Venta venta) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulUpdate = false;
		if( entity.find(Venta.class, venta.getIDventa()) != null) {
			try {
				entity.getTransaction().begin();
				entity.merge(venta);
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
	
	public static boolean remove(Venta venta) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulRemoval = false;
		if( entity.find(Venta.class, venta.getIDventa()) != null) {
			try {
				entity.getTransaction().begin();
				entity.remove(venta);
				entity.getTransaction().commit();
				successfulRemoval = true;
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
		
		return successfulRemoval; 
		
	}
	
}
