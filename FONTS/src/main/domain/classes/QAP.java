package main.domain.classes;

import javax.swing.*;
import java.util.*;


/**
Algoritmo QAP que es una subclasse de Algoritmo

@author Iván Parreño Benítez
 */
public class QAP extends Algoritmo
{
    /**
    Creada de la clase QAP a partir de una Lista_Palabras, Alfabeto, filas y columnas
     */
    public QAP(Lista_Palabras lista_teclado, Alfabeto alfabeto,int filas, int columnas) {
        super(lista_teclado,alfabeto, filas,columnas);
    }

    /**
    Implementación Eager del algoritmo. Vamos explorando Nodos
    que són soluciones parciales hasta que encontramos el final
    y desde clase algoritmo tratamos como enviarselo a teclado.
     */
    @Override
    public TreeMap<Integer, Character> creaTeclado() {
        primera = alfabeto.getPrimera();
        matriz_flow = crea_matriz_flow();
        matriz_distancias = crea_matriz_distancias();
        Map<Integer, Character> solucion = new HashMap<>();
        //Llamada a greedy para crear una solución parcial
        Greddy g = new Greddy();
        solucion = g.Solucion_Parcial(matriz_distancias, matriz_flow,primera);

        Nodo teclado = null;
        Nodo inicial = new Nodo();
        edita_nodo_inicial(inicial,solucion);
        PriorityQueue<Nodo> cola_nodos = new PriorityQueue<Nodo>(new NodoComparator());
        inicial.calcula_cota(matriz_flow,matriz_distancias);
        cola_nodos.add(inicial);
        while(!cola_nodos.isEmpty()) {
            Nodo actual = new Nodo(cola_nodos.poll());
            if(actual.letras_libres.isEmpty() && (teclado == null ||  (actual.valor_cota()) < teclado.valor_cota())){
                teclado = actual;
            }else{
                int cota_padre = actual.valor_cota();
                int cota_hijo = 0;
                Nodo bueno = null;
                int r = 0;
                if (!actual.letras_libres.isEmpty()) {
                    char letra_nueva = actual.letras_libres.pollFirst();
                    TreeSet<Integer> libres = new TreeSet<>(actual.posiciones_libres);
                    Boolean f = false;
                    r = 0;
                    for (Integer i : libres) {

                        Nodo nuevo = new Nodo(actual);
                        nuevo.edita_nodo(letra_nueva, i);
                        nuevo.calcula_cota(matriz_flow, matriz_distancias);
                        //if (r == 0) cota_hijo = nuevo.valor_cota();
                        //System.out.println(cota_hijo);
                        int cota = nuevo.valor_cota();
                        //System.out.println(cota + " " + cota_hijo + " " + cota_padre);
                        if (cota < cota_hijo || r == 0) {
                            if (r == 0) {
                                r = 1;
                                bueno = new Nodo(nuevo);
                                cota_hijo = cota;
                            }
                            if (cota < cota_hijo || cola_nodos.size() < 10) {
                                cota_hijo = cota;
                                bueno = new Nodo(nuevo);
                            }
                        }
                    }
                    /*
                }else{
                    int i =  libres.pollFirst();
                    Nodo nuevo = new Nodo(actual);
                    nuevo.edita_nodo(letra_nueva, i);
                    nuevo.calcula_cota(matriz_flow,matriz_distancias);
                    cola_nodos.add(nuevo);
                }
                     */
                }
                if (r == 1) cola_nodos.add(bueno);
            }
        }
        teclado.imprime_nodo();

        try {
            // Pause the program for 5 seconds
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return teclado.posiciones_ocupadas;
    }

    /**
    Función que edita las diferentes estructuras del Nodo, inicializándolo
     @param nodo nodo inicial que enviamos para su edición
     @param sol solución parcial del greedy
     */
    public void edita_nodo_inicial(Nodo nodo, Map<Integer,Character> sol) {
        TreeSet<Integer> pos = new TreeSet<>();
        TreeSet<Character> libre = new TreeSet<>();
            libre = (TreeSet)letras.clone();
        for(int i = 0 ; i< 30; ++i) {
            if(!sol.containsKey(i)) pos.add(i);
            else{
                libre.remove(sol.get(i));
            }
        }
        TreeMap<Integer,Character> ocupadas = new TreeMap<>(sol);
        nodo.edita_nodo_inicial(pos,libre,primera,ocupadas);
    }

    /**
    Función donde se crea la matriz_flow, que nos indica la frecuencia entre pares
    de letras.
     */
    public int[][] crea_matriz_flow(){
        int[][] matrix = new int[alfabeto.getNumCaracters()][alfabeto.getNumCaracters()];
        Map<String,Integer> listaT = lista_teclado.getWord_list();
        for(String s : listaT.keySet()){
            int m = listaT.get(s);
            for(int i = 0; i < s.length()-1; ++i) {
                char letraini = s.charAt(i);
                char letrasig = s.charAt(i+1);
                if(letraini == 'ñ' || letraini == 'ç') letraini = 'z' +1;
                if(letrasig == 'ñ' || letrasig == 'ç') letrasig = 'z' +1;
                if(letraini != letrasig) {
                    matrix[(letraini-primera)][letrasig-primera] += 1 * m;
                    matrix[(letrasig-primera)][letraini-primera] += 1 * m;
                }
            }
        }
        return matrix;
    }

    /**
    Función donde se crea la matriz_distancias, que nos indica la distancia entre teclas
     */
    public  double[][] crea_matriz_distancias(){
        double[][] matrix = new double[filas*columnas][filas*columnas];
        int px_i, py_i, px_j, py_j;
        for(int i = 0; i < (filas * columnas); ++i) {
            px_i = i%columnas;
            py_i= i/columnas;
            //System.out.println(py_i + " " + px_i);
            for(int j = i+1; j < (filas * columnas); ++j) {
                px_j = j%columnas;
                py_j= j/columnas;
                //System.out.println(py_j + " " + px_j);
                matrix[i][j] = matrix[j][i] =
                        Math.sqrt((px_i-px_j)*(px_i-px_j)+(py_i-py_j)*(py_i-py_j));
            }
        }
        return matrix;
    }

    @Override
    public String getnombreAlgoritmo() {
        return "Branch&Bound";
    }
}
