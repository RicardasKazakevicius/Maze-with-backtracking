/**
 * This class defines maze.
 * "." value means clear position. "#" value means that there is an obstacle.
 * "S" value means maze start location. "F" value means maze finish position.
 * "*" means path
*/
public class Matrix {
    private final int rows;
    private final int columns;
    public final String data[][];
    public String name;
    
    public Matrix(int m, int n, String name) {
        this.rows = m;
        this.columns = n;
        data = new String[m][n];
        this.name = name;
    }
    /**
     * initializing matrix
     * @param randomNumbersArray numbers in this array means that at 
     * matrix position by given numbers there is an obstacle.
     */
    public void init(int[] randomNumbersArray) {
        int position = 0;
        
        for (int i = 0; i < rows; ++i) {
            
            for (int j = 0; j < columns; ++j) {
                
                if (numberIsInArray(position, randomNumbersArray)) {
                    data[i][j] = "1";
                }
                else {
                    data[i][j] = "0";
                }
                ++position;
            }
        }
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
}
 