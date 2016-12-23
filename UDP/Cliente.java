import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente{
	
	public static void main(String args[]){

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
				ConexionUDP c = new ConexionUDP();
				if(c.login(rut, clave)){

					String serie;
					System.out.println("Conexion realizada con éxito");
					System.out.println("Seleccione la serie que dessea calcular: ");

					Scanner sc  = new Scanner();
					 serie = sc.nextLine();

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
		

		try{
			DatagramSocket socket = new DatagramSocket();
			InetAddress host = InetAddress.getLocalHost();
			int puerto = 6789;

			

			union = rut + ":" + pass;
			byte[] credenciales = union.getBytes("UTF-8");

			DatagramPacket peticion = new DatagramPacket(	credenciales, 
															union.length(), 
															host, 
															puerto
															);
			socket.send(peticion);





														//mensaje, largo, host, puerto
			//DatagramPacket peticion = new DatagramPacket(m, args[0].length(), host, puerto);
			//socket.send(peticion);



			byte[] bufer = new byte[1000];
			
			DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
			socket.receive(respuesta);
			System.out.println("\nDefinicion: \n" + new String(respuesta.getData()).trim() + "\n");
			
			socket.close();
		}
		catch(SocketException e){
			System.out.println("Socket: " + e.getMessage());
		}
		catch(IOException e){
			System.out.println("IO: " + e.getMessage());
		}
	}
	
}
