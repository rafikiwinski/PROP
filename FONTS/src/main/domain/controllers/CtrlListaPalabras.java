package main.domain.controllers;

import java.util.HashMap;
import java.util.Map;
import main.domain.classes.Lista_Palabras;
import main.domain.classes.exceptions.listaExistente;


public class CtrlListaPalabras {
    private Lista_Palabras llistaActiva;
    private Map<String, Lista_Palabras> listasInstanciadas;

    //Creadoras del Controlador
    /**
     * Creadora del controlador
     */
    public CtrlListaPalabras(){
        this.llistaActiva = null;
        listasInstanciadas = new HashMap<>();
    }

    public void setLlistaActiva(Lista_Palabras list) {
        this.llistaActiva = list;
    }

    public Lista_Palabras getLlistaActiva() {
        return llistaActiva;
    }

    public Lista_Palabras getLista(String name){
        return listasInstanciadas.get(name);
    }

    //Creadoras de las listas

    /**
     * Creadora de la lista de palabras
     * @param name: nombre de la lista
     */
    public void crearListaPalabras(String name) throws listaExistente{
        if(llistaActiva != null) throw new listaExistente(llistaActiva.getName_list());
        llistaActiva = new Lista_Palabras(name);
        listasInstanciadas.put(name, llistaActiva);
    }

    /**
     * Creadora de la lista de palabras
     * @param name: nombre de la lista, texto del cual sacar la lista
     */
    public void crearListaPalabras(String name, String text, String alfabeto){
        llistaActiva = new Lista_Palabras(name, text, alfabeto);
        listasInstanciadas.put(name, llistaActiva);
    }

    /**
     * Creadora de la lista de palabras
     * @param name nombre de la lista
     */
    public void crearListaPalabras(String name, Map<String ,Integer> freq, String alf){
        llistaActiva = new Lista_Palabras(name, alf, freq);
        listasInstanciadas.put(name, llistaActiva);
    }

    /**
     * Funcion que busca la frecuencia de una palabra en la lista
     * @param word es la palabra a buscar
     * @return la frecuencia de la palabra o null si la palabra no existe
     */
    public Integer findWord(String word){
        return llistaActiva.buscaPalabra(word);
    }

    /**
     * Cambiamos la frecuencia de una palabra
     * @param word palabra a cambiar la frecuencia
     * @param n nueva frecuencia
     */
    public void modificarPalabra(String word, int n){
        llistaActiva.setWordValue(word, n);
    }

    /**
     * Función que cierra la lista activa
     */
    public void cerrarLista(){
        llistaActiva = null;
    }

    /**
     * Función que borra una palabra de la lista activa
     * @param word palabra a eliminar
     */
    public void deleteWord(String word){
        llistaActiva.borraParaula(word);
    }

    /**
     * Función que imprime la lista activa
     */
    public void imprimeListaActiva(){
        llistaActiva.printList();
    }

    public void anadirPalabra(String word, int freq){
        llistaActiva.anadePalabra(word, freq);
    }

    public void cambiarNombreLista(String nombre){
        llistaActiva.setName_list(nombre);
    }



}