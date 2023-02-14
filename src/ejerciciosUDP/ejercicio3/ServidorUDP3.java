package ejerciciosUDP.ejercicio3;

import java.io.*;
import java.net.*;

public class ServidorUDP3 {
    /**
     * Crea una aplicación cliente/servidor en la que se realicen las siguientes acciones:
     * El cliente le pide una cadena al usuario por la entrada estándar y se la envía al servidor. Por ejemplo: ABC.
     * El servidor recibe la cadena y devuelve al cliente la suma de los valores ASCII de los caracteres. En nuestro
     * ejemplo sería: 65+66+67=198.
     * El cliente debe imprimir por consola el resultado de la suma.
     */


    public static void main(String[] args) {

        try {

            System.out.println("Creacion del socket del servidor");
            DatagramSocket socket = new DatagramSocket(41500);

            System.out.println("Creacion del array de bytes");

            while (true) {
                byte[] buffer = new byte[64];

                System.out.println("Creacion del datagrama del servidor");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                System.out.println("A la espera de recibir datagrama");
                socket.receive(packet);

                System.out.println("Leemos el mensaje");
                String mensaje = new String(packet.getData()).trim();

                int sumaAscii = 0;

                for (int i = 0; i < mensaje.length(); i++) {

                    char caracter = mensaje.charAt(i);
                    int valorAscii = (int) caracter;
                    sumaAscii += valorAscii;
                }

                byte[] mensajeAEnviar = String.valueOf(sumaAscii).getBytes();

                DatagramSocket socketEnviar = new DatagramSocket();

                DatagramPacket packetMensaje = new DatagramPacket(mensajeAEnviar, mensajeAEnviar.length,packet.getAddress(), packet.getPort());

                socketEnviar.send(packetMensaje);

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


