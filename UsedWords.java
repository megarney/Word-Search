/*
 * Keeps track of all of the words that have been displayed to the user in a hashmap
 * Hashmap : key = the word sorted, value = the word unsorted
 */

import java.util.Arrays;
import java.util.HashMap;

public class UsedWords {
    private static HashMap<String, String> usedWords = new HashMap<String, String>();
    private static int fiveAvailable = 686;
    private static int eightAvailable = 504;
    private static int twelveAvailable = 71;

    public static int getFiveAvailable(){
        return fiveAvailable;
    }

    public static void fiveUsed(){
        fiveAvailable--;
    }

    public static int getEightAvailable(){
        return eightAvailable;
    }

    public static void eightUsed(){
        eightAvailable--;
    }

    public static int getTwelveAvailable(){
        return twelveAvailable;
    }

    public static void twelveUsed(){
        twelveAvailable--;
    }

    public static void setDemo(){
        fiveAvailable = 5;
        eightAvailable = 5;
        twelveAvailable = 5;
    }

    //check if word has already been used
    public static boolean checkUsed(String answer){
        char[] chars = answer.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        //if the sorted solution does not return a value, adds the answer to usedWords
        if(usedWords.get(sorted)==null){
            usedWords.put(sorted, answer);
            return false;
        }
        return true;
    }

}




