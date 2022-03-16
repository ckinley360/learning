import java.util.Arrays;
 
public class Main {
 
    public static void main(String[] args) {
        int[] numbers = {8, 3, 7, 9, 1, 2, 4};
        MainProgram.sort(numbers);
    }
 
    public static int smallest(int[] array) {
        // Initialize the smallest number to the first number in the array.
        int smallestNumber = array[0];
        
        // Compare the current smallest number to every number in the array, updating the smallest number accordingly.
        for (int number:array) {
            if (number < smallestNumber) {
                smallestNumber = number;
            }
        }
        
        return smallestNumber;
    }
    
    public static int indexOfSmallest(int[] array) {
        // Initialize the smallest number to the first number in the array, and the index of smallest number to the first element of the array.
        int smallestNumber = array[0];
        int indexOfSmallestNumber = 0;
        
        // Compare the current smallest number to every number in the array, updating the smallest number and corresponding index accordingly.
        for (int i = 0; i <= array.length - 1; i++) {
            if (array[i] < smallestNumber) {
                smallestNumber = array[i];
                indexOfSmallestNumber = i;
            }
        }
        
        return indexOfSmallestNumber;
    }
    
    public static int indexOfSmallestFrom(int[] array, int startIndex) {
        // Initialize the smallest number to the number in position startIndex in the array, and the index of smallest number to startIndex.
        int smallestNumber = array[startIndex];
        int indexOfSmallestNumber = startIndex;
        
        // Compare the current smallest number to every number in the array, updating the smallest number and corresponding index accordingly.
        for (int i = startIndex; i <= array.length - 1; i++) {
            if (array[i] < smallestNumber) {
                smallestNumber = array[i];
                indexOfSmallestNumber = i;
            }
        }
        
        return indexOfSmallestNumber;
    }
    
    public static void swap(int[] array, int index1, int index2) {
        // Temporary storage for the address of value at first index.
        int helper = array[index1];
 
        // Do the swap.
        array[index1] = array[index2]; // array[index1] now points at the same thing as array[index2].
        array[index2] = helper; // array[index2] now points at what array[index1] used to point at.
    }
    
    public static void sort(int[] array) {
        // Pass through the array n number of times, where n is the length of the array minus one.
        for (int i = 0; i <= array.length - 1; i++) {
            System.out.println(Arrays.toString(array)); // Print the contents of the array.
            swap(array, indexOfSmallestFrom(array, i), i); // Move the smallest number in the subarray to the beginning of the subarray, where subarray is array[i] to array[array.length - 1].
        }
    }
}
