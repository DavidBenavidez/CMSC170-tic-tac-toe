import java.util.*;
import java.io.*;

class Position {
    int xPos, yPos;
    public Position(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }
}

class pointUtilities{
    int score;
    Position pos;
    pointUtilities(int score, Position pos){
        this.score = score;
        this.pos = pos;
    }
}

public class MiniMax{
    public Position bestMove;
    private char[][] board;
    private char currentPlayer;
    private int numTurns;
    private ArrayList<pointUtilities> stateScores;

    public boolean winning(char player, char[][] newGameBoard){
        if(((newGameBoard[0][0] == player) && (newGameBoard[1][1] == player) && (newGameBoard[2][2] == player)) ||  // diagonal left
        ((newGameBoard[0][2] == player) && (newGameBoard[1][1] == player) && (newGameBoard[2][0] == player)) ||     // diagonal right
        ((newGameBoard[0][0] == player) && (newGameBoard[0][1] == player) && (newGameBoard[0][2] == player)) ||     // Top horizontal
        ((newGameBoard[1][0] == player) && (newGameBoard[1][1] == player) && (newGameBoard[1][2] == player)) ||     // Mid horizontal
        ((newGameBoard[2][0] == player) && (newGameBoard[2][1] == player) && (newGameBoard[2][2] == player)) ||     // Bot horizontal
        ((newGameBoard[0][0] == player) && (newGameBoard[1][0] == player) && (newGameBoard[2][0] == player)) ||     // Left Vertical
        ((newGameBoard[0][1] == player) && (newGameBoard[1][1] == player) && (newGameBoard[2][1] == player)) ||     // Mid Vertical
        ((newGameBoard[0][2] == player) && (newGameBoard[1][2] == player) && (newGameBoard[2][2] == player))){      // Right Vertical
            return true;
        }
        return false;
    }

    public ArrayList<Position> getEmptySpots(char[][] currentBoard){
        ArrayList<Position> spotsArray = new ArrayList<Position>(); 
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(currentBoard[i][j] == ' '){
                    Position newPoint = new Position(i, j);
                    spotsArray.add(newPoint);
                }
            }
        }
        return spotsArray;
    }

    public int min_value(ArrayList<Integer> list){
        int m = 10000;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < m) {
                m = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    public int max_value(ArrayList<Integer> list){
        int m = -10000;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > m) {
                m = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    public Position getBestMove(){
        int m = -100000;
        int best = -1;

        for (int i = 0; i < stateScores.size(); ++i) { 
            if (stateScores.get(i).score > m) {
                m = stateScores.get(i).score;
                best = i;
            }
        }
        return stateScores.get(best).pos;   
    }

    public int minimaxify(int level, char currentPlayer, char[][] newBoard){
        char[][] newGameBoard = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                newGameBoard[i][j] = newBoard[i][j];
            }
        }
        
        // If s is terminal: return utility(s)
        if(winning('O', newGameBoard)) return 10;
        if(winning('X', newGameBoard)) return -10;
        ArrayList<Position> availableSpots = new ArrayList<Position>(getEmptySpots(newGameBoard));    
        if(availableSpots.isEmpty()) return 0;

        // Keeps track of the utility values of each state
        ArrayList<Integer> utilities = new ArrayList<Integer>();

        // Iterate through all available spots
        for(int i = 0; i < availableSpots.size(); i++){
            Position currentSpot = availableSpots.get(i);
            if(currentPlayer == 'O'){
                newGameBoard[currentSpot.xPos][currentSpot.yPos] = 'O';
                int cuurrenUtility = minimaxify(level + 1, 'X', newGameBoard);
                utilities.add(cuurrenUtility);
                if(level == 0) this.stateScores.add(new pointUtilities(cuurrenUtility, currentSpot));
            } else if (currentPlayer =='X'){
                newGameBoard[currentSpot.xPos][currentSpot.yPos] = 'X';
                utilities.add(minimaxify(level + 1, 'O', newGameBoard));
            }
            // clear curruntspot for next state
            newGameBoard[currentSpot.xPos][currentSpot.yPos] = ' ';            
        }
        /* 
            if s is max_node: return max_value(s)
            if s is min_node: return min_value(s)
        */
        if (currentPlayer == 'O') return max_value(utilities);
        else return min_value(utilities);
    }

    public MiniMax(char[][] board, char player){
        this.currentPlayer = player;
        this.board = board;
        this.stateScores = new ArrayList<pointUtilities>();
        minimaxify(0, this.currentPlayer, this.board);
        this.bestMove = getBestMove();
    }
}

// REFERENCE: http://www.codebytes.in/2014/08/minimax-algorithm-tic-tac-toe-ai-in.html