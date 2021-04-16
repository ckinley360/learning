
public class NodeScorePair {

    private CharacterNode node;
    private int score;
    
    public NodeScorePair(CharacterNode node, int score) {
        this.node = node;
        this.score = score;
    }
    
    public CharacterNode getNode() {
        return this.node;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setNode(CharacterNode node) {
        this.node = node;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
}
