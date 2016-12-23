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

	public boolean validarToken(){


		return false;
	}

}