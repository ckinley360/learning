import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        // Create the objects used in the program.
        GradeBook gradeBook = new GradeBook();
        Scanner scanner = new Scanner(System.in);
        UserInterface ui = new UserInterface(gradeBook, scanner);
        
        // Start the program.
        ui.start();
    }
}
