public class Hint {
    //keep track of how many hints used and how many hints available
    private static int used;
    private static int available;
    private static String output;
    private static String wordle;
    private static String answer;
    private static boolean hasFirstHint;
    
    //method that prints hint
    //needs to be able to give a hint that hasn't been revealed already
    public static void hint(){
        wordle = Word.getOutput();
        answer = Word.getAnswer();
        output = "";
        hasFirstHint = false;
        used = 0;
    }

    public static boolean getHasFirstHint(){
        return hasFirstHint;
    }

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
            Points.usedHint();
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
            Points.usedHint();
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
            Points.usedHint();
            if(available==0){
                Points.allHints();
            }
        }
        else if(available==0){
            System.out.println("No hints available.");
        }
    }

}
