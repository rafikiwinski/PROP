package main.domain.controllers;

import main.domain.classes.*;
import main.domain.classes.exceptions.*;
import main.persistence.CtrlPersistencia;

import java.util.*;

/**
 * Clase del controlador del dominio
 * @author rafael.ibanez.rodriguez
 * @author juan.jose.torredemer
 */

public class CtrlDominio {
    private CtrlListaPalabras CDLista;
    private CtrlAlfabeto CDAlfabeto;
    private CtrlTeclado CDTeclado;
    private CtrlPersistencia _ctrlPersistencia;
    private Algoritmo algoritmo;

    /**
     * Función creadora de la
     */
    public CtrlDominio(){
        CDLista = new CtrlListaPalabras();
        CDAlfabeto = new CtrlAlfabeto();
        CDTeclado = new CtrlTeclado();
        _ctrlPersistencia = CtrlPersistencia.getInstance();
        _ctrlPersistencia.guardar();
    }

    /**
     * Función que crea una Lista_Palabras
     * @param name nombre de la Lista_Palabras
     * @param freq Map que contiene las palabras con sus frecuencias
     * @param alfabeto Alfabeto al que pertenece la Lista_Palabras
     */
    public void crearListaPalabras(String name, Map<String, Integer> freq, String alfabeto) throws listaCorta, listaExistente {
        if(_ctrlPersistencia.checkLista(name) != null) throw new listaExistente(name);
        Lista_Palabras lista = new Lista_Palabras(name, alfabeto, freq);
        if(freq.size() < 20) throw new listaCorta(freq.size());
        CDLista.setLlistaActiva(lista);

        _ctrlPersistencia.addLista(name, freq, alfabeto);
    }

    /**
     * Función que crea un Teclado
     * @param nombreT nombre del Teclado
     * @param nfilas numero de filas del Teclado
     * @param ncolumnas numero de columnas del Teclado
     * @param nombreAlgoritmo nombre del Algoritmo que se usara para crear el Teclado
     * @param nombreA nombre del Alfabeto del que esta hecho el Teclado
     * @param listaPalabras nombre de la Lista_Palabras que se usara para crear el Teclado
     * @throws tecladoExistente si el teclado ya existe
     * @throws alfabetoErroneo si el alfabeto indicado y el de la lista son diferentes
     */
    public void crearTeclado(String nombreT, int nfilas, int ncolumnas, String nombreAlgoritmo,  String nombreA, String listaPalabras) throws tecladoExistente, alfabetoErroneo, listaNoExiste {
        if(getAlfabetoSize(nombreA) > 30) nfilas = 4;
        if(getAlfabetoSize(nombreA) > 40) nfilas = 5;
        if(_ctrlPersistencia.existe_teclado(nombreT)) throw new tecladoExistente(nombreT);

        TreeMap<Integer,Character> Dist = new TreeMap<>();

        Map<String, Integer> l = _ctrlPersistencia.getFreq(listaPalabras);
        if(l == null) throw new listaNoExiste(listaPalabras);
        if(!_ctrlPersistencia.get_Alfabeto_list(listaPalabras).equals(nombreA)) throw new alfabetoErroneo(nombreA, _ctrlPersistencia.get_Alfabeto_list(listaPalabras));

        Lista_Palabras lista = new Lista_Palabras(listaPalabras, nombreA, l);

        Alfabeto alfabeto = CDAlfabeto.getAlfabet(nombreA);
        Algoritmo algoritmo_nuevo = null;

        if(nombreAlgoritmo.equals("QAP")) algoritmo_nuevo= new QAP(lista,alfabeto,nfilas, ncolumnas);
        else if(nombreAlgoritmo.equals("Genetic")) algoritmo_nuevo = new Population(lista,alfabeto,nfilas, ncolumnas);
        Dist = algoritmo_nuevo.creaTeclado();

        CDTeclado.distribuir_Teclado(Dist,nombreT);
        Teclado teclado =  new Teclado(CDTeclado.getTeclado());

        teclado.imprime_Teclado();

        _ctrlPersistencia.addTeclado(nombreT, Dist);

    }

    /**
     * Función que cierra un Teclado
     */
    public void cerrarTeclado(){
        CDTeclado.cerrarTeclado();
    }

    /**
     * Función que cambia el nombre de un Teclado
     * @param nombre_antiguo el nombre antiguo de Teclado
     * @param nombre_nuevo el nombre nuevo de Teclado
     */
    public void cambiarNombreTeclado(String nombre_antiguo,String nombre_nuevo) throws tecladoExistente{
        _ctrlPersistencia.cambiarnombreteclado(nombre_antiguo,nombre_nuevo);
    }

    /**
     * Función que crea una Lista_Palabras mediante un texto
     * @param name nombre de la Lista_Palabras
     * @param text texto que pasamos con el que creamos el teclado
     * @param alfabeto alfabeto del que esta hecho Lista_Palabras
     */
    public void crearListaPalabras(String name, String text, String alfabeto) throws listaCorta, listaExistente {
        if(_ctrlPersistencia.checkLista(name) != null) throw new listaExistente(name);
        Lista_Palabras lista = new Lista_Palabras(name,text, alfabeto);
        CDLista.setLlistaActiva(lista);
        Map<String, Integer> freq = lista.getWord_list();
        if(lista.getListSize() < 20) throw new listaCorta(lista.getListSize());
        _ctrlPersistencia.addLista(name, freq, alfabeto);
    }


    /**
     * Función que elimina una Lista_Palabras
     * @param nameL nombre de la Lista_Palabras a eliminar
     */
    public boolean eliminarLista(String nameL){
        return _ctrlPersistencia.removeLista(nameL);

    }

    /**
     * Función que modifica una palabra y su frecuencia
     * @param nameL nombre de la Lista_Palabras
     * @param word palabra a añadir
     * @param fr frequencia con las que saldra la palabra word
     */
    public int modificarPalabra(String nameL, String word, int fr) throws listaNoExiste {
        Lista_Palabras l = CDLista.getLista(nameL);
        if(l == null){
            Integer c = _ctrlPersistencia.checkLista(nameL);
            System.out.print(c);
            if(c == null) throw new listaNoExiste(nameL);
            else{
                Map<String, Integer> freq = _ctrlPersistencia.getFreq(nameL);
                CDLista.crearListaPalabras(nameL, freq, _ctrlPersistencia.get_Alfabeto_list(nameL));
            }
        }
        else CDLista.setLlistaActiva(l);
        Integer n = CDLista.findWord(word);
        if(n == null){
            return -1;
        }
        else {
            String alfabeto = _ctrlPersistencia.get_Alfabeto_list(nameL);
            CDLista.modificarPalabra(word, fr);
            _ctrlPersistencia.removeLista(nameL);
            _ctrlPersistencia.addLista(nameL, CDLista.getLlistaActiva().getWord_list(), alfabeto);
            return 0;
        }
    }

    /**
     * Función que añade una palabra a una Lista_Palabras
     * @param nameL nombre de la Lista_Palabras
     * @param word palabra a añadir
     * @param fr frequencia con las que saldra la palabra word
     */
    public int anadirPalabra(String nameL, String word, int fr) throws listaNoExiste {
        Lista_Palabras l = CDLista.getLista(nameL);
        if(l == null){
            Integer c = _ctrlPersistencia.checkLista(nameL);
            if(c == null) throw new listaNoExiste(nameL);
            else{
                Map<String, Integer> freq = _ctrlPersistencia.getFreq(nameL);
                CDLista.crearListaPalabras(nameL, freq, _ctrlPersistencia.get_Alfabeto_list(nameL));
            }
        }
        else CDLista.setLlistaActiva(l);
        Integer n = CDLista.findWord(word);
        if(n != null){
            return -1;
        }
        else {
            CDLista.anadirPalabra(word, fr);
            String alfabeto = _ctrlPersistencia.get_Alfabeto_list(nameL);
            _ctrlPersistencia.removeLista(nameL);
            _ctrlPersistencia.addLista(nameL, CDLista.getLlistaActiva().getWord_list(), alfabeto);
            return 0;
        }
    }


    /**
     * Función que elimina una palabra word de la Lista_Palabras nameL
     * @param nameL nombre de la Lista_Palabras
     * @param word Palabra a eliminar de la Lista_Palabras nameL
     */
    public int eliminarPalabra(String nameL, String word) throws listaNoExiste {
        Lista_Palabras l = CDLista.getLista(nameL);
        if(l == null){
            Integer c = _ctrlPersistencia.checkLista(nameL);
            if(c == null) throw new listaNoExiste(nameL);
            else{
                Map<String, Integer> freq = _ctrlPersistencia.getFreq(nameL);
                CDLista.crearListaPalabras(nameL, freq, _ctrlPersistencia.get_Alfabeto_list(nameL));
            }
        }
        else CDLista.setLlistaActiva(l);
        Integer n = CDLista.findWord(word);
        if(n == null){
            return -1;
        }
        else {
            CDLista.deleteWord(word);
            String alfabeto = _ctrlPersistencia.get_Alfabeto_list(nameL);
            _ctrlPersistencia.removeLista(nameL);
            _ctrlPersistencia.addLista(nameL, CDLista.getLlistaActiva().getWord_list(), alfabeto);
            return 0;
        }
    }

    /**
     * Función que cambia el nombre de una lista
     * @param nameL nombre de la lista a cambiar
     * @param newName nuevo nombre de la lista
     * @throws listaNoExiste si la lista no existe
     */
    public void cambiarNombreLista(String nameL, String newName) throws listaNoExiste, listaExistente {
        Lista_Palabras l = CDLista.getLista(nameL);
        if(l == null){
            Integer c = _ctrlPersistencia.checkLista(nameL);
            if(c == null) throw new listaNoExiste(nameL);
            else{
                Map<String, Integer> freq = _ctrlPersistencia.getFreq(nameL);
                CDLista.crearListaPalabras(nameL, freq, _ctrlPersistencia.get_Alfabeto_list(nameL));
            }
        }
        else CDLista.setLlistaActiva(l);

        Lista_Palabras l1 = CDLista.getLista(newName);
        if (l1 != null) throw new listaExistente(newName);
        else if(l1 == null){
            Integer c = _ctrlPersistencia.checkLista(newName);
            if(c != null) throw new listaExistente(newName);
        }

        CDLista.cambiarNombreLista(newName);
        String alfabeto = _ctrlPersistencia.get_Alfabeto_list(nameL);
        _ctrlPersistencia.removeLista(nameL);
        _ctrlPersistencia.addLista(newName, CDLista.getLlistaActiva().getWord_list(), alfabeto);
        
    }

    /**
     * Función que busca la frecuencia de una palabra en la lista
     * @param nameL es el nombre de la lista
     * @param word es la palabra a buscar
     * @return la frecuencia de la palabra o null si la palabra no existe
     */
    public int consultarPalabra(String nameL, String word) throws listaNoExiste {
        Lista_Palabras l = CDLista.getLista(nameL);
        if(l == null){
            Integer c = _ctrlPersistencia.checkLista(nameL);
            if(c == null) throw new listaNoExiste(nameL);
            else{
                Map<String, Integer> freq = _ctrlPersistencia.getFreq(nameL);
                CDLista.crearListaPalabras(nameL, freq, _ctrlPersistencia.get_Alfabeto_list(nameL));
            }
        }
        else CDLista.setLlistaActiva(l);
        Integer num =  CDLista.findWord(word);
        if(num == null) return -1;
        else return num;
    }

    /**
     * Función que abre un teclado
     * @param nameT nombre del teclado a abrir
     */
    public void abrirTeclado(String nameT){
        TreeMap<Integer, Character> aux = _ctrlPersistencia.getTeclado(nameT);
        CDTeclado.abrirTeclado(nameT, 3, 10, 30, aux);
    }

    /**
     * Función que imprime todas las listas
     */
    public void imprimirTodasListas(){
        List<String> l = _ctrlPersistencia.getListas();
        for (String s : l) {
            System.out.println(s);
        }
    }

    /**
     * Imprimir todos teclados
     */
    public void imprimirTodosTeclados() throws noHayTecladosCreados{
        List<String> l = _ctrlPersistencia.getTeclados();
        if(l.size() == 0) throw new noHayTecladosCreados();
        for (String s : l) {
            System.out.println(s);
        }
    }

    /**
     * Función que crea un Alfabet
     */
    public void crearAlfabeto() {
        List<String> l = _ctrlPersistencia.getAlfabetos();
        for (String s : l){
            char[] alf = _ctrlPersistencia.getAlfabeto(s);
            CDAlfabeto.crearAlfabeto(alf, s);
        }
    }

    /**
     * Función que imprime un Alfabeto
     */
    public void imprimeAlfabetos() {
        _ctrlPersistencia.printAlfabetos();
    }

    /**
     * Función que imprime las palabras y su frecuencia de Lista_Palabras
     * @param nameL es el nombre de Lista_Palabra
     */
    public void imprimeLista(String nameL) {
        Map<String, Integer> freqList = _ctrlPersistencia.getFreq(nameL);
        for(Map.Entry<String,Integer> it : freqList.entrySet()) {
            System.out.println(it.getKey() + " " + it.getValue());
        }
    }


    /**
     * Función que imprime un teclado
     * @param name nombre del teclado a imprimir
     */
    public void imprimeTeclado(String name) {
        _ctrlPersistencia.printTeclado(name);
    }

    /**
     * Función que elimina un teclado
     * @param name nombre del teclado a eliminar
     */
    public boolean eliminaTeclado(String name) {
        return _ctrlPersistencia.removeTeclado(name);
    }

    /**
     * Función que devuelve una lista de los nombres de los teclados
     * @return lista de los nombres de los teclados
     */
    public List<String>teclados(){
        return _ctrlPersistencia.getTeclados();
    }

    /**
     * Función que devuelve una lista de los nombres de las listas
     * @return lista de los nombres de las listas
     */
    public List<String>listas(){
        return _ctrlPersistencia.getListas();
    }

    /**
     * Función que devuelve la distribucion de un teclado
     * @param text nombre del teclado
     * @return distribucion del teclado
     */
    public TreeMap<Integer,Character> ObtenerTeclado(String text){
        return  _ctrlPersistencia.getTeclado(text);
    }

    /**
     * Función que comprueba si existe un teclado
     * @param text nombre del teclado
     * @return true si existe, false si no
     */
    public boolean ExisteTeclado(String text){
        return _ctrlPersistencia.existe_teclado(text);
    }


    /**
     * Función que devuelve el tamaño de un alfabeto
     * @param nombreA nombre del alfabeto
     * @return tamaño del alfabeto
     */
    public int getAlfabetoSize(String nombreA) {
        return CDAlfabeto.getAlfabetSize(nombreA);
    }

    public char[] getAlfabeto(String nombreA) {
        return CDAlfabeto.getAlfabet(nombreA).getordererCharacters();
    }


}
