import java.util.*;

public class HangmanManager {
    /**
     * The length of the word to guess.
     */
    private final int wordLength;

    /**
     * The maximum number of allowed tries to guess the word.
     */
    private int maxAllowedAnswer;

    /**
     * The number of tries left to guess the word.
     */
    private int leftGuessesCount;

    /**
     * A set of words in the dictionary that match the specified length.
     */
    private TreeSet<String> wordSet;

    /**
     * A set of characters that have been guessed.
     */
    private TreeSet<Character> playerGuessLetters;

    /**
     * The pattern of the word to guess, consisting of correct letters and dashes for unguessed letters.
     */
    private String patternWord;


    /**
     * Creates a new HangmanManager object with a list of words from the dictionary, word length and the number of allowed incorrect guesses.
     *
     * @param dictionary a list of words from the dictionary
     * @param length the length of the word to be guessed
     * @param max the number of allowed incorrect guesses
     *
     * @throws IllegalArgumentException if the word length is less than 1 or the number of allowed incorrect guesses is less than 0
     */
    public HangmanManager(List<String> dictionary, int length, int max) {
        // Initialize the wordSet as a new TreeSet of Strings.
        wordSet = new TreeSet<String>();
        // Initialize the playerGuessLetters as a new TreeSet of Character.
        playerGuessLetters = new TreeSet<Character>();
        // initializing the maxAllowedAnswer to the provided max value
        maxAllowedAnswer = max;
        // initializing the lengthWord to the provided length value
        wordLength = length;

        // For-loop that will take dictionary and i = word.
        for (String i : dictionary) {
            // Adds the word to the set if its length matches the specified length.
            if (i.length() == length) {
                wordSet.add(i);
            }
        }
        //Check if length and max are valid.
        if (length < 1 || max < 0) {
            throw new IllegalArgumentException("Length is less than 1 or max is less than 0");
        }
    }


    /**
     * Returns a set of all words in the dictionary.
     * @return a set of all words in the dictionary
     * The client calls this method to get  access to the current set of words being considered by the HangmanManager
     */

    public Set<String> words() {
        //Return the word set from the dictionary.
        return wordSet;
    }


    /**
     * Returns the number of incorrect guesses left.
     *
     * @return the number of incorrect guesses left
     */

    public int guessesLeft() {
        //Will return max allowed answer that player has.
        return maxAllowedAnswer;
    }


    /**
     * Returns a set of characters that have been guessed so far.
     *
     * @return a set of characters that have been guessed so far
     */
    public TreeSet<Character> guesses() {

        // Return the set of guessed letters by the player.
        return this.playerGuessLetters;
    }


    /**
     * Returns a string pattern representing the current state of the word to be guessed.
     *
     * @return a string pattern representing the current state of the word to be guessed.
     *
     * @throws IllegalArgumentException if the word length is less than 1 or the number of allowed incorrect guesses is less than 0
     */
    public String pattern() {
        //Creating new patter.
        String wordPattern = "";
        // Check if wordSet is null and throw an exception if it is.
        if (wordSet == null) {
            throw new IllegalArgumentException("Length is less than 1 or max is less than 0\n");
        }
        // Initializing a sample word from the wordSet to
        // use as a reference to determine the length of the word.
        String sampleWord = wordSet.iterator().next();

        for (int i = 0; i < wordLength; i++) {
            // Add the character from the sample word if it is already guessed by the player.
            if (playerGuessLetters.contains(sampleWord.charAt(i))) {
                wordPattern = wordPattern + sampleWord.charAt(i) + " ";
            }
            //It will generate next dashes.
            else {
                wordPattern = wordPattern + "- ";
            }
        }
        //Display the patter that created in the for loop.
        return wordPattern;
    }



    /**

     The record method keeps track of the player's guess and updates the set of words in play based on that guess.
     It returns the number of occurrences of the guessed character in the words in the current set.
     @param guess the character that the player has guessed
     @return the number of occurrences of the guessed character in the words in the current set
     @throws IllegalStateException if maxAllowedAnswer is less than 1 or the wordSet is empty
     */
    public int record(char guess) {
        // repeatedSequences maps a pattern of the word (generated by generatePattern) to the number of times the guess character occurs in the word.
        // The data is stored in a TreeMap to maintain the sorted order of the patterns.
        Map<String, Integer> repeatedSequences = new TreeMap<>();
        //This line creates a new TreeMap with key of type String and value of type TreeSet<String>.
        // The map is used to store the pattern of the word generated by generatePattern method as the key and the set of words with the same pattern as the value.
        Map<String, TreeSet<String>> patternsWords = new TreeMap<>();
        int cases = 0;
        //Throws an IllegalStateException if either the maximum allowed number of answers is less than 1 or the set of words is empty.
        if (maxAllowedAnswer < 1 || wordSet.isEmpty()) {
            throw new IllegalStateException("");
        }
        // Add the current guess character to the set of player's guess letters.
        playerGuessLetters.add(guess);
        for (String word : wordSet) {
            //generates the pattern for the given word based on the letter guessed by the player.
            String pattern = generatePattern(word, guess);
            // Counts number of times guess appears in word
            int wordCases = countMatches(word, guess);
            if (wordCases > cases) {
                cases = wordCases;
            }

            //Adding word to existing pattern set in patternsWords map.
            if (patternsWords.containsKey(pattern)) {
                patternsWords.get(pattern).add(word);
            }
            // Create a new pattern and add the word to it if the pattern does not already exist in the map.
            else {
                TreeSet<String> newPatternWords = new TreeSet<>();
                newPatternWords.add(word);
                patternsWords.put(pattern, newPatternWords);
                repeatedSequences.put(pattern, wordCases);
            }
        }
        // Decrement maxAllowedAnswer if no match is found in the word set for the given guess.
        if (cases == 0) {
            maxAllowedAnswer--;
        }
        //The function sets the word set to the largest word family
        // by calling the getLargestWordFamily function and returns the number of matches cases.
        wordSet = getLargestWordFamily(patternsWords);

        // Return the number of word cases (number of correct letter matches) in the guess.
        return cases;
    }

    /**

     This method counts the number of occurrences of a given character in a given word.
     @param word The word in which the occurrence of the character is to be counted.
     @param guess The character for which the occurrence is to be counted in the word.
     @return The number of occurrences of the character in the word.
     */
    private int countMatches(String word, char guess) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            //// If the character at the current position in the word matches the guessed character, increase the count by 1.
            if (word.charAt(i) == guess) {
                count++;
            }
        }
        return count;
    }



    /**

     The getLargestKey method accepts a map, patternsWords, where the key is a string pattern and the value is a set of strings representing words with the same pattern.

     The method returns the largest set of words from patternsWords based on the size of the set.

     If two or more sets have the same size, the method returns the set of words that was processed last.

     @param patternsWords A map where the key is a string pattern and the value is a set of strings representing words with the same pattern

     @return A TreeSet of strings representing the largest set of words from patternsWords
     */
    public TreeSet<String> getLargestWordFamily(Map<String, TreeSet<String>> patternsWords) {
        // Declare variables to store TreeSets of strings: 'words' and 'largestWordFamily'
        TreeSet<String> words;
        // The latter is initialized as an empty TreeSet.
        TreeSet<String> largestWordFamily = new TreeSet<>();
        int largestFamilySize = 0;

        for(String i : patternsWords.keySet()) {
            //Retrieve the set of words associated with the pattern i from the map patternsWords.
            words = patternsWords.get(i);
            // Update largest word family if the current one is larger than the previous one.
            if (words.size() > largestFamilySize) {
                largestFamilySize = words.size();
                largestWordFamily = words;
            }
            else if (words.size() == largestFamilySize) {
                largestWordFamily = words;

            }
        }

        return largestWordFamily;
    }





    /**
     * Generates a pattern string based on the given word and a guessed character.
     * The pattern will consist of the correct letters and dashes for unguessed letters.
     *
     * @param word the word to generate the pattern for
     * @param guess the guessed character
     * @return the pattern string
     */
    private String generatePattern(String word, char guess)  {
        // Declaring a StringBuilder object "s" for mutable string operations.
        StringBuilder s = new StringBuilder();
        for(int i = 0; i< word.length(); i++) {
            //Replace matching characters with the guessed character in the pattern.
            if (word.charAt(i) == guess) {
                s.append(guess);
            }
            // Append the character to s if it's in playerGuessLetters
            else if(playerGuessLetters.contains(word.charAt(i))) {
                s.append(word.charAt(i));
            }
            // Generates the pattern by replacing characters of the word with '-'
            // or the guess letter if it's a match or already guessed by player.
            else {
                s.append("-");
            }
        }
        return s.toString();

    }







}
