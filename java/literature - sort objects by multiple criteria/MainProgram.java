
import java.util.Scanner;
 
public class MainProgram {
 
    public static void main(String[] args) {
        
        // The objects that the program will use.
        BookCollection books = new BookCollection();
        Scanner scanner = new Scanner(System.in);
 
        // The user interface object.
        UserInterface ui = new UserInterface(books, scanner);
        
        // Start the program.
        ui.start();
    }
}
