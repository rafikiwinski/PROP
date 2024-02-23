package test;
import main.domain.classes.Hungarian;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

/**
 * Clase para probar de forma unitaria la clase hungarian
 */

public class TestHungarian
{
    private Hungarian hungarian;

    @Before
    public void llenar() {
        hungarian = new Hungarian();
    }

    @Test
    public void CosteHungarian() {
        int[][] matriz = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // resultado de la matriz
        int expectedCoste = 15;

        // coste actual
        int actualCoste = hungarian.Coste_Hungarian(matriz);

        assertEquals(expectedCoste, actualCoste);
    }
}
