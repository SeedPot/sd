import java.net.*;
import java.io.*;
import java.util.*;

public class ClienteUDP{
	
	public static void main(String args[]){
		try{
			DatagramSocket socket = new DatagramSocket();
			InetAddress host = InetAddress.getLocalHost();
			int puerto = 6789;

			//Menu
			Scanner sc = new Scanner(System.in);
			String rut, pass, union;
			System.out.println("\n\n\tServicios Calculo de Series\n");
			System.out.println("\tInicio de sesion");

			System.out.print("\n\tRUT: ");			
			rut = sc.nextLine();

			System.out.print("\tPASSWORD: ");
			pass = sc.nextLine();

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
