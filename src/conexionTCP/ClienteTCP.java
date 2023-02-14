package conexionTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP {


    public static void main(String[] args) {
        try {
            //1.- Creacion del socket de tipo cliente
            System.out.println("(conexionUDP.Cliente): Creacion del socket...");
            InetAddress direccion= InetAddress.getLocalHost();
            Socket socketCliente = new Socket(direccion, 2500);

            //2.- Abrir flujos de lectura y escritura
            System.out.println("(conexionUDP.Cliente): Apertura de flujos de entrada y salida...");
            OutputStream os = socketCliente.getOutputStream();
            InputStream is = socketCliente.getInputStream();

            //3.- Intercambio de datos con el servidor
            System.out.println("(conexionUDP.Cliente): Envia mensajae al servidor con 14...");
            os.write(14);

            System.out.println("(conexionUDP.Cliente): Lectura del mensaje del servidor...");
            System.out.println("Mensaje recibbido por servidor: " + is.read());

            //4.- Cerrar los flujos de lectura y escritura
            System.out.println("(conexionUDP.Cliente): Cerramos el flujo de lectura y escritura...");
            is.close();
            os.close();

            //5.- Cerrar la conexion
            socketCliente.close();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
