import java.net.*;
import java.io.*;
import java.util.*;

public class ServidorUDP{
	
    public static void main(String args[]){
		
        try{
            DatagramSocket unSocket = new DatagramSocket(6789);
            byte[] bufer = new byte[1000];
            
            while(true){
                DatagramPacket peticion = new DatagramPacket(bufer,bufer.length);
                unSocket.receive(peticion);
                
                String mensaje = new String(peticion.getData(), "UTF-8").trim();
                System.out.println("Mensaje del usuario: " + mensaje);

                String[] corte = mensaje.split(":"); 
                int codigo = Integer.parseInt(corte[0]);
                String user = corte[1];
                String pass = corte[2]; 

                DatagramPacket respuesta = new DatagramPacket(
                                                    peticion.getData(),
                                                    peticion.getLength(),
                                                    peticion.getAddress(),
                                                    peticion.getPort()
                                                );

                switch(codigo){
                    case 1: //[CODIGO:USER:PASS]
                        Login login = new Login();
                        if( login.validarCredenciales(user, pass) ){    //Devolver token
                            Token token = new Token();
                            String tokenGenerado = token.generarToken(user);
                            tokenGenerado = tokenGenerado + ":success"; //[TOKEN:success]
                            byte[] b = tokenGenerado.getBytes("UTF-8");
                            respuesta.setData(b);
                        }else{
                            String error = "Error al validar credenciales:error";
                            byte[] b = error.getBytes("UTF-8");
                            respuesta.setData(b);
                        }

                        break;
                    case 2: //[CODIGO:USER:TOKEN:COD_SERIE:N]

                        break;
                }                
                
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
