import java.util.ArrayList;
 
public class Suitcase {
    private ArrayList<Item> items;
    private int maxWeight;
//    private int itemCount;
//    private int weight;
    
    public Suitcase(int maxWeight) {
        this.items = new ArrayList<>();
        this.maxWeight = maxWeight;
    }
    
    public void addItem(Item item) {
        // Calculate current weight of the suitcase.
        int currentWeight = 0;
        
        if (!this.items.isEmpty()) {
            for (Item existingItem:this.items) {
                currentWeight += existingItem.getWeight();
            }
        }
 
        // If adding the item wouldn't cause the suitcase's weight to exceed its max weight, then add it to the suitcase.
        if (currentWeight + item.getWeight() <= this.maxWeight) {
            this.items.add(item);
        }
    }
    
    public void printItems() {
        for (Item item:items) {
            System.out.println(item);
        }
    }
    
    public int totalWeight() {
        int totalWeight = 0;
        
        for (Item item:items) {
            totalWeight += item.getWeight();
        }
        
        return totalWeight;
    }
    
    public Item heaviestItem() {
        // Check to make sure the suitcase is not empty.
        if (items.isEmpty()) {
            return null;
        }
 
        // Initialize the heaviest item as the first item in the list.
        Item heaviestItem = items.get(0);
        
        // Compare the weight of the current heaviest item to all other items in the list, and update heaviest item accordingly.
        for (Item item:items) {
            if (heaviestItem.getWeight() < item.getWeight()) {
                heaviestItem = item;
            }
        }
        
        return heaviestItem;
    }
    
    @Override
    public String toString() {
        // Check if the suitcase is empty.
        if (this.items.isEmpty()) {
            return "no items (0 kg)";
        }
        
        // Calculate current count and weight of items in the suitcase.
        int itemCount = 0;
        int weight = 0;
        
        for (Item item:this.items) {
            itemCount++;
            weight += item.getWeight();
        }
        
        // Output for 1 item.
        if (itemCount == 1) {
            return "1 item (" + weight + " kg)";
        }
        
        // Output for more than 1 item.
        return itemCount + " items (" + weight + " kg)";
    }
}
