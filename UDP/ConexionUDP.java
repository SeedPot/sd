import java.net.*;
import java.io.*;
import java.util.*;


public class ConexionUDP{

    private static final int puerto = 6789;
    private String token;
	

    public ConexionUDP(){
    }

    public boolean login(String rut, String pass){
        String union;
        String respuesta;
        Login login = new Login();
        String passEncriptado = login.encriptar(pass);

        union = "1:" + rut + ":" + passEncriptado;
        respuesta = this.request(union);

        String[] corte = respuesta.split(":");  //[TOKEN:success] ó [mensaje:error]
        if(corte[1].equals("success")){
            setToken(corte[0]);
            return true;
        }

        return false;
    }

    public String serie(String rut, String token, int idSerie, int n){
        String union;
        String respuesta = new String();

        //idSerie = 1: fibonacci 2:taylor    
        switch (idSerie) {
            case 1:
                //calcular fibonacci
                union = "2:" + rut + ":" + token + ":" + idSerie + ":" + n;
                respuesta = this.request(union);
                break;
            
            case 2:
                //calcular fibonacci
                union = "2:" + rut + ":" + token + ":" + idSerie + ":" + n;
                respuesta = this.request(union);
                break;
        
            default:
                break;
        }         
        return respuesta;
    }

    public String request(String mensaje){
        try{
            InetAddress host = InetAddress.getLocalHost();
            DatagramSocket socket = new DatagramSocket();
            
            byte[] mensajeBytes = mensaje.getBytes("UTF-8");
    		DatagramPacket peticion = new DatagramPacket(mensajeBytes, mensaje.length(), host, this.puerto);
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

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}