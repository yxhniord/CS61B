package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF connectedGrids;
    private WeightedQuickUnionUF connectedGridsExcludeBottom; // to avoid backwash
    private int size;
    private boolean[][] grid;
    private boolean percolated;
    private int numberOfOpenSites;
    private int virtualTop;
    private int virtualBottom;
    private int[][] surroundings = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // Return 1D-array index corresponding to the given grid
    private int xyTo1D(int row, int col) {
        return size * row + col + 1;
    }


    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("size N should be larger than 0");
        }
        size = N;
        grid = new boolean[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                grid[x][y] = false;
            }
        }
        connectedGrids = new WeightedQuickUnionUF(N * N + 2);
        connectedGridsExcludeBottom = new WeightedQuickUnionUF(N * N + 1);
        percolated = false;
        numberOfOpenSites = 0;
        virtualTop = 0;
        virtualBottom = N * N + 1;
    }

    // validate the validity of (row, col)
    private void validate(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numberOfOpenSites++;
        }
        if (row == 0) {
            connectedGrids.union(xyTo1D(row, col), virtualTop);
            connectedGridsExcludeBottom.union(xyTo1D(row, col), virtualTop);
        }
        if (row == size - 1) {
            connectedGrids.union(xyTo1D(row, col), virtualBottom);
        }
        for (int[] surrounding : surroundings) {
            int adjacentRow = row + surrounding[0];
            int adjacentCol = col + surrounding[1];
            if (adjacentRow >= 0 && adjacentRow < size) {
                if (adjacentCol >= 0 && adjacentCol < size) {
                    if (isOpen(adjacentRow, adjacentCol)) {
                        connectedGrids.union(xyTo1D(row, col), xyTo1D(adjacentRow, adjacentCol));
                        connectedGridsExcludeBottom.union(xyTo1D(row, col), xyTo1D(adjacentRow, adjacentCol));
                    }
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return connectedGridsExcludeBottom.connected(virtualTop, xyTo1D(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return connectedGrids.connected(virtualTop, virtualBottom);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {

    }
}
