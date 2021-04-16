
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class MainProgram {
    
    // Create a hash map to track the original score of each node. This will help us later when we merge nodes.
    private static Map<String, Integer> nodeScores = new HashMap<>();
    
    public static void main(String[] args) {
        // Create the min heap from the data file.
        MinHeap minHeap = createMinHeapFromFile("data.txt");
        
    }
    
    public static void createHuffmanTree(MinHeap minHeap) {
        // Build the tree.
        while (!minHeap.isEmpty()) {
            // Extract the two lowest-score nodes from the min heap.
            NodeScorePair pairOne = minHeap.poll();
            NodeScorePair pairTwo = minHeap.poll();
            Node nodeOne = pairOne.getNode();
            Node nodeTwo = pairTwo.getNode();
            int scoreOne = pairOne.getScore();
            int scoreTwo = pairTwo.getScore();
            
            // Create a merged node from the two lowest-score nodes. We will use the pipe character to delimit the distinct node names in the merged name.
            Node mergedNode = new Node(nodeOne.getName() + "|" + nodeTwo.getName(), )
        }
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
                Node node = new Node(String.valueOf(rowNumber), null, null, null, 0);
                int score = Integer.valueOf(line);
                NodeScorePair pair = new NodeScorePair(node, score);
                minHeap.add(pair);
                
                // Record the node's score in the hash map.
                nodeScores.put(node.getName(), score);
                
                // Increment the row counter.
                rowNumber++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return minHeap;
    }
}
