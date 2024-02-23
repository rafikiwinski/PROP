package drivers;

import main.domain.classes.exceptions.listaExistente;
import main.domain.controllers.CtrlListaPalabras;
import java.util.*;
public class DriverListaPalabras {
    private CtrlListaPalabras cl = null;
    private Scanner in;

    public void listarOpciones(){
        System.out.println("(1 o constructora) - Constructora del Driver");
        System.out.println("(2 o imprimirListaActiva) - Imprimir Lista Activa");
        System.out.println("(3 o crearLista) - Crear Lista");
        System.out.println("(4 o modificarPalabra) - Modificar Palabra");
        System.out.println("(5 o anadirPalabra) - Añadir Palabra");
        System.out.println("(6 o eliminarPalabra) - Eliminar Palabra");
        System.out.println("(7 o cambiarNombreLista) - Cambiar el nombre de la lista");
        System.out.println("(8 o consultarPalabra) - Consultar Palabra");
        System.out.println("(9 o cerrarLista) - Cerrar Lista");
        System.out.println("----------------");
        System.out.println("(0 o salir) - Cerrar el Driver");
    }

    public static void main(String[] args) throws Exception {
        DriverListaPalabras dl = new DriverListaPalabras();
        System.out.println("Driver de la lista de palabras");
        System.out.println("---------------");

        dl.listarOpciones();
        dl.in = new Scanner(System.in);
        String input = dl.in.nextLine();

        while (!input.equals("0") && !input.equals("salir")) {
            switch (input) {
                case "1":
                case "constructora": {
                    dl.testConstructora();
                    break;
                }
                case "2":
                case "imprimirListaActiva": {
                    dl.testImprimirListaActiva();
                    break;
                }
                case "3":
                case "crearLista":{
                    dl.testCrearLista();
                    break;
                }
                case "4":
                case "modificarPalabra": {
                    dl.testModificarPalabra();
                    break;
                }
                case "5":
                case "anadirPalabra": {
                    dl.testAnadirPalabra();
                    break;
                }
                case "6":
                case "eliminarPalabra": {
                    dl.testEliminarPalabra();
                    break;
                }
                case "7":
                case "cambiarNombreLista": {
                    dl.testCambiarNombreLista();
                    break;
                }
                case "8":
                case "consultarPalabra": {
                    dl.testConsultarPalabra();
                    break;
                }
                case "9":
                case "cerrarLista": {
                    dl.testCerrarLista();
                    break;
                }
                default:
                    System.out.println("Operación erronea");
                    break;
            }
            dl.volverAlMenu();
            dl.listarOpciones();
            input = dl.in.nextLine();
        }
        dl.in.close();
    }

    /**
     * Funcion prueba constructora controlador
     */
    public void testConstructora() throws listaExistente {
        cl = new CtrlListaPalabras();
        cl.crearListaPalabras("new_list");
    }


    public void testImprimirListaActiva() {
        if (cl == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Esta es la lista de frecuencias activa:");
        cl.imprimeListaActiva();
    }

    /**
     * Test para la crear la lista.
     */
    public void testCrearLista() {
        if (cl == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }

        System.out.println("Como quieres entrar la lista de palabras:");
        System.out.println("1 - Palabra a Palabra");
        System.out.println("2 - A partir de un texto");
        System.out.println("-----------------------------------");

        Scanner reader = new Scanner(System.in);
        int input2 = reader.nextInt();

        if (input2 == 1) {
            System.out.println("Nombre de la lista a crear: ");
            Scanner s = new Scanner(System.in);
            String name = s.nextLine();
            System.out.println("Nombre del alfabeto a usar: ");
            String alf = s.nextLine();
            System.out.println("Has escogido entrar la lista palabra a palabra");
            System.out.println("Instrucciones");
            System.out.println("Entrada [palabra][espacio][numero]");
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
            cl.crearListaPalabras(name, freq, alf);
        }
        else if (input2 == 2){
            System.out.println("Nom de la llista a crear: ");
            Scanner n = new Scanner(System.in);
            String name = n.nextLine();
            System.out.println("Nombre del alfabeto a usar: ");
            String alf = n.nextLine();
            System.out.println("Has Escogido entrar la lista a partir de un texto");
            System.out.println("Instrucciones");
            System.out.println("Tienes que entrar un texto sin puntos ni comas\n");
            Scanner s = new Scanner(System.in);
            String text = s.nextLine();
            cl.crearListaPalabras(name, text, alf);
        }
    }

    /**
     * Funcion para modificar la frecuencia de una lista
     */
    public void testModificarPalabra() {
        if (cl == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Entra la palabra y luego la frecuencia que quieras");
        String word = in.nextLine();
        Integer n = cl.findWord(word);
        if(n == null) System.err.println("La palabra no existe");
        int x = in.nextInt();
        cl.modificarPalabra(word, x);
    }
    public void testAnadirPalabra() {
        if (cl == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Entra la palabra y luego la frecuencia que quieras");
        String word = in.nextLine();
        //Integer n = cl.findWord(word);
        //if(n != null) System.err.println("La palabra ya existe");
        int x = in.nextInt();
        cl.modificarPalabra(word, x);
    }
    public void testEliminarPalabra() {
        if (cl == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Entra la palabra");
        String word = in.nextLine();
        Integer n = cl.findWord(word);
        if(n == null) System.err.println("La palabra no existe");
        cl.deleteWord(word);
    }
    public void testCambiarNombreLista() {
        if (cl == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
    }
    public void testConsultarPalabra() {
        if (cl == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        System.out.println("Entra la palabra");
        String word = in.nextLine();
        Integer n = cl.findWord(word);
        if(n == null) System.err.println("La palabra no existe");
        else{
            System.out.println("La palabra " + word + " tiene una frecuencia de " + n);
        }
    }
    public void testCerrarLista() {
        if (cl == null) {
            System.err.println("Esto no funciona si no hemos creado el controlador antes.");
            return;
        }
        cl.cerrarLista();
    }

    private void volverAlMenu() {
        System.out.println("Prem ENTER per tornar al menu principal");
        in.nextLine();
    }

}

