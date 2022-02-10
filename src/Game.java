import java.util.*;

public class Game {
    // Calls the end sequence with the result of the game, which was started in start()
    public static void main(String[] args) {endSequence(start());}

    // The main calls to run the game
    public static char start() {
        Scanner scan = new Scanner(System.in);
        int playerCount = setup(scan);
        announcement(playerCount);
        return run(playerCount, scan);
    }

    // Welcomes the player(s) and gets the number of players in the game
    public static int setup(Scanner scan) {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("-----------------------\n");
        System.out.print("Input number of players: ");
        return Functions.getIntInRange(1,2,scan);
    }

    // Decides who starts, creates the board, and then plays until either the board is full or a winner is found
    public static char run(int playerCount, Scanner scan) {
        // Decides player turn by "flipping a coin"
        int playerTurn = Functions.flipCoin() ? 1:2;

        // Informs player of who starts, then initializes the game board
        System.out.println("\nBeginning game!\n");
        System.out.printf("Player %d starts\n", playerTurn);
        TicTacToeBoard board = new TicTacToeBoard();
        board.initializeBoard();

        // Plays the game as long as there are open spaces and no winners
        while (board.winner() == ' ' && board.getMoveCount() < 9) {
            // If Player 1's turn, play their valid turn and set to other player's turn
            if (playerTurn == 1) {
                playTurn(scan, board, 'X');
                playerTurn = 2;
            }
            // If Player 2's turn, play their valid turn and set to other player's turn
            else if (playerCount == 2){
                playTurn(scan, board, 'O');
                playerTurn = 1;
            }
            // If Player 2's turn, but Player 2 is the AI, play AI's random move
            else {
                playAITurn(board);
                playerTurn = 1;
            }

            // Shows the current game board with the new move and increments the turn counter
            board.printBoard();
            board.incrementMove();
        }
        // Returns the winner of the game
        return board.winner();
    }

    // Gets user input (validated through Functions) and makes sure that it is an empty space before setting the space
    public static void playTurn(Scanner scan, TicTacToeBoard board, char playerTile) {
        System.out.print("Input the column (1-3): ");
        int j = Functions.getIntInRange(1,3,scan) - 1;
        System.out.print("Input the row (1-3): ");
        int i = Functions.getIntInRange(1,3,scan) - 1;
        while (board.validateInput(i, j)) {
            System.out.printf("The space %d, %d is already taken\n\n", j + 1, i + 1);
            System.out.print("Input the column (1-3): ");
            j = Functions.getIntInRange(1,3,scan) - 1;
            System.out.print("Input the row (1-3): ");
            i = Functions.getIntInRange(1,3,scan) - 1;

        }
        board.setBoard(i, j, playerTile);
    }

    // Generates two random moves that result in a free space and places it there
    public static void playAITurn(TicTacToeBoard board) {
        int i = board.generateInt(1,3);
        int j = board.generateInt(1,3);
        while (board.validateInput(i, j)) {
            i = board.generateInt(1,3);
            j = board.generateInt(1,3);
        }
        board.setBoard(i, j, 'O');
    }

    // Announces the game style (Player vs. Player or Player vs. AI) and then states the rules
    public static void announcement(int playerCount) {
        if(playerCount == 1) System.out.println("You chose to play versus the PC!");
        if(playerCount == 2) System.out.println("You chose to play versus another player!");
        System.out.println("\nThe rules:");
        System.out.println("1) The players take turns placing their tile onto the board");
        System.out.println("2) You can only place on open spaces");
        System.out.println("3) The first player to get three of their tiles in an uninterrupted row is the winner");
        System.out.println("In the event that no one has three in a row by the end of the game, the game is tied");
    }

    // Announces the winner and thanks the player(s)
    public static void endSequence(char winner) {
        switch (winner) {
            case ' ' -> System.out.println("The game was a draw, better luck next time!");
            case 'X' -> System.out.println("Congrats Player 1!");
            case 'O' -> System.out.println("Congrats Player 2!");
            default -> System.out.println("Good Game");
        }
        System.out.println("\nThank you for playing Tic Tac Toe!");
    }
}
