import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente {

	public static void main(String args[]) {
		String token;
		Int calculo;
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
		System.out.println("\nTipo de conexion: ");
		System.out.println("1:UDP, 2:TCP, 3:Serialización");
		tipoCon = sc.nextLine();

		switch (tipoCon) {
		case 1:
			int serie;
			int n;
			ConexionUDP c = new ConexionUDP();
			token = c.login(rut, clave);
			System.out.println("Token: " + token + "\n");

			System.out.println("Seleccione la serie que desea calcular: ");
			System.out.println("1: Fibonacci 2:Taylor");

			Scanner sc = new Scanner();
			serie = Int.toInt(sc.nextLine());

			System.out.println("Seleccione hasta donde desea calcular n");
			n = Int.toInteger(sc.nextLine());

			switch (serie) {
			case 1:
				//Fibonacci
				calculo = c.serie(rut, token, serie, n);
				break;
			case 2:
				//Taylor	
				calculo = c.serie(rut, token, serie, n);
				break;
			default:
				System.out.println("Selecciona una opcion válida");
				break;

			}
			System.out.println("El resultado es: " + calculo);
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
