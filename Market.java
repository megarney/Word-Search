/*
 * Keeps track of what can be bought with Megash and the cost
 * Things that can be bought: 
 *      -Hints: Max 3 per game, price goes up 5 with each hint within a game
 *      -Auto-Win: Used to reveal the word spending 50 cash
 *      -Mini games:
 *          -Sprint: Get as many words in x amount of time as possible for an increased rate of points and Megash
 *          -No Hint Challenge: Get as many words as possible without failing without being able to access the market (ie no hints)
 */

import java.io.IOException;
//import java.util.Scanner;

public class Market {
    private static int hintCost; //keeps track of the current cost of a hint
    private static int hintsAvailable; //keeps track of how many hints are available for the game
    //private static Scanner scan = new Scanner(System.in);
    private static String response;

    /*
     * Keeps track of how many hints are available per game
     * 5 letters - 2 hints
     * 8 letters - 3 hints
     * 12 letters - 4 hints
     */
    public static void newGame(){
        hintCost = 20; //sets the hint cost to the base 20
        //sets the number of hints available based on the level/word length
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
    public static void hint() throws IOException{
        if(hintsAvailable == 0){
            System.out.println("You have already used the max number of hints for this game");
            System.out.println("===================");
            System.out.println("Returned to game mode.");
            System.out.println("Enter 1 to go to the Market, 2 to quit, or type guess below.");
        }
        else if(Megash.spendCash(hintCost)){
            System.out.println("===================");
            System.out.println("Returned to game mode.");
            System.out.println(Word.getScrambled() + "\nHint: ");
            Hint.hint();
            Hint.setWordle();
            Hint.giveHint();
            hintsAvailable--;
            hintCost += 5;
            Word.getResponse();
        }
        else{
            System.out.println("You do not have enough Megash to buy a hint.");
        }
    }

    //reveal the answer with 50 Megash
    public static void autoWin(){
        if(Megash.spendCash(50)){
            System.out.println("===================");
            System.out.println("Returned to game mode.");
            System.out.println("Answer: " + Word.getAnswer());
            System.out.println("Enter 1 to go to the Market, 2 to quit, or type guess below.");
        }
        else{
            System.out.println("You do not have enough Megash to buy an Auto-Win.");
        }
    }

    //display all items for sale
    public static void displayMarket() throws IOException{
        System.out.println("===================");
        System.out.println("Megash: $" + Megash.getTotalCash());
        System.out.println("Market:");
        System.out.println("Hint: Reveal the placement of one letter in the word for " + hintCost + " Megash"); 
        System.out.println("Auto-Win: Reveal the full word for 50 Megash");
        System.out.println("Press 1 to exit Market and 2 to buy");
        getResponse();
    }

    //gets the response from user to either buy or return to game
    public static void getResponse() throws IOException{
        response = Word.scan.nextLine();
        if(response.equals("1")){
            System.out.println("===================");
            System.out.println("Returned to game mode.");
            System.out.println(Word.getScrambled() + "\n" + Word.getLastDisplayed());
            Word.getResponse();
        }
        else if(response.equals("2")){
            buy();
        }
    }

    //method that lets the user buy an item
    public static void buy() throws IOException{
        System.out.println("===================");
        boolean hint = hintBuy();
        boolean autoWin = autoWinBuy();
        System.out.println("Press 3 to return to the Market or 4 to return to the game.");
        response = Word.scan.nextLine();
        if(response.equals("1") && hint){
            hint();
            Word.getResponse();
        }
        else if(response.equals("2") && autoWin){
            autoWin();
            Word.getResponse();
        }
        else if(response.equals("3")){
            displayMarket();
        }
        else if(response.equals("4")){
            System.out.println("===================");
            System.out.println("Returned to game mode.");
            System.out.println(Word.getScrambled() + "\n" + Word.getLastDisplayed());
            Word.getResponse();
        }
        else{
            System.out.println("===================");
            System.out.println("Response invalid. Please try again.");
            buy();
        }
    }

    //user tries to buy a hint - fail or success based on if they can afford it
    public static boolean hintBuy() throws IOException{
        if(Megash.canSpend(hintCost)){
            System.out.println("Press 1 to buy a Hint for $" + hintCost);
            return true;
        }
        else{
            System.out.println("You need $" + (hintCost-Megash.getCash()) + " more to buy a hint");
            return false;
        }
    }

    //user tries to buy an auto-win - fail or success based on if they can afford it
    public static boolean autoWinBuy() throws IOException{
        if(Megash.canSpend(50)){
            System.out.println("Press 2 to buy an Auto-Win for $50");
            return true;
        }
        else{
            System.out.println("You need $" + (50-Megash.getCash()) + " more to buy an Auto-Win");
            return false;
        }
    }
}
