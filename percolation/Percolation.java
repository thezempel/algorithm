/* Jonathon Zempel
    CS318 Winter 2022
    Percolation.java
    Description:
        implements Percolation API, importing WeightedQuickUnionUF, and utilizes two private methods to
        1) verify coordinates and
        2) map the 2D array to a 1D array for the WeightedQuickUnionUF to utilize.

 */



import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int[][] grid;
    private int size;
    private int top = 0;
    private int bottom;
    private int count;
    private WeightedQuickUnionUF union;


    public Percolation(int n)  {  // create n-by-n grid, with all sites blocked
        if (n <= 0)
            throw new IllegalArgumentException("too small");
        size = n;
        grid = new int[n][n];
        bottom = n * n + 1;
        union = new WeightedQuickUnionUF(n * n + 2);
        count = 0;
    }
    //maps 2D to 1D for WeightedQuickUnionUF
    private int xyTo1D(int row, int col) {
        return (row-1) * size + col;
    }
    //coordinate validation
    private boolean validate(int row, int col) {
        return (row >= 1 || row < size || col >= 1 || col < size);
    }

    public void open(int row, int col) { //open site (row, col) if not open already
        //validate
        if (!validate(row, col))
            throw new IndexOutOfBoundsException("out of bounds");

        //open
        grid[row - 1][col - 1] = 1;
        //find unions
        if (row == 1)  // union with top
            union.union(xyTo1D(row, col), top);
        if (row == size)  // union with bottom
            union.union(xyTo1D(row, col), bottom);

        // union up
        if (row > 1 && isOpen(row - 1, col))
            union.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        //union down
        if (row < size && isOpen(row + 1, col))
            union.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        //union left
        if (col > 1 && isOpen(row, col - 1))
            union.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        //union right
        if (col < size && isOpen(row, col + 1))
            union.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        //increase count
        count++;

    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        //validate
        if (!validate(row, col))
            throw new IndexOutOfBoundsException("bounds error");
        return grid[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        //validate
        if (!validate(row, col))
            throw new IndexOutOfBoundsException("not in bounds");
        return union.connected(xyTo1D(row, col), top);
    }

    public int numberOfOpenSites() { // number of open sites
        return count;
    }

    public boolean percolates() { // does the system percolate?
        return union.connected(top, bottom);
    }
    public static void main(String[] args){}  // test client (optional)
}