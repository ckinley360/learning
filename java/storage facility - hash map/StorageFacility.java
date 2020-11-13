import java.util.HashMap;
import java.util.ArrayList;
 
public class StorageFacility {
    private HashMap<String, ArrayList<String>> storageUnits;
    
    public StorageFacility() {
        this.storageUnits = new HashMap<>();
    }
    
    public void add(String unit, String item) {
        // If unit does not already exist in the facility, then add it along with an empty list.
        if (!(this.storageUnits.containsKey(unit))) {
            this.storageUnits.put(unit, new ArrayList<>());
        }
        
        // Append the item to the list for the given unit.
        this.storageUnits.get(unit).add(item);
    }
    
    public ArrayList<String> contents(String storageUnit) {
        return this.storageUnits.getOrDefault(storageUnit, new ArrayList<>());
    }
    
    public void remove(String storageUnit, String item) {
        // If the unit exists, then remove the first occurrence of the given item from the given unit.
        if (this.storageUnits.containsKey(storageUnit)) {
            this.storageUnits.get(storageUnit).remove(item);
        }
        
        // If the unit exists and is empty, then remove the unit.
        if (this.storageUnits.containsKey(storageUnit) && this.storageUnits.get(storageUnit).isEmpty()) {
            this.storageUnits.remove(storageUnit);
        }
    }
    
    // Return a list of names of all non-empty units.
    public ArrayList<String> storageUnits() {
        // List to keep track of non-empty units.
        ArrayList<String> nonEmptyUnits = new ArrayList<>();
        
        // For each unit in the facility, check whether it is empty. If it is not empty, then add it to the non-empty list.
        for (String unit:this.storageUnits.keySet()) {
            if (!(this.storageUnits.get(unit)).isEmpty()) {
                nonEmptyUnits.add(unit);
            }
        }
        
        return nonEmptyUnits;
    }
}
