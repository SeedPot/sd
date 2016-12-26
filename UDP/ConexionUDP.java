import java.net.*;
import java.io.*;
import java.util.*;


public class ConexionUDP extends Conexion{

    public ConexionUDP(){
        this.serial = false;
    }

    @Override
    public String request(String mensaje){
        try{
            InetAddress host = InetAddress.getLocalHost();
            DatagramSocket socket = new DatagramSocket();
            
            byte[] mensajeBytes = mensaje.getBytes("UTF-8");
    		DatagramPacket peticion = new DatagramPacket(mensajeBytes, mensaje.length(), host, this.puertoUDP);
    		socket.send(peticion);

            byte[] bufer = new byte[1000];
    			
    		DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
    		socket.receive(respuesta);
    		socket.close();

            return new String(respuesta.getData()).trim();
        }
        catch(SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }
        catch(IOException e){
            System.out.println("IO: " + e.getMessage());
        }

        return null;
    }

}