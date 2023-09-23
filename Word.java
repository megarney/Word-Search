import java.io.IOException;
import java.nio.file.Files;
//import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.*;

public class Word{

    private static String answer = "";  //selects five letter word
    private static String response = "";
    private static ArrayList<String> letters = new ArrayList<String>();
    
    //selects a random five letter word from sgb-words.txt file
    public static void SelectFiveLetterWord() throws IOException{
        int n = (int)Math.floor(Math.random() * (5757 - 0 + 1)); //selects random line from five letter word database
        try (Stream<String> lines = Files.lines(Paths.get("sgb-words.txt"))){
            answer = lines.skip(n).findFirst().get(); //returns the word from line n
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

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

    //initiates new game
    public static void newGame() throws IOException{
        SelectFiveLetterWord(); 
        //System.out.println(Word.getAnswer());
        printLetters();
        getResponse();
    }
}