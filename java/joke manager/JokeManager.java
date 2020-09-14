import java.util.ArrayList;
import java.util.Random;
 
public class JokeManager {
    
    private ArrayList<String> jokes;
    
    public JokeManager() {
        this.jokes = new ArrayList<>();
    }
    
    public void addJoke(String joke) {
        jokes.add(joke);
    }
    
    public String drawJoke() {
        // Check if joke list is empty.
        if (jokes.isEmpty()) {
            return "Jokes are in short supply.";
        }
        
        // Grab a joke from the list at random.
        Random joke = new Random();
        int index = joke.nextInt(jokes.size());
        
        return jokes.get(index);
    }
    
    public void printJokes() {
        for (String joke:jokes) {
            System.out.println(joke);
        }
    }
}
