/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoestudio;

import java.awt.event.ActionEvent;
import modelo.Conexion;

/**
 *
 * @author luisj
 */
public class Logros extends javax.swing.JDialog {

    private int valor1;
    private int valor2;
    private int valor3;
    private int valor4;
    private int valor5;
    private int valor6;
    private int valor7;
    private int valorTotal;
    private int[] valores = {0, 0, 0, 0, 0, 0, 0};
    private String consulta;
    private javax.swing.JButton jbtAyuda;

    private javax.swing.JPanel panelEstudio;
    private javax.swing.JPanel panelTrabajo;
    private javax.swing.JPanel panelTareas;
    private javax.swing.JPanel panelGym;
    private javax.swing.JPanel panelDescanso;
    private javax.swing.JPanel panelAficion;
    private javax.swing.JPanel panelOtros;

    private javax.swing.JLabel lblEstudio;
    private javax.swing.JLabel lblTrabajo;
    private javax.swing.JLabel lblTareas;
    private javax.swing.JLabel lblGym;
    private javax.swing.JLabel lblDescanso;
    private javax.swing.JLabel lblAficion;
    private javax.swing.JLabel lblOtros;

    private javax.swing.JPanel lblColorEstudio;
    private javax.swing.JPanel lblColorTrabajo;
    private javax.swing.JPanel lblColorTareas;
    private javax.swing.JPanel lblColorGym;
    private javax.swing.JPanel lblColorDescanso;
    private javax.swing.JPanel lblColorAficion;
    private javax.swing.JPanel lblColorOtros;

    private javax.swing.JLabel lblNotaEstudio;
    private javax.swing.JLabel lblNotaTrabajo;
    private javax.swing.JLabel lblNotaTareas;
    private javax.swing.JLabel lblNotaGym;
    private javax.swing.JLabel lblNotaDescanso;
    private javax.swing.JLabel lblNotaAficion;
    private javax.swing.JLabel lblNotaOtros;

    private java.util.ArrayList<java.awt.Color> listColores;

    private javax.swing.JScrollPane jsEstudio;
    private javax.swing.JScrollPane jsTrabajo;
    private javax.swing.JScrollPane jsTareas;
    private javax.swing.JScrollPane jsGym;
    private javax.swing.JScrollPane jsDescanso;
    private javax.swing.JScrollPane jsAficion;
    private javax.swing.JScrollPane jsOtros;

    private Conexion conexion;

    public Logros(java.awt.Frame parent, boolean modal, java.util.ArrayList<java.awt.Color> lista) {

        super(parent, modal);
        this.setTitle("Logros");
        this.setSize(420, 680);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        valor1 = 0;
        valor2 = 0;
        valor3 = 0;
        valor4 = 0;
        valor5 = 0;
        valor6 = 0;
        valor7 = 0;
        valorTotal = 0;
        listColores = lista;

        initComponents();

    }

    private void initComponents() {

        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(listColores.get(0));
        // Inicializando componentes
        panelEstudio = new javax.swing.JPanel();
        panelTrabajo = new javax.swing.JPanel();
        panelTareas = new javax.swing.JPanel();
        panelGym = new javax.swing.JPanel();
        panelDescanso = new javax.swing.JPanel();
        panelAficion = new javax.swing.JPanel();
        panelOtros = new javax.swing.JPanel();
        lblEstudio = new javax.swing.JLabel();
        lblTrabajo = new javax.swing.JLabel();
        lblTareas = new javax.swing.JLabel();
        lblGym = new javax.swing.JLabel();
        lblAficion = new javax.swing.JLabel();
        lblDescanso = new javax.swing.JLabel();
        lblOtros = new javax.swing.JLabel();
        lblColorEstudio = new javax.swing.JPanel();
        lblColorTrabajo = new javax.swing.JPanel();
        lblColorTareas = new javax.swing.JPanel();
        lblColorGym = new javax.swing.JPanel();
        lblColorDescanso = new javax.swing.JPanel();
        lblColorAficion = new javax.swing.JPanel();
        lblColorOtros = new javax.swing.JPanel();
        lblNotaEstudio = new javax.swing.JLabel();
        lblNotaTrabajo = new javax.swing.JLabel();
        lblNotaTareas = new javax.swing.JLabel();
        lblNotaGym = new javax.swing.JLabel();
        lblNotaDescanso = new javax.swing.JLabel();
        lblNotaAficion = new javax.swing.JLabel();
        lblNotaOtros = new javax.swing.JLabel();
        jbtAyuda = new javax.swing.JButton();

        jsEstudio = new javax.swing.JScrollPane();
        jsTrabajo = new javax.swing.JScrollPane();
        jsTareas = new javax.swing.JScrollPane();
        jsGym = new javax.swing.JScrollPane();
        jsDescanso = new javax.swing.JScrollPane();
        jsAficion = new javax.swing.JScrollPane();
        jsOtros = new javax.swing.JScrollPane();

        jsEstudio.setBounds(0, 25, 400, 65);
        jsTrabajo.setBounds(0, 110, 400, 65);
        jsTareas.setBounds(0, 195, 400, 65);
        jsGym.setBounds(0, 280, 400, 65);
        jsDescanso.setBounds(0, 365, 400, 65);
        jsAficion.setBounds(0, 450, 400, 65);
        jsOtros.setBounds(0, 535, 400, 65);

        panelEstudio.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        panelTrabajo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        panelTareas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        panelGym.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        panelDescanso.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        panelAficion.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
        panelOtros.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));

        panelEstudio.setBackground(listColores.get(0));
        panelTrabajo.setBackground(listColores.get(0));
        panelTareas.setBackground(listColores.get(0));
        panelGym.setBackground(listColores.get(0));
        panelDescanso.setBackground(listColores.get(0));
        panelAficion.setBackground(listColores.get(0));
        panelOtros.setBackground(listColores.get(0));

        lblEstudio.setText("Estudio");
        lblEstudio.setBounds(175, 0, 100, 30);
        lblEstudio.setForeground(java.awt.Color.WHITE);
        lblColorEstudio.setBounds(220, 5, 30, 20);
        lblTrabajo.setText("Trabajo");
        lblTrabajo.setBounds(175, 85, 100, 30);
        lblTrabajo.setForeground(java.awt.Color.WHITE);
        lblColorTrabajo.setBounds(220, 90, 30, 20);
        lblTareas.setText("Tareas del hogar");
        lblTareas.setBounds(155, 170, 100, 30);
        lblTareas.setForeground(java.awt.Color.WHITE);
        lblColorTareas.setBounds(255, 175, 30, 20);
        lblGym.setText("Ejercicio");
        lblGym.setBounds(170, 255, 100, 30);
        lblGym.setForeground(java.awt.Color.WHITE);
        lblColorGym.setBounds(230, 260, 30, 20);
        lblDescanso.setText("Descanso");
        lblDescanso.setBounds(170, 340, 100, 30);
        lblDescanso.setForeground(java.awt.Color.WHITE);
        lblColorDescanso.setBounds(230, 345, 30, 20);
        lblAficion.setText("Afición");
        lblAficion.setBounds(177, 425, 100, 30);
        lblAficion.setForeground(java.awt.Color.WHITE);
        lblColorAficion.setBounds(218, 430, 30, 20);
        lblOtros.setText("Otros");
        lblOtros.setBounds(179, 510, 100, 30);
        lblOtros.setForeground(java.awt.Color.WHITE);
        lblColorOtros.setBounds(213, 515, 30, 20);
        jbtAyuda.setText("Ayuda");
        jbtAyuda.setBackground(java.awt.Color.WHITE);
        jbtAyuda.setBounds(170, 600, 70, 30);
        jbtAyuda.addActionListener( new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String textoAyuda = "Cada vez que realice 10 horas de tareas, se agregará una nueva casilla. \n"
                        + "Al llegar a determinadas horas de tareas, se cambiará el color del progreso\n"
                        + "y la nota de calificación. \n"
                        + "\n"
                        + "Leyenda:\n \n"
                        + "NM: Necesita mejorar  (Llevas poquitas horas de tareas). \n"
                        + "PA: Progresa adecuadamente  (Vas por buen camino). \n"
                        + "NT: Notable  (Ya vas cogiendo la rutina). \n"
                        + "SB: Sobresaliente  (Enhorabuena, conseguirás lo que te propongas).";
                
                javax.swing.JOptionPane.showMessageDialog(null, textoAyuda, "Información", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Añadiendo componentes al formulario
        jsEstudio.setViewportView(panelEstudio);
        jsTrabajo.setViewportView(panelTrabajo);
        jsTareas.setViewportView(panelTareas);
        jsGym.setViewportView(panelGym);
        jsDescanso.setViewportView(panelDescanso);
        jsAficion.setViewportView(panelAficion);
        jsOtros.setViewportView(panelOtros);

        // Añadiendo a la ventana principal
        this.getContentPane().add(lblEstudio);
        this.getContentPane().add(lblTrabajo);
        this.getContentPane().add(lblTareas);
        this.getContentPane().add(lblGym);
        this.getContentPane().add(lblDescanso);
        this.getContentPane().add(lblAficion);
        this.getContentPane().add(lblOtros);
        this.getContentPane().add(lblColorEstudio);
        this.getContentPane().add(lblColorTrabajo);
        this.getContentPane().add(lblColorTareas);
        this.getContentPane().add(lblColorGym);
        this.getContentPane().add(lblColorDescanso);
        this.getContentPane().add(lblColorAficion);
        this.getContentPane().add(lblColorOtros);
        this.lblColorEstudio.add(lblNotaEstudio);
        this.lblColorTrabajo.add(lblNotaTrabajo);
        this.lblColorTareas.add(lblNotaTareas);
        this.lblColorGym.add(lblNotaGym);
        this.lblColorDescanso.add(lblNotaDescanso);
        this.lblColorAficion.add(lblNotaAficion);
        this.lblColorOtros.add(lblNotaOtros);
        this.getContentPane().add(jsEstudio);
        this.getContentPane().add(jsTrabajo);
        this.getContentPane().add(jsTareas);
        this.getContentPane().add(jsGym);
        this.getContentPane().add(jsDescanso);
        this.getContentPane().add(jsAficion);
        this.getContentPane().add(jsOtros);
        this.getContentPane().add(jbtAyuda);

        // Rellenando los paneles
        mostrarLogros();
    }

    private void mostrarLogros() {

        consulta = "Select * FROM tareas";
        conexion = new Conexion(consulta);
        valores = conexion.datosMes();
        try {
            conexion.cerrarConexion();
        } catch (Exception e) {
            System.out.println("No se pudo cerrar la conexión");
        }

        // Aquí para rellenar los valores del gráfico 1
        valor1 = valores[0] / 60;
        valor2 = valores[1] / 60;
        valor3 = valores[2] / 60;
        valor4 = valores[3] / 60;
        valor5 = valores[4] / 60;
        valor6 = valores[5] / 60;
        valor7 = valores[6] / 60;

        if (valor1 >= 0 && valor1 <= 99) {
            lblColorEstudio.setBackground(java.awt.Color.RED);
            lblNotaEstudio.setText("NM");
        } else if (valor1 >= 100 && valor1 <= 499) {
            lblColorEstudio.setBackground(java.awt.Color.YELLOW);
            lblNotaEstudio.setText("PA");
        } else if (valor1 >= 500 && valor1 <= 999) {
            lblColorEstudio.setBackground(java.awt.Color.GREEN);
            lblNotaEstudio.setText("NT");
        } else if (valor1 >= 1000) {
            lblColorEstudio.setBackground(java.awt.Color.WHITE);
            lblNotaEstudio.setText("SB");
        }

        if (valor2 >= 0 && valor2 <= 99) {
            lblColorTrabajo.setBackground(java.awt.Color.RED);
            lblNotaTrabajo.setText("NM");
        } else if (valor2 >= 100 && valor2 <= 499) {
            lblColorTrabajo.setBackground(java.awt.Color.YELLOW);
            lblNotaTrabajo.setText("PA");
        } else if (valor2 >= 500 && valor2 <= 999) {
            lblColorTrabajo.setBackground(java.awt.Color.GREEN);
            lblNotaTrabajo.setText("NT");
        } else if (valor2 >= 1000) {
            lblColorTrabajo.setBackground(java.awt.Color.WHITE);
            lblNotaTrabajo.setText("SB");
        }
        if (valor3 >= 0 && valor3 <= 99) {
            lblColorTareas.setBackground(java.awt.Color.RED);
            lblNotaTareas.setText("NM");
        } else if (valor3 >= 100 && valor3 <= 499) {
            lblColorTareas.setBackground(java.awt.Color.YELLOW);
            lblNotaTareas.setText("PA");
        } else if (valor3 >= 500 && valor3 <= 999) {
            lblColorTareas.setBackground(java.awt.Color.GREEN);
            lblNotaTareas.setText("NT");
        } else if (valor3 >= 1000) {
            lblColorTareas.setBackground(java.awt.Color.WHITE);
            lblNotaTareas.setText("SB");
        }
        if (valor4 >= 0 && valor4 <= 99) {
            lblColorGym.setBackground(java.awt.Color.RED);
            lblNotaGym.setText("NM");
        } else if (valor4 >= 100 && valor4 <= 499) {
            lblColorGym.setBackground(java.awt.Color.YELLOW);
            lblNotaGym.setText("PA");
        } else if (valor4 >= 500 && valor4 <= 999) {
            lblColorGym.setBackground(java.awt.Color.GREEN);
            lblNotaGym.setText("NT");
        } else if (valor4 >= 1000) {
            lblColorGym.setBackground(java.awt.Color.WHITE);
            lblNotaGym.setText("SB");
        }
        if (valor5 >= 0 && valor5 <= 99) {
            lblColorDescanso.setBackground(java.awt.Color.RED);
            lblNotaDescanso.setText("NM");
        } else if (valor5 >= 100 && valor5 <= 499) {
            lblColorDescanso.setBackground(java.awt.Color.YELLOW);
            lblNotaDescanso.setText("PA");
        } else if (valor5 >= 500 && valor5 <= 999) {
            lblColorDescanso.setBackground(java.awt.Color.GREEN);
            lblNotaDescanso.setText("NT");
        } else if (valor5 >= 1000) {
            lblColorDescanso.setBackground(java.awt.Color.WHITE);
            lblNotaDescanso.setText("SB");
        }
        if (valor6 >= 0 && valor6 <= 99) {
            lblColorAficion.setBackground(java.awt.Color.RED);
            lblNotaAficion.setText("NM");
        } else if (valor6 >= 100 && valor6 <= 499) {
            lblColorAficion.setBackground(java.awt.Color.YELLOW);
            lblNotaAficion.setText("PA");
        } else if (valor6 >= 500 && valor6 <= 999) {
            lblColorAficion.setBackground(java.awt.Color.GREEN);
            lblNotaAficion.setText("NT");
        } else if (valor6 >= 1000) {
            lblColorAficion.setBackground(java.awt.Color.WHITE);
            lblNotaAficion.setText("SB");
        }
        if (valor7 >= 0 && valor7 <= 99) {
            lblColorOtros.setBackground(java.awt.Color.RED);
            lblNotaOtros.setText("NM");
        } else if (valor7 >= 100 && valor7 <= 499) {
            lblColorOtros.setBackground(java.awt.Color.YELLOW);
            lblNotaOtros.setText("PA");
        } else if (valor7 >= 500 && valor7 <= 999) {
            lblColorOtros.setBackground(java.awt.Color.GREEN);
            lblNotaOtros.setText("NT");
        } else if (valor7 >= 1000) {
            lblColorOtros.setBackground(java.awt.Color.WHITE);
            lblNotaOtros.setText("SB");
        }

        int totalPanelesEstudio = valor1 / 10;
        int totalPanelesTrabajo = valor2 / 10;
        int totalPanelesTareas = valor3 / 10;
        int totalPanelesGym = valor4 / 10;
        int totalPanelesDescanso = valor5 / 10;
        int totalPanelesAficion = valor6 / 10;
        int totalPanelesOtros = valor7 / 10;
        
        for (int i = 1; i < totalPanelesEstudio + 1; i++) {
            panelEstudio.add(new PanelLogros(i*10 + "", listColores));
            
        }
        for (int i = 1; i < totalPanelesTrabajo + 1; i++) {
            panelTrabajo.add(new PanelLogros(i*10 + "", listColores));
            
        }for (int i = 1; i < totalPanelesTareas + 1; i++) {
            panelTareas.add(new PanelLogros(i*10 + "", listColores));
            
        }for (int i = 1; i < totalPanelesGym + 1; i++) {
            panelGym.add(new PanelLogros(i*10 + "", listColores));
            
        }for (int i = 1; i < totalPanelesDescanso + 1; i++) {
            panelDescanso.add(new PanelLogros(i*10 + "", listColores));
            
        }for (int i = 1; i < totalPanelesAficion + 1; i++) {
            panelAficion.add(new PanelLogros(i*10 + "", listColores));
            
        }for (int i = 1; i < totalPanelesOtros + 1; i++) {
            panelOtros.add(new PanelLogros(i*10 + "", listColores));
            
        }

    }

}
