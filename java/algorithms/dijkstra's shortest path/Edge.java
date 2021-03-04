
public class Edge {

    private Vertex endpointOne;
    private Vertex endpointTwo;
    private int length;
    
    public Edge(Vertex endpointOne, Vertex endpointTwo, int length) {
        this.endpointOne = endpointOne;
        this.endpointTwo = endpointTwo;
        this.length = length;
    }
    
    public Vertex getEndpointOne() {
        return this.endpointOne;
    }
    
    public Vertex getEndpointTwo() {
        return this.endpointTwo;
    }
    
    public int getLength() {
        return this.length;
    }
    
    public void setEndpointOne(Vertex vertex) {
        this.endpointOne = vertex;
    }
    
    public void setEndpointTwo(Vertex vertex) {
        this.endpointTwo = vertex;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    
    @Override
    public boolean equals(Object obj) {
        // Compare the references.
        if (this == obj) {
            return true;
        }
        
        // Ensure the object is not null.
        if (obj == null) {
            return false;
        }
        
        // Ensure the object is of Edge class.
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        // Convert the object to Edge type, and compare the names.
        Edge other = (Edge) obj;
        
        return ((this.endpointOne.equals(other.getEndpointOne()) && this.endpointTwo.equals(other.getEndpointTwo())) || (this.endpointOne.equals(other.getEndpointTwo()) && this.endpointTwo.equals(other.getEndpointOne()))) && this.length == other.getLength();
    }
    
    @Override
    public String toString() {
        return ("Endpoint One: " + this.endpointOne.getName() + ", Endpoint Two: " + this.endpointTwo.getName());
    }
}
