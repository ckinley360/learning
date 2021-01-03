
import java.util.ArrayList;
import java.util.Random;
 
public class LotteryRow {
 
    private ArrayList<Integer> numbers;
 
    public LotteryRow() {
        // Draw the numbers when the LotteryRow is created
        this.randomizeNumbers();
    }
 
    public ArrayList<Integer> numbers() {
        return this.numbers;
    }
 
    public void randomizeNumbers() {
        // Initialize the list for numbers
        this.numbers = new ArrayList<>();
        
        // Create the Random object.
        Random numberGenerator = new Random();
        
        // Draw random numbers and store them in the ArrayList until it has 7 unique numbers..
        while(this.numbers.size() < 7) {
            int randomNumber = numberGenerator.nextInt(40) + 1;
            
            if (!this.numbers.contains(randomNumber)) {
                this.numbers.add(randomNumber);
            }
        }
    }
}
