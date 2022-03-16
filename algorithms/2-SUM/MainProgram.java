
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
        
        // Compute and output the count of distinct 2-SUMs.
        int distinctSums = computeDistinctSums(sortedData);
        System.out.println(distinctSums);
    }
    
    public static int computeDistinctSums(long[] sortedData) {
        // Create variable to track count of distinct sums.
        int distinctSumCount = 0;
        
        // Create hashmap to track distinct sums. The Boolean is arbitrary.
        Map<Long, Boolean> distinctSums = new HashMap<Long, Boolean>();
        
        // For each element x of the array, find all other elements y in the interval [-10000 - x, 10000 - x].
        for (int i = 0; i < sortedData.length; i++) {
            long x = sortedData[i];
            long lowerBound = -10000 - x;
            long upperBound = 10000 - x;
            
            // Find the left and right indices of the range of elements within the target interval.
            int lowerBoundIndex = binarySearch(sortedData, 0, sortedData.length - 1, lowerBound);
            int upperBoundIndex = binarySearch(sortedData, 0, sortedData.length - 1, upperBound);
            
            // Do a linear scan through the array between the lowerBoundIndex and upperBoundIndex, looking for elements y that fall within the target interval.
            for (int j = lowerBoundIndex; j <= upperBoundIndex; j++) {
                long y = sortedData[j];
                if (y != x && y >= lowerBound && y <= upperBound) {
                    distinctSums.put(x + y, true);
                }
            }
        }
        
        distinctSumCount = distinctSums.size();
        return distinctSumCount;
    }
    
    // Find the target element x. If not found, then return the last index that was searched.
    public static int binarySearch(long[] array, int l, int r, long x) {
        if (r >= l) { 
            int mid = l + (r - l) / 2; 
  
            // If the middle is the target element x. 
            if (array[mid] == x) 
                return mid; 
  
            // If target element is smaller than mid, then it can only be present in left subarray. 
            if (array[mid] > x) 
                return binarySearch(array, l, mid - 1, x); 
  
            // Else target element can only be present in right subarray. 
            return binarySearch(array, mid + 1, r, x); 
        } 
  
        // Target element is not in the array, so return the last index that was searched. 
        return r;
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
        // Create the long map to store the data.
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
