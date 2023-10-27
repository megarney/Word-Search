/*
 * Adds a limited number of attempts per game
 * Number of attempts is equal to the length of the word
 * Can pay 50 Megash to get one more attempt
 */

import java.io.IOException;
//import java.util.Scanner;

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
        attempts--;
        if(attempts == 0){
            fail();
        }
        else{
            System.out.println("Attempts remaining: " + attempts);
        }
    }

    //when there are no more attempts left, either starts a new game or user pays for one more attempt
    public static void fail() throws IOException{
        System.out.println("No attempts remaining.\nEnter 1 to start a new game or enter 2 to spend $50 Megash for one more attempt.");
        response = Word.scan.nextLine();
        if(response.equals("2")){
            if(Megash.spendCash(50)){
                attempts++;
                System.out.println("Attempts remaining: 1\n" + Word.getScrambled() + "\n" + Word.getLastDisplayed());
                Word.getResponse();
            }
            else{
                System.out.println("Insufficient funds.");
                System.out.println("===================");
                Streak.loseStreak();
                Word.newGame();
            }
        }
        else{
            System.out.println("===================");
            Streak.loseStreak();
            Word.newGame();
        }
    }
}
