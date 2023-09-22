import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;

public class SelectWord{
    
    public static String SelectFiveLetterWord() throws IOException{
        
        int n = (int)Math.floor(Math.random() * (5757 - 0 + 1)); //selects random line from five letter word database
        try (Stream<String> lines = Files.lines(Paths.get("sgb-words.txt"))){
            return lines.skip(n).findFirst().get(); //returns the word from line n
        }
        catch(IOException e){
            System.out.println(e);
        }

        return "pineapple"; //if failure, print pineapple

    }
}