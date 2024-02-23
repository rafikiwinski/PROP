package main.presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.presentation.controllers.CtrlPresentacion;

/**
 * Vista que te permite editar una lista con las opciones siguientes: Cambiar Nombre, Modificar Frecuencia, Eliminar Palabra,
 * Añadir Palabra y Volver Menú
 * @author Iván Parreño Benítez
 */
public class VistaEditarLista extends JFrame {


    /**
     * Iniciliaza el frame
     */
    private void iniFrame(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1000, 600);
        int height = (screenSize.height - 600) / 2;
        int width = (screenSize.width - 1000) / 2;
        setLocation(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    /**
     * Creadora de la vista VistaEditarLista
     */
    public VistaEditarLista(){
        iniFrame();
        setTitle("Editar Lista");

        JPanel contenedor = new JPanel();

        JButton bCambiarNombre = new JButton("Cambiar Nombre");

        JButton bModificarFrecuencia = new JButton("Modificar Frecuencia");

        JButton bEliminarPalabra = new JButton("Eliminar Palabra");

        JButton bAnadirPalabra = new JButton("Añadir Palabra");

        JButton bVolverMenu = new JButton("Volver Menú");

        bCambiarNombre.setBounds(368, 65, 200, 50);
        add(bCambiarNombre);

        bModificarFrecuencia.setBounds(368, 145, 200, 50);
        add(bModificarFrecuencia);

        bEliminarPalabra.setBounds(368, 225, 200, 50);
        add(bEliminarPalabra);

        bAnadirPalabra.setBounds(368, 305, 200, 50);
        add(bAnadirPalabra);

        bVolverMenu.setBounds(368, 385, 200, 50);
        add(bVolverMenu);

        ActionListener lCambiarNombre = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaCambiarNombreLista();
                setVisible(false);
            }
        };

        ActionListener lModificarFrecuencia = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaModificarFrecuencia();
                setVisible(false);
            }
        };


        ActionListener lEliminarPalabra = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaEliminarPalabra();
                setVisible(false);
            }
        };


        ActionListener lAnadirPalabra = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaAnadirPalabra();
                setVisible(false);
            }
        };

        ActionListener lVolverMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.iniPresentacion();
                setVisible(false);
            }
        };

        bCambiarNombre.addActionListener(lCambiarNombre);
        bModificarFrecuencia.addActionListener(lModificarFrecuencia);
        bEliminarPalabra.addActionListener(lEliminarPalabra);
        bAnadirPalabra.addActionListener(lAnadirPalabra);
        bVolverMenu.addActionListener(lVolverMenu);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);

        setVisible(true);
    }
}
