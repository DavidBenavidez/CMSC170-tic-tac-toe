import java.util.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;

import javafx.util.*;
import javafx.embed.swing.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Board extends JPanel{
    private static final String topRight = "assets/images/topright.png";
    private static final String topRightX = "assets/images/toprightX.png";
    private static final String topRightO = "assets/images/toprightO.png";
    private static final String topMid = "assets/images/topMid.png";
    private static final String topMidX = "assets/images/topMidX.png";
    private static final String topMidO = "assets/images/topMidO.png";
    private static final String topLeft = "assets/images/topleft.png";
    private static final String topLeftX = "assets/images/topleftX.png";
    private static final String topLeftO = "assets/images/topleftO.png";
    private static final String leftMid = "assets/images/leftmid.png";
    private static final String leftMidX = "assets/images/leftmidX.png";
    private static final String leftMidO = "assets/images/leftmidO.png";
    private static final String midBox = "assets/images/mid.png";
    private static final String midBoxX = "assets/images/midX.png";
    private static final String midBoxO = "assets/images/midO.png";
    private static final String rightMid = "assets/images/rightmid.png";  
    private static final String rightMidX = "assets/images/rightmidX.png";
    private static final String rightMidO = "assets/images/rightmidO.png";
    private static final String botLeft = "assets/images/botleft.png";
    private static final String botLeftX = "assets/images/botleftX.png";
    private static final String botLeftO = "assets/images/botleftO.png";
    private static final String botMid = "assets/images/botmid.png";
    private static final String botMidX = "assets/images/botmidX.png";
    private static final String botMidO = "assets/images/botmidO.png";
    private static final String botRight = "assets/images/botright.png";
    private static final String botRightX = "assets/images/botrightX.png";
    private static final String botRightO = "assets/images/botrightO.png";
    private JLabel[][] board;
    private MouseListener[][] boardListeners;
    private char playerTurn;

    public void startBoard(){
        removeAll();
        // Generate Initial Board
            this.board = new JLabel[3][3];
            this.board[0][0] = new JLabel();
            this.board[0][0].setIcon(new ImageIcon(topLeft));
            add(this.board[0][0]);
            
            this.board[0][1] = new JLabel();
            this.board[0][1].setIcon(new ImageIcon(topMid));
            add(this.board[0][1]);
            
            this.board[0][2] = new JLabel();
            this.board[0][2].setIcon(new ImageIcon(topRight));
            add(this.board[0][2]);
            
            this.board[1][0] = new JLabel();
            this.board[1][0].setIcon(new ImageIcon(leftMid));
            add(this.board[1][0]);
            
            this.board[1][1] = new JLabel();
            this.board[1][1].setIcon(new ImageIcon(midBox));
            add(this.board[1][1]);
            
            this.board[1][2] = new JLabel();
            this.board[1][2].setIcon(new ImageIcon(rightMid));
            add(this.board[1][2]);
            
            this.board[2][0] = new JLabel();
            this.board[2][0].setIcon(new ImageIcon(botLeft));
            add(this.board[2][0]);
            
            this.board[2][1] = new JLabel();
            this.board[2][1].setIcon(new ImageIcon(botMid));
            add(this.board[2][1]);
            
            this.board[2][2] = new JLabel();
            this.board[2][2].setIcon(new ImageIcon(botRight));
            add(this.board[2][2]);
    }
    
    public void generateListeners(){
        this.boardListeners = new MouseListener[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                final int iValue = i;
                final int jValue = j;
                this.boardListeners[i][j] = new MouseListener(){
                    public void mouseClicked(MouseEvent e){
                        if(playerTurn == 'X'){
                            replaceIcon(iValue, jValue,'X');
                            playerTurn = 'O';
                        }else{
                            replaceIcon(iValue, jValue,'O');
                            playerTurn = 'X';
                        }
                    }
                    public void mouseEntered(MouseEvent e){}
                    public void mouseExited(MouseEvent e){}
                    public void mousePressed(MouseEvent e){}
                    public void mouseReleased(MouseEvent e){}
                };
                this.board[i][j].addMouseListener(this.boardListeners[i][j]);
            }
        }
    }

    public void resetBoard(){
        // Generate Initial Board
            this.board[0][0].setIcon(new ImageIcon(topLeft));
            this.board[0][1].setIcon(new ImageIcon(topMid));
            this.board[0][2].setIcon(new ImageIcon(topRight));
    
            this.board[1][0].setIcon(new ImageIcon(leftMid));
            this.board[1][1].setIcon(new ImageIcon(midBox));
            this.board[1][2].setIcon(new ImageIcon(rightMid));

            this.board[2][0].setIcon(new ImageIcon(botLeft));
            this.board[2][1].setIcon(new ImageIcon(botMid));
            this.board[2][2].setIcon(new ImageIcon(botRight));
    }

    public boolean checkDraw(){
        // Checks if Draw
        if(this.board[0][0].getIcon().toString() != topLeft){
            if(this.board[0][1].getIcon().toString() != topMid){
                if(this.board[0][2].getIcon().toString() != topRight){
                    if(this.board[1][0].getIcon().toString() != leftMid){
                        if(this.board[1][1].getIcon().toString() != midBox){
                            if(this.board[1][2].getIcon().toString() != rightMid){
                                if(this.board[2][0].getIcon().toString() != botLeft){
                                    if(this.board[2][1].getIcon().toString() != botMid){
                                        if(this.board[2][2].getIcon().toString() != botLeft){
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkWin(){
        // X wins
            // diagonal left
            if((this.board[0][0].getIcon().toString() == topLeftX) && (this.board[1][1].getIcon().toString() == midBoxX) && (this.board[2][2].getIcon().toString() == botRightX)){
                JOptionPane.showMessageDialog(null, "X wins!");
                return true; 
            } // diagonal right
            else if((this.board[0][2].getIcon().toString() == topRightX) && (this.board[1][1].getIcon().toString() == midBoxX) && (this.board[2][0].getIcon().toString() == botLeftX)){
                JOptionPane.showMessageDialog(null, "X wins!");
                return true; 
            } // Top horizontal
            else if((this.board[0][0].getIcon().toString() == topLeftX) && (this.board[0][1].getIcon().toString() == topMidX) && (this.board[0][2].getIcon().toString() == topRightX)){
                JOptionPane.showMessageDialog(null, "X wins!");
                return true; 
            } // Mid horizontal
            else if((this.board[1][0].getIcon().toString() == leftMidX) && (this.board[1][1].getIcon().toString() == midBoxX) && (this.board[1][2].getIcon().toString() == rightMidX)){
                JOptionPane.showMessageDialog(null, "X wins!");
                return true; 
            } // Bot horizontal
            else if((this.board[2][0].getIcon().toString() == botLeftX) && (this.board[2][1].getIcon().toString() == botMidX) && (this.board[2][2].getIcon().toString() == botRightX)){
                JOptionPane.showMessageDialog(null, "X wins!");
                return true; 
            } // Left Vertical
            else if((this.board[0][0].getIcon().toString() == topLeftX) && (this.board[1][0].getIcon().toString() == leftMidX) && (this.board[2][0].getIcon().toString() == botLeftX)){
                JOptionPane.showMessageDialog(null, "X wins!");
                return true; 
            } // Mid Vertical
            else if((this.board[0][1].getIcon().toString() == topMidX) && (this.board[1][1].getIcon().toString() == midBoxX) && (this.board[2][1].getIcon().toString() == botMidX)){
                JOptionPane.showMessageDialog(null, "X wins!");
                return true; 
            } // Right Vertical
            else if((this.board[0][2].getIcon().toString() == topRightX) && (this.board[1][2].getIcon().toString() == rightMidX) && (this.board[2][2].getIcon().toString() == botRightX)){
                JOptionPane.showMessageDialog(null, "X wins!");
                return true; 
            }
        // O wins
            else if((this.board[0][0].getIcon().toString() == topLeftO) && (this.board[1][1].getIcon().toString() == midBoxO) && (this.board[2][2].getIcon().toString() == botRightO)){
                JOptionPane.showMessageDialog(null, "O wins!");
                return true; 
            } // diagonal right
            else if((this.board[0][2].getIcon().toString() == topRightO) && (this.board[1][1].getIcon().toString() == midBoxO) && (this.board[2][0].getIcon().toString() == botLeftO)){
                JOptionPane.showMessageDialog(null, "O wins!");
                return true; 
            } // Top horizontal
            else if((this.board[0][0].getIcon().toString() == topLeftO) && (this.board[0][1].getIcon().toString() == topMidO) && (this.board[0][2].getIcon().toString() == topRightO)){
                JOptionPane.showMessageDialog(null, "O wins!");
                return true; 
            } // Mid horizontal
            else if((this.board[1][0].getIcon().toString() == leftMidO) && (this.board[1][1].getIcon().toString() == midBoxO) && (this.board[1][2].getIcon().toString() == rightMidO)){
                JOptionPane.showMessageDialog(null, "O wins!");
                return true; 
            } // Bot horizontal
            else if((this.board[2][0].getIcon().toString() == botLeftO) && (this.board[2][1].getIcon().toString() == botMidO) && (this.board[2][2].getIcon().toString() == botRightO)){
                JOptionPane.showMessageDialog(null, "O wins!");
                return true; 
            } // Left Vertical
            else if((this.board[0][0].getIcon().toString() == topLeftO) && (this.board[1][0].getIcon().toString() == leftMidO) && (this.board[2][0].getIcon().toString() == botLeftO)){
                JOptionPane.showMessageDialog(null, "O wins!");
                return true; 
            } // Mid Vertical
            else if((this.board[0][1].getIcon().toString() == topMidO) && (this.board[1][1].getIcon().toString() == midBoxO) && (this.board[2][1].getIcon().toString() == botMidO)){
                JOptionPane.showMessageDialog(null, "O wins!");
                return true; 
            } // Right Vertical
            else if((this.board[0][2].getIcon().toString() == topRightO) && (this.board[1][2].getIcon().toString() == rightMidO) && (this.board[2][2].getIcon().toString() == botRightO)){
                JOptionPane.showMessageDialog(null, "O wins!");
                return true; 
            }
        if(checkDraw()){
            JOptionPane.showMessageDialog(null, "It's a draw!");
            return true;
        }

        return false;
    }

    public void replaceIcon(int i, int j, char turn){
        if(i == 0){
            if(j == 0){
                if(this.board[i][j].getIcon().toString() == topLeft){
                    if(turn == 'X'){
                        this.board[i][j].setIcon(new ImageIcon(topLeftX));
                    }else{
                        this.board[i][j].setIcon(new ImageIcon(topLeftO));
                    }
                }
            }else if(j == 1){
                if(this.board[i][j].getIcon().toString() == topMid){
                    if(turn == 'X'){
                        this.board[i][j].setIcon(new ImageIcon(topMidX));
                    }else{
                        this.board[i][j].setIcon(new ImageIcon(topMidO));
                    }
                }
            }else if(j == 2){
                if(this.board[i][j].getIcon().toString() == topRight){
                    if(turn == 'X'){
                        this.board[i][j].setIcon(new ImageIcon(topRightX));
                    }else{
                        this.board[i][j].setIcon(new ImageIcon(topRightO));
                    }
                }
            }
        } else if(i == 1){
            if(j == 0){
                if(this.board[i][j].getIcon().toString() == leftMid){
                    if(turn == 'X'){
                        this.board[i][j].setIcon(new ImageIcon(leftMidX));
                    }else{
                        this.board[i][j].setIcon(new ImageIcon(leftMidO));
                    }
                }
            }else if(j == 1){
                if(this.board[i][j].getIcon().toString() == midBox){
                    if(turn == 'X'){
                        this.board[i][j].setIcon(new ImageIcon(midBoxX));
                    }else{
                        this.board[i][j].setIcon(new ImageIcon(midBoxO));
                    }
                }
            }else if(j == 2){
                if(this.board[i][j].getIcon().toString() == rightMid){
                    if(turn == 'X'){
                        this.board[i][j].setIcon(new ImageIcon(rightMidX));
                    }else{
                        this.board[i][j].setIcon(new ImageIcon(rightMidO));
                    }
                }
            }
        } else if(i == 2){
            if(j == 0){
                if(this.board[i][j].getIcon().toString() == botLeft){
                    if(turn == 'X'){
                        this.board[i][j].setIcon(new ImageIcon(botLeftX));
                    }else{
                        this.board[i][j].setIcon(new ImageIcon(botLeftO));
                    }
                }
            }else if(j == 1){
                if(this.board[i][j].getIcon().toString() == botMid){
                    if(turn == 'X'){
                        this.board[i][j].setIcon(new ImageIcon(botMidX));
                    }else{
                        this.board[i][j].setIcon(new ImageIcon(botMidO));
                    }
                }
            }else if(j == 2){
                if(this.board[i][j].getIcon().toString() == botRight){
                    if(turn == 'X'){
                        this.board[i][j].setIcon(new ImageIcon(botRightX));
                    }else{
                        this.board[i][j].setIcon(new ImageIcon(botRightO));
                    }
                }
            }
        }

        if (checkWin()) resetBoard();
        if (checkDraw()) resetBoard();
          
    }

    public Board(){
        startBoard();
        generateListeners();

        this.setLayout(new GridLayout(3, 3)); // GridLayout will arrange elements in Grid Manager 8 X 8
		this.setSize(750, 750);
		this.setFocusable(true);
		this.setVisible(true);
    }
}