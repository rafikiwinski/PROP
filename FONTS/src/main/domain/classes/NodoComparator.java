package main.domain.classes;

import java.util.Comparator;

/**
 Clase  utilizada para ordenar el priority queue
de Nodos.
 @author Iván Parreño Benítez
 */
public class NodoComparator implements Comparator<Nodo> {
    public int compare(Nodo n1, Nodo n2){
        if(n1.valor_cota() <= n2.valor_cota()) {
            return  1;
        }else if(n1.valor_cota() > n2.valor_cota()) {
            return -1;
        }
        return 0;
    }
}
