
public class CharacterNode {
    
    private String name;
    private CharacterNode parent;
    private CharacterNode leftChild;
    private CharacterNode rightChild;
    
    public CharacterNode(String name, CharacterNode parent, CharacterNode leftChild, CharacterNode rightChild) {
        this.name = name;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    
    public String getName() {
        return this.name;
    }
    
    public CharacterNode getParent() {
        return this.parent;
    }
    
    public CharacterNode getLeftChild() {
        return this.leftChild;
    }
    
    public CharacterNode getRightChild() {
        return this.rightChild;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setParent(CharacterNode parent) {
        this.parent = parent;
    }
    
    public void setLeftChild(CharacterNode leftChild) {
        this.leftChild = leftChild;
    }
    
    public void setRightChild(CharacterNode rightChild) {
        this.rightChild = rightChild;
    }
}
