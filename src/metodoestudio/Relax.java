/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoestudio;

// Importaciones para el reproductor
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;

/**
 *
 * @author luisj
 */
public class Relax extends javax.swing.JDialog {

    private static MP3 mp3;
    private java.util.ArrayList<java.awt.Color> listColores;
    private boolean isStopped;
    private javax.swing.JPanel panelCat;
    private javax.swing.JPanel panelCan;
    private javax.swing.JComboBox comboCat;
    private javax.swing.JComboBox comboCan;
    private javax.swing.JButton jbtPlay;
    private javax.swing.JButton jbtPause;
    private javax.swing.JButton jbtStop;
    private String ruta;
    private javax.swing.JLabel lblCan;
    private MetodoEstudio principal;
    private javax.swing.JButton jbtAyuda;
    
    // Para rellenar el combo de las canciones personalizadas
    private java.io.File carpeta;
    private String dirCarpeta;
   
    // Prueba para cerrar los mp3s
   java.util.ArrayList<MP3> mp3s = new java.util.ArrayList<MP3>();
   private String s;
    public Relax(java.awt.Frame parent, boolean modal, java.util.ArrayList<java.awt.Color> colores) {

        super(parent, modal);
        principal = new MetodoEstudio();
        principal.jbtRelax.setEnabled(false);
        this.listColores = new java.util.ArrayList<java.awt.Color>(colores.size());
        this.listColores = colores;
        this.setSize(400, 200);
        this.setResizable(false);
        this.setLocationRelativeTo(parent);
        this.setTitle("Reproductor");
        isStopped = false;
        dirCarpeta = "Musica/Naturaleza";
        
        initComponents();
        initCombos();
    }

    private void exitDialog(java.awt.event.WindowEvent evt) {
        principal.relaxOpen = false;
        if(mp3 != null) mp3.cerrar();
        if(mp3 != null) mp3 = null;
        isStopped = true;
        this.setVisible(false);
        this.dispose();
    }

    private void initComponents() {
        
        getContentPane().setLayout(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitDialog(evt);
            }
        });
        
        // Método para actualizar el jcombobox de las canciones
        java.awt.event.ItemListener combo = new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combosItemListener(evt);
            }
        };

        getContentPane().setLayout(new java.awt.GridLayout(2, 2));

        panelCat = new javax.swing.JPanel();
        panelCan = new javax.swing.JPanel();
        comboCat = new javax.swing.JComboBox();
        comboCan = new javax.swing.JComboBox();
        jbtPlay = new javax.swing.JButton();
        jbtPause = new javax.swing.JButton();
        jbtStop = new javax.swing.JButton();
        lblCan = new javax.swing.JLabel();
        jbtAyuda = new javax.swing.JButton();

        // Propiedades
        
        this.getContentPane().setLayout(null);
        comboCat.addItemListener(combo);
        panelCan.setLayout(null);
        panelCat.setBounds(0, 0, 380, 100);
        panelCan.setBounds(0, 100, 380, 50);
        jbtStop.setText("Stop");
        jbtStop.setBackground(listColores.get(1));
        jbtStop.setBounds(250, 10, 100, 30);
        jbtStop.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                try {
                    jbtStopActionPerformed(evt);
                } catch (JavaLayerException ex) {
                    Logger.getLogger(Relax.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        jbtAyuda.setText("?");
        jbtAyuda.setBackground(listColores.get(1));
        jbtAyuda.setBounds(170, 10, 40, 30);
        jbtAyuda.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String mensaje = "Para agregar música a la lista, \n tan solo debe copiar la carpeta con las canciones que desea \n "
                        + "en la carpeta Música del directorio de instalación. \n Normalmente estará en C/MisTareas/Musica. \n Asegúrese de que "
                        + "las canciones tengan formato .mp3.";
                javax.swing.JOptionPane.showMessageDialog(null, mensaje, "Ayuda para agregar", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });
        jbtPlay.setText("Play");
        jbtPlay.setBackground(listColores.get(1));
        jbtPlay.setBounds(30, 10, 100, 30);
        jbtPlay.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPlayActionPerformed(evt);
            }
        });
        
        comboCat.setBounds(20, 20, 120, 30);
        comboCan.setBounds(160, 20, 200, 30);
        

        
        lblCan.setBounds(20, 60, 350, 30);
        lblCan.setFont(new java.awt.Font("MV Boli", java.awt.Font.ITALIC, 20));
        lblCan.setForeground(java.awt.Color.WHITE);

        panelCat.setLayout(null);
        panelCat.setBackground(listColores.get(0));
        panelCan.setBackground(listColores.get(0));
        panelCat.setBorder(
                new javax.swing.border.LineBorder(java.awt.Color.WHITE, 2));
        panelCan.setBorder(
                new javax.swing.border.LineBorder(java.awt.Color.WHITE, 2));

        // Agregando a los paneles
        panelCat.add(comboCat);
        panelCat.add(comboCan);
        panelCan.add(jbtPlay);
        panelCan.add(jbtStop);
        panelCat.add(lblCan);
        panelCan.add(jbtAyuda);
        // Agregando los paneles

        getContentPane()
                .add(panelCat);
        getContentPane()
                .add(panelCan);

    }

    private void initCombos() {
        java.io.File carpetas = new java.io.File("Musica");
        if (!carpetas.exists()) javax.swing.JOptionPane.showMessageDialog(null, "No se encontró la carpeta 'Musica' en el directorio de instalación.", "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        carpeta = new java.io.File(dirCarpeta);
        for(java.io.File nombreCarpetas : carpetas.listFiles()){
            if(nombreCarpetas.isDirectory())
                comboCat.addItem(nombreCarpetas.getName());
        }
       
        for(java.io.File ficheros : carpeta.listFiles()){
            if(ficheros != null){
             comboCan.addItem(ficheros.getName());
        }
        }
     
    }
    
    private void actualizarCombos(){
        carpeta = new java.io.File(dirCarpeta);
        javax.swing.DefaultComboBoxModel modelo = (javax.swing.DefaultComboBoxModel)comboCan.getModel();
        modelo.removeAllElements();
       // Se podría haber hecho igual con comboCan.removeallitems(), pero por practicar lo del defaultcomboboxmodel :D
        for(java.io.File ficheros : carpeta.listFiles()){
            if(ficheros != null && ficheros.getName().endsWith(".mp3")){
             modelo.addElement(ficheros.getName());
             
        }
        }
       comboCan.setModel(modelo);
    }
    
    private void combosItemListener(java.awt.event.ItemEvent evt){
        String eleccion = comboCat.getSelectedItem().toString();
        dirCarpeta = "Musica/" + eleccion;
        actualizarCombos();
       
    }

    private void jbtPlayActionPerformed(java.awt.event.ActionEvent evt) {
      
        if(mp3 != null) mp3.cerrar();
        if(mp3 != null) mp3 = null;
        StopAll();
        s = "";
        s = dirCarpeta + "/" + comboCan.getSelectedItem();
        
        mp3 = new MP3(s);
        
        new Thread() {
            public void run() {
                try {
                    while (!isStopped) {
                        mp3.play();
                    }
                } catch (Exception e) {
                }
            }

        }.start();
        lblCan.setText(comboCan.getSelectedItem().toString());
        mp3s.add(mp3);
        
        
    }

    private void jbtStopActionPerformed(java.awt.event.ActionEvent evt) throws JavaLayerException {
      
        for(int i = 0; i<mp3s.size(); i++){
            mp3s.get(i).close();
            mp3s.get(i).cerrar();
        }
       
        lblCan.setText("");
    }
    
    private void StopAll(){
        for(int i = 0; i<mp3s.size(); i++){
            mp3s.get(i).close();
            mp3s.get(i).cerrar();
            mp3s.remove(i);
        }
       
        lblCan.setText("");
    }
    
    public void actualizarTema(java.util.ArrayList<java.awt.Color> listaColores){
        this.listColores = listaColores;
        panelCat.setBackground(listColores.get(0));
        panelCan.setBackground(listColores.get(0));
        jbtStop.setBackground(listColores.get(1));
        jbtPlay.setBackground(listColores.get(1));
        jbtAyuda.setBackground(listColores.get(1));
        
    }

}
