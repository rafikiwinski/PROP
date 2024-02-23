package main.domain.classes;

import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*Class Representing a Frequency List of Words
* @author Rafael Ibáñez Rodríguez*/
public class Lista_Palabras {

    /*Nombre de la lista*/
    private String name_list;

    /*El Mapa es la estructura de datos más conveniente para hacer la lista de frequencias*/
    private Map<String, Integer> word_list;

    /**
     * Alfabeto de la lista
     */
    private String nom_alfabeto;


    //Creadoras

    /**
     *Creadora de la lista apartir de un string para el nombre
     * @param name es el nombre de la lista
     */
    public Lista_Palabras(String name){
        this.name_list = name;
    }

    /**
     *Creadora de la lista apartir de un string para el nombre y un texto para
     * sacar la lista de palabras
     * @param name es el nombre de la lista, texto es el texto del que sacaremos
        *            la lista
     * @param text es el texto del que sacaremos la lista
     * @param nom_alfabeto es el nombre del alfabeto
     */
    public Lista_Palabras(String name, String text, String nom_alfabeto){
        this.name_list = name;
        this.nom_alfabeto = nom_alfabeto;
        createFromString(text);
    }

    /**
     * Creadora de la lista apartir de un string para el nombre y un mapa para
     * sacar la lista de palabras
     * @param name es el nombre de la lista
     * @param freq es el mapa de palabras
     * @param nom_alfabeto es el nombre del alfabeto
     */
    public Lista_Palabras(String name, String nom_alfabeto, Map<String, Integer> freq){
        this.name_list = name;
        this.nom_alfabeto = nom_alfabeto;
        this.word_list = freq;
    }

    //Getters

    /**
     * Getter del numero de palabras en la lista
     * @return numero de palabras
     */
    public int getListSize(){
        return word_list.size();
    }

    /**
     * Getter del nombre de la lista
     * @return String
     * */
    public String getName_list() {
        return name_list;
    }

/**
     * Getter del nombre del alfabeto
     * @return String
     * */
    public String getNom_alfabeto() {
        return nom_alfabeto;
    }

    //Setters

    /**
     * Setter del nombre de la lista
     * @param name nombre de la lista
     * */
    public void setName_list(String name){
        this.name_list = name;
    }

    //Métodos de la clase

    /**Clase constructora de la lista de palabras
    * Crea una lista de palabras con los parametros introducidos
    * Luego el mapa se crea apartir de dividir la string en tokens y recorrerla */
    public void createFromString(String text){
        Map<String, Integer> frequencies = new HashMap<>();
        String text_no_punctuation = removePunctuation(text);
        text_no_punctuation = text_no_punctuation.toLowerCase();
        String[] words = text_no_punctuation.split(" ");
        for (String word : words) {
            if (word.equals("")) continue;
            if (word.equals(" ")) continue;
            if (word.equals("\n")) continue;
            frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
        }
        this.word_list = frequencies;
    }

    public static String removePunctuation(String str) {
        Pattern pattern = Pattern.compile("[¿¡+/$·#%&().:;<>*^{}=@~¬,!?1234567890_'-]");
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("");
    }

    /**
     * Función que imprime la lista de palabras
     */
    public void printList(){
        for (Map.Entry<String, Integer> entry : word_list.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * Función que busca una palabra en la lista
     * @param word palabra a buscar
     * @return numero de veces que aparece la palabra, si esta no aparece retorna null
     */
    public Integer buscaPalabra(String word){
        return word_list.get(word);
    }

    /**
     * Función que modifica el valor de una palabra
     *
     * @param word palabra a modificar
     * @param n    nuevo valor de la palabra
     */
    public void setWordValue(String word, int n){
        word_list.put(word, n);
    }


    /**
     * Función que borra una palabra de la lista
     * @param word palabra a borrar
     */
    public void borraParaula(String word){
        word_list.remove(word);
    }

    /**
     * Función que añade una palabra a la lista
     * @param word palabra a añadir
     * @param n valor de la palabra
     */
    public void anadePalabra(String word, int n){
        word_list.put(word, n);
    }

    /**
     * Función que devuelve la lista de palabras
     * @return lista de palabras
     */
    public Map<String, Integer> getWord_list() {
        return word_list;
    }

}
