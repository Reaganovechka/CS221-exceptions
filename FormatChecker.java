import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FormatChecker {
    public static void main(String[] args) {
        String fileName;
        Boolean fileStatus = true;
        

        //Read in file
        for (int i = 0; i < args.length; i++) {
            fileName = args[i];
            //read file
            try {
                Scanner fileScnr = new Scanner (new File(fileName));
            } catch (FileNotFoundException e) {
                System.out.println("java.io.FileNotFoundException: noSuchFile (The system cannot find the file specified)");
                fileStatus = false;
                System.out.print(fileStatus.toString());
            }

            if (fileStatus == true) {
                
            }

            
        }
    }
}