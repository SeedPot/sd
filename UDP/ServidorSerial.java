import java.net.*;
import java.io.*;
import java.util.*;

public class ServidorSerial {

    public static void main(String args[]){
		
        try{
            DatagramSocket socket = new DatagramSocket(6789);
            byte[] buffer = new byte[1000];
            
            while(true){
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socket.receive(peticion);

                DatagramPacket respuesta = new DatagramPacket(
                                                    peticion.getData(),
                                                    peticion.getLength(),
                                                    peticion.getAddress(),
                                                    peticion.getPort()
                                                );

                ByteArrayInputStream byteArray = new ByteArrayInputStream(peticion.getData());
                ObjectInputStream is = new ObjectInputStream(byteArray);
                PeticionSerializada objeto = (PeticionSerializada)is.readObject();
                is.close();

                int codigo = objeto.getTipoPeticion();

                switch(codigo){
                    
                    case 1:     //[LOGIN:USER:PASS]
                        Login login = new Login();
                        if( login.validarCredenciales(objeto.getRut(), objeto.getPass()) ){    //Usuario válido, devolver token
                            Token token = new Token();
                            String tokenGenerado = token.generarToken(objeto.getRut());
                            tokenGenerado = tokenGenerado + ":SUCCESS"; //[TOKEN:SUCCESS]
                            objeto.setRespuesta(tokenGenerado);
                        }else{
                            String error = "Error al validar credenciales:ERROR";   //[ERR_MESSAGE:ERROR]
                            objeto.setRespuesta(error);
                        }

                        break;

                    case 2:     //[SERIES:USER:TOKEN:COD_SERIE:N]                        
                        Token token = new Token();
                        if( token.validarToken(objeto.getRut(), objeto.getToken()) ){   //Validar si el Token está vigente
                            int cod_serie = objeto.getCodigoSerie();
                            int terminos = objeto.getN();

                            //Calcular serie
                            Serie serie = new Serie();
                            String success = "";   //[RESULTADO:SUCCESS]

                            switch(cod_serie){
                                case 1: //Fibonacci
                                    success = serie.fibonacci(terminos) + ":SUCCESS";
                                    break;
                                case 2: //Taylor
                                    int x = objeto.getX();
                                    success = serie.taylor(terminos, x) + ":SUCCESS";
                                    break;
                            }

                            objeto.setRespuesta(success);
                        }else{
                            String error = "Token Expirado, inicie sesion nuevamente:ERROR";   //[ERR_MESSAGE:ERROR]
                            objeto.setRespuesta(error);
                        }                        
                        break;
                }                
                
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream (bytes);
                os.writeObject(objeto);
                os.close();
                byte[] peticionEnBytes = bytes.toByteArray();
                respuesta.setData(peticionEnBytes);
                
                socket.send(respuesta);
                buffer = new byte[1000];
            }
            
        }
        catch(SocketException e){
			System.out.println("Socket: " + e.getMessage());
        }
        catch(IOException e){
			System.out.println("IO: " + e.getMessage());
		}catch(ClassNotFoundException e){
            System.out.println("ClassNotFound: " + e.getMessage());
        }
    }

}
