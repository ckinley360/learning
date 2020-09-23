import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        // Variables
        Scanner scan = new Scanner(System.in);
        int firstContainerVolume = 0;
        int secondContainerVolume = 0;
        int amount = 0;
 
        while (true) {
            // Output the current status of the two containers.
            System.out.println("First: " + firstContainerVolume + "/100");
            System.out.println("Second: " + secondContainerVolume + "/100");
 
            // Obtain command and parameter from user.
            String input = scan.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0];
            
            // If more than 1 argument was provided by user, then use the 2nd argument as amount.
            if (parts.length > 1) {
                amount = Integer.valueOf(parts[1]);
            }
            
            // Quit - quit the program.
            if (command.equals("quit")) {
                break;
            }
            
            // If the amount is negative, then don't process any commands.
            if (amount < 0) {
                System.out.println("");
                continue;
            }
            
            // Add - add the given amount of liquid to the first container.
            if (command.equals("add")) {
                
                if (firstContainerVolume + amount > 100) { // If adding the amount would exceed the first container's 100 liter max, then set the volume to 100.
                    firstContainerVolume = 100;
                } else {
                    firstContainerVolume += amount; // Otherwise, add the amount.
                }
                
            }
            
            // Move - move the given amount of liquid from the first container to the second container.
            if (command.equals("move")) {
               
               if (amount > firstContainerVolume) { // If the amount is greater than what the first container has, then take everything that first container has.
                   amount = firstContainerVolume;
                   firstContainerVolume = 0;
               } else {
                   firstContainerVolume -= amount; // Otherwise, remove the amount from first container.
               }
               
               if (secondContainerVolume + amount > 100) { // If moving the amount would exceed second container's max, then set the volume to 100.
                   secondContainerVolume = 100;
               } else {
                   secondContainerVolume += amount; // Otherwise, add the amount to second container.
               }
               
            }
            
            // Remove - remove the given amount of liquid from the second container.
            if (command.equals("remove")) {
                
                if (secondContainerVolume - amount < 0) { // If removing the amount would result in a negative volume, then set the volume to 0.
                    secondContainerVolume = 0;
                } else {
                    secondContainerVolume -= amount; // Otherwise, remove the amount.
                }
                
            }
            
            // Output a line break to separate each status and command block.
            System.out.println("");
        }
    }
}
