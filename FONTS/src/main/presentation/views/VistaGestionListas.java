package main.presentation.views;

import main.presentation.controllers.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Vista que te lleva al submenú para gestionar las listas del sistema, se puede Crear Lista, Eliminar Lista,
 * Editar Lista y Volver Menú Principal
 * @author Luis Jesús Valverde Zavaleta
 */
public class VistaGestionListas extends JFrame {
    private final JPanel contenedor = new JPanel();

    private final JButton bCrearLista = new JButton("Crear Lista");

    private final JButton bEliminarLista = new JButton("Eliminar Lista");

    private final JButton bEditarLista = new JButton("Editar Lista");

    private final JButton bVolverMenu = new JButton("Volver Menú Principal");

    /**
     * Inicilizar el frame
     */
    private void iniFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1000, 600);
        int height = (screenSize.height - 600) / 2;
        int width = (screenSize.width - 1000) / 2;
        setLocation(width, height);
        setResizable(false);
    }

    /**
     * Creadora de la vista VistaGestionListas
     */
    public VistaGestionListas(){
        iniFrame();
        setTitle("Gestión de Listas");

        bCrearLista.setBounds(368, 85, 200, 50);
        add(bCrearLista);

        bEliminarLista.setBounds(368, 165, 200, 50);
        add(bEliminarLista);

        bEditarLista.setBounds(368, 245, 200, 50);
        add(bEditarLista);

        bVolverMenu.setBounds(368, 325, 200, 50);
        add(bVolverMenu);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);

        setVisible(true);

        ActionListener crearLista = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaCrearLista();
                setVisible(false);
            }
        };

        ActionListener eliminarLista = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaEliminarLista();
                setVisible(false);
            }
        };

        ActionListener editarLista = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaEditarLista();
                setVisible(false);
            }
        };

        ActionListener volverMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.iniPresentacion();
                setVisible(false);
            }
        };


        bCrearLista.addActionListener(crearLista);

        bEliminarLista.addActionListener(eliminarLista);

        bEditarLista.addActionListener(editarLista);

        bVolverMenu.addActionListener(volverMenu);


    }
}
