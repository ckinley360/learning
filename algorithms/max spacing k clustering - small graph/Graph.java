
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;

public class Graph {
    
    private Map<Integer, Vertex> vertices;
    private Map<String, Edge> edges;
    
    public Graph() {
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }
    
    public Map<Integer, Vertex> getVertices() {
        return this.vertices;
    }
    
    public Map<String, Edge> getEdges() {
        return this.edges;
    }
    
    public Vertex getVertex(int name) {
        return this.vertices.get(name);
    }
    
    public Edge getEdge(String name) {
        return this.edges.get(name);
    }
    
    public boolean hasVertex(Vertex vertex) {
        return this.vertices.containsKey(vertex.getName());
    }
    
    public boolean hasEdge(Edge edge) {
        return this.edges.containsKey(String.valueOf(edge.getEndpointOne().getName()) + " " + String.valueOf(edge.getEndpointTwo().getName()));
    }
    
    public void addVertex(Vertex vertex) {
        this.vertices.put(vertex.getName(), vertex);
    }
    
    public void addEdge(Edge edge) {
        this.edges.put(String.valueOf(edge.getEndpointOne().getName()) + " " + String.valueOf(edge.getEndpointTwo().getName()), edge);
    }
    
    public int getVertexCount() {
        return this.vertices.size();
    }
    
    public int getEdgeCount() {
        return this.edges.size();
    }
    
    public void removeVertex(Vertex vertex) {
        this.vertices.remove(vertex.getName());
    }
    
    public void removeEdge(Edge edge) {
        this.edges.remove(String.valueOf(edge.getEndpointOne().getName()) + " " + String.valueOf(edge.getEndpointTwo().getName()));
    }
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        
        for (Edge edge : this.edges.values()) {
            output.append(edge.toString());
            output.append("\n");
        }
        
        return output.toString();
    }
}
