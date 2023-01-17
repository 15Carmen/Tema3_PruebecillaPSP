package ejercicios.ejercicio2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente2 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int num;

        try {
            //1.- Creacion del socket de tipo cliente
            System.out.println("(Cliente): Creacion del socket...");
            InetAddress direccion= InetAddress.getLocalHost();
            Socket socketCliente = new Socket(direccion, 2500);

            //2.- Abrir flujos de lectura y escritura
            System.out.println("(Cliente): Apertura de flujos de entrada y salida...");
            OutputStream os = socketCliente.getOutputStream();
            InputStream is = socketCliente.getInputStream();

            //3.- Intercambio de datos con el servidor
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);

            num = leerNumero();
            bw.write(num);

            bw.newLine();
            bw.flush();

            System.out.println("(Cliente): Lectura del mensaje del servidor...");
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println("Mensaje recibido por el cliente: " + br.readLine());

            //4.- Cerrar los flujos de lectura y escritura
            System.out.println("(Cliente): Cerramos el flujo de lectura y escritura...");
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

    private static int leerNumero() {
        int num;
        do {
            System.out.println("Introduzca un numero entero positivo: ");
            num = sc.nextInt();
        }while (num<0);

        return num;
    }


}

