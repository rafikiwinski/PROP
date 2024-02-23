package main.domain.classes.exceptions;

/**
 * Funcion: Excepcion que salta cuando se intenta crear una lista que ya existe
 * @author Rafael Ibáñez Rodríguez
 */
public class listaExistente extends Exception{
    public listaExistente(String  name) {
        super("La lista de palabras " + name + " ya existe");
    }

    public listaExistente() {
        super("Ya existe una lista de palabras con ese nombre");
    }
}
