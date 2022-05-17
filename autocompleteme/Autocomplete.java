/*
    implemented data type that provides "autocomplete" functionality for a given set of string and weights
    uses Term and BinarySearchDeluxe
    @author Jonathon Zempel
    @version 02-17-2022
 */



import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class Autocomplete {
    private Term[] terms;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null)
            throw new IllegalArgumentException("there are no terms here; there must be some terms");

        this.terms = new Term[terms.length];
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == null)
                throw new NullPointerException("one of these terms is null; please fix and try again");
            this.terms[i] = terms[i];
            Arrays.sort(this.terms);
        }
    }

    // Returns all terms that start with the given prefix,
    // in descending order of weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null)
            throw new NullPointerException("please make sure there is a prefix here; it seems to be null");

        Term p = new Term(prefix, 0);
        int first = BinarySearchDeluxe.firstIndexOf(terms, p, Term.byPrefixOrder(prefix.length()));
        int last = BinarySearchDeluxe.lastIndexOf(terms, p, Term.byPrefixOrder(prefix.length()));

        Term[] matches = new Term[last - first + 1];
        for (int i = first; i <= last; i++) {
            matches[i - first] = terms[i];
        }
        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if(prefix == null)
            throw new NullPointerException("please make sure prefix is not null");

        Term p = new Term(prefix, 0);
        int first = BinarySearchDeluxe.firstIndexOf(terms, p, Term.byPrefixOrder(prefix.length()));
        int last = BinarySearchDeluxe.lastIndexOf(terms, p, Term.byPrefixOrder(prefix.length()));

        return last - first + 1;
    }

    public static void main(String[] args) {
        if (args.length != 2 || args[0] == null || args[1] == null)
            StdOut.println("Usage: first argument must be a text file that is to be searched, followed by an int value" +
                    "that is to be the number of matching terms\n"+
                    "command line format must be : file.txt int");

        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();          // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }
        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}
