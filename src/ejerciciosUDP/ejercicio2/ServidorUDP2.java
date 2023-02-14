package ejerciciosUDP.ejercicio2;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class ServidorUDP2 {
    /**
     * Crea una aplicación cliente/servidor en la que se realicen las siguientes acciones:
     * El cliente lee de consola dos cadenas y se las envía al servidor. Enviar las cadenas por separado, primero una
     * y luego la otra.
     * Una vez el servidor recibe las cadenas, las ordena por orden alfabético y se las envía ordenadas al cliente. El
     * cliente debe imprimir por consola ambas cadenas.
     */

    public static void main(String[] args) {

        try {
            System.out.println("Creacion del socket del servidor");
            DatagramSocket socketRecibir = new DatagramSocket(41500);

            System.out.println("Creacion del array de bytes");

            while (true) {
                byte[] buffer1 = new byte[64];
                byte[] buffer2 = new byte[64];

                System.out.println("Creacion del datagrama del servidor");
                DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length);
                DatagramPacket packet2 = new DatagramPacket(buffer2, buffer2.length);

                System.out.println("A la espera de recibir datagrama");
                socketRecibir.receive(packet1);
                socketRecibir.receive(packet2);


                String[] cadenas = {new String(packet1.getData()).trim(), new String(packet2.getData()).trim()};

                Arrays.sort(cadenas);

                for (int i = 0; i < cadenas.length; i++) {
                    System.out.println(cadenas[i]);
                }
                byte[] cadenaOrdenada1 = cadenas[0].getBytes();
                byte[] cadenaOrdenada2 = cadenas[1].getBytes();

                DatagramSocket socketEnviar = new DatagramSocket();

                DatagramPacket packetOrdenada1 = new DatagramPacket(cadenaOrdenada1, cadenaOrdenada1.length, packet1.getAddress(), packet1.getPort());
                DatagramPacket packetOrdenada2 = new DatagramPacket(cadenaOrdenada2, cadenaOrdenada2.length, packet2.getAddress(), packet2.getPort());

                socketEnviar.send(packetOrdenada1);
                socketEnviar.send(packetOrdenada2);
            }


        } catch (SocketException e) {
            System.out.println("Error en la creacion del socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error en la recepcion del paquete");
            e.printStackTrace();
        }
    }
}
