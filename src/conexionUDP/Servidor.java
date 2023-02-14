package conexionUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args) {


        try {
            //Obtener direccion IP local
            InetAddress direccion = InetAddress.getLocalHost();

            //Creacion del socket
            System.out.println("Creacion del socket");
            DatagramSocket socket = new DatagramSocket(41700);
            byte[] buffer = new byte[64];

            System.out.println("Creamos el datagrama");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("A la espera de recibir datagrama");
            socket.receive(packet);

            System.out.println("Leemos el mensaje");
            String mensaje = new String(packet.getData());

            System.out.println("Mensaje que ha enviado el cliente: " + mensaje.trim());

        } catch (
        SocketException e) {
            System.err.println("Error en la creacion del socket");
        } catch (
        IOException e) {
            System.err.println("Error en la recepcion del paquete");
        }
    }
}
