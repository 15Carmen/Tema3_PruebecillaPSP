package conexionTCP_2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP2 {
    public static void main(String[] args) {
        try {
            //1.- Creacion del socket de tipo cliente
            System.out.println("Estableciendo conexión con el servidor");

            Socket cliente = new Socket("192.168.0.176", 49200);

            //2.- Abrir flujos de lectura y escritura
            OutputStream os = cliente.getOutputStream();
            InputStream is = cliente.getInputStream();

            //3.- Intercambio de datos con el servidor
            //Envío de mensaje de texto al servidor
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("Soy el cliente. Envío este mensaje al servidor");
            bw.newLine();
            bw.flush();

            //Leo mensajes que me envía el servidor
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println("El servidor me envía el siguiente mensaje: " + br.readLine());

            //4.- Cerrar flujos de lectura y escritura
            br.close();
            isr.close();
            is.close();
            bw.close();
            osw.close();
            os.close();

            //5.- Cerrar la conexión
            System.out.println("Se cierra la conexion del cliente");
            cliente.close();

        } catch (UnknownHostException e) {
            System.err.println("No se encuentra el host especificado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Se ha producido un error en la conexión con el servidor.");
            e.printStackTrace();
        }
    }
}