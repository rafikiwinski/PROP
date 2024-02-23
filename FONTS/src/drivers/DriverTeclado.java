package drivers;

import java.util.Scanner;

import main.domain.controllers.CtrlTeclado;

public class DriverTeclado {
    private CtrlTeclado ct = null;
    private Scanner reader;

    public void listarOpciones() {
        System.out.println("Opciones disponibles:");
        System.out.println("(1 o constructora) - Constructora del Controlador");
        System.out.println("(2 o crearTeclado) - Crear teclado");
        System.out.println("(3 o cambiarNombreTeclado) - Cambiar nombre teclado");
        System.out.println("(4 o cerrarTeclado) - Cerrar teclado");
        System.out.println("(5 o imprimirTeclado) - Imprimir teclado activo");
        System.out.println("----------------");
        System.out.println("(0 o salir) - Salir");
    }

    public static void main(String[] args){
        DriverTeclado dt = new DriverTeclado();
        dt.listarOpciones();
        dt.reader = new Scanner(System.in);
        String input = dt.reader.nextLine();

        while(!input.equals("0") && !input.equals("salir")){
            switch (input){
                case "1":
                case "constructora": {
                    dt.testConstructora();
                    break;
                }
                case "2":
                case "crearTeclado": {
                    dt.testCrearTeclado();
                    break;
                }
                case "3":
                case "cambiarNombreTeclado": {
                    dt.testCambiarNombreTeclado();
                    break;
                }
                case "4":
                case "cerrarTeclado": {
                    dt.testCerrarTeclado();
                    break;
                }
                case "5":
                case "imprimirTeclado": {
                    dt.testImprimirTeclado();
                    break;
                }
            }
            dt.volverAlMenu();
            dt.listarOpciones();
            input = dt.reader.nextLine();
        }
        dt.reader.close();
    }

    public void testConstructora() {
        ct = new CtrlTeclado();
    }

    /**
     * Función que crea un teclado
     */
    public void testCrearTeclado(){
        if (ct == null) {
            System.out.println("No hay ningún controlador de teclado creado");
        }
        System.out.println("Nombre del teclado: ");
        String nombreT = reader.nextLine();
        System.out.println("Número de filas: ");
        int nfilas = reader.nextInt();
        System.out.println("Número de columnas: ");
        int ncolumnas = reader.nextInt();
        System.out.println("Número de teclas: ");
        int nteclas = reader.nextInt();
        ct.crearTeclado(nombreT, nfilas, ncolumnas, nteclas);
    }

    /**
     * Función que cambia el nombre del teclado
     */
    public void testCambiarNombreTeclado(){
        if (ct == null) {
            System.out.println("No hay ningún controlador de teclado creado");
        }
        else {
            System.out.println("Nuevo nombre del teclado: ");
            String nombreT = reader.nextLine();
            ct.cambiarNombreTeclado(nombreT);
        }
    }

    /**
     * Función que cierra el teclado
     */
    public void testCerrarTeclado(){
        if (ct == null) {
            System.out.println("No hay ningún controlador de teclado creado");
        }
        else {
            ct.cerrarTeclado();
        }
    }

    /**
     * Función que imprime el teclado
     */
    public void testImprimirTeclado(){
        if (ct == null) {
            System.out.println("No hay ningún controlador de teclado creado");
        }
        else {
            ct.imprimirTeclado();
        }
    }

    /**
     * Función para volver al menú del driver
     */
    private void volverAlMenu() {
        System.out.println("Prem ENTER per tornar al menu principal");
        reader.nextLine();
    }

}