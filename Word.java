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
    private static Scanner scan = new Scanner(System.in);
    private static String answer = ""; //keeps track of answer
    private static String addedAnswer = ""; //answer with added letters
    private static String response = "";
    private static ArrayList<String> letters = new ArrayList<String>(); //used to print letters in a random order
    private static String lastDisplayed;
    private static String scrambled;

    //returns answer
    public static String getAnswer(){
        return answer;
    }

    public static String getLastDisplayed(){
        return lastDisplayed;
    }

    public static String getScrambled(){
        return scrambled;
    }

    //gets response from user
    public static void getResponse() throws IOException{
        response = scan.nextLine();
        if(response.equals("1")){
            Market.displayMarket();
            getResponse();
        }
        else if(!response.toLowerCase().equals(answer)){
            if(response.length() != answer.length()){
                System.out.println("Invalid word length. Enter 1 to go to the Market.");
                lastDisplayed = "Invalid word length.";
                getResponse();
            }
            else{
                System.out.println("Incorrect, letters in right spot: " + SelectWord.checkCorrect(response, answer)+ "\nEnter 1 to go to the Market.");
                lastDisplayed = SelectWord.checkCorrect(response, answer);
                getResponse();
            }
        }
        else{
            System.out.println("Correct! Would you like to start a new game? 1 for yes, 2 for no");
            response = scan.nextLine();
            if(response.equals("1")){
                newGame();
            }
            if(response.equals("2")){
                scan.close();
            }
        }
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
            System.out.print(letters.get(rand));
            scrambled += letters.get(rand);
            letters.remove(rand);
        }
        System.out.println();
    }

    //lets user select a difficulty
    public static void selectDifficulty() throws IOException{
        answer = SelectWord.SelectFiveLetterWord();
        Market.newGame();
        System.out.println("Select Difficulty Level: 1 for easy, 2 for medium, 3 for hard.");
        response = scan.nextLine();
        if(response.equals("2")){
            addedAnswer = SelectWord.addLetters(2, answer);
        }
        else if(response.equals("3")){
            addedAnswer = SelectWord.addLetters(3, answer);
        }
        else{
            addedAnswer = answer;
        }
    }

    //initiates new game
    public static void newGame() throws IOException{
        selectDifficulty();
        //System.out.println(Word.getAnswer());
        printLetters();
        getResponse();
    }
}