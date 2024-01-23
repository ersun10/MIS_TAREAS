/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisj
 */
public class Conexion {

    private java.sql.Connection con;
    private java.sql.PreparedStatement ps;
    private java.sql.Statement st;
    private java.sql.ResultSet rs;
    private String url;
    private String driver;
    private String consulta;

    public Conexion() {

        driver = "org.sqlite.JDBC";
        url = "jdbc:sqlite:Database/Database.db";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            //System.out.println("Conectado a la base de datos");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    // Constructor de clase que se conecta a la base de datos
    public Conexion(String consultaUser) {
        this.consulta = consultaUser;
        driver = "org.sqlite.JDBC";
        url = "jdbc:sqlite:Database/Database.db";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            //System.out.println("Conectado a la base de datos");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public int[] datosMes() {
        int[] valores = {0, 0, 0, 0, 0, 0, 0};
        int valorEstudio = 0;
        int valorTrabajo = 0;
        int valorTareas = 0;
        int valorGym = 0;
        int valorDescanso = 0;
        int valorAficion = 0;
        int valorOtros = 0;

        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                if (rs.getString("descripcion").compareTo("Estudio") == 0) {
                    valorEstudio += rs.getInt("duracion");
                }
                if (rs.getString("descripcion").compareTo("Trabajo") == 0) {
                    valorTrabajo += rs.getInt("duracion");
                }
                if (rs.getString("descripcion").compareTo("Tareas del hogar") == 0) {
                    valorTareas += rs.getInt("duracion");
                }
                if (rs.getString("descripcion").compareTo("Ejercicio") == 0) {
                    valorGym += rs.getInt("duracion");
                }
                if (rs.getString("descripcion").compareTo("Descanso") == 0) {
                    valorDescanso += rs.getInt("duracion");
                }
                if (rs.getString("descripcion").compareTo("Afición") == 0) {
                    valorAficion += rs.getInt("duracion");
                }
                if (rs.getString("descripcion").compareTo("Otros") == 0) {
                    valorOtros += rs.getInt("duracion");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
            //javax.swing.JOptionPane.showMessageDialog(null, "No se pudieron recuperar los datos", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        valores[0] = valorEstudio;
        valores[1] = valorTrabajo;
        valores[2] = valorTareas;
        valores[3] = valorGym;
        valores[4] = valorDescanso;
        valores[5] = valorAficion;
        valores[6] = valorOtros;
        return valores;
    }

    public boolean guardarTarea(String descripcion, int duracion, String mes, int year) {

        mes = mes.toLowerCase();
        if (mes.equals("january")) {
            mes = "Enero";
        } else if (mes.equals("february")) {
            mes = "Febrero";
        } else if (mes.equals("march")) {
            mes = "Marzo";
        } else if (mes.equals("april")) {
            mes = "Abril";
        } else if (mes.equals("may")) {
            mes = "Mayo";
        } else if (mes.equals("june")) {
            mes = "Junio";
        } else if (mes.equals("july")) {
            mes = "Julio";
        } else if (mes.equals("august")) {
            mes = "Agosto";
        } else if (mes.equals("september")) {
            mes = "Septiembre";
        } else if (mes.equals("october")) {
            mes = "Octubre";
        } else if (mes.equals("november")) {
            mes = "Noviembre";
        } else if (mes.equals("december")) {
            mes = "Diciembre";
        }
        boolean exitoGuardado = false;
        try {
            ps = con.prepareStatement("INSERT INTO tareas VALUES (null, ?, ?, ?, ?)");
            ps.setString(1, descripcion);
            ps.setInt(2, duracion);
            ps.setString(3, mes);
            ps.setInt(4, year);
            ps.executeUpdate();

            exitoGuardado = true;
            this.cerrarConexion();
        } catch (Exception ex) {
            exitoGuardado = false;
            System.out.println("No se pudo guardar la tarea");
        }

        return exitoGuardado;
    }

    public void cerrarConexion() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
