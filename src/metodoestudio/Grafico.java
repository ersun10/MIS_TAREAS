/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoestudio;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.time.ZoneId;
import modelo.Conexion;

/**
 *
 * @author luisj
 */
public class Grafico extends javax.swing.JDialog {

    private javax.swing.JPanel panelGrafico;
    private javax.swing.JLabel lblGrafico;
    private java.util.ArrayList<java.awt.Color> listColores;
    private java.awt.Graphics g;
    private javax.swing.JButton jbtGraficar;
    private boolean pintar;
    private javax.swing.JComboBox<String> comboMonth;
    private javax.swing.JComboBox<Integer> comboYear;
    private javax.swing.JButton jbtComparar;
    private javax.swing.JButton jbtTotales;

    private int grafico1;
    private int grafico2;
    private int xCuadrado, xTexto, xCuadradoSeg, xTextoSeg, xGrafico1, yGrafico1, xGrafico2, yGrafico2;
    private boolean comparar;
    private String consulta;
    private int fontSizelbl;

    public String fechaMes1, fechaMes2;
    public int fechaYear1, fechaYear2;
    private int fechaYear1Ant;
    private String fechaMes1Ant;
    private java.util.Date fecha = new java.util.Date();
    private javax.swing.JLabel lblGrafico1, lblGrafico2, lblLeyenda1, lblLeyenda2;
    private int year;
    private boolean totalActivo;

    private int[] valores = {0, 0, 0, 0, 0, 0, 0};
    private int[] valores2 = {0, 0, 0, 0, 5, 0, 0};

    private int valor1Seg, valor2Seg, valor3Seg, valor4Seg, valor5Seg, valor6Seg, valor7Seg, valorTotalSeg;
    private int valor1, valor2, valor3, valor4, valor5, valor6, valor7, valorTotal;
    
    // Instancias
    private jdialogFecha dialogoFecha;
    private Conexion conexion;

    public Grafico() {
        conexion = new Conexion();
    }

    public Grafico(java.awt.Frame parent, boolean modal, java.util.ArrayList<java.awt.Color> listaColores) {
        super(parent, modal);
        this.setTitle("Gráficos");
        this.setSize(700, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.listColores = listaColores;
        grafico1 = 360;
        grafico2 = 250;
        xCuadrado = 470;
        xCuadradoSeg = 880;
        xTexto = 510;
        xTextoSeg = 920;
        xGrafico1 = 40;
        yGrafico1 = 150;
        xGrafico2 = 350;
        yGrafico2 = 190;
        pintar = false;
        comparar = false;
        totalActivo = false;
        fontSizelbl = 15;
        fechaMes1 = "Enero";
        fechaYear1 = 2022;
        ZoneId timeZone = ZoneId.systemDefault();
        java.time.LocalDate getLocalDate = fecha.toInstant().atZone(timeZone).toLocalDate();
        year = getLocalDate.getYear();
        conexion = new Conexion();
        initComponents();
        initCombos();

    }

    private void initComponents() {
        this.getContentPane().setLayout(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        // Iniciando componentes
        panelGrafico = new javax.swing.JPanel();
        lblGrafico = new javax.swing.JLabel();
        jbtGraficar = new javax.swing.JButton();
        comboMonth = new javax.swing.JComboBox<String>();
        comboYear = new javax.swing.JComboBox<Integer>();
        jbtComparar = new javax.swing.JButton();
        jbtTotales = new javax.swing.JButton();

        lblGrafico1 = new javax.swing.JLabel();
        lblGrafico2 = new javax.swing.JLabel();
        lblLeyenda1 = new javax.swing.JLabel();
        lblLeyenda2 = new javax.swing.JLabel();

        this.getContentPane().setBackground(listColores.get(0));
        panelGrafico.setBackground(java.awt.Color.WHITE);

        lblGrafico.setText("Seleccione una fecha...");
        lblGrafico.setFont(new java.awt.Font("MV Boli", java.awt.Font.PLAIN, 24));
        lblGrafico.setForeground(java.awt.Color.WHITE);
        lblGrafico.setBounds(90, 20, 500, 30);
        panelGrafico.setLayout(null);
        panelGrafico.setBounds(20, 50, 550, 450);

        lblGrafico1.setBounds(100, 100, 130, 30);
        lblGrafico1.setForeground(listColores.get(1));
        lblGrafico1.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, fontSizelbl));
        lblGrafico1.setVisible(false);

        lblGrafico2.setBounds(420, 100, 130, 30);
        lblGrafico2.setForeground(listColores.get(1));
        lblGrafico2.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, fontSizelbl));
        lblGrafico2.setVisible(false);

        lblLeyenda1.setBounds(680, 100, 130, 30);
        lblLeyenda1.setForeground(listColores.get(1));
        lblLeyenda1.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, fontSizelbl));
        lblLeyenda1.setVisible(false);

        lblLeyenda2.setBounds(910, 100, 130, 30);
        lblLeyenda2.setForeground(listColores.get(1));
        lblLeyenda2.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, fontSizelbl));
        lblLeyenda2.setVisible(false);

        jbtGraficar.setText("Realizar gráfico");
        jbtGraficar.setBackground(listColores.get(1));
        jbtGraficar.setBounds(330, 510, 130, 30);
        jbtGraficar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaMes1Ant = comboMonth.getSelectedItem().toString();
                fechaYear1Ant = Integer.parseInt(comboYear.getSelectedItem().toString());
                consulta = "Select * FROM tareas WHERE month = '" + fechaMes1Ant + "' AND year = " + fechaYear1Ant;
                conexion = new Conexion(consulta);
                valores = conexion.datosMes();
                try{
                    conexion.cerrarConexion();
                }catch(Exception e){
                    System.out.println("No se pudo cerrar la conexión");
                }
                
                // Aquí para rellenar los valores del gráfico 1
                valor1 = valores[0];
                valor2 = valores[1];
                valor3 = valores[2];
                valor4 = valores[3];
                valor5 = valores[4];
                valor6 = valores[5];
                valor7 = valores[6];
                valorTotal = valor1 + valor2 + valor3 + valor4 + valor5 + valor6 + valor7;
                if(valorTotal == 0) javax.swing.JOptionPane.showMessageDialog(null, "No existen tareas en el mes seleccionado", "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                if(valorTotal == 0) comboMonth.setSelectedItem(fechaMes1);
                if(valorTotal == 0) comboYear.setSelectedItem(fechaYear1);
                if(valorTotal == 0){
                    consulta = "Select * FROM tareas WHERE month = '" + fechaMes1 + "' AND year = " + fechaYear1;
                conexion = new Conexion(consulta);
                valores = conexion.datosMes();
                try{
                    conexion.cerrarConexion();
                }catch(Exception e){
                    System.out.println("No se pudo cerrar la conexión");
                }
                }
                if(valorTotal == 0) return;
                fechaMes1 = fechaMes1Ant;
                fechaYear1 = fechaYear1Ant;
                totalActivo = false;
                lblGrafico.setText("Gráfico mensual: " + comboMonth.getSelectedItem() + " de " + comboYear.getSelectedItem());
                lblGrafico.setBounds(90, 20, 500, 30);
                xGrafico1 = 40;
                yGrafico1 = 130;
                int width = 700;
                int weight = 600;
                xCuadrado = 470;
                xTexto = 510;
                redimensionar(width, weight);
                comparar = false;
                grafico1 = 360;

                pintar = true;
                lblGrafico1.setVisible(false);
                lblGrafico2.setVisible(false);
                lblLeyenda1.setVisible(false);
                lblLeyenda2.setVisible(false);
                repaint();
            }
        });
        comboMonth.setBounds(30, 510, 150, 30);
        comboYear.setBounds(180, 510, 150, 30);
        jbtComparar.setText("Comparar");
        jbtComparar.setBackground(listColores.get(1));
        jbtComparar.setBounds(520, 470, 130, 30);
        jbtComparar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!pintar) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Seleccione un gráfico principal.");
                }
                if (!pintar) {
                    return;
                }
                
                valor1 = valores[0];
                valor2 = valores[1];
                valor3 = valores[2];
                valor4 = valores[3];
                valor5 = valores[4];
                valor6 = valores[5];
                valor7 = valores[6];
                valorTotal = valor1 + valor2 + valor3 + valor4 + valor5 + valor6 + valor7;
                if(valorTotal == 0) javax.swing.JOptionPane.showMessageDialog(null, "Realice primero el gráfico principal", "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                if(valorTotal == 0) return;

                dialogoFecha = new jdialogFecha(null, true, listColores, year);
                fechaMes2 = "";
                fechaYear2 = 0;
                dialogoFecha.setVisible(true);
                fechaMes2 = dialogoFecha.getMesUser();
                fechaYear2 = dialogoFecha.getYearUser();
                if (fechaMes2 == "" || fechaYear2 == 0) {
                    return;
                }
                consulta = "Select * FROM tareas WHERE month = '" + fechaMes2 + "' AND year = " + fechaYear2;
                conexion = new Conexion(consulta);
                valores2 = conexion.datosMes();
                try{
                    conexion.cerrarConexion();
                }catch(Exception e){
                    System.out.println("No se pudo cerrar la conexión");
                }
                // Aquí para rellenar los valores del grafico 2
                valor1Seg = valores2[0];
                valor2Seg = valores2[1];
                valor3Seg = valores2[2];
                valor4Seg = valores2[3];
                valor5Seg = valores2[4];
                valor6Seg = valores2[5];
                valor7Seg = valores2[6];
                valorTotalSeg = valor1Seg + valor2Seg + valor3Seg + valor4Seg + valor5Seg + valor6Seg + valor7Seg;
                if(valorTotalSeg == 0) javax.swing.JOptionPane.showMessageDialog(null, "No existen tareas en el mes a comparar", "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                if(valorTotalSeg == 0) return;

                if(totalActivo == true){
                    lblGrafico.setText("Comparación de gráficos: Total tareas - " + fechaMes2 + "/" + fechaYear2);
                } else {
                    lblGrafico.setText("Comparación de gráficos: " + fechaMes1 + "/" + fechaYear1 + " - " + fechaMes2 + "/" + fechaYear2);
                }
                
                if(totalActivo == true){
                     lblGrafico1.setText("Total tareas");
                     lblLeyenda1.setText("Total tareas");
                } else {
                     lblGrafico1.setText(fechaMes1 + "/" + fechaYear1);
                     lblLeyenda1.setText(fechaMes1 + "/" + fechaYear1);
                }
               
                comboMonth.setSelectedItem(fechaMes1);
                comboYear.setSelectedItem(fechaYear1);
                lblGrafico2.setText(fechaMes2 + "/" + fechaYear2);
                lblLeyenda2.setText(fechaMes2 + "/" + fechaYear2);
                lblGrafico.setBounds(190, 20, 700, 30);
                redimensionar(1100, 600);
                xCuadrado = 650;
                yGrafico1 = 190;
                xTexto = 690;
                grafico1 = 250;
                comparar = true;
                lblGrafico1.setVisible(true);
                lblGrafico2.setVisible(true);
                lblLeyenda1.setVisible(true);
                lblLeyenda2.setVisible(true);
                repaint();
            }
        });
        jbtTotales.setText("Total");
        jbtTotales.setBackground(listColores.get(1));
        jbtTotales.setBounds(520, 510, 130, 30);
        jbtTotales.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                consulta = "Select * FROM tareas";
                conexion = new Conexion(consulta);
                valores = conexion.datosMes();
                try{
                    conexion.cerrarConexion();
                }catch(Exception e){
                    System.out.println("No se pudo cerrar la conexión");
                }
                
                // Aquí para rellenar los valores del gráfico 1
                valor1 = valores[0];
                valor2 = valores[1];
                valor3 = valores[2];
                valor4 = valores[3];
                valor5 = valores[4];
                valor6 = valores[5];
                valor7 = valores[6];
                valorTotal = valor1 + valor2 + valor3 + valor4 + valor5 + valor6 + valor7;
                if(valorTotal == 0) javax.swing.JOptionPane.showMessageDialog(null, "No se ha realizado ninguna tarea", "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                if(valorTotal == 0) return;
                totalActivo = true;
                lblGrafico.setText("Gráfico del total de tareas realizadas");
                lblGrafico.setBounds(90, 20, 500, 30);
                xGrafico1 = 40;
                yGrafico1 = 130;
                int width = 700;
                int weight = 600;
                xCuadrado = 470;
                xTexto = 510;
                redimensionar(width, weight);
                comparar = false;
                grafico1 = 360;

                pintar = true;
                lblGrafico1.setVisible(false);
                lblGrafico2.setVisible(false);
                lblLeyenda1.setVisible(false);
                lblLeyenda2.setVisible(false);
                repaint();
            }
        });

        // Añadiendo al panel
        // Añadiendo componentes
        this.getContentPane().add(lblGrafico);
        //this.getContentPane().add(panelGrafico);
        this.getContentPane().add(jbtGraficar);
        this.getContentPane().add(jbtComparar);
        this.getContentPane().add(jbtTotales);

        this.getContentPane().add(comboMonth);
        this.getContentPane().add(comboYear);

        this.getContentPane().add(lblGrafico1);
        this.getContentPane().add(lblGrafico2);
        this.getContentPane().add(lblLeyenda1);
        this.getContentPane().add(lblLeyenda2);
    }

    private void exitForm(java.awt.event.WindowEvent evt) {
        this.setVisible(false);
        this.dispose();
    }

    public void paint(java.awt.Graphics g) {

        super.paint(g);

        if (pintar == false) {
            return;
        }

        valor1 = valores[0];
        valor2 = valores[1];
        valor3 = valores[2];
        valor4 = valores[3];
        valor5 = valores[4];
        valor6 = valores[5];
        valor7 = valores[6];
        valorTotal = valor1 + valor2 + valor3 + valor4 + valor5 + valor6 + valor7;

        String textoEstudio, textoTrabajo, textoTareas, textoGym, textoDescanso, textoAficion, textoOtros;
        textoEstudio = "Horas Estudio: ";
        textoTareas = "Horas Tareas Hogar: ";
        textoGym = "Horas Ejercicio: ";
        textoDescanso = "Horas Descanso: ";
        textoAficion = "Horas Afición: ";
        textoOtros = "Horas Otros: ";
        textoTrabajo = "Horas Trabajo: ";

        if (valor1 != 0) {
            textoEstudio = textoEstudio + valor1 / 60;
        }
        if (valor2 != 0) {
            textoTrabajo = textoTrabajo + valor2 / 60;
        }
        if (valor3 != 0) {
            textoTareas = textoTareas + valor3 / 60;
        }
        if (valor4 != 0) {
            textoGym = textoGym + valor4 / 60;
        }
        if (valor5 != 0) {
            textoDescanso = textoDescanso + valor5 / 60;
        }
        if (valor6 != 0) {
            textoAficion = textoAficion + valor6 / 60;
        }
        if (valor7 != 0) {
            textoOtros = textoOtros + valor7 / 60;
        }

        try {
            int zona1 = valor1 * 370 / valorTotal; // Aquí debería ser 360 que son los grados, pero no se pintaba bien así que le implemente un poco mas a 370
            int zona2 = valor2 * 370 / valorTotal;
            int zona3 = valor3 * 370 / valorTotal;
            int zona4 = valor4 * 370 / valorTotal;
            int zona5 = valor5 * 370 / valorTotal;
            int zona6 = valor6 * 370 / valorTotal;
            int zona7 = valor7 * 370 / valorTotal;

            g.setColor(Color.RED);
            g.fillArc(xGrafico1, yGrafico1, grafico1, grafico1, 0, zona1);
            g.fillRect(xCuadrado, 180, 30, 30);
            g.drawString(textoEstudio, xTexto, 200);

            g.setColor(Color.BLUE);
            g.fillArc(xGrafico1, yGrafico1, grafico1, grafico1, zona1, zona2);
            g.fillRect(xCuadrado, 220, 30, 30);
            g.drawString(textoTrabajo, xTexto, 240);

            g.setColor(Color.PINK);
            g.fillArc(xGrafico1, yGrafico1, grafico1, grafico1, zona1 + zona2, zona3);
            g.fillRect(xCuadrado, 260, 30, 30);
            g.drawString(textoTareas, xTexto, 280);

            g.setColor(Color.GREEN);
            g.fillArc(xGrafico1, yGrafico1, grafico1, grafico1, zona1 + zona2 + zona3, zona4);
            g.fillRect(xCuadrado, 300, 30, 30);
            g.drawString(textoGym, xTexto, 320);

            g.setColor(Color.YELLOW);
            g.fillArc(xGrafico1, yGrafico1, grafico1, grafico1, zona1 + zona2 + zona3 + zona4, zona5);
            g.fillRect(xCuadrado, 340, 30, 30);
            g.drawString(textoDescanso, xTexto, 360);

            g.setColor(Color.ORANGE);
            g.fillArc(xGrafico1, yGrafico1, grafico1, grafico1, zona1 + zona2 + zona3 + zona4 + zona5, zona6);
            g.fillRect(xCuadrado, 380, 30, 30);
            g.drawString(textoAficion, xTexto, 400);

            g.setColor(Color.WHITE);
            g.fillArc(xGrafico1, yGrafico1, grafico1, grafico1, zona1 + zona2 + zona3 + zona4 + zona5 + zona6, zona7);
            g.fillRect(xCuadrado, 420, 30, 30);
            g.drawString(textoOtros, xTexto, 440);

        } catch (ArithmeticException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Todavía no has completado ninguna tarea.");
        }

        if (comparar == false) {
            return;
        }

        String textoEstudioSeg, textoTrabajoSeg, textoTareasSeg, textoGymSeg, textoDescansoSeg, textoAficionSeg, textoOtrosSeg;
        textoEstudioSeg = "Horas Estudio: ";
        textoTareasSeg = "Horas Tareas Hogar: ";
        textoGymSeg = "Horas Ejercicio: ";
        textoDescansoSeg = "Horas Descanso: ";
        textoAficionSeg = "Horas Afición: ";
        textoOtrosSeg = "Horas Otros: ";
        textoTrabajoSeg = "Horas Trabajo: ";

        if (valor1Seg != 0) {
            textoEstudioSeg = textoEstudioSeg + valor1Seg / 60;
        }
        if (valor2Seg != 0) {
            textoTrabajoSeg = textoTrabajoSeg + valor2Seg / 60;
        }
        if (valor3Seg != 0) {
            textoTareasSeg = textoTareasSeg + valor3Seg / 60;
        }
        if (valor4Seg != 0) {
            textoGymSeg = textoGymSeg + valor4Seg / 60;
        }
        if (valor5Seg != 0) {
            textoDescansoSeg = textoDescansoSeg + valor5Seg / 60;
        }
        if (valor6Seg != 0) {
            textoAficionSeg = textoAficionSeg + valor6Seg / 60;
        }
        if (valor7Seg != 0) {
            textoOtrosSeg = textoOtrosSeg + valor7Seg / 60;
        }

        try {
            int zona1Seg = valor1Seg * 370 / valorTotalSeg; // Aquí debería ser 360 que son los grados, pero no se pintaba bien así que le implemente un poco mas a 370
            int zona2Seg = valor2Seg * 370 / valorTotalSeg;
            int zona3Seg = valor3Seg * 370 / valorTotalSeg;
            int zona4Seg = valor4Seg * 370 / valorTotalSeg;
            int zona5Seg = valor5Seg * 370 / valorTotalSeg;
            int zona6Seg = valor6Seg * 370 / valorTotalSeg;
            int zona7Seg = valor7Seg * 370 / valorTotalSeg;

            g.setColor(Color.RED);
            g.fillArc(xGrafico2, yGrafico2, grafico2, grafico2, 0, zona1Seg);
            g.fillRect(xCuadradoSeg, 180, 30, 30);
            g.drawString(textoEstudioSeg, xTextoSeg, 200);

            g.setColor(Color.BLUE);
            g.fillArc(xGrafico2, yGrafico2, grafico2, grafico2, zona1Seg, zona2Seg);
            g.fillRect(xCuadradoSeg, 220, 30, 30);
            g.drawString(textoTrabajoSeg, xTextoSeg, 240);

            g.setColor(Color.PINK);
            g.fillArc(xGrafico2, yGrafico2, grafico2, grafico2, zona1Seg + zona2Seg, zona3Seg);
            g.fillRect(xCuadradoSeg, 260, 30, 30);
            g.drawString(textoTareasSeg, xTextoSeg, 280);

            g.setColor(Color.GREEN);
            g.fillArc(xGrafico2, yGrafico2, grafico2, grafico2, zona1Seg + zona2Seg + zona3Seg, zona4Seg);
            g.fillRect(xCuadradoSeg, 300, 30, 30);
            g.drawString(textoGymSeg, xTextoSeg, 320);

            g.setColor(Color.YELLOW);
            g.fillArc(xGrafico2, yGrafico2, grafico2, grafico2, zona1Seg + zona2Seg + zona3Seg + zona4Seg, zona5Seg);
            g.fillRect(xCuadradoSeg, 340, 30, 30);
            g.drawString(textoDescansoSeg, xTextoSeg, 360);

            g.setColor(Color.ORANGE);
            g.fillArc(xGrafico2, yGrafico2, grafico2, grafico2, zona1Seg + zona2Seg + zona3Seg + zona4Seg + zona5Seg, zona6Seg);
            g.fillRect(xCuadradoSeg, 380, 30, 30);
            g.drawString(textoAficionSeg, xTextoSeg, 400);

            g.setColor(Color.WHITE);
            g.fillArc(xGrafico2, yGrafico2, grafico2, grafico2, zona1Seg + zona2Seg + zona3Seg + zona4Seg + zona5Seg + zona6Seg, zona7Seg);
            g.fillRect(xCuadradoSeg, 420, 30, 30);
            g.drawString(textoOtrosSeg, xTextoSeg, 440);

        } catch (ArithmeticException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se han realizado tareas en el mes a comparar.");
        }

    }

    private void initCombos() {
        comboMonth.addItem("Enero");
        comboMonth.addItem("Febrero");
        comboMonth.addItem("Marzo");
        comboMonth.addItem("Abril");
        comboMonth.addItem("Mayo");
        comboMonth.addItem("Junio");
        comboMonth.addItem("Julio");
        comboMonth.addItem("Agosto");
        comboMonth.addItem("Septiembre");
        comboMonth.addItem("Octubre");
        comboMonth.addItem("Noviembre");
        comboMonth.addItem("Diciembre");

        for (int i = 2022; i < year + 5; i++) {
            comboYear.addItem(i);
        }
    }

    private void redimensionar(int ancho, int alto) {
        this.setSize(ancho, alto);
    }
}
