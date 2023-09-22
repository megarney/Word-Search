import java.io.IOException;

public class Test {
    
    

    public static void main(String[] args) throws IOException{
        Word.SelectFiveLetterWord(); 
        System.out.println(Word.getAnswer());
        Word.printLetters();
        Word.getResponse();
    }

    

}
