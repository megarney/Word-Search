package databasetostring;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class databasetostring {
    public static void main (String[] args) throws FileNotFoundException, IOException{
        try {
            File database = new File("Database-Related\\twelveLetterWords.txt");
            Scanner reader = new Scanner(database);
            FileWriter fileWriter = new FileWriter("databasetostring\\twelveletterstring.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            while(reader.hasNextLine()){
                String word = reader.nextLine();
                printWriter.print(word + ",");
            }
            reader.close();
            printWriter.close();
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
