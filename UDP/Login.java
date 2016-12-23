public class Login{

	//Cifrado Cesar
	private String abc = "abcdefghijklmnopqrstuvwxyz ";
	private int desplazamiento = 7;

	public Login(){

	}

	public boolean validarCredenciales(String user, String pass){
		//Llamar a desencriptar(pass) pasándole la pass


		return false;
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

	public String desencriptar(String pass){


		return "";
	}

}