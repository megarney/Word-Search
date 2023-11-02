import java.io.IOException;

public class Help {
    public static void displayHelp() throws IOException{
        System.out.println("===================");
        System.out.println("Welcome to MegaProject, a (currently) text-based word game where you have to unscramble the given word. Lets break it down.\n");
        System.out.println("Levels: There are three levels. \nIn level 1, the answer will contain 5 letters; \nlevel 2, the answer will contain 8 letters; \nand level 3, the answer will contain 12 letters.\n");
        System.out.println("Difficulty: There are three levels of difficulty that determine if or how many extra unused letters will be given in the scrambled word. \nIn difficulty 1, the scrambled word will contain no extra letters; \ndifficulty 2, the scrambled word will contain 1 extra letter; \nand difficulty 3, the scrambled word will contain 2 extra letters.\n");
        System.out.println("Points: There are multiple things that affect points. Points start with a base of 100, but can go up (giving you the opportunity to earn more) \ndepending on level, difficulty, and streak. \nLevel 1 will add 0 points, \nlevel 2 will add 50, \nand level 3 will add 100. \nDifficulty 1 will add 0 points, \ndifficulty 2 will add 10, \nand difficulty 3 will add 20. \nStreak is based on the number of games you get correct in a row and will add 10 points for each game won in a row. \nFor example, if you were to select level 1 (+0) and difficulty 1 (+0); and have a streak of 2 (+20) you can receive up to 120 points. \nWhereas if you were to select level 2 (+50) and difficulty 2 (+10); and have a streak of 0 (+0) you can receive up to 160 points. \nYou will lose 10 points for a failed guess and 20 points for using a hint.\n");
        System.out.println("Megash: Megash is the cash system in MegaProject. \nYour Megash is dependent on the number of points you earn. \nAt the end of a round, you will receive 10% of the amount of points you earned in Megash.\n");
        System.out.println("Market: Megash can be used to buy hints or auto-wins in the Market. \nHints will unscramble one letter for you. The number of hints you can use in a game is dependent on the level. \nFor level 1, you can buy up to 2 hints; \nlevel 2, you can buy up to 3 hints; \nand level 3, you can buy up to 4 hints. \nAuto-wins will give you the answer.\n");
        System.out.println("Guessing: Guesses can be made by typing into the Terminal. \nIf your guess is correct, you will be shown the amount of points earned, Megash earned, and total Megash. \nYou will then be able to decide if you want to start another game or quit. \nIf your guess is wrong, you will be shown which letters were guessed in the right place. \nYou will also be shown the number of attempts remaining.\n");
        System.out.println("Attempts: The number of attempts you get to guess the answer is based on level. \nFor level 1, you get 5 guesses; \nlevel 2, you get 8 guesses; \nand level 3, you get 12 guesses. \nIf you run out of guesses, you will have the opportunity to spend $50 Megash to get one more attempt.");
        System.out.println("===================");
        System.out.println("Returned to game mode.");
        System.out.println(Word.getScrambled());
        if(Word.getLastDisplayed() != null && !Word.getLastDisplayed().isEmpty()){
            System.out.println(Word.getLastDisplayed());
        }
        Word.getResponse();
    }
}
