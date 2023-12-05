/*
 * Adds a limited number of attempts per game
 * Number of attempts is equal to the length of the word
 * Can pay 50 Megash to get one more attempt
 */

import java.io.IOException;

public class Attempt {
    private static int attempts; //keeps track of the number of attempts
    //private static Scanner scan = new Scanner(System.in);
    private static String response;

    //sets the number of attempts to the word length at the beginning of a game
    public static void attempts(){
        attempts = Word.getAnswer().length();
    }

    //loses an attempt
    public static void failedAttempt() throws IOException{
        attempts--; //subtracts from total attempt
        if(attempts == 0){ //if there are no more attempts remaining - fail
            fail();
        }
        else{
            System.out.println("Attempts remaining: " + attempts); //displays attempts remaining
        }
    }

    //when there are no more attempts left, either starts a new game or user pays for one more attempt
    public static void fail() throws IOException{
        System.out.println("No attempts remaining.\nEnter 1 to start a new game or enter 2 to spend $50 Megash for one more attempt.");
        response = Word.scan.nextLine();
        if(response.equals("2")){ //if the user selects to spend $50 for a new attempt
            if(Megash.spendCash(50)){ //if they have a sufficient amount to pay for another attempt, they get one more try
                attempts++;
                System.out.println("Attempts remaining: 1\n" + Word.getScrambled() + "\n" + Word.getLastDisplayed());
                Word.getResponse();
            }
            else{ //if they can not afford it, they lose their streak and a new game starts
                System.out.println("Insufficient funds.");
                System.out.println("===================");
                System.out.println("The correct answer was: " + Word.getAnswer());
                Streak.loseStreak();
                Word.newGame();
            }
        }
        else{ //if the user does not try to pay for another attempt, loses streak and a new game starts
            System.out.println("===================");
            System.out.println("The correct answer was: " + Word.getAnswer());
            Streak.loseStreak();
            Word.newGame();
        }
    }
}
