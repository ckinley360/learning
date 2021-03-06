
import java.util.Objects;


public class VertexScorePair {

    private Vertex vertex;
    private int score;
    
    public VertexScorePair(Vertex vertex, int score) {
        this.vertex = vertex;
        this.score = score;
    }
    
    public Vertex getVertex() {
        return this.vertex;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.vertex);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VertexScorePair other = (VertexScorePair) obj;
        if (!Objects.equals(this.vertex, other.vertex)) {
            return false;
        }
        return true;
    }
}
