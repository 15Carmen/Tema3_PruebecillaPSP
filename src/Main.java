import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        try {
            InetAddress direccion = InetAddress.getByName("google.es");
            System.out.println("Direcccion IP: " + direccion.getHostAddress());

            byte[] direccionIP = direccion.getAddress();
            System.out.println(Arrays.toString(direccionIP));

            InetAddress local = InetAddress.getLocalHost();
            System.out.println("Direcccion IP Local: " + local.getHostAddress());
        } catch (UnknownHostException e) {
            System.err.println("Error: No se encuentra la direccion de host");
            e.printStackTrace();
        }
    }
}