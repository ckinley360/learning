import java.util.ArrayList;
 
public class BoxWithMaxWeight extends Box {
    private ArrayList<Item> itemList;
    private int capacity;
    //private int currentWeight;
    
    public BoxWithMaxWeight(int capacity) {
        this.itemList = new ArrayList<>();
        this.capacity = capacity;
        //this.currentWeight = 0;
    }
    
    @Override
    public void add(Item item) {
        // Calculate the current weight of the box.
        int currentWeight = 0;
        for (Item currentItem:this.itemList) {
            currentWeight += currentItem.getWeight();
        }
        
        // If adding the item will not cause the box's capacity to be exceeded, then add it.
        if (currentWeight + item.getWeight() <= this.capacity) {
            this.itemList.add(item);
        }
    }
    
    @Override
    public boolean isInBox(Item item) {
        return this.itemList.contains(item);
    }
}
