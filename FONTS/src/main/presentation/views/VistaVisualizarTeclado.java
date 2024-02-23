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
import java.util.Map;
import java.util.TreeMap;

/**
 * Vista que permite la visualización de un teclado por pantalla
 * @author Iván Parreño Benítez
 */
public class VistaVisualizarTeclado extends JFrame {
    private JPanel contenedor = new JPanel();

    private final JButton bVolverMenu = new JButton("Volver al menú principal");

    /**
     * Inicialización del frame
     */
    private void iniFrame(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1000, 600);
        int height = (screenSize.height - 600) / 2;
        int width = (screenSize.width - 1000) / 2;
        setLocation(width, height);
        setResizable(false);
    }

    /**
     * Creadora de la vista VistaVisualizarTeclado que le pasamos la distribución del teclado como parámetro
     * @param teclado
     */
    public VistaVisualizarTeclado(TreeMap<Integer,Character> teclado) {
        iniFrame();
        //setExtendedState(Frame.MAXIMIZED_BOTH);
        setTitle("Visualizar Teclado");
        int i = 0;
        int x = 100;
        int y = 50;
        for(Map.Entry<Integer,Character> it : teclado.entrySet()) {
            JButton Tecla = new JButton(String.valueOf(it.getValue()));
            Tecla.setBounds(x, y, 70, 70);
            add(Tecla);
            x += 70;
            ++i;
            if (i == 10) {
                y += 70;
                x = 100;
                i = 0;
            }
        }

        bVolverMenu.setBounds(600,400,300,40);
        add(bVolverMenu);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);
        setVisible(true);

        ActionListener volver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.iniPresentacion();
                setVisible(false);
            }
        };

        bVolverMenu.addActionListener(volver);
    }
}

