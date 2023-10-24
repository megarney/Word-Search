/*
 * Determines and prints hints to the user
 */

public class Hint {
    private static int used; //keeps track of the number of hints used
    private static int available; //keeps track of the number of hints that can be used
    private static String output; //keeps track of what the hint with print
    private static String wordle; //keeps track of what letters the user already has in the right spot
    private static String answer; //keeps track of the answer
    private static boolean hasFirstHint; //tells whether the user has had their first hint
    
    //resets the hint when the user first asks for a hint
    public static void hint(){
        wordle = SelectWord.getOutput();
        answer = Word.getAnswer();
        output = "";
        hasFirstHint = false;
        used = 0;
    }

    //returns whether or not user has had their first hint
    public static boolean getHasFirstHint(){
        return hasFirstHint;
    }

    //gives hint
    public static void giveHint(){
        hasFirstHint = true;
        boolean hasHint = false;
        //first hint and entered invalid word length
        if(wordle.equals("")&&used==0){
            output += answer.charAt(0);
            available = answer.length();
            for(int i = 1; i < answer.length(); i++){
                output += "_";
            }
            used++;
            available--;
            System.out.println(output);
        }
        else if(!wordle.equals("")&&used==0){
            for(int i = 0; i < wordle.length(); i++){
                if(wordle.charAt(i)=='_' && hasHint == false){
                    output += answer.charAt(i);
                    hasHint = true;
                }
                else{
                    output += wordle.charAt(i);
                }
            }
            used++;
            available--;
            System.out.println(output);
        }
        else if(used>=1 && available!=0){
            String newOutput = "";
            for(int i = 0; i < output.length(); i++){
                if(output.charAt(i)=='_' && hasHint == false){
                    newOutput += answer.charAt(i);
                    hasHint = true;
                }
                else{
                    newOutput += wordle.charAt(i);
                }
            }
            used++;
            available--;
            output = newOutput;
            System.out.println(output);
        }
        else if(available==0){
            System.out.println("No hints available.");
        }
    }

}