package persistencia.transacciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import modelo.Vendedor;
import persistencia.conexion.Conexion;

public class VendedorSQL {

	public static boolean insert(Vendedor vendedor) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulEntry = false;

		try {
			entity.getTransaction().begin();
			entity.persist(vendedor);
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
	
	public static boolean update(Vendedor vendedor) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulUpdate = false;
		if( entity.find(Vendedor.class,vendedor.getCodigo()) != null) {
			try {
				entity.getTransaction().begin();
				entity.merge(vendedor);
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
	
	public static boolean remove(Vendedor vendedor) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulRemoval = false;
		if( entity.find(Vendedor.class,vendedor.getCodigo()) != null) {
			try {
				entity.getTransaction().begin();
				entity.remove(vendedor);
				entity.getTransaction().commit();
				successfulRemoval = true;
				entity.flush();
			    entity.clear();
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
	
	
	//busqueda por codigo, o por nombre
	@SuppressWarnings("unchecked")
	public static List<Vendedor> searchProducts(String tipoDeBusqueda, Object dato) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		List<Vendedor> vendedores = new ArrayList<Vendedor>();
		String datoAbuscar = dato.toString();
		if( tipoDeBusqueda.trim().equals("codigo".trim())) {
			Vendedor vendedorEncontrado = entity.find(Vendedor.class,Integer.parseInt(datoAbuscar));
			if( vendedorEncontrado != null) {
				vendedores.add(vendedorEncontrado);
			}
		}
		
		if( tipoDeBusqueda.trim().equals("nombre".trim())) {
			Query query = entity.createQuery("from Vendedor vendedor where vendedor.nombre='"+datoAbuscar+"' ");
			vendedores = query.getResultList();
		}
		
		entity.close();
		Conexion.shutdown(); 
		return vendedores;
	}

}
