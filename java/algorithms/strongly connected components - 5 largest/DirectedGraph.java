
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;

public class DirectedGraph {
    
    private ArrayList<Vertex> vertices;
    private Map<Integer, Vertex> verticesHashMap;
    private ArrayList<Edge> edges;
    
    public DirectedGraph() {
        this.vertices = new ArrayList<>();
        this.verticesHashMap = new HashMap<>();
        this.edges = new ArrayList<>();
    }
    
    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }
    
    public ArrayList<Edge> getEdges() {
        return this.edges;
    }
    
    public Vertex getVertex(Vertex vertex) {
        return this.verticesHashMap.get(vertex.getName());
    }
    
    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
        this.verticesHashMap.put(vertex.getName(), vertex);
    }
    
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
    
    public boolean hasVertex(Vertex vertex) {
        return this.verticesHashMap.containsKey(vertex.getName());
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
