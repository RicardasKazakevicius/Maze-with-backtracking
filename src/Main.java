/**
 * @author Ričardas Kazakevičius
 * 11 užduotis: Kelio labirinte paieška su grįžimu atgal.
 * Duota: Labirintas, turintis m eilučių ir n stulpelių bei pradinis taškas (i,j).
 * Rasti: Kelią išeiti iš labirinto naudojant paiešką su grįžimu.
 */
public class Main {
    
    public static void main(String[] args) {
        int m = 5;
        int n = 5;
        int partitions = 10;
        int startRow = 0; 
        int startCol = 0; 
        int finishRow = m-1; 
        int finishCol = n-1;
        
        if (args.length == 7) {
            m = Integer.parseInt(args[0]);
            n = Integer.parseInt(args[1]);
            partitions = Integer.parseInt(args[2]);
            startRow = Integer.parseInt(args[3]); 
            startCol = Integer.parseInt(args[4]); 
            finishRow = Integer.parseInt(args[5]); 
            finishCol = Integer.parseInt(args[6]);
        }
     
        //Maze maze = new Maze(m, n, partitions);
        //maze.findPath(startRow, startCol, finishRow, finishCol);
        Test testmaze = new Test();
        testmaze.test();
    }
}
