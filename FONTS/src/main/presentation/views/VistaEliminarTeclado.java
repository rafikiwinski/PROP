package main.presentation.views;


import main.presentation.controllers.CtrlPresentacion;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.util.List;

/**
 * Vista que nos permite seleccionar entre los teclados existentes y eliminarlos
 * @author Iván Parreño Benítez
 */
public class VistaEliminarTeclado extends JFrame {
    private JFrame frame = new JFrame();

    private JPanel contenedor = new JPanel();

    private JComboBox cbTeclados = new JComboBox();

    private JLabel txtTeclados = new JLabel("Teclados");

    private JButton bEliminar = new JButton("Eliminar");

    private JButton bVolverMenu = new JButton("Volver Menú");

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
     * Ventana que emerge en caso de error y en caso de que el teclado se elimine correctamente
     * @param status ERROR o EXITO
     * @param text El porque del error o mensaje de éxito
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
                if (status.equals("Error")) setVisible(true);
                else {
                    CtrlPresentacion.iniPresentacion();
                    setVisible(false);
                }
            }
        });
        senseLoc.setVisible(true);
    }

    /**
     * Creadora de la vista VistaEliminarTeclado
     */
    public VistaEliminarTeclado(){
        iniFrame();
        setTitle("Eliminar Teclado");

        txtTeclados.setBounds(50, 35, 200, 20);
        add(txtTeclados);

        cbTeclados.setBounds(200,35,200,20);
        cbTeclados.addItem("");
        List<String> l = CtrlPresentacion.getTeclados();
        for (String s : l) {
            cbTeclados.addItem(s);
        }
        add(cbTeclados);

        bEliminar.setBounds(200, 95, 200, 20);
        add(bEliminar);

        bVolverMenu.setBounds(200, 150, 200, 20);
        add(bVolverMenu);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);

        ActionListener lVolverMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.iniPresentacion();
                setVisible(false);
            }
        };

        ActionListener lEliminar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbTeclados.getSelectedItem().toString().equals("")){
                    pop_up("Error", "No has seleccionado ningún teclado");
                }
                else {
                    deleteFile(cbTeclados.getSelectedItem().toString());
                }
            }
        };

        bVolverMenu.addActionListener(lVolverMenu);
        bEliminar.addActionListener(lEliminar);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Función que elimina un teclado con nombre s
     * @param s
     */
    private void deleteFile(String s){
        boolean b = CtrlPresentacion.eliminarTeclado(s);
        if (!b) {
            pop_up("Error", "No existe ningún teclado con ese nombre");

        } else {
            pop_up("Éxito", "Teclado eliminado correctamente");
        }
    }


}
