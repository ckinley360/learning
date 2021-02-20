
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;

public class Graph {
    
    private Map<String, Vertex> vertices;
    private ArrayList<Edge> edges;
    
    public Graph() {
        this.vertices = new HashMap<>();
        this.edges = new ArrayList<>();
    }
    
    public Map<String, Vertex> getVertices() {
        return this.vertices;
    }
    
    public ArrayList<Edge> getEdges() {
        return this.edges;
    }
    
    public Vertex getVertex(String name) {
        return this.vertices.get(name);
    }
    
    public Edge getEdge(int index) {
        return this.edges.get(index);
    }
    
    public boolean hasVertex(Vertex vertex) {
        return this.vertices.containsKey(vertex.getName());
    }
    
    public void addVertex(Vertex vertex) {
        this.vertices.put(vertex.getName(), vertex);
    }
    
    public void addEdge(Edge edge) {
        this.edges.add(edge);
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
        this.edges.remove(edge);
    }
    
    public void removeSelfLoops() {
        for (Edge edge:this.edges) {
            if (edge.isSelfLoop()) {
                
                this.getVertex(edge.getEndpointOne().getName()).removeEdge(edge);
                
                this.removeEdge(edge);
            }
        }
    }
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        
        for (Vertex vertex:this.vertices.values()) {
            output.append(vertex.toString());
            output.append("\n");
        }
        
        return output.toString();
    }
}
