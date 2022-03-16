
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Vertex {

    private int name;
    private ArrayList<Edge> edges;
    
    public Vertex(int name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }
    
    public int getName() {
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
        
        return this.name == other.getName();
    }
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        
        output.append(this.name + ": [");
        
        for (Edge edge:this.edges) {
            if (!edge.getEndpointOne().equals(this)) {
                output.append(edge.getEndpointOne().getName() + "|" + edge.getLength() + ", ");
            }
            
            if (!edge.getEndpointTwo().equals(this)) {
                output.append(edge.getEndpointTwo().getName() + "|" + edge.getLength() + ", ");
            }
        }
        
        // Delete the extra comma and space at the end of the string.
        output.delete(output.length() - 2, output.length());
        
        output.append("]");
        
        return output.toString();
    }
}
