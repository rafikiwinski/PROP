package test;

import main.domain.classes.Lista_Palabras;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

/**
 * Clase para probar de forma unitaria la clase Lista_Palabras
 * @author rafael.ibanez.rodriguez
 */
public class TestLista {

    /**
     * Objecte de la prova: Test del metódo Lista_Palabras(String name) de la clase Lista_Palabras
     *                      Tambien se prueba el getter de nombre y el getter de tamaño.
     * Fitxers de dades necessaris: Datos introducidos manualmente, no se usan ficheros.
     * Valors Estudiats: Estategia caja gris
     * Operativa:
     */
    @Test
    public void testConstructora(){
        //Probamos para constructora sin parametros más que el nombre
        Lista_Palabras l1 = new Lista_Palabras("lista");
        assertEquals("lista", l1.getName_list());

        //Probamos para constructora con nombre y texto
        Lista_Palabras l2 = new Lista_Palabras("lista", "hola que tal", "Latin");
        assertEquals("lista", l2.getName_list());
        assertEquals(3, l2.getListSize());

        //Probamos para constructora con nombre y lista de palabras
        Map<String, Integer> freq = new HashMap<String, Integer>();
        freq.put("hola", 1);
        freq.put("que", 1);
        freq.put("tal", 1);
        Lista_Palabras l3 = new Lista_Palabras("lista", "Latin", freq);
        assertEquals("lista", l3.getName_list());
        assertEquals(3, l3.getListSize());
    }
    /**
     * Objecte de la prova: Probar el setter de nombre de la clase Lista_Palabras
     * Fitxers de dades necessaris: Ninguno, los datos se introducen manualmente.
     * Valors Estudiats: Estategia caja gris
     * Operativa: Primero se crea una lista con un nombre, luego se cambia el nombre y se comprueba que se ha cambiado.
     */
    @Test
    public void testSetNombre(){
        Lista_Palabras l1 = new Lista_Palabras("lista");
        assertEquals("lista", l1.getName_list());
        l1.setName_list("lista2");
        assertEquals("lista2", l1.getName_list());
    }
    /**
     * Objecte de la prova: Comprobar que se crea una lista de palabras a partir de un string
     * Fitxers de dades necessaris: No se usan ficheros, los datos se introducen manualmente.
     * Valors Estudiats: Estategia caja gris
     * Operativa: Se crea una lista de palabras a partir de un string y se comprueba que se ha creado correctamente.
*/
    @Test
    public void testCreateFromString(){
        Lista_Palabras l1 = new Lista_Palabras("lista");
        l1.createFromString("hola que tal");
        assertEquals(3, l1.getListSize());
    }
    /**
     * Objecte de la prova: Comprobar que se crea una lista de palabras
     * Fitxers de dades necessaris:
     * Valors Estudiats:
     * Operativa:
*/
    @Test
    public void testBuscarPalabra(){
        Lista_Palabras l1 = new Lista_Palabras("lista");
        l1.createFromString("hola hola que tal tal tal tal");
        int h = l1.buscaPalabra("hola");
        int q = l1.buscaPalabra("que");
        int t = l1.buscaPalabra("tal");
        assertEquals(2, h);
        assertEquals(1, q);
        assertEquals(4, t);
        assertNull(l1.buscaPalabra("adios"));
    }

    /**
     * Objecte de la prova:
     * Fitxers de dades necessaris:
     * Valors Estudiats:
     * Operativa:
     */
    @Test
    public void testPrintLista(){
        Lista_Palabras l1 = new Lista_Palabras("lista");
        l1.createFromString("hola hola que tal tal tal tal");
        l1.printList();
    }

    /**
     * Objecte de la prova:
     * Fitxers de dades necessaris:
     * Valors Estudiats:
     * Operativa:
     */
    @Test
    public void testSetWordValue(){
        Lista_Palabras l1 = new Lista_Palabras("lista");
        l1.createFromString("hola hola que tal tal tal tal");
        l1.setWordValue("hola", 5);
        assertEquals(5, l1.buscaPalabra("hola").intValue());
        l1.setWordValue("hola", 0);
        assertEquals(0, l1.buscaPalabra("hola").intValue());
        l1.setWordValue("hola", 1);
        assertEquals(1, l1.buscaPalabra("hola").intValue());
    }

    /**
     * Objecte de la prova:
     * Fitxers de dades necessaris:
     * Valors Estudiats:
     * Operativa:
     */
    @Test
    public void testAnadePalabra() {
        Lista_Palabras l1 = new Lista_Palabras("lista");
        l1.createFromString("hola hola que tal tal tal tal");
        l1.anadePalabra("adios", 8);
        assertEquals(8, l1.buscaPalabra("adios").intValue());
        l1.anadePalabra("pepe", 20);
        assertEquals(20, l1.buscaPalabra("pepe").intValue());
        l1.anadePalabra("hola", 9);
        assertEquals(9, l1.buscaPalabra("hola").intValue());
    }

/**
     * Objecte de la prova:
     * Fitxers de dades necessaris:
     * Valors Estudiats:
     * Operativa:
     */
    @Test
    public void testBorraPalabra() {
        Lista_Palabras l1 = new Lista_Palabras("lista");
        l1.createFromString("hola hola que tal tal tal tal");
        l1.borraParaula("hola");
        assertNull(l1.buscaPalabra("hola"));
        l1.borraParaula("que");
        assertNull(l1.buscaPalabra("que"));
        l1.borraParaula("tal");
        assertNull(l1.buscaPalabra("tal"));
        l1.borraParaula("adios");
        assertNull(l1.buscaPalabra("adios"));
    }

}
