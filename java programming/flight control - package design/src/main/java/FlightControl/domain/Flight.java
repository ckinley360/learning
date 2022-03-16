
package FlightControl.domain;
 
public class Flight {
    
    private Airplane airplane;
    private Place departurePlace;
    private Place targetPlace;
    
    public Flight(Airplane airplane, Place departurePlace, Place targetPlace) {
        this.airplane = airplane;
        this.departurePlace = departurePlace;
        this.targetPlace = targetPlace;
    }
    
    public Airplane getAirplane() {
        return this.airplane;
    }
    
    public Place getDeparturePlace() {
        return this.departurePlace;
    }
    
    public Place getTargetPlace() {
        return this.targetPlace;
    }
    
    @Override
    public String toString() {
        return this.airplane.toString() + " (" + this.departurePlace + "-" + this.targetPlace + ")";
    }
}
