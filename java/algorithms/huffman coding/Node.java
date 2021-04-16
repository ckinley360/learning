
public class Node {
    
    private String name;
    private Node parent;
    private Node leftChild;
    private Node rightChild;
    private int depth;
    
    public Node(String name, Node parent, Node leftChild, Node rightChild, int depth) {
        this.name = name;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.depth = depth;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Node getParent() {
        return this.parent;
    }
    
    public Node getLeftChild() {
        return this.leftChild;
    }
    
    public Node getRightChild() {
        return this.rightChild;
    }
    
    public int getDepth() {
        return this.depth;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }
    
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
    
    public void setDepth(int depth) {
        this.depth = depth;
    }
}
