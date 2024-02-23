package main.domain.classes;

import java.util.*;

/*
 * Clase Greddy
 * @autor Luis Jesús Valverde Zavaleta
 */

public class Greddy{

    /*
       Algoritmo Greddy que devuelve un map con letra asignada a la posicion del Teclado
      por ejemplo letra d en tecla 2
     */
    public static Map<Integer, Character> Solucion_Parcial(double[][] distancia, int[][] flow, char primera) {

        Map<Integer, Character> solucion = new HashMap<>();

        TreeMap<Double, Set<Integer>>  mejores_teclas_aux = new TreeMap<>();
        AsignaImportanciaTecla(distancia, mejores_teclas_aux);
        TreeMap<Double, Set<Integer>>  mejores_teclas = new TreeMap<>(mejores_teclas_aux.descendingMap());

        TreeMap<Integer, Set<Character>> mejores_letras = new TreeMap<>();
        AsignaImportanciaLetra(flow, mejores_letras, primera);


        ImplementarSolucion(solucion, mejores_letras, mejores_teclas);
        return solucion;
    }


    /*
        AsignaImportanciaTecla le asigna a cada tecla de teclado
       un valor que representa su influencia en el teclado,
       representada con la suma de cada coste con el resto de teclas
     */
    private static void AsignaImportanciaTecla(double[][] distancia, TreeMap<Double, Set<Integer>>  mejores_teclas){
        for ( int i = 0; i < distancia.length; ++i){
            double result = 0.0;
            for ( int j = 0; j < distancia[i].length; ++j){
                result += distancia[i][j];
            }
            if (mejores_teclas.containsKey(result)){
                mejores_teclas.get(result).add(i + 1);
            }
            else{
                Set<Integer> nueva_instancia = new HashSet<>();
                nueva_instancia.add(i + 1);
                mejores_teclas.put(result, nueva_instancia);
            }
        }
    }

    /*
        AsignaImportanciaLetra le asigna a cada letra del alfabeto
       un valor de su influencia con todas sumando el coste de su
       relacion con el resto de letras del alfabeto
    */
    private static void AsignaImportanciaLetra(int[][] flow, TreeMap<Integer, Set<Character>> mejores_letras, char primera){
        for ( int i = 0; i < flow.length; ++i){
            int result = 0;
            for ( int j = 0; j < flow[i].length; ++j){
                result += flow[i][j];
            }
            //Si usamos otra Alfabeto habra que cambiarlo
            char letra = (char) (i + primera);
            if (mejores_letras.containsKey(result)){
                mejores_letras.get(result).add(letra);
            }
            //Si usamos otra Alfabeto habra que cambiarlo
            else{
                Set<Character> nueva_instancia = new HashSet<>();
                nueva_instancia.add(letra);
                mejores_letras.put(result, nueva_instancia);
            }
        }
    }

    /*
        Se hace una asignacion de la letra con mayor influencia
        con la tecla con mayor influencia, y la,s tres letras con menos
         influencia se les asigna las teclas menos influyentes
     */
    private static void ImplementarSolucion( Map<Integer, Character> solucion, TreeMap<Integer, Set<Character>> mejores_letras, TreeMap<Double, Set<Integer>>  mejores_teclas ){
        //Poner la letra con mas influencia en la tecla con mas importancia
        int ultimo_elemento = mejores_letras.lastKey();
        Set<Character> posibles_letras = mejores_letras.get(ultimo_elemento);
        char letra = posibles_letras.iterator().next();

        double tecla_top = mejores_teclas.lastKey();
        Set<Integer> posibles_teclas = mejores_teclas.get(tecla_top);
        int tecla = posibles_teclas.iterator().next();
        solucion.put(tecla, letra);




        //Poner las letras con menor influencia en las teclas mas alejadas;

        int contador = 0;
        Iterator<Map.Entry<Integer, Set<Character>>> it = mejores_letras.entrySet().iterator();
        Iterator<Character> it_aux = null;

        Iterator<Map.Entry<Double, Set<Integer>>> it2 = mejores_teclas.entrySet().iterator();
        Iterator<Integer> it_aux2 = null;

        boolean quedan_teclas = false;
        boolean quedan_letras = false;
        while(it.hasNext() && it2.hasNext() && contador < 3){

            if(!quedan_teclas || contador == 0) {
                Map.Entry<Double, Set<Integer>> entry2 = it2.next();
                posibles_teclas = entry2.getValue();
                it_aux2 = posibles_teclas.iterator();
            }

            if(!quedan_letras || contador == 0) {
                Map.Entry<Integer, Set<Character>> entry = it.next();
                posibles_letras = entry.getValue();
                it_aux = posibles_letras.iterator();
            }

            letra = it_aux.next();
            tecla = it_aux2.next();
            solucion.put(tecla, letra);
            quedan_letras = it_aux.hasNext();
            quedan_teclas = it_aux2.hasNext();
            ++contador;


        }
    }

}