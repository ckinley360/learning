
import java.nio.file.Paths;
import java.util.Scanner;

public class MainProgram {

    public static void main(String[] args) {
        // Create an array and add the items from the data file to it.
        Item[] items = createArrayFromFile("smalldata.txt");
        
        // The weight capacity of the knapsack.
        int knapsackCapacity = 10000;
    }
    
    public static int computeOptimalKnapsackSolution(Item[] items, int knapsackCapacity) {
        // Create an array to store the value of the optimal solution for all subproblems.
        int[][] optimalSolutionValues = new int[items.length + 1][knapsackCapacity + 1];
        
        // Initialize the array for item count of 0.
        for (int x = 0; x <= knapsackCapacity + 1; x++) {
            optimalSolutionValues[0][x] = 0;
        }
        
        // Compute the rest of the values.
        for (int i = 1; i <= items.length + 1; i++) {
            for (int x = 0; x <= knapsackCapacity + 1; x++) {
                int maxValue = 0;
                
                if ()
//                optimalSolutionValues[i, x] = 
            }
        }
    }
    
    public static Item[] createArrayFromFile(String filePath) {
        // Declare the array to store the items in. We will create it and set its size using the first row of the data file.
        Item[] items = null;
        
        // Boolean to track whether we are reading the first row, which contains the knapsack capacity and the number of items.
        Boolean firstRow = true;
        
        // To track the index of the array to fill.
        int i = 0;
        
        // Create a Scanner object for reading the file.
        try (Scanner scanner = new Scanner(Paths.get(filePath))) {
            
            // Read the file until all lines have been read.
            while (scanner.hasNextLine()) {
                // Read one line.
                String line = scanner.nextLine();
                
                // Split the string on the space delimiter.
                String[] parts = line.split(" ");
                
                // If this is the first row, then create the array and set its size using the second element of the split string.
                if (firstRow) {
                    items = new Item[Integer.valueOf(parts[1])];
                    firstRow = false;
                    continue;
                }
                
                // Create an Item object from the row.
                Item item = new Item(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
                
                // Add the item to the array.
                items[i] = item;
                
                // Increment the array index tracker.
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return items;
    }
}
