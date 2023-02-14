package ejerciciosUDP.ejercicio1;

import java.io.*;
import java.net.*;

public class ServidorUDP1 {
    /**
     * Crea una aplicación cliente/servidor que realice lo siguiente:
     * El cliente debe solicitar al usuario su nombre y envía este nombre al servidor. El servidor debe responder
     * con un mensaje que diga “Hola nombre”. Sustituyendo nombre por el nombre enviado por el cliente.
     * El cliente imprime por consola el mensaje del servidor.
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
                String mensaje = "Hola " + new String(packet.getData()).trim() + " desde el servidor.";

                byte[] mensajeAEnviar = mensaje.getBytes();

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


