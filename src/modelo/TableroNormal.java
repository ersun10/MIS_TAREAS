/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.FlowLayout;
import metodoestudio.Puzzle;
import modelo.*;

public class TableroNormal extends javax.swing.JPanel {

    public static Celula[][] board;
    public static java.util.ArrayList<Celula> completeBoard = new java.util.ArrayList<Celula>();
    private int dimension;
    private int x, y;
    private int figuraWidth, figuraHeight;
    private javax.swing.JLabel empty;
    private java.util.ArrayList<java.awt.Color> listColor;

    public TableroNormal(int dimension, java.awt.image.BufferedImage rompecabezas, java.util.ArrayList<java.awt.Color> colores) {

        this.dimension = dimension;
        board = new Celula[dimension][dimension];
        this.listColor = colores;
        this.setBackground(listColor.get(1));
        x = 0;
        y = 0;

        figuraWidth = rompecabezas.getWidth() / dimension;
        figuraHeight = rompecabezas.getHeight() / dimension;

        this.setLayout(new java.awt.GridLayout(dimension, dimension, 0, 0));

        if (board.length != 0) {
            completeBoard.clear();
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                completeBoard.add(new Celula(i, j, new Figura(i, j, new javax.swing.ImageIcon(rompecabezas.getSubimage(x, y, figuraWidth, figuraHeight)), dimension)));
                x += figuraWidth;

            }

            x = 0;
            y += figuraHeight;
        }

        
        Desordenar();

        Remover();
    }

     private void Desordenar() {
        java.util.Random generador = new java.util.Random();
        java.util.ArrayList<Celula> copia = new java.util.ArrayList<Celula>(completeBoard);
        int valor[] = new int[completeBoard.size()];
        for(int x = 0; x<completeBoard.size(); x++){
            valor[x] = x;
         }
        
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                
                
                int aleatorio = 0;
                completeBoard.get(0).getFigura().setxPos(i);
                completeBoard.get(0).getFigura().setyPos(j);
                board[i][j] = new Celula(i, j, completeBoard.get(0).getFigura());
                completeBoard.remove(0);
            }
        }

        completeBoard = copia;
        Remover();

    }

    public void Remover() {

        this.removeAll();
        Actualizar();
    }

    private void Actualizar() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j].getFigura() == null) {
                    empty = new javax.swing.JLabel();
                    empty.setPreferredSize(new java.awt.Dimension(figuraWidth, figuraHeight));
                    this.add(empty);
                    continue;
                }
                this.add(board[i][j].getFigura());
            }
        }
        Puzzle.contenedor.validate();
    }

    public void Limpiar() {
        this.removeAll();
    }
}
