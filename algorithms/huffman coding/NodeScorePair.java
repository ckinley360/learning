
public class NodeScorePair {

    private Node node;
    private long score;
    
    public NodeScorePair(Node node, long score) {
        this.node = node;
        this.score = score;
    }
    
    public Node getNode() {
        return this.node;
    }
    
    public long getScore() {
        return this.score;
    }
    
    public void setNode(Node node) {
        this.node = node;
    }
    
    public void setScore(long score) {
        this.score = score;
    }
}
