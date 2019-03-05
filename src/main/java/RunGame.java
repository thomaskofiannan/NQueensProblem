import java.util.Scanner;

public class RunGame {
    public static void main(String[] args) {
        System.out.println("Enter the dimensions for the chessboard:");
        ChessBoard chessBoard = new ChessBoard(new Scanner(System.in).nextInt());
        chessBoard.startGame();
    }
}
class ChessBoard {

    private static int gameBoard[][];
    private int numOfQueens;
    private static int boardDimension;

    public ChessBoard(int n) {
        numOfQueens = 0;
        boardDimension = n;
        gameBoard = new int[n][n];

        fillBoardWithZeros(n);
    }

    //fill empty board with Zeros
    private void fillBoardWithZeros(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                gameBoard[i][j] = 0;
            }
        }
    }

    public void startGame(){
        //starts the finding Spot with empty queen
        findSpot(0,0);
    }

    //True is place queen, False is remove queen
    private void placeQueen(int row, int column, boolean placeQueenInSpot) {
        if (placeQueenInSpot) {
            gameBoard[row][column] = 1;
            numOfQueens++;
        } else {
            gameBoard[row][column] = 0;
        }
    }

    //gets value of Cell
    private static int getCell(int row, int column) {
        if (row < 0 || column < 0 || row > boardDimension - 1 || column > boardDimension - 1) {
            return -1;
        }
        return gameBoard[row][column];
    }

    //Display board
    private void displayChessBoard() {
        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
                System.out.print(this.getCell(i, j) + " ");
            }
            System.out.println("");
        }
    }

    //solves the ChessBoard
    private boolean findSpot(int numOfQueens,int row) {
        //checks if the current number of queens is equal to the max
        if (numOfQueens == boardDimension) {
            this.displayChessBoard();
            return true;
        } else {
            for (int i = row; i < boardDimension; i++) {
                for (int j = 0; j < boardDimension; j++) {
                    //if its a accepted move
                    if (acceptedMove(i, j)) {
                        //place queen in this spot
                        this.placeQueen(i, j, true);
                        numOfQueens++;
                        //recursive call checks if its true
                        if (findSpot(numOfQueens, i+1)) {
                            return true;
                        } else {
                            //if its not it removes queen and try again now
                            this.placeQueen(i, j, false);
                            numOfQueens--;
                        }
                    }
                }
            }
        }
        return false;
    }

    //check to see if a move is accepted
    private static boolean acceptedMove(int row, int column) {

        //check diagonal  and angular attack
        for (int i = 0; i < boardDimension; i++) {
            if ((getCell(row - i, column - i) == 1) || (getCell(row - i, column + i) == 1)||(getCell(row + i, column - i) == 1)||(getCell(row + i, column + i) == 1) ){
                return false;
            }
        }

        for (int i = 0; i < boardDimension; i++) {
            //checks horizontal and vertical attacks
            if ((getCell(row, i) == 1) || (getCell(i, column) == 1) ) {
                return false;
            }
        }
        return true;
    }



}