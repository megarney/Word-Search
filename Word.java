/*
 * Initiates a new game
 * Gets all responses from user
 * Prints letters in a random order
 * Lets user select a difficulty that will add extra letters
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Word{
    public static Scanner scan = new Scanner(System.in);
    private static String answer = ""; //keeps track of answer
    private static String addedAnswer = ""; //answer with added letters
    private static String response = "";
    private static ArrayList<String> letters = new ArrayList<String>(); //used to print letters in a random order
    private static String lastDisplayed;  //keeps track of what was last displayed to the user - used for Market
    private static String scrambled; //keeps track of the original scrambled word - used for Market
    private static int difficulty; //keeps track of the difficulty - used to calculate points
    private static int level; //keeps track of the level - used to calculate points

    //gets answer
    public static String getAnswer(){
        return answer;
    }

    //gets last displayed
    public static String getLastDisplayed(){
        return lastDisplayed;
    }

    //gets scrambled
    public static String getScrambled(){
        return scrambled;
    }

    //gets difficulty
    public static int getDifficulty(){
        return difficulty;
    }

    //gets level
    public static int getLevel(){
        return level;
    }

    //gets response from user
    public static void getResponse() throws IOException{
        response = scan.nextLine();
        if(response.equals("1")){
            Market.displayMarket();
            getResponse();
        }
        else if(response.equals("2")){
            scan.close();
        }
        else if(!response.toLowerCase().equals(answer)){
            incorrect();
        }
        else{
            correct();
        }
    }

    //if response from user is incorrect - takes away points and an attempt
    public static void incorrect() throws IOException{
        Points.failedAttempt();
        Attempt.failedAttempt();
        if(response.length() != answer.length()){
            System.out.println("Invalid word length.\nEnter 1 to go to the Market or 2 to quit.\n" + scrambled);
            lastDisplayed = "Invalid word length.";
            getResponse();
        }
        else{
            System.out.println("Incorrect, letters in right spot: " + SelectWord.checkCorrect(response, answer)+ "\nEnter 1 to go to the Market or 2 to quit.\n" + scrambled);
            lastDisplayed = SelectWord.checkCorrect(response, answer);
            getResponse();
        }
    }

    //if response from user is correct - increases streak and calculates total cash
    public static void correct() throws IOException{
        Streak.increaseStreak();
        Megash.calculateCash();
        System.out.println("===================");
        System.out.println("Correct!\nPoints Earned: " + Points.getPoints() + "\nMegash Earned: $" + Megash.getCash() + "\nMegash Total: $" + Megash.getTotalCash() + "\nWould you like to start a new game? 1 for yes, 2 for no");
        response = scan.nextLine();
        if(response.equals("1")){
            System.out.println("===================");
            newGame();
        }
        scan.close();
        System.exit(0);
    }

    //prints letters in random order
    public static void printLetters(){
        scrambled = "";
        for(int i = 0; i < addedAnswer.length(); i++){
            letters.add(addedAnswer.substring(i, i+1));
        }
        int rand;
        for(int i = 0; i < addedAnswer.length(); i++){
            rand = (int)Math.floor(Math.random() * (letters.size()));
            scrambled += letters.get(rand);
            letters.remove(rand);
        }
        if(!scrambled.equals(answer)){
            System.out.println(scrambled);
        }
        else{
            printLetters();
        }
    }

    /*
     * Lets user select difficulty
     * 1 - adds no extra letters
     * 2 - adds 1 extra letter
     * 3 - addes 2 extra letters
     */
    public static void selectDifficulty() throws IOException{
        System.out.println("Select Difficulty: 1 for easy, 2 for medium, 3 for hard.");
        response = scan.nextLine();
        if(response.equals("2")){
            difficulty = 2;
            addedAnswer = SelectWord.addLetters();
        }
        else if(response.equals("3")){
            difficulty = 3;
            addedAnswer = SelectWord.addLetters();
        }
        else{
            addedAnswer = answer.toLowerCase();
            difficulty = 1;
        }
        System.out.println("===================");
    }

    /*
     * Lets user select a level
     * 1 - 5 letter word
     * 2 - 8 letter word
     * 3 - 12 letter word
     * Selects the answer
     */
    public static void selectLevel() throws IOException{
        System.out.println("Select Level: 1 for a five letter word, 2 for an eight letter word, 3 for a twelve letter word.");
        response = scan.nextLine();
        if(response.equals("2")){
            answer = SelectWord.SelectEightLetterWord();
            level = 2;
        }
        else if(response.equals("3")){
            answer = SelectWord.SelectTwelveLetterWord();
            level = 3;
        }
        else{
            answer = SelectWord.SelectFiveLetterWord();
            level = 1;
        }
    }

    /*
     * 1. Lets the user select the level/word length
     * 2 & 3 & 4 & 5. Resets the Market, points, attempts, and cash
     * 6. Lets the user select the difficulty/extra letters
     * 7. Prints letters in a random order
     * 8. Gets users input
     */
    public static void newGame() throws IOException{
        selectLevel();
        Market.newGame();
        Points.points();
        Attempt.attempts();
        Megash.newGame();
        selectDifficulty();
        //System.out.println(Word.getAnswer());
        printLetters();
        getResponse();
    }
}