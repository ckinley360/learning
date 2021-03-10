
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;

public class MainProgram {
    
    public static void main(String[] args) {
        // Read in the data from the file.
        List<Integer> data = readDataFromFile("data.txt");
        
        // Create a max heap object.
        MaxHeap maxHeap = new MaxHeap();
        
        // Create a min heap object.
        MinHeap minHeap = new MinHeap();
        
        // Insert the first number of the data into the max heap.
        maxHeap.add(data.get(0));
        
        // Add the rest of the numbers to the min and max heaps.
        for (int i = 1; i < data.size(); i++) {
            
            // Insert the number into the appropriate heap.
            if (data.get(i) > maxHeap.peek()) { // If the number is larger than the max of the max heap, insert into the min heap.
                minHeap.add(data.get(i));
            } else if (data.get(i) < minHeap.peek()) { // If the number is smaller than the min of the min heap, insert into the max heap.
                maxHeap.add(data.get(i));
            } else { // If the number is between the max of the max heap and the min of the min heap, insert arbitrarily into the max heap.
                maxHeap.add(data.get(i));
            }
            
            // Rebalance the heaps as needed.
            rebalanceHeaps(maxHeap, minHeap);
        }
        
        // Print out the min.
        System.out.println("Min: " + minHeap.poll());
        
        System.out.println("");
        
        // Print out the max.
        System.out.println("Max: " + maxHeap.poll());
    }
    
    public static void rebalanceHeaps(MaxHeap maxHeap, MinHeap minHeap) {
        // If the size of maxHeap is more than 1 greater than the size of minHeap, extract the max from maxHeap and insert it into minHeap.
        if (maxHeap.getSize() - minHeap.getSize() > 1) {
            int transferNumber = maxHeap.poll();
            minHeap.add(transferNumber);
        }
        
        // If the size of minHeap is more than 1 greater than the size of maxHeap, extract the min from minHeap and insert it into maxHeap.
        if (minHeap.getSize() - maxHeap.getSize() > 1) {
            int transferNumber = minHeap.poll();
            maxHeap.add(transferNumber);
        }
    }
    
    public static int computeMedian(MinHeap minHeap, MaxHeap maxHeap) {
        
    }
    
    public static List<Integer> readDataFromFile(String filePath) {
        // Create the arraylist to store the data.
        List<Integer> data = new ArrayList<>();
        
        // Create a Scanner object for reading the file.
        try (Scanner scanner = new Scanner(Paths.get(filePath))) {
            
            // Read the file until all lines have been read.
            while (scanner.hasNextLine()) {
                // Read one line.
                int number = Integer.valueOf(scanner.nextLine());
                // Add the number to the arraylist.
                data.add(number);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return data;
    }
}
