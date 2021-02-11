
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;

public class MainProgram {

    public static void main(String[] args) {
        
        int[] numberArray = readDataFromFile("QuickSortData.txt");
//        for (int number:numberArray) {
//            System.out.println(number);
//        }

//        int[] testArray = {2, 20, 1, 15, 3, 11, 13, 6, 16, 10, 19, 5, 4, 9, 8, 14, 18, 17, 7, 12};
        int comparisonCount = 0;
        
        System.out.println(Arrays.toString(numberArray));
        comparisonCount = QuickSort(numberArray, 0, numberArray.length - 1);
        System.out.println(Arrays.toString(numberArray));
        System.out.println("Comparisons: " + comparisonCount);
    }
    
    public static int QuickSort(int[] array, int start, int end) {
        // Variable to count the number of comparisons.
        int comparisonCount = 0;

        // Base case: single-element array.
        if (start >= end) {
            return 0;
        }
        
        // Do any necessary swaps to ensure the desired pivot element is the first element of the array.
        // LAST ELEMENT.
//        int tempElement = array[end];
//        array[end] = array[start];
//        array[start] = tempElement;

        // MEDIAN-OF-THREE ELEMENT
        int middleElementIndex;
        int arrayLength = end - start + 1; 
        if (arrayLength % 2 == 0) {
            middleElementIndex = (arrayLength / 2) - 1 + start;
        } else {
            middleElementIndex = (int) Math.floor((double) arrayLength / 2) + start;
        }

        int[] threeElements = new int[3];
        threeElements[0] = array[start];
        threeElements[1] = array[middleElementIndex];
        threeElements[2] = array[end];
        
        Arrays.sort(threeElements);
        int medianElement = threeElements[1];
        
        int medianElementIndex;
        if (array[start] == medianElement) {
            medianElementIndex = start;
        } else if (array[middleElementIndex] == medianElement) {
            medianElementIndex = middleElementIndex;
        } else {
            medianElementIndex = end;
        }

        int tempElement = array[medianElementIndex];
        array[medianElementIndex] = array[start];
        array[start] = tempElement;
                
        // Partition the array around the pivot element.
        comparisonCount += end - start;
        int pivot = Partition(array, start, end);
        
        // Recursively sort the elements smaller than the pivot.
        comparisonCount += QuickSort(array, start, pivot - 1);
        
        // Recursively sort the elements bigger than the pivot.
        comparisonCount += QuickSort(array, pivot + 1, end);
        
        return comparisonCount;
    }
    
    // This method expects the pivot element to be the first element of the array passed as a parameter => array[leftBoundary].
    public static int Partition(int[] array, int leftBoundary, int rightBoundary) {
//        System.out.println("Before: " + Arrays.toString(array));

        int pivot = array[leftBoundary];
        int i = leftBoundary + 1;
        
        // Scan the entire array, starting at the element following the pivot.
        for (int j = leftBoundary + 1; j <= rightBoundary; j++) {
            // If the current element is smaller than pivot, then swap it with the left-most element that is bigger than the pivot. 
            if (array[j] < pivot) {
                int tempElement = array[i];
                array[i] = array[j];
                array[j] = tempElement;
                i++;
            }
        }
        
        // Swap the pivot with the right-most element that is smaller than the pivot.
        int tempElement = array[i - 1];
        array[i - 1] = array[leftBoundary];
        array[leftBoundary] = tempElement;
        
//        System.out.println(Arrays.toString(array));

        // Return the index of the pivot.
        return i - 1;
    }
    
    public static int[] readDataFromFile(String filePath) {
        // Create the array to store the data.
        int[] data = new int[10000];

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
}
