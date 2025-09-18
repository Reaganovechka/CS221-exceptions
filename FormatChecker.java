import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FormatChecker {
    public static void main(String[] args) {
        String fileName;
        Boolean fileStatus = true;
        int expectedRow;
        int expectedCol;
        double[][] grid;
        

        //Read in file
        for (int i = 0; i < args.length; i++) {
            fileName = args[i];
            Scanner fileScnr = null;
            Scanner lineScnr = null;
            //read file
            try {
                //read file
                fileScnr = new Scanner(new File(fileName));
                //catch exception below

                //Get rows/ cols
                String rowsAndCols = fileScnr.nextLine().trim(); 
                lineScnr = new Scanner(rowsAndCols);
                expectedRow = lineScnr.nextInt();
                expectedCol = lineScnr.nextInt();
                //Catch exception below

                //read in the data
                grid = new double[expectedRow][expectedCol];
            
                for (int row = 0; row < grid.length; row++) { //Change the grid.length to expected row to get rid of that trailing line?? Should always have the proper amout??
                    String line = fileScnr.nextLine().trim();
                    lineScnr = new Scanner(line);
                    for (int col = 0; col < grid[row].length; col++) {
                        if (grid[row].length == 0) {
                            break;
                        }                        
                        grid[row][col] = lineScnr.nextDouble();
                    }
                }

            } 
            catch (Exception e) {
                if (e == error)
            }
            // catch (FileNotFoundException e) {
            //     System.out.println("java.io.FileNotFoundException: " + fileName +  "(The system cannot find the file specified)");
            //     fileStatus = false;
            // }                
            // catch (InputMismatchException e) {
            //         System.out.println(e.toString() + ": Row/Col not integer");
            //         fileStatus = false;
            //         break; //file is invalid, do not read the rest of the file
            // }
            // catch (NumberFormatException e) {
            //     System.out.println(e.toString());
            // }

            //TODO: print the result of the file
        }
    }
}