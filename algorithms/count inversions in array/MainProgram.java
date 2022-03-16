
import java.util.Scanner;
import java.nio.file.Paths;
import java.math.BigInteger;

public class MainProgram {
    
    public static void main(String[] args) {
//        int[] testArray = {8, 7, 6, 5, 4, 3, 2, 1};
        int[] numberArray = readDataFromFile("data.txt");
        BigInteger inversions = new BigInteger("0");
        
        inversions = inversions.add(sortAndCountInversions(numberArray, 0, numberArray.length - 1));
        System.out.println("Total inversions: " + inversions.toString());
    }
    
    public static int[] readDataFromFile(String filePath) {
        // Create the array to store the data.
        int[] data = new int[100000];

        // Create the index to determine where to insert each number.
        int i = 0;
        
        // Create a Scanner object for reading the file.
        try (Scanner scanner = new Scanner(Paths.get(filePath))) {
            
            // Read the file until all lines have been read.
            while (scanner.hasNextLine()) {
                // Read one line (number)..
                int number = Integer.valueOf(scanner.nextLine());
                // Store the number in the array.
                data[i] = number;
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return data;
    }
    
    public static BigInteger sortAndCountInversions(int[] array, int start, int end) {
        // Variable to count number of inversions.
        BigInteger inversions = new BigInteger("0");

        // Base case: single-element array.
        if (start >= end) {
            return BigInteger.valueOf(0);
        }
        
        // Divide left, divide right, merge.
        int middle = (start + end) / 2;
        inversions = inversions.add(sortAndCountInversions(array, start, middle));
        inversions = inversions.add(sortAndCountInversions(array, middle + 1, end));
        inversions = inversions.add(mergeAndCountSplitInversions(array, start, middle, end));
        
        return inversions;
    }
    
    public static BigInteger mergeAndCountSplitInversions(int[] array, int start, int middle, int end) {
        // Variable to count number of inversions.
        BigInteger inversions = new BigInteger("0");

        // Create temporary subarrays.
        int[] leftArray = new int[middle - start + 1];
        int[] rightArray = new int[end - middle];
        
        // Copy the elements from the array into the appropriate subarray.
        for (int i = 0; i < leftArray.length; i++) {
            leftArray[i] = array[start + i];
        }
        
        for (int i = 0; i < rightArray.length; i++) {
            rightArray[i] = array[middle + i + 1];
        }

        // Copy the elements from the left and right subarrays into the array in sorted order.
        int i = 0; // The index of the left subarray.
        int j = 0; // The index of the right subarray.
        
        for (int k = start; k <= end; k++) { // The index of the array.
            // If there are still untouched elements in both the left and right subarrays, copy the minimum of the two.
            if (i < leftArray.length && j < rightArray.length) {
                if (leftArray[i] < rightArray[j]) {
                    array[k] = leftArray[i];
                    i++;
                } else {
                    array[k] = rightArray[j];
                    inversions = inversions.add(BigInteger.valueOf(1)); // There was a swap, so +1 regular inversion.
                    if (i < leftArray.length) {
                        inversions = inversions.add(BigInteger.valueOf(leftArray.length - i - 1)); // There was a jump to the right subarray while there were still elements in the left subarray, so +n split inversions, where n is the number of untouched elements remaining in the left subarray.
                    }
                    j++;
                }
            // If there are still untouched elements in only the left subarray, then copy the remaining elements from it.
            } else if (i < leftArray.length) {
                array[k] = leftArray[i];
                i++;
            // If there are still untouched elements in only the right subarray, then copy the remaining elements from it.
            } else if (j < rightArray.length) {
                array[k] = rightArray[j];
                j++;
            }
        }
        
        return inversions;
    }
}
