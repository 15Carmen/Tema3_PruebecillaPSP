package ejerciciosTCP.ejercicio2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Crea una aplicación cliente/servidor que se comunique por el puerto 1500 y realice lo siguiente:
 *
 * El cliente debe solicitar al usuario un número entero positivo. Hay que comprobar que el número introducido
 * por el usuario es positivo. Si el usuario introduce un número negativo, el cliente debe seguir pidiéndole
 * números hasta que introduzca un número positivo.
 *
 * Una vez el cliente tenga un número entero positivo se lo envía al servidor. El servidor debe enviar como
 * mensaje el factorial del número indicado por el cliente.
 */

public class Servidor2 {
    public static void main(String[] args) {

        int numero;
        int numFactorial;

        try {

            // 1 - Crear socket de tipo servidor y le indicamos el puerto
            ServerSocket servidor = new ServerSocket(1500);

            // 2 - Queda a la espera de peticiones y las acepta cuando las recibe
            System.out.println("conexionUDP.Servidor se encuentra a la escucha...");
            Socket peticion = servidor.accept();

            // 3 - Abrir flujos de lectura y escritura de datos
            InputStream is = peticion.getInputStream();
            OutputStream os = peticion.getOutputStream();

            // 4 - Intercambiar datos con el cliente
            // Leer mensaje enviado por el cliente e imprimirlo por consola
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            numero = br.read();
            numFactorial = factorial(numero);

            // Enviarle mensaje al cliente
            System.out.println("conexionUDP.Servidor envía al cliente un mensaje");
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(numFactorial);
            bw.newLine();
            bw.flush();

            // 5 - Cerrar flujos de lectura y escritura
            br.close();
            isr.close();
            is.close();
            bw.close();
            osw.close();
            os.close();

            // 6 - Cerra la conexión
            System.out.println("Cierre de conexión del servidor");
            peticion.close();
            servidor.close();

        } catch (IOException e) {
            System.err.println("Ha habido algún error en la creación del Socket conexionUDP.Servidor");
            e.printStackTrace();
        }
    }

    public static int factorial(int numero) {
        return numero * factorial(numero - 1);
    }
}
