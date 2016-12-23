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

    public bool login(String rut, String clave){
        Login login = new Login();
        String clavEn = login.encriptar(clave);
        union = rut + ":" + pass;
        return true;
    }

}