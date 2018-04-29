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


public class MiniMax{
    private char[][] newBoard;
    private char player;

    public boolean winning(char player){
          // X wins
          if(((this.newBoard[0][0] == player) && (this.newBoard[1][1] == player) && (this.newBoard[2][2] == player)) ||  // diagonal left
            ((this.newBoard[0][2] == player) && (this.newBoard[1][1] == player) && (this.newBoard[2][0] == player)) || // diagonal right
            ((this.newBoard[0][0] == player) && (this.newBoard[0][1] == player) && (this.newBoard[0][2] == player)) || // Top horizontal
            ((this.newBoard[1][0] == player) && (this.newBoard[1][1] == player) && (this.newBoard[1][2] == player)) || // Mid horizontal
            ((this.newBoard[2][0] == player) && (this.newBoard[2][1] == player) && (this.newBoard[2][2] == player)) ||  // Bot horizontal
            ((this.newBoard[0][0] == player) && (this.newBoard[1][0] == player) && (this.newBoard[2][0] == player)) || // Left Vertical
            ((this.newBoard[0][1] == player) && (this.newBoard[1][1] == player) && (this.newBoard[2][1] == player)) || // Mid Vertical
            ((this.newBoard[0][2] == player) && (this.newBoard[1][2] == player) && (this.newBoard[2][2] == player))){// Right Vertical
                    return true; 
            }
            return false;
    }

    public ArrayList<Integer[]> emptyIndexies(){
        ArrayList<Integer[]> availableSpots = new ArrayList<Integer[]>();
        int[] index = new int[2];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                if(this.newBoard[i][j] == ''){
                    index = new int[2];
                    index[0] = i;
                    index[1] = j;
                    availableSpots.add(index);
                }
            }
        }

        return availableSpots;
    }

    public Moves getBestMove(char player){
          //available spots
          ArrayList<Integer[]> availSpots = new ArrayList<Integer>(emptyIndexies(newBoard));
          
              // checks for the terminal states such as win, lose, and tie and returning a value accordingly
              Moves newMove = new Moves();
              if (winning(aiPlayer)){
                newMoves.score = -10;
                return newMove;
              }
              else if (winning(humanPlayer)){
                newMove.score = 10;
                return newMove;
              }
              else if (availSpots.size() == 0){
                newMove.score = 0;
                return newMove;
              }
          
          // an array to collect all the objects
              ArrayList<Moves[]> moves = new ArrayList<Moves[]>();
          
            // loop through available spots
            for (var i = 0; i < availSpots.size(); i++){
                //create an object for each and store the index of that spot that was stored as a number in the object's index key
                Moves move = new Moves(); 

                move.index = this.newBoard[availSpots.get(i)[0]][availSpots.get(i)[1]];
                    
                // set the empty spot to the current player
                this.newBoard[availSpots.get(i)[0]][availSpots.get(i)[1]] = player;
            
                //if collect the score resulted from calling minimax on the opponent of the current player
                if (this.player == aiPlayer){
                    Moves result = getBestMove('X');
                    move.score = result.score;
                }
                else{
                    Moves result = getBestMove('O');
                    move.score = result.score;
                }
            
                //reset the spot to empty
                newBoard[availSpots.get(i)[0]][availSpots.get(i)[1]] = move.index;
            
                // push the object to the array
                moves.add(move);
            }
          
          // if it is the computer's turn loop over the moves and choose the move with the highest score
              int[] bestMove = new int[2];
              if(player === 'O'){
              int bestScore = -10000;
              for(var i = 0; i < moves.size; i++){
                  if(moves.get(i).score > bestScore){
                  bestScore = moves.get(i).score;
                  bestMove = i;
                  }
              }
              }else{
          
              // else loop over the moves and choose the move with the lowest score
                  var bestScore = 10000;
                  for(var i = 0; i < moves.length; i++){
                      if(moves[i].score < bestScore){
                      bestScore = moves[i].score;
                      bestMove = i;
                      }
                  }
              }
          
          // return the chosen move (object) from the array to the higher depth
              return moves[bestMove];
          }
    }

    public MiniMax(char[][] newBoard, char ai){
        this.player = 'O';
        this.newBoard = newBoard;
        
        Moves bestmove = getBestMove(this.player);
}