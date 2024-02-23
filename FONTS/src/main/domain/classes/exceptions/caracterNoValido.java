package main.domain.classes.exceptions;

public class caracterNoValido extends Exception{
    public caracterNoValido(String c) {
        super("El caracter " + c + " no es válido");
    }

    public caracterNoValido() {
        super("El caracter no es válido");
    }

    public caracterNoValido(char character) {
    }
}
