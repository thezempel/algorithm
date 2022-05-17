/*
    Term constructor that contains a query string and assigns it an integer weight.
    supports comparing terms in three different orders: the natural order (lexicographic), descending by weight, and by
    the first r terms in lexicographic order (a specified prefix).
    @author: Jonathon Zempel
    @version: 02-17-2022
 */


import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Term implements Comparable<Term> {
    private String query;
    private long weight;
    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null)
            throw new NullPointerException("need to put something in the query");
        this.query = query;
        if (weight < 0)
            throw new IllegalArgumentException("weight must be greater ");
        this.weight = weight;
    }
    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }
    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term t1, Term t2) {
            if (t1.weight < t2.weight)
                return 1;
            else if (t1.weight > t2.weight)
                return -1;
            else return 0;
        }
    }
    // Compares the two terms in lexicographic order
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
            throw new IllegalArgumentException("the weight must be at least 0");
        }
        return new PrefixOrder(r);
    }
    private static class PrefixOrder implements Comparator<Term> {
        private int r;
        public PrefixOrder(int r) {
            this.r = r;
        }
        public int compare(Term t1, Term t2) {
            int max;
            if (t1.query.length() < t2.query.length())
                max = t1.query.length();
            else
                max = t2.query.length();
            if (max > r)
                max = r;
            for (int i = 0; i < max; i++) {
                if (t1.query.charAt(i) > t2.query.charAt(i))
                    return 1;
                else if (t1.query.charAt(i) < t2.query.charAt(i))
                    return -1;

            }
            if ((r > t1.query.length()) && (t2.query.length() > t1.query.length()))
                return -1;
            else if ((r > t2.query.length()) && (t1.query.length() > t2.query.length()))
                return 1;
            return 0;
        }
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }
    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return weight + "\t" + query;
    }
    //unit testing
    public static void main(String[] args) {
        Term[] terms = {new Term("Abra", 3), new Term("Ka", 8), new Term("Dabra", 1), new Term("Alakazam", 4)};
        for (Term term : terms)
            StdOut.println(term);
        StdOut.println();

        Arrays.sort(terms, Term.byReverseWeightOrder());
        for (Term term : terms)
            StdOut.println(term);
        StdOut.println();

        Arrays.sort(terms, Term.byPrefixOrder(2));
        for (Term term : terms)
            StdOut.println(term);
        StdOut.println();

        Arrays.sort(terms);
        for (Term term : terms)
            StdOut.println(term);
    }
}
