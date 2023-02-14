package ejerciciosTCP.ejercicio4;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Cliente {

    private static BufferedWriter bw;

    public static void main(String[] args) {
        String suma;
        try {
            //1.- Creacion del Socket del tipo Cliente
            System.out.println("(Cliente) Creamos Socket");
            Socket socketCliente=new Socket(InetAddress.getLocalHost(),3000);

            // 2.- Abrimos flujo de lectura y escritura
            System.out.println("(Cliente) Abrimos flujo de entrada y salida");
            InputStream is=socketCliente.getInputStream();
            OutputStream os=socketCliente.getOutputStream();

            // 3.- Intercambiamos datos con el servidor
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(os, StandardCharsets.UTF_8);
            bw = new BufferedWriter(outputStreamWriter);
            InputStreamReader inputStreamReader=new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(inputStreamReader);



            leerFichero();

            suma = br.readLine();
            System.out.printf("La suma total de los números del fichero es %s", suma);

            // 4.- cerramos flujo de datos
            br.close();
            bw.close();
            outputStreamWriter.close();
            inputStreamReader.close();
            is.close();
            os.close();
            socketCliente.close();

        } catch (IOException e) {
            System.err.println("ERROR: Problema con la conexión.");
            e.printStackTrace();
        }
    }

    private static void leerFichero (){
        String linea;
        File fichero = new File("src/ejerciciosTCP/ejercicio4/ficheroFachero.txt");
        FileReader fr;
        BufferedReader brFile;

        try {
            fr = new FileReader(fichero);
            brFile = new BufferedReader(fr);
            linea = brFile.readLine();

            while(linea != null) {
                bw.write(linea);
                bw.newLine();
                bw.flush();
                linea = brFile.readLine();
            }
            bw.write("FIN");
            bw.newLine();
            bw.flush();
            brFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
