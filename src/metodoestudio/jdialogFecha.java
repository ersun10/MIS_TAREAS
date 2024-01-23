/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoestudio;

import java.awt.event.ActionEvent;

/**
 *
 * @author luisj
 */
public class jdialogFecha extends javax.swing.JDialog{
    
    private javax.swing.JComboBox comboMonth;
    private javax.swing.JComboBox comboYear;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JButton jbtOk;
    private java.util.ArrayList<java.awt.Color> listColores;
    private int year;
    private final Grafico grafico;
    
    private int yearUser;
    private String mesUser;
    
    
    public jdialogFecha(java.awt.Frame parent, boolean modal, java.util.ArrayList<java.awt.Color> lista, int year){
        super(parent, modal);
        this.setSize(380, 180);
        grafico = new Grafico();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Selección de fecha");
        this.listColores = lista;
        this.year = year;
        yearUser = 0;
        mesUser = "";
        initComponents();
        initCombos();
    }
    
    private void initComponents(){
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(listColores.get(0));
        
        comboMonth = new javax.swing.JComboBox();
        comboYear = new javax.swing.JComboBox();
        lblMensaje = new javax.swing.JLabel();
        jbtOk = new javax.swing.JButton();
        comboMonth.setBounds(20, 50, 150, 30);
        comboYear.setBounds(200, 50, 150, 30);
        lblMensaje.setText("Seleccione mes a comparar..");
        lblMensaje.setFont(new java.awt.Font("MV Boli", java.awt.Font.ITALIC, 20));
        lblMensaje.setForeground(java.awt.Color.WHITE);
        lblMensaje.setBounds(20, 10, 300, 30);
        jbtOk.setBackground(listColores.get(1));
        jbtOk.setBounds(250, 100, 100, 30);
        jbtOk.setText("Aceptar");
        jbtOk.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesUser = comboMonth.getSelectedItem().toString();
                yearUser = Integer.parseInt(comboYear.getSelectedItem().toString());
                exitForm();
            }
        });
        
        
        
        
        this.getContentPane().add(comboMonth);
        this.getContentPane().add(comboYear);
        this.getContentPane().add(lblMensaje);
        this.getContentPane().add(jbtOk);
    }

    public int getYearUser() {
        return yearUser;
    }

    public void setYearUser(int yearUser) {
        this.yearUser = yearUser;
    }

    public String getMesUser() {
        return mesUser;
    }

    public void setMesUser(String mesUser) {
        this.mesUser = mesUser;
    }
    
    private void initCombos(){
        
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        
        for(int i = 2022; i < year + 5; i++){
            comboYear.addItem(i);
        }
        for (int i = 0; i < meses.length; i++){
            comboMonth.addItem(meses[i]);
        }
    }
    
    private void exitForm(){
        this.setVisible(false);
        this.dispose();
    }
    
}
