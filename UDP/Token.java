import java.net.*;
import java.io.*;
import java.util.*;

public class Token{

	private String abc = "abcdefghijklmnopqrstuvwxyz";

	public Token(){

	}

	public String generarToken(){
		//Crea un Token random de 20 caracteres
		char[] abcchar = abc.toCharArray();
		StringBuilder cadena = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 20; i++) {
			char c = abcchar[random.nextInt(abcchar.length)];
			cadena.append(c);
		}

		String token = cadena.toString();

		return token;
	}

	public boolean validarToken(String user, String token){
		//Llama a extraerToken(user) para extraer el token del usuario desde el txt
		String corte[] = leerArchivo(user);
		if(corte == null)
			return false;
		
		String tokenGuardado = corte[3];
		int tokenExpiracion = Integer.parseInt(corte[4]);

		//Llamar a existe(token) para compararlo con el token enviado
		if(!existe(tokenGuardado, token))
			return false;

		//Si el token del usuario existe, llama a expirado(token) para ver si el token esta vigente
		if(expirado(tokenExpiracion, token))
			return false;

		return true;
	}

	public boolean existe(String tokenGuardado, String token){
		return tokenGuardado.equals(token);
	}

	public boolean expirado(int expiracion, String token){
		Calendar calendario = Calendar.getInstance();
		long milisegundos = calendario.getTimeInMillis();

		int segundosActuales = (int) (milisegundos / 1000) % 60 ;
		int segundosToken = (int) (expiracion / 1000) % 60 ;

		int segundosDiferencia = segundosActuales - segundosToken;

		return (segundosDiferencia >= 60) ? true : false;
	}

	public String[] leerArchivo(String user){		//[USER:PASS:TOKEN:EXP]
		File file = new File("usuarios.txt");
		try{
			Scanner s = new Scanner(file);
			while(s.hasNextLine()){
				String linea = s.nextLine();
				String[] corte = linea.split(":");

				if(corte[0].equals(user))
					return corte;
				else 
					return null;

			}
		}catch(FileNotFoundException e){
			System.out.println("Error. No se ha encontrado el archivo usuarios.txt\n");
		}

		return null;
	}

}