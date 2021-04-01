
public class EdgeScorePair {

    private Edge edge;
    private int score;
    
    public EdgeScorePair(Edge edge, int score) {
        this.edge = edge;
        this.score = score;
    }
    
    public Edge getEdge() {
        return this.edge;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setEdge(Edge edge) {
        this.edge = edge;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
}
