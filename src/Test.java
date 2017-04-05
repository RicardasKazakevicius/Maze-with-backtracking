
public class Test {
    private int m = 11; // number of rows in maze
    private int n = 11; // number of columns in maze
    int partitions = 100;
    
    private int startRow = 0; 
    private int startCol = 0;
    private int endRow = m-1; 
    private int endCol = n-1;
    private Maze maze;
    
    public void test() {
                
        int testTimes = 100;
        int counter = 0;
        
        for (int i = 0; i < testTimes; ++i) {
            
            maze = new Maze(m, n, partitions);
            
            if (maze.findPath(startRow, startRow, endRow, endCol))
                ++counter;
        }
        System.out.println("Counter = " + counter);
    }
}
