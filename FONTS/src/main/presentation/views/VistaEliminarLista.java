package main.presentation.views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import main.presentation.controllers.CtrlPresentacion;

/**
 * Vista encargada de eliminar una lista de las existentes en nuestro sistema
 * @author Rafael Ibáñez Rodríguez
 */
public class VistaEliminarLista extends JFrame {
    private final JFrame frame = new JFrame();

    private final JPanel contenedor = new JPanel();

    private final JLabel label = new JLabel("Selecciona la lista que quieres eliminar");

    private final JComboBox<String> comboBox = new JComboBox<>();

    private final JButton button = new JButton("Eliminar");

    private final JButton button2 = new JButton("Menú principal");

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
     * Ventana que emerge en caso de error y en caso de que la lista se elimine correctamente
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
                if(status.equals("Error")){
                    setVisible(true);
                }
                else{
                    CtrlPresentacion.iniPresentacion();
                    setVisible(false);
                }
            }
        });
        senseLoc.setVisible(true);
    }

    /**
     * Creadora de la vista VistaEliminarLista
     */
    public VistaEliminarLista(){
        iniFrame();
        setTitle("Eliminar Lista");

        label.setBounds(268, 35, 300, 20);
        add(label);

        comboBox.setBounds(268, 65, 300, 20);
        comboBox.addItem("");
        List<String> l = CtrlPresentacion.getListaPalabras();
        for (String s : l) {
            comboBox.addItem(s);
        }
        add(comboBox);

        button.setBounds(268, 95, 300, 20);
        add(button);

        button2.setBounds(268, 125, 300, 20);
        add(button2);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);

        ActionListener lbutton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBox.getSelectedItem();
                eliminarListaPalabras(s);
            }
        };

        ActionListener lbutton2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.iniPresentacion();
                setVisible(false);
            }
        };
        setVisible(true);
        button.addActionListener(lbutton);
        button2.addActionListener(lbutton2);

    }

    /**
     * Función que se encarga de eliminar la lista de palabras "s"
     * @param s
     */
    private void eliminarListaPalabras(String s) {
        boolean b = CtrlPresentacion.eliminarLista(s);
        JButton exitButton = new JButton("Salir");
        if (!b) {
            pop_up("Error", "La lista " + s + " no se ha podido eliminar");

        } else {
            pop_up("Éxito", "La lista " + s + " se ha eliminado correctamente");
        }
    }
}
