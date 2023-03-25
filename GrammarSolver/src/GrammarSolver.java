import java.util.*;

/**

 This program was written by Pavel Stepanov for the CS 145 Programming Assignment: Grammar Solver.
 Version: 1.2
 Date: February 19, 2023
 */
public class GrammarSolver {
    // Map that will contain <String, String[]>
    private Map<String, String[]> map = new TreeMap<String, String[]>();

    // Random numbers.
    private Random random = new Random();

    /**

     Constructs a GrammarSolver object with the provided list of rules. The rules must be
     of the form "nonTerminal ::= x | x_2 | x_3 | ... and so on.
     The constructor creates a map with the nonTerminal as the key and the array of productions as
     the value.
     @param rules a list of strings representing the rules for the grammar
     @throws IllegalArgumentException if the list is null or empty, or if the same non-terminal
     appears multiple times within a single rule
     */
    public GrammarSolver(List<String> rules) {
        // Check if the input List of rules is null or has zero elements
        // If so, throw an IllegalArgumentException with an appropriate error message.
        if (rules == null || rules.size() == 0) {
            throw new IllegalArgumentException("List null or empty.");
        }

        // For loop hat will check all rule in rules.
        for (String rule : rules) {
            // String array that will split rule into two parts using "::=" as the delimiter.
            String[] parts = rule.split("::=");
            // First element - non-Terminal.
            String nonTerminal = parts[0].trim();
            // The second part is a list of productions separated by "|"
            String[] productions = parts[1].split("[|]");

            /*
              For example, if the original string is "A -> B | C | D", the split()
              method will return an array of three sub-strings: ["A -> B", "C", "D"].
             */

            // throw an IllegalArgumentException if the grammar contains more than one line for the same non-terminal.
            if (map.containsKey(nonTerminal)) {
                throw new IllegalArgumentException("The grammar contains more than one line for the same non-terminal.");
            }

            String[] terminals = new String[productions.length];
            // i variable for count.
            int i = 0;
            // for loop to add all terminals.
            for (String production : productions) {
                terminals[i] = production.trim();
                // Increasing the i.
                i++;
            }
            // Adding the non-terminal symbol and its corresponding array of terminals to the map.
            map.put(nonTerminal, terminals);

        }
    }

    /**

     Checks whether the grammar contains a non-terminal symbol.
     @param symbol the non-terminal symbol to check
     @return true if the grammar contains the symbol, false otherwise map.containsKey(symbol)
     @throws IllegalArgumentException if the symbol is null or has a length of 0
     */
    public boolean contains(String symbol) {
        // throw an IllegalArgumentException if the string is null or has a length of 0.
        if (symbol == null || symbol.length() == 0) {
            throw new IllegalArgumentException("Symbol cannot be null or have a length of 0.");
        }
        // Should return true if the given symbol is a non-terminal in the grammar and false otherwise
        return map.containsKey(symbol);
    }


    /**

     Returns a set of all the symbols in the grammar.
     @return a Set of Strings Set<String> representing all the symbols in the grammar.
     */
    public Set<String> getSymbols() {
        //Should return all non-terminal symbols of grammar as a sorted set of strings.
        return map.keySet();
    }

    /**

     Generates a random string using the input symbol and the corresponding rules found in the class's map field. If the input symbol
     is not found in the map, it is simply returned as is.
     @param symbol the input symbol to generate the random string from
     @return the randomly generated string based on the input symbol and the rules found in the class's map field
     @throws IllegalArgumentException if the input symbol is null or has a length of 0
     */
    public String generate(String symbol) {
        //throw an IllegalArgumentException if the string is null or has a length of 0.
        if (symbol == null || symbol.length() == 0) {
            throw new IllegalArgumentException("Symbol cannot be null or have a length of 0.");
        }

        // Check if the symbol is not present in the map, and return.
        if (!map.containsKey(symbol)) {
            return symbol;
        }

        // Get the array of rules associated with the input symbol
        String[] rules = map.get(symbol);

        // Generate a random integer within the range of possible rule indices
        int index = random.nextInt(rules.length);

        // Choose a random rule based on the index generated above
        String rule = rules[index];

        //rule.split("\\s+") will split rule into an array of substrings wherever one or more whitespace characters occur.
        String[] symbols = rule.split("\\s+");
        String result = "";

        // The base case for recursion in this code is when the input symbol does not have any corresponding rules in the given map.
        // In this case, the method returns the original symbol without making any further recursive calls.
        for (String s : symbols) {
            result += " " + generate(s);
        }
        // Delete extra spaces using additional .trim() method and return the result.
        return result.trim();
    }



}
