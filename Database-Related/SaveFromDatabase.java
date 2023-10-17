import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class SaveFromDatabase {
    public static void main (String[] args) throws FileNotFoundException, IOException{
        try {
            File database = new File("Database-Related\\NewDatabase.csv");
            Scanner reader = new Scanner(database);
            FileWriter fileWriterFive = new FileWriter("Database-Related\\\\fiveLetterWords.txt");
            PrintWriter printWriterFive = new PrintWriter(fileWriterFive);
            FileWriter fileWriterSix = new FileWriter("Database-Related\\\\sixLetterWords.txt");
            PrintWriter printWriterSix = new PrintWriter(fileWriterSix);
            FileWriter fileWriterEight = new FileWriter("Database-Related\\eightLetterWords.txt");
            PrintWriter printWriterEight = new PrintWriter(fileWriterEight);
            FileWriter fileWriterNine = new FileWriter("Database-Related\\\\nineLetterWords.txt");
            PrintWriter printWriterNine = new PrintWriter(fileWriterNine);
            FileWriter fileWriterTwelve = new FileWriter("Database-Related\\\\twelveLetterWords.txt");
            PrintWriter printWriterTwelve = new PrintWriter(fileWriterTwelve);
            FileWriter fileWriterThirteen = new FileWriter("Database-Related\\\\thirteenLetterWords.txt");
            PrintWriter printWriterThirteen = new PrintWriter(fileWriterThirteen);
            while(reader.hasNextLine()){
                String word = reader.nextLine();
                if(word.length() == 5){
                    printWriterFive.println(word);
                }
                if(word.length() == 6){
                    printWriterSix.println(word);
                }
                if(word.length() == 8){
                    printWriterEight.println(word);
                }
                if(word.length() == 9){
                    printWriterNine.println(word);
                }
                if(word.length() == 12){
                    printWriterTwelve.println(word);
                }
                if(word.length() == 13){
                    printWriterThirteen.println(word);
                }
            }
            reader.close();
            printWriterFive.close();
            printWriterSix.close();
            printWriterEight.close();
            printWriterNine.close();
            printWriterTwelve.close();
            printWriterThirteen.close();
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
