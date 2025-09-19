import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * FormatChecker verifies the format of each file provided as a command line
 * argument.
 * For each file, it checks that:
 * The first line contains exactly 2 integers: number of rows and columns
 * The file contains the specified number of rows and columns, confirming each
 * value is either a double or an integer.
 * The file has no extra rows or columns
 * 
 * If the file is valid, prints the file name followed by 'VALID.'
 * If the file is invalid, prints the file name, details about the formatting
 * error, and 'INVALID.'
 * (This javadoc describes class FormatChecker)
 * 
 * @author Reagan Ovechka
 */
public class FormatChecker {

    /**
     * Main method to process whether each file is formatted correctly, then prints results to standard output.
     * (this javadoc describes method main())
     * 
     * @param args List of all file names to be checked
     * @throws FileNotFoundException if a file cannot be found
     * @throws NumberFormatException if a number cannot be parsed
     * @throws Exception             for any other format issues
     */
    public static void main(String[] args) {
        String fileName; // Holds name of current file
        int expectedRow; // Expected number of rows in the file
        int expectedCol; // Expected number of columns in the file
        double[][] grid; // Holds the data read from the file

        // Try to read each file and check format, catching exceptions as needed
        for (int i = 0; i < args.length; i++) {
            fileName = args[i]; // get file name from command line
            Boolean fileStatus = true; // Keeps track if file is valid or not
            Scanner fileScnr = null; // Scanner to read the file
            Scanner lineScnr = null; // Scanner to read each line

            try {
                // read file
                fileScnr = new Scanner(new File(fileName));
                System.out.println(fileName);

                // Get rows/ cols
                String rowsAndCols = fileScnr.nextLine().trim();
                lineScnr = new Scanner(rowsAndCols);
                expectedRow = Integer.parseInt(lineScnr.next());
                expectedCol = Integer.parseInt(lineScnr.next());
                if (lineScnr.hasNext()) {
                    throw new Exception("too many row/col args");
                }

                grid = new double[expectedRow][expectedCol];

                for (int row = 0; row < grid.length; row++) {
                    String line = fileScnr.nextLine().trim();
                    lineScnr = new Scanner(line);
                    if (line.isEmpty()) {
                        lineScnr.close();
                        throw new Exception("Not enough rows!");
                    }
                    for (int col = 0; col < grid[row].length; col++) {
                        if (!lineScnr.hasNext()) {
                            lineScnr.close();
                            throw new Exception("Not enough columns!");
                        }
                        grid[row][col] = Double.parseDouble(lineScnr.next());
                    }
                    if (lineScnr.hasNext()) {
                        lineScnr.close();
                        throw new Exception("Too many numbers in row " + (row + 1));
                    }
                }

                // Is there any extra lines? do they have data?
                if (fileScnr.hasNextLine()) {
                    String extraLine = fileScnr.nextLine().trim();
                    if (!extraLine.isEmpty()) {
                        throw new Exception("Too many rows!");
                    }
                }
            }

            // Catch exceptions
            catch (FileNotFoundException | NumberFormatException e) {
                System.out.println(e.toString());
                fileStatus = false;
            } catch (Exception e) {
                System.out.println("File formatted incorrectly: " + e.getMessage());
                fileStatus = false;
            } finally {
                // Close scanners
                fileScnr.close();
                lineScnr.close();
            }

            // Print the result of the file format
            if (!fileStatus) {
                System.out.println("INVALID");
                System.out.println();
            } else if (fileStatus) {
                System.out.println("VALID");
                System.out.println();
            }

        }
    }
}