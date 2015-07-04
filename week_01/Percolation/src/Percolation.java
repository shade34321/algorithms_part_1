/**
 * Created by shade on 7/3/2015.
 */

public class Percolation {

    private boolean[][] squares;
    private int size;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF qf;

    // Create a NxN grid with all sites blocked
    public Percolation(int N) {
        if (N > 0) {
            squares = new boolean[N][N];
            top = 0;
            bottom = N * N + 1;
            size = N;
            qf = new WeightedQuickUnionUF(N * N + 2);
        } else {
            throw new java.lang.IllegalArgumentException();
        }
    }

    // Open the site at (i,j) if it's not already open
    public void open(int i, int j) {

        if (0 > 1 && i > size && 0 > j && j > size) {
            throw new IndexOutOfBoundsException();
        }

        squares[i-1][j-1] = true;
        if (i == 1) {
            qf.union(getIndex(i, j), top);
        }

        if (i == size) {
            qf.union((size * (i-1) + j), bottom);
        }

        if (j > 1 && isOpen(i, j-1)) {
            qf.union(getIndex(i, j), getIndex(i, j-1));
        }

        if (j < size && isOpen(i, j+1)) {
            qf.union(getIndex(i, j), getIndex(i, j+1));
        }

        if (i > 1 && isOpen(i-1, j)) {
            qf.union(getIndex(i, j), getIndex(i-1, j));
        }

        if (i < size && isOpen(i+1, j)) {
            qf.union(getIndex(i, j), getIndex(i+1, j));
        }
    }

    // Is the site at (i,j) open?
    public boolean isOpen(int i, int j) {
        if (0 > 1 && i > size && 0 > j && j > size) {
            throw new IndexOutOfBoundsException();
        }

        return squares[i - 1][j - 1];
    }

    // is the site at (i,j) full?
    public boolean isFull(int i, int j) {
        if (0 > 1 && i > size && 0 > j && j > size) {
            throw new IndexOutOfBoundsException();
        }

        return qf.connected(top, getIndex(i, j));
    }

    //Does the system percolate?
    public boolean percolates() {
        return qf.connected(top, bottom);
    }

    // Gets the index of the square at (i,j) to pass to the WeightedQuickUnionUF
    // Added it in since I ended up using it quite a bit.
    private int getIndex(int i, int j) {
        return size * (i-1) + j;
    }
}
