package MachineProblem;

import lombok.Getter;
import lombok.Setter;
import java.util.Scanner;

public class TicTacToeGameManager {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        Player player1  = new Player("Shivam",'X',true);
        Player player2 = new Player("Aditya",'O',false);
        System.out.println("Hi "+player1.getName()+" and " +player2.getName()+ "in the game");
        Scanner sc = new Scanner(System.in);
        while(checkIfBoardEmpty(board)){
            Player playerTurn = player1.getTurn() ? player1 : player2;
            Player nextPlayer =! player1.getTurn() ? player1 : player2;
            System.out.println("Player's turn "+playerTurn.getName());
            System.out.println("Enter row position to fill ");
            int row = sc.nextInt();
            System.out.println("Enter col position to fill");
            int col = sc.nextInt();
            try{
                if(fillInBoard(board, row, col, playerTurn)){
                    System.out.println("Player won "+playerTurn.getName());
                    return;
                }
                playerTurn.setTurn(false);
                nextPlayer.setTurn(true);
                printBoard(board);
            } catch (Exception ex) {
                System.out.println("Error occurred while playing "+ex.getMessage());
                System.out.println("Please try again player "+playerTurn.getName());
            }
        }

        System.out.println("It's a draw game");

    }

    public static boolean fillInBoard(char[][] board, int row, int col,Player player) {
        if(row>=3 || col>=3) {
            throw new RuntimeException("This is an incorrect move");
        }
        if(board[row][col] != '\0') {
            throw new RuntimeException("Place is already occupied");
        }
        board[row][col] = player.getSymbol();
        return anyWin(board,player,row,col);

    }

    public static boolean anyWin(char[][] board, Player player, int row, int col) {
        boolean rowWin = true;
        boolean colWin  = true;
        boolean diagonalWin = false;
        for(int i = 0;i<3;i++) {
            if(board[row][i] != player.getSymbol()) {
                rowWin = false;
                break;
            }
        }

        for(int i = 0;i<3;i++) {
            if(board[i][col] != player.getSymbol()){
                colWin = false;
                break;
            }
        }
        if((row == 0 && col == 2) || (row == 2 && col == 0) || (row == col)) {
            if((board[0][0] == player.getSymbol() && board[1][1] == player.getSymbol() && board[2][2] == player.getSymbol())
            || (board[2][0] == player.getSymbol() && board[1][1] == player.getSymbol() && board[0][2] == player.getSymbol())){
                diagonalWin = true;
            }
        }
        return rowWin || colWin || diagonalWin;
    }

    public static boolean checkIfBoardEmpty(char[][] board) {
        for(int i = 0;i<3;i++) {
            for(int j = 0;j<3;j++) {
                if(board[i][j] =='\0') {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i][j] == '\0' ? '-' : board[i][j];
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}

@Getter
@Setter
class Player  {
    String name;
    char symbol;
    boolean turn;

    Player(String name, char symbol, boolean turn) {
        this.name = name;
        this.symbol = symbol;
        this.turn = turn;
    }

    public boolean getTurn (){
        return this.turn;
    }

}
