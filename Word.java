import java.io.IOException;
import java.nio.file.Files;
//import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.*;

public class Word{

    private static String answer = ""; 
    private static String response = "";
    private static ArrayList<String> letters = new ArrayList<String>();

    //returns answer
    public static String getAnswer(){
        return answer;
    }

    //gets response from user
    public static void getResponse() throws IOException{
        Scanner scan = new Scanner(System.in);
        response = scan.nextLine();
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
        }
        scan.close();
    }

    //prints letters in random order
    public static void printLetters(){
        for(int i = 0; i < answer.length(); i++){
            letters.add(answer.substring(i, i+1));
        }

        int rand;
        for(int i = 0; i < answer.length(); i++){
            rand = (int)Math.floor(Math.random() * (letters.size()));
            System.out.print(letters.get(rand));
            letters.remove(rand);
        }
        
        System.out.println();
    }

    /*public static boolean checkValid(String response){
        
    }*/

    //initiates new game
    public static void newGame() throws IOException{
        answer = SelectWord.SelectFiveLetterWord(); 
        System.out.println(Word.getAnswer());
        printLetters();
        getResponse();
    }
}