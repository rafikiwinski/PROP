package main.domain.classes;

import java.util.*;

public class Genetic {
    /**
     * Lista de Characters que representa el teclado.
     */
    public List<Character> lista = new ArrayList<>();
    /**
     * Atributo que representa la "adaptación" del gen al teclado
     */
    private double fitness;

    /**
     * Creadora de la clase Genetic
     */
    public Genetic(){}

    /**
     * Creadora de la clase teclado a partir de una lista de Characters
     * @param lista
     */
    public Genetic(List<Character> lista) {
        this.lista = lista;
    }

    /**
     * Creadora de la clase a partir de otro Genetic.
     * @param genetic
     */
    public Genetic(Genetic genetic) {
        this.lista = new ArrayList<>(genetic.lista);
        this.fitness = genetic.fitness;
    }

    /**
     * Función encargada de mezclar el alfabeto para la creación de
     * un individuo de la 1ª generaciñon
     * @param alfabeto
     */
    public void shuffle(char[] alfabeto) {
        List<Character> lista_final = new ArrayList<>();
        for (char c : alfabeto) {
            lista_final.add(c);
        }
        Collections.shuffle(lista_final);
        this.lista = lista_final;
    }
    /*
    public void print() {
        for(int i = 0; i < lista.size(); ++i) {
            if(i == 10 || i == 20) System.out.println();
            System.out.print(lista.get(i) + " ");
        }
        System.out.println(" " + fitness);
    }
     */

    /**
     * Función que devuelve la List<Character>
     * @return
     */
    List<Character> getCromosoma(){
        return lista;
    }

    /**
     * Primera parte de la función del crossover.
     * Con el genetic actual y otro que se pasa por referencia, se hace
     * un crossover en dos puntos aleatorios de los genes, creando
     * dos hijos nuevos.
     * @param other
     * @return
     */
    List<Character>[] crossover(Genetic other) {

        char[] padre1 = new char[lista.size()];
        char[] padre2 = new char[lista.size()];
        for (int i = 0; i < lista.size(); ++i) {
            padre1[i] = lista.get(i);
            padre2[i] = other.lista.get(i);
        }
        Random random = new Random();
        int r1 = random.nextInt(lista.size());
        int r2 = random.nextInt(lista.size());
        while (r1 >= r2) {
            r1 = random.nextInt(lista.size());
            r2 = random.nextInt(lista.size());
        }
        char[] hijo = crossover_final(padre1,padre2,r1,r2);
        char[] hijo2 = crossover_final(padre2,padre1,r1,r2);
        List<Character>[] hijos = createArrayOfLists();
        List<Character> nuevo = new ArrayList<>();
        List<Character> nuevo2 = new ArrayList<>();
        for(int j = 0; j < hijo.length; ++j) {
            nuevo.add(hijo[j]);
            nuevo2.add(hijo2[j]);
        }
        hijos[0] = nuevo;
        hijos[1] = nuevo2;
        return hijos;
    }

    /**
     * Segunda parte del crossover, encargada de realizar la función en si.
     * Se pasan los dos padres a cruzar y los puntos dónde se cruzan.
     * Devuelve un hijo parecido a padre1, pero cruzado con padre2.
     * @param padre1
     * @param padre2
     * @param r1
     * @param r2
     * @return
     */
    private char[] crossover_final(char[] padre1, char[] padre2, int r1, int r2) {
        char[] hijo = new char[lista.size()];
        ArrayList<Character> diferentes = new ArrayList<>();
        for(int i = r1; i < r2; ++i) {
            hijo[i] = padre1[i];
        }
        for (int i = r1; i < r2; i++) {
            boolean flag = true;
            for (int j = r1; j < r2; j++) {
                if (padre2[i] == hijo[j]) {
                    flag = false;
                }
            }
            if (flag) {
                diferentes.add((char) i);
            }
        }
        for (int i = 0; i < diferentes.size(); i++) {
            char actual = padre1[diferentes.get(i)];
            char guardado = padre2[diferentes.get(i)];
            boolean flag = true;
            while (flag) {
                int indice = -1;
                for (int j = 0; j < padre2.length; j++) {
                    if (padre2[j] == actual) {
                        indice = j;
                    }
                }
                if (hijo[indice] == 0) {
                    hijo[indice] = guardado;
                    flag = false;
                } else {
                    actual = padre1[indice];
                }
            }
        }
        for (int i = 0; i < padre2.length; i++) {
            if (hijo[i] == 0) {
                hijo[i] = padre2[i];
            }
        }
        return hijo;
    }

    /**
     * Función de mutació de un Genetic.
     * @return
     */
    List<Character> mutacion() {
        int random_int = (int)Math.floor(Math.random() * (3 - 2 + 1) + 2);
        //System.out.println(random_int);
        for(int i = 0; i < random_int; ++i) {
            Random r1 = new Random();
            Random r2 = new Random();
            int ram1 = r1.nextInt(lista.size());
            int ram2 = r2.nextInt(lista.size());
            while(ram1 == ram2) ram2 = r2.nextInt(lista.size());
            Character a = lista.get(ram1);
            Character b = lista.get(ram2);
            if(ram1 > ram2) {
                lista.remove(ram1);
                lista.remove(ram2);
                lista.add(ram2,a);
                lista.add(ram1,b);
            }
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    private List<Character>[] createArrayOfLists() {
        return (List<Character>[]) new List<?>[2];
    }

    /**
     * Cálculo del fitness de un gen. Parecido al cálculo del primer término
     * en el B&B.
     * @param matriz_flow
     * @param matriz_costes
     * @param primera
     */
    public void calculafitness(int [][] matriz_flow, double[][] matriz_costes, char primera) {
        TreeSet<Character> visitadas = new TreeSet<>();
        this.fitness = 0;
        for(int i = 0; i < matriz_flow.length; ++i) {
            for(int j = 0; j < matriz_flow[i].length; ++j) {
                if(!visitadas.contains(lista.get(j)) && lista.get(i) != lista.get(j)) {
                    if(lista.get(j) == 'ñ' || lista.get(j) == 'ç'){
                        char prueba = ('z'+1);
                        this.fitness += (matriz_costes[i][j] * matriz_flow[lista.get(i)-primera][prueba-primera]);
                    }else if (lista.get(i) == 'ñ' || lista.get(i) == 'ç') {
                        char prueba = ('z'+1);
                        this.fitness += (matriz_costes[i][j] * matriz_flow[prueba-primera][lista.get(j)-primera]);
                    }
                    else{
                        this.fitness += (matriz_costes[i][j] * matriz_flow[lista.get(i)-primera][lista.get(j)-primera]);
                    }
                }
            }
            visitadas.add(lista.get(i));
        }
    }

    public double getFitness(){
        return fitness;
    }

}
