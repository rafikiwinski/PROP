package main.domain.classes;

import java.io.*;
import java.util.*;

/*
 * Clase teclado
 * @autor Luis Jesús Valverde Zavaleta
 */
public class Teclado {
    private String nameTeclado;
    private int numeroTeclas;
    private int numFilas;

    TreeMap<Integer,Character> teclado;

    //Constructoras

    /**
     * Constructora por defecto
     */
    public Teclado(){
        this.nameTeclado = "";
        this.numeroTeclas = 0;
        this.numFilas = 0;
    }
    //Constructora por parametros
    //Precondicion = filas > 0. columanas > 0, nTeclas > 0

    /**
     * Constructora por parametros
     * @param nombre nombre del teclado
     * @param filas numero de filas del teclado
     * @param columnas numero de columnas del teclado
     * @param nTeclas numero de teclas del teclado
     */
    public Teclado(String nombre, int filas, int columnas, int nTeclas){
        this.nameTeclado = nombre;
        this.numeroTeclas = nTeclas;
        this.numFilas = filas;
    }

    public Teclado(Teclado teclado) {
        this.teclado = teclado.teclado;
    }

    public Teclado(String nombre, int filas, int columnas, int nTeclas, TreeMap<Integer, Character> aux){
        this.nameTeclado = nombre;
        this.numeroTeclas = nTeclas;
        this.numFilas = filas;
        this.teclado = aux;
    }


    //Getters

    /**
     * Consultora del nombre del Teclado
     * @return nombre del teclado
     */
    public String getNameTeclado() {
        return nameTeclado;
    }

    /**
     * Consultora del numero de teclas del Teclado
     * @return numero de teclas del teclado
     */
    public int getNumeroTeclas() {
        return numeroTeclas;
    }



    /**
     * Consultora del numero de Filas del Teclado
     * @return numero de filas del teclado
     */
    public int getNumFilas(){
        return numFilas;
    }

    //Setters
    /**
     * Setter que permite el cambio del nombre del teclado
     * @param nombre nombre del teclado
     */
    public void cambiarNombreTeclado(String nombre){
        this.nameTeclado = nombre;
    }




    public void imprime_Teclado() {
        int i = 0;
        for (HashMap.Entry<Integer, Character> it : teclado.entrySet()) {
            System.out.print(it.getValue() + " ");
            ++i;
            if (i == 9) {
                System.out.println();
                i = 0;
            }
        }
    }

    public void setTeclado(TreeMap<Integer, Character> dist) {
        this.teclado= dist;
        //imprime_Teclado();
    }
}
