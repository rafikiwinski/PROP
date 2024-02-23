package main.domain.classes.exceptions;

/**
 * Clase de excepcion de alfabeto excepcion
 * @author Rafael Ibáñez Rodríguez
 */
public class tecladoExistente extends Exception{
    public tecladoExistente(String  name) {
        super("El teclado " + name + " ya existe");
    }

    public tecladoExistente() {
        super("Ya existe un teclado con ese nombre");
    }
}
