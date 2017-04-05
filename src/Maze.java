/**
 * S means start of maze, F means finish of maze. 
 * Finish is always at any row at last column.
 */
import java.util.Arrays;
import java.util.Random;

public class Maze {
    private final int row; 
    private final int col;  
    private int finishRow;
    private int finishCol;
    public int[] randomNumbersArray;
    /**
     * hmatrix defines where is horizontal partitions in maze
     */
    private final Matrix hmatrix; 
    /**
     * vmatrix defines where is vertical partitions in maze
     */
    private final Matrix vmatrix;
    
    private final Matrix pathmatrix;
    public int steps = 0;
    
    public Maze(int m, int n, int partitions) {
        this.row = m;
        this.col = n;
        hmatrix = new Matrix(row, col, "Horizontal");
        hmatrix.init(randPosForHMatrix(partitions / 2));
        
        vmatrix = new Matrix(row, col, "Vertical");
        vmatrix.init(randPosForVMatrix(partitions / 2  + (partitions % 2)));
        
        pathmatrix = new Matrix(row, col, "Path");
        pathmatrix.init(new int[0]);      
    }
    
    boolean trace = false;
    boolean showSteps = false;
    
    /**
     * @param x start position index in row 
     * @param y start position index in column
     * @param finishRow finish position index in row 
     * @param finishCol finish position index in column
     * @return true if path found, false if not
     */
    public boolean findPath(int x, int y, int finishRow, int finishCol) {
        this.finishRow = finishRow;
        this.finishCol = finishCol;
        if (step(x,y,hmatrix)) {
            pathmatrix.data[x][y] = "S"; // solved, start position
            if (trace) {
                print(hmatrix);
                print(vmatrix);
                System.out.println("Solved!");
                print(pathmatrix);
            }
            return true;
        }
        else {
            if (trace) {
                print(hmatrix);
                print(vmatrix);
                System.out.println("Path not found!"); 
                print(pathmatrix);
            }
            return false;
        }
        
    }
    
    private boolean step(int x, int y, Matrix matrix) {
        ++steps;
        
        if (showSteps)
            System.out.println("x=" + x + " y=" + y);
        
        if ((y == finishCol) && (x == finishRow)) { // found exit
            pathmatrix.data[x][y] = "F"; // exit position
            return true;           
        }
        
        if ( (x == row) || (y == col) || (x < 0) || (y < 0) || ("1".equals(matrix.data[x][y])) 
                || ("*".equals(pathmatrix.data[x][y])) ){
            return false;
        }
        
        // mark location as part of path
        pathmatrix.data[x][y] = "*";
        
        boolean result;
        
        // try to go right
        result = step(x, y+1, vmatrix); 
        if (result) 
            return true;
        
        // try to go down
        result = step(x+1, y, hmatrix); 
        if (result) 
            return true;
        
        // try to go left
        result = step(x, y-1, vmatrix); 
        if (result) 
            return true;
        
        // try to go up
        result = step(x-1, y, hmatrix); 
        if (result) 
            return true;
        
        // unmark location as part of path
        pathmatrix.data[x][y] = "0";
        
        return false;
    } 
    
    /**
     * returns array with different numbers in range m..(n-1)*m
     * Amount of numbers is given by parameter partitions
     */
    private int[] randPosForHMatrix(int partitions) {
        
        randomNumbersArray = new int[partitions];
        Arrays.fill(randomNumbersArray, -1); // eliminate zeros from array
        
        int numbersGenerated = 0;
                
        while(numbersGenerated != partitions) {
            
            Random rand = new Random();
            
            int number = rand.nextInt((row)*(col)); 
            
            if ((number >= col) && (number < col*(row-1)) 
                    && !numberIsInArray(number, randomNumbersArray)){

                randomNumbersArray[numbersGenerated] = number;
                ++numbersGenerated;
            }
        }
        return randomNumbersArray;
    }  
    
    /**
    * returns array with different numbers in range 0..n*m, 
    * excluding numbers which are in first and last columns positions
    * Amount of numbers is given by parameter partitions
    */
    private int[] randPosForVMatrix(int partitions) {
        
        randomNumbersArray = new int[partitions];
        //Arrays.fill(randomNumbersArray, -1); // eliminate zeros from array
        
        int numbersGenerated = 0;
                
        while(numbersGenerated != partitions) {
            
            Random rand = new Random();
            
            int number = rand.nextInt(row*col); 
            
            if (((number % row) != 0) && (((number - (col-1)) % col) != 0)
                    && !numberIsInArray(number, randomNumbersArray)){

                randomNumbersArray[numbersGenerated] = number;
                ++numbersGenerated;
            }
        }
        return randomNumbersArray;
    }  

     /**
     * @return is @param number is in @param array
     */
    private boolean numberIsInArray(int number, int[] array) {
        
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == number) {
                return true;
            }        
        }
        return false;
    }

    private void print(Matrix matrix) {
        System.out.println(matrix.name);
        for (int i = 0; i < row; ++i) {
            
            for (int j = 0; j < col; ++j) {
                
                System.out.print(matrix.data[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
