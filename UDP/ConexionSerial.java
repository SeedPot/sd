import java.net.*;
import java.io.*;
import java.util.*;

public class ConexionSerial{

	private static final int puertoTCP = 7896;
    private String token;
    private boolean expirado;

	public ConexionSerial(){

	}

	public boolean login(String rut, String pass){
        String union;
        String respuesta;
        Login login = new Login();
        String passEncriptado = login.encriptar(pass);

        union = "1:" + rut + ":" + passEncriptado;  //[LOGIN:RUT:PASS_ENC]
        respuesta = this.request(union);

        String[] corte = respuesta.split(":");  //[TOKEN:SUCCESS] ó [Mensaje:ERROR]
        if(corte[1].equals("SUCCESS")){
            this.expirado = false;
            setToken(corte[0]);
            return true;
        }

        return false;
    }

    public String serie(String rut, String token, String serie){
        String union;
        String respuesta = new String();

        union = "2:" + rut + ":" + token + ":" + serie; //[SERIES:USER:TOKEN:COD_SERIE(N[+X])]
        respuesta = this.request(union); 
        String[] corte = respuesta.split(":");  //[Resultado:SUCCESS] ó [Mensaje:ERROR]

        if(corte[1].equals("ERROR")){
            this.expirado = true;
        }  

        return corte[0];
    }

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