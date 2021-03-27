
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.Arrays;

public class MainProgram {
    
    public static void main(String[] args) {
        // Create the Job arrays.
        Job[] jobs = readDataFromFile("data.txt", "difference");
        Job[] jobs2 = readDataFromFile("data.txt", "quotient");
        
        // Sort the Job arrays based on score.
        Arrays.sort(jobs);
        Arrays.sort(jobs2);
        
        // Compute the completion time of each job.
        int sumOfWeightedCompletionTimes = computeSumOfWeightedCompletionTimes(jobs);
        int sumOfWeightedCompletionTimes2 = computeSumOfWeightedCompletionTimes(jobs2);
        
        // Print the sum of weighted completion times.
        System.out.println("Sum of weighted completion times (difference): " + sumOfWeightedCompletionTimes);
        System.out.println("Sum of weighted completion times (quotient): " + sumOfWeightedCompletionTimes2);
    }
    
    public static int computeSumOfWeightedCompletionTimes(Job[] jobs) {
        // Variable to track the running total of completion time.
        int completionTime = 0;
        double sumOfWeightedCompletionTimes = 0;
        
        for (Job job : jobs) {
            completionTime += job.getLength();
            sumOfWeightedCompletionTimes += (double) job.getWeight() * completionTime;
        }
        
        return (int) sumOfWeightedCompletionTimes;
    }
    
    public static Job[] readDataFromFile(String filePath, String scoreType) {
        // Create the Job array to store the jobs in.
        Job[] jobs = new Job[10000];
        
        // Create index tracker for the Job array.
        int i = 0;
        
        // Boolean to track whether we are reading the first row, which we want to ignore because it only tells us the total number of jobs.
        Boolean firstRow = true;
        
        // Create a Scanner object for reading the file.
        try (Scanner scanner = new Scanner(Paths.get(filePath))) {
            
            // Read the file until all lines have been read.
            while (scanner.hasNextLine()) {
                // Read one line.
                String row = scanner.nextLine();
                
                // Extract the data from the row only if it is not the first row.
                if (firstRow) {
                    firstRow = false;
                    continue;
                }
                
                // Split the line on space character.
                String[] parts = row.split(" ");
                
                // Store the weight and length in their own variables.
                int weight = Integer.valueOf(parts[0]);
                int length = Integer.valueOf(parts[1]);
                
                // Create a Job object from the row.
                Job job = new Job(weight, length, scoreType);
                
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
