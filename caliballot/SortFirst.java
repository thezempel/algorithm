/**
allows for comparison of candidates by first initial of first name
 *  compare - compares two candidates first initial of first name within a custom order
 *  toString - for finding out what this class does
 * @author Jonathon Zempel
 * @version 01-29-2022
 */


import java.util.Comparator;
import static java.lang.Character.toUpperCase;

public class SortFirst implements Comparator<Candidate> {
    String order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";

    /**
     * overrides compare to utilize custom order
     * @param o1 candidate object to be compared to
     * @param o2 candidate object to be compared
     * @return specified order in comparison to 'order'
     */
    @Override
    public int compare(Candidate o1, Candidate o2) {
        //makes first letter of first name uppercase
        char first = toUpperCase(o1.firstName.charAt(0));
        char second = toUpperCase(o2.firstName.charAt(0));

        //compares the letters with the custom ordering
        if (order.indexOf(first) > order.indexOf(second))
            return 1;
        else if (order.indexOf(first) < order.indexOf(second))
            return -1;
        else
            return o1.compareTo(o2); //if the letters are equal, compares the ages



    }
    /**
     * overrides toString to include what was sorted and the order it was sorted
     * @return indicating it was first names and the order
     */
    @Override
    public String toString() {
        return "first names sorted in this order: " + order;
    }

}
