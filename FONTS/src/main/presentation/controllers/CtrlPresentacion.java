package main.presentation.controllers;

import main.domain.classes.exceptions.*;
import main.domain.controllers.CtrlDominio;
import main.persistence.CtrlPersistencia;
import main.presentation.views.*;
import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * El controlador de presentación que se encargara de la comunicación entre vistas y la capa de presentacion
 * Tambien es el que envia a la capa de presentacion los datos de las capas inferiores
 * @author Luis Jesús Valverde Zavaleta
 */
public class CtrlPresentacion {

    private static CtrlDominio cd = null;

    /**
     * Creadora de la clasde CtrlPresentacion
     */
    public CtrlPresentacion(){
        cd = new CtrlDominio();
    }

    /**
     * Inicializamos los alfabetos y llamamos a iniPresentacion
     */
    public static void firstIniPresenacion(){
        cd.crearAlfabeto();
        iniPresentacion();
    }

    public static char[] getAlfabeto(String nomAlfabeto){
        return cd.getAlfabeto(nomAlfabeto);
    }

    /**
     * Ventana que muestra el menú principal
     */
    public static void iniPresentacion(){
        VistaMenuPrincipal vMP = new VistaMenuPrincipal();
    }

    /**
     * Ventana que muestra la pantalla de creación de un teclado
     */
    public static void vistaCreacionTeclado(){ VistaCrearTeclado cCT = new VistaCrearTeclado();}

    /**
     * Función que se encarga de crear un teclado con los parametros que le pasamos, se comunica con la capa de dominio
     * para su creación
     * @param NomTeclado
     * @param filas
     * @param columnas
     * @param nomAlfabeto
     * @param alg Algoritomo a utilizar
     * @param ListaPalabras
     * @throws tecladoExistente
     * @throws alfabetoErroneo
     */
    public static void crearTecladoPresentacion(String NomTeclado, int filas, int columnas, String alg, String nomAlfabeto, String ListaPalabras) throws tecladoExistente, alfabetoErroneo, listaNoExiste {
        cd.crearTeclado(NomTeclado,filas,columnas,alg, nomAlfabeto, ListaPalabras);
    }


    /**
     * Ventana que muestra el teclado con nombre "teclado" por pantalla
     * @param teclado TreeMap que contiene la distribución del teclado
     */
    public static void vistaVisualizarTeclado(TreeMap<Integer,Character> teclado){ VistaVisualizarTeclado vVT = new VistaVisualizarTeclado(teclado);}

    /**
     * Ventana que muestra la diversas acciones a realizar con los teclados como eliminar, crear, renombrar, etc
     */
    public static void vistaGestionarTeclados(){ VistaGestionarTeclados vGT = new VistaGestionarTeclados();}

    /**
     * Ventana que hace de transición a la visualización del teclado que seleccionarás
     */
    public static void vistaAbrirTeclado(){ VistaAbrirTeclado vAT = new VistaAbrirTeclado();}

    /**
     * Ventana que se encarga de la eliminación de un teclado
     */
    public static void vistaEliminarTeclado(){ VistaEliminarTeclado vET = new VistaEliminarTeclado();}

    /**
     * Ventana que se encarga de renombrar un teclado
     */
    public static void vistaCambiarNombreTeclado(){ VistaCambiarNombreTeclado vCNT = new VistaCambiarNombreTeclado();}

    /**
     * Ventana que muestra las opciones de creacion de una lista
     */
    public static void vistaCrearLista(){VistaCrearLista vGL = new VistaCrearLista();}

    /**
     * Ventana que te permite eliminar una lista existente en el sistema
     */
    public static void vistaEliminarLista(){VistaEliminarLista vGL = new VistaEliminarLista();}


    /**
     * Ventana que permite editar una lista
     */
    public static void vistaEditarLista(){VistaEditarLista vEL = new VistaEditarLista();}

    /**
     * Función que cambiará el nombre del teclado con nombre = nombre_viejo mediante la comunicación con la capa de dominio
     * @param nombre_viejo
     * @param nombre_nuevo
     * @throws tecladoExistente
     */
    public static void cambioNombreTeclado(String nombre_viejo, String nombre_nuevo) throws tecladoExistente {
        cd.cambiarNombreTeclado(nombre_viejo,nombre_nuevo);
    }

    /**
     * Función que devuelve un booleano si el teclado con nomTeclado ha sido elminiado del sistema, se comunica con la
     * capa de dominio para su elminación
     * @param nomTeclado
     * @return boolean, true si se borra, false si no se borra
     */
    public static boolean eliminarTeclado(String nomTeclado) {
        return cd.eliminaTeclado(nomTeclado);
    }


    /**
     * Función que devuelve un booleano si la lista de plabras con nomLista ha sido elminiado del sistema, se comunica con la
     * capa de dominio para su elminación
     * @param nomLista
     * @return boolean, true si se borra, false si no se borra
     */
    public static boolean eliminarLista(String nomLista) {
        return cd.eliminarLista(nomLista);
    }

    /**
     * Función que devuelve una lista de todos los teclados de nuestro sistema, se comunica con la capa de dominio para su
     * obtención
     * @return lista con los teclados del sistema
     */
    public static List<String> getTeclados() {
        return cd.teclados();
    }

    /**
     * Función que devuelve una lista de todas las listas de plabras de nuestro sistema, se comunica con la capa de
     * dominio para suobtención
     * @return una lista de las listas de palabras
     */
    public static List<String> getListaPalabras() {
       return cd.listas();
    }

    /**
     * Ventana que muestra la interfaz para gestionar las lista de plabras del sistema
     */
    public static void vistaGestionListas() {
        VistaGestionListas vGL = new VistaGestionListas();
    }

    /**
     * Ventana que permite la creación de una lista desde interfaz
     */
    public static void vistaCrearListaManualmente() {
        VistaCrearListaManualmente vCLM = new VistaCrearListaManualmente();
    }

    /**
     * Ventana que permite crear una lista mediante la introducción de un texto
     */
    public static void vistaCrearListaTexto() {
        VistaCrearLIstaTexto vCLT = new VistaCrearLIstaTexto();
    }

    /**
     * Función que crea una lista de palabras por via lista de plabras mediante la comunicación con la capa de dominio
     * @param nomLista
     * @param freq
     * @throws listaCorta
     * @throws listaExistente
     */
    public static void crearListaMapa(String nomLista, Map<String, Integer> freq, String nomA) throws listaCorta, listaExistente {
        cd.crearListaPalabras(nomLista, freq, nomA);
    }

    /**
     * Función que crea una lista de palabras por via texto mediante la comunicación con la capa de dominio
     * @param nomLista
     * @param texto
     * @throws listaCorta
     * @throws listaExistente
     */
    public static void crearListaTexto(String nomLista, String texto, String nomA) throws listaCorta, listaExistente {
        cd.crearListaPalabras(nomLista, texto, nomA);
    }

    /**
     * Función que se encarga de cambiar el nombre de una lista de palabras "nombreViejo" a "nombreNuevo" se realiza mediante
     * la comunicación con la capa de dominio
     * @param nombreNuevo
     * @param nombreViejo
     * @throws listaNoExiste
     */
    public static void cambiarNombreLista(String nombreNuevo, String nombreViejo) throws listaNoExiste, listaExistente {
        cd.cambiarNombreLista(nombreViejo, nombreNuevo);
    }

    /**
     * Ventana que permite y muestra la opción de cambiar el nombre a una lista
     */
    public static void vistaCambiarNombreLista() {
        VistaCambiarNombreLista vCNL = new VistaCambiarNombreLista();
    }

    /**
     * Ventana que permite modificar la frecuencia de plabras de una lista
     */
    public static void vistaModificarFrecuencia() {
        VistaModificarFrecuencia vMF = new VistaModificarFrecuencia();
    }

    /**
     * Comunicación con la capa de dominio para el cambio de frecuencia de una palabra en una lista
     * @param nombre
     * @param palabra
     * @param frecuencia
     * @return
     * @throws listaNoExiste
     */
    public static int modificarFrecuencia(String nombre, String palabra, String frecuencia) throws listaNoExiste {
        return cd.modificarPalabra(nombre, palabra, Integer.parseInt(frecuencia));
    }




    /**
     * Función que obtiene un teclado con nombre "text" mediante la capa de dominio
     * @param text
     * @return
     */
    public static TreeMap<Integer,Character> getTeclado(String text){
       return  cd.ObtenerTeclado(text);
    }

    /**
     * Ventana que permite añadir una palabra a una lista
     */
    public static void vistaAnadirPalabra() {
        VistaAnadirPalabra vAP = new VistaAnadirPalabra();
    }

    /**
     * Ventana que elimina una palabra de una lista
     */
    public static void vistaEliminarPalabra() {
        VistaEliminarPalabra vEP = new VistaEliminarPalabra();
    }

    /**
     * Función que eimina una palabra de una lista mediante la comunicación con la capa de dominio
     * @param nombre
     * @param palabra
     * @return
     * @throws listaNoExiste
     */
    public static int eliminarPalabra(String nombre, String palabra) throws listaNoExiste {
        return cd.eliminarPalabra(nombre, palabra);
    }

    /**
     * Función que añade una palabra a una lista de plabras mediante la comunicación con la capa de dominio
     * @param nombre
     * @param palabra
     * @param frecuencia
     * @return
     * @throws listaNoExiste
     */
    public static int anadirPalabra(String nombre, String palabra, String frecuencia) throws listaNoExiste {
        return cd.anadirPalabra(nombre, palabra, Integer.parseInt(frecuencia));
    }
}
