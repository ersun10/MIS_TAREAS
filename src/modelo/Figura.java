/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.event.ActionEvent;
import metodoestudio.Puzzle;

/**
 *
 * @author luisj
 */
public class Figura extends javax.swing.JButton implements java.awt.event.ActionListener {

    private final int xSolPos, ySolPos;
    private int xPos, yPos;
    private int dimension;
    private int ancho, alto;

    public Figura(int xSolPos, int ySolPos, javax.swing.ImageIcon subimage, int dimension) {

        this.xSolPos = xSolPos;
        this.ySolPos = ySolPos;
        this.dimension = dimension;

        this.xPos = xSolPos;
        this.yPos = ySolPos;

        this.setIcon(subimage);
        this.setPreferredSize(new java.awt.Dimension(subimage.getIconWidth(), subimage.getIconHeight()));
        this.setVisible(false);
        this.setEnabled(false);
        this.addActionListener(this);

        this.ancho = subimage.getIconWidth();
        this.alto = subimage.getIconHeight();
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxSolPos() {
        return xSolPos;
    }

    public int getySolPos() {
        return ySolPos;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {

        try{
            Move();
        } catch(Exception ex){
            
        }
        
    }

    private void Move() {
        
        if(Puzzle.puzzleFinish == true) return;

        try {

            Celula[][] board = Tablero.board;
            //ARRIBA
            if (board[xPos - 1][yPos].getFigura() == null) {
                Tablero.board[xPos - 1][yPos].setFigura(this);
                Tablero.board[xPos][yPos].setFigura(null);
                xPos--;
                Puzzle.tablero.Remover();
                Puzzle.movimientos();
                ComprobarRespuesta();
                return;
            }

        } catch (ArrayIndexOutOfBoundsException ex) {
        }

        try {

            Celula[][] board = Tablero.board;
            //ABAJO
            if (board[xPos + 1][yPos].getFigura() == null) {
                Tablero.board[xPos + 1][yPos].setFigura(this);
                Tablero.board[xPos][yPos].setFigura(null);
                xPos++;
                Puzzle.tablero.Remover();
                Puzzle.movimientos();
                ComprobarRespuesta();
                return;
            }

        } catch (ArrayIndexOutOfBoundsException ex) {
        }

        try {

            Celula[][] board = Tablero.board;
            // DERECHA
            if (board[xPos][yPos + 1].getFigura() == null) {
                Tablero.board[xPos][yPos + 1].setFigura(this);
                Tablero.board[xPos][yPos].setFigura(null);
                yPos++;
                Puzzle.tablero.Remover();
                Puzzle.movimientos();
                ComprobarRespuesta();
                return;
            }

        } catch (ArrayIndexOutOfBoundsException ex) {
        }

        try {

            Celula[][] board = Tablero.board;
            // IZQUIERDA
            if (board[xPos][yPos - 1].getFigura() == null) {
                Tablero.board[xPos][yPos - 1].setFigura(this);
                Tablero.board[xPos][yPos].setFigura(null);
                yPos--;
                Puzzle.tablero.Remover();
                Puzzle.movimientos();
                ComprobarRespuesta();
                return;
            }

        } catch (ArrayIndexOutOfBoundsException ex) {
        }
    }

    private void ComprobarRespuesta() {
       Figura figure = null;
		for(int i = 0; i<dimension; i++){
			for(int j = 0; j<dimension; j++){
				
				figure = Tablero.board[i][j].getFigura();
				if(figure == null)
					continue;
				
				if(figure.getxPos() != figure.getxSolPos() || figure.getyPos() != figure.getySolPos()){
					return;
				}
			}	
		}
		
		javax.swing.JOptionPane.showMessageDialog(new javax.swing.JPanel(), "Bieeenn, has resuelto el puzzleee!!", "ENHORABUENA", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                Puzzle.puzzleFinish = true;
    }

}
