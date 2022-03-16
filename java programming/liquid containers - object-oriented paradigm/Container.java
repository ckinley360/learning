public class Container {
    private int amount;
    private int max;
    
    public Container() {
        this.amount = 0;
        this.max = 100;
    }
    
    public int contains() {
        return this.amount;
    }
    
    public void add(int amount) {
        // If the amount is negative, then do nothing.
        if (amount < 0) {
            return;
        }
        
        if (this.amount + amount > this.max) { // If adding the amount would cause the container to overflow, then set the amount equal to the max.
            this.amount = this.max;
        } else {
            this.amount += amount; // Otherwise, add the amount to the container.
        }
    }
    
    public void remove(int amount) {
        // If the amount is negative, then do nothing.
        if (amount < 0) {
            return;
        }
        
        if (this.amount - amount < 0) { // If subtracting the amount would result in a negative amount, then set the amount equal to 0.
            this.amount = 0;
        } else {
            this.amount -= amount; // Otherwise, subtract the amount.
        }
    }
    
    public String toString() {
        return this.amount + "/100";
    }
}
