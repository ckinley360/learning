
import java.nio.file.Paths;
import java.util.Scanner;

public class MainProgram {

    public static void main(String[] args) {
        Graph graph = readDataFromFile("data.txt");
        
        System.out.println(graph);
    }
    
    // Need a solution for dealing with duplicate connections (e.g. vertex 90 > vertex 4, vertex 4 > vertex 90).
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
