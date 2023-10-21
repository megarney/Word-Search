public class Points {
    private static int totalPoints;
    //private static int attempts;

    public static int getPoints(){
        return totalPoints;
    }
    
    public static void points(int streak){
        totalPoints = 100*streak;
    }

    public static void failedAttempt(){
        totalPoints -= 10;
    }

    public static void usedHint(){
        totalPoints -= 20;
    }

    public static void allHints(){
        totalPoints = 0;
    }
}
