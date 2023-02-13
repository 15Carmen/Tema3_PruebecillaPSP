package ejerciciosTCP.ejercicio1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Crea una aplicación cliente/servidor que se comunique por el puerto 2500 y realice lo siguiente:
 *
 * El cliente debe solicitar al usuario un número entero positivo. Hay que comprobar que el número introducido
 * por el usuario es positivo. Si el usuario introduce un número negativo, el cliente debe seguir pidiéndole
 * números hasta que introduzca un número positivo.
 *
 * Una vez el cliente tenga un número entero positivo se lo envía al servidor. El servidor debe enviar un
 * mensaje indicando si el número es primo o no.
 */

public class Servidor {
    public static void main(String[] args) {

        int numero;
        String mensaje;

        try {
            // 1 - Crear socket de tipo servidor y le indicamos el puerto
            ServerSocket servidor = new ServerSocket(2500);

            // 2 - Queda a la espera de peticiones y las acepta cuando las recibe
            System.out.println("Servidor se encuentra a la escucha...");
            Socket peticion = servidor.accept();

            // 3 - Abrir flujos de lectura y escritura de datos
            InputStream is = peticion.getInputStream();
            OutputStream os = peticion.getOutputStream();

            // 4 - Intercambiar datos con el cliente
            // Leer mensaje enviado por el cliente e imprimirlo por consola
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            numero = br.read();
            mensaje = esPrimo(numero);


            // Enviarle mensaje al cliente
            System.out.println("Servidor envía al cliente un mensaje");
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(mensaje);
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
            System.err.println("Ha habido algún error en la creación del Socket Servidor");
            e.printStackTrace();
        }
    }

    public static String esPrimo(int n){
        String resultado = "Es primo";
        boolean enc = false;

        if (n <= 1){
            resultado = "No es primo";
        }
        for (int i = 2; i < n/2 && !enc; i++) {
            if (n%i==0){
                resultado = "No es primo";
                enc = true;
            }
        }

        return resultado;
    }
}
