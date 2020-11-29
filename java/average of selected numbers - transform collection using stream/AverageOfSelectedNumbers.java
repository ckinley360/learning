
import java.util.ArrayList;
import java.util.Scanner;
 
public class AverageOfSelectedNumbers {
 
    public static void main(String[] args) {
        
        // Create the objects used by the program.
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<>();
 
        // Instruct user to enter numbers.
        System.out.println("Input numbers, type \"end\" to stop.");
        
        // Obtain user input until they enter "end".
        while (true) {
            String input = scanner.nextLine();
            
            if (input.equals("end")) {
                break;
            }
            
            inputs.add(input);
        }
        
        // Ask user whether they want the average of positive or negative numbers.
        System.out.println("Print the average of the negative numbers or the positive numbers? (n/p)");
        boolean userWantsNegative = (scanner.nextLine().equals("n"));
        
        // Calculate the average of the numbers requested by user.
        double average;
        
        if (userWantsNegative) {
            average = inputs.stream()
                    .mapToInt(n -> Integer.valueOf(n))
                    .filter(n -> n < 0)
                    .average()
                    .getAsDouble();
        } else {
            average = inputs.stream()
                    .mapToInt(n -> Integer.valueOf(n))
                    .filter(n -> n >= 0)
                    .average()
                    .getAsDouble();
        }
        
        // Output the average.
        System.out.println("Average of the negative numbers: " + average);
        
    }
}
