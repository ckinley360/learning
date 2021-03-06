
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class MinHeap {

    private int capacity = 10;
    private int size = 0;
    
    VertexScorePair[] items = new VertexScorePair[capacity];
    Map<VertexScorePair, Integer> indexTracker = new HashMap<>();
    
    private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex + 1; }
    private int getRightChildIndex(int parentIndex) { return 2 * parentIndex + 2; }
    private int getParentIndex(int childIndex) { return (childIndex - 1) / 2; }
    
    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }
    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }
    
    private VertexScorePair leftChild(int index) { return items[getLeftChildIndex(index)]; }
    private VertexScorePair rightChild(int index) { return items[getRightChildIndex(index)]; }
    private VertexScorePair parent(int index) { return items[getParentIndex(index)]; }
    
    private void swap(int indexOne, int indexTwo) {
        // To track the indices for the two pairs.
        indexTracker.put(items[indexOne], indexTwo);
        indexTracker.put(items[indexTwo], indexOne);
        
        VertexScorePair temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;
    }
    
    private void ensureExtraCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }
    
    public VertexScorePair peek() {
        if (size == 0) throw new IllegalStateException();
        return items[0];
    }
    
    public VertexScorePair poll() {
        if (size == 0) throw new IllegalStateException();
        VertexScorePair item = items[0];
        indexTracker.put(items[size - 1], 0); // To track the index for this pair.
        items[0] = items[size - 1];
        size--;
        heapifyDown();
        return item;
    }
    
    public void add(VertexScorePair item) {
        ensureExtraCapacity();
        items[size] = item;
        indexTracker.put(item, size); // To track the index for this pair.
        size++;
        heapifyUp();
    }
    
    public void deleteFromMiddle(int index) {
        while (hasParent(index)) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
        
        poll();
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
