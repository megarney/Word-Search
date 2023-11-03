/*
 * Used to keep track of the user's cash
 */

public class Megash {
    private static int totalCash = 200; //keeps track of balance
    private static int cash; //keeps track of cash for that game

    //resets cash
    public static void newGame(){
        cash = 0;
    }

    //returns cash total
    public static int getTotalCash(){
        return totalCash;
    }

    //returns game's cash
    public static int getCash(){
        return cash;
    }

    //at the end of a game, calculates the number of cash earned
    public static void calculateCash(){
        cash = Points.getPoints()/10;
        totalCash = totalCash + cash; 
    }

    /*
     * Used when spending money in the market on hints
     * Checks to make sure the balance won't go negative
     * If it goes negative, cash will not be spent and will return false
     * If it won't go negative, cash will be spent and will return true
     */
    public static boolean spendCash(int spend){
        if(spend > totalCash){
            return false;
        }
        else{
            totalCash -= spend;
            return true;
        }
    }

    //returns a boolean value based on whether or not the user can spend a specific amount
    public static boolean canSpend(int spend){
        if(spend > totalCash){
            return false;
        }
        else{
            return true;
        }
    }
}
