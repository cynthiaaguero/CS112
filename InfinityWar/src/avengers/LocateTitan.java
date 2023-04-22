package avengers;
/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {

    public static int getMinCost(boolean[] dilk, int[] minny){
        int smallNumba = 0; 
        int othaNumba = Integer.MAX_VALUE;

        for (int i = 0; i < minny.length; i++){
            if (minny[i] < othaNumba && dilk[i] == false){
                othaNumba = minny[i];
                smallNumba = i;
            }
        }
        return smallNumba;
    }

	
    public static void main (String [] args) {
    	        
        StdIn.setFile(args[0]); //set input
        StdOut.setFile(args[1]); //set output

        int n = StdIn.readInt(); //set first number read as matrix size
        int[][] matrix = new int[n][n]; //set matrix as nxn matrix
        double[] functionality = new double [n]; //set matrix to carry double values from input

        for (int i = 0; i < n; i++){ //fill functionality array
            int index = StdIn.readInt();
            functionality[index] = StdIn.readDouble();
        }
        for (int i = 0; i < n; i++){ //fill matrix
            for (int j = 0; j < n; j++){
                matrix[i][j] = StdIn.readInt();

            }
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                double info = (matrix[i][j])/(functionality[i]*functionality[j]); //
                matrix[i][j] = (int) info; //cast double as int for new matrix
            }
        }

        int[] minny = new int [n]; //array that keeps track of the min path 
        boolean[] dilk = new boolean[n]; //array that keeps track of what we have checked 

        for(int i = 0; i < minny.length; i++){ //fills the array
            if (i == 0){ //if we are at the first index
                minny[i] = 0; //set the current as 0 so we go to it first in next for loop 
            }
            else {
                minny[i] = Integer.MAX_VALUE; //set everything else as max value
            }
        }

        for (int i = 0; i < minny.length - 1; i++){ 
            int current = getMinCost(dilk, minny); //get min path for current value

            dilk[current] = true; //set the current value as done

            for (int j = 0; j < minny.length; j++){ //look at neighbors
                if (matrix[current][j] > 0){ //if the matrix at our current position is greater than zero 
                    if (dilk[j] == false && minny[current] != Integer.MAX_VALUE && minny[current] + matrix[current][j] < minny[j]){ 
                    //if its not checked and its less than the min pathway
                        minny[j] = minny[current] + matrix[current][j]; //set added values as new min 
                    }
                }
            }
        }
        StdOut.print(minny[n-1]);
    }
}
