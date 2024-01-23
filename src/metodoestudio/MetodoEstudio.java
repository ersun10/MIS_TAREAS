/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoestudio;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import metodoestudio.Puzzle;

/**
 *
 * @author luisj
 */
public class MetodoEstudio extends javax.swing.JFrame {

    // Componenetes menú
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenu jmnuArchivo;
    private javax.swing.JMenu jmnuOpciones;
    private javax.swing.JMenu jmnuTemas;
    private javax.swing.JMenu jmnuAyuda;
    private javax.swing.JMenuItem itemAcerca;
    private javax.swing.JMenuItem itemInstrucciones;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JCheckBoxMenuItem itemDefecto;
    private javax.swing.JCheckBoxMenuItem itemCarol;
    // Componentes paneles y botones
    public javax.swing.JButton jbtTarea;
    public javax.swing.JButton jbtRelax;
    private javax.swing.JButton jbtLogros;
    private javax.swing.JPanel panelBotones;
    public java.util.ArrayList<java.awt.Color> listColores;
    private Puzzle puzzle;
    private boolean modal;
    private int dimension2;
    private int tiempo;

    // Componentes tarea
    private javax.swing.JLabel lblTarea;
    private javax.swing.JLabel lblTiempo;
    private javax.swing.JLabel lblDificultad;
    private javax.swing.JComboBox comboOpcion;
    private javax.swing.JComboBox comboTiempo;
    private int minutosTarea;
    private javax.swing.JComboBox comboDificultad;
    private int dificultad;
    private javax.swing.JButton jbtGrafico;

    // Instancias
    private Relax relax;
    public boolean relaxOpen;
    private Grafico grafico;
    private Logros logros;
    private boolean tableroNormal = false;

    public MetodoEstudio() {
        setTitle("Mis Tareas");
        setSize(350, 490);
        setResizable(false);
        setLocationRelativeTo(null);
        listColores = new java.util.ArrayList<>(2);
        modal = true;
        dificultad = 3;
        minutosTarea = 0;
        initComponents();
        obtenerColores();
        initCombos();

    }

    private void initComponents() {
        relaxOpen = false;
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        getContentPane().setLayout(null);

        java.awt.event.ActionListener itemTemas = new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTemasActionPerformed(evt);
            }
        };

        // Inicialización de componentes del menú
        barraMenu = new javax.swing.JMenuBar();
        jmnuArchivo = new javax.swing.JMenu();
        jmnuOpciones = new javax.swing.JMenu();
        jmnuTemas = new javax.swing.JMenu();
        jmnuAyuda = new javax.swing.JMenu();
        itemInstrucciones = new javax.swing.JMenuItem();
        itemAcerca = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();
        itemDefecto = new javax.swing.JCheckBoxMenuItem();
        itemCarol = new javax.swing.JCheckBoxMenuItem();
        // Inicalización de componentes
        jbtTarea = new javax.swing.JButton();
        jbtRelax = new javax.swing.JButton();
        jbtLogros = new javax.swing.JButton();
        panelBotones = new javax.swing.JPanel();

        lblTarea = new javax.swing.JLabel("Tarea: ");
        comboDificultad = new javax.swing.JComboBox();
        comboTiempo = new javax.swing.JComboBox();
        comboOpcion = new javax.swing.JComboBox();
        jbtGrafico = new javax.swing.JButton();
        lblTiempo = new javax.swing.JLabel("Minutos tarea:");
        lblDificultad = new javax.swing.JLabel("Dificultad juego:");

        // Propiedades de componentespanelBotones.setBorder(new javax.swing.border.LineBorder(Color.WHITE, 2));
        panelBotones.setBounds(30, 30, 270, 360);
        lblTarea.setBounds(20, 20, 100, 30);
        lblTarea.setForeground(java.awt.Color.WHITE);
        lblTiempo.setBounds(20, 60, 100, 30);
        lblTiempo.setForeground(java.awt.Color.WHITE);
        lblDificultad.setBounds(20, 100, 100, 30);
        lblDificultad.setForeground(java.awt.Color.WHITE);
        comboOpcion.setBounds(130, 20, 110, 30);
        comboTiempo.setBounds(130, 60, 110, 30);
        comboDificultad.setBounds(130, 100, 110, 30);
        jbtTarea.setText("Iniciar tarea");
        jbtTarea.setBounds(60, 160, 150, 30);
        jbtTarea.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                // Obteniendo las propiedades para el juego
                String titulo = comboOpcion.getSelectedItem().toString();

                if (comboDificultad.getSelectedItem().equals("Sin juego")){
                    tableroNormal = true;
                }
                
                if (comboDificultad.getSelectedItem().equals("Fácil")) {
                    dificultad = 3;
                    tableroNormal = false;
                } else if (comboDificultad.getSelectedItem().equals("Difícil")) {
                    dificultad = 5;
                    tableroNormal = false;
                } else if (comboDificultad.getSelectedItem().equals("Imposible")) {
                    dificultad = 7;
                    tableroNormal = false;
                }

                minutosTarea = Integer.parseInt(comboTiempo.getSelectedItem().toString());

                dimension2 = dificultad;
                tiempo = minutosTarea;
                try {

                    puzzle = new Puzzle(null, false, listColores, titulo, dimension2, tiempo, tableroNormal);
                    puzzle.setVisible(true);
                    jbtTarea.setEnabled(false);

                    puzzle.addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowClosing(java.awt.event.WindowEvent evt) {
                            jbtTarea.setEnabled(true);
                            tableroNormal = false;
                        }
                    });
                } catch (Exception e) {
                }

            }
        });
        jbtRelax.setText("Música");
        jbtRelax.setBounds(60, 210, 150, 30);
        jbtRelax.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                if (relaxOpen == false) {
                    setRelax(true);
                    relax = new Relax(null, false, listColores);
                    relax.setVisible(true);
                    relax.addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowClosing(java.awt.event.WindowEvent evt) {
                            relax = null;
                            relaxOpen = false;
                        }
                    });

                } else {
                    relax.setVisible(true);
                }

            }
        });

        jbtLogros.setText("Logros");
        jbtLogros.setBounds(60, 260, 150, 30);
        jbtLogros.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logros = new Logros(null, modal, listColores);
                logros.setVisible(true);
            }
        });

        jbtGrafico.setText("Gráfico");
        jbtGrafico.setBounds(60, 310, 150, 30);
        jbtGrafico.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grafico = new Grafico(null, modal, listColores);
                grafico.setVisible(true);
            }
        });

        // MENÚ
        jmnuArchivo.setText("Archivo");
        jmnuArchivo.setMnemonic('A');
        jmnuOpciones.setText("Opciones");
        jmnuOpciones.setMnemonic('O');
        jmnuTemas.setText("Temas");
        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        itemDefecto.setText("Azul");
        itemDefecto.setSelected(true);
        itemDefecto.addActionListener(itemTemas);
        itemCarol.setText("Morado");
        itemCarol.addActionListener(itemTemas);

        jmnuAyuda.setText("Ayuda");
        itemInstrucciones.setText("Instrucciones");
        itemInstrucciones.setMnemonic('I');
        itemInstrucciones.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String texto = "Botón 'Iniciar tarea': \n"
                        + "Comienza una nueva tarea usando los datos seleccionados\n"
                        + "en las listas desplegables; el tiempo de la tarea comenzará de inmediato.\n"
                        + "Una vez en la ventana de la tarea podrá parar y reanudar el tiempo. \n"
                        + "Al completar el tiempo podrá realizar el minijuego para descansar a la vez\n"
                        + "que mantiene su mente activa. Este minijuego no es obligatorio completarlo\n"
                        + "puesto que la tarea se guardará automáticamente al completarse el tiempo."
                        + "\n\n"
                        + "Con el botón de 'Música' abrirá un minireproductor de funciones básicas\n"
                        + "en el que, por defecto, contendrá listas de música relajante.\n\n"
                        + "El botón de 'Logros' mostrará en forma de logros(casillas) el número de horas totales. \n\n"
                        + "Para finalizar con el botón de 'Gráfico' podrá realizar gráficos de torta\n"
                        + "de sus tareas totales o por meses para ver y comparar su progreso.\n";

                javax.swing.JTextArea textoAcerca = new javax.swing.JTextArea(texto);
                javax.swing.JOptionPane.showMessageDialog(null, textoAcerca, "Instrucciones", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });
        itemAcerca.setText("Acerca de..");
        itemAcerca.setMnemonic('A');
        itemAcerca.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String texto = "Esta aplicación ha sido desarrollada "
                        + "con finalidad de practicar de manera no profesional,\n"
                        + "por lo que es posible que contenga errores de código y/o diseño. \n"
                        + "Quizás mas adelante se desarrolle alguna versión con mejoras y nuevas \n"
                        + "funcionalidades pero de momento queda descartado. \n"
                        + "Para más información o sugerencias puede ponerse en contacto con el desarrollador: \n \n"
                        + "luisjose_ev@hotmail.com\n \n"
                        + "Gracias por usar la aplicación ;)";
                javax.swing.JTextArea textoAcerca = new javax.swing.JTextArea(texto);
                javax.swing.JOptionPane.showMessageDialog(null, textoAcerca, "Información de aplicación", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // Añadiendo objetos al menú
        barraMenu.add(jmnuArchivo);
        barraMenu.add(jmnuOpciones);
        barraMenu.add(jmnuAyuda);
        jmnuArchivo.add(itemSalir);
        jmnuOpciones.add(jmnuTemas);
        jmnuTemas.add(itemDefecto);
        jmnuTemas.add(itemCarol);
        jmnuAyuda.add(itemInstrucciones);
        jmnuAyuda.add(itemAcerca);
        // Añadiendo eventos a los controles
        // Añadiendo componentes al jpanel
        setJMenuBar(barraMenu);
        panelBotones.setLayout(null);
        panelBotones.add(lblTarea);
        panelBotones.add(lblTiempo);
        panelBotones.add(lblDificultad);
        panelBotones.add(comboOpcion);
        panelBotones.add(comboDificultad);
        panelBotones.add(comboTiempo);
        panelBotones.add(jbtTarea);
        panelBotones.add(jbtRelax);
        panelBotones.add(jbtLogros);
        panelBotones.add(jbtGrafico);
        // Añadiendo componentes al formulario
        getContentPane().add(panelBotones);
    }

    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }

    public void setRelax(boolean relax_Open) {
        this.relaxOpen = relax_Open;
    }

    public boolean getRelax() {
        return relaxOpen;
    }

    private void cambiarTemas(java.util.ArrayList<java.awt.Color> listColores) {

        listColores.clear();
        java.awt.Color jbtRosa, backMorado;

        jbtRosa = new java.awt.Color(247, 146, 241);
        backMorado = new java.awt.Color(102, 0, 102);

        // Añadiendo colores a la lista
        listColores.add(backMorado);
        listColores.add(jbtRosa);
        // Cambiando colores
        jbtTarea.setBackground(listColores.get(1));
        jbtRelax.setBackground(listColores.get(1));
        jbtLogros.setBackground(listColores.get(1));
        jbtGrafico.setBackground(listColores.get(1));
        getContentPane().setBackground(listColores.get(0));
        panelBotones.setBackground(listColores.get(0));
    }

    private void obtenerColores() {
        listColores.clear();
        //java.awt.Color color1 = new java.awt.Color(55, 42, 253);
        java.awt.Color color1 = new java.awt.Color(0, 0, 102);
        java.awt.Color color2 = new java.awt.Color(104, 243, 239);
        listColores.add(color1);
        listColores.add(color2);
        // Cambiando colores
        getContentPane().setBackground(listColores.get(0));
        panelBotones.setBorder(new javax.swing.border.LineBorder(Color.WHITE, 2));
        panelBotones.setBackground(listColores.get(0));
        jbtTarea.setBackground(listColores.get(1));
        jbtRelax.setBackground(listColores.get(1));
        jbtLogros.setBackground(listColores.get(1));
        jbtGrafico.setBackground(listColores.get(1));

    }

    private void itemTemasActionPerformed(java.awt.event.ActionEvent evt) {
        javax.swing.JCheckBoxMenuItem obj = (javax.swing.JCheckBoxMenuItem) evt.getSource();

        if (obj.getText().compareTo("Azul") == 0) {
            listColores.clear();
            obtenerColores();
            itemDefecto.setSelected(true);
            itemCarol.setSelected(false);
            if (relax != null) {
                relax.actualizarTema(listColores);
            }
            if (puzzle != null) {
                puzzle.actualizarTema(listColores);
            }
        } else if (obj.getText().compareTo("Morado") == 0) {
            cambiarTemas(listColores);
            itemDefecto.setSelected(false);
            itemCarol.setSelected(true);
            if (relax != null) {
                relax.actualizarTema(listColores);
            }
            if (puzzle != null) {
                puzzle.actualizarTema(listColores);
            }
        }

    }

    private void initCombos() {
        comboOpcion.addItem("Estudio");
        comboOpcion.addItem("Trabajo");
        comboOpcion.addItem("Tareas del hogar");
        comboOpcion.addItem("Ejercicio");
        comboOpcion.addItem("Descanso");
        comboOpcion.addItem("Afición");
        comboOpcion.addItem("Otros");

        for (int i = 10; i <= 180; i += 10) {
            comboTiempo.addItem(i);
        }

        comboDificultad.addItem("Sin juego");
        comboDificultad.addItem("Fácil");
        comboDificultad.addItem("Difícil");
        comboDificultad.addItem("Imposible");
    }

    public static void main(String[] args) {

        // Cambiar diseño del formulario
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception ex) {
                    System.out.println("No se pudo cargar el aspecto Nimbus");
                }
            }
        }
        new MetodoEstudio().setVisible(true);
    }

}
