import java.util.*;
import java.io.*;


/**
 * The QuestionTree class represents a decision tree for a 20 Questions game.
 */
public class QuestionTree {
  private UserInterface my; // The user interface for interaction with the user
  private QuestionNode overallRoot; // The root node of the tree
  private int totalGames; // The total number of games played

  private int gamesWon; // The number of games won by the program

  /**

   Constructor for the QuestionTree class that initializes the user interface
   and default settings for the game.

   @param ui the user interface for the game

   @throws IllegalArgumentException if the user interface is null
   */
  public QuestionTree(UserInterface ui) {
    if (ui == null) {
      throw new IllegalArgumentException("User interface cannot be null.");
    }

    // Default Settings:
    this.my = ui; // Use the given UI object for user input/output
    this.overallRoot = new QuestionNode("Jedi"); // Create a new QuestionNode with "Jedi" as the root data
    this.totalGames = 0; // Set the totalGames counter to 0
    this.gamesWon = 0; // Set the gamesWon counter to 0
  }

  /**

   1. Starts the game by calling the private method play with the root of the tree.
   2. Increases the total games count by 1 after the game has ended.
   */
  public void play() {
    // Start the game from the overall root of the decision tree
    overallRoot = play(overallRoot);

    // Update the totalGames counter
    totalGames++;
  }

  /**

   Plays one round of the 20 Questions game, starting from a given node in the decision tree.

   @param node The starting node for the round.

   @return The node that represents the object that the computer guessed or learned about during the round. :)
   */
  private QuestionNode play(QuestionNode node) {
    if (node.leafNode()) {
      my.print("Would your object happen to be " + node.data + "? ");
      // If the user says yes, the computer wins the game.
      if (my.nextBoolean()) {
        // Print "I win!"
        my.println("I win!");
        // Add point to computer
        gamesWon++;
        // And return node
        return node;
      }
      // If the user says no, the computer learns a new object and adds it to the decision tree.
      else {
        // Ask the user what their object is
        my.println("I lose. What is your object?");
        // Ask the user for a question that distinguishes their object from the guess
        String object = my.nextLine();
        // Ask the user for the answer to the distinguishing question for their object
        my.print("Type a yes/no question to distinguish your item from " + node.data + ": ");
        String question = my.nextLine();

        my.print("And what is the answer for your object? ");
        // Check yes/no
        boolean answer = my.nextBoolean();
        // Depending on the answer, create a new QuestionNode with the user's object as a child
        if (answer) {
          // Return the new QuestionNode
          return new QuestionNode(question, new QuestionNode(object), node);
        }
        else {
          // Return the new QuestionNode
          return new QuestionNode(question, node, new QuestionNode(object));
        }
      }
    }
    // If the current node is not a leaf node, ask the user the question and recursively play
    else {
      // Ask the user the question associated with the current node
      my.print(node.data + " ");
      // If the user responds "yes", recursively play the left child node
      if (my.nextBoolean()) {
        node.left = play(node.left);
      }
      // If the user responds "no", recursively play the right child node
      else {
        node.right = play(node.right);
      }
      // Return the current node after the round is complete
      return node;
    }
  }


  /**

   Saves the current state of the game to a given output stream.
   @param output the output stream to save the game state to
   @throws IllegalArgumentException if the given output stream is null
   */
  public void save(PrintStream output) {
    // If the output PrintStream is null, throw an exception
    if (output == null) {
      throw new IllegalArgumentException("Save cannot be null.");
    }

    // Start the save method at the overall root of the decision tree
    save(output, overallRoot);
  }

  /**

   Saves a decision tree to a given PrintStream in a pre-order traversal, starting from the given node.

   @param output The PrintStream to save the decision tree to.

   @param node The starting node for the traversal.
   */
  private void save(PrintStream output, QuestionNode node) {
    // If the node is null, return without doing anything
    if (node == null) {
      return;
    }

    // If the node is a null node, print its data with a prefix of "A:"
    if (node.leafNode()) {
      output.println("A:" + node.data);
    }
    // If the node is not a null node, print its data with a prefix of "Q:"
    else {
      output.println("Q:" + node.data);
    }

    // Recursively save the LEFT and RIGHT children of the current node
    save(output, node.left);
    save(output, node.right);
  }

  /**

   This method loads a binary tree of questions and answers from the given Scanner input.
   @param input the scanner object to read data from
   @throws IllegalArgumentException if the scanner input is null
   */
  public void load(Scanner input) {
    // If the input Scanner object is null, throw an exception
    if (input == null) {
      throw new IllegalArgumentException("Scanner input cannot be null.");
    }
    // Start loading the tree from the overall root
    overallRoot = load(input, overallRoot);
  }

  /**

   Recursively loads a binary tree of questions and answers from a Scanner object.
   @param input the Scanner object containing the questions and answers to be loaded
   @param node the current node being loaded
   @return the root node of the loaded binary tree
   */
  private QuestionNode load(Scanner input, QuestionNode node) {
    // If there are no more lines in the input, return null
    if (!input.hasNextLine()) {
      return null;
    }
    // Read the next line and extract the Q: and A:
    String line = input.nextLine();
    String data = line.substring(2);
    // If the line represents an Answer node, create a new QuestionNode with the extracted data:
    if (line.startsWith("A:")) {
      node = new QuestionNode(data);
    }
    // If the line represents a Question node:
    else if (line.startsWith("Q:")) {
      // Create a new QuestionNode with the extracted data and recursively load its children
      node = new QuestionNode(data);
      node.left = load(input, node.left);
      node.right = load(input, node.right);
    }
    // Return this node
    return node;
  }

  /**

   Returns the total number of games that have been played on this tree so far.
   @return the total number of games played
   */
  public int totalGames() {
    // Return the total games
    return totalGames;
  }

  /**

   Returns the total number of games won by the computer during the current execution of the program.
   @return the total number of games won by the computer
   */
  public int gamesWon() {
    // Return the games that was won by the computer
    return gamesWon;
  }

}