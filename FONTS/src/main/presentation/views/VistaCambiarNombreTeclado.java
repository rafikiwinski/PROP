package main.presentation.views;

import main.domain.classes.exceptions.tecladoExistente;
import main.presentation.controllers.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

/**
 * Vista que permite cambiar el nombre a un teclado, primero identificas al teclado con su nombre actual y despues el nuevo nombre
 * que le quieres otorgar
 * @author Luis Jesús Valverde Zavaleta
 */
public class VistaCambiarNombreTeclado extends JFrame {
    private final JPanel contenedor = new JPanel();

    private final JLabel lNombreActual = new JLabel("Nombre Actual:");

    private final JLabel lNombreNuevo = new JLabel("Nombre Nuevo:");

    private final JComboBox cbTeclados = new JComboBox();

    private final JTextArea areaNombreNuevo = new JTextArea();

    private final JButton bCambiar = new JButton("Cambiar");

    private final JButton bAtras = new JButton("Atrás");

    private JFrame frame = new JFrame();

    /**
     * Ventana que emerge en caso de error y en caso de que el teclado se renombre correctamente
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
                    CtrlPresentacion.iniPresentacion();
                    setVisible(false);
                }
            }
        });
        senseLoc.setVisible(true);
    }

    /**
     * Inicialización del frame
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
     * Creadora de la vista VistaCambiarNombreTeclado
     */
    public VistaCambiarNombreTeclado() {
        iniFrame();
        setResizable(false);

        lNombreActual.setBounds(25,25,120,50);
        add(lNombreActual);

        lNombreNuevo.setBounds(25, 75,120,50);
        add(lNombreNuevo);

        cbTeclados.setBounds(150,40,200,20);
        cbTeclados.addItem("");
        List<String> l = CtrlPresentacion.getTeclados();
        for (String s : l) {
            cbTeclados.addItem(s);
        }
        add(cbTeclados);

        areaNombreNuevo.setBounds(150,100,200,20);
        add(areaNombreNuevo);

        bCambiar.setBounds(350,200,100,20);
        add(bCambiar);

        bAtras.setBounds(150,200,100,20);
        add(bAtras);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);
        setVisible(true);



        ActionListener Cambiar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String nombreActual = (String) cbTeclados.getSelectedItem();
                if(areaNombreNuevo.getText().length() == 0) {
                    pop_up("Error", "El nombre nuevo no puede estar vacío");
                }
                else if(nombreActual.equals("")){
                    pop_up("Error", "No hay ningún teclado seleccionado");
                }
                try {
                    CtrlPresentacion.cambioNombreTeclado(nombreActual, areaNombreNuevo.getText());
                } catch (tecladoExistente ex) {
                    pop_up("Error", "Ya existe un teclado con ese nombre");
                    throw new RuntimeException(ex);
                }
                pop_up("Exito", "El nombre se ha cambiado correctamente");
            }
        };
        ActionListener Atras = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.iniPresentacion();
                setVisible(false);
            }
        };

        bAtras.addActionListener(Atras);
        bCambiar.addActionListener(Cambiar);
    }
}
