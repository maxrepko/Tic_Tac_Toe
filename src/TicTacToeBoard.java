import java.util.Random;

public class TicTacToeBoard {
    // Tracks the move count, as well as instantiates the board
    private int moveCount = 0;
    final static char[][] board = new char[3][3];

    // Fills the board with empty chars
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) setBoard(i ,j ,' ');
        }
    }

    // Prints stylized board
    public void printBoard() {
        System.out.println("\n/-----------\\");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(getBoard(i,j) + " | ");
            }
            System.out.print("\n");
        }
        System.out.println("\\-----------/\n\n");
    }

    public int getMoveCount() {return moveCount;}

    public void incrementMove() {moveCount++;}

    // Returns whether a space is empty or not, but inverted, as I inverted it every other instance anyway, saving the
    // extra '!' in the code, just because
    public boolean validateInput(int i, int j) {return board[i][j] != ' ';}

    // Generates a random int from the lower to the upper bound
    // I primarily use it to get a random number 1 - 3
    public int generateInt(int lower, int upper) {
        Random rand = new Random();
        return rand.nextInt(upper) + lower - 1;
    }

    // Once the first player has placed 3 tiles, check the possible combinations.
    // If no winner is found by move 9, the game is a tie
    public char winner() {
        if (moveCount >= 4) {
            for (int i = 0; i < 3; i++) {
                if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) return getBoard(i,0);
                if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) return getBoard(0, i);
            }
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) return getBoard(1,1);
            else if (board[2][0] == board[1][1] && board[1][1] == board[0][2]) return getBoard(2,0);
        }
        return ' ';
    }

    public void setBoard(int i, int j, char input) {board[i][j] = input;}

    public char getBoard(int i, int j) {return board[i][j];}
}
