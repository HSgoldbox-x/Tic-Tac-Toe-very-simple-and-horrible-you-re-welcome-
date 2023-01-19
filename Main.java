import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    System.out.println("\n-\t-\t=\t=\tTicTac Toe\t=\t=\t-\t-");
    System.out.print("\t Welchen Modus wollen sie spielen? \n\t 1 = Local \t 2 = Offline \n\t Eingabe: ");
    Scanner userInput = new Scanner(System.in);

    int userModeChoice = userInput.nextInt();

    switch (userModeChoice)
    {
        case 1:
            TicTacToe tictactoePVP1 = new TicTacToe(false);
            tictactoePVP1.giveUserName(1);
            tictactoePVP1.giveUserName(2);
            tictactoePVP1.startGameNStuff();
            break;
        case 2:
            TicTacToe tictactoeAI1 = new TicTacToe(true, "Computer");
            //tictactoeAI1.giveUserName();
            tictactoeAI1.startGameNStuff();
            break;
        default:
            System.out.println("\n Eingabe ungueltig! Modus '2' wurde automatisch ausgewaehlt");
            TicTacToe tictactoeAI2 = new TicTacToe(true, "Computer");
            tictactoeAI2.startGameNStuff();

    }


    }
}