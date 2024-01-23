/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoestudio;

import controlador.ImgResize;
import java.awt.Container;
import java.io.IOException;
import java.time.ZoneId;
import modelo.Conexion;
import modelo.Tablero;
import modelo.TableroNormal;

/**
 *
 * @author luisj
 */
public class Puzzle extends javax.swing.JDialog {

    public static Container contenedor;
    public static javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private java.util.ArrayList<java.awt.Color> listColores;
    MetodoEstudio estudio;
    private javax.swing.JLabel contador;
    public static javax.swing.JLabel contadorPulsacionesLbl;
    private static int contadorPulsaciones;
    private java.awt.image.BufferedImage imgOriginal;
    private java.awt.image.BufferedImage img;

    // Para las imagenes
    public static Tablero tablero;
    public static TableroNormal tableroNormal;
    private int newWidth, newHeight;
    private final ImgResize redimensionado;
    private java.awt.image.BufferedImage imgMini;
    private javax.swing.ImageIcon icono;
    private javax.swing.JButton miniImage;
    private int dimension;
    private java.util.Random random = new java.util.Random();
    private int[] lista;
    private javax.swing.JLabel jlbPrueba;
    private int aleatorio;
    private int[] lista2;
    private java.util.ArrayList<Integer> auxiliar;
    private int contadorList;

    // Para el temporizador
    private javax.swing.Timer tiempoEstudio;
    private javax.swing.Timer tiempoProgreso;
    private javax.swing.JProgressBar barraProgreso;
    private int milisegundos;
    private int cuenta;
    private int maxBar;
    private int dimensionTotal;
    private int tiempo;
    private int maxBar1;
    private javax.swing.JButton jbtParar;
    private javax.swing.JButton jbtReanudar;
    private String titulo;
    private int year;
    private String mes;
    private java.util.Date fecha;

    //Controlador de puzzle acabado
    public static boolean puzzleFinish = false;
    public static boolean tableroNormalActivo = false;
    // Cargar imagenes aleatorias
    private int imagenAleatorio;
    private int[] listaImg;
    private java.util.ArrayList<Integer> auxiliarImg;
    private int totalImagenes;
    private Conexion conexion;

    public Puzzle(javax.swing.JFrame parent, boolean modal, java.util.ArrayList<java.awt.Color> listColores, String titulo, int dimension2, int tiempo, boolean normal) {
        super(parent, modal);
        setSize(960, 850);
        estudio = new MetodoEstudio();
        setLocationRelativeTo(null);
        this.titulo = titulo;
        setTitle("Tarea: " + titulo);
        setResizable(false);
        estudio = new MetodoEstudio();
        redimensionado = new ImgResize();
        conexion = new Conexion();
        this.listColores = listColores;
        tableroNormalActivo = normal;
        if (tableroNormalActivo == true) {
            dimension2 = 5;
        }
        contadorList = 0;
        contadorPulsaciones = 0;
        cuenta = 0;
        totalImagenes = 0;
        fecha = new java.util.Date();
        ZoneId timeZone = ZoneId.systemDefault();
        java.time.LocalDate getLocalDate = fecha.toInstant().atZone(timeZone).toLocalDate();
        year = getLocalDate.getYear();
        mes = getLocalDate.getMonth().toString();
        this.tiempo = tiempo;
        dimensionTotal = dimension2 * dimension2;
        this.maxBar = tiempo * 60;
        contenedor = this.getContentPane();

        initComponents();
        initTimerComponent();

        int imagenAleatorio = 0;
        java.util.ArrayList<String> nombreArchivos = new java.util.ArrayList<String>();
        java.util.Random rnd = new java.util.Random();
        int cuentaArchivos = 0;
        java.io.File carpeta = new java.io.File("Imagenes");
        if (!carpeta.exists()) {
            javax.swing.JOptionPane.showMessageDialog(null, "No se encontró la carpeta 'Imagenes' en el directorio de instalación.", "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

        for (java.io.File archivos : carpeta.listFiles()) {
            if (archivos != null) {

                nombreArchivos.add(archivos.getName());

            }

        }
        if (nombreArchivos.size() == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "No existen imagenes \n Asegúrese de que la carpeta Imagenes del directorio de instalación \n no esté vacia.");
        }

        try {

            if (!carpeta.exists() || nombreArchivos.size() == 0) {
                totalImagenes = 0;
            } else {
                totalImagenes = nombreArchivos.size() + 1;
            }

            //totalImagenes = nombreArchivos.length; // Número total de imagenes en la carptea
            auxiliarImg = new java.util.ArrayList<Integer>(totalImagenes - 1); // Creamos un arraylista donde ir guardando los aleatorios que ya han salido, es arraylista para usar el metodo contains

            imagenAleatorio = rnd.nextInt(totalImagenes - 1); // Generamos un aleatorio dentro del rango del tamaño de la lista
            while (auxiliarImg.contains(imagenAleatorio)) { // Mientras, aleatorio se encuentre dentro del array lista auxiliar, generamos un nuevo aleatorio
                imagenAleatorio = rnd.nextInt(totalImagenes - 1);
            }
            auxiliarImg.add(imagenAleatorio); // Añadimos al arraylista auxiliar el numero aleatorio que ha salido para que no vuelva a salir usando el contains y el while
            String Imagen = "";
            imagenAleatorio = imagenAleatorio;
            Imagen = "Imagenes/" + nombreArchivos.get(imagenAleatorio);

            imgOriginal = javax.imageio.ImageIO.read(new java.io.File(Imagen));
            newWidth = panel1.getWidth();
            newHeight = panel1.getHeight();
            img = redimensionado.resize(imgOriginal, newWidth, newHeight);
            imgMini = redimensionado.resize(imgOriginal, 300, 300);
            icono = new javax.swing.ImageIcon(imgMini);
            miniImage = new javax.swing.JButton(icono);
            miniImage.setVisible(false);

            if (tableroNormalActivo == true) {
                try {
                    tableroNormal = new TableroNormal(dimension2, img, listColores);
                    tableroNormal.setBounds(10, 20, 600, 600);
                    contenedor.remove(panel1);
                    contenedor.add(tableroNormal);
                    contenedor.validate();
                    panel2.add(miniImage);
                } catch (Exception ex) {
                    System.out.println("Error" + dimension2);
                    if (img == null) System.out.println("Sin imagen");
                    tableroNormalActivo = false;
                }
                initOtherComponentsNormal(tableroNormal);
            } else {
                tablero = new Tablero(dimension2, img, listColores);
                tablero.setBounds(10, 20, 600, 600);
                contenedor.remove(panel1);
                contenedor.add(tablero);
                contenedor.validate();
                panel2.add(miniImage);
                initOtherComponents(tablero);
            }

        } catch (IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "No se encontraron imágenes", "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }

    }

    private void initComponents() {

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        getContentPane().setLayout(null);

        // Inicializando
        panel1 = new javax.swing.JPanel();
        panel2 = new javax.swing.JPanel();
        panel3 = new javax.swing.JPanel();
        panel4 = new javax.swing.JPanel();
        contador = new javax.swing.JLabel();
        contadorPulsacionesLbl = new javax.swing.JLabel();
        jlbPrueba = new javax.swing.JLabel();
        barraProgreso = new javax.swing.JProgressBar();
        jbtParar = new javax.swing.JButton();
        jbtReanudar = new javax.swing.JButton();

        // Propiedades
        getContentPane().setBackground(listColores.get(0));
        panel1.setBounds(10, 20, 600, 600);
        panel1.setBackground(listColores.get(1));
        //panel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        panel2.setBounds(630, 20, 300, 300);
        panel2.setBackground(listColores.get(1));
        panel3.setBounds(10, 640, 920, 150);
        panel3.setBorder(new javax.swing.border.LineBorder(java.awt.Color.WHITE, 5));
        panel3.setBackground(listColores.get(0));
        panel3.setLayout(null);
        panel4.setBounds(630, 360, 300, 260);
        panel4.setBorder(new javax.swing.border.LineBorder(java.awt.Color.WHITE, 5));
        panel4.setBackground(listColores.get(0));
        panel4.setLayout(new java.awt.GridLayout(2, 1));

        contador.setText("Movimientos");
        java.awt.Font fuenteContador = new java.awt.Font("MV Boli", java.awt.Font.ITALIC, 46);
        contador.setForeground(listColores.get(1));
        contador.setFont(fuenteContador);
        contadorPulsacionesLbl.setForeground(listColores.get(1));
        contadorPulsacionesLbl.setText("0");
        contadorPulsacionesLbl.setFont(fuenteContador);
        contadorPulsacionesLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contadorPulsacionesLbl.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        panel2.setLayout(new java.awt.GridLayout(1, 1));

        jlbPrueba.setText("Progreso del la tarea...");
        jlbPrueba.setFont(new java.awt.Font("MV Boli", java.awt.Font.ITALIC, 20));
        jlbPrueba.setForeground(listColores.get(1));
        jlbPrueba.setBounds(340, 20, 300, 50);
        jbtParar.setText("Parar");
        jbtParar.setBounds(335, 110, 100, 30);
        jbtParar.setBackground(listColores.get(1));
        jbtParar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPararActionPerformed(evt);

            }
        });

        jbtReanudar.setText("Reanudar");
        jbtReanudar.setBounds(455, 110, 100, 30);
        jbtReanudar.setBackground(listColores.get(1));
        jbtReanudar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtReanudarActionPerformed(evt);
            }
        });

        maxBar1 = maxBar - 20;
        barraProgreso.setMinimum(0);
        barraProgreso.setMaximum(maxBar1);
        barraProgreso.setValue(0);
        barraProgreso.setBounds(55, 60, 800, 40);

        // Añadiendo a los paneles
        panel4.add(contador);
        panel4.add(contadorPulsacionesLbl);
        panel3.add(barraProgreso);
        panel3.add(jlbPrueba);
        panel3.add(jbtParar);
        panel3.add(jbtReanudar);
        // Añadiendo

        getContentPane().add(panel2);
        getContentPane().add(panel3);
        getContentPane().add(panel4);

    }

    private void exitForm(java.awt.event.WindowEvent evt) {

        this.setVisible(false);
        this.dispose();

    }

    private void initOtherComponents(Tablero tablero) {

        lista = new int[tablero.getComponentCount()];
        for (int i = 0; i < tablero.getComponentCount(); i++) {
            lista[i] = i;
        }

        lista2 = new int[lista.length]; // Creamos una nueva lista con el mismo rango que la lista para meter los valores desordenados
        auxiliar = new java.util.ArrayList<Integer>(lista.length); // Creamos un arraylista donde ir guardando los aleatorios que ya han salido, es arraylista para usar el metodo contains
        aleatorio = random.nextInt(lista.length); // Generamos un aleatorio dentro del rango del tamaño de la lista

        // DESORDENAMOS LA LISTA AÑADIENDO SUS VALORES ALEATORIAMENTE A LA LISTA2
        for (int i = 0; i < lista.length; i++) {

            while (auxiliar.contains(aleatorio)) { // Mientras, aleatorio se encuentre dentro del arraylista auxiliar, generamos un nuevo aleatorio
                aleatorio = random.nextInt(lista.length);
            }
            lista2[i] = lista[aleatorio]; // Añadimos a la lista2 un valor aleatorio de la lista1
            auxiliar.add(aleatorio); // Añadimos al arraylista auxiliar el numero aleatorio que ha salido para que no vuelva a salir usando el contains y el while
        }

    }

    public static void movimientos() {

        contadorPulsaciones++;
        Integer count = contadorPulsaciones;
        contadorPulsacionesLbl.setText(count.toString());
    }

    private void initTimerComponent() {

        int rangoTiempoMil;
        rangoTiempoMil = ((tiempo * 60) * 1000) / dimensionTotal;
        //System.out.print(rangoTiempoMil);
        //cuenta = 0;
        java.awt.event.ActionListener al = new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (tableroNormalActivo == true) {
                    onTimer2();
                } else {
                    onTimer();
                }

            }
        };

        java.awt.event.ActionListener tp = new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onProgress();
            }
        };

        tiempoProgreso = new javax.swing.Timer(1000, tp);
        tiempoEstudio = new javax.swing.Timer(rangoTiempoMil, al);
        tiempoProgreso.start();
        tiempoEstudio.start();

    }

    private void onTimer() {

        try {
            tablero.getComponent(lista2[contadorList]).setVisible(true);

            contadorList++;
            if (contadorList == tablero.getComponentCount()) {
                miniImage.setVisible(true);
                tiempoEstudio.stop();
                tiempoProgreso.stop();
                barraProgreso.setValue(maxBar1);
                jbtParar.setVisible(false);
                jbtReanudar.setVisible(false);
                for (int i = 0; i < tablero.getComponentCount(); i++) {
                    tablero.getComponent(i).setEnabled(true);
                }
                // Añadimos la tarea realizada a la base de datos
                boolean guardado = conexion.guardarTarea(titulo, tiempo, mes, year);
                // Si se ha guardado la tarea con exito, mostramos el mensaje
                java.awt.Toolkit.getDefaultToolkit().beep();
                if (guardado) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Enhorabuena, tarea completada con éxito", "Tarea completa", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    tableroNormalActivo = false;
                }

            }

        } catch (Exception ex) {

        }
    }

    private void onTimer2() {

        try {
            tableroNormal.getComponent(lista2[contadorList]).setVisible(true);

            contadorList++;
            if (contadorList == tableroNormal.getComponentCount()) {
                miniImage.setVisible(true);
                tiempoEstudio.stop();
                tiempoProgreso.stop();
                barraProgreso.setValue(maxBar1);
                jbtParar.setVisible(false);
                jbtReanudar.setVisible(false);
                for (int i = 0; i < tableroNormal.getComponentCount(); i++) {
                    tableroNormal.getComponent(i).setEnabled(true);
                }
                // Añadimos la tarea realizada a la base de datos
                boolean guardado = conexion.guardarTarea(titulo, tiempo, mes, year);
                // Si se ha guardado la tarea con exito, mostramos el mensaje
                java.awt.Toolkit.getDefaultToolkit().beep();
                if (guardado) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Enhorabuena, tarea completada con éxito", "Tarea completa", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    tableroNormalActivo = false;
                }

            }

        } catch (Exception ex) {

        }
    }

    private void onProgress() {

        ++cuenta;
        //contador.setText(Integer.toString(cuenta));
        barraProgreso.setValue(cuenta);
    }

    private void jbtReanudarActionPerformed(java.awt.event.ActionEvent evt) {
        tiempoEstudio.restart();
        tiempoProgreso.restart();
    }

    private void jbtPararActionPerformed(java.awt.event.ActionEvent evt) {
        tiempoEstudio.stop();
        tiempoProgreso.stop();
    }

    public void actualizarTema(java.util.ArrayList<java.awt.Color> listaColores) {
        this.listColores = listaColores;

        jbtReanudar.setBackground(listColores.get(1));
        jbtParar.setBackground(listColores.get(1));
        jlbPrueba.setForeground(listColores.get(1));
        contadorPulsacionesLbl.setForeground(listColores.get(1));
        contador.setForeground(listColores.get(1));
        panel4.setBackground(listColores.get(0));
        panel3.setBackground(listColores.get(0));
        panel2.setBackground(listColores.get(1));
        if(tableroNormalActivo == true){
            tableroNormal.setBackground(listColores.get(1));
        } else{
            tablero.setBackground(listColores.get(1));
        }
        panel1.setBackground(listColores.get(1));
        getContentPane().setBackground(listColores.get(0));
    }

    private void initOtherComponentsNormal(TableroNormal tablero) {

        lista = new int[tableroNormal.getComponentCount()];
        for (int i = 0; i < tableroNormal.getComponentCount(); i++) {
            lista[i] = i;
        }

        lista2 = new int[lista.length]; // Creamos una nueva lista con el mismo rango que la lista para meter los valores desordenados
        auxiliar = new java.util.ArrayList<Integer>(lista.length); // Creamos un arraylista donde ir guardando los aleatorios que ya han salido, es arraylista para usar el metodo contains
        aleatorio = random.nextInt(lista.length); // Generamos un aleatorio dentro del rango del tamaño de la lista

        // DESORDENAMOS LA LISTA AÑADIENDO SUS VALORES ALEATORIAMENTE A LA LISTA2
        for (int i = 0; i < lista.length; i++) {

            while (auxiliar.contains(aleatorio)) { // Mientras, aleatorio se encuentre dentro del arraylista auxiliar, generamos un nuevo aleatorio
                aleatorio = random.nextInt(lista.length);
            }
            lista2[i] = lista[aleatorio]; // Añadimos a la lista2 un valor aleatorio de la lista1
            auxiliar.add(aleatorio); // Añadimos al arraylista auxiliar el numero aleatorio que ha salido para que no vuelva a salir usando el contains y el while
        }

    }

}
