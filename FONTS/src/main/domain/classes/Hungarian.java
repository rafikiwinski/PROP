package main.domain.classes;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;


/*
 * Clase Hungarian
 * @autor Luis Jesús Valverde Zavaleta
 */

public class Hungarian {

    // indicadores de matriz
    int[] marcaInFila, marcaInCol, filaCubierta, colCubierta, zerosInFila;

    /* Funcion que retornara el coste de matriz

     */
    public int Coste_Hungarian(int[][] matriz) {
        //Comienzo fase de preproceso
        int final_size;
        int num_fila = matriz.length;
        int num_columna = matriz[0].length;
        final_size = Math.max(num_fila, num_columna);

        //Si la matriz NO es cuadrada la normalizo
        if (matriz.length != matriz[0].length) matriz = normalizar(matriz, final_size, num_fila, num_columna);

        //Me tengo que guaradar los costes para el ultimo paso de Hungarian
        int[][] matriz_costes = new int[final_size][final_size];
        for (int i = 0; i < matriz.length; i++) {
            matriz_costes[i] = Arrays.copyOf(matriz[i], matriz[i].length);
        }

        marcaInFila = new int[matriz.length];
        marcaInCol = new int[matriz[0].length];
        filaCubierta = new int[matriz.length];     // indica una fila cubierta
        colCubierta = new int[matriz[0].length];   // indica una columna cubierta
        zerosInFila = new int[matriz.length];
        //Inicializacion
        Arrays.fill(zerosInFila, -1);
        Arrays.fill(marcaInFila, -1);
        Arrays.fill(marcaInCol, -1);


        restar(matriz);
        //Final fase de preproceso

        //Inicio Fase Itirativa
        marcar_zeros(final_size, matriz);
        marcarColumnas();


        while (!columnasCubiertas()) {

            int[] mainZero = zero_sin_cubrir(matriz, final_size);
            while(mainZero == null){
                int minimo = MinimoNoCubierto(matriz, final_size);
                if ( minimo > 0 ) Modificacion_Matriz(matriz, minimo, final_size);
                mainZero = zero_sin_cubrir(matriz, final_size);
            }
            if (marcaInFila[mainZero[0]] == -1) {
                // no hay marca en mainZero
                marcarRestante(mainZero);
                marcarColumnas();    // cubrir columanas que contengan un zero marcado
            } else {

                filaCubierta[mainZero[0]] = 1;  // cubrir fila de mainZero
                colCubierta[marcaInFila[mainZero[0]]] = 0;  // dejar de cubrir col de mainZero
                int minimo = MinimoNoCubierto(matriz, final_size);
                if ( minimo > 0 ) Modificacion_Matriz(matriz, minimo, final_size);
            }
        }
        //Final Fase Iterativa

        //Calculo y Return Coste

        int coste = 0;
        for ( int i = 0; i < final_size; ++i) {
            coste += matriz_costes[marcaInCol[i]][i];
        }
        return coste;

    }

    /* Función que se encargara de restar ek maximo de cada fila a esta misma,
        y de restar el maximo de cada columna a esta misma
     */
    private static void restar(int[][] matriz) {
        //restar minimo a las filas
        for (int i = 0; i < matriz.length; ++i) {
            int num_min = Integer.MAX_VALUE;
            boolean hay_zero = false;
            //Consigo el numero mas pequeño de la fila, si no hay ningun 0 en la fila
            for (int j = 0; j < matriz[i].length && !hay_zero; ++j) {
                if (matriz[i][j] == 0) hay_zero = true;
                else if (matriz[i][j] < num_min) num_min = matriz[i][j];
            }
            //Resto el numero mas pequeño de la fila a todos los elementos de esta
            if (!hay_zero) {
                for (int j = 0; j < matriz[i].length; ++j) {
                    matriz[i][j] -= num_min;
                }
            }
        }
        //restar minimo a las columnas
        for (int i = 0; i < matriz.length; ++i) {
            int num_min = Integer.MAX_VALUE;
            boolean hay_zero = false;
            //Consigo el numero mas pequeño de la columna
            for (int j = 0; j < matriz[i].length && !hay_zero; ++j) {
                if (matriz[j][i] == 0) hay_zero = true;
                else if (matriz[j][i] < num_min) num_min = matriz[j][i];
            }
            //Resto el numero mas pequeño de la columna a todos los elementos de esta
            if (!hay_zero) {
                for (int j = 0; j < matriz[i].length; ++j) {
                    matriz[j][i] -= num_min;
                }
            }
        }
    }

    /* Normalizar se encargar de convertir matriz que no es cuadrada en cuadrada

     */
    private static int[][] normalizar(int[][] matriz, int final_size, int num_fila, int num_columna) {

        //Hacemos una matriz vacia pero CUADRADA
        int[][] matriz_resultado = new int[final_size][final_size];
        //Encontramos el maximo de la matrix ORIGINAL
        int maximo = encontrarMaximo(matriz);
        //Copiamos su contenido
        for (int i = 0; i < matriz.length; ++i) {
            System.arraycopy(matriz[i], 0, matriz_resultado[i], 0, matriz[i].length);
        }

        //Ponemos el valor maximo a la parte ampliada de la nueva matriz
        for (int i = num_fila; i < final_size; i++) {
            for (int j = 0; j < final_size; j++) {
                matriz_resultado[i][j] = maximo;
            }
        }

        for (int j = num_columna; j < final_size; j++) {
            for (int i = 0; i < final_size; i++) {
                matriz_resultado[i][j] = maximo;
            }
        }
        return matriz_resultado;

    }
    /* Se encargara marcar os zeros, si no hay otros zeros marcados

     */
    private void marcar_zeros(int size, int[][] matriz) {
        int[] fila_marcada = new int[size];
        int[] col_marcada = new int[size];

        for ( int i = 0; i < size; ++i){
            for ( int j = 0; j < size; ++j){
                if ( matriz[i][j] == 0 && fila_marcada[i] == 0 && col_marcada[j] == 0) {
                    fila_marcada[i] = 1;
                    col_marcada[j] = 1;
                    marcaInFila[i] = j;
                    marcaInCol[j] = i;
                }
            }
        }
    }

    /* Funcion que se encargara de devolver un boolean dependiendo si estan cubiertas
        todas las columnas o no
     */
    private boolean columnasCubiertas() {
        for (int i : colCubierta) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    /* Marcar las columnas que tienen su zero asignado

     */
    private void marcarColumnas() {
        for (int i = 0; i < marcaInCol.length; i++) {
            if ( marcaInCol[i] != -1 ) colCubierta[i] = 1;
            else colCubierta[i] = 0;
        }
    }

    /* que se encargara de localizar los zeros no cubiertos y meterlos en el vector las coordenadas i, j, y marcarlo en zerosInFilaque
     se encargara de localizar los zeros no cubiertos y meterlos en el vector las coordenadas i, j, y marcarlo en zerosInFila
     */
    private int[] zero_sin_cubrir(int[][] matriz, int size) {
        for (int i = 0; i < size; i++) {
            if (filaCubierta[i] == 0) {
                for (int j = 0; j < size; j++) {
                    if (matriz[i][j] == 0 && colCubierta[j] == 0) {
                        zerosInFila[i] = j;
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }


    /*
        Funcion que se encarga de obtener el maximo valor de una matriz
     */
    private static int encontrarMaximo(int[][] matriz) {
        int maximo = Integer.MIN_VALUE;
        for (int i = 0; i < matriz.length; ++i) {
            for (int j = 0; j < matriz[i].length; ++j) {
                if (matriz[i][j] > maximo) {
                    maximo = matriz[i][j];
                }
            }
        }
        return maximo;
    }

    /*
        Funcion que se encarga de encontar el valor minimo de las celdas
        que no estan marcadas por ninguna de sus columnas y filas
     */
    private int MinimoNoCubierto(int[][] m, int size) {
        int minimo = Integer.MAX_VALUE;
        for (int i = 0; i < size; ++i) {
            if (filaCubierta[i] == 1){
                continue;
            }
            for (int j = 0; j < size; ++j) {
                if(colCubierta[j] == 0 && m[i][j] < minimo) minimo = m[i][j];
            }
        }
        return minimo;
    }

    /*
        Funcion que se encarga de marcar 
     */
    private void marcarRestante (int[] mainZero) {
        int i = mainZero[0];
        int j = mainZero[1];

        Set<int[]> cadena = new LinkedHashSet<>();

        cadena.add(mainZero);
        boolean found = false;
        do {
            if (marcaInCol[j] != -1) {
                cadena.add(new int[]{marcaInCol[j], j});
                found = true;
            } else {
                found = false;
            }


            if (!found) {
                break;
            }

            i = marcaInCol[j];
            j = zerosInFila[i];

            if (j != -1) {
                cadena.add(new int[]{i, j});
                found = true;
            } else {
                found = false;
            }

        } while (found);


        for (int[] zero : cadena) {

            if (marcaInCol[zero[1]] == zero[0]) {
                marcaInCol[zero[1]] = -1;
                marcaInFila[zero[0]] = -1;
            }

            if (zerosInFila[zero[0]] == zero[1]) {
                marcaInFila[zero[0]] = zero[1];
                marcaInCol[zero[1]] = zero[0];
            }
        }

        Arrays.fill(zerosInFila, -1);
        Arrays.fill(filaCubierta, 0);
        Arrays.fill(colCubierta, 0);
    }


    /*
        Funcion encargada de restar un numero a las celdas en las que sus columnas y filas no estan marcadas
        y sumarle el minimo en las columnas y filas que estan marcadas ambas
     */
    private void Modificacion_Matriz(int[][] matriz,  int min, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (filaCubierta[i] == 1 && colCubierta[j] == 1) {
                    // Añadir min a las celdas marcadas en fila y columna
                    matriz[i][j] += min;
                } else if (filaCubierta[i] == 0 && colCubierta[j] == 0) {
                    //Restar min a todas la celdas no cubiertas
                    matriz[i][j] -= min;
                }
            }
        }
    }

}