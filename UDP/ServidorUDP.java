import java.net.*;
import java.io.*;
import java.util.*;

public class Servidor{
	
    public static void main(String args[]){
		
        try{
            DatagramSocket unSocket = new DatagramSocket(6789);
            byte[] bufer = new byte[1000];
            
            while(true){
                DatagramPacket peticion = new DatagramPacket(bufer,bufer.length);
                unSocket.receive(peticion);
                
                String mensaje = new String(peticion.getData(), "UTF-8").trim();
                System.out.println(mensaje + ": " + mensaje.length());

                //Preguntar si viene el Token en la petición

                // Si no viene Token, es porque viene el Login [RUT:PASS]
                
                //Formar paquete de respuesta
                DatagramPacket respuesta = new DatagramPacket(
													peticion.getData(),
													peticion.getLength(),
													peticion.getAddress(),
													peticion.getPort()
												);
												
				// if(palabra_respuesta != null){
				// 	byte[] b = palabra_respuesta.getSignificado().getBytes("UTF-8");
				// 	respuesta.setData(b);
				// }else{
				// 	String no_encontrado = "No se ha encontrado la palabra.";
				// 	byte[] b = no_encontrado.getBytes("UTF-8");
				// 	respuesta.setData(b);
				// }
                
                
                unSocket.send(respuesta);
                bufer = new byte[1000];
            }
            
        }
        catch(SocketException e){
			System.out.println("Socket: " + e.getMessage());
        }
        catch(IOException e){
			System.out.println("IO: " + e.getMessage());
		}
    }
    
    
}
