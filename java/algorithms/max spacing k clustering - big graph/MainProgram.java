
import java.nio.file.Paths;
import java.util.Scanner;

public class MainProgram {
    
    public static void main(String[] args) {
        // Create the graph from the data file.
        Graph graph = readDataFromFile("bigdata.txt");
        
        // Create the min heap and add the graph's edges to it.
        MinHeap minHeap = createMinHeapFromGraph(graph);

        // Compute the number of clusters after connecting all vertices with hamming distance less than 3.
        int countOfClusters = computeCountOfClustersAfterConnectingVerticesWithDistanceLessThanThree(graph, minHeap);
        System.out.println("Count of clusters: " + countOfClusters);
    }
    
    public static int computeCountOfClustersAfterConnectingVerticesWithDistanceLessThanThree(Graph graph, MinHeap minHeap) {
        // Create the union-find and add the graph's vertices to it.
        UnionFind unionFind = new UnionFind(graph);
        
        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll().getEdge();
            Vertex endpointOne = edge.getEndpointOne();
            Vertex endpointTwo = edge.getEndpointTwo();
            
            if (unionFind.find(endpointOne) != unionFind.find(endpointTwo)) {
                unionFind.union(endpointOne, endpointTwo);
            }
        }
        
        return unionFind.getGroupCount();
    }
    
    public static MinHeap createMinHeapFromGraph(Graph graph) {
        MinHeap minHeap = new MinHeap();
        
        for (int i = 1; i <= 200000; i++) {
            for (int j = i + 1; j <= 200000; j++) {
                Vertex vertexOne = graph.getVertex(i);
                Vertex vertexTwo = graph.getVertex(j);
                int hammingDistance = hammingDistance(vertexOne.getBitLabel(), vertexTwo.getBitLabel());
                
                if (hammingDistance < 3) {
                    Edge edge = new Edge(vertexOne, vertexTwo, hammingDistance);
                    EdgeScorePair pair = new EdgeScorePair(edge, hammingDistance);
                    minHeap.add(pair);
                }
            }
        }
        
        return minHeap;
    }
    
    public static Graph readDataFromFile(String filePath) {
        // Create the Graph object to store the data.
        Graph graph = new Graph();
        
        // Boolean to track whether we are reading the first row, which we want to ignore because it only tells us the total number of vertices and number of bits for each node's label.
        Boolean firstRow = true;
        
        // The row number of the input file (ignoring the header row) is the vertex's name.
        int vertexName = 0;
        
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
                
                // Increment the vertex name since we have advanced to the next row.
                vertexName++;
                
                // Remove all spaces from the line to create the binary string label of the vertex.
                String binaryString = line.replaceAll("\\s", "");
                
                // If the vertex doesn't yet exist in the graph, then create the vertex and add it to the graph.
                Vertex vertex = new Vertex(vertexName, binaryString);
                if (!graph.hasVertex(vertex)) {
                    graph.addVertex(vertex);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return graph;
    }
    
    public static int hammingDistance(long number1, long number2) {
        long number = number1 ^ number2;
        
        int countOfSetBits = 0;
        while (number != 0) {
            countOfSetBits += number & 1;
            number = number >> 1;
        }
        
        return countOfSetBits;
    }
}
