
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;
import java.util.Collections;

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
    
    public void setVerticesExplored(boolean explored) {
        for (Vertex vertex : this.vertices) {
            vertex.setExplored(explored);
        }
    }
    
    public void setVerticesNamesToFinishingTime() {
        for (Vertex vertex : this.vertices) {
            vertex.setNameToFinishingTime();
        }
    }
    
    public ArrayList<Integer> getFiveLargestSccs() {
        // Store the size of each SCC in sccSizes list.
        ArrayList<Integer> sccSizes = new ArrayList<>();
        
        for (Vertex vertex : this.vertices) {
            if (vertex.isLeader()) {
                sccSizes.add(vertex.getSccSize());
            }
        }
        
        // Sort the sccSizes list descending.
        Collections.sort(sccSizes, Collections.reverseOrder());
        
        // Store the top 5 largest SCCs in topFiveSccSizes list.
        ArrayList<Integer> topFiveSccSizes = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            topFiveSccSizes.add(sccSizes.get(i));
        }
        
        return topFiveSccSizes;
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
