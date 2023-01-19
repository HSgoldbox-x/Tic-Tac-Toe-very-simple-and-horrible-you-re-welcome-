import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {

    public int[] playgroundNR = new int[10];
    public String[] fieldNum = new String[]{" "," "," "," "," "," "," "," "," "," "};
    ArrayList<Integer> availableNumbers = new ArrayList<Integer>();
    public String user1Name = "USER1", user2Name = "USER2", symbolUser1 = "X", symbolUser2 = "O";
    public boolean gameOngoing = false, whosTurnItIs = false, chosenFieldIsFree = true, isAI_ON = false;
    public int roundNumber = 1, whoStartsFirst;

    public TicTacToe(boolean isAI_ON)
    {
        this.isAI_ON = isAI_ON;
    }

    public TicTacToe(boolean isAI_ON, String user2Name)
    {
        this.isAI_ON = isAI_ON;
        this.user2Name = user2Name;
    }

    public void startGameNStuff()
    {
        //gameOngoing = true;
        whoStartsFirst = (int)(Math.random()*2);
        switch (whoStartsFirst)
        {
            case 0:
                whosTurnItIs = false;
                break;
            case 1:
                whosTurnItIs = true;
                break;
        }
        game();
    }

    public void game()
    {
        int userConAnswer;
        boolean continueGame = true;
        while (continueGame == true)
        {
            setUpGame();
            for (int i = 0; i < 10; i++)
            {
                fieldNum[i] = " ";
            }
            gameOngoing = true;

            while (gameOngoing == true)
            {
                System.out.println();

                if (whosTurnItIs == false)
                {
                    playersTurn(user1Name, symbolUser1, whosTurnItIs, isAI_ON);
                    checkIfUSERWon(symbolUser1, user1Name);
                    roundNumber++;
                }
                else
                {
                    playersTurn(user2Name, symbolUser2, whosTurnItIs, isAI_ON);
                    checkIfUSERWon(symbolUser2, user2Name);
                    roundNumber++;
                }
                System.out.println("--------------------------------------------");
                playground();

                if (roundNumber == 10)
                {
                    gameOngoing = false;
                }
            }
            System.out.print("\n Das Spiel ist beendet! \n Wollen sie weiter spielen? \n\t 1 = Ja / 2 = Nein \n\t Eingabe: ");
            Scanner userAnswer = new Scanner(System.in);
            userConAnswer = userAnswer.nextInt();
            if (userConAnswer == 1)
            {
                continueGame = true;
            }
            else
            {
                continueGame = false;
            }
        }
    }

    public void playersTurn(String userName, String symbol, boolean AITurn, boolean isAI_ON)
    {
        System.out.println("Spieler '" + userName + "', waehle ein Feld aus!: \t");
        Scanner scanUserField = new Scanner(System.in);
        if (AITurn && isAI_ON == true)
        {
            fieldNum[randomAINumber()] = symbol;
        }
        else
        {
            while (chosenFieldIsFree == true)
            {
                int userField = scanUserField.nextInt();
                if ((userField < 1 || userField > 9) || (fieldNum[userField] == symbolUser1 || fieldNum[userField] == symbolUser2))
                {
                    System.out.println("Ungueltig! Erneute Eingabe erforderlich: \t");
                }
                else
                {
                    fieldNum[userField] = symbol;
                    for (int i = 0; i < availableNumbers.size(); i++)
                    {
                        if (availableNumbers.get(i) == userField)
                        {
                            availableNumbers.remove(i);
                        }
                    }
                    chosenFieldIsFree = false;
                }
            }
        }
        chosenFieldIsFree = true;
        whosTurnItIs = !whosTurnItIs;
    }

    public void checkIfUSERWon(String userFieldSymbol, String user)
    {
        boolean oneUserWon = false;
        String winCombiUser = userFieldSymbol + userFieldSymbol + userFieldSymbol;

        if(fieldNum[1] == userFieldSymbol && fieldNum[2] == userFieldSymbol && fieldNum[3] == userFieldSymbol)
        {
            fieldNum[1] = "-";
            fieldNum[2] = "-";
            fieldNum[3] = "-";
            oneUserWon = true;
        }
        else if (fieldNum[4] == userFieldSymbol && fieldNum[5] == userFieldSymbol && fieldNum[6] == userFieldSymbol)
        {
            fieldNum[4] = "-";
            fieldNum[5] = "-";
            fieldNum[6] = "-";
            oneUserWon = true;
        }
        else if (fieldNum[7] == userFieldSymbol && fieldNum[8] == userFieldSymbol && fieldNum[9] == userFieldSymbol)
        {
            fieldNum[7] = "-";
            fieldNum[8] = "-";
            fieldNum[9] = "-";
            oneUserWon = true;
        }
        else if (fieldNum[1] == userFieldSymbol && fieldNum[4] == userFieldSymbol && fieldNum[7] == userFieldSymbol)
        {
            fieldNum[1] = "|";
            fieldNum[4] = "|";
            fieldNum[7] = "|";
            oneUserWon = true;
        }
        else if (fieldNum[2] == userFieldSymbol && fieldNum[5] == userFieldSymbol && fieldNum[8] == userFieldSymbol)
        {
            fieldNum[2] = "|";
            fieldNum[5] = "|";
            fieldNum[8] = "|";
            oneUserWon = true;
        }
        else if (fieldNum[3] == userFieldSymbol && fieldNum[6] == userFieldSymbol && fieldNum[9] == userFieldSymbol)
        {
            fieldNum[3] = "|";
            fieldNum[6] = "|";
            fieldNum[9] = "|";
            oneUserWon = true;
        }
        else if (fieldNum[1] == userFieldSymbol && fieldNum[5] == userFieldSymbol && fieldNum[9] == userFieldSymbol)
        {
            fieldNum[1] = "\\";
            fieldNum[5] = "\\";
            fieldNum[9] = "\\";
            oneUserWon = true;
        }
        else if (fieldNum[3] == userFieldSymbol && fieldNum[5] == userFieldSymbol && fieldNum[7] == userFieldSymbol)
        {
            fieldNum[3] = "/";
            fieldNum[5] = "/";
            fieldNum[7] = "/";
            oneUserWon = true;
        }

        if (oneUserWon == true)
        {
            gameOngoing = false;
            System.out.println("\n" + user + " hat gewonnen!");
        }
    }

    public int randomAINumber()
    {
        int num = 0;
        int range = availableNumbers.size();
        int randNum = (int)(Math.random()*availableNumbers.size());
        num = availableNumbers.get(randNum);
        availableNumbers.remove(randNum);

        return num;
    }

    public void giveUserName(int userNum)
    {
        String userInput;
        Scanner userGivenName = new Scanner(System.in);
        System.out.print("\n\t" + userNum + ". Spieler, bitte geben sie sich einen Namen! \n\t Eingabe: \t");
        userInput = userGivenName.nextLine();

        if (userInput == "")
        {
            userInput = "USER" + userNum;
        }
        if (userNum == 1) user1Name = userInput;
        else user2Name = userInput;

        System.out.println("\t Ihr Name: " + userInput);
    }

    public void setUpGame()
    {
        roundNumber = 1;
        System.out.println("\n\t-- == Das Feld == --");
        stuffNSuch();
        testPlayground();
    }

    public void testPlayground()
    {
        System.out.println();
        System.out.println( "\t   "+playgroundNR[1] + "   |   "+playgroundNR[2]+"   |   "+playgroundNR[3]+"   \n"+
                            "\t ---------------------\n"+
                            "\t   "+playgroundNR[4] + "   |   "+playgroundNR[5]+"   |   "+playgroundNR[6]+"   \n"+
                            "\t ---------------------\n"+
                            "\t   "+playgroundNR[7]+"   |   " + playgroundNR[8]+"   |   "+playgroundNR[9]+"   ");
    }

    public void playground()
    {
        System.out.println("\r\n\t ----== Runde: " + roundNumber + " ==---- \n");
        System.out.println( "\t   "+ fieldNum[1] + "   |   "+ fieldNum[2]+"   |   "+ fieldNum[3]+"   \n"+
                            "\t ---------------------\n"+
                            "\t   "+ fieldNum[4] + "   |   "+ fieldNum[5]+"   |   "+ fieldNum[6]+"   \n"+
                            "\t ---------------------\n"+
                            "\t   "+ fieldNum[7]+"   |   " + fieldNum[8]+"   |   "+ fieldNum[9]+"   \r");
    }

    public void stuffNSuch()
    {
        for (int i = 1; i < 10; i++)
        {
            this.playgroundNR[i] = i;
            this.availableNumbers.add(i);
        }
    }

}
