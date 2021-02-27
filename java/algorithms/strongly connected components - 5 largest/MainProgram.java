
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

public class MainProgram {
    
    public static int finishingTime;

    public static void main(String[] args) {
        // Create the graph from the data file.
        DirectedGraph graph = readDataFromFile("data.txt");
        depthFirstSearchLoopReverse(graph);
        System.out.println(graph);
    }
    
    public static void depthFirstSearchLoopReverse(DirectedGraph graph) {
        // Create variable to set finishing time of each vertex.
        finishingTime = 0;
        
        // Store the vertices list address in vertices variable.
        ArrayList<Vertex> vertices = graph.getVertices();
        
        // Sort the vertices descending.
        Collections.sort(vertices, Collections.reverseOrder());
        
        for (int i = 0; i < vertices.size(); i++) {
            if (!vertices.get(i).isExplored()) {
                depthFirstSearchReverse(graph, i);
            }
        }
    }
    
    public static void depthFirstSearchReverse(DirectedGraph graph, Vertex startNode) {
        // Mark start node as explored.
        startNode.setExplored(true);
        
        // Explore all of start node's accessible neighbor nodes, with arcs reversed.
        for (Edge edgeHead : startNode.getEdgeHeads()) {
            if (!edgeHead.getTail().isExplored()) {
                depthFirstSearchReverse(graph, edgeHead.getTail());
            }
        }
        
        // Set the finishing time of the start node.
        finishingTime++;
        startNode.setFinishingTime(finishingTime);
    }
    
    public static DirectedGraph readDataFromFile(String filePath) {
        // Create the DirectedGraph object to store the data.
        DirectedGraph graph = new DirectedGraph();
        
        // Create a Scanner object for reading the file.
        try (Scanner scanner = new Scanner(Paths.get(filePath))) {
            
            // Read the file until all lines have been read.
            while (scanner.hasNextLine()) {
                // Read one line.
                String line = scanner.nextLine();
                // Split the line on one space..
                String[] parts = line.split(" ");
                
                // If the vertex (tail) represented by the first element of the array doesn't yet exist in the graph, then create the vertex and add it to the graph.
                Vertex tailVertex = new Vertex(Integer.valueOf(parts[0]));
                if (!graph.hasVertex(tailVertex)) {
                    graph.addVertex(tailVertex);
                }
                
                // If the vertex (head) represented by the second element of the array doesn't yet exist in the graph, then create the vertex and add it to the graph.
                Vertex headVertex = new Vertex(Integer.valueOf(parts[1]));
                if (!graph.hasVertex(headVertex)) {
                    graph.addVertex(headVertex);
                }
                    
                // Create the edge and point it at the appropriate vertices.
                Edge edge = new Edge(graph.getVertex(tailVertex), graph.getVertex(headVertex));
                    
                // Point the appropriate vertices at the edge.
                graph.getVertex(tailVertex).addEdgeTail(edge);
                graph.getVertex(headVertex).addEdgeHead(edge);
                    
                // Add the edge to the graph.
                graph.addEdge(edge);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return graph;
    }
}
