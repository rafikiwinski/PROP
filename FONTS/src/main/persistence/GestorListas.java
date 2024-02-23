package main.persistence;

import java.io.*;
import java.nio.file.Files;
import java.util.*;


public class GestorListas {
    private static GestorListas instance = null;

    private List<String> listas = new LinkedList<>();

    public static GestorListas getInstance() {
        if (instance == null) instance = new GestorListas();
        return instance;
    }

    private GestorListas() {
        try {
            actualizaIndice();
            Scanner in = new Scanner(new FileReader("../../DATA/listas.txt"));
            while (in.hasNextLine()) {
                listas.add(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Función que actualiza el índice de las listas
     */
    private void actualizaIndice(){
        try{
            File fi = new File("../../DATA/listas.txt");
            fi.delete();
            File dir = new File("../../DATA/listas");
            File[] files = dir.listFiles();

            FileWriter fw = new FileWriter("../../DATA/listas.txt");
            for (File f : files) {
                String fileName = f.getName();
                String[] parts = fileName.split("\\.");
                fw.write(parts[0] + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Función que añade una lista
     * @param lista nombre de la lista
     * @param freq frecuencias de la lista
     */
    public void addLista(String lista, Map<String, Integer> freq, String alfabeto){
        listas.add(lista);
        try{
            FileWriter fw = new FileWriter("../../DATA/listas.txt", true);
            fw.write(lista + "\n");
            fw.close();
            FileWriter fw2 = new FileWriter("../../DATA/listas/" + lista + ".txt");
            fw2.write(alfabeto + "\n");
            for (Map.Entry<String, Integer> entry : freq.entrySet()) {
                fw2.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
            fw2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAlfabeto_List(String Lista){
        String comodin = " ";
        try {

            FileReader fr = new FileReader("../../DATA/listas/" + Lista + ".txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            line = br.readLine();
            comodin = line;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return comodin;
    }

    /**
     * Función que elimina una lista
     * @param lista nombre de la lista a eliminar
     */
    public boolean removeLista(String lista){
        listas.remove(lista);
        removeWords(lista, "../../DATA/listas.txt");
        //Codi PROPI
        File f = new File("../../DATA/listas/" + lista + ".txt");
        return f.delete();
    }

    /**
     * Función que devuelve las frecuencias de una lista
     * @param lista nombre de la lista
     * @return frecuencias de la lista
     */
    public Map<String, Integer> getListaFrecuencias(String lista){
        Map<String, Integer> freq = new HashMap<>();
        try{
            FileReader fr = new FileReader("../../DATA/listas/" + lista + ".txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            //saltamos la primera linea que contiene el alfabeto
            line = br.readLine();
            while((line = br.readLine()) != null){
                String[] parts = line.split(" ");
                freq.put(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return freq;
    }

    /**
     * Función que devuelve una lista con el nombre de todas las listas
     * @return lista con todas las listas
     */
    public List<String> getListasPalabras(){
        return listas;
    }

    private void removeWords(String word, String path) {
        try {
            //Aquesta funció l'hem agafat d'un post de stackoverflow URL: https://stackoverflow.com/questions/1377279/find-a-line-in-a-file-and-remove-it
            File inputFile = new File(path);
            File tempFile = new File("../../DATA/Temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(word)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
