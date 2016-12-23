public class Login{

	//Cifrado Cesar
	private String abc = "abcdefghijklmnopqrstuvwxyz1234567890";
	private int desplazamiento = 7;

	public Login(){
		
	}

	public boolean validarCredenciales(String user, String pass){
		Token token = new Token();
		String[] corte = token.leerArchivo(user);

		if(corte == null)
			return false;

		System.out.println("Validacion: user->" + corte[0] + ", pass->" + corte[1]);

		return corte[1].equals(pass);
	}

	public String encriptar(String pass){
		String cadena = pass;
		String cifrado = "";

		for(int i = 0; i < cadena.length(); i++){
			for(int j = 0; j < abc.length(); j++){

				if( cadena.charAt(i) == abc.charAt(j) ){	

					if( j + desplazamiento >= abc.length() ){	//El desplazamiento sobrepasó a la letra z
						cifrado += abc.charAt( (j + desplazamiento) % abc.length() );
					}else{										//Desplazamiento normal
						cifrado += abc.charAt( j + desplazamiento );
					}

				}

			}
		}

		return cifrado;
	}

}