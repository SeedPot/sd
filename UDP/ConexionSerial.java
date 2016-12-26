import java.net.*;
import java.io.*;
import java.util.*;

public class ConexionSerial extends Conexion{

	public ConexionSerial(){
		this.serial = true;
	}

	public String request(String mensaje){
    	try{
    		InetAddress host = InetAddress.getLocalHost();
			DatagramSocket socket = new DatagramSocket(6788, host);

			String[] corte = mensaje.split(":");
            int codigo = Integer.parseInt(corte[0]);

			PeticionSerializada peticion = new PeticionSerializada(codigo);
	        peticion.setRut(corte[1]);

	        if(codigo == 1){	//Login
	        	peticion.setPass(corte[2]);	        	
	        }else{				//Series
            	int codigoSerie = Integer.parseInt(corte[3]);
            	int n = Integer.parseInt(corte[4]);
	        	peticion.setToken(corte[2]);
	        	peticion.setCodigoSerie(codigoSerie);
	        	peticion.setN(n);
            	if(codigoSerie == 2){	//Taylor
            		int x = Integer.parseInt(corte[5]);
            		peticion.setX(x);
            	}
	        }

	        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream (bytes);
			os.writeObject(peticion);
			os.close();
			byte[] peticionEnBytes = bytes.toByteArray();

	        DatagramPacket objeto = new DatagramPacket(	peticionEnBytes,
                    									peticionEnBytes.length, 
                    									host, 
                    									6789);
	        socket.send(objeto);

	        byte[] buffer = new byte[1000];

	        DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
    		socket.receive(respuesta);
    		socket.close();

    		ByteArrayInputStream baos = new ByteArrayInputStream(buffer);
	      	ObjectInputStream oos = new ObjectInputStream(baos);
	      	PeticionSerializada c1 = (PeticionSerializada)oos.readObject();
			
			return c1.getRespuesta();
			
		}catch(UnknownHostException e){
			System.out.println("Socket: " + e.getMessage());
		}catch(EOFException e){
			System.out.println("EOF: " + e.getMessage());
		}catch(IOException e){
			System.out.println("IO: " + e.getMessage());
		}catch(ClassNotFoundException e){
			System.out.println("ClassNotFound: " + e.getMessage());
		}

        return null;
    }    

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }

    public boolean estaExpirado(){
        return this.expirado;
    }

}