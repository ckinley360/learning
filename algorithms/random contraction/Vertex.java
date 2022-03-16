
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Vertex {

    private String name;
    private ArrayList<Edge> edges;
    
    public Vertex(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }
    
    public String getName() {
        return this.name;
    }
    
    public ArrayList<Edge> getEdges() {
        return this.edges;
    }
    
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
    
    public void removeEdge(Edge edge) {
        this.edges.remove(edge);
    }
    
    public void transferEdges(Vertex transferToVertex) {
        for (Edge edge:this.edges) {
            
            // If needed, add current edge to the transferToVertex.
            if (edge.getEndpointOne().equals(this) || edge.getEndpointTwo().equals(this)) {
                transferToVertex.addEdge(edge);
            }
            
            if (edge.getEndpointOne().equals(this)) {
                edge.setEndpointOne(transferToVertex);
            }
            
            if (edge.getEndpointTwo().equals(this)) {
                edge.setEndpointTwo(transferToVertex);
            }
        }
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
        
        // Ensure the object is of Vertex class.
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        // Convert the object to Vertex type, and compare the names.
        Vertex other = (Vertex) obj;
        
        return this.name.equals(other.getName());
    }
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        
        output.append(this.name + ": [");
        
        for (Edge edge:this.edges) {
            if (!edge.getEndpointOne().equals(this)) {
                output.append(edge.getEndpointOne().getName() + ", ");
            }
            
            if (!edge.getEndpointTwo().equals(this)) {
                output.append(edge.getEndpointTwo().getName() + ", ");
            }
        }
        
        // Delete the extra comma and space at the end of the string.
        output.delete(output.length() - 2, output.length());
        
        output.append("]");
        
        return output.toString();
    }
}
