import java.util.ArrayList;

public class NewSudokuSolver {
    /*
    * TODO
    *   -Analize the old code
    *   -Create a new code and document each function
    * */
    //this String is a placeholder to try to resolve this sudoku.
    String GridToUse = "000000048470019000008000000040008000600000050900002600017050000000000560000020093";
    int[][] SudokuGrid = new int[9][9];
     ArrayList[][] PosibleValues = new ArrayList[9][9];
     boolean[][] StateOfGrid = new boolean[9][9];
    public static void main(String[] args) {
        NewSudokuSolver sudoku = new NewSudokuSolver();
        sudoku.InsertValues();
        sudoku.printGrid(sudoku.SudokuGrid);
    }
    //InsertValues
    public void InsertValues(){
        int count = 0;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++) {
                int value = Character.getNumericValue(GridToUse.charAt(count)) ;
                count++;
                this.SudokuGrid[i][j] = value;
            }
        }
    }
    //PrintGrid
    /*
    * This function will take a sudoku grid as input and will print it by going through each value and formating it to look better.
    * add example of sudoku grid->
    * */
    public void printGrid(int[][] GridToPrint){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(j == 0){
                    System.out.print("  " + GridToPrint[i][j] + "  ");
                }else{
                    System.out.print("│  " + GridToPrint[i][j] + "  ");
                }
            }
            System.out.println();
            if(i!=8) {
                System.out.println("─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────");
            }
        }
    }
    //CheckLine
    //CheckSquare
    //CheckandAddValues
    //AbleToSolve
    //BacktrackingAlgoritm
}
