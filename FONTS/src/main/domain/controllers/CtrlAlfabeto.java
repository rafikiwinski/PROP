package main.domain.controllers;

import main.domain.classes.Alfabeto;

import java.util.Map;
import java.util.HashMap;

public class CtrlAlfabeto {
    private Alfabeto alfabeto;

    private static Map<String, Alfabeto> alfabetoMap;
    //creadoras
    /**
     * Creadora del controlador
     */
    public CtrlAlfabeto(){
        this.alfabeto = null;
        alfabetoMap = new HashMap<>();
    }
    public Alfabeto getAlfabet(String alfabeto){
        return alfabetoMap.get(alfabeto);
    }
    // creadoras de alfabetos
    /**
     * Creadora del alfabeto
     * param: caracteres: caracteres del alfabeto, nomAlfabeto: nombre del alfabeto
     */
    public void crearAlfabeto(char[] caracteres, String nomAlfabeto) {
        alfabeto = new Alfabeto(caracteres, nomAlfabeto);
        alfabetoMap.put(nomAlfabeto, alfabeto);
    }


    // setters
    public void setAlfabet(Alfabeto alfabet) {
        this.alfabeto = alfabet;
    }

    public void cambioNombre(String nom) {
        alfabeto.cambioNombre(nom);
    }

    public void cerrarAlfabeto(){
        alfabeto = null;
    }

    public int numeroCaracteres() {
        return alfabeto.getNumCaracters();
    }

    public char getPrimera(){
        return alfabeto.getPrimera();
    }

    public void putAlfabeto(String nombre, Alfabeto alfabeto){
        alfabetoMap.put(nombre, alfabeto);
    }

    public int getAlfabetSize(String nombre){
        return alfabetoMap.get(nombre).getNumCaracters();
    }


}
