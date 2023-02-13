package ejerciciosUDP.ejercicio1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteUDP1 {
    /**
     * Crea una aplicación cliente/servidor que realice lo siguiente:
     * El cliente debe solicitar al usuario su nombre y envía este nombre al servidor. El servidor debe responder
     * con un mensaje que diga “Hola nombre”. Sustituyendo nombre por el nombre enviado por el cliente.
     * El cliente imprime por consola el mensaje del servidor.
     */


    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            InetAddress direccion = InetAddress.getLocalHost();
            System.out.println("Creacion del socket del cliente");
            DatagramSocket socket = new DatagramSocket();

            System.out.println("Introduce tu nombre : ");
            byte[] buffer = sc.next().getBytes();

            System.out.println("Creacion del datagrama del cliente");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length,direccion,41500);

            socket.send(packet);

            byte[] mensajeRecibido = new byte[64];

            DatagramPacket packetRecibido = new DatagramPacket(mensajeRecibido, mensajeRecibido.length);

            socket.receive(packetRecibido);

            System.out.println(new String(packetRecibido.getData()).trim());

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


