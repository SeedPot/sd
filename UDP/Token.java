import java.net.*;
import java.io.*;
import java.util.*;

public class Token{

	private String abc = "abcdefghijklmnopqrstuvwxyz";

	public Token(){

	}

	public String generarToken(String user){
		//Crea un Token random de 20 caracteres
		char[] abcchar = abc.toCharArray();
		StringBuilder cadena = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 20; i++) {
			char c = abcchar[random.nextInt(abcchar.length)];
			cadena.append(c);
		}

		String token = cadena.toString();

		guardarToken(user, token);

		return token;
	}

	public void guardarToken(String user, String token){
		//Extraer a todos los usuarios y guardarlos en una lista y a la vez encontrar al usuario
		ArrayList<String> listado = new ArrayList<String>();
		String filename = "usuarios.txt";
		File file = new File(filename);
		try{
			Scanner s = new Scanner(file);

			while(s.hasNextLine()){
				String linea = s.nextLine();
				String[] corte = linea.split(":");

				if( corte[0].equals(user) ){
					Calendar calendario = Calendar.getInstance();
					long milisegundos = calendario.getTimeInMillis();
					//Actualiza el token y su expiracion
					linea = corte[0] + ":" + corte[1] + ":" + token + ":" + milisegundos;	
				}			

				listado.add(linea);
			}

			Iterator<String> iterador = listado.iterator();
			file.delete();
			File fold = new File(filename);
			FileWriter fw = new FileWriter(fold, false);

			String item = "";
			while(iterador.hasNext()){
				item = iterador.next();
				fw.write(item + "\n");
			}

			fw.close();

		}catch(FileNotFoundException e){
			System.out.println("Error. No se ha encontrado el archivo usuarios.txt\n");
		}catch(IOException e){
			System.err.println("IOException: " + e.getMessage());
		}

	}

	public boolean validarToken(String user, String token){
		//Llama a extraerToken(user) para extraer el token del usuario desde el txt
		String corte[] = leerArchivo(user);
		if(corte == null)
			return false;

		String tokenGuardado = corte[2];
		long tokenExpiracion = Long.valueOf(corte[3]).longValue();;

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

	public boolean expirado(long expiracion, String token){
		Calendar calendario = Calendar.getInstance();
		long milisegundos = calendario.getTimeInMillis();

		System.out.println("\nActual: " + milisegundos);
		System.out.println("Token: " + expiracion + "\n");

		long segundosDiferencia = (milisegundos - expiracion) /1000;

		System.out.println("Token segundos: " + segundosDiferencia);

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