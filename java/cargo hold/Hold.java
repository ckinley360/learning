import java.util.ArrayList;
 
public class Hold {
    private ArrayList<Suitcase> suitcases;
    private int maxWeight;
    
    public Hold(int maxWeight) {
        this.suitcases = new ArrayList<>();
        this.maxWeight = maxWeight;
    }
    
    public void addSuitcase(Suitcase suitcase) {
        // Calculate current weight of the hold.
        int currentWeight = 0;
        
        if (!this.suitcases.isEmpty()) {
            for (Suitcase existingSuitcase:this.suitcases) {
                currentWeight += existingSuitcase.totalWeight();
            }
        }
 
        // If adding the suitcase wouldn't cause the hold's weight to exceed its max weight, then add it to the hold.
        if (currentWeight + suitcase.totalWeight() <= this.maxWeight) {
            this.suitcases.add(suitcase);
        }
    }
    
    public void printItems() {
        for (Suitcase suitcase:suitcases) {
            suitcase.printItems();
        }
    }
    
    @Override
    public String toString() {
        // Check if the hold is empty.
        if (this.suitcases.isEmpty()) {
            return "no suitcases (0 kg)";
        }
        
        // Calculate current count and weight of suitcases in the hold.
        int suitcaseCount = 0;
        int weight = 0;
        
        for (Suitcase suitcase:this.suitcases) {
            suitcaseCount++;
            weight += suitcase.totalWeight();
        }
        
        // Output for 1 suitcase.
        if (suitcaseCount == 1) {
            return "1 suitcase (" + weight + " kg)";
        }
        
        // Output for more than 1 suitcase.
        return suitcaseCount + " suitcases (" + weight + " kg)";
    }
}
