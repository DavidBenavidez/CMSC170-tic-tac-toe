/*
    Author(s): Benavidez, David Ralph
               Ganayo, PA
    Description: This program serves as a tic-tac-toe game
                 where the player faces an unbeatbale AI

*/
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main{
    public static void main(String[] args){
        JFrame frame = new JFrame("Tick tack tick");
        frame.setPreferredSize(new Dimension(750, 780));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        
        Board tictac = new Board();
        
        container.add(tictac);

        frame.setFocusable(true);
        frame.pack();
        frame.setVisible(true);
    }
}