package main.presentation.views;

import main.presentation.controllers.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Vista que te lleva a las diferentes formas de crear una lista de palabras, que puede ser mediante texto o
 * creando la lista manualmente
 * @author Juan José Torredemer Pueyo
 */
public class VistaCrearLista extends JFrame{

    private JPanel contenedor = new JPanel();

    /**
     * Inicializa el frame
     */
    private void iniFrame(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(900, 600);
        int height = (screenSize.height-600)/2;
        int width = (screenSize.width-900)/2;
        setLocation(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    /**
     * Creadora de la vista VistaCrearLista
     */
    public VistaCrearLista() {
        iniFrame();
        setTitle("Crear Lista");

        JPanel contenedor = new JPanel();

        JButton bCrearMano = new JButton("Crear Manualmente");

        JButton bCrearTexto = new JButton("Crear a partir de un texto");

        JButton bVolverMenu = new JButton("Volver Menú");

        bCrearMano.setBounds(268, 85, 300, 50);
        add(bCrearMano);

        bCrearTexto.setBounds(268, 165, 300, 50);
        add(bCrearTexto);

        bVolverMenu.setBounds(268, 245, 300, 50);
        add(bVolverMenu);

        ActionListener lCrearMano = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaCrearListaManualmente();
                setVisible(false);
            }
        };

        ActionListener lCrearTexto = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaCrearListaTexto();
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

        bCrearMano.addActionListener(lCrearMano);
        bCrearTexto.addActionListener(lCrearTexto);
        bVolverMenu.addActionListener(lVolverMenu);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);

        setVisible(true);
    }
}
