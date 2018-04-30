import java.util.*;
import java.io.*;

public class MiniMax{
    public Moves bestMove;
    private char[][] board;
    private char player;
    private int numTurns;

    public boolean winning(char player){
        // X wins
        if(((this.board[0][0] == player) && (this.board[1][1] == player) && (this.board[2][2] == player)) ||  // diagonal left
        ((this.board[0][2] == player) && (this.board[1][1] == player) && (this.board[2][0] == player)) || // diagonal right
        ((this.board[0][0] == player) && (this.board[0][1] == player) && (this.board[0][2] == player)) || // Top horizontal
        ((this.board[1][0] == player) && (this.board[1][1] == player) && (this.board[1][2] == player)) || // Mid horizontal
        ((this.board[2][0] == player) && (this.board[2][1] == player) && (this.board[2][2] == player)) ||  // Bot horizontal
        ((this.board[0][0] == player) && (this.board[1][0] == player) && (this.board[2][0] == player)) || // Left Vertical
        ((this.board[0][1] == player) && (this.board[1][1] == player) && (this.board[2][1] == player)) || // Mid Vertical
        ((this.board[0][2] == player) && (this.board[1][2] == player) && (this.board[2][2] == player))){// Right Vertical
            return true; 
        }
        return false;
    }

    public ArrayList<int[]> getEmptySpots(){
        ArrayList<int[]> emptySpotArray = new ArrayList<int[]>();
        int[] emptySpot = new int[2];

        for(int i = 0; i < 3; i++){
            for(int k = 0; k < 3; k++){
                if(board[i][k] == ' '){
                    emptySpot = new int[2];
                    emptySpot[0] = i;
                    emptySpot[1] = k;
                    emptySpotArray.add(emptySpot);
                }   
            }
        }
        return emptySpotArray;
    }

    public Moves getBestMove(char player){
        // an array to collect all the objects
        ArrayList<Moves> moves = new ArrayList<Moves>();
        ArrayList<int[]> emptySpots = new ArrayList<int[]>(getEmptySpots());
        Moves move = new Moves();

        if(this.numTurns > 3){
            if (winning('X')){
                move.score = -10;
                return move;
            }
            else if (winning('O')){
                move.score = 10;
                return move;
            }
            else if (emptySpots.size() == 0){
                move.score = 0;
                return move;
            }
        
            // loop through available spots
            for (int i = 0; i < emptySpots.size(); i++){
                //create an object for each and store the index of that spot that was stored as a number in the object's index key
                // Moves move = new Moves();
                move.index = this.board[emptySpots.get(i)[0]][emptySpots.get(i)[1]];
            
                // set the empty spot to the current player
                this.board[emptySpots.get(i)[0]][emptySpots.get(i)[1]] = this.player;
            
                //if collect the score resulted from calling minimax on the opponent of the current player
                if (this.player == 'O'){
                    Moves result = getBestMove('X');
                    move.score = move.score + result.score;
                }
                else{
                    Moves result = getBestMove('O');
                    move.score = move.score + result.score;
                }
            
                //reset the spot to empty
                this.board[emptySpots.get(i)[0]][emptySpots.get(i)[1]] = move.index;
            
                // push the object to the array
                move.pos = new int[2];
                move.pos[0] = emptySpots.get(i)[0];
                move.pos[1] = emptySpots.get(i)[1];
                moves.add(move);
            }
        
            // if it is the computer's turn loop over the moves and choose the move with the highest score
            int bestScore = 1;
            int bestMove = 1;
            if(player == 'O'){
                bestScore = -10000;
                for(int j = 0; j < moves.size(); j++){
                    if(moves.get(j).score > bestScore){
                        bestScore = moves.get(j).score;
                        bestMove = j;
                    }
                }
            }else{
            // else loop over the moves and choose the move with the lowest score
                bestScore = 10000;
                for(int j = 0; j < moves.size(); j++){
                    if(moves.get(j).score < bestScore){
                        bestScore = moves.get(j).score;
                        bestMove = j;
                    }
                }
            }
        
            // return the chosen move (object) from the array to the higher depth
            return moves.get(bestMove);
        }else{
            System.out.println("random" + this.numTurns);
            move.pos = new int[2];
            move.pos[0] = emptySpots.get(0)[0];
            move.pos[1] = emptySpots.get(0)[1];
            return move;
        }

    }

    public MiniMax(int numTurnsVal, char[][] board, char player){
        this.numTurns = numTurnsVal;
        this.player = player;
        this.bestMove = new Moves();
        this.board = board;
        this.bestMove = getBestMove(this.player);
    }
}