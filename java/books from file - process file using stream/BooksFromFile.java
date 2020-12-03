
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
 
public class BooksFromFile {
    
    public static void main(String[] args) {
        System.out.println(readBooks("books.txt"));
    }
 
    public static List<Book> readBooks(String file) {
        // Create the list that will store the Book objects.
        ArrayList<Book> books = new ArrayList<>();
        
        // Read the lines of the file into the books list.
        try {
            // Create a stream from the file.
            Files.lines(Paths.get(file))
                    // Split each row into parts using "," as the delimiter.
                    .map(row -> row.split(","))
                    // Delete rows that have less than 4 parts, since we want all 4 object variables of each Book object to have values.
                    .filter(parts -> parts.length >= 4)
                    // Create Book objects from the parts.
                    .map(parts -> new Book(parts[0], Integer.valueOf(parts[1]), Integer.valueOf(parts[2]), parts[3]))
                    // Add the Book objects to the books list.
                    .forEach(book -> books.add(book));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return books;
    }
}
