/*
 * Selects word from database based on the word length
 * Checks with UsedWords to make sure the word has not been used before
 * Adds letters based on difficulty
 * If the answer is wrong, check which letters are in the right spot
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

public class SelectWord {
    /*
     * the output when checking which letters are in the right spot
     * ex: answer is bingo, guess is bnigo, output is b__go
     */
    private static String output;
    private static boolean demo = false;

    //returns output
    public static String getOutput(){
        return output;
    }

    public static void setOutput(String putout){
        output = putout;
    }

    public static void setDemoTrue(){
        demo = true;
    }

    //selects a random five letter word
    public static String SelectFiveLetterWord() throws IOException{
        String answer = "";
        if(UsedWords.getFiveAvailable() == 0){
            Word.noWordsAvailable();
        }
        else if(demo == false){ //if not a demo
            int n = (int)Math.floor(Math.random() * (686)); //selects random line from five letter word database
            try (Stream<String> lines = Files.lines(Paths.get("Database-Related\\fiveLetterWords.txt"))){
                answer = lines.skip(n).findFirst().get(); //returns the word from line n
                if(UsedWords.checkUsed(answer) == true){
                    return SelectFiveLetterWord();
                }
            }
            catch(IOException e){
                System.out.println(e);
            }
            UsedWords.fiveUsed();
        }
        else{ //if it is a demo
            int n = (int)Math.floor(Math.random() * (5)); //selects random line from five letter word database
            System.out.println(n);
            try (Stream<String> lines = Files.lines(Paths.get("Database-Related\\demoFiveLetters.txt"))){
                answer = lines.skip(n).findFirst().get(); //returns the word from line n
                if(UsedWords.checkUsed(answer) == true){
                    return SelectFiveLetterWord();
                }
            }
            catch(IOException e){
                System.out.println(e);
            }
            UsedWords.fiveUsed();
        }
        return answer;
    }

    //selects a random eight letter word
    public static String SelectEightLetterWord() throws IOException{
        String answer = "";
        if(UsedWords.getEightAvailable() == 0){
            Word.noWordsAvailable();
        }
        else if(demo == false){
            int n = (int)Math.floor(Math.random() * (504)); 
            try (Stream<String> lines = Files.lines(Paths.get("Database-Related\\eightLetterWords.txt"))){
                answer = lines.skip(n).findFirst().get(); //returns the word from line n
                if(UsedWords.checkUsed(answer) == true){
                    return SelectEightLetterWord();
                }
                return answer;
            }
            catch(IOException e){
                System.out.println(e);
            }
            UsedWords.eightUsed();
        }
        else{ //if it is a demo
            int n = (int)Math.floor(Math.random() * (5)); 
            System.out.println(n);
            try (Stream<String> lines = Files.lines(Paths.get("Database-Related\\demoEightLetters.txt"))){
                answer = lines.skip(n).findFirst().get(); //returns the word from line n
                if(UsedWords.checkUsed(answer) == true){
                    return SelectEightLetterWord();
                }
            }
            catch(IOException e){
                System.out.println(e);
            }
            UsedWords.eightUsed();
        }
        return answer;
    }

    //selects a random twelve letter word
    public static String SelectTwelveLetterWord() throws IOException{
        String answer = "";
        if(UsedWords.getTwelveAvailable() == 0){
            Word.noWordsAvailable();
        }
        else if(demo == false){
            int n = (int)Math.floor(Math.random() * (71)); //selects random line from five letter word database
            try (Stream<String> lines = Files.lines(Paths.get("Database-Related\\twelveLetterWords.txt"))){
                answer = lines.skip(n).findFirst().get(); //returns the word from line n
                if(UsedWords.checkUsed(answer) == true){
                    return SelectTwelveLetterWord();
                }
                return answer;
            }
            catch(IOException e){
                System.out.println(e);
            }
        }
        else{
            int n = (int)Math.floor(Math.random() * (5)); 
            System.out.println(n);
            try (Stream<String> lines = Files.lines(Paths.get("Database-Related\\demoTwelveLetters.txt"))){
                answer = lines.skip(n).findFirst().get(); //returns the word from line n
                if(UsedWords.checkUsed(answer) == true){
                    return SelectTwelveLetterWord();
                }
            }
            catch(IOException e){
                System.out.println(e);
            }
            UsedWords.twelveUsed();
        }
        return answer;
    }

    /*
     * Adds 1 letter for difficulty 2
     * Adds 2 letters for difficulty 3
     */
    public static String addLetters(){
        int difficulty = Word.getDifficulty();
        String added = "";
        String addedAnswer = Word.getAnswer();
        int numAdded = 0;
        Random r;
        char c;
        numAdded = difficulty - 1;
        //gets random letters to be added
        for(int i = 1; i <= numAdded; i++){
            r = new Random();
            c = (char)(r.nextInt(26) + 'a');
            if(c == 'r' || c == 'd' || c == 'y' || c == 's'){
                addLetters(); //if one of the added letters is r, d, y, or s, will restart
            }
            added += c;
        }
        addedAnswer += added.toLowerCase();
        return addedAnswer; //returns the answer with 1-2 extra letters at the end
    }

    //if guess is wrong, checks which letters are in the right spot
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

}
