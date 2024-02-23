package main.presentation.views;

import main.presentation.controllers.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Esta vista representa la ventana mediante la cual abriremos un teclado
 * @author Luis Jesús Valverde Zavaleta
 */
public class VistaAbrirTeclado extends JFrame {
    private final JPanel contenedor = new JPanel();

    private final JLabel NombreTeclado = new JLabel("AbrirTeclado");

    private final  JComboBox cbTeclados = new JComboBox();

    private final JButton bAbrir = new JButton("Abre");

    private final JButton bAtras = new JButton("Volver Menú");

    private final JFrame frame = new JFrame();

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
     * Ventana que emerge en caso de error
     * @param status ERROR
     * @param text El porque del error
     */
    private void pop_up(String status, String text) {
        JDialog senseLoc = new JDialog(frame, status);
        senseLoc.setSize(600, 400);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (screenSize.height - 400) / 2;
        int width = (screenSize.width - 600) / 2;
        senseLoc.setLocation(width, height);
        senseLoc.setLayout(null);

        JLabel txtErroNombre = new JLabel(text);
        txtErroNombre.setBounds(50, 20, 400, 40);
        senseLoc.add(txtErroNombre);

        JButton bOk = new JButton("Ok");
        bOk.setBounds(150, 100, 100, 20);
        senseLoc.add(bOk);

        bOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                senseLoc.setVisible(false);
            }
        });
        senseLoc.setVisible(true);
    }


    /**
     * Creadora de la vista VistaAbrirTeclado
     */
    public VistaAbrirTeclado(){
        iniFrame();
        setTitle("Abrir Teclado");
        add(NombreTeclado);

        cbTeclados.setBounds(50,50,200,20);
        List<String> l = CtrlPresentacion.getTeclados();
        for (String s : l) {
            cbTeclados.addItem(s);
        }
        add(cbTeclados);

        bAbrir.setBounds(275, 50, 100, 20);
        add(bAbrir);

        bAtras.setBounds(400, 50, 200, 20);
        add(bAtras);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);

        setVisible(true);

        ActionListener Abrir = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbTeclados.getSelectedItem() == "") {
                    pop_up("Error", "No hay ningún teclado seleccionado");
                }else{
                    TreeMap<Integer, Character> teclado =  CtrlPresentacion.getTeclado(cbTeclados.getSelectedItem().toString());
                    CtrlPresentacion.vistaVisualizarTeclado(teclado);
                    setVisible(false);
                }
            }
        };

        ActionListener Atras = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.iniPresentacion();
                setVisible(false);
            }
        };

        bAbrir.addActionListener(Abrir);
        bAtras.addActionListener(Atras);
    }
}
