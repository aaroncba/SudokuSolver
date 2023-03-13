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
        System.out.println();
        sudoku.CheckLine();
        sudoku.printGrid(sudoku.SudokuGrid);
    }
    //InsertValues
    public void InsertValues(){
        int count = 0;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++) {
                this.PosibleValues[i][j] = new ArrayList<Integer>();
                for(int add = 1; add <= 9; add++){
                    this.PosibleValues[i][j].add(add);
                }

                int value = Character.getNumericValue(GridToUse.charAt(count)) ;
                count++;
                this.SudokuGrid[i][j] = value;
                if(value != 0){
                    this.StateOfGrid[i][j] = true;
                }else{
                    this.StateOfGrid[i][j] = false;
                }

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
    /*
     * This code will check each horizontal and vertical line, to check the values that can be at an specific square
     *
     * It does it by runnig 2 nested for loops, in which the first loop determines the x axis and the second for loop determines the y axis
     * The code checks if there is a value in that square then it checks the values that are on the horizontal and vertical value, for each value that is present it deletes
     * the value from the possible values. It checks the lines by runnig a for loop inside the nested for loop.
     * */

    public void CheckLine(){
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                if (this.StateOfGrid[x][y]) {
                    continue;
                }
                for(int staticLine = 0; staticLine < 9; staticLine++){
                    //System.out.printf("Boolean value %B, posicion x %d, posicion y %d, Static line %d %n", this.StateOfGrid[staticLine][y], x, y, staticLine);
                    if(this.StateOfGrid[staticLine][y]){
                        this.PosibleValues[x][y].remove((Integer)this.SudokuGrid[staticLine][y]);
                        System.out.println("Value y " + y + "Value staticLine " + staticLine + " Value to remove " + this.SudokuGrid[staticLine][y]);
                        System.out.println("List is the following" + this.PosibleValues[x][y].toString());
                    }
                    if(this.StateOfGrid[x][staticLine]){
                        this.PosibleValues[x][y].remove((Integer)this.SudokuGrid[x][staticLine]);
                        System.out.println("Value x " + x + "Value staticLine " + staticLine + " Value to remove " + this.SudokuGrid[staticLine][y]);
                        System.out.println("List is the following" + this.PosibleValues[x][y].toString());
                    }
                }

            }
        }
    }

    //CheckSquare
    /*
    * 3 int variables are declared and those variables will be equal to 0
    *   -Reinicio
    *   -Renglon
    *   -Pasos
    * a while loop will be created that will determine that if reinicio is 3, it will end, since the logic for reinicio works the following way:
    * -STATEMENT A.1 -> The sudoku grid is divided in 3 big columns size.
    * When the while loop enters by first time, a for loop is created that will run 3 times, this is based on the previous statement (A.1)
    * 2 variables are created:
    *   -PlaceHolderRenglon = 0
    *   -PlaceHolderReinicio = 0
    * based on the range of value of Renglon, a value will be set up to PlaceHolderRenglon
    *   -renglon < 3 -> PlaceHolderRenglon = 0
    *   -renglon < 6 -> PlaceHolderRenglon = 3
    *   -else -> PlaceHolderRenglon = 6
    * a new while loop is initiated, it will work the same way as the previous while loop created, this while loop will work with PlaceHolderReinicio < 3.
    * a for loop will be set up to run 3 times.
    * remove value in the list with position [renglon] [i+(3*reinicion)]  and remove the value on [PlaceHolderRenglon][a+(3*Reinicio)]
    * Once that value is checked, PlaceHolderRenglon and PlaceHolderReinicio will increment by 1.
    *
    * After it exits the second for loop and second while loop, it will increment Renglon and Pasos by 1, then check if Renglon is equal to 9.
    * If equal 9, renglon will be 0 and reinicio will be incremented by 1, this is because of STATEMENT A.1, Renglon 9 is out of limits in the Grid.
    * After it exits the first for loop, pasos will be set up to 0.
     * */

    public void CheckSquare(){
        int reinicio = 0, renglon = 0, pasos = 0;
        while(reinicio < 3){
            while(pasos < 3){
                for(int i = 0; i < 3; i++){
                    int PlaceHolderRenglon = renglon, PlaceHolderReinicio = 0;
                    if(!this.StateOfGrid[renglon][i+(3*reinicio)]){
                        if(renglon < 3){
                            PlaceHolderRenglon = 0;
                        }
                        else if(renglon < 6){
                            PlaceHolderRenglon = 3;
                        }else{
                            PlaceHolderRenglon = 6;
                        }
                        while(PlaceHolderRenglon < 3){
                            for(int a = 0; a < 3; a++){
                                this.PosibleValues[renglon][i+(3*PlaceHolderReinicio)].remove((Integer)this.GridToUse[PlaceHolderRenglon][a+(3*reinicio)]);
                            }
                            PlaceHolderReinicio++;
                            PlaceHolderRenglon++;
                        }
                    }
                }
                renglon++;
                pasos++;
                if(renglon == 9){
                    renglon = 0;
                    reinicio++;
                }
            }
            pasos = 0;
        }

    }


    //CheckandAddValues
    //AbleToSolve
    //BacktrackingAlgoritm
}
