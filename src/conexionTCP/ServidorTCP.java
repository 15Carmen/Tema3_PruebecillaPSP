package conexionTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        try {

            //1.- Creacion del Socket Servidor
            System.out.println("(Servidor): Abriendo conexion... ");
            ServerSocket socketServidor = new ServerSocket(50000);

            //2.- Espera y acepta conexiones
            System.out.println("(Servidor): Aceptado conexion...");
            Socket socketCliente = socketServidor.accept();

            //3.- Flujos de entrada y salida
            System.out.println("(Servidor): Abriendo flujos de entrada y salida...");
            InputStream is = socketCliente.getInputStream();
            OutputStream os = socketCliente.getOutputStream();

            //4.- Intercambiar datos con el cliente
            System.out.println("(Servidor): Leo mensaje del cliente...");
            System.out.println("Mensaje del cliente: " + is.read());

            System.out.println("(Servidor): Envio mensaje al cliente con 7...");
            os.write(7);

            //5.- Cerrar flujos de lectura y escritura
            is.close();
            os.close();

            //6.- Cerrar la conexion
            socketServidor.close();
            socketCliente.close();


        }catch (IOException e){
            System.err.println("Error: ");
        }
    }
}
