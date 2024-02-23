package main.domain.classes;

import java.io.*;
import java.util.*;

/*
 * Clase alfabeto
 * @autor Juan José Torredemer Pueyo
 */


public class Alfabeto {
    private char[] caracteres;
    private String nomAlfabet;
    private int numCaracters;
    private char primera;


    public Alfabeto(){

    }
    //funciones
    /*
     * se crea alfabeto con todos los caracteres
     */
    //creadora
      public Alfabeto(char[] caracters, String nom) {
        this.caracteres = caracters;
        this.numCaracters = caracters.length;
        this.nomAlfabet = nom;
        primera = caracters[0];
        }
     /*
     * Devuelve el vector orderer
     */
    public char[] getordererCharacters() {
        return caracteres;
    }
     /*
     * Devuelve el número de carácteres
     */
    public int getNumCaracters() {
        return numCaracters;
    }

    public void cambioNombre(String nom) {
        nomAlfabet = nom;
    }
    /*
    * Devuelve el nombre del alfabeto
    */
    public String getNomAlfabet() {
        return nomAlfabet;
    }
    //Setters
     /*
    * El nombre del alfabeto pasa a ser nomAlfabet
    */
    public void setNomAlfabet(String nomAlfabet) {
        this.nomAlfabet = nomAlfabet;
    }


    public void imprimeAlfabeto() {
        System.out.println("El alfabeto " + nomAlfabet + " tiene " + numCaracters + " carácteres.");
        for(char a : caracteres) {
            System.out.println(a + " " + (int) (a));
        }
    }

    public char getPrimera(){
        return primera;
    }
}
