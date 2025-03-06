import java.util.ArrayList;

/**
 * SentimentAnalyzer class
 * This class analyzes the sentiment of sentences based on positive and negative words.
 */
public class SentimentAnalyzer {
    private ArrayList<String> positiveWords;
    private ArrayList<String> negativeWords;
    
    /**
     * Constructor for SentimentAnalyzer
     * Initializes lists of positive and negative words from text files
     */
    public SentimentAnalyzer() {
        positiveWords = FileReader.toStringList("positive_words.txt");
        negativeWords = FileReader.toStringList("negative_words.txt");
    }
    
    /**
     * Analyzes the sentiment of a given sentence
     * @param sentence The input sentence to analyze
     * @return A score representing the sentiment (-1 to 1)
     */
    public double findSentiment(String sentence) {
        ArrayList<String> words = tokenize(sentence);
        int positiveCount = 0;
        int negativeCount = 0;
        
        // Counts positive and negative words
        for (String word : words) {
            if (isPositiveWord(word)) {
                positiveCount++;
            } else if (isNegativeWord(word)) {
                negativeCount++;
            }
        }
        
        // Calculates sentiment score
        if (words.size() == 0) {
            return 0;
        }
        // Converts to double by multiplying by 1.0
        return (positiveCount - negativeCount) * 1.0 / words.size();
    }
    
/**
 * Breaks a sentence into individual words (tokens).
 * This method converts the sentence to lowercase and separates words
 * based on non-letter characters.
 *
 * @param sentence The input sentence to be tokenized.
 * @return An ArrayList of words (tokens) from the sentence.
 */

    private ArrayList<String> tokenize(String sentence) {
      // Creates a new ArrayList to store the words (tokens)
        ArrayList<String> tokens = new ArrayList<>();
      // Converts the entire sentence to lowercase
        String lowerCaseSentence = sentence.toLowerCase();
       // Initializes an empty string to build each word
        String currentWord = "";
        
        // Processes each character in the sentence
        for (int i = 0; i < lowerCaseSentence.length(); i++) {
           // Gets the current character as a string
            String c = lowerCaseSentence.substring(i, i+1);
           // Check if the character is a lowercase letter
            if (c.compareTo("a") >= 0 && c.compareTo("z") <= 0) {
               // If it's a letter, it adds it to the current word
                currentWord += c;
            } else if (!currentWord.equals("")) {
               // If it's not a letter and we have a word built up:
               // Adds the completed word to our list of tokens
                tokens.add(currentWord);
               // Reset currentWord to start a new word
                currentWord = "";
            }
        // If it's not a letter and currentWord is empty, it skips it (punctuation or spaces)
        }
        
        // Checks if there's any word left after processing all characters
        if (!currentWord.equals("")) {
        // If there is, add it to the tokens
            tokens.add(currentWord);
        }
        // Returns the list of words (tokens)
        return tokens;
    }
    
    /**
     * Checks if a word is positive
     * @param word The word to check
     * @return true if the word is positive, false otherwise
     */
    private boolean isPositiveWord(String word) {
        return positiveWords.contains(word);
    }
    
    private boolean isNegativeWord(String word) {
        return negativeWords.contains(word);
    }
    
    /**
     * Interprets the sentiment score as a descriptive string
     * @param score The sentiment score
     * @return A string describing the sentiment
     */
    public String explainSentiment(double score) {
        if (score < -0.5) {
            return "Very Negative";
        } else if (score < -0.1) {
            return "Negative";
        } else if (score < 0.1) {
            return "Neutral";
        } else if (score < 0.5) {
            return "Positive";
        } else {
           return "Very Positive";
        }
    }
}
