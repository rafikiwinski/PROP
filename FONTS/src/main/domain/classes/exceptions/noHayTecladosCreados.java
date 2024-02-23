package main.domain.classes.exceptions;

/**
 * Clase de excepcion si no hay teclados creados
 * @author Rafael Ibáñez Rodríguez
 */
public class noHayTecladosCreados extends Exception{
    public noHayTecladosCreados() {
        super("No hay teclados creados");
    }
}
