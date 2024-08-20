import java.util.Scanner;

public class TicTacToe {
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'О';
    private boolean boxAvailable = false;
    private boolean boxEmpty = false;
    private final char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private byte winner = 0;
    private static final Scanner scan = new Scanner(System.in);
    private final int[][] WINNING_COMBINATIONS = {
            {0, 1, 2}, // горизонтально 1 рядок
            {3, 4, 5}, // горизонтально 2 рядок
            {6, 7, 8}, // горизонтально 3 рядок
            {0, 3, 6}, // вертикально 1 колонка
            {1, 4, 7}, // вертикально 2 колонка
            {2, 5, 8}, // вертикально 3 колонка
            {0, 4, 8}, // діагональ зліва направо
            {2, 4, 6}  // діагональ справа наліво
    };

    private void displayBoard() {
        System.out.println("\n\n " + this.box[0] + " | " + this.box[1] + " | " + this.box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + this.box[3] + " | " + this.box[4] + " | " + this.box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + this.box[6] + " | " + this.box[7] + " | " + this.box[8] + " \n");
        if (!this.boxEmpty) {
            for (int i = 0; i < 9; i++) {
                this.box[i] = ' ';
            }
            this.boxEmpty = true;
        }
    }

    private void checkOnEndGame() {
        switch (this.winner) {
            case (1):
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case (2):
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case (3):
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            default:
                System.out.println("Error in the system, please report about bug");
        }
    }

    private void characterInputInBox() {
        while (true) {
            byte input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (this.box[input - 1] == 'X' || this.box[input - 1] == 'O')
                    System.out.println("That one is already in use. Enter another.");
                else {
                    box[input - 1] = 'X';
                    break;
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    private void checkOnPlayerWin() {
        for (int[] combination : WINNING_COMBINATIONS) {
            if (box[combination[0]] == PLAYER_X &&
                    box[combination[1]] == PLAYER_X &&
                    box[combination[2]] == PLAYER_X) {
                this.winner = 1;
                return;
            }
        }
    }

    private void checkOnComputerWin() {
        for (int[] combination : WINNING_COMBINATIONS) {
            if (box[combination[0]] == PLAYER_O &&
                    box[combination[1]] == PLAYER_O &&
                    box[combination[2]] == PLAYER_O) {
                this.winner = 2;
                return;
            }
        }
    }

    private void checkOnDraw() {
        if (!boxAvailable) {
            winner = 3;
        }
    }

    private void checkBoxAvailable() {
        boxAvailable = false;
        for (int i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                boxAvailable = true;
                break;
            }
        }
    }

    private void computerMove() {
        if(!boxAvailable) return;
        while (true) {
           byte rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }


    private void sendStartInfo() {
        System.out.println(("Enter box number to select. Enjoy!\n"));
    }

    public void startGame() {
        sendStartInfo();
        while (winner == 0) {
            displayBoard();
            characterInputInBox();
            checkBoxAvailable();
            checkOnDraw();
            checkOnPlayerWin();
            if (winner==0){
                computerMove();
                checkOnComputerWin();
            }

        }
        displayBoard();
        checkOnEndGame();
    }
}
