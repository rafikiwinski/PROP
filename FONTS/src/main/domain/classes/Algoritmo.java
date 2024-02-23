package main.domain.classes;

/**
 * Clase que representa un algoritmo que crea
 * un teclado nuevo a partir de una lista de palabras
 * 
 * @autor Iván Parreño Benítez
 * 
 */



import java.util.TreeMap;
import java.util.TreeSet;

public abstract class Algoritmo {
    //atributos privados
    /** Nombre del Algoritmo*/
    private String nombre;
    /* Número de filas del teclado*/
    int filas;
    /* Número de columnas del teclado*/
    int columnas;
    /*Lista de Palabras con las que se creará el teclado*/
    Lista_Palabras lista_teclado;

    Alfabeto alfabeto;
    /*Matriz de flow entre letras*/
    int[][] matriz_flow;
    /*Matriz de distancia entre teclas*/
    double[][] matriz_distancias;
    /*Letras que faltan añadir en el teclado*/
    TreeSet<Character> letras;
    char primera;

    public Algoritmo(Lista_Palabras lista_teclado, Alfabeto alfabeto,int filas, int columnas) {
        this.alfabeto = alfabeto;
        this.lista_teclado = lista_teclado;
        this.filas = filas;
        this.columnas = columnas;
        matriz_flow = new int[alfabeto.getNumCaracters()][alfabeto.getNumCaracters()];
        char[] l = alfabeto.getordererCharacters();
        /*letras.add('a');
        letras.add('b');*/
        letras = new TreeSet<>();

        for(int i = 0; i < l.length; ++i) {
            letras.add(l[i]);
        }

        /*
        for(char c : l) {
            System.out.print(c + " ");
            Character aux = c;
            letras.add(aux);
        }

         */
    }

    /**
     * Función que crea la matriz de flow entre letras
     * @return la matriz de flow
     */
    public TreeMap<Integer,Character> creaTeclado(){
        TreeMap<Integer,Character> dist_vacia = new TreeMap<>();
        return dist_vacia;
    }
   
    /*
     * Un teclado creado se envia para que se imprima 
     * por pantalla
     */
    public void enviaTeclado(){
        
    }

    /*
     * Devuelve el nombre del algoritmo
     */
    public String getnombreAlgoritmo(){
        return null;
    }

    public void analizaNodo(Nodo teclado){

    }
}
