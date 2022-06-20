package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;

public class Conexion 
{
	public static Conexion instancia;
	private Connection connection;
	
	private Conexion()
	{
		try
		{
			this.connection = DriverManager.getConnection("jdbc:mysql://ufwrf3seko1d8hq2:7laQtPxhVAFTMPa94SXb@bdokimpevmxyofkbslzf-mysql.services.clever-cloud.com:3306/bdokimpevmxyofkbslzf","ufwrf3seko1d8hq2","7laQtPxhVAFTMPa94SXb");
			this.connection.setAutoCommit(false);
			System.out.println("conexion exitosa");
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("Conexion fallida");
		}
	}
	
	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
			System.out.println("Conexion cerrada");
		}
		catch (SQLException e) 
		{
			System.out.println(e);
			System.out.println("Error al cerrar la conexion");
		}
		instancia = null;
	}

}
