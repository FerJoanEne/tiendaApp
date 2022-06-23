package presentacion;

import java.util.Scanner;

public class CLI {
	
	public static void mostrarMenuPrincipal() {
		int input = 0;
		Scanner sc = new Scanner(System.in);
		while(input != 5) {
			System.out.println("1. PRODUCTOS");
			System.out.println("2. VENDEDORES");
			System.out.println("3. VENTAS");
			System.out.println("4. FINALIZAR");
			
		}
		

		
		
	}
	
	public static void limpiarConsola() {
		try{
            String operatingSystem = System.getProperty("os.name");
              
            if(operatingSystem.contains("Windows")){        
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.start();

                startProcess.waitFor();
            } 
        }catch(Exception e){
            System.out.println(e);
        }
    
	} 
}