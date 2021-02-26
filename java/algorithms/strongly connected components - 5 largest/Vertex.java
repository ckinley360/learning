
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Vertex {

    private int name;
    private ArrayList<Edge> edgeTails;
    private ArrayList<Edge> edgeHeads;
    private Vertex leader;
    private boolean explored;
    private int finishingTime;
    private int sccSize;
    
    public Vertex(int name) {
        this.name = name;
        this.edgeTails = new ArrayList<>();
        this.edgeHeads = new ArrayList<>();
        this.leader = null;
        this.explored = false;
        this.finishingTime = 0;
        this.sccSize = 0;
    }
    
    public int getName() {
        return this.name;
    }
    
    public ArrayList<Edge> getEdgeTails() {
        return this.edgeTails;
    }
    
    public ArrayList<Edge> getEdgeHeads() {
        return this.edgeHeads;
    }
    
    public Vertex getLeader() {
        return this.leader;
    }
    
    public boolean isExplored() {
        return this.explored;
    }
    
    public int getFinishingTime() {
        return this.finishingTime;
    }
    
    public int getSccSize() {
        return this.sccSize;
    }
    
    public void addEdgeTail(Edge edge) {
        this.edgeTails.add(edge);
    }
    
    public void addEdgeHead(Edge edge) {
        this.edgeHeads.add(edge);
    }
    
    public void setLeader(Vertex vertex) {
        this.leader = vertex;
    }
    
    public void setExplored(boolean explored) {
        this.explored = explored;
    }
    
    public void setFinishingTime(int finishingTime) {
        this.finishingTime = finishingTime;
    }
    
    public void setSccSize(int sccSize) {
        this.sccSize = sccSize;
    }
    
    public void removeEdgeTail(Edge edge) {
        this.edgeTails.remove(edge);
    }
    
    public void removeEdgeHead(Edge edge) {
        this.edgeHeads.remove(edge);
    }
    
    @Override
    public boolean equals(Object obj) {
        // Compare the references.
        if (this == obj) {
            return true;
        }
        
        // Ensure the object is not null.
        if (obj == null) {
            return false;
        }
        
        // Ensure the object is of Vertex class.
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        // Convert the object to Vertex type, and compare the names.
        Vertex other = (Vertex) obj;
        
        return this.name == other.getName();
    }
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        
        output.append(String.valueOf(this.name) + ": [");
        
        for (Edge edge:this.edgeTails) {
            output.append(edge.getHead().getName() + ", ");
        }
        
        // Delete the extra comma and space at the end of the string.
        if (!this.edgeTails.isEmpty()) {
            output.delete(output.length() - 2, output.length());
        }
        
        output.append("]");
        
        return output.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.name;
        return hash;
    }
}
