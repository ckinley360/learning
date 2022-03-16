
import java.util.Arrays;

public class MinHeap {

    private int capacity = 10;
    private int size = 0;
    
    private EdgeScorePair[] items = new EdgeScorePair[capacity];
    
    private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex + 1; }
    private int getRightChildIndex(int parentIndex) { return 2 * parentIndex + 2; }
    private int getParentIndex(int childIndex) { return (childIndex - 1) / 2; }
    
    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }
    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }
    
    private EdgeScorePair leftChild(int index) { return items[getLeftChildIndex(index)]; }
    private EdgeScorePair rightChild(int index) { return items[getRightChildIndex(index)]; }
    private EdgeScorePair parent(int index) { return items[getParentIndex(index)]; }
    
    public int getSize() {
        return this.size;
    }
    
    private void swap(int indexOne, int indexTwo) {
        EdgeScorePair temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;
    }
    
    private void ensureExtraCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }
    
    public EdgeScorePair peek() {
        if (size == 0) throw new IllegalStateException();
        return items[0];
    }
    
    public EdgeScorePair poll() {
        if (size == 0) throw new IllegalStateException();
        EdgeScorePair item = items[0];
        items[0] = items[size - 1];
        size--;
        heapifyDown();
        return item;
    }
    
    public void add(EdgeScorePair item) {
        ensureExtraCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }
    
    public void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && parent(index).getScore() > items[index].getScore()) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }
    
    public void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index).getScore() < leftChild(index).getScore()) {
                smallerChildIndex = getRightChildIndex(index);
            }
            
            if (items[index].getScore() < items[smallerChildIndex].getScore()) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            
            index = smallerChildIndex;
        }
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
}
