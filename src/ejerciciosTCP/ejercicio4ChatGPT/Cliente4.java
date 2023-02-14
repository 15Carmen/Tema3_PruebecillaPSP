package ejerciciosTCP.ejercicio4ChatGPT;

import java.io.*;
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

public class Cliente4 {

    public static void main(String[] args) throws IOException {
        // Dirección IP y puerto del servidor
        String host = "localhost";
        int port = 3000;

        // Crear un socket cliente y conectarlo al servidor
        Socket clientSocket = new Socket(host, port);

        // Leer los números del fichero
        BufferedReader fileReader = new BufferedReader(new FileReader("src/ejerciciosTCP/ejercicio4/ficheroFachero.txt"));
        String line;
        while ((line = fileReader.readLine()) != null) {
            // Enviar el número al servidor
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(line);
        }

        // Cerrar el fichero y el socket
        fileReader.close();


        // Leer la suma de los números del servidor
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        int sum = Integer.parseInt(in.readLine());
        System.out.println("La suma de los números es: " + sum);
    }
}


