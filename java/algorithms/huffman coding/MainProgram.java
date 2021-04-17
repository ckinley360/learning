
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class MainProgram {
    
    // Create a hash map to track the original score of each node. This will help us later when we merge nodes.
    private static Map<String, Long> nodeScores = new HashMap<>();
    
    public static void main(String[] args) {
        // Create the min heap from the data file.
        MinHeap minHeap = createMinHeapFromFile("data.txt");
        
        // Create the Huffman tree.
        createHuffmanTree(minHeap);
    }
    
    public static void createHuffmanTree(MinHeap minHeap) {
        // These will track the last two remaining nodes in the tree, after building it.
        NodeScorePair pairOne = null;
        NodeScorePair pairTwo = null;

        // Build the tree.
        while (true) {
            // The base case: two nodes remaining in the tree.
            if (minHeap.getSize() == 2) {
                break;
            }

            // Extract the two lowest-score nodes from the min heap.
            pairOne = minHeap.poll();
            pairTwo = minHeap.poll();
            
            // Merge the two lowest-score nodes, using the pipe character to delimit the distinct node names in the merged name.
            NodeScorePair mergedNode = mergePairs(pairOne, pairTwo);
            
            // Insert the merged node into the min heap.
            minHeap.add(mergedNode);
        }
        
        System.out.println("Node one: " + pairOne.getNode().getName() + " " + pairOne.getScore());
        System.out.println("Node two: " + pairTwo.getNode().getName() + " " + pairTwo.getScore());
    }
    
    public static NodeScorePair mergePairs(NodeScorePair pairOne, NodeScorePair pairTwo) {
        Node nodeOne = pairOne.getNode();
        Node nodeTwo = pairTwo.getNode();
        long scoreOne = pairOne.getScore();
        long scoreTwo = pairTwo.getScore();
        
        Node mergedNode = new Node(nodeOne.getName() + "|" + nodeTwo.getName(), null, null, null, 0);
        
        NodeScorePair mergedPair = new NodeScorePair(mergedNode, scoreOne + scoreTwo);
        
        return mergedPair;
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
                long score = Long.valueOf(line);
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
