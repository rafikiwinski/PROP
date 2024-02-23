package main.persistence;

import main.domain.classes.exceptions.tecladoExistente;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class CtrlPersistencia {
    /**
     * Singleton Pattern
     */
    private static CtrlPersistencia instance = null;
    /**
     * Gestores de persistencia
     */
    private GestorAlfabetos gestorAlfabetos;
    private GestorListas gestorListas;
    private GestorTeclados gestorTeclados;

    /**
     * Constructora publica
     */
    public CtrlPersistencia() {
        gestorListas = GestorListas.getInstance();
        gestorAlfabetos = GestorAlfabetos.getInstance();
        gestorTeclados = GestorTeclados.getInstance();
    }


    /**
     * Singleton Pattern
     * @return instancia de CtrlPersistencia
     */
    public static CtrlPersistencia getInstance() {
        if (instance == null) instance = new CtrlPersistencia();
        return instance;
    }

    /**
     * Funció que guarda una lista de palabras
     * @param lista nombre de la lista
     * @param freq frecuencias de la lista
     * @param alfabeto alfabeto de la lista
     */
    public void addLista(String lista, Map<String, Integer> freq, String alfabeto) {
        gestorListas.addLista(lista, freq, alfabeto);

    }

    /**
     * Función que retorna el alfabeto de una lista
     * @param list nombre de la lista
     * @return alfabeto de la lista
     */
    public String get_Alfabeto_list(String list){
        return gestorListas.getAlfabeto_List(list);
    }

    /**
     * Función que borra una lista
     * @param lista nombre de la lista
     * @return true si se ha borrado, false si no
     */
    public boolean removeLista(String lista) {
         return gestorListas.removeLista(lista);
    }

    /**
     * Función que retorna una lista con las listas de palabras
     * @return lista de listas de palabras
     */
    public List<String> getListas() {
        return gestorListas.getListasPalabras();
    }

    /**
     * Función que retorna la frecuencia de una lista
     * @param lista nombre de la lista
     * @return frecuencia de la lista
     */
    public Map<String, Integer> getFreq(String lista) {
        return gestorListas.getListaFrecuencias(lista);
    }

    /**
     * Función que comprueba si existe una lista
     * @param lista
     * @return 1 si existe, null si no
     */
    public Integer checkLista(String lista){
        List<String> l = getListas();
        if(l.contains(lista)) return 1;
        else return null;
    }

    /**
     * Función que retorna una lista con los alfabetos disponibles
     * @return lista de alfabetos
     */
    public List<String> getAlfabetos() {
        return gestorAlfabetos.getAlfabetos();
    }

    /**
     * Función que imprime los alfabetos
     */
    public void printAlfabetos(){
        for(String s : gestorAlfabetos.getAlfabetos()){
            System.out.println(s);
        }
    }

    /**
     * Función que retorna el alfabeto
     * @param alfabeto nombre del alfabeto
     * @return alfabeto
     */
    public char[] getAlfabeto(String alfabeto) {
        return gestorAlfabetos.getAlfabeto(alfabeto);
    }

    /**
     * Función que devuelve un teclado
     * @param nom nombre del teclado
     * @return teclado
     */
    public TreeMap<Integer, Character> getTeclado(String nom){
        return gestorTeclados.getTeclado(nom);
    }

    /**
     * Función que imprime un teclado
     * @param teclado
     */
    public void printTeclado(String teclado) {
        gestorTeclados.printTeclado(teclado);
    }

    /**
     * Función que carga los teclados guardados al principio de la ejecución
     */
    public void guardar(){
        gestorTeclados.guarda();
    }

    /**
     * Función que comprueba si existe un teclado
     * @param nom nombre del teclado
     * @return true si existe, false si no
     */
    public boolean existe_teclado(String nom){
        return gestorTeclados.comprueba_existencia(nom);
    }

    /**
     * Función que añade un teclado
     * @param nom_teclado nombre del teclado
     * @param tm distribución del teclado
     * @throws tecladoExistente excepción lanzada si el teclado ya existe
     */
    public void addTeclado(String nom_teclado, TreeMap<Integer, Character> tm) throws tecladoExistente {
        gestorTeclados.addTeclado(nom_teclado, tm);
    }

    /**
     * Función que retorna una lista con los teclados
     * @return lista de teclados
     */
    public List<String> getTeclados(){
        return gestorTeclados.getTeclados();
    }

    /**
     * Función que cambia el nombre de un teclado
     * @param antes nombre del teclado antes del cambio
     * @param despues nombre del teclado después del cambio
     * @throws tecladoExistente excepción lanzada si el teclado ya existe
     */
    public void cambiarnombreteclado(String antes, String despues) throws tecladoExistente{
        gestorTeclados.cambiarnombreteclados(antes, despues);
    }

    /**
     * Función que borra un teclado
     * @param nom_Teclado nombre del teclado
     * @return true si se ha borrado, false si no
     */
    public boolean removeTeclado(String nom_Teclado){
        return gestorTeclados.removeTeclado(nom_Teclado);
    }

}
