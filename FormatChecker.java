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
        

        //Read in file
        for (int i = 0; i < args.length; i++) {
            fileName = args[i];
            Scanner fileScnr = null;
            Scanner lineScnr = null;
            //read file
            try {
                fileScnr = new Scanner(new File(fileName));
            } catch (FileNotFoundException e) {
                System.out.println("java.io.FileNotFoundException: noSuchFile (The system cannot find the file specified)");
                fileStatus = false;
            }

            //If the file is valid, start checking the format
            if (fileStatus) {
                String rowsAndCols = fileScnr.nextLine().trim(); 
                lineScnr = new Scanner(rowsAndCols);
                try {
                    expectedRow = lineScnr.nextInt();
                    expectedCol = lineScnr.nextInt();
                    System.out.print(expectedRow);
                    System.out.print(expectedCol);
                }
                catch (InputMismatchException e) {
                    System.out.println(e.toString() + ": Row/Col not integer");
                    fileStatus = false;
                }
            }

            
        }
    }
}