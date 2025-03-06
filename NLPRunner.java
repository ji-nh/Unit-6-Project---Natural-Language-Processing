import java.util.Scanner;

/**
 * NLPRunner class
 * This class runs a sentiment analysis program that analyzes user input sentences.
 * It uses the SentimentAnalyzer class to perform the analysis and provide interpretations.
 */
public class NLPRunner {
    public static void main(String[] args) {
        // Creates a Scanner object to read user input
        Scanner userInput = new Scanner(System.in);
        
        // Creates a SentimentAnalyzer object to analyze sentences
        SentimentAnalyzer analyzer = new SentimentAnalyzer();

        // Welcome message
        System.out.println("Welcome to the Sentiment Analyzer!");
        
        // Main loop for analyzing sentences
        while (true) {
            // Prompt user for input
            System.out.print("Enter a sentence to analyze (or 'quit' to exit): ");
            String sentence = userInput.nextLine();
            
            // Check if the user wants to quit
            if (sentence.equalsIgnoreCase("quit")) {
                break;  // Exit the loop if user types 'quit'
            }
            
            // Analyze the sentiment of the input sentence
            double sentimentScore = analyzer.findSentiment(sentence);
            
            // Round the sentiment score to two decimal places
            double roundedScore = Math.round(sentimentScore * 100.0) / 100.0;
            
            // Display the results
            System.out.println("Sentiment score: " + roundedScore);
            System.out.println("Interpretation: " + analyzer.explainSentiment(sentimentScore));
            System.out.println();  // Adds a blank line for readability
        }
        
        // Close the Scanner
        userInput.close();
        
        // bye bye message
        System.out.println("Thank you for using the Sentiment Analyzer!");
    }
}
