package test;

import main.domain.classes.Teclado;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

/**
 * Clase para probar de forma unitaria la clase teclado
 * @author juan.jose.torredemer
 */

public class TestTeclado {
    int filas;
    int columnas;
    int n_teclas;

    @Before
    public void llenar_params(){
        filas = 3;
        columnas = 10;
        n_teclas= 26;
    }

    @Test
    //getNameteclado
    public void getnombreteclado(){
        Teclado t = new Teclado("Prop", filas, columnas, n_teclas);
        assertEquals("Prop", t.getNameTeclado());
    }

    @Test
    //getNumeroteclas
    public void getnumeroteclas(){
        Teclado t = new Teclado("Prop", filas, columnas, n_teclas);
        assertEquals(n_teclas, t.getNumeroTeclas());
    }


    @Test
    //getNumFilas
    public void getnumerofil(){
        Teclado t = new Teclado("Prop", filas, columnas, n_teclas);
        assertEquals(filas, t.getNumFilas());
    }

    @Test
    public void creadora_Teclado(){
        Teclado t = new Teclado();
        assertEquals("", t.getNameTeclado());
        assertEquals(0, t.getNumeroTeclas());
        assertEquals(0, t.getNumFilas());
    }

    @Test
    public void creadora_Teclado_parametros(){
        Teclado t = new Teclado("Prop", filas, columnas, n_teclas);
        assertEquals("Prop", t.getNameTeclado());
        assertEquals(26, t.getNumeroTeclas());
        assertEquals(3, t.getNumFilas());
    }

    @Test
    public void cambiar_nombre(){
        Teclado t = new Teclado("Prop", filas, columnas, n_teclas);
        t.cambiarNombreTeclado("prop2");
        assertEquals("prop2", t.getNameTeclado());
    }
   
    



}
