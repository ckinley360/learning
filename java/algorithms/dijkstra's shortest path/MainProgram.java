
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;

public class MainProgram {

    public static void main(String[] args) {
        // Create the graph from the data file.
        Graph graph = readDataFromFile("data.txt");
        
        // Create the min heap and add the graph's vertices to it.
        MinHeap minHeap = createMinHeapFromGraph(graph, 1);

        // Compute the shortest path distances.
        Map<Integer, Integer> shortestPathDistances = computeShortestPathDistances(minHeap);
        
        // Output the shortest paths to the target vertices.
        StringBuilder output = new StringBuilder();
        output.append(shortestPathDistances.get(7) + ",");
        output.append(shortestPathDistances.get(37) + ",");
        output.append(shortestPathDistances.get(59) + ",");
        output.append(shortestPathDistances.get(82) + ",");
        output.append(shortestPathDistances.get(99) + ",");
        output.append(shortestPathDistances.get(115) + ",");
        output.append(shortestPathDistances.get(133) + ",");
        output.append(shortestPathDistances.get(165) + ",");
        output.append(shortestPathDistances.get(188) + ",");
        output.append(shortestPathDistances.get(197));
        System.out.println(output.toString());
    }
    
    public static Map<Integer, Integer> computeShortestPathDistances(MinHeap minHeap) {
        Map<Integer, Integer> shortestPathDistances = new HashMap<>();
        
        while (!minHeap.isEmpty()) {
            // Grab the node with the lowest Dijkstra greedy score.
            VertexScorePair pair = minHeap.poll();
            
            // Add the node and its Dijkstra greedy score to the hash map to track its distance from the start node.
            shortestPathDistances.put(pair.getVertex().getName(), pair.getScore());
            
            // Create HashMap to track which nodes we need to recompute Djikstra greedy score for.
            Map<Integer, Integer> adjacentVertices = new HashMap<>();
            
            // For every node "across the frontier" that is adjacent to the node we just grabbed, recompute its Dijkstra greedy score.
            for (Edge edge : pair.getVertex().getEdges()) {
                if (!shortestPathDistances.containsKey(edge.getEndpointOne().getName())) {
                    // Add the adjacent node to the adjacentVertices hashmap so we know it needs to have its Dijkstra greedy score recomputed.
                    adjacentVertices.put(edge.getEndpointOne().getName(), 0);
                }
                
                if (!shortestPathDistances.containsKey(edge.getEndpointTwo().getName())) {
                    // Add the adjacent node to the adjacentVertices hashmap so we know it needs to have its Dijkstra greedy score recomputed.
                    adjacentVertices.put(edge.getEndpointTwo().getName(), 0);
                }
            }
            
            // Recompute the greedy scores for the necessary vertices.
            for (int vertexName : adjacentVertices.keySet()) {
                VertexScorePair adjacentPair = minHeap.getPair(minHeap.getIndex(vertexName));
                Vertex adjacentVertex = adjacentPair.getVertex();
                MinHeap localMinHeap = new MinHeap();
                
                for (Edge edge : adjacentVertex.getEdges()) {
                    if (shortestPathDistances.containsKey(edge.getEndpointOne().getName())) { // Need to look at ALL connected vertices in X, not just the one that just got Polled.
                        VertexScorePair localContestant = new VertexScorePair(edge.getEndpointOne(), edge.getLength() + shortestPathDistances.get(edge.getEndpointOne().getName())); // Need to add the score of the corresponding vertex in X.
                        localMinHeap.add(localContestant);
                    }
                    
                    if (shortestPathDistances.containsKey(edge.getEndpointTwo().getName())) {
                        VertexScorePair localContestant = new VertexScorePair(edge.getEndpointTwo(), edge.getLength() + shortestPathDistances.get(edge.getEndpointTwo().getName())); // Need to add the score of the corresponding vertex in X.
                        localMinHeap.add(localContestant);
                    }
                }
                
                adjacentPair.setScore(localMinHeap.poll().getScore());
                
                // Delete the pair from the min heap and re-add it to maintain the heap property.
                minHeap.deleteFromMiddle(minHeap.getIndex(vertexName));
                minHeap.add(adjacentPair);
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
