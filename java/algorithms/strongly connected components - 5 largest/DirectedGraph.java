
import java.util.ArrayList;
import java.lang.StringBuilder;

public class DirectedGraph {
    
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    
    public DirectedGraph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
    
    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }
    
    public ArrayList<Edge> getEdges() {
        return this.edges;
    }
    
    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
    }
    
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        
        for (Vertex vertex : this.vertices) {
            output.append(vertex.toString());
            output.append("\n");
        }
        
        return output.toString();
    }
}
