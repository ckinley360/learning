import java.util.ArrayList;
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
     
        // Create the specified number of book objects.
        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        System.out.println("How many books to create?");
        int numberOfBooks = Integer.valueOf(scanner.nextLine());
        for (int i = 0; i < numberOfBooks; i++) {
            books.add(new Book(i, "name for the book " + i));
        }
 
        // Obtain the book ID that the user wants to search for.
        System.out.println("Id of the book to search for?");
        int idToSearchFor = Integer.valueOf(scanner.nextLine());
 
        // Search for the ID using linear search.
        System.out.println("");
        System.out.println("Searching with linear search:");
        long start = System.currentTimeMillis();
        int linearSearchId = linearSearch(books, idToSearchFor);
        System.out.println("The search took " + (System.currentTimeMillis() - start) + " milliseconds.");
        if (linearSearchId < 0) {
            System.out.println("Book not found");
        } else {
            System.out.println("Found it! " + books.get(linearSearchId));
        }
 
        System.out.println("");
 
        // Search for the ID using binary search.
        System.out.println("");
        System.out.println("Seaching with binary search:");
        start = System.currentTimeMillis();
        int binarySearchId = binarySearch(books, idToSearchFor);
        System.out.println("The search took " + (System.currentTimeMillis() - start) + " milliseconds.");
        if (binarySearchId < 0) {
            System.out.println("Book not found");
        } else {
            System.out.println("Found it! " + books.get(binarySearchId));
        }
 
    }
 
    public static int linearSearch(ArrayList<Book> books, int searchedId) {
        // Compare the searchedId to each element of the list, one by one. If found, return the index. If not found, return -1.
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == searchedId) {
                return i;
            }
        }
        
        return -1;
    }
 
    public static int binarySearch(ArrayList<Book> books, long searchedId) {
        int begin = 0; // First index in the list.
        int end = books.size() - 1; // Last index in the list.
        int middle; // Middle index in the list.
        
        // Search for the value until there is no longer a search area.
        while (begin <= end) {
            middle = (int) Math.floor((begin + end) / 2);
            
            // We have a match! Return the index of the match.
            if (books.get(middle).getId() == searchedId) {
                return middle;
            }
            
            // The value is smaller than what we're looking for. Start the next search at middle index + 1.
            if (books.get(middle).getId() < searchedId) {
                begin = middle + 1;
            }
            
            // The value is larger than what we're looking for. End the next search at middle index - 1.
            if (books.get(middle).getId() > searchedId) {
                end = middle - 1;
            }
        }
        
        // We did not find the value. Return -1 to indicate this.
        return -1;
    }
}
