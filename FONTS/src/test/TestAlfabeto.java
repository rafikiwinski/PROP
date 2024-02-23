package test;
import main.domain.classes.Alfabeto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

/**
 * Clase para probar de forma unitaria la clase alfabeto
 * @author juan.jose.torredemer
 */
public class TestAlfabeto {
    int n = 26;
    private char[] caracteres = new char[n];

    @Before
    public void llenar_alfabeto(){
        int i = 0;
        for(char letra = 'a'; letra <= 'z'; letra++){
            caracteres[i] = letra;
            i++;
        }
    }

    @Test
    public void creadoraAlfabeto(){
        Alfabeto alf = new Alfabeto(caracteres, "latin");
        assertEquals(n, alf.getNumCaracters());
        assertEquals("latin", alf.getNomAlfabet());
        assertEquals(caracteres, alf.getordererCharacters());
    }

    @Test
    public void cambio_nombre(){
        Alfabeto alf = new Alfabeto(caracteres, "latin");
        alf.cambioNombre("latino_cambio");
        assertEquals("latino_cambio", alf.getNomAlfabet());
    }


    @Test
    public void primera_letra(){
        Alfabeto alf = new Alfabeto(caracteres, "latin");
        char letra = alf.getPrimera();
        assertEquals('a', letra);
    }

    @Test
    //getcharacters
    public void getcaracters(){
        Alfabeto alf = new Alfabeto(caracteres, "latin");
        assertArrayEquals(caracteres, alf.getordererCharacters());
    }

    @Test
    //getNumCaracters
    public void getnumcaracters(){
        Alfabeto alf = new Alfabeto(caracteres, "latin");
        assertEquals(caracteres.length, alf.getNumCaracters());
    }

    @Test
    //getNomAlfabet
    public void getnombrealfabeto(){
        Alfabeto alf = new Alfabeto(caracteres, "latin");
        assertEquals("latin", alf.getNomAlfabet());
    }


    @Test
    public void set_nombre(){
        Alfabeto alf = new Alfabeto(caracteres, "latin");
        alf.setNomAlfabet("latino_cambio");
        assertEquals("latino_cambio", alf.getNomAlfabet());
    }


}
