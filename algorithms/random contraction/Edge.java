
public class Edge {

    private Vertex endpointOne;
    private Vertex endpointTwo;
    
    public Edge(Vertex endpointOne, Vertex endpointTwo) {
        this.endpointOne = endpointOne;
        this.endpointTwo = endpointTwo;
    }
    
    public Vertex getEndpointOne() {
        return this.endpointOne;
    }
    
    public Vertex getEndpointTwo() {
        return this.endpointTwo;
    }
    
    public void setEndpointOne(Vertex vertex) {
        this.endpointOne = vertex;
    }
    
    public void setEndpointTwo(Vertex vertex) {
        this.endpointTwo = vertex;
    }
    
    public boolean isSelfLoop() {
        return this.endpointOne.equals(this.endpointTwo);
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
        
        return this.endpointOne.equals(other.getEndpointOne()) && this.endpointTwo.equals(other.getEndpointTwo());
    }
    
    @Override
    public String toString() {
        return ("Endpoint One: " + this.endpointOne.getName() + ", Endpoint Two: " + this.endpointTwo.getName());
    }
}
