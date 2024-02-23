package main.domain.controllers;

import main.domain.classes.*;

import java.util.TreeMap;

public class CtrlTeclado {
    private Teclado teclado;

    /**
     * Creadora del controlador
     */
    public CtrlTeclado() {
        teclado = null;
    }

    /**
     * Consultora del teclado activo
     * @return teclado activo
     */
    public Teclado getTecladoActivo() {
        return teclado;
    }

    public String getNombreteclado(){
        return teclado.getNameTeclado();
    }

    public void getNombreTecladoActivo() {
        System.out.println(teclado.getNameTeclado());
    }

    public void crearTeclado(String nombreT, int nfilas, int ncolumnas, int nteclas){
            teclado = new Teclado(nombreT, nfilas, ncolumnas, nteclas);
    }

    public void cambiarNombreTeclado(String nombre){
        teclado.cambiarNombreTeclado(nombre);

    }
    public void cerrarTeclado(){
        teclado = null;
    }

    public void distribuir_Teclado(TreeMap<Integer,Character> Dist, String nameT) {
        teclado = new Teclado();
        teclado.setTeclado(Dist);
    }

    public void abrirTeclado(String nombre, int filas, int columnas, int nTeclas, TreeMap<Integer, Character> aux) {
        Teclado t = new Teclado(nombre, filas, columnas, nTeclas, aux);
        teclado = t;
        //teclado.imprime_Teclado();;
    }

    public void imprimirTeclado() {
        teclado.imprime_Teclado();
    }

    public Teclado getTeclado() {
        return teclado;
    }
}