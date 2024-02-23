package main.presentation.views;

import main.presentation.controllers.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta vista representa la ventana de gestión de teclados en la que puedes Crear un Teclado, Cambiar Nombre Teclado,
 * Eliminar Teclado y Volver Menú
 * @author Luis Jesús Valverde Zavaleta
 */
public class VistaGestionarTeclados extends JFrame {

    // Botons
    private final JPanel contenedor = new JPanel();
    private JButton bCrearTeclado = new JButton("Crear Teclado");
    private JButton bEliminarTeclado = new JButton("Eliminar Teclado");
    private JButton bCambiarNombreTeclado = new JButton("Cambiar Nombre Teclado");
    private JButton bVolverMenu = new JButton("Volver Menú");

    /**
     * Inicializa el frame
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
     * Creadora de la vista VistaGestionarTeclados
     */
    public VistaGestionarTeclados () {
        iniFrame();
        setTitle("Gestionar Teclados");

        // Posicions
        bCrearTeclado.setBounds(343, 85, 250, 50);
        add(bCrearTeclado);
        bCambiarNombreTeclado.setBounds(343, 165, 250, 50);
        add(bCambiarNombreTeclado);
        bEliminarTeclado.setBounds(343, 245, 250, 50);
        add(bEliminarTeclado);
        bVolverMenu.setBounds(343, 325, 250, 50);
        add(bVolverMenu);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);



        ActionListener lcrearTeclado = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaCreacionTeclado();
                setVisible(false);
            }
        };

        ActionListener leliminarTeclado = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaEliminarTeclado();
                setVisible(false);
            }
        };

        ActionListener lcambiarNombreTeclado = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaCambiarNombreTeclado();
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




        // Afegim elements
        bCrearTeclado.addActionListener(lcrearTeclado);
        bCambiarNombreTeclado.addActionListener(lcambiarNombreTeclado);
        bEliminarTeclado.addActionListener(leliminarTeclado);
        bVolverMenu.addActionListener(lVolverMenu);


        setVisible(true);
    }
}
