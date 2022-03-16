
package dictionary;
 
import java.util.HashMap;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.PrintWriter;
import java.util.ArrayList;
 
public class SaveableDictionary {
    
    private HashMap<String, String> dictionary;
    private String filePath;
    
    public SaveableDictionary() {
        this.dictionary = new HashMap<>();
    }
    
    public SaveableDictionary(String filePath) {
        this.dictionary = new HashMap<>();
        this.filePath = filePath;
    }
    
    public void add(String word, String translation) {
        // Add the word and translation to the hashmap.
        this.dictionary.putIfAbsent(word, translation);
        
        // Add the reverse to the hashmap.
        this.dictionary.putIfAbsent(translation, word);
    }
    
    public String translate(String word) {
        return this.dictionary.get(word);
    }
    
    public void delete(String word) {
        // Find the translation so we can delete it after deleting the word.
        String translation = this.dictionary.get(word);
        
        // Delete the word from the dictionary.
        this.dictionary.remove(word);
        
        // Delete the translation from the dictionary.
        this.dictionary.remove(translation);
    }
    
    public boolean load() {
        // Create a scanner for reading the file.
        try (Scanner scanner = new Scanner(Paths.get(this.filePath))) {
            
            // Read the file until all lines have been read.
            while (scanner.hasNextLine()) {
                // Read one line.
                String line = scanner.nextLine();
                // Split the line on the colon delimiter.
                String[] parts = line.split(":");
                // Add the word and translation to the dictionary.
                String word = parts[0];
                String translation = parts[1];
                this.add(word, translation);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        
        // Return true to signify that the file was successfully read and added to the dictionary.
        return true;
    }
    
    public boolean save() {
        // Create a list to track which values are written to the file.
        ArrayList<String> values = new ArrayList<>();
 
        // Create or overwrite the file.
        try (PrintWriter writer = new PrintWriter(this.filePath)) {
            // Loop through each key-value pair in the dictionary and write each unique pair to the file.
            for (String key:this.dictionary.keySet()) {
                if (!values.contains(key)) {
                    String value = this.dictionary.get(key);
                    writer.println(key + ":" + value);
                    values.add(value);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        
        return true;
    }
}
