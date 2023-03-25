import java.util.*;

public class GrammarReference {


    private Map<String, List<String>> grammar;
    private Random random = new Random();
    public GrammarSolver(List<String> rules) {
        if (rules == null || rules.size() == 0) {
            throw new IllegalArgumentException("List cannot be null or empty.");
        }

        grammar = new HashMap<>();
        for (String rule : rules) {
            String[] parts = rule.split("::=");
            String nonTerminal = parts[0].trim();
            String[] productions = parts[1].split("[|]");

            if (grammar.containsKey(nonTerminal)) {
                throw new IllegalArgumentException("Grammar contains more than one line for the same non-terminal.");
            }

            List<String> productionList = new ArrayList<>();
            for (String production : productions) {
                productionList.add(production.trim());
            }
            grammar.put(nonTerminal, productionList);
        }
    }
    public boolean contains(String symbol) {
        if (symbol == null || symbol.length() == 0) {
            throw new IllegalArgumentException("Symbol cannot be null or have a length of 0.");
        }

        return grammar.containsKey(symbol);
    }


    public Set<String> getSymbols() {
        return new TreeSet<>(grammar.keySet());
    }


    public String generate(String symbol) {
        if (symbol == null || symbol.length() == 0) {
            throw new IllegalArgumentException("Symbol cannot be null or have a length of 0.");
        }

        if (!grammar.containsKey(symbol)) {
            return symbol;
        }

        List<String> rules = grammar.get(symbol);
        int index = random.nextInt(rules.size());
        String rule = rules.get(index);

        String[] symbols = rule.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String s : symbols) {
            result.append(generate(s));
        }

        return result.toString();
    }
}
