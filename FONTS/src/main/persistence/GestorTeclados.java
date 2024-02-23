package main.persistence;

import java.io.*;
import java.util.*;

import java.util.HashMap;
import java.util.Scanner;

import main.domain.classes.exceptions.tecladoExistente;

public class GestorTeclados {
    private static GestorTeclados instance = null;

    private List<String> teclados = new LinkedList<>();

    public static GestorTeclados getInstance() {
        if (instance == null) instance = new GestorTeclados();
        return instance;
    }
    /**
     * Obtiene la distribución de teclado asociada al nombre nom.
     * @param nom Nombre del teclado.
     * @return TreeMap que representa la distribución de teclas.
     */
    public TreeMap<Integer, Character> getTeclado(String nom){
        TreeMap< Integer, Character> comodin = new TreeMap<>();
        try {

            FileReader fr = new FileReader("../../DATA/teclados/" + nom + ".txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(" ");
                comodin.put(Integer.parseInt(parts[1]), parts[0].charAt(0));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return comodin;
    }

    public void printTeclado(String teclado) {
        try {
            TreeMap< Integer, Character> comodin = new TreeMap<>();

            FileReader fr = new FileReader("../../DATA/teclados/" + teclado + ".txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(" ");
                comodin.put(Integer.parseInt(parts[1]), parts[0].charAt(0));
            }
            int i = 0;
            for (HashMap.Entry<Integer, Character> it : comodin.entrySet()) {
                System.out.print(it.getValue() + " ");
                if (it.getKey() >= 9 && i == 0) {
                    System.out.println();
                    i = 1;
                }
                if (it.getKey() >= 19 && i == 1) {
                    System.out.println();
                    i = 2;
                }
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void Teclado_existente(String nom_teclado) throws tecladoExistente{
        if(teclados.contains(nom_teclado)) throw new tecladoExistente(nom_teclado);
    }

    public boolean comprueba_existencia(String nom_teclado){
        if(teclados.contains(nom_teclado)) return true;
        else return false;
    }

    /**
     * Agrega un nuevo teclado con el nombre y la distribución especificados
     * @param nom_teclado Nombre del nuevo teclado
     * @param tm Distribución de teclas representada como un TreeMap
     * @throws tecladoExistente excepción lanzada si el teclado ya existe
     */
    public void addTeclado(String nom_teclado, TreeMap<Integer, Character> tm) throws tecladoExistente{
        Teclado_existente(nom_teclado);
        teclados.add(nom_teclado);
        try{
            FileWriter fw = new FileWriter("../../DATA/teclados.txt", true);
            fw.write(nom_teclado + "\n");
            fw.close();
            FileWriter fw2 = new FileWriter("../../DATA/teclados/" + nom_teclado + ".txt");

            Iterator<Map.Entry<Integer, Character>> iterator = tm.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<Integer, Character> entry = iterator.next();
                Character value = entry.getValue();
                Integer posicion = entry.getKey();

                fw2.write(value + " " + posicion + "\n");
            }
            fw2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeWords(String word, String path) {
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

    public void cambiarnombreteclados(String antes, String despues) throws tecladoExistente {
        TreeMap<Integer, Character> aux = this.getTeclado(antes);
        this.removeTeclado(antes);
        this.addTeclado(despues, aux);
    }
    /**
     * Obtiene la lista de nombres de todos los teclados existentes en el sistema.
     * @return Lista de nombres de teclados.
     */
    public List<String> getTeclados(){
        return teclados;
    }
    /**
     * Elimina un teclado existente por su nombre
     * @param nom_Teclado nombre del teclado a eliminar
     */
    public boolean removeTeclado(String nom_Teclado) {
        teclados.remove(nom_Teclado);
        removeWords(nom_Teclado, "../../DATA/teclados.txt");
        //Codi PROPI
        File f = new File("../../DATA/teclados/" + nom_Teclado + ".txt");
        return f.delete();
    }

    public void guarda(){

        try{
            Scanner in = new Scanner(new FileReader("../../DATA/teclados.txt"));
            List<String> auxiliar = new ArrayList<>();
            while (in.hasNextLine()) {
                String line = in.nextLine();
                auxiliar.add(line);
            }
            teclados = auxiliar;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private GestorTeclados(){

    }
}
// Juan José Torredemer
