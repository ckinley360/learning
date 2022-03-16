public class Item {
    private String identifier;
    private String name;
    
    public Item(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.identifier + ": " + this.name;
    }
    
    public boolean equals(Object compared) {
        // Check if the objects have the same reference. If they do, then the objects are equal.
        if (this == compared) {
            return true;
        }
        
        // Check if the objects are of the same type. If they are not, then the objects are not equal.
        if (!(compared instanceof Item)) {
            return false;
        }
        
        // Convert compared object to type Item.
        Item comparedItem = (Item) compared;
        
        // Check the identifiers for equality. If they are equal, then the objects are equal. If they are not equal, then the objects are not equal.
        return this.identifier.equals(comparedItem.identifier);
    }
}
