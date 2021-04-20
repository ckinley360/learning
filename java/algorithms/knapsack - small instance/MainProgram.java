
import java.nio.file.Paths;
import java.util.Scanner;

public class MainProgram {

    public static void main(String[] args) {
        // Create an array and add the items from the data file to it.
        Item[] items = createArrayFromFile("smalldata.txt");
        
        for (Item item : items) {
            System.out.println(item);
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
