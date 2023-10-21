import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class Word{

    private static ArrayList<String> letters = new ArrayList<String>();
    private static String answer = ""; 
    private static String addedAnswer = "";
    private static String output;

    public static String getOutput(){
        return output;
    }

    //selects a random five letter word from sgb-words.txt file
    public static String SelectFiveLetterWord() throws IOException{
        int n = (int)Math.floor(Math.random() * (686 - 0 + 1)); //selects random line from five letter word database
        //int n = (int)Math.floor(Math.random() * (5 - 0 + 1));
        try (Stream<String> lines = Files.lines(Paths.get("Database-Related\\fiveLetterWords.txt"))){
        //try (Stream<String> lines = Files.lines(Paths.get("test-words.txt"))){
            answer = lines.skip(n).findFirst().get(); //returns the word from line n
            if(UsedWords.checkUsed(answer) == true){
                return SelectFiveLetterWord();
            }
            return answer;
        }
        catch(IOException e){
            System.out.println(e);
        }
        return "nv"; //not valid
    }

    public static String addLetters(int difficulty, String answer){
        String added = "";
        if(answer.length()==5 && difficulty==2){
            Random r = new Random();
            char c = (char)(r.nextInt(26) + 'a');
            added += c;
        }
        if (added.equals("r") || added.equals("d") || added.equals("y") || added.equals("s")){
                addLetters(difficulty, answer);
        }
        answer += added.toLowerCase();
        return answer;
    }

    public static String checkCorrect(String response, String answer){
        output = "";
        for(int i = 0; i < answer.length(); i++){
            if(response.charAt(i)==(answer.charAt(i))){
                output += response.substring(i, i+1);
            }
            else{
                output += "_";
            }
        }
        return output;
    }

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

}