package main.presentation.views;

import main.domain.classes.exceptions.listaCorta;
import main.domain.classes.exceptions.listaExistente;
import main.presentation.controllers.CtrlPresentacion;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Vista que permite crear una lista de palabras a partir de un texto que introducimos, tenemos que poner el nombre
 * de la lista de palabras que generará, del alfabeto que seleccionamos y el texto que introducimos
 * @author Rafael Ibáñez Rodríguez
 */
public class VistaCrearLIstaTexto extends JFrame{

        private JPanel contenedor = new JPanel();

        private JLabel txtNombre = new JLabel("Nombre");

        private JTextArea areaNombre = new JTextArea("");

        private JLabel Alfabeto = new JLabel("Alfabeto");

        private JComboBox cbAlfabeto = new JComboBox();

        private JLabel txtTexto = new JLabel("Texto");

        private JTextArea areaTexto = new JTextArea("");

        private JButton bCrear = new JButton("Crear");

        private JButton bVolverMenu = new JButton("Volver Menú");

        private JFrame frame = new JFrame("JFrame");

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
     * Ventana que emerge en caso de error y en caso de que la lista se creé correctamente
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
     * Creadora de la vista VistaCrearListaTexto
     */
    public VistaCrearLIstaTexto() {
            iniFrame();
            setTitle("Crear Lista con un Texto");

            txtNombre.setBounds(50, 35, 200, 20);
            add(txtNombre);

            areaNombre.setBounds(200,35,200,20);
            add(areaNombre);

            Alfabeto.setBounds(50, 75, 200, 20);
            add(Alfabeto);

            cbAlfabeto.setBounds(200, 75, 200, 20);
            cbAlfabeto.addItem("");
            cbAlfabeto.addItem("Cylliric");
            cbAlfabeto.addItem("Greek");
            cbAlfabeto.addItem("Hebrew");
            cbAlfabeto.addItem("Latin");
            cbAlfabeto.addItem("Latin_CAT");
            cbAlfabeto.addItem("Latin_ESP");
            add(cbAlfabeto);

            txtTexto.setBounds(50, 115, 200, 20);
            add(txtTexto);

            areaTexto.setBounds(200,115,500,100);
            areaTexto.setLineWrap(true);
            JScrollPane scrollPane = new JScrollPane(areaTexto);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane);
            add(areaTexto);

            bCrear.setBounds(200, 240, 200, 20);
            add(bCrear);

            bVolverMenu.setBounds(200, 290, 200, 20);
            add(bVolverMenu);

            contenedor.setBackground(Color.decode("#f8c8dc"));
            add(contenedor);

            ActionListener lCrear = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombre = areaNombre.getText();
                    String texto = areaTexto.getText();
                    for (char s : texto.toCharArray()) {
                        if (s == '\n') texto = texto.replace("\n", " ");
                        if (s == '\t') texto = texto.replace("\t", " ");
                        if (s == '\r') texto = texto.replace("\r", " ");
                    }
                    String alfabeto = (String) cbAlfabeto.getSelectedItem();
                    try {
                        CtrlPresentacion.crearListaTexto(nombre, texto, alfabeto);
                    } catch (listaCorta ex) {
                        pop_up("Error", ex.getMessage());
                        throw new RuntimeException(ex);
                    } catch (listaExistente ex) {
                        pop_up("Error", ex.getMessage());
                        throw new RuntimeException(ex);
                    }
                    pop_up("Lista creada", "La lista se ha creado correctamente");
                }
            };

            ActionListener lVolverMenu = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CtrlPresentacion.iniPresentacion();
                    setVisible(false);
                }
            };

            bCrear.addActionListener(lCrear);
            bVolverMenu.addActionListener(lVolverMenu);


            setVisible(true);
        }
}
