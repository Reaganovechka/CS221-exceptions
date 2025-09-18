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
        

        //Read in file
        for (int i = 0; i < args.length; i++) {
            fileName = args[i];
            Boolean fileStatus = true;
            Scanner fileScnr = null;
            Scanner lineScnr = null;
            //read file
            try {
                //read file
                fileScnr = new Scanner(new File(fileName));
                System.out.println(fileName);
                //catch exception below

                //Get rows/ cols
                String rowsAndCols = fileScnr.nextLine().trim(); 
                lineScnr = new Scanner(rowsAndCols);
                expectedRow = lineScnr.nextInt();
                expectedCol = lineScnr.nextInt();
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
                        grid[row][col] = lineScnr.nextDouble();
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
                System.out.println("java.io.FileNotFoundException: " + fileName +  "(The system cannot find the file specified)");
                fileStatus = false;
            }                
            catch (InputMismatchException e) {
                    System.out.println(e.toString() + ": Row/Col not integer");
                    fileStatus = false;
            }
            catch (NumberFormatException e) {
                System.out.println(e.toString());
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