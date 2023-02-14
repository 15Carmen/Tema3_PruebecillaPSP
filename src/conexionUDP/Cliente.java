package conexionUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Cliente {
    public static void main(String[] args) {

        try {

            //Obtener direccion IP local
            InetAddress direccion = InetAddress.getLocalHost();

            //Creacion del socket
            System.out.println("Creacion del socket");
            DatagramSocket socket = new DatagramSocket(41500);

            //Creacion del mensaje
            String mensaje = "Hola caracola";
            byte[] buffer = mensaje.getBytes();

            System.out.println("Creamos el datagrama");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, 41700);
            socket.send(packet);

        } catch (SocketException e) {
            System.err.println("Error en la creacion del socket");
        } catch (IOException e) {
            System.err.println("Error en la recepcion del paquete");
        }

    }
}