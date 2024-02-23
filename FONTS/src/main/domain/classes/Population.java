package main.domain.classes;

import java.util.*;

/**
 * Clase que Representa el conjunto de Genetics que hay
 * en nuestra población. Además de conseguir el más adapatado
 *
 * @author Iván Parreño Benítez
 */
public class Population extends Algoritmo {
    /**
     * Individuo genetic más adaptado
     */
    private Genetic genetic;

    /**
     * Creadora de la clase Population, que extiende de la clase Algortimo.
     * Se necesita la lista, alfabeto, filas y columnas del teclado.
     * @param lista_teclado
     * @param alfabeto
     * @param filas
     * @param columnas
     */
    public Population(Lista_Palabras lista_teclado, Alfabeto alfabeto, int filas, int columnas) {
        super(lista_teclado, alfabeto, filas, columnas);
    }

    /**
     * Función de creación del teclado. Primero se genera la solución
     * y después se actualiza en un TreeMap que es la representación
     * final del teclado.
     * @return
     */
    @Override
    public TreeMap<Integer, Character> creaTeclado() {
        generacion();
        TreeMap<Integer,Character> solucion = new TreeMap<>();
        for(int i = 0; i < genetic.getCromosoma().size(); ++i) {
            solucion.put(i,genetic.getCromosoma().get(i));
        }
        return solucion;
    }

    /**
     * Función principal de la clase Population.
     * Se divide en 4 partes:
     * 1- Creación población
     * 2- Selección individuos para crossover y mutación
     * 3- Crossover
     * 4- Mutación
     * Finalmente this.genetic será el mejor individuo.
     */
    public void generacion() {
        PriorityQueue<Genetic> poblacion = new PriorityQueue<>(new GeneticComparator());

        this.matriz_flow = crea_matriz_flow();
        this.matriz_distancias = crea_matriz_distancias();

        poblacion= init(alfabeto.getordererCharacters(), 100);

        for(int g = 0; g < 500; ++g) {
            int ps = poblacion.size();
            PriorityQueue<Genetic> salvados_crossover = new PriorityQueue<>(new GeneticComparator());
            PriorityQueue<Genetic> salvados_mutacion = new PriorityQueue<>(new GeneticComparator());
            List<Genetic> lista = new ArrayList<>();
            for(int i = 0; i< ps; ++i) {
                Genetic gen = new Genetic();
                gen = poblacion.poll();
                if((g == 0 && i == 0) || gen.getFitness() < genetic.getFitness()){
                    //System.out.println("Nuevo");
                    genetic = new Genetic(gen);
                }
                if(i < 32)  {
                    lista.add(gen);
                    salvados_crossover.add(gen);
                    salvados_mutacion.add(gen);
                }
            }
            poblacion.addAll(salvados_crossover);
            Collections.shuffle(lista);
            for(int i = 0; i < salvados_crossover.size()/2; ++i) {
                Genetic genetic1 = new Genetic(lista.get(i));
                Genetic genetic2 = new Genetic(lista.get(i+1));
                List<Character> [] hijos = genetic1.crossover(genetic2);
                for(int j = 0; j < 1; ++j) {
                    genetic1 = new Genetic(hijos[j]);
                    genetic1.calculafitness(matriz_flow, matriz_distancias, alfabeto.getPrimera());
                    poblacion.add(genetic1);
                    salvados_mutacion.add(genetic1);
                }
            }
            for(int i = 0; i < salvados_mutacion.size(); ++i) {
                Genetic genetic1 = new Genetic(salvados_mutacion.poll());
                genetic1.mutacion();
                genetic1.calculafitness(matriz_flow, matriz_distancias, alfabeto.getPrimera());
                poblacion.add(genetic1);
            }
        }
        //System.out.println("Final");
        //genetic.print();
    }

    /**
     * Función para crear la primera Generación de individuos.
     * Necesita el alfabeto con el que se generará el teclado, además del tamaño de la población.
     * Devuelve una cola de prioridad con todos los genetic de la primera generación ordenados
     * según su fitness.
     * @param alfabetos
     * @param poblacion
     * @return
     */
    public PriorityQueue<Genetic> init(char[] alfabetos, int poblacion) {
        PriorityQueue<Genetic> inicial = new PriorityQueue<>(new GeneticComparator());
        for(int i = 0; i < poblacion; ++i) {
            Genetic gen = new Genetic();
            gen.shuffle(alfabetos);
            gen.calculafitness(matriz_flow, matriz_distancias, alfabeto.getPrimera());
            inicial.add(gen);
        }
        return inicial;
    }

    /**
     * Función que crea la matriz de flow para el cálculo de fitness.
     * @return
     */
    public int[][] crea_matriz_flow(){
        int[][] matrix = new int[alfabeto.getNumCaracters()][alfabeto.getNumCaracters()];
        Map<String,Integer> listaT = lista_teclado.getWord_list();
        primera = alfabeto.getPrimera();
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
     * Función que crea la matriz de distancias entre las teclas del teclado
     * necesaria para el cálculo de fitness.
     * @return
     */
    public  double[][] crea_matriz_distancias(){
        double[][] matrix = new double[filas*columnas][filas*columnas];
        int px_i, py_i, px_j, py_j;
        for(int i = 0; i < (filas * columnas); ++i) {
            px_i = i%columnas;
            py_i= i/columnas;
            for(int j = i+1; j < (filas * columnas); ++j) {
                px_j = j%columnas;
                py_j= j/columnas;
                matrix[i][j] = matrix[j][i] =
                        Math.sqrt((px_i-px_j)*(px_i-px_j)+(py_i-py_j)*(py_i-py_j));
            }
        }
        return matrix;
    }
}
