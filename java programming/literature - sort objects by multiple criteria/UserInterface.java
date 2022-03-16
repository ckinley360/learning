
import java.util.Scanner;
 
public class UserInterface {
 
    // The object that the UI will use.
    private BookCollection books;
    
    // The means by which the UI will obtain user input.
    private Scanner scanner;
    
    public UserInterface(BookCollection books, Scanner scanner) {
        this.books = books;
        this.scanner = scanner;
    }
    
    public void start() {
        // Request and obtain books from user until they enter an empty string.
        while (true) {
            System.out.print("Input the name of the book, empty stops: ");
            String name = scanner.nextLine();
            
            if (name.isEmpty()) {
                System.out.println("");
                break;
            }
            
            System.out.print("Input the age recommendation: ");
            int recommendedAge = Integer.valueOf(scanner.nextLine());
            
            books.add(new Book(name, recommendedAge));
            
            System.out.println("");
        }
        
        // Print book information.
        System.out.println(books.getBookCount() + " books in total.");
        System.out.println("");
        books.printBooks();
    }
}
