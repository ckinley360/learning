
package FlightControl.domain;
 
public class Place {
    
    private String id;
    
    public Place(String id) {
        this.id = id;
    }
    
    public String getID() {
        return this.id;
    }
    
    @Override
    public String toString() {
        return this.id;
    }
}
