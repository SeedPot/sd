import java.net.DatagramSocket;
import java.net.InetAddress;


public class ConexionUDP{

    private DatagramSocket socket;
    private InetAddres host;
    private int puerto;
	

    public ConexionUDP(){
        this.socket = new DatagramSocket();
        this.puerto = 6789;
        this.host = InetAddress.getLocalHost();
    }

    public String login(String rut, String clave){
        String union;
        String respuesta;
        Login login = new Login();
        String clavEn = login.encriptar(clave);

        union = "1:" + rut + ":" + claveEn;

        return respuesta = this.request(union);
    }

    public int serie(String rut, String token,String idSerie, int n){
        String union;
        String respuesta;
        //series 1: fibonacci 2:taylor    

        union = "2:" + "rut:" + "token:" + n;
         
        return respuesta = this.request(union);
    }

    public String  request(String mensaje){
        
        byte[] mensajeBytes = mensaje.getBytes("UTF-8");
		DatagramPacket peticion = new DatagramPacket(mensajeBytes, mensaje.length(), this.host, this.puerto);
		socket.send(peticion);

        byte[] bufer = new byte[1000];
			
		DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
		socket.receive(respuesta);
		socket.close();

        return new String(respuesta.getData()).trim();

    }

}