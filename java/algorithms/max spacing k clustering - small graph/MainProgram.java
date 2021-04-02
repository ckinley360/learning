
import java.nio.file.Paths;
import java.util.Scanner;

public class MainProgram {
    
    public static void main(String[] args) {
        // Create the graph from the data file.
        Graph graph = readDataFromFile("smalldata.txt");
        
        // Create the min heap and add the graph's edges to it.
        MinHeap minHeap = createMinHeapFromGraph(graph);

        // Compute the k-clustering with maximum spacing.
        int maxSpacing = computeMaxSpacingKClustering(graph, minHeap, 4);
        
        // Print out the max spacing.
        System.out.println("Max spacing: " + maxSpacing);
    }
    
    public static int computeMaxSpacingKClustering(Graph graph, MinHeap minHeap, int clusterCount) {
        // Create the union-find and add the graph's vertices to it.
        UnionFind unionFind = new UnionFind(graph);
        
        // Combine the closest pair of separated points until there are the desired number of clusters..
        while (unionFind.getGroupCount() > clusterCount) {
            Edge minLengthEdge = minHeap.poll().getEdge();
            Vertex endpointOne = minLengthEdge.getEndpointOne();
            Vertex endpointTwo = minLengthEdge.getEndpointTwo();
            
            // If the endpoints of the shortest edge are in separate groups, then combine them into one group.
            if (unionFind.find(endpointOne) != unionFind.find(endpointTwo)) {
                unionFind.union(endpointOne, endpointTwo);
            }
        }
        
        // Now that we've achieved the desired number of clusters, return the shortest distance between two points in separate clusters.
        return minHeap.poll().getScore();
    }
    
    public static MinHeap createMinHeapFromGraph(Graph graph) {
        MinHeap minHeap = new MinHeap();
        
        for (Edge edge : graph.getEdges().values()) {
            EdgeScorePair pair = new EdgeScorePair(edge, edge.getLength());
            
            minHeap.add(pair);
        }
        
        return minHeap;
    }
    
    public static Graph readDataFromFile(String filePath) {
        // Create the Graph object to store the data.
        Graph graph = new Graph();
        
        // Boolean to track whether we are reading the first row, which we want to ignore because it only tells us the total number of vertices.
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
                
                // If the vertex represented by the second element of the array doesn't yet exist in the graph, then create the vertex and add it to the graph.
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
