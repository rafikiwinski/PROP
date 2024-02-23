package main.presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import main.domain.classes.exceptions.listaCorta;
import main.domain.classes.exceptions.listaExistente;
import main.presentation.controllers.CtrlPresentacion;

/**
 * Vista que permite crear una lista introduciendo manualmente las palabras con sus respectivas frecuencias, para esto
 * tienes que seleccionar un alfabeto, especificar el nombre que tendrá tu lista e ir yendo introduciendo las palabras con
 * sus frecuencias
 * @author Rafael Ibáñez Rodríguez
 */
public class VistaCrearListaManualmente extends JFrame {

    private JFrame frame = new JFrame();

    private JPanel contenedor = new JPanel();

    private JLabel Nombre = new JLabel("Nombre");

    private JTextArea areaNombre = new JTextArea("");

    private JLabel Alfabeto = new JLabel("Alfabeto");

    private JComboBox cbAlfabeto = new JComboBox();

    private JLabel Palabra = new JLabel("Palabra");

    private JTextArea areaPalabra = new JTextArea("");

    private JLabel Frequencia = new JLabel("Frecuencia");

    private JTextArea areaFrequencia = new JTextArea("");

    private JButton bAnadir = new JButton("Añadir");

    private JButton bCrear = new JButton("Crear");

    private JLabel Contador = new JLabel("Contador: ");

    private int n = 0;

    private JLabel Actual = new JLabel(String.valueOf(n));

    private JButton bVolverMenu = new JButton("Volver Menú");

    private Map<String, Integer> mapa = new HashMap<>();

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
     * Ventana que emerge en caso de error y en caso de que se cree correctamente la lista
     * @param status ERROR o EXITO
     * @param text El porque del error o mensaje de éxito
     * */
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
     * Creadora de la vista VistaCrearListaManualmente
     */
    public VistaCrearListaManualmente(){
        iniFrame();
        setTitle("Crear Lista Manualmente");

        Nombre.setBounds(50, 35, 200, 20);
        add(Nombre);

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

        Palabra.setBounds(50, 115, 200, 20);
        add(Palabra);

        areaPalabra.setBounds(200,115,200,20);
        add(areaPalabra);

        Contador.setBounds(425, 115, 100, 20);
        add(Contador);

        Actual.setBounds(550, 115, 100, 20);
        add(Actual);

        Frequencia.setBounds(50, 155, 200, 20);
        add(Frequencia);

        areaFrequencia.setBounds(200,155,200,20);
        add(areaFrequencia);

        bAnadir.setBounds(425, 155, 200, 20);
        add(bAnadir);

        bCrear.setBounds(200, 195, 200, 20);
        add(bCrear);

        bVolverMenu.setBounds(200, 235, 200, 20);
        add(bVolverMenu);

        ActionListener lAnadirPalabra = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palabra = areaPalabra.getText();
                String frequencia = areaFrequencia.getText();
                try{
                    Integer.parseInt(frequencia);
                }
                catch (NumberFormatException ex){
                    pop_up("Error", "La frecuencuia tiene que ser un número");
                    throw new RuntimeException(ex);
                }
                if (palabra.equals("")) {
                    pop_up("Error", "Introduce una palabra");

                } else if (frequencia.equals("")) {
                    pop_up("Error", "Introduce una freqüència");
                } else if (mapa.get(palabra) != null) {
                    pop_up("Error", "La palabra ya existe en la lista");
                } else if (Integer.parseInt(frequencia) < 0) {
                    pop_up("Error", "La freqüència tiene que ser mayor que 0");
                } else {
                    palabra.toLowerCase();
                    mapa.put(palabra, Integer.parseInt(frequencia));
                    areaPalabra.setText("");
                    areaFrequencia.setText("");
                    n = n + 1;
                    Actual.setText(String.valueOf(n));
                }
            }
        };

        ActionListener lCrear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (areaNombre.getText().equals("")) {
                    pop_up("Error", "Introduce un nombre");
                } else if (cbAlfabeto.getSelectedItem().toString().equals("")) {
                    pop_up("Error", "Introduce un alfabeto");
                } else if (mapa.isEmpty()) {
                    pop_up("Error", "Se tienen que introducir al menos 20 palabras");
                }
                else {
                    try {
                        CtrlPresentacion.crearListaMapa(areaNombre.getText(), mapa, cbAlfabeto.getSelectedItem().toString());
                    } catch (listaCorta ex) {
                        pop_up("Error", "Necesitas al menos 20 palabras");
                        throw new RuntimeException(ex);
                    } catch (listaExistente ex) {
                        pop_up("Error", ex.getMessage());
                        throw new RuntimeException(ex);
                    }
                    pop_up("Lista Creada", "La lista ha sido creada correctamente");
                }
            }
        };

        ActionListener lVolverMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacion.iniPresentacion();
                setVisible(false);
            }
        };

        bAnadir.addActionListener(lAnadirPalabra);
        bCrear.addActionListener(lCrear);
        bVolverMenu.addActionListener(lVolverMenu);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);

        setVisible(true);
    }
}
