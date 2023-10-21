public class Megash {
    private static int cash;

    public static int getCash(){
        return cash;
    }

    public static void calculateCash(){
        int points = Points.getPoints();
        cash = points/10;
    }

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
