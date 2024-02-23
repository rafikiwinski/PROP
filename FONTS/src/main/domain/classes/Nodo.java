package main.domain.classes;

import java.util.*;

/**
 Clase que representa una solución Parcial del teclado en un momento
 dado, por tanto, no tiene porque tener todas las letras añadidas.

 @author Iván Parreño Benítez
 */
public class Nodo{
    /**
     Valor actual de la cota del nodo, sumando primer término más el
     resultado del Hungarian.
     */
    private int cota;
    /**
     Resultado del primer término*/
    private int primer;
    /**
     Mapa ordenado dónde se indican las posciones del teclado y
     las letras que ocupan
     */
    TreeMap<Integer,Character> posiciones_ocupadas;
    /**
     Set ordenado dónde se indica cuáles son las letras que
     falta por colocar en el teclado
     */
    TreeSet<Character> letras_libres;
    /**
     Set ordenado dónde se indica cuáles son las las posiciones
     donde todavía falta por añadir alguna letra. Al final de la ejecución no
     tiene por qué estar vacío.
     */
    TreeSet<Integer> posiciones_libres;
    /**
     Primera letra del alfabeto del teclado, que nos ayuda a saber en
     que posición empezar a construir diversas matrices*/
    Character primera;

    /**Creadora de la clase Nodo*/
    public Nodo(){}

    /**
     Creadora de la clase nodo, dónde se le asignan los parámetros del nodo que recibimos
     @param inicial nodo con el que se crea el nuevo nodo
     */
    public Nodo(Nodo inicial) {
        this.posiciones_ocupadas = new TreeMap<>(inicial.posiciones_ocupadas);
        this.letras_libres = new TreeSet<>(inicial.letras_libres);
        this.posiciones_libres = new TreeSet<>(inicial.posiciones_libres);
        this.primera = new Character(inicial.primera);
        this.cota = inicial.cota;
        this.primer = inicial.primer;
    }

    /** Devuelve el valor de la cota del nodo*/
    public int valor_cota(){
        return cota;
    }

    /**
     Edita el nodo actual con el que estaba trabajando añadiendo una nueva letra
     en una posición.
     @param letra Character de la letra que se insertará
     @param posicion
     */
    public void edita_nodo(Character letra, Integer posicion){
        this.posiciones_ocupadas.put(posicion,letra);
        this.letras_libres.remove(letra);
        this.posiciones_libres.remove(posicion);
    }

    /**Funcionalidad básica del nodo, que calcula la cota*/
    public void calcula_cota(int[][] matriz_flow, double[][] matriz_distancias) {
        calculo_primer_termino(matriz_flow, matriz_distancias);
        if(!letras_libres.isEmpty()) {
            double[][] C1 = calculo_C1(matriz_flow,matriz_distancias);
            double[][] C2 = calculo_C2(matriz_flow,matriz_distancias);
            int[][] C3 = suma_matrices(C1,C2);
            Hungarian hungarian = new Hungarian();
            int segundo = hungarian.Coste_Hungarian(C3);
            this.cota = primer + segundo;
        }else{
            this.cota = primer;
        }

    }

    /**
     Cálculo del primer término. Se suman el flow entre las teclas colocadas por
     el coste de su distancia
     */
    public void calculo_primer_termino(int [][] matriz_flow, double[][] matriz_costes) {
        TreeSet<Integer> visitadas = new TreeSet<>();
        primer = 0;
        for(Map.Entry<Integer,Character> it : posiciones_ocupadas.entrySet()) {
            for (Map.Entry<Integer, Character> it_2 : posiciones_ocupadas.entrySet()) {
                if (!visitadas.contains(it_2.getKey()) && it.getValue() != it_2.getValue()) {
                    if(it.getValue() == 'ñ' || it.getValue() == 'ç') {
                        char prueba = ('z'+1);
                        primer += (int) (matriz_costes[it.getKey()][it_2.getKey()] * matriz_flow[prueba - primera][it_2.getValue() - primera]);
                    }else if(it_2.getValue() == 'ñ' || it_2.getValue() == 'ç') {
                        char prueba = ('z'+1);
                        primer += (int) (matriz_costes[it.getKey()][it_2.getKey()] * matriz_flow[it.getValue() - primera][prueba - primera]);
                    }else{
                        primer += (int) (matriz_costes[it.getKey()][it_2.getKey()] * matriz_flow[it.getValue() - primera][it_2.getValue() - primera]);
                    }
                }
            }
            visitadas.add(it.getKey());
        }
    }

    /**
     Cálculo de la matriz C1. Se crea una matriz (N-M)(N-M) dónde cada posición representa
     el coste de colocar una letra en esa posición libre, respecto a las ya emplazadas
     */
    public double[][] calculo_C1(int [][] matriz_flow, double[][] matriz_costes){
        int tam_C1 = matriz_flow.length-posiciones_ocupadas.size();
        char prueba = ('z'+1);
        double[][] C1 = new double[tam_C1][tam_C1];
        Iterator<Character> it_letras = letras_libres.iterator();
        for(int i = 0; i < tam_C1; ++i) {
            Character letra_actual = it_letras.next();
            if(letra_actual == 'ñ' || letra_actual == 'ç') letra_actual = prueba;
            Iterator<Integer> it_libres = posiciones_libres.iterator();
            for(int j = 0; j < tam_C1; ++j) {
                int libre_actual = it_libres.next();
                for(Map.Entry<Integer, Character> entry : posiciones_ocupadas.entrySet()) {
                    if (entry.getValue() == 'ñ' || entry.getValue() == 'ç') {
                        C1[i][j] += matriz_flow[prueba - primera][letra_actual - primera] * matriz_costes[entry.getKey()][libre_actual];
                    }else {
                        C1[i][j] += matriz_flow[entry.getValue() - primera][letra_actual - primera] * matriz_costes[entry.getKey()][libre_actual];
                    }
                }
            }
        }
        return C1;
    }

    /**
     Cálculo de la matriz C2. Se crea una matriz (N-M)(N-M), dónde cada posición represnta
     el coste de colocar una tecla no emplazada en una posición, respecto a las teclas
     aún no colocadas
     */
    public double[][] calculo_C2(int[][] matriz_flow, double[][] matriz_costes){
        int tam_C2 = matriz_flow.length-posiciones_ocupadas.size();
        char prueba = ('z'+1);
        double[][] C2 = new double[tam_C2][tam_C2];
        Iterator<Character> it_libres = letras_libres.iterator();
        for(int i = 0; i < tam_C2; ++i) {
            Character letra_actual = it_libres.next();
            List<Integer> T = new ArrayList<>();
            if(letra_actual == 'ñ' || letra_actual == 'ç') calculo_T(T,matriz_flow,prueba);
            else calculo_T(T,matriz_flow,letra_actual);
            Iterator<Integer> iterator = posiciones_libres.iterator();
            int j = 0;
            Collections.sort(T);
            while (iterator.hasNext() && j < tam_C2) {
                List<Double> D = new ArrayList<>();
                calculo_D(D,matriz_costes, i);
                D.sort(Collections.reverseOrder());
                Iterator<Double> it_D = D.iterator();
                Iterator<Integer>it_T = T.iterator();
                double suma = 0;
                while(it_D.hasNext() && it_T.hasNext()) {
                    suma += it_D.next()*it_T.next();
                }
                C2[i][j] = suma;
                ++j;
            }
        }
        return C2;
    }

    /**Cálculo del vector T, necesario en calculo_C2*/
    public void calculo_T(List<Integer> T, int[][] matriz_flow, char letra) {
        char prueba = ('z'+1);
        for(Character letras : letras_libres) {
            if(letras != letra){
                if (letras == 'ñ' || letras == 'ç' ) {
                    T.add(matriz_flow[letra - primera][prueba - primera]);
                }else {
                    T.add(matriz_flow[letra - primera][letras - primera]);
                }
            }
        }
    }

    /**Cálculo del vector D, necesario en calculo_C2*/
    public void calculo_D(List<Double> D,double[][] matriz_costes, int pos) {
        for(Integer posicion : posiciones_libres) {
            if(posicion != pos && posicion < letras_libres.size()) {
                D.add(matriz_costes[posicion][pos]);
            }
        }
    }


    /**Suma Matrices C1 y C2 y las convertimos en enteros*/
    public int[][] suma_matrices(double[][] C1, double[][] C2) {
        int[][] C3 = new int[C1.length][C1.length];
        for(int i = 0; i < C1.length; ++i) {
            for(int j = 0; j < C1[i].length; ++j) {
                C3[i][j] = (int) (C1[i][j]+C2[i][j]);
            }
        }
        return C3;
    }

    /** Editar nodo inicial, enviando solución del greedy y su estado actual*/
    public void edita_nodo_inicial(TreeSet<Integer> pos, TreeSet<Character> letras, Character primera,
                                   TreeMap<Integer, Character> ocupadas ) {
        this.posiciones_libres = pos;
        this.letras_libres = letras;
        this.primera = primera;
        this.posiciones_ocupadas = ocupadas;
    }

    public void imprime_nodo() {
        int i = 0;
        for(Map.Entry<Integer,Character> it : posiciones_ocupadas.entrySet()) {
            System.out.print(it.getValue() + " ");
            ++i;
            if(i == 10){
                System.out.println();
                i = 0;
            }
        }
        System.out.println(cota);
    }
}