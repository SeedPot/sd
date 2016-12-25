import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente {

	public static void main(String args[]) {
		String token = "";
		//Menu
		String rut, pass, union;
		int tipoCon;

		do{
			Scanner sc = new Scanner(System.in);
			System.out.println("\n\n\tServicios Calculo de Series\n");
			System.out.println("\tInicio de sesion");

			System.out.print("\n\tRUT: ");
			rut = sc.nextLine();

			System.out.print("\tPASSWORD: ");
			pass = sc.nextLine();
			
			System.out.println("\n\tTIPO DE CONEXION: ");
			System.out.println("\t[1]: UDP ");
			System.out.println("\t[2]: TCP");
			System.out.print("\t[3]: Serializacion ");
			tipoCon = sc.nextInt();

			int serie;
			int n;
			Conexion conexion = null;

			switch (tipoCon) {
				/*=========================== UDP ===============================*/
				case 1:
					conexion = new ConexionUDP();					
					break;
				/*=========================== TCP ===============================*/
				case 2:
					conexion = new ConexionTCP();					
					break;
				/*=========================== Serializacion ===============================*/
				case 3:
					
					break;
				/*=========================== Opcion invalida ===============================*/
				default:
					System.out.println("\n\tOpcion no valida.\n");
					break;
			}

			if(conexion != null){

				if(conexion.login(rut, pass)){	//Usuario existe
					do{
						System.out.println("\n\n\tSeleccione la serie que desea calcular: ");
						System.out.println("\t[1]: Fibonacci");
						System.out.print("\t[2]: Taylor ");

						Scanner sc2 = new Scanner(System.in);
						serie = sc2.nextInt();

						switch (serie) {

							case 1://Fibonacci										
								System.out.print("\n\tIngrese la cantidad de terminos N: ");
								n = sc2.nextInt();
								String fibonacci = conexion.serie(rut, conexion.getToken(), serie, n);
								System.out.println("\n\n\tRespuesta: " + fibonacci + "\n");	
								break;

							case 2://Taylor										
								System.out.print("\n\tIngrese la cantidad de terminos N: ");
								n = sc2.nextInt();
								String taylor = conexion.serie(rut, conexion.getToken(), serie, n);
								System.out.println("\n\n\tRespuesta: " + taylor + "\n");	
								break;

							default:
								System.out.println("\n\tSelecciona una opcion valida");
								break;
						}
						
						}while(!conexion.estaExpirado());
					}else{
						System.out.println("\n\tUsuario o password incorrectos");
					}
				}

		}while(true);
	}

}
