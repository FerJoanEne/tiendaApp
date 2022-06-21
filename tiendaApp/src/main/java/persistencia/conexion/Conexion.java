package persistencia.conexion;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexion 
{
	private static final String PERSISTENCIA = "PERSISTENCE";
	private static EntityManagerFactory factory;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCIA);
		}
		
		return factory;
	}
	
	public static void shutdown() {
		if(factory != null) {
			factory.close();
		}
	}
}
