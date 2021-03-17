
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class MainProgram {

    public static void main(String[] args) {
        // Read in the data from the file. Since we are inserting the data into a hashmap, the numbers will be de-duplicated.
        Map<Long, Long> data = readDataFromFile("data.txt");
        
        // Create a sorted array from the data.
        long[] sortedData = createSortedArray(data);
        

    }
    
    public static long[] createSortedArray(Map<Long, Long> data) {
        long[] sortedData = new long[data.size()];
        int i = 0;
        
        for (long number : data.keySet()) {
            sortedData[i] = number;
            i++;
        }
        
        Arrays.sort(sortedData);
        
        return sortedData;
    }
    
    public static Map<Long, Long> readDataFromFile(String filePath) {
        // Create the long array to store the data.
        Map<Long, Long> data = new HashMap<>();
        
        // Create a Scanner object for reading the file.
        try (Scanner scanner = new Scanner(Paths.get(filePath))) {
            
            // Read the file until all lines have been read.
            while (scanner.hasNextLine()) {
                // Read one line.
                long number = Long.valueOf(scanner.nextLine());
                // Add the number to the array.
                data.put(number, number);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return data;
    }
}
