package ejerciciosTCP.ejercicio4ChatGPT;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Crea una aplicación cliente/servidor que se comunique por el puerto 3000 y realice lo siguiente:
 * <p>
 * El cliente debe leer un fichero que se encuentra en su máquina previamente creado. Este fichero debe contener
 * una serie de números enteros, cada uno en una línea. El cliente debe ir leyendo el fichero línea a línea e
 * ir escribiendo los números de uno en uno en el socket.
 * <p>
 * El servidor debe ir leyendo número a número del socket para enviar posteriormente al cliente la suma de todos
 * los números.
 */

public class Servidor4 {


    public static void main(String[] args) throws IOException {
        // Puerto del servidor
        int port = 3000;

        // Crear un socket servidor
        ServerSocket serverSocket = new ServerSocket(port);

        // Aceptar una conexión de un cliente
        Socket clientSocket = serverSocket.accept();

        // Crear un flujo de entrada desde el socket
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Leer los números enviados por el cliente
        int sum = 0;
        String line;
        while ((line = in.readLine()) != null) {
            sum += Integer.parseInt(line);
        }

        // Enviar la suma de los números al cliente
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println(sum);

        // Cerrar el socket y los flujos
        clientSocket.close();
        serverSocket.close();
    }
}


