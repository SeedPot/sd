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
			//
			System.out.print("\n\tRUT: ");
			rut = sc.nextLine();
			//password
			System.out.print("\tPASSWORD: ");
			pass = sc.nextLine();
			//solicitar tipo conexion 
			System.out.println("\n\tTIPO DE CONEXION: ");
			System.out.println("\t[1]: UDP ");
			System.out.println("\t[2]: TCP");
			System.out.print("\t[3]: Serializacion ");
			tipoCon = sc.nextInt();

			switch (tipoCon) {
				case 1:
					int serie;
					int n;
					ConexionUDP c = new ConexionUDP();
					if(c.login(rut, pass)){	//Usuario existe
						do{
							System.out.println("\n\n\tSeleccione la serie que desea calcular: ");
							System.out.println("\t[1]: Fibonacci");
							System.out.print("\t[2]: Taylor ");

							Scanner sc2 = new Scanner(System.in);
							serie = sc2.nextInt();

							switch (serie) {
								case 1:
									//Fibonacci	
									System.out.print("\n\tIngrese la cantidad de terminos N: ");
									n = sc2.nextInt();
									String respuesta = c.serie(rut, c.getToken(), serie, n);
									System.out.println("\n\n\tRespuesta: " + respuesta + "\n");	
									break;
								case 2:
									//Taylor	
									System.out.print("\n\tIngrese la cantidad de terminos N: ");
									n = sc2.nextInt();
									//calculo = c.serie(rut, c.getToken(), serie, n);
									break;
								default:
									System.out.println("\n\tSelecciona una opcion valida");
									break;
							}
							
							}while(!c.estaExpirado());
					}else{
						System.out.println("\n\tUsuario o password incorrectos");
					}
					break;
				case 2:
					//TCP
					break;

				case 3:
					//serializacion
					break;

				default:
					System.out.println("1: UDP , 2:TCP , 3:Serialización");
					break;
			}
		}while(true);
	}
}
