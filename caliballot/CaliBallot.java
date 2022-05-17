/**
 *  Creates an array of Candidate objects and sorts the  list of candidates names using the ordering of:
 *          R W Q O J M V A H B S G Z X N T C I E K U P D Y F L
 *   can be ordered by first name, last name, or the default age.  any tiebreakers are then ordered by age.
 *   utilizes helper methods: usageMessage - prints a usage message
 *                              verify - verifies correct input
 *                              main - main client method
 *
 * @author Jonathon Zempel
 * @version 01-29-2022
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdOut;

public class CaliBallot {


    public static void usageMessage(){
        StdOut.println("the order the commands must be in are : sortType sortOrder inputFile\n" +
                "sortOrder must be one of the following:  Selection, Insertion\n" +
                "sortType must be one of the following:  Default, FirstName, LastName\n" +
                "inputFile is the name of the file from where to get the candidate data");
    }

    /**
     * helper method to determine which sortType and sortOrder
     *
     * @param candidates array of Candidate objects
     * @param whatSort which type of sort to be used; Insertion or Selection
     * @param sortOrder which order to sort by; last_name, first_name, or default(age);
     */
    public static void candidateSort(Candidate[] candidates, String whatSort, String sortOrder) {
        if (sortOrder == "LastName") {
            if (whatSort == "Insertion")
                Insertion.sort(candidates, new SortLast());
            if (whatSort == "Selection")
                Selection.sort(candidates, new SortLast());
        StdOut.println("sorted by Last Name");
        }
        if (sortOrder == "FirstName") {
            if (whatSort == "Insertion")
                Insertion.sort(candidates, new SortFirst());
            if (whatSort == "Selection")
                Selection.sort(candidates, new SortFirst());
        StdOut.println("sorted by First Name");
        }

        if (sortOrder == "Default") {
            if (whatSort == "Insertion")
                Insertion.sort(candidates);
            if (whatSort == "Selection")
                Selection.sort(candidates);

        StdOut.println("sorted by Age");
        }
    }

    /**
     * verifies the formatting of sortType and sortOrder
     *
     * @param sortType Insertion or Selection
     * @param sortOrder last_name, first_name, default
     * @return true if input format is correct, false if not following expected format
     */
    private static boolean verify(String sortType, String sortOrder){
        return (sortType == "Insertion" || sortType == "Selection") &&
                (sortOrder == "FirstName" || sortOrder == "LastName" || sortOrder == "Default");

    }

    /**
     * main method.  takes command line input; sort_order (first_name or last_name), sort_type (Insertion or selection),
     * and a file to sort. interprets the file data and stores the appropriate data to an array of Candidate objects
     * then sorts them according to a specific order.
     * Prints the candidates in the new order and how long it took to sort.
     * @param args command line arguments, must have 3 arguments
     */
    public static void main(String[] args) {
        String input;
        String sortType;
        String sortOrder;
        //make sure correct number of arguments
        if (args.length == 3){
            sortType = args[0];
            sortOrder = args[1];
             if (verify(sortType, sortOrder)) {
                 In in = new In(args[2]);
                 //verifies and reads sortType and sortOrder
                 //starts reading input file
                 String n = in.readLine();
                 //creates candidate array of n size
                 int size = Integer.parseInt(n);
                 Candidate[] candidates = new Candidate[size];

                 //creates an array of candidates using Candidate constructor
                 while (!in.isEmpty()) {
                     //cleans up input file
                     for (int i = 0; i < size; i++) {
                         input = in.readLine();
                         input = input.replaceAll(" ", "");//deletes extra whitespace in each line
                         String[] data = input.split(",");//splits each line by comma
                         //create new candidate object (firstName,lastName,  age, city)
                         candidates[i] = new Candidate(data[0], data[1], data[2], data[3]);
                     }
                 }

                 Stopwatch timer = new Stopwatch(); //start stopwatch
                 candidateSort(candidates, sortType, sortOrder);//calls to sort the candidates

                 double timeToSort = timer.elapsedTime();//end stopwatch
                 //prints the sorted candidates
                 for (int i = 0; i < size; i++)
                     StdOut.println(i + 1 + "- " + candidates[i].toString());

                 StdOut.println(timer);
             } else usageMessage();
            } else //handles improper number of args and improper sortType and sortOrder format
                usageMessage();

    }
}
