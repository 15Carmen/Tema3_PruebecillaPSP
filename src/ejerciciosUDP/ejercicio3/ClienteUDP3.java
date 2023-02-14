package ejerciciosUDP.ejercicio3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteUDP3 {
    /**
     * Crea una aplicación cliente/servidor en la que se realicen las siguientes acciones:
     * El cliente le pide una cadena al usuario por la entrada estándar y se la envía al servidor. Por ejemplo: ABC.
     * El servidor recibe la cadena y devuelve al cliente la suma de los valores ASCII de los caracteres. En
     * nuestro ejemplo sería: 65+66+67=198.
     * El cliente debe imprimir por consola el resultado de la suma.
     */

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            InetAddress direccion = InetAddress.getLocalHost();
            System.out.println("Creacion del socket del cliente");
            DatagramSocket socket = new DatagramSocket();

            System.out.println("Introduce una cadena : ");
            byte[] buffer = sc.next().getBytes();

            System.out.println("Creacion del datagrama del cliente");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length,direccion,41500);

            socket.send(packet);

            byte[] mensajeRecibido = new byte[64];

            DatagramPacket packetRecibido = new DatagramPacket(mensajeRecibido, mensajeRecibido.length);

            socket.receive(packetRecibido);

            System.out.println("El valor de la cadena introducida es: " + new String(packetRecibido.getData()).trim());

            socket.close();
        } catch (SocketException e) {
            System.out.println("Error en la creacion del socket");
            e.printStackTrace();
        } catch (UnknownHostException e) {
            System.out.println("Error al obtener la ip local");
        } catch (IOException e) {
            System.out.println("Error en el envio del paquete");
            e.printStackTrace();
        }
    }
}


