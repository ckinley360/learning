
public class Item {

    private int value;
    private int weight;
    
    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public int getWeight() {
        return this.weight;
    }
    
    public String toString() {
        return "(" + this.value + ", " + this.weight + ")"; 
    }
}
