
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Random;
import java.sql.Timestamp;
import java.util.Arrays;

public class MainProgram {

    public static void main(String[] args) {
        // Create the graph from the data file.
        DirectedGraph graph = readDataFromFile("data.txt");
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
                Edge edge = new Edge(graph.getVertex(tailVertex.getName()), graph.getVertex(adjacentVertex.getName()));
                    
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
}
