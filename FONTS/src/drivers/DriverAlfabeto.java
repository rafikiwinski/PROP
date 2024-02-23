package drivers;

import main.domain.classes.Alfabeto;
import main.domain.controllers.CtrlAlfabeto;

import java.util.*;

public class DriverAlfabeto {
    private CtrlAlfabeto ca = null;
    private Alfabeto a = null;
    private Scanner in;

    private static void listaOpciones() {
        System.out.println("(1 o constructora) - Constructora del Driver");
        System.out.println("(2 o consulta Alfabetos Disponibles) - Consulta Alfabetos disponibles");
        System.out.println("(3 o consulta Alfabeto Específico) - Consulta Alfabeto Específico");
        System.out.println("----------------");
        System.out.println("(0 o salir) - Cerrar el Driver");


    }

    public void testConstructora() {
        ca = new CtrlAlfabeto();
    }
    //
    //
    //
    public void testonsultaAlfabetos(){
        if (ca == null) {
            System.err.println("Aún no se ha creado el controlador");
            return;
        }
        System.out.println("Nommbre del alfabeto: ");
        String nomAlfabet = in.nextLine();
        System.out.println("Nombre elegido: " + nomAlfabet);
        System.out.println("Carácteres: ");
        char[] Caracteres = in.nextLine().toCharArray();
        System.out.println("carácteres del alfabeto: " + Caracteres);
        ca.crearAlfabeto(Caracteres, nomAlfabet);
    }
    public void testCambiarNomAlfabeto() {
        if (ca == null) {
            System.err.println("Aún no se ha creado el controlador");
            return;
        }
        System.out.println("Nuevo nombre del alfabeto: ");
        String nomAlf = in.nextLine();
        ca.cambioNombre(nomAlf);
        System.out.println("Nombre del alfabeto ahora es: " + nomAlf);
    }


    public void testCerrarAlfabeto() {
        if (ca == null) {
            System.err.println(" Aún no se ha creado el controlador");
            return;
        }
        ca.cerrarAlfabeto();
    }

    public static void main(String[] args) throws Exception {
        DriverAlfabeto da = new DriverAlfabeto();
        System.out.println("Driver de Alfabeto");
        System.out.println("---------------");
        listaOpciones();

        da.in = new Scanner(System.in);
        String input = da.in.nextLine();
        while (!input.equals("0") && !input.equals("salir")) {
            switch (input) {
                case "1":
                case "constructora": {
                    da.testConstructora();
                    break;
                }
                case "2":
                case "crearAlfabeto": {
                    da.testonsultaAlfabetos();
                    break;
                }
                case "3":
                case "cambiarNomAlfabeto": {
                    da.testCambiarNomAlfabeto();;
                    break;
                }

                case "4":
                case "cerrarAlfabeto": {
                    da.testCerrarAlfabeto();
                    break;
                }

                default:
                    break;
            }
            da.volverAlMenu();
            listaOpciones();
            input = da.in.nextLine();

        }
        da.in.close();
    }

    private void volverAlMenu() {
        System.out.println("ENTER regresar menu principal");
        in.nextLine();
    }
}
