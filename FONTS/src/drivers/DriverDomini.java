package drivers;

import main.domain.classes.*;
import main.domain.controllers.*;
import main.domain.classes.exceptions.*;
import main.domain.classes.exceptions.listaCorta;

import java.util.*;

public class DriverDomini {
    /**
     * Controlador del dominio
     */
    private CtrlDominio CD = null;
    /**
     * Scanner para leer por teclado
     */
    private Scanner lector = null;

    /**
     * Función que lista las opciones disponibles desde
     * el driver del dominio.
     */
    private static void listarOpciones() {
        System.out.println("(1 o constructora) - Constructora del Driver");
        System.out.println("(2 o crearTeclado) - Crear un Teclado");
        System.out.println("(3 o crearListadePalabras) - Crear una lista de Palabras");
        System.out.println("(4 o cambiarNombreTeclado) - Cambiar el nombre del Teclado Activo");
        System.out.println("(5 o abrirTeclado) - Abrir el teclado");
        System.out.println("(6 o consultarPalabra) - Consultar Palabra de una lista");
        System.out.println("(7 o imprimirTeclado) - Guardar el teclado");
        System.out.println("(8 o borrarTeclado) - Borrar el Teclado Activo");
        System.out.println("(9 o cerrarTeclado) - Cerrar el teclado Activo");
        System.out.println("(10 o eliminarLista) - Eliminar una lista");
        System.out.println("(11 o modificarPalabra) - Modificar Palabra de una lista");
        System.out.println("(12 o anadirPalabra) - Añadir Palabra de una lista");
        System.out.println("(13 o eliminarPalabra) - Eliminar Palabra de una lista");
        System.out.println("(14 o cambiarNombreLista) - Cambiar el nombre de una lista");
        System.out.println("(15 o imprimeAlfabetos) - Imprime Alfabetos existentes");
        System.out.println("(16 o imprimeLista) - Imprime Listas");
        System.out.println("----------------");
        System.out.println("(0 o salir) - Cerrar Aplicación");

    }

    /**
     * Función principal del driver del dominio
     * @param args entrada del main
     * @throws Exception en caso de error
     */
    public static void main(String[] args) throws Exception {
        DriverDomini dd = new DriverDomini();
        dd.testConstructora();
        dd.crearAlfabeto();

        System.out.println("Driver del Dominio");
        System.out.println(" ");
        listarOpciones();
        dd.lector = new Scanner(System.in);
        String input = dd.lector.nextLine();
        while (!input.equals("0") && !input.equals("salir")) {
            switch (input) {
                case "1":
                case "constructora": {
                    dd.testConstructora();
                    break;
                }
                case "2":
                case "crearTeclado": {
                    dd.testCrearTeclado();
                    break;
                }
                case "3":
                case "crearListadePalabras":{
                    dd.testcrearListadePalabras();
                    break;
                }
                case "4":
                case "cambiarNombreTeclado": {
                    dd.testcambiarNombreTeclado();
                    break;
                }
                case "5":
                case "abrirTeclado": {
                    dd.testAbrirTeclado();
                    break;
                }
                case "6":
                case "consultarPalabra": {
                    dd.testConsultarPalabra();
                    break;
                }
                case "7":
                case "imprimirTeclado": {
                    dd.imprimirTeclado();
                    break;
                }
                case "8":
                case "borrarTeclado": {
                    dd.borrarTeclado();
                    break;
                }
                case "9":
                case "cerrarTeclado": {
                    dd.testCerrarTeclado();
                    break;
                }
                case "10":
                case "eliminarLista": {
                    dd.testEliminarLista();
                    break;
                }
                case "11":
                case "modificarPalabra": {
                    dd.testModificarPalabra();
                    break;
                }
                case "12":
                case "anadirPalabra": {
                    dd.testAnadirPalabra();
                    break;
                }

                case "13":
                case "eliminarPalabra": {
                    dd.testEliminarPalabra();
                    break;
                }
                case "14":
                case "cambiarNombreLista": {
                    dd.testCambiarNombreLista();
                    break;
                }
                case "16":
                case "imprimeListas":{
                    dd.testImprimeListas();
                    break;
                }

                case "15":
                case "imprimeAlfabetos": {
                    dd.testImprimeAlfabetos();
                    break;
                }
            }
            dd.volverAlMenu();
            listarOpciones();
            input = dd.lector.nextLine();
        }
        dd.lector.close();

    }

    private void testImprimeAlfabetos() {
        CD.imprimeAlfabetos();
    }

    private void borrarTeclado() throws noHayTecladosCreados{
        if(CD == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Estos son los teclados existentes: ");
        CD.imprimirTodosTeclados();
        System.out.println("Indica el nombre teclado quieres eliminar: ");
        String name = lector.nextLine();
        CD.eliminaTeclado(name);
    }

    private void imprimirTeclado() throws noHayTecladosCreados {
        if(CD == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Estos son los teclados existentes: ");
        CD.imprimirTodosTeclados();
        System.out.println("Indica el nombre teclado quieres ver: ");
        String name = lector.nextLine();
        CD.imprimeTeclado(name);
    }

    private void imprimeAlfabetos() {
        CD.imprimeAlfabetos();
    }

    private void crearAlfabeto() {
        CD.crearAlfabeto();
    }

    /**
     * Función inicializadora del controlador del dominio
     */
    public void testConstructora(){
        CD = new CtrlDominio();
    }

    /**
     * Función que crea un teclado
     */
    public void testCrearTeclado() throws tecladoExistente, alfabetoErroneo, listaNoExiste {
        if(CD == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }


        System.out.println("Nombre del nuevo teclado: ");
        String nombreT = lector.nextLine();

        System.out.println("Aquí tienes todas las listas. Introduce el nombre de la lista que quieras utilizar: ");
        CD.imprimirTodasListas();
        System.out.println("-----------------------------------");
        String nombreLista = lector.nextLine();

        System.out.println("Nombre del Alfabeto: ");
        CD.imprimeAlfabetos();
        System.out.println("-----------------------------------");
        String nombreA = lector.nextLine();

        System.out.println("Nombre del Algoritmo: ");
        System.out.println("1-QAP");
        System.out.println("2-Genetic");
        System.out.println("-----------------------------------");
        String nombreAlgoritmo= lector.nextLine();
        CD.crearTeclado(nombreT, 3, 10,nombreAlgoritmo, nombreA, nombreLista);
    }

    /**
     * Función que crea una lista de palabras
     */
    public void testcrearListadePalabras() throws listaCorta, listaExistente {
        if(CD == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }

        System.out.println("Como quieres entrar la lista de palabras:");
        System.out.println("1 - Palabra a Palabra");
        System.out.println("2 - A partir de un texto");
        System.out.println("-----------------------------------");

        //Scanner reader = new Scanner(System.in);
        int input2 = lector.nextInt();

        System.out.println("Nombre de la lista a crear: ");
        //Scanner s = new Scanner(System.in);
        lector.nextLine();
        String name = lector.nextLine();

        System.out.println("Alfabetos disponibles: ");
        CD.imprimeAlfabetos();
        System.out.println("-----------------------------------");
        System.out.println("Nombre del alfabeto a usar: ");
        String alf = lector.nextLine();

        if (input2 == 1) {
            System.out.println("Has escogido entrar la lista palabra a palabra");
            System.out.println("Instrucciones");
            System.out.println("Entrada [palabra][espacio][numero] sin acentos");
            System.out.println("Para acabar Ctrl+D");
            Map<String, Integer> freq = new HashMap<>();
            Scanner lector = new Scanner(System.in);
            while(lector.hasNextLine()){
                String linea = lector.nextLine();
                String[] partes = linea.split(" ");
                int number = -1;
                try{
                    number = Integer.parseInt(partes[1]);
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
                if(!Objects.equals(partes[0], "end") && number != -1) freq.put(partes[0], number);
            }
            CD.crearListaPalabras(name, freq, alf);
        }
        else if (input2 == 2){
            System.out.println("Has Escogido entrar la lista a partir de un texto");
            System.out.println("Instrucciones");
            System.out.println("Tienes que entrar un texto sin acentos\n");
            Scanner n = new Scanner(System.in);
            String text = n.nextLine();
            CD.crearListaPalabras(name, text, alf);
        }
    }

    /**
     * Función que abre un teclado
     */
    public void testAbrirTeclado() throws noHayTecladosCreados{
        if(CD == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Todas los teclados disponibles son: ");
        CD.imprimirTodosTeclados();
        System.out.println("-----------------");
        System.out.println("Nombre del teclado a abrir: ");
        String nombreT = lector.nextLine();
        CD.abrirTeclado(nombreT);
    }

    public void testCerrarTeclado(){
        if(CD == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        CD.cerrarTeclado();
        System.out.println("Se ha cerrado el teclado activo");
    }

    /**
     * Función que cambia el nombre del teclado
     */
    public void testcambiarNombreTeclado() throws tecladoExistente{
        if(CD == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Nuevo nombre del teclado: ");
        String nombre = lector.nextLine();
        //CD.cambiarNombreTeclado(nombre);
        //System.out.println("nombre del teclado cambiado a "+ nombre );
    }

    /**
     * Función que guarda el teclado
     */

    /**
     * Función que elimina una lista
     */
    public void testEliminarLista(){
        if(CD == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Todas las listas disponibles son: ");
        CD.imprimirTodasListas();
        System.out.println("-----------------");
        System.out.println("Entra el nombre de la lista que quieres eliminar");
        String name = lector.nextLine();
        CD.eliminarLista(name);
    }

    /**
     *
     */
    private void volverAlMenu() {
        System.out.println("Prem ENTER per tornar al menu principal");
        lector.nextLine();
    }

    /**
     * Función que modifica una palabra
     */
    public void testModificarPalabra() throws listaNoExiste {
        if(CD == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Todas las listas disponibles son: ");
        CD.imprimirTodasListas();
        System.out.println("-----------------");
        System.out.println("Entra el nombre de la lista donde esta la palabra");
        String nameL = lector.nextLine();
        System.out.println("Entra la palabra que quieres modificar");
        String word = lector.nextLine();
        System.out.println("Entra la nueva frecuencia de la palabra");
        int fr = lector.nextInt();
        CD.modificarPalabra(nameL, word, fr);
    }

    /**
     * Función que añade una palabra
     */
    public void testAnadirPalabra() throws listaNoExiste {
        if(CD == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Todas las listas disponibles son: ");
        CD.imprimirTodasListas();
        System.out.println("-----------------");
        System.out.println("Entra el nombre de la lista donde quieres añadir la palabra");
        String nameL = lector.nextLine();
        System.out.println("Entra la palabra que quieres añadir");
        String word = lector.nextLine();
        System.out.println("Entra la frecuencia de la palabra");
        int fr = lector.nextInt();
        CD.anadirPalabra(nameL, word, fr);
    }

    /**
     * Función que elimina una palabra
     */
    public void testEliminarPalabra() throws listaNoExiste {
        if(CD == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Todas las listas disponibles son: ");
        CD.imprimirTodasListas();
        System.out.println("-----------------");
        System.out.println("Entra el nombre de la lista donde quieres eliminar la palabra");
        String nameL = lector.nextLine();
        System.out.println("Entra la palabra que quieres eliminar");
        String word = lector.nextLine();
        CD.eliminarPalabra(nameL, word);
    }

    /**
     * Función que cambia el nombre de una lista
     */
    public void testCambiarNombreLista() throws listaNoExiste {
        if(CD == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Todas las listas disponibles son: ");
        CD.imprimirTodasListas();
        System.out.println("-----------------");
        System.out.println("Entra el nombre de la lista que quieres cambiar");
        String nameL = lector.nextLine();
        System.out.println("Entra el nuevo nombre de la lista");
        String newNameL = lector.nextLine();
        CD.cambiarNombreLista(nameL, newNameL);
    }

    /**
     * Función que consulta una palabra
     */
    public void testConsultarPalabra() throws listaNoExiste {
        if(CD == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Todas las listas disponibles son: ");
        CD.imprimirTodasListas();
        System.out.println("-----------------");
        System.out.println("Entra el nombre de la lista donde esta la palabra");
        String nameL = lector.nextLine();
        System.out.println("Entra la palabra que quieres consultar");
        String word = lector.nextLine();
        int num = CD.consultarPalabra(nameL, word);
        if(num == -1) System.out.println("La palabra no existe");
        else System.out.println("La palabra " + word + " tiene una frecuencia de " + num);
    }

    /**
     * Función que imprime una lista
     */
    private void testImprimeListas(){
        System.out.println("Todas las listas disponibles son: ");
        CD.imprimirTodasListas();
        System.out.println("-----------------");
        System.out.println("Entra el nombre de la lista que quieres imprimir");
        String nameL = lector.nextLine();
        CD.imprimeLista(nameL);
    }
}
