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
        totalPoints = 100 + Streak.streakMultiplier() + getDifMult() + getLevMult();
    }

    /*
     * Calculates the multiplier for difficulty
     * Difficulty 1 = +0
     * Difficulty 2 = +10
     * Difficulty 3 = +20
     */
    public static int getDifMult(){
        int difficulty = Word.getDifficulty();
        if(difficulty == 1){
            return 0;
        }
        else if(difficulty == 2){
            return 10;
        }
        else if(difficulty == 3){
            return 20;
        }
        return 0;
    }

    /*
     * Calculates the multiplier for difficulty
     * Level 1 = +0
     * Level 2 = +50
     * Level 3 = +100
     */
    public static int getLevMult(){
        int level = Word.getLevel();
        if(level == 1){
            return 0;
        }
        else if(level == 2){
            return 50;
        }
        else if(level == 3){
            return 100;
        }
        return 0;
    }

    //method to get the current number of points
    //minimum number of points is 0
    public static int getPoints(){
        if(totalPoints >= 0){
            return totalPoints;
        }
        return 0;
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
