
import java.nio.file.Paths;
import java.util.Scanner;

public class MainProgram {
    
    public static void main(String[] args) {
        // Create the array from the data file.
        int[] data = createArrayFromFile("testdata.txt");
        
        // Compute the values of the max weight independent set.
        int[] values = computeMaxWeightIndependentSetValues(data);
        
        for (int value : values) {
            System.out.println(value);
        }
    }
    
    public static int[] computeMaxWeightIndependentSetValues(int[] data) {
        // Create the array to store the max weight independent set values in.
        int[] values = new int[7];
        
        // Insert the initial values for the empty set and single node set.
        values[0] = 0;
        values[1] = data[0];
        
        // Compute the remaining values.
        for (int i = 2; i < 7; i++) {
            int lastExcluded = values[i - 1];
            int secondToLastExcluded = values[i - 2] + data[i - 1];
            
            if (lastExcluded > secondToLastExcluded) {
                values[i] = lastExcluded;
            } else {
                values[i] = secondToLastExcluded;
            }
        }
        
        return values;
    }
    
    public static int[] createArrayFromFile(String filePath) {
        // Create the array to store the data in.
        int[] data = new int[6];
        
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
