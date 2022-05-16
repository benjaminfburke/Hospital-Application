public class PatientQueue {
    private Patient[] array;
    private int size = 0;
    private int capacity;
    
    //constructor: set variables
    //capacity = initial capacity of array 
    public PatientQueue(int capacity) {
        this.capacity = capacity;
        this.array = new Patient[capacity];
    }

    //function to get position of parent
    public int parent(int childIndex) {
        return (childIndex - 1) / 2;
    }

    //function to check if a given element has a parent
    public boolean hasParent(int index) {
        return parent(index) >= 0;
    }

    //function to get position of left child
    public int leftChild(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    //function to check if a given element has a left child
    public boolean hasLeftChild(int index) {
        return leftChild(index) < size;
    }

    //function to get position of right child
    public int rightChild(int parentIndex) {
        return (parentIndex * 2) + 2;
    }

    //function to check if a given element has a left child
    public boolean hasRightChild(int index) {
        return rightChild(index) < size;
    }

    //function to swap to elements in the array
    public void swapElement(int first, int second) {
        Patient temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    //function to heapify down in the max heap array
    public void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallestChildIndex = leftChild(index);
            if (hasRightChild(index) && array[rightChild(index)].compareTo(array[leftChild(index)]) > 0) {
                smallestChildIndex = rightChild(index);
            }
            if (array[smallestChildIndex].compareTo(array[index]) > 0) {
                swapElement(index, smallestChildIndex);
            } else {
                break;
            }
            index = smallestChildIndex;
        }
    }

    //function to heapify up in the max heap array
    public void heapifyUp() {
        int index = size - 1;
        int temp;
        while (hasParent(index) && array[index].compareTo(array[parent(index)]) > 0) {
            temp = parent(index);
            swapElement(parent(index), index);
            index = temp;
        }
    }
    
    //insert Patient p into queue
    //return the final index at which the patient is stored
    //return -1 if the patient could not be inserted
    public int insert(Patient p) {
	
        if (size == capacity) {
            return -1;
        }
        array[size] = p;
        size++;
        heapifyUp();
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == p) {
                temp = i;
            }
        }
        return temp;
    }

    //remove and return the patient with the highest urgency level
    //if there are multiple patients with the same urgency level,
    //return the one who arrived first
    public Patient delMax() {
	
        if (size == 0) {
            return null;
        }
        Patient delete = array[0];
        array[0] = array[size - 1];
        size--;
        heapifyDown();
        return delete;
    }

    //return but do not remove the first patient in the queue
    public Patient getMax() {
	
        if (isEmpty()) {
            return null;
        }
        return array[0];
    }

    //return the number of patients currently in the queue
    public int size() {
	
        return size;
    }

    //return true if the queue is empty; false else
    public boolean isEmpty() {
	
        if (size() == 0) {
            return true;
        }
        return false;
    }

    //used for testing underlying data structure
    public Patient[] getArray() {
	return array;
    }
}
    
