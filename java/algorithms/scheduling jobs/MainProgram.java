
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.Arrays;

public class MainProgram {
    
    public static void main(String[] args) {
        
    }
    
    public static Job[] readDataFromFile(String filePath) {
        // Create the Job array to store the jobs in.
        Job[] jobs = new Job[10000];
        
        // Create index tracker for the Job array.
        int i = 0;
        
        // Create a Scanner object for reading the file.
        try (Scanner scanner = new Scanner(Paths.get(filePath))) {
            
            // Read the file until all lines have been read.
            while (scanner.hasNextLine()) {
                // Read one line.
                String row = scanner.nextLine();
                
                // Split the line on space character.
                String[] parts = row.split(" ");
                
                // Store the weight and length in their own variables.
                int weight = Integer.valueOf(parts[0]);
                int length = Integer.valueOf(parts[1]);
                
                // Create a Job object from the row.
                Job job = new Job(weight, length, "difference");
                
                // Add the Job object to the array.
                jobs[i] = job;
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return jobs;
    }
}
