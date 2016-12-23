import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente {

	public static void main(String args[]) {
		String token = "";
		int calculo = 0;
		//Menu
		Scanner sc = new Scanner(System.in);
		String rut, pass, union;
		int tipoCon;

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
				if(c.login(rut, pass)){
					System.out.println("\n\tToken: " + c.getToken() + "\n\n");

					System.out.println("\tSeleccione la serie que desea calcular: ");
					System.out.println("\t[1]: Fibonacci");
					System.out.print("\t[2]: Taylor ");

					Scanner sc2 = new Scanner(System.in);
					serie = sc2.nextInt();

					System.out.println("\n\tSeleccione hasta donde desea calcular n");
					n = sc2.nextInt();

					switch (serie) {
						case 1:
							//Fibonacci
							calculo = c.serie(rut, c.getToken(), serie, n);
							break;
						case 2:
							//Taylor	
							calculo = c.serie(rut, c.getToken(), serie, n);
							break;
						default:
							System.out.println("\n\tSelecciona una opcion válida");
							break;

					}
					System.out.println("\n\tEl resultado es: " + calculo);
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
	}
}
