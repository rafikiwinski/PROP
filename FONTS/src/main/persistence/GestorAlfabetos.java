package main.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class GestorAlfabetos {
    private static GestorAlfabetos instance = null;

    private List<String> alfabetos = new LinkedList<>();

    public static GestorAlfabetos getInstance() {
        if (instance == null) instance = new GestorAlfabetos();
        return instance;
    }

    /**
     * Creadora del gestor de alfabetos
     */
    private GestorAlfabetos() {
        try {
            Scanner in = new Scanner(new FileReader("../../DATA/alfabetos.txt"));
            while (in.hasNextLine()) {
                alfabetos.add(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Función que devuelve los alfabetos
     * @return lista de todos los alfabetos
     */
    public List<String> getAlfabetos() {
        return alfabetos;
    }

    /**
     * Función que devuelve un alfabeto
     * @param alfabeto nombre del alfabeto
     * @return alfabeto
     */
    public char[] getAlfabeto(String alfabeto){
        try {
            ArrayList<Character> alf = new ArrayList<>();
            Scanner in = new Scanner(new FileReader("../../DATA/alfabetos/" + alfabeto + ".txt"));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                alf.add(line.charAt(0));
            }
            char[] res = new char[alf.size()];
            for (int i = 0; i < alf.size(); ++i) {
                res[i] = alf.get(i);
            }
            return res;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
