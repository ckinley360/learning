
import java.nio.file.Paths;
import java.util.Scanner;

public class MainProgram {
    
    public static void main(String[] args) {
        // Create the array from the data file.
        int[] data = createArrayFromFile("data.txt");
        
        for (int weight : data) {
            System.out.println(weight);
        }
    }
    
    public static int[] createArrayFromFile(String filePath) {
        // Create the array to store the data in.
        int[] data = new int[1000];
        
        // Boolean to track whether we are reading the first row, which we want to ignore because it only tells us the total number of nodes in the path graph.
        Boolean firstRow = true;
        
        // To track the index of the array to fill.
        int i = 0;
        
        // Create a scanner object for reading the file.
        try (Scanner scanner = new Scanner(Paths.get(filePath))) {
            
            // Read the file until all lines have been read.
            while (scanner.hasNextLine()) {
                // Read one line.
                String line = scanner.nextLine();
                
                // If this is the first row, then skip it.
                if (firstRow) {
                    firstRow = false;
                    continue;
                }
                
                // Convert the line to integer, which represents the node weight.
                int weight = Integer.valueOf(line);
                
                // Insert the weight into the array.
                data[i] = weight;
                
                // Increment the array index tracker.
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return data;
    }
}
