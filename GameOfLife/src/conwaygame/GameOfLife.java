package conwaygame;
import java.util.ArrayList;
//import javax.sound.sampled.SourceDataLine;
//import javax.sql.RowSet;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
        ArrayList boardSpot = new ArrayList()
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {
        StdIn.setFile(file);
        int r = StdIn.readInt();
        int c = StdIn.readInt();
        
        grid = new boolean [r][c]; //fill array with alive
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                if(StdIn.readBoolean() == true){
                    grid[i][j] = ALIVE;
                    totalAliveCells++;
                }
            }
        } 
    }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {

        if (grid[row][col] == true){
            return true;
        }
        return false;
    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {
        grid= getGrid();

        int row = grid.length; 
        int col = grid[0].length; 
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (grid[i][j] == true){
                    return true;
                }
            }
        } 
        return false;
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {
        
        int aliveBuddy = 0; //counter
        int rowL = grid.length; //row length
        int colL = grid[0].length; //column length
        int i = row;
        int j = col;

        if (grid[((i-1) + rowL)%rowL][((j-1) + colL)%colL]==true){
            aliveBuddy++;
        }
        if (grid[((i-1) + rowL)%rowL][((j) + colL)%colL]==true){
            aliveBuddy++;
        }
        if (grid[((i-1) + rowL)%rowL][(j+1)%colL]==true){
            aliveBuddy++;
        }
        if (grid[((i) + rowL)%rowL][((j-1) + colL)%colL]==true){
            aliveBuddy++;
        }
        if (grid[((i) + rowL)%rowL][(j+1)%colL]==true){
            aliveBuddy++;
        }
        if (grid[(i+1)%rowL][((j-1) + colL)%colL]==true){
            aliveBuddy++;
        }
        if (grid[(i+1)%rowL][((j) + colL)%colL]==true){
            aliveBuddy++;
        }
        if (grid[(i+1)%rowL][(j+1)%colL]==true){
            aliveBuddy++;
        }

        return aliveBuddy;

    }
    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {
        //Create a new temporary grid
        //With that temporary grid, fill it in with values from the actual grid
        //set the temp grid to grid
        boolean[][] tempGrid = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                tempGrid[i][j] = grid[i][j];
            }
        }
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                int buddies = numOfAliveNeighbors(i,j);
                if (buddies <= 1){
                    tempGrid[i][j] = DEAD;
                }
                else if (buddies == 3){
                    tempGrid[i][j] = ALIVE;
                }
                else if (buddies >= 4){
                    tempGrid[i][j] = DEAD;
                }
            }
        }
        return tempGrid;
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid(). 
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {
        boolean grid[][]= getGrid();
        boolean[][] newGrid = computeNewGrid();

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                grid[i][j] = newGrid[i][j];
            }
        }
        
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {
        for (int i = 0; i < n; i++){
            nextGeneration();
        }
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() { 
        int row = grid.length;
        int col = grid[0].length;
        int counter = 0;
        grid = getGrid();

        WeightedQuickUnionUF lady = new WeightedQuickUnionUF(row, col);

        if (getTotalAliveCells() == 0){
            return 0;
        }

        else {
            for (int i = 0; i < row; i++){
                for (int j = 0; j < col; j++){
                    if (getCellState(i, j) == ALIVE){
                        if (grid[((i-1) + row)%row][((j-1) + col)%col]==true){
                            lady.union(i,j,((i-1) + row)%row,((j-1) + col)%col);
                        }
                        if (grid[((i-1) + row)%row][((j) + col)%col]==true){
                            lady.union(i,j,((i-1) + row)%row,((j) + col)%col);
                        }
                        if (grid[((i-1) + row)%row][(j+1)%col]==true){
                            lady.union(i,j,((i-1) + row)%row,(j+1)%col);
                        }
                        if (grid[((i) + row)%row][((j-1) + col)%col]==true){
                            lady.union(i,j,((i) + row)%row,((j-1) + col)%col);
                        }
                        if (grid[((i) + row)%row][(j+1)%col]==true){
                            lady.union(i,j,((i) + row)%row,(j+1)%col);
                        }
                        if (grid[(i+1)%row][((j-1) + col)%col]==true){
                            lady.union(i,j,(i+1)%row,((j-1) + col)%col);
                        }
                        if (grid[(i+1)%row][((j) + col)%col]==true){
                            lady.union(i,j,(i+1)%row,((j) + col)%col);
                        }
                        if (grid[(i+1)%row][(j+1)%col]==true){
                            lady.union(i,j,(i+1)%row,(j+1)%col);
                        }
                }
                }
            }

ArrayList <Integer> communities = new ArrayList<Integer>();
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (grid[i][j] == ALIVE){
                    int root = lady.find(i, j);
                    if (communities.contains(root) == false){
                        communities.add(root);
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
}
}

   