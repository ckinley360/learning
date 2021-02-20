
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
}
