
import java.nio.file.Paths;
import java.util.Scanner;

public class MainProgram {
    
    public static void main(String[] args) {
        // Create the min heap from the data file.
        MinHeap minHeap = createMinHeapFromFile("data.txt");
    }
    
    public static MinHeap createMinHeapFromFile(String filePath) {
        // Create the min heap object to store the data.
        MinHeap minHeap = new MinHeap();
        
        // Boolean to track whether we are reading the first row, which we want to ignore because it only tells us the total number of characters in the alphabet.
        Boolean firstRow = true;
        
        // To track the name of the character, represented by the row number (ignoring the first row).
        int rowNumber = 1;
        
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
                
                // Create the NodeScorePair object corresponding to the current row and add it to the min heap.
                CharacterNode node = new CharacterNode(String.valueOf(rowNumber), null, null, null);
                int score = Integer.valueOf(line);
                NodeScorePair pair = new NodeScorePair(node, score);
                minHeap.add(pair);
                
                // Increment the row counter.
                rowNumber++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return minHeap;
    }
}
