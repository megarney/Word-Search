/*
 * Keeps track of what can be bought with Megash and the cost
 * Things that can be bought: 
 *      -Hints: Max 3 per game, price goes up 5 with each hint within a game
 *      -Auto-Win: Used to keep streak but will not award any points
 *      -Mini games:
 *          -Sprint: Get as many words in x amount of time as possible for an increased rate of points and Megash
 *          -No Hint Challenge: Get as many words as possible without failing without being able to access the market (ie no hints)
 */

public class Market {
    private static int hintsUsed; //keeps track of how many hints are used in any given game
    private static int hintCost;

    //resets hintsUsed
    public static void newGame(){
        hintsUsed = 0;
        hintCost = 20;
    }
    
    //buy a hint only if the user has enough Megash
    public static void hint(){
        if(Megash.spendCash(hintCost)){
            Hint.giveHint();
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

    //display
    public static void displayMarket(){
        System.out.println("Market:\nHint: Reveal the placement of one letter in the word for " + hintCost + " Megash\nAuto-Win: Reveal the full word for 50 Megash");
    }
}
