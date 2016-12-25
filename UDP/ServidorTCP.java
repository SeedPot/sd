import java.net.*;
import java.io.*;

public class ServidorTCP{
	
	public static void main(String args[]){
		
		try{
			int puerto = 7896;
			ServerSocket escuchandoSocket = new ServerSocket(puerto);
			
			while(true){
				Socket socketCliente = escuchandoSocket.accept();
				ConexionSocket c = new ConexionSocket(socketCliente);
			}
			
		}catch(IOException e){
			System.out.println("Escuchando: " + e.getMessage());
		}
		
	}
}

class ConexionSocket extends Thread{
	
	DataInputStream entrada;
	DataOutputStream salida;
	Socket socketCliente;
	
	public ConexionSocket(Socket unSocketCliente){
		try{
			socketCliente = unSocketCliente;
			entrada = new DataInputStream(socketCliente.getInputStream());
			salida = new DataOutputStream(socketCliente.getOutputStream());
			
			this.start();
			
		}catch(IOException e){
			System.out.println("Conexion: " + e.getMessage());
		}
	}
	
	public void run(){
		try{			
			String mensaje = entrada.readUTF();

			String[] corte = mensaje.split(":");
            int codigo = Integer.parseInt(corte[0]);    //codigo -> 1:LOGIN, 2:SERIES

            switch(codigo){
                
                case 1:     //[LOGIN:USER:PASS]
                    Login login = new Login();
                    if( login.validarCredenciales(corte[1], corte[2]) ){    //Usuario válido, devolver token
                        Token token = new Token();
                        String tokenGenerado = token.generarToken(corte[1]);
                        tokenGenerado = tokenGenerado + ":SUCCESS"; //[TOKEN:SUCCESS]
                        salida.writeUTF(tokenGenerado);
                    }else{
                        String error = "Error al validar credenciales:ERROR";   //[ERR_MESSAGE:ERROR]
                        salida.writeUTF(error);
                    }

                    break;

                case 2:     //[SERIES:USER:TOKEN:COD_SERIE:N]                        
                    Token token = new Token();
                    if( token.validarToken(corte[1], corte[2]) ){   //Validar si el Token está vigente
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
                                success = serie.taylor(terminos) + ":SUCCESS";
                                break;
                        }

                        salida.writeUTF(success);
                    }else{
                        String error = "Token Expirado, inicie sesion nuevamente:ERROR";   //[ERR_MESSAGE:ERROR]
                        salida.writeUTF(error);
                    }                        
                    break;
            }           			

						
			socketCliente.close();
			
		}catch(EOFException e){
			System.out.println("EOF: " + e.getMessage());
		}catch(IOException e){
			System.out.println("IO: " + e.getMessage());
		}catch(NumberFormatException e){
			System.out.println("Error: " + e.getMessage());
		}
	}
	
}
	
