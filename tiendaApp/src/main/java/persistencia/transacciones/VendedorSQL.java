package persistencia.transacciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import modelo.Vendedor;
import persistencia.conexion.Conexion;

public class VendedorSQL {

	public static boolean insert(String nombreVendedor, double sueldo) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulEntry = false;
		Vendedor vendedor = new Vendedor();
		vendedor.setNombre(nombreVendedor);
		vendedor.setSueldo(sueldo);
		try {
			entity.getTransaction().begin();
			entity.persist(vendedor);
			entity.getTransaction().commit();
			successfulEntry = true;
			entity.close();
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
	
	
	public static boolean remove(int codigo) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulRemoval = false;
		Vendedor vendedorAuxiliar = entity.find(Vendedor.class,codigo);
		if( vendedorAuxiliar != null) {
			try {
				entity.getTransaction().begin();
				entity.remove(vendedorAuxiliar);
				entity.getTransaction().commit();
				successfulRemoval = true;
				entity.flush();
			    entity.clear();
				entity.close(); 							///ver si falla
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
	public static List<Vendedor> searchVendedor(String tipoDeBusqueda, Object dato) {
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

		return vendedores;
	}

}
