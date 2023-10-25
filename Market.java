/*
 * Keeps track of what can be bought with Megash and the cost
 * Things that can be bought: 
 *      -Hints: Max 3 per game, price goes up 5 with each hint within a game
 *      -Auto-Win: Used to keep streak but will not award any points
 *      -Mini games:
 *          -Sprint: Get as many words in x amount of time as possible for an increased rate of points and Megash
 *          -No Hint Challenge: Get as many words as possible without failing without being able to access the market (ie no hints)
 */

import java.io.IOException;
import java.util.Scanner;

public class Market {
    private static int hintCost;
    private static int hintsAvailable;
    private static Scanner scan = new Scanner(System.in);
    private static int response;

    //resets hintsUsed
    public static void newGame(){
        hintCost = 20;
        int wordLength = Word.getAnswer().length();
        if(wordLength == 5){
            hintsAvailable = 2;
        }
        else if(wordLength == 8){
            hintsAvailable = 3;
        }
        else if(wordLength == 12){
            hintsAvailable = 4;
        }
    }
    
    //buy a hint only if the user has enough Megash and has not used the max number of hints
    public static void hint(){
        if(hintsAvailable != 0){
            System.out.println("You have already used the max number of hints for this game");
        }
        else if(Megash.spendCash(hintCost)){
            Hint.hint();
            Hint.giveHint();
            hintsAvailable--;
            hintCost += 5;
        }
        else{
            System.out.println("You do not have enough Megash to buy a hint.");
        }
    }

    //reveal the answer with 50 Megash
    public static void autoWin(){
        if(Megash.spendCash(50)){
            System.out.println(Word.getAnswer());
        }
        else{
            System.out.println("You do not have enough Megash to buy an Auto-Win.");
        }
    }

    //display all items for sale
    public static void displayMarket() throws IOException{
        System.out.println("Market:");
        System.out.println("Hint: Reveal the placement of one letter in the word for " + hintCost + " Megash"); 
        System.out.println("Auto-Win: Reveal the full word for 50 Megash");
        System.out.println("Press 1 to exit Market and 2 to buy");
        getResponse();
    }

    //gets the response from user to either buy or return to game
    public static void getResponse() throws IOException{
        response = scan.nextInt();
        if(response == 1){
            System.out.println("Returned to game mode.");
            System.out.println(Word.getLastDisplayed());
            Word.getResponse();
        }
        else if(response == 2){
            buy();
        }
        scan.close();
    }

    //method that lets the user buy an item
    public static void buy() throws IOException{
        System.out.println("Press 1 to buy a Hint");
        System.out.println("Press 2 to buy an Auto-Win");
        response = scan.nextInt();
        if(response == 1){
            System.out.println("Returned to game mode.");
            System.out.println(Word.getScrambled());
            hint();
            Word.getResponse();
        }
        else if(response == 2){
            System.out.println("Returned to game mode.");
            autoWin();
            Word.getResponse();
        }
        else{
            System.out.println("Response invalid. Please try again.");
            buy();
        }
    }
}
