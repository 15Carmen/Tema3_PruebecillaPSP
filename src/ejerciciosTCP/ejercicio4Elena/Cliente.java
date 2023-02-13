package ejerciciosTCP.ejercicio4Elena;

import java.io.*;
import java.net.*;

public class Cliente {

    private static BufferedWriter bw;
    private static BufferedReader br;

    public static void main(String[] args) {

        String suma;

        try {
            // 1 - Creación de socket de tipo cliente
            System.out.println("(Cliente): Creación de socket...");
            InetAddress direccion = InetAddress.getLocalHost();
            Socket socketCliente = new Socket(direccion, 50000);

            // 2 - Abrir flujos de lectura y escritura
            System.out.println("(Cliente): Apertura de flujos de entrada y salida...");
            OutputStream os = socketCliente.getOutputStream();
            InputStream is = socketCliente.getInputStream();

            // 3 - Intercambio de datos con el servidor
            System.out.println("(Cliente): Envía el número al servidor...");
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            bw = new BufferedWriter(osw);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            br = new BufferedReader(isr);

            leeFichero();
            suma = br.readLine();
            System.out.println("La suma total es: " + suma);

            // 4 - Cerrar los flujos de lectura y escritura
            System.out.println("(Cliente): Cerramos flujo de lectura y escritura...");
            bw.close();
            osw.close();
            br.close();
            isr.close();

            is.close();
            os.close();

            // 5 - Cerrar la conexión
            socketCliente.close();

        } catch (UnknownHostException e) {
            System.err.println("ERROR: No se encuentra el host");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("ERROR: Problema con la conexión");
            e.printStackTrace();
        }
    }


    public static void leeFichero() {
        String linea;
        File fichero = new File("src/ejerciciosTCP/ejercicio4/ficheroFachero.txt");

        FileReader fr;
        BufferedReader brFile;
        try {
            fr = new FileReader(fichero);
            brFile = new BufferedReader(fr);
            linea = brFile.readLine();
            while(linea != null) {
                bw.write(String.valueOf(linea));
                linea = brFile.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
