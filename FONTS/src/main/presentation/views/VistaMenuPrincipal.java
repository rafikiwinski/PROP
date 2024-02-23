package main.presentation.views;

import main.presentation.controllers.CtrlPresentacion;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Esta vista representa el menu principal al ejecutar el projecto
 * Desde esta ventana podemos acceder a diversas vistas de la interfaz
 * @author Luis Jesús Valverde Zavaleta
 */
public class VistaMenuPrincipal extends JFrame {

    private final JPanel contenedor = new JPanel();

    private final JButton bAbrirTeclado = new JButton("Abrir Teclado");

    private final JButton bGestionTeclados = new JButton("Gestionar Teclados");

    private final JButton bGestionListas = new JButton("Gestionar Listas ");

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
     * Creadora de VistaMenuPrincipal
     */
    public VistaMenuPrincipal(){
        //Ventana
        iniFrame();
        setTitle("Menú Principal");




        ImageIcon logo = new ImageIcon("../../DATA/images/logo.png");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(220, 35, 500, 100);
        logoLabel.setVerticalAlignment(JLabel.CENTER);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        add(logoLabel);

        bAbrirTeclado.setBounds(368, 150, 200, 50);
        bAbrirTeclado.setVerticalAlignment(JLabel.CENTER);
        bAbrirTeclado.setHorizontalAlignment(JLabel.CENTER);
        add(bAbrirTeclado);

        bGestionTeclados.setBounds(368, 230, 200, 50);
        bGestionTeclados.setVerticalAlignment(JLabel.CENTER);
        bGestionTeclados.setHorizontalAlignment(JLabel.CENTER);
        add(bGestionTeclados);

        bGestionListas.setBounds(368, 310, 200, 50);
        bGestionListas.setVerticalAlignment(JLabel.CENTER);
        bGestionListas.setHorizontalAlignment(JLabel.CENTER);
        add(bGestionListas);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);

        setVisible(true);

        ActionListener abrirTeclado = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaAbrirTeclado();
                setVisible(false);
            }
        };

        ActionListener gestionarTeclados = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaGestionarTeclados();
                setVisible(false);
            }
        };

        ActionListener gestionarListas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaGestionListas();
                setVisible(false);
            }
        };

        bAbrirTeclado.addActionListener(abrirTeclado);
        bGestionTeclados.addActionListener(gestionarTeclados);
        bGestionListas.addActionListener(gestionarListas);

    }
}
