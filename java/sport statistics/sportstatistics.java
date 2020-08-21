import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
 
public class SportStatistics {
 
    public static void main(String[] args) {
        // Initialize Scanner object to read user input.
        Scanner scan = new Scanner(System.in);
        
        // Request and grab filename from user.
        System.out.println("File:");
        String file = scan.nextLine();
 
        // Read the records from the file and store in a list. Result is a list of Game objects.
        ArrayList<Game> games = readRecordsFromFile(file);
        
        // Request and grab team of interest from user.
        System.out.println("Team:");
        String team = scan.nextLine();
        
        // Iterate through each Game object in the list and check if the team played, and if so, whether they won or lost.
        int gamesPlayed = 0;
        int wins = 0;
        int losses = 0;
        
        for (Game game:games) {
            // Check if the team played.
            if (game.teamPlayed(team)) {
                gamesPlayed++;
                // Since the team did play, check if they won or lost.
                if (game.getWinner().equals(team)) {
                    wins++;
                } else if (game.getLoser().equals(team)) {
                    losses++;
                }
            }
            
        }
        
        // Output the number of games played, wins, and losses for the team.
        System.out.println("Games: " + gamesPlayed);
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
    }
    
    public static ArrayList<Game> readRecordsFromFile(String file) {
        // Initialize list to store each line of file which will be converted to a Game object.
        ArrayList<Game> games = new ArrayList<>();
        
        // Create Scanner object to read the file.
        try (Scanner fileReader = new Scanner(Paths.get(file))) {
            
            // Read in each line of the file and store in the list.
            while (fileReader.hasNextLine()) {
                
                // Grab the line
                String line = fileReader.nextLine();
                
                // Split the line on comma and store the substrings in an array.
                String[] substrings = line.split(",");
                
                // User the four substrings in the array to initialize a new Game object and add to the games list.
                games.add(new Game(substrings[0], substrings[1], Integer.valueOf(substrings[2]), Integer.valueOf(substrings[3])));
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        return games;
    }
}
