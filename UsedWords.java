/*
 * Keeps track of all of the words that have been displayed to the user in a hashmap
 * Hashmap : key = the word sorted, value = the word unsorted
 */

import java.util.Arrays;
import java.util.HashMap;

public class UsedWords {
    private static HashMap<String, String> usedWords = new HashMap<String, String>();

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




