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
        //Market:
        if(response.equals("1")){
            Market.displayMarket();
            getResponse();
        }
        //Quit:
        else if(response.equals("2")){
            scan.close();
        }
        //Help Menu:
        else if(response.equals("3")){
            Help.displayHelp();
        }
        //give up:
        else if(response.equals("4")){
            System.out.println("===================\nNew Game:");
            newGame();
        }
        //Incorrect Answer:
        else if(!response.toLowerCase().equals(answer)){
            incorrect();
        }
        //Correct Answer:
        else{
            correct();
        }
    }

    //if response from user is incorrect
    public static void incorrect() throws IOException{
        Points.failedAttempt(); //takes away 10 points
        Attempt.failedAttempt(); //takes away an attempt
        //if the input was invalid
        if(response.length() != answer.length()){
            System.out.println("Invalid word length.\nEnter 1 to go to the Market, 2 to quit, 3 for help, 4 to start a new round, or type guess below.\n" + scrambled);
            lastDisplayed = "Invalid word length.";
            getResponse();
        }
        //if the input was valid but incorrect - displays wordle
        else{
            System.out.println("Incorrect, letters in right spot: " + SelectWord.checkCorrect(response, answer)+ "\nEnter 1 to go to the Market, 2 to quit, 3 for help, 4 to start a new round, or type guess below.\n" + scrambled);
            lastDisplayed = SelectWord.checkCorrect(response, answer);
            getResponse();
        }
    }

    //if response from user is correct
    public static void correct() throws IOException{
        Streak.increaseStreak(); //increases streak
        Megash.calculateCash(); //calculates cash
        System.out.println("===================");
        System.out.println("Correct!\nStreak: " + Streak.getStreak() + "\nPoints Earned: " + Points.getPoints() + "\nMegash Earned: $" + Megash.getCash() + "\nMegash Total: $" + Megash.getTotalCash() + "\nWould you like to start a new game? 1 for yes, 2 for no");
        response = scan.nextLine();
        //new game
        if(response.equals("1")){
            System.out.println("===================");
            newGame();
        }
        //quit
        else if(response.equals("2")){
            scan.close();
            System.exit(0);
        }
        //invalid response
        else{
            System.out.println("Invalid response. Please try again.");
            correct();
        }
        
    }

    //prints letters in random order
    public static void printLetters(){
        System.out.println("Enter 1 to go to the Market, 2 to quit, 3 for help, 4 to start a new round, or type guess below.");
        scrambled = "";
        //adds all of the letters from the answer + any extra letters to an ArrayList
        for(int i = 0; i < addedAnswer.length(); i++){
            letters.add(addedAnswer.substring(i, i+1));
        }
        //Randomly selects a letter from the ArrayList, adds them to the scrambled string, then removes it from the ArrayList
        int rand;
        for(int i = 0; i < addedAnswer.length(); i++){
            rand = (int)Math.floor(Math.random() * (letters.size()));
            scrambled += letters.get(rand);
            letters.remove(rand);
        }
        //if the scrambled word is not the same as the answer, prints out the scrambled string
        if(!scrambled.equals(answer)){
            System.out.println(scrambled);
        }
        //if it is the same as the answer, restarts
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
        //difficulty 2 - adds one extra letter
        if(response.equals("2")){
            difficulty = 2;
            addedAnswer = SelectWord.addLetters();
        }
        //difficult 3 - adds two extra letters
        else if(response.equals("3")){
            difficulty = 3;
            addedAnswer = SelectWord.addLetters();
        }
        //difficulty 1 - adds no extra letters
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
        //level 2 - eight letter word
        if(response.equals("2")){
            answer = SelectWord.SelectEightLetterWord();
            level = 2;
        }
        //level 3 - twelve letter word
        else if(response.equals("3")){
            answer = SelectWord.SelectTwelveLetterWord();
            level = 3;
        }
        //level 1 - five letter word - default
        else{
            answer = SelectWord.SelectFiveLetterWord();
            level = 1;
        }
    }

    /*
     * Lets the user select the level/word length
     * Resets the Market, points, attempts, and cash
     * Lets the user select the difficulty/extra letters
     * Prints letters in a random order
     * Gets users input
     */
    public static void newGame() throws IOException{
        selectLevel();
        selectDifficulty();
        lastDisplayed = null;
        SelectWord.setOutput("");
        Market.newGame();
        Points.points();
        Attempt.attempts();
        Megash.newGame();
        //System.out.println(Word.getAnswer());
        printLetters();
        getResponse();
    }
}