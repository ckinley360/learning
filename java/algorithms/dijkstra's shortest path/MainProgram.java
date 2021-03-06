
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class MainProgram {

    public static void main(String[] args) {
        // Create the graph from the data file.
        Graph graph = readDataFromFile("data.txt");
        
        // Create the min heap and add the graph's vertices to it.
        MinHeap minHeap = createMinHeapFromGraph(graph, 1);
        
        // Print the vertices in the min heap in ascending order.
//        for (int i = 1; i <= 200; i++) {
//            System.out.println(minHeap.poll().getVertex().getName());
//        }

        // Compute the shortest path distances.
        Map<Integer, Integer> shortestPathDistances = computeShortestPathDistances(minHeap);
        System.out.println(shortestPathDistances.get(1));
    }
    
    public static Map<Integer, Integer> computeShortestPathDistances(MinHeap minHeap) {
        Map<Integer, Integer> shortestPathDistances = new HashMap<>();
        
        while (!minHeap.isEmpty()) {
            // Grab the node with the lowest Dijkstra greedy score.
            VertexScorePair pair = minHeap.poll();
            
            // Create HashMap to track which nodes we need to recompute Djikstra greedy score for.
            
            
            // Add the node and its Dijkstra greedy score to the hash map to track its distance from the start node.
            shortestPathDistances.put(pair.getVertex().getName(), pair.getScore());
            
            // For every node "across the frontier" that is adjacent to the node we just grabbed, recompute its Dijkstra greedy score.
            for (Edge edge : pair.getVertex().getEdges()) {
                if (!shortestPathDistances.containsKey(edge.getEndpointOne().getName())) {
                    // Recompute greedy score
                }
                
                if (!shortestPathDistances.containsKey(edge.getEndpointTwo().getName())) {
                    // Recompute greedy score
                }
            }
        }
        
        return shortestPathDistances;
    }
    
    public static MinHeap createMinHeapFromGraph(Graph graph, int startVertexName) {
        MinHeap minHeap = new MinHeap();
        
        for (Vertex vertex : graph.getVertices().values()) {
            VertexScorePair pair = new VertexScorePair(null, 1000000);
            pair.setVertex(vertex);
            
            if (vertex.getName() == startVertexName) {
                pair.setScore(0);
            }
            
            minHeap.add(pair);
        }
        
        return minHeap;
    }
    
    public static Graph readDataFromFile(String filePath) {
        // Create the Graph object to store the data.
        Graph graph = new Graph();
        
        // Create a Scanner object for reading the file.
        try (Scanner scanner = new Scanner(Paths.get(filePath))) {
            
            // Read the file until all lines have been read.
            while (scanner.hasNextLine()) {
                // Read one line.
                String line = scanner.nextLine();
                // Split the line on the tab character.
                String[] parts = line.split("\t");
                
                // If the vertex represented by the first element of the array doesn't yet exist in the graph, then create the vertex and add it to the graph.
                Vertex vertex = new Vertex(Integer.valueOf(parts[0]));
                if (!graph.hasVertex(vertex)) {
                    graph.addVertex(vertex);
                }
                
                // Iterate through the rest of the array (vertex-length pairs), create a Vertex object for each adjacent vertex as needed, and connect edges with the appropriate length from the adjacent vertices to the vertex represented by the first element of the array.
                for (int i = 1; i <= parts.length - 1; i++) {
                    String[] pair = parts[i].split(",");
                    
                    Vertex adjacentVertex = new Vertex(Integer.valueOf(pair[0]));
                    int edgeLength = Integer.valueOf(pair[1]);
                    
                    if (!graph.hasVertex(adjacentVertex)) {
                        graph.addVertex(adjacentVertex);
                    }
                    
                    // Create the edge and point it at the appropriate vertices.
                    Edge edge = new Edge(graph.getVertex(vertex.getName()), graph.getVertex(adjacentVertex.getName()), edgeLength);
                    
                    // If the edge already exists in the graph, that means that this specific connection between vertex and adjacent vertex already exists in the graph. Do not add a duplicate.
                    if (!graph.hasEdge(edge)) {
                        // Point the appropriate vertices at the edge.
                        graph.getVertex(vertex.getName()).addEdge(edge);
                        graph.getVertex(adjacentVertex.getName()).addEdge(edge);
                    
                        // Add the edge to the graph.
                        graph.addEdge(edge);
                    }      
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return graph;
    }
}
