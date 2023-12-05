import java.io.IOException;

public class Test {
   
    public static void main(String[] args) throws IOException{
        //Megash.setTotalCash(50);
        System.out.println("Enter 1 to go to the Market, 2 to quit, 3 for help, 4 to start a new round, or type guess below.");
        Word.getResponse(); //initiates the newGame() method that starts everything in Word
    }

}
