import java.io.*;

public class PeticionSerializada implements Serializable{

	private int tipoPeticion;
	private String rut;
	private String pass;
	private String token;
	private int codigoSerie;
	private int n;
	private int x;
	private String respuesta;

	public PeticionSerializada(int tipoPeticion){
		this.tipoPeticion = tipoPeticion;
	}

	//SETTER
	public void setRut(String rut){
		this.rut = rut;
	}

	public void setPass(String pass){
		this.pass = pass;
	}

	public void setToken(String token){
		this.token = token;
	}

	public void setCodigoSerie(int codigoSerie){
		this.codigoSerie = codigoSerie;
	}

	public void setN(int n){
		this.n = n;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setRespuesta(String respuesta){
		this.respuesta = respuesta;
	}

	//GETTER
	public int getTipoPeticion(){
		return this.tipoPeticion;
	}

	public String getRut(){
		return this.rut;
	}

	public String getPass(){
		return this.pass;
	}

	public String getToken(){
		return this.token;
	}

	public int getCodigoSerie(){
		return this.codigoSerie;
	}

	public int getN(){
		return this.n;
	}

	public int getX(){
		return this.x;
	}

	public String getRespuesta(){
		return this.respuesta;
	}

}