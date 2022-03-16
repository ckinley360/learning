
public class Edge {

    private Vertex tail;
    private Vertex head;
    
    public Edge(Vertex tail, Vertex head) {
        this.tail = tail;
        this.head = head;
    }
    
    public Vertex getTail() {
        return this.tail;
    }
    
    public Vertex getHead() {
        return this.head;
    }
    
    public void setTail(Vertex vertex) {
        this.tail = vertex;
    }
    
    public void setHead(Vertex vertex) {
        this.head = vertex;
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
        
        // Ensure the object is of Edge class.
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        // Convert the object to Edge type, and compare the names.
        Edge other = (Edge) obj;
        
        return this.tail.equals(other.getTail()) && this.head.equals(other.getHead());
    }
    
    @Override
    public String toString() {
        return ("Tail: " + this.tail.getName() + ", Head: " + this.head.getName());
    }
}
