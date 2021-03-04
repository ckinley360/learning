
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
    
    public void setScore(int score) {
        this.score = score;
    }
}
