
package FlightControl;
 
import FlightControl.logic.FlightControl;
import java.util.Scanner;
import FlightControl.ui.TextUI;
 
public class Main {
 
    public static void main(String[] args) {
        FlightControl flightControl = new FlightControl();
        Scanner scanner = new Scanner(System.in);
        TextUI textUI = new TextUI(flightControl, scanner);
        
        textUI.start();
    }
}
