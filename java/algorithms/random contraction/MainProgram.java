
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Random;
import java.sql.Timestamp;
import java.util.Arrays;

public class MainProgram {

    public static void main(String[] args) {
        int[] edgeCounts = new int[2000];
        
        // Run the experiment 2000 times.
        for (int i = 0; i <= 1999; i++) {
            
            // Create the graph from the data file.
            Graph graph = readDataFromFile("data.txt");
            
            // While there are more than 2 vertices remaining, run the random contraction algorithm.
            while (graph.getVertexCount() > 2) {
                // Choose a remaining edge at random.
                Edge randomEdge = chooseEdgeAtRandom(graph);
                
                // Contract the endpoints of the edge.
                contractEndpoints(graph, randomEdge);
                
                // Remove self-loops.
                graph.removeSelfLoops();
            }
            
            edgeCounts[i] = graph.getEdgeCount();
//            System.out.println("Number of edges remaining: " + graph.getEdgeCount());
        }
        
        // Find the min cut and display the array.
        Arrays.sort(edgeCounts);
        System.out.println("Min cut: " + edgeCounts[0] / 2); // Divide by two to account for duplicate edges due to data format of input file.
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
                Vertex vertex = new Vertex(parts[0]);
                if (!graph.hasVertex(vertex)) {
                    graph.addVertex(vertex);
                }
                
                // Iterate through the rest of the array, create a Vertex object for each adjacent vertex as needed, and connect edges from the adjacent vertices to the vertex represented by the first element of the array.
                for (int i = 1; i <= parts.length - 1; i++) {
                    Vertex adjacentVertex = new Vertex(parts[i]);
                    
                    if (!graph.hasVertex(adjacentVertex)) {
                        graph.addVertex(adjacentVertex);
                    }
                    
                    // Create the edge and point it at the appropriate vertices.
                    Edge edge = new Edge(graph.getVertex(vertex.getName()), graph.getVertex(adjacentVertex.getName()));
                    
                    // Point the appropriate vertices at the edge.
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
    
    public static Edge chooseEdgeAtRandom(Graph graph) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(graph.getEdgeCount());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int randomIndex = (int) (randomNumber * timestamp.getTime() % (graph.getEdgeCount() - 1));  // Subtract 1 from edge count to make adjustment from length to index.
        Edge randomEdge = graph.getEdge(randomIndex);
        
        return randomEdge;
    }
    
    public static void contractEndpoints(Graph graph, Edge edge) {
        Vertex endpointOne = edge.getEndpointOne();
        Vertex endpointTwo = edge.getEndpointTwo();
        
        // Transfer all of endpointTwo's edges to endpointOne.
        endpointTwo.transferEdges(endpointOne);
        
        // Delete the currently chosen edge, and endpointTwo.
        graph.removeEdge(edge);
        graph.removeVertex(endpointTwo);
    }
}
