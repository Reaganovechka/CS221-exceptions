import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FormatChecker extends Exception {
    public static void main(String[] args) {
        String fileName;
        int expectedRow;
        int expectedCol;
        double[][] grid;
        String tempVal = null; // stores temp value before parsing to int/double
        

        //Read in file
        for (int i = 0; i < args.length; i++) {
            fileName = args[i]; //get file name from command line
            Boolean fileStatus = true; //Keeps track if file is valid or not
            Scanner fileScnr = null;
            Scanner lineScnr = null;

            //read file
            try {
                //read file
                fileScnr = new Scanner(new File(fileName));
                System.out.println(fileName);

                //Get rows/ cols
                String rowsAndCols = fileScnr.nextLine().trim(); 
                lineScnr = new Scanner(rowsAndCols);
                expectedRow = Integer.parseInt(lineScnr.next());
                expectedCol = Integer.parseInt(lineScnr.next());
                if (lineScnr.hasNext()){
                    throw new Exception ("too many row/col args");
                }
                //Catch exception below

                //read in the data
                
                grid = new double[expectedRow][expectedCol];
            
                for (int row = 0; row < grid.length; row++) { 
                    String line = fileScnr.nextLine().trim();
                    lineScnr = new Scanner(line);
                    if (line.isEmpty()) {
                        throw new Exception("Not enough rows!");
                    }
                    for (int col = 0; col < grid[row].length; col++) {                    
                        grid[row][col] = Double.parseDouble(lineScnr.next());
                    }
                    if (lineScnr.hasNext()) {
                        throw new Exception ("Too many numbers in row " + (row + 1));
                    }
                }
                
                //Check next line after reading expected rows
                if (fileScnr.hasNextLine()){
                    String extraLine = fileScnr.nextLine().trim();
                    if (!extraLine.isEmpty()) {
                        throw new Exception("Too many rows!");
                    }
                }
            }

            catch (FileNotFoundException e) {
                System.out.println(e.toString());
                fileStatus = false;
            }                
            catch (NumberFormatException e) {
                System.out.println(e.toString());
                fileStatus = false;
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.toString());
                fileStatus = false;
            }
            catch (Exception e) {
                System.out.println("A format error occured: " + e.getMessage());
                fileStatus = false;
            }

            //Print result of file
            if (!fileStatus){
                System.out.println("INVALID");
                System.out.println();
            }                
            else if (fileStatus) {
                   System.out.println("VALID");
                System.out.println();
            }
        }
    }
}