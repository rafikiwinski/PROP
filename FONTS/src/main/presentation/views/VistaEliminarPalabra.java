package main.presentation.views;

import main.domain.classes.exceptions.listaNoExiste;
import main.presentation.controllers.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Vista encargada de elminar una palabra de una lista que seleccionamos
 * @author Rafael Ibáñez Rodríguez
 */
public class VistaEliminarPalabra extends JFrame{
    private JFrame frame = new JFrame("JFrame");

    private JPanel contenedor = new JPanel();

    private JLabel Nombre = new JLabel("Nombre Lista");

    private JComboBox listaNombres = new JComboBox();

    private JLabel Palabra = new JLabel("Palabra");

    private JTextArea areaPalabra = new JTextArea("");

    private JButton bEliminar = new JButton("Eliminar");

    private JButton bVolverMenu = new JButton("Volver Menú");

    /**
     * Ventana que emerge en caso de error y en caso de que la palabra de la lista se elimine correctamente
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
                if (status.equals("Error")) {
                    areaPalabra.setText("");
                }
                else {
                    setVisible(false);
                    CtrlPresentacion.vistaEditarLista();
                }
            }
        });
        senseLoc.setVisible(true);
    }


    /**
     * Inicializa el frame
     */
    private void iniFrame(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1000, 600);
        int height = (screenSize.height - 600) / 2;
        int width = (screenSize.width - 1000) / 2;
        setLocation(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    /**
     * Creadora de la vista VistaEliminarPalabra
     */
    public VistaEliminarPalabra(){
        iniFrame();
        setTitle("Eliminar Palabra");

        Nombre.setBounds(368, 35, 200, 20);
        add(Nombre);

        listaNombres.setBounds(368, 60, 200, 20);
        listaNombres.addItem("");
        List<String> l = CtrlPresentacion.getListaPalabras();
        for (String s : l) {
            listaNombres.addItem(s);
        }
        add(listaNombres);

        Palabra.setBounds(368, 85, 200, 20);
        add(Palabra);

        areaPalabra.setBounds(368, 110, 200, 20);
        add(areaPalabra);
        
        bEliminar.setBounds(368, 150, 200, 20);
        add(bEliminar);

        bVolverMenu.setBounds(368, 180, 200, 20);
        add(bVolverMenu);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);


        ActionListener lEliminar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = (String) listaNombres.getSelectedItem();
                String palabra = areaPalabra.getText();
                int status;
                try {
                    status = CtrlPresentacion.eliminarPalabra(nombre, palabra);
                } catch (listaNoExiste ex) {
                    pop_up("Error", ex.getMessage());
                    throw new RuntimeException(ex);
                }
                if(status == -1){
                    pop_up("Error", "La palabra no existe en la lista");
                }
                else{
                    pop_up("Palabra Borrada", "La palabra ha sido eliminada");
                }
            }
        };



        ActionListener lVolverMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaEditarLista();
                setVisible(false);
            }
        };

        bEliminar.addActionListener(lEliminar);
        bVolverMenu.addActionListener(lVolverMenu);
        setVisible(true);
    }
}
