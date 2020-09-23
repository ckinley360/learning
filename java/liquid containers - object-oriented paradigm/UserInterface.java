import java.util.Scanner;
 
public class UserInterface {
    // The objects that the UI will use.
    private Container container1;
    private Container container2;
    
    // The means by which the UI will obtain user input.
    private Scanner scanner;
    
    public UserInterface(Container container1, Container container2, Scanner scanner) {
        this.container1 = container1;
        this.container2 = container2;
        this.scanner = scanner;
    }
    
    public void start() {
        while (true) {
            // Output the current status of the two containers.
            System.out.println("First: " + this.container1);
            System.out.println("Second: " + this.container2);
            
            // Obtain command and parameter from user.
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0];
            int amount = 0;
            
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
                this.container1.add(amount);
            }
            
            // Move - move the given amount of liquid from the first container to the second container.
            if (command.equals("move")) {
                
                // If the amount is greater than what the first container has, then set amount equal to what the first container has.
                if (amount > this.container1.contains()) {
                    amount = this.container1.contains();
                }
                
                this.container1.remove(amount);
                this.container2.add(amount);
            }
            
            // Remove - remove the given amount of liquid from the second container.
            if (command.equals("remove")) {
                this.container2.remove(amount);
            }
            
            // Output a line break to separate each status and command block.
            System.out.println("");
        }
    }
}
