import java.net.*;
import java.io.*;
import java.util.*;

public class ServidorUDP {

    public static void main(String args[]){

        int codigo=0;
        String user = new String();
        String pass = new String();
        String cod_serie = new String();
        String token = new String();
        String n = new String();
        DatagramPacket respuesta = null;
		
        try{
            DatagramSocket unSocket = new DatagramSocket(6789);
            byte[] bufer = new byte[1000];
            
            while(true){
                DatagramPacket peticion = new DatagramPacket(bufer,bufer.length);
                unSocket.receive(peticion);
                
                String mensaje = new String(peticion.getData(), "UTF-8").trim();
                System.out.println("Mensaje del usuario: " + mensaje);

                String[] corte = mensaje.split(":"); 
                int tam = corte.length;
                if(tam == 3 ){
                    //1:login 2:calcuo serie
                codigo = Integer.parseInt(corte[0]);
                //
                 user = corte[1];
                //
                 pass = corte[2]; 

                 respuesta = new DatagramPacket(
                                                    peticion.getData(),
                                                    peticion.getLength(),
                                                    peticion.getAddress(),
                                                    peticion.getPort()
                                                );
                }
                if(tam == 5 ){
                    //1:login 2:calcuo serie
                codigo = Integer.parseInt(corte[0]);
                //
                 user = corte[1];
                //
                token = corte[2]; 
                //
                cod_serie = corte[3];

                n = corte[4];

                 respuesta = new DatagramPacket(
                                                    peticion.getData(),
                                                    peticion.getLength(),
                                                    peticion.getAddress(),
                                                    peticion.getPort()
                                                );
                }
                

                switch(codigo){
                    case 1: //[CODIGO:USER:PASS]
                        Login login = new Login();
                        if( login.validarCredenciales(user, pass) ){    //Devolver token
                            Token token2 = new Token();
                            String tokenGenerado = token2.generarToken(user);
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
                        Serie serie = new Serie();
                        String resultado = new String();
                        int numero = Integer.parseInt(n);
                        String union = new String();
                        //Fibonacci

                        if(Integer.parseInt(cod_serie) == 1){
                            //si el token existe o algo asi 
                            Token token2 = new Token();
                            if(token2.validarToken(user, token)){
                                //Si el token existe
                                resultado = serie.fibonacci(numero);
                                union = token2 + ":" + resultado;
                                byte[] b = union.getBytes();
                                respuesta.setData(b);                                
                            }
                        }
                        //Taylor
                        if( Integer.parseInt(cod_serie) == 2){

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
