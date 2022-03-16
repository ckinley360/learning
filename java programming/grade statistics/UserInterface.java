import java.util.Scanner;
 
public class UserInterface {
    // The object that the UI will use.
    private GradeBook gradeBook;
    
    // The means by which the UI will obtain user input.
    private Scanner scanner;
    
    public UserInterface(GradeBook gradeBook, Scanner scanner) {
        this.gradeBook = gradeBook;
        this.scanner = scanner;
    }
    
    public void start() {
        // Request input from the user.
        System.out.println("Enter point totals, -1 stops:");
 
        // Obtain points from the user until user enters -1, at which point we will stop reading input.
        while (true) {
            int points = Integer.valueOf(scanner.nextLine());
            
            if (points == -1) {
                break;
            }
            
            gradeBook.addPoints(points);
        }
        
        // Print out the statistics.
        System.out.println("Point average (all): " + gradeBook.getAverage());
        
        if (gradeBook.getPassingAverage() == 0) {
            System.out.println("Point average (passing): -");  
        } else {
            System.out.println("Point average (passing): " + gradeBook.getPassingAverage());
        }
        
        System.out.println("Pass percentage: " + gradeBook.getPassingPercentage());
        
        System.out.println("Grade distribution:");
        gradeBook.printGradeDistribution();
    }
}
