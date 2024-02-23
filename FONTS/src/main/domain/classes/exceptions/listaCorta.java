package main.domain.classes.exceptions;

/**
 * Excepcion para cuando la lista de palabras es demasiado corta
 * @author Rafael Ibáñez Rodríguez
 */
public class listaCorta extends Exception{
    public listaCorta(int k) {
        super("La lista de palabras tiene " + k + " palabras únicas" + "\n, se necesitan 20");
    }

    public listaCorta() {
        super("La lista de palabras tiene menos de 20 palabras únicas");
    }


}
