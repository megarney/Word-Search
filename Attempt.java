import java.io.IOException;
import java.util.Scanner;

public class Attempt {
    private static int attempts;
    private static Scanner scan = new Scanner(System.in);
    private static String response;

    public static void attempts(){
        attempts = Word.getAnswer().length();
    }

    public static void failedAttempt() throws IOException{
        attempts--;
        if(attempts == 0){
            fail();
        }
        else{
            System.out.println("Attempts remaining: " + attempts);
        }
    }

    public static void fail() throws IOException{
        System.out.println("No attempts remaining.\nEnter 1 to start a new game or enter 2 to spend 50 Megash for one more attempt.");
        response = scan.nextLine();
        if(response.equals("2")){
            if(Megash.spendCash(50)){
                attempts++;
                System.out.println("Attempts remaining: 1\n" + Word.getScrambled() + "\n" + Word.getLastDisplayed());
                Word.getResponse();
            }
            else{
                System.out.println("Insufficient funds.");
                System.out.println("===================");
            }
        }
        else{
            System.out.println("===================");
            Word.newGame();
        }
    }
}
