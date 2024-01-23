/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoestudio;

import java.awt.Dimension;

/**
 *
 * @author luisj
 */
public class PanelLogros extends javax.swing.JPanel{
   
    private javax.swing.JLabel lblTexto;
    private javax.swing.JLabel lblnumHoras;
    private javax.swing.JLabel lblImagen;
    private java.util.ArrayList<java.awt.Color> listColores;
    
    public PanelLogros(String textoLabel, java.util.ArrayList<java.awt.Color> lista){
        this.setPreferredSize(new Dimension(60, 33));
        this.listColores = lista;
        this.setBackground(listColores.get(1));
        this.setLayout(null);
        this.lblTexto = new javax.swing.JLabel();
        this.lblnumHoras = new javax.swing.JLabel();
        this.lblImagen = new javax.swing.JLabel();
        this.lblTexto.setText("Horas");
        this.lblnumHoras.setText(textoLabel);
        this.lblTexto.setFont(new java.awt.Font("MV Boli", java.awt.Font.ITALIC, 12));
        this.lblTexto.setOpaque(false);
        this.lblnumHoras.setBounds(10, 5, 40, 10);
        this.lblnumHoras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //this.lblnumHoras.setFont(new java.awt.Font("MV Boli", java.awt.Font.ITALIC, 12));
        this.lblTexto.setBounds(5, 20, 40, 10);
        this.lblTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.add(lblnumHoras);
        this.add(lblTexto);
        this.add(lblImagen);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                javax.swing.JOptionPane.showMessageDialog(null, "Logro conseguido por " + lblnumHoras.getText() + " horas de esta tarea.", "Información", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
