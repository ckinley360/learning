import java.util.ArrayList;
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        // Create Scanner object to read user input.
        Scanner scanner = new Scanner(System.in);
        
        // Create Item list to hold user-inputted items.
        ArrayList<Item> items = new ArrayList<>();
 
        // Request and obtain item data from user until they enter empty line.
        while (true) {
            // Request and obtain item identifier.
            System.out.println("Identifier? (empty will stop)");
            String identifier = scanner.nextLine();
            if (identifier.equals("")) {
                break;
            }
            
            // Request and obtain item name.
            System.out.println("Name? (empty will stop)");
            String name = scanner.nextLine();
            if (name.equals("")) {
                break;
            }
            
            // Create an Item object using user input as object variable values.
            Item item = new Item(identifier, name);
            
            // Check if the Item object already exists in the list (based on the identifier). If it does, then do not add it again.
            if (items.contains(item)) {
                continue;
            }
            
            // Add the Item object to the list.
            items.add(item);
        }
        
        // Print out the items in the list.
        System.out.println("==Items==");
        for (Item item:items) {
            System.out.println(item);
        }
    }
}
