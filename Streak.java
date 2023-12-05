/*
 * Keeps track of the streak for all games
 */

public class Streak {
    private static int streak; //counts number of games won in a row

    //initiates streak at 0
    public static void streak(){
        streak = 0;
    }

    public static int getStreak(){
        return streak;
    }

    //when a game is won, streak is increased
    public static void increaseStreak(){
        streak++;
    }

    //when a game is lost, streak goes back to 0
    public static void loseStreak(){
        streak=0;
    }

    //used in points to add 10 points per streak number
    public static int streakMultiplier(){
        return streak*10;
    }
}
