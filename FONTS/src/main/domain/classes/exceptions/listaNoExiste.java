package main.domain.classes.exceptions;

public class listaNoExiste extends Exception{
    public listaNoExiste(String  name) {
        super("La lista de palabras " + name + " no existe");
    }

    public listaNoExiste() {
        super("No existe una lista de palabras con ese nombre");
    }
}
