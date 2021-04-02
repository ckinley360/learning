
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;

public class Graph {
    
    private Map<Integer, Vertex> vertices;
    private Map<Integer, Edge> edges;
    
    public Graph() {
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }
    
    public Map<Integer, Vertex> getVertices() {
        return this.vertices;
    }
    
    public Map<Integer, Edge> getEdges() {
        return this.edges;
    }
    
    public Vertex getVertex(int name) {
        return this.vertices.get(name);
    }
    
    public Edge getEdge(int name) {
        return this.edges.get(name);
    }
    
    public boolean hasVertex(Vertex vertex) {
        return this.vertices.containsKey(vertex.getName());
    }
    
    public boolean hasEdge(Edge edge) {
        return this.edges.containsKey(Integer.valueOf(String.valueOf(edge.getEndpointOne().getName()) + String.valueOf(edge.getEndpointTwo().getName())));
    }
    
    public void addVertex(Vertex vertex) {
        this.vertices.put(vertex.getName(), vertex);
    }
    
    public void addEdge(Edge edge) {
        this.edges.put(Integer.valueOf(String.valueOf(edge.getEndpointOne().getName()) + String.valueOf(edge.getEndpointTwo().getName())), edge);
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
        // Remove the first instance of the edge from the edge list.
        this.edges.remove(Integer.valueOf(String.valueOf(edge.getEndpointOne().getName()) + String.valueOf(edge.getEndpointTwo().getName())));
    }
    
    // Return the name of the group (leader name) that the vertex with specified name belongs to.
    
    // Fuse the connected components (give them all the same leader) of vertexOne and vertexTwo.
    
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
