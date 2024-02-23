package main.presentation.views;

import main.domain.classes.exceptions.alfabetoErroneo;
import main.domain.classes.exceptions.listaNoExiste;
import main.domain.classes.exceptions.tecladoExistente;
import main.presentation.controllers.CtrlPresentacion;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * Ventana que te permite la creación de un teclado indicando el nombre del teclado, el algoritmo a utilizar,
 * el alfabeto que se basará nuestro teclado y la lista de palabras utilizadas
 * @author Luis Jesús Valverde Zavaleta
 */
public class VistaCrearTeclado extends JFrame {

    private JPanel contenedor = new JPanel();

    private JLabel algoritmo = new JLabel("Algoritmo");

    private JLabel alfabeto = new JLabel("Alfabeto");

    private JLabel listaPalabras = new JLabel("Lista de Palabras");

    private JLabel nombreTeclado = new JLabel("Nombre Teclado");

    private JComboBox cbAlgoritmo = new JComboBox();

    private JComboBox cbAlfabeto = new JComboBox();

    private JComboBox cbListas = new JComboBox();


    private JTextArea areaNombreTeclado = new JTextArea();

    private final JButton bCrear = new JButton("Crear");

    private final JButton bVolverMenu = new JButton("Volver Menú");

    private JFrame frame = new JFrame("JFrame");

    /**
     * Ventana emergenete en caso de error
     * @param status ERROR
     * @param text Muestra el porque del error
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(900, 600);
        int height = (screenSize.height-600)/2;
        int width = (screenSize.width-900)/2;
        setLocation(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }


    /**
     * Creadora de la vista VistaCrearTeclado
     */
    public VistaCrearTeclado(){
        iniFrame();
        setTitle("Crear Teclado");


        nombreTeclado.setBounds(50, 35, 200, 20);
        add(nombreTeclado);

        areaNombreTeclado.setBounds(200,35,200,20);
        add(areaNombreTeclado);


        algoritmo.setBounds(50, 75, 200, 20);
        add(algoritmo);

        cbAlgoritmo.setBounds(200, 75, 200, 20);
        cbAlgoritmo.addItem("");
        cbAlgoritmo.addItem("QAP");
        cbAlgoritmo.addItem("Genetic");
        add(cbAlgoritmo);


        alfabeto.setBounds(50,125, 200, 20);
        add(alfabeto);

        cbAlfabeto.setBounds(200, 125, 200, 20);
        cbAlfabeto.addItem("");
        cbAlfabeto.addItem("Cylliric");
        cbAlfabeto.addItem("Greek");
        cbAlfabeto.addItem("Hebrew");
        cbAlfabeto.addItem("Latin");
        cbAlfabeto.addItem("Latin_CAT");
        cbAlfabeto.addItem("Latin_ESP");
        add(cbAlfabeto);

        listaPalabras.setBounds(50, 175, 200, 20);
        add(listaPalabras);

        cbListas.setBounds(200,175,200,20);
        cbListas.addItem("");
        List<String> l = CtrlPresentacion.getListaPalabras();
        for (String s : l) {
            cbListas.addItem(s);
        }
        add(cbListas);

        bCrear.setBounds(200, 225, 200, 20);
        add(bCrear);

        bVolverMenu.setBounds(420, 225, 200, 20);
        add(bVolverMenu);

        contenedor.setBackground(Color.decode("#f8c8dc"));
        add(contenedor);
        setVisible(true);



        ActionListener lCrear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filas = 3;
                int columnas = 10;
                if(areaNombreTeclado.getText().length() == 0) {
                    pop_up("Error", "No se ha especificado ningun NOMBRE");

                }
                else if (cbAlgoritmo.getSelectedItem() == "") {
                    pop_up("Error", "No se ha especificado ningun ALGORITMO");

                }
                else if (cbAlfabeto.getSelectedItem() == "") {
                    pop_up("Error", "No se ha especificado ningun ALFABETO");

                }
                else if (cbListas.getSelectedItem() == "") {
                    pop_up("Error", "No se ha especificado ninguna LISTA DE PALABRAS");
                }
                else{
                    String nAlgoritmo = (String) cbAlgoritmo.getSelectedItem();
                    String nAlfabeto = (String) cbAlfabeto.getSelectedItem();
                    try {
                        CtrlPresentacion.crearTecladoPresentacion(areaNombreTeclado.getText(), filas, columnas, nAlgoritmo, nAlfabeto, cbListas.getSelectedItem().toString());
                    } catch (tecladoExistente ex) {
                        pop_up("Error", "Ya existe un teclado con ese nombre");
                        throw new RuntimeException(ex);
                    } catch (alfabetoErroneo ex) {
                        pop_up("Error", "El alfabeto no coincide con el de la lista");
                        throw new RuntimeException(ex);
                    } catch (listaNoExiste ex) {
                        pop_up("Error", "La lista no existe");
                        throw new RuntimeException(ex);
                    }

                    CtrlPresentacion.vistaVisualizarTeclado(CtrlPresentacion.getTeclado(areaNombreTeclado.getText()));
                    setVisible(false);
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

        bCrear.addActionListener(lCrear);
        bVolverMenu.addActionListener(lVolverMenu);
    }

}
