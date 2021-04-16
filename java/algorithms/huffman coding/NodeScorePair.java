
public class NodeScorePair {

    private Node node;
    private int score;
    
    public NodeScorePair(Node node, int score) {
        this.node = node;
        this.score = score;
    }
    
    public Node getNode() {
        return this.node;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setNode(Node node) {
        this.node = node;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
}
