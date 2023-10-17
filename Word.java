import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Word{

    private static Scanner scan = new Scanner(System.in);
    private static String answer = ""; 
    private static String addedAnswer = "";
    private static ArrayList<String> letters = new ArrayList<String>();

    //returns answer
    public static String getAnswer(){
        return answer;
    }

    //gets response from user
    public static void getResponse() throws IOException{
        String response = scan.nextLine();
        if(!response.toLowerCase().equals(answer)){
            System.out.println("Incorrect. Try again.");
            getResponse();
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
        for(int i = 0; i < addedAnswer.length(); i++){
            letters.add(addedAnswer.substring(i, i+1));
        }
        int rand;
        for(int i = 0; i < addedAnswer.length(); i++){
            rand = (int)Math.floor(Math.random() * (letters.size()));
            System.out.print(letters.get(rand));
            letters.remove(rand);
        }
        System.out.println();
    }

    public static void selectDifficulty() throws IOException{
        answer = SelectWord.SelectFiveLetterWord();
        System.out.println("Select Difficulty Level: 1 for easy, 2 for medium, 3 for hard.");
        String response = scan.nextLine();
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