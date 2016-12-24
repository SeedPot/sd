import java.net.*;
import java.io.*;
import java.util.*;

public class ServidorUDP {

    public static void main(String args[]){
		
        try{
            DatagramSocket unSocket = new DatagramSocket(6789);
            byte[] bufer = new byte[1000];
            
            while(true){
                DatagramPacket peticion = new DatagramPacket(bufer,bufer.length);
                unSocket.receive(peticion);

                DatagramPacket respuesta = new DatagramPacket(
                                                    peticion.getData(),
                                                    peticion.getLength(),
                                                    peticion.getAddress(),
                                                    peticion.getPort()
                                                );
                
                String mensaje = new String(peticion.getData(), "UTF-8").trim();
                System.out.println("Mensaje del usuario: " + mensaje);

                String[] corte = mensaje.split(":");
                int codigo = Integer.parseInt(corte[0]);    //codigo -> 1:LOGIN, 2:SERIES

                switch(codigo){
                    case 1:     //[LOGIN:USER:PASS]
                        Login login = new Login();
                        if( login.validarCredenciales(corte[1], corte[2]) ){    //Usuario válido, devolver token
                            Token token = new Token();
                            String tokenGenerado = token.generarToken(corte[1]);
                            tokenGenerado = tokenGenerado + ":SUCCESS"; //[TOKEN:SUCCESS]
                            byte[] b = tokenGenerado.getBytes("UTF-8");
                            respuesta.setData(b);
                        }else{
                            String error = "Error al validar credenciales:ERROR";   //[ERR_MESSAGE:ERROR]
                            byte[] b = error.getBytes("UTF-8");
                            respuesta.setData(b);
                        }

                        break;
                    case 2:     //[SERIES:USER:TOKEN:COD_SERIE:N]
                        //Validar si el Token está vigente -> corte[2]
                        Token token = new Token();
                        if( token.validarToken(corte[1], corte[2]) ){
                            int cod_serie = Integer.parseInt(corte[3]);
                            int terminos = Integer.parseInt(corte[4]);

                            //Calcular serie
                            Serie serie = new Serie();
                            String success = "";   //[RESULTADO:SUCCESS]

                            switch(cod_serie){
                                case 1: //Fibonacci
                                    success = serie.fibonacci(terminos) + ":SUCCESS";
                                    break;
                                case 2: //Taylor
                                    break;
                            }

                            byte[] b = success.getBytes("UTF-8");                            
                            respuesta.setData(b);
                        }else{
                            String error = "Token Expirado:ERROR";   //[ERR_MESSAGE:ERROR]
                            byte[] b = error.getBytes("UTF-8");
                            respuesta.setData(b);
                        }                        
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
