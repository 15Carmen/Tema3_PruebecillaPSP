package ejerciciosTCP.ejercicio4;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Servidor {

    private static BufferedWriter bufferedWriter;

    private static BufferedReader bufferedReader;

    public static void main(String[] args) {

        int suma = 0, numero;
        String linea;

        try {
            // 1.- Crear un Socket servidor
            ServerSocket socketServidor = new ServerSocket(3000);

            // 2.- Espera y acepta conexiones, para ello creamos el socket cliente, ya que es lo que devuelve el método,
            // y lo hacemos en bucle infinito, porque es un servidor y no debe apagarse.
            while (true) {
                System.out.println("(Servidor) Esperando peticiones.");
                Socket socketCliente = socketServidor.accept();

                // 3.- Flujo de entrada y salida
                System.out.println("(Servidor) Abriendo flujos de entrada y salida.");
                InputStream is = socketCliente.getInputStream();
                OutputStream os = socketCliente.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                bufferedWriter= new BufferedWriter(outputStreamWriter);

                // 4.- Intercambiar datos con el cliente
                InputStreamReader inputStream = new InputStreamReader(is, StandardCharsets.UTF_8);
                bufferedReader = new BufferedReader(inputStream);

                linea = bufferedReader.readLine();
                while (!linea.equals("FIN")){
                    numero = Integer.parseInt(linea);
                    suma += numero;
                    linea = bufferedReader.readLine();
                    System.out.println(linea);
                }

                System.out.println(suma);
                bufferedWriter.write(String.valueOf(suma));
                bufferedWriter.newLine();
                bufferedWriter.flush();

                // Cerramos los flujos de lectura y escritura
                bufferedWriter.close();
                bufferedReader.close();
                outputStreamWriter.close();
                is.close();
                os.close();

                //Cierro la conexión solo con ese cliente concreto
                socketCliente.close();
            }
        } catch (IOException e) {
            System.err.println("ERROR: Error al crear el socket en el puerto 3000.");
            e.printStackTrace();
        }
    }

}
