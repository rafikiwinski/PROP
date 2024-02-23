package main.domain.classes.exceptions;

/**
 * Clase de excepcion de alfabeto erroneo
 * @author Rafael Ibáñez Rodríguez
 */
public class alfabetoErroneo extends Exception{
    public alfabetoErroneo(String name, String namelista){
        super("El alfabeto "+name+" no es el mismo que el de la lista de palabras"+namelista);

    }

    public alfabetoErroneo() {
        super("El alfabeto no es compatible con la lista de palabras");
    }
}
