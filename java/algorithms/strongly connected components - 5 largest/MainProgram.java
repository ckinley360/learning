
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

public class MainProgram {
    
    public static int finishingTime;
    public static Vertex leader;

    public static void main(String[] args) {
        // Create the graph from the data file.
        DirectedGraph graph = readDataFromFile("data.txt");
        
        // Run Kosaraju's two-pass algorithm.
        depthFirstSearchLoopReverse(graph);
        depthFirstSearchLoop(graph);
        
        // Calculate and display the top five SCC sizes.
        ArrayList<Integer> topFiveSccSizes = graph.getFiveLargestSccs();
        for (int size : topFiveSccSizes) {
            System.out.println(size);
        }
    }
    
    public static void depthFirstSearchLoop(DirectedGraph graph) {
        // Initialize leader to null.
        leader = null;
        
        // Store the vertices list address in vertices variable.
        ArrayList<Vertex> vertices = graph.getVertices();
        
        // Sort the vertices descending.
        Collections.sort(vertices, Collections.reverseOrder());
        
        // Call depth first search on every vertex in the graph. Reset finishing time to 0, which will be used to store the size of each SCC.
        for (int i = 0; i < vertices.size(); i++) {
            finishingTime = 0;
            if (!vertices.get(i).isExplored()) {
                leader = vertices.get(i);
                depthFirstSearch(graph, vertices.get(i));
            }
        }
    }
    
    public static void depthFirstSearch(DirectedGraph graph, Vertex startNode) {
        // Mark start node as explored.
        startNode.setExplored(true);
        
        // Set the leader of start node.
        startNode.setLeader(leader);
        
        // Explore all of start node's accessible neighbor nodes.
        for (Edge edgeTail : startNode.getEdgeTails()) {
            if (!edgeTail.getHead().isExplored()) {
                depthFirstSearch(graph, edgeTail.getHead());
            }
        }
        
        // Set the SCC size of the start node.
        finishingTime++;
        startNode.setSccSize(finishingTime);
    }
    
    public static void depthFirstSearchLoopReverse(DirectedGraph graph) {
        // Initialize finishing time to 0.
        finishingTime = 0;
        
        // Store the vertices list address in vertices variable.
        ArrayList<Vertex> vertices = graph.getVertices();
        
        // Sort the vertices descending.
        Collections.sort(vertices, Collections.reverseOrder());
        
        // Call depth first search reverse on every vertex in the graph.
        for (int i = 0; i < vertices.size(); i++) {
            if (!vertices.get(i).isExplored()) {
                depthFirstSearchReverse(graph, vertices.get(i));
            }
        }
        
        // Set all vertices back to unexplored.
        graph.setVerticesExplored(false);
        
        // Set all vertices' names to their finishing time.
        graph.setVerticesNamesToFinishingTime();
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
