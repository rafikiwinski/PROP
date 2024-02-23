package main.presentation.views;

import javax.swing.*;

import main.domain.classes.exceptions.listaExistente;
import main.domain.classes.exceptions.listaNoExiste;
import main.presentation.controllers.CtrlPresentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

/**
 * Vista que te permite cambiarle el nombre a una lista de palabras existente en el sistema, tienes que seleccionar una
 * lista existente en el sistema y introducir el nuevo nombre que quieres ponerle
 * @author Juan José Torredemer Pueyo
 */
public class VistaCambiarNombreLista extends JFrame{

    private JPanel contenedor = new JPanel();

    private JLabel ListaActual = new JLabel("Lista Actual");

    private JComboBox cbListas = new JComboBox();

    private JLabel Nombre = new JLabel("Nuevo Nombre");

    private JTextArea areaNombre = new JTextArea("");

    private JButton bCambiar = new JButton("Cambiar");

    private JButton bVolverMenu = new JButton("Volver Menú");

    private JFrame frame = new JFrame("JFrame");

    /**
     * Ventana que emerge en caso de error y en caso de que la lista de plabras se renombre correctamente
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
        setSize(900, 600);
        int height = (screenSize.height - 600) / 2;
        int width = (screenSize.width - 900) / 2;
        setLocation(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    /**
     * Creadora de la vista VistaCambiarNombreLista
     */
    public VistaCambiarNombreLista(){
        iniFrame();
        setTitle("Cambiar Nombre Lista");

        ListaActual.setBounds(50, 35, 200, 20);
        add(ListaActual);

        cbListas.setBounds(200,35,200,20);
        cbListas.addItem("");
        List<String> l = CtrlPresentacion.getListaPalabras();
        for (String s : l) {
            cbListas.addItem(s);
        }
        add(cbListas);

        Nombre.setBounds(50, 75, 200, 20);
        add(Nombre);

        areaNombre.setBounds(200,75,200,20);
        add(areaNombre);

        bCambiar.setBounds(200, 135, 200, 20);
        add(bCambiar);

        bVolverMenu.setBounds(200, 175, 200, 20);
        add(bVolverMenu);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);

        ActionListener lCambiar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreNuevo = areaNombre.getText();
                String nombreViejo = (String) cbListas.getSelectedItem();
                if (nombreNuevo.equals("")) {
                    pop_up("Error", "El nombre no puede estar vacío");
                    throw new RuntimeException("El nombre no puede estar vacío");
                }
                try {
                    CtrlPresentacion.cambiarNombreLista(nombreNuevo, nombreViejo);
                } catch (listaNoExiste | listaExistente ex) {
                    pop_up("Error", ex.getMessage());
                    throw new RuntimeException(ex);
                }
                pop_up("Nombre cambiado", "El nombre se ha cambiado correctamente");
            }
        };

        ActionListener lVolverMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.iniPresentacion();
                setVisible(false);
            }
        };

        bCambiar.addActionListener(lCambiar);
        bVolverMenu.addActionListener(lVolverMenu);

        setVisible(true);
    }
}
