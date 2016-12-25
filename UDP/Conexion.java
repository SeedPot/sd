public abstract class Conexion{

	protected static final int puertoUDP = 6789;
	protected static final int puertoTCP = 7896;
    protected String token;
    protected boolean expirado;

    public abstract String request(String mensaje);

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

        union = "2:" + rut + ":" + token + ":" + serie; //[SERIES:USER:TOKEN:COD_SERIE:N]
        respuesta = this.request(union); 
        String[] corte = respuesta.split(":");  //[Resultado:SUCCESS] ó [Mensaje:ERROR]

        if(corte[1].equals("ERROR")){
            this.expirado = true;
        }  

        return corte[0];
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