import java.util.Scanner;
 
public class UserInterface {
    
    // The object that the UI will use.
    private JokeManager jokeManager;
    
    // The means by which the UI will obtain user input.
    private Scanner scanner;
    
    public UserInterface(JokeManager jokeManager, Scanner scanner) {
        this.jokeManager = jokeManager;
        this.scanner = scanner;
    }
    
    public void start() {
        
        // Obtain and execute commands from user until "X" is entered.
        while (true) {
            // Output the possible commands to user.
            System.out.println("Commands:\n1 - add a joke\n2 - draw a joke\n3 - list jokes\nX - stop");
            
            // Obtain command from user.
            String command = scanner.nextLine();
            
            // Execute the command.
            if (command.equals("X")) {
                break;
            }
            
            if (command.equals("1")) {
                System.out.println("Write the joke to be added:");
                String joke = scanner.nextLine();
                jokeManager.addJoke(joke);
            }
            
            if (command.equals("2")) {
                System.out.println(jokeManager.drawJoke());
            }
            
            if (command.equals("3")) {
                jokeManager.printJokes();
            }
        }
    }
}
