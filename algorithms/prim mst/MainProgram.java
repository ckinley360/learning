
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class MainProgram {

    public static void main(String[] args) {
        // Create the graph from the data file.
        Graph graph = readDataFromFile("edges.txt");
        
        // Create the min heap and add the graph's vertices to it.
        MinHeap minHeap = createMinHeapFromGraph(graph, 1);

        // Compute the MST cost.
        int mstCost = computeMstCost(minHeap);
        
        // Output the MST cost.
        System.out.println("MST Cost: " + mstCost);
    }
    
    public static int computeMstCost(MinHeap minHeap) {
        // To track the overall cost of the MST.
        int mstCost = 0;
        
        // To track which nodes have been spanned by the MST. The Boolean value is arbitrary.
        Map<Integer, Boolean> spannedVertices = new HashMap<>();
        
        while (!minHeap.isEmpty()) {
            // Grab the node with the lowest edge length.
            VertexScorePair pair = minHeap.poll();
            
            // Add the node's score to the mstCost tracker, and add the node and its score to the hash map so we know it has been spanned.
            mstCost += pair.getScore();
            spannedVertices.put(pair.getVertex().getName(), true);
            
            // Create HashMap to track which nodes we need to recompute the score for. The Boolean value is arbitrary.
            Map<Integer, Boolean> adjacentVertices = new HashMap<>();
            
            // For every node "across the frontier" that is adjacent to the node we just grabbed, recompute its score.
            for (Edge edge : pair.getVertex().getEdges()) {
                if (!spannedVertices.containsKey(edge.getEndpointOne().getName())) {
                    // Add the adjacent node to the adjacentVertices hashmap so we know it needs to have its score recomputed.
                    adjacentVertices.put(edge.getEndpointOne().getName(), true);
                }
                
                if (!spannedVertices.containsKey(edge.getEndpointTwo().getName())) {
                    // Add the adjacent node to the adjacentVertices hashmap so we know it needs to have its score recomputed.
                    adjacentVertices.put(edge.getEndpointTwo().getName(), true);
                }
            }
            
            // Recompute the scores for the necessary vertices.
            for (int vertexName : adjacentVertices.keySet()) {
                VertexScorePair adjacentPair = minHeap.getPair(minHeap.getIndex(vertexName));
                Vertex adjacentVertex = adjacentPair.getVertex();
                MinHeap localMinHeap = new MinHeap();
                
                for (Edge edge : adjacentVertex.getEdges()) {
                    if (spannedVertices.containsKey(edge.getEndpointOne().getName())) { // Need to look at ALL connected vertices in X, not just the one that just got Polled.
                        VertexScorePair localContestant = new VertexScorePair(edge.getEndpointOne(), edge.getLength());
                        localMinHeap.add(localContestant);
                    }
                    
                    if (spannedVertices.containsKey(edge.getEndpointTwo().getName())) {
                        VertexScorePair localContestant = new VertexScorePair(edge.getEndpointTwo(), edge.getLength());
                        localMinHeap.add(localContestant);
                    }
                }
                
                adjacentPair.setScore(localMinHeap.poll().getScore());
                
                // Delete the pair from the min heap and re-add it to maintain the heap property.
                minHeap.deleteFromMiddle(minHeap.getIndex(vertexName));
                minHeap.add(adjacentPair);
            }
        }
        
        return mstCost;
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
        
        // Boolean to track whether we are reading the first row, which we want to ignore because it only tells us the total number of vertices and edges.
        Boolean firstRow = true;
        
        // Create a Scanner object for reading the file.
        try (Scanner scanner = new Scanner(Paths.get(filePath))) {
            
            // Read the file until all lines have been read.
            while (scanner.hasNextLine()) {
                // Read one line.
                String line = scanner.nextLine();
                
                // Extract the data from the row only if it is not the first row.
                if (firstRow) {
                    firstRow = false;
                    continue;
                }
                
                // Split the line on the space character.
                String[] parts = line.split(" ");
                
                // If the vertex represented by the first element of the array doesn't yet exist in the graph, then create the vertex and add it to the graph.
                Vertex vertex = new Vertex(Integer.valueOf(parts[0]));
                if (!graph.hasVertex(vertex)) {
                    graph.addVertex(vertex);
                }
                
                // If the Vertex represented by the second element of the array doesn't yet exist in the graph, then create the vertex and add it to the graph.
                Vertex adjacentVertex = new Vertex(Integer.valueOf(parts[1]));
                if (!graph.hasVertex(adjacentVertex)) {
                    graph.addVertex(adjacentVertex);
                }
                    
                // Create the edge with weight represented by the third element of the array, and point it at the vertex and adjacent vertex.
                int edgeLength = Integer.valueOf(parts[2]);
                Edge edge = new Edge(graph.getVertex(vertex.getName()), graph.getVertex(adjacentVertex.getName()), edgeLength);
                    
                // If the edge already exists in the graph, that means that this specific connection between vertex and adjacent vertex already exists in the graph. Do not add a duplicate.
                if (!graph.hasEdge(edge)) {
                    // Point the vertex and adjacent vertex at the edge.
                    graph.getVertex(vertex.getName()).addEdge(edge);
                    graph.getVertex(adjacentVertex.getName()).addEdge(edge);
                    
                    // Add the edge to the graph.
                    graph.addEdge(edge);
                }      
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return graph;
    }
}
