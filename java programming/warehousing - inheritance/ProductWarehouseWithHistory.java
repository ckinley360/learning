public class ProductWarehouseWithHistory extends ProductWarehouse {
    private ChangeHistory changeHistory;
    
    public ProductWarehouseWithHistory(String productName, double capacity, double initialBalance) {
        super(productName, capacity);
        super.addToWarehouse(initialBalance); // Tells Java to look "up" the hierarchy, but not how many levels.
        this.changeHistory = new ChangeHistory(); // Create the ChangeHistory object.
        this.changeHistory.add(initialBalance); // Add initialBalance to the ChangeHistory object as the first value.
    }
    
    public String history() {
        return this.changeHistory.toString();
    }
    
    public void addToWarehouse(double amount) {
        super.addToWarehouse(amount); // Tells Java to look "up" the hierarchy, but not how many levels.
        this.changeHistory.add(super.getBalance()); // Tells Java to look "up" the hierarchy, but not how many levels.
    }
    
    public double takeFromWarehouse(double amount) {
        // Calculate what the balance will be after taking, and add that resulting balance to the change history.
        if (amount <= 0) {
            this.changeHistory.add(super.getBalance());
        } else if (amount >= super.getBalance()) {
            this.changeHistory.add(0.0);
        } else {
            this.changeHistory.add(super.getBalance() - amount);
        }
        
        // Return what was taken.
        return super.takeFromWarehouse(amount); // Tells Java to look "up" the hierarchy, but not how many levels.
        //this.changeHistory.add(super.getBalance()); // Tells Java to look "up" the hierarchy, but not how many levels.
    }
    
    public void printAnalysis() {
        System.out.println("Product: " + super.getName());
        System.out.println("History: " + this.history());
        System.out.println("Largest amount of product: " + this.changeHistory.maxValue());
        System.out.println("Smallest amount of product: " + this.changeHistory.minValue());
        System.out.println("Average: " + this.changeHistory.average());
    }
}
