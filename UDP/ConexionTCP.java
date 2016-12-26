import java.net.*;
import java.io.*;
import java.util.*;


public class ConexionTCP extends Conexion{	

    public ConexionTCP(){
    	this.serial = false;
    }

    @Override
    public String request(String mensaje){
    	try{
			Socket s = new Socket("localhost", puertoTCP);
			DataInputStream entrada = new DataInputStream(s.getInputStream());
			DataOutputStream salida = new DataOutputStream(s.getOutputStream());
			
			salida.writeUTF(mensaje);
			String respuesta = entrada.readUTF();
			
			//System.out.println(respuesta);//Respuesta
			s.close();

			return respuesta.trim();
			
		}catch(UnknownHostException e){
			System.out.println("Socket: " + e.getMessage());
		}catch(EOFException e){
			System.out.println("EOF: " + e.getMessage());
		}catch(IOException e){
			System.out.println("IO: " + e.getMessage());
		}

        return null;
    }

}