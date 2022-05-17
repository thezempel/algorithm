/**
 *     Takes an edge weighted digraph and using Dijkstras shortest path algorithm, finds and prints the shortest
 *     path from a user defined starting point to every other point; along with the total distance.
 *
 * @author: Jonathon Zempel
 * @version: 03-01-2022
 */


import edu.princeton.cs.algs4.*;

public class ZempelP4 {

    public static void main(String[] args) {
        try {
            In in = new In(args[0]); //input file with vectors/edges/weights
            int start = Integer.parseInt(args[1]); //starting point

            EdgeWeightedDigraph graph = new EdgeWeightedDigraph(in); //from edu.princeton.cs.algs4.EdgeWeightedDigraph
            DijkstraSP path = new DijkstraSP(graph, start); //from edu.princeton.cs.algs4.DijkstraSP
            for (int i = 0; i < graph.V(); i++)  //print out each path from starting point
                if(path.hasPathTo(i)) StdOut.printf("%d -> %d \n the path is %s \n total distance: %.0f \n", start, i, path.pathTo(i), path.distTo(i));
             else StdOut.println("no suvh path exists\n");//if path == null
        }
        catch (Exception e) {
            StdOut.println("java -cp .:algs4.jar ZempelP4\n"+"Usage: java -cp .:algs4.jar ZempelP4 text.txt num_start num_end");
        }
    }
}


/*


StdOut.println(start + " -> " + i +
                        "\n the path is " + path.pathTo(i) + "\n  total distance: " + (int)path.distTo(i) + "\n");
                else StdOut.println("no such path exists\n")
 */