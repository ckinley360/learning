import java.util.ArrayList;
import java.util.Collections;
 
public class ChangeHistory {
    private ArrayList<Double> changeList;
    
    public ChangeHistory() {
        this.changeList = new ArrayList<>();
    }
    
    public void add(double status) {
        this.changeList.add(status);
    }
    
    public void clear() {
        this.changeList.clear();
    }
    
    public double maxValue() {
        return Collections.max(this.changeList);
    }
    
    public double minValue() {
        return Collections.min(this.changeList);
    }
    
    public double average() {
        // If the change list is empty, then return 0.
        if (this.changeList.isEmpty()) {
            return 0.0;
        }
        
        // Calculate the average.
        double sum = 0.0;
        double count = 0.0;
        
        for (Double status:this.changeList) {
            sum += status;
            count++;
        }
        
        return sum / count;
    }
    
    @Override
    public String toString() {
        return this.changeList.toString();
    }
}
