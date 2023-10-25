/*
 * Used to keep track of the user's cash
 */

public class Megash {
    private static int cash = 200; //keeps track of balance

    //returns cash amount
    public static int getCash(){
        return cash;
    }

    //at the end of a game, calculates the number of cash earned
    public static void calculateCash(){
        cash = Points.getPoints()/10;
    }

    /*
     * Used when spending money in the market on hints
     * Checks to make sure the balance won't go negative
     * If it goes negative, cash will not be spent and will return false
     * If it won't go negative, cash will be spent and will return true
     */
    public static boolean spendCash(int spend){
        if(spend > cash){
            return false;
        }
        else{
            cash -= spend;
            return true;
        }
    }
}
