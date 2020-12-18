
package FlightControl.logic;
 
import java.util.HashMap;
import FlightControl.domain.Airplane;
import FlightControl.domain.Place;
import FlightControl.domain.Flight;
import java.util.Collection;
 
public class FlightControl {
 
    private HashMap<String, Airplane> airplanes;
    private HashMap<String, Place> places;
    private HashMap<String, Flight> flights;
    
    public FlightControl() {
        this.airplanes = new HashMap<>();
        this.places = new HashMap<>();
        this.flights = new HashMap<>();
    }
    
    public void addAirplane(String id, int capacity) {
        // Create an Airplane object.
        Airplane airplane = new Airplane(id, capacity);
        
        // Add the Airplane to the hash map.
        this.airplanes.put(id, airplane);
    }
    
    public void addFlight(Airplane airplane, String departurePlaceID, String targetPlaceID) {
        // If the departure place and target place don't exist in the places hash map, then add them.
        this.places.putIfAbsent(departurePlaceID, new Place(departurePlaceID));
        this.places.putIfAbsent(targetPlaceID, new Place(targetPlaceID));
        
        // Create a Flight object.
        Flight flight = new Flight(airplane, this.places.get(departurePlaceID), this.places.get(targetPlaceID));
        
        // Add the Flight object to the flights hash map.
        this.flights.put(flight.toString(), flight);
    }
    
    public Collection<Airplane> getAirplanes() {
        return this.airplanes.values();
    }
    
    public Collection<Flight> getFlights() {
        return this.flights.values();
    }
    
    public Airplane getAirplane(String id) {
        return this.airplanes.get(id);
    }
}
