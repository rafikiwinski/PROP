package main.presentation.views;

import main.domain.classes.exceptions.listaNoExiste;
import main.presentation.controllers.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

/**
 * Vista que añade una palabra a una lista existente en el sistema
 * @author Rafael Ibáñez Rodríguez
 */
public class VistaAnadirPalabra extends JFrame{
    private JFrame frame = new JFrame("JFrame");

    private JPanel contenedor = new JPanel();

    private JLabel Nombre = new JLabel("Nombre");

    private JComboBox listaNombres = new JComboBox();

    private JLabel Palabra = new JLabel("Palabra");

    private JTextArea areaPalabra = new JTextArea("");

    private JLabel Frequencia = new JLabel("Frecuencia");

    private JTextArea areaFrequencia = new JTextArea("");

    private JButton bAnadir = new JButton("Añadir");

    private JButton bVolverMenu = new JButton("Volver Menú");

    /**
     * Ventana que emerge en caso de error y en caso de que se añada la palabra a la lista correctamente
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
                if(Objects.equals(status, "Error")){
                    setVisible(true);
                }
                else{
                    CtrlPresentacion.vistaEditarLista();
                    setVisible(false);
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    /**
     * Creadora de la vista VistaAnadirPalabra
     */
    public VistaAnadirPalabra(){
        iniFrame();
        setTitle("Añadir Palabra");

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

        Frequencia.setBounds(368, 135, 200, 20);
        add(Frequencia);

        areaFrequencia.setBounds(368, 160, 200, 20);
        add(areaFrequencia);

        bAnadir.setBounds(368, 185, 200, 20);
        add(bAnadir);

        bVolverMenu.setBounds(368, 210, 200, 20);
        add(bVolverMenu);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);

        ActionListener lAnadir = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = (String) listaNombres.getSelectedItem();
                String palabra = areaPalabra.getText();
                String frecuencia = areaFrequencia.getText();
                int status;
                try {
                    status = CtrlPresentacion.anadirPalabra(nombre, palabra, frecuencia);
                } catch (listaNoExiste ex) {
                    pop_up("Error", ex.getMessage());
                    throw new RuntimeException(ex);
                }
                if(status == -1){
                    pop_up("Error", "La palabra no existe en la lista");
                }
                else{
                    pop_up("Palabra Añadida", "La palabra ha sido añadida");
                }
                setVisible(false);
            }
        };

        ActionListener lVolverMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.vistaEditarLista();
                setVisible(false);
            }
        };

        bAnadir.addActionListener(lAnadir);
        bVolverMenu.addActionListener(lVolverMenu);
        setVisible(true);
    }
}
