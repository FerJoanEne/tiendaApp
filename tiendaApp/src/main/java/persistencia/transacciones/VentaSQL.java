package persistencia.transacciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import modelo.Venta;
import persistencia.conexion.Conexion;

public class VentaSQL {
	
	public static boolean insert(int codVendedor, String nombreProducto, double precioProducto) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		boolean successfulEntry = false;
		Venta venta = new Venta();
		venta.setCodVendedor(codVendedor);
		venta.setNombreProducto(nombreProducto);
		venta.setPrecioProducto(precioProducto);
		try {
			entity.getTransaction().begin();
			entity.persist(venta);
			entity.getTransaction().commit();
			successfulEntry = true;
			entity.close();						///ver si falla
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
	
	
	@SuppressWarnings("unchecked")
	public static List<Venta> searchVentas(String tipoDeBusqueda, Object dato) {
		EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();
		List<Venta> ventas = new ArrayList<Venta>();
		String datoAbuscar = dato.toString();
		if( tipoDeBusqueda.trim().equals("ID".trim())) {
			Venta ventaEncontrada = entity.find(Venta.class,Integer.parseInt(datoAbuscar));
			if( ventaEncontrada != null) {
				ventas.add(ventaEncontrada);
			}
		}
		
		if( tipoDeBusqueda.trim().equals("codVendedor".trim())) {
			Query query = entity.createQuery("from Venta venta where venta.codVendedor='"+Integer.parseInt(datoAbuscar)+"' ");
			ventas = query.getResultList();
		}

		entity.close();
		return ventas;
	}
	
}
