/*
 * Keeps track of the points for each game
 * Resets points with each game
 * Base number of points: 100
 * Adds streak multiplier
 */

public class Points {
    private static int totalPoints; //keeps track of number of points in a game

    //starts points at 100 plus the streak multiplier
    public static void points(){
        totalPoints = 100 + Streak.streakMultiplier();
    }

    //method to get the current number of poitns
    public static int getPoints(){
        return totalPoints;
    }

    //failed attempt takes away 10 points
    public static void failedAttempt(){
        totalPoints -= 10;
    }

    //using a hint takes away 20 points
    public static void usedHint(){
        totalPoints -= 20;
    }

    //using all hints sets points gained to 0
    //while you don't gain any points, you still keep your streak
    public static void allHints(){
        totalPoints = 0;
    }
}
