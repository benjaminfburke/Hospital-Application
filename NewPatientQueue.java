public class NewPatientQueue {
    private Patient[] array;
    private int size = 0;
    private int capacity;
    private PHashtable pHashtable;
    //private PHashtable table;

    /*TO BE COMPLETED IN PART 1*/

    //constructor: set variables
    //capacity = initial capacity of array 
    public NewPatientQueue(int capacity) {
	//TO BE COMPLETED
        this.capacity = capacity;
        this.array = new Patient[capacity];
        pHashtable = new PHashtable(capacity);
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
        pHashtable.remove(array[first].name());
        array[first].setPosInQueue(second);
        pHashtable.remove(array[second].name());
        array[second].setPosInQueue(first);
        Patient temp = array[first];
        array[first] = array[second];
        pHashtable.put(array[first]);
        array[second] = temp;
        pHashtable.put(array[second]);
    }

    //function to heapify down in the max heap array
    public void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int smallestChildIndex = leftChild(index);
            if (hasRightChild(index) && array[rightChild(index)].compareTo(array[leftChild(index)]) > 0) {
                smallestChildIndex = rightChild(index);
            }
            if (array[smallestChildIndex].compareTo(array[index]) > 0) {
                swapElement(index, smallestChildIndex);
            }
            index = smallestChildIndex;
        }
    }

    //function to heapify up in the max heap array
    public void heapifyUp(int index) {
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
        //TO BE COMPLETED
        if (size == capacity) {
            return -1;
        }
        p.setPosInQueue(size);
        array[size] = p;
        pHashtable.put(p);
        heapifyUp(size-1);
        size++;
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
        //TO BE COMPLETED
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            Patient temp = array[0];
            array[0] = null;
            size--;
            temp.setPosInQueue(-1);
            pHashtable.remove(temp.name());
            return temp;
        }
        if (size == 2) {
            Patient temp = array[0];
            array[0] = array[1];
            array[1] = null;
            array[0].setPosInQueue(0);
            size--;
            temp.setPosInQueue(-1);
            pHashtable.remove(temp.name());
            return temp;
        }
        Patient temp = array[0];
        remove(temp.name());
        return temp;
    }

    //return but do not remove the first patient in the queue
    public Patient getMax() {
        //TO BE COMPLETED
        if (isEmpty()) {
            return null;
        }
        return array[0];
    }

    //return the number of patients currently in the queue
    public int size() {
        //TO BE COMPLETED
        return size;
    }

    //return true if the queue is empty; false else
    public boolean isEmpty() {
        //TO BE COMPLETED
        if (size() == 0) {
            return true;
        }
        return false;
    }


    //used for testing underlying data structure
    public Patient[] getArray() {
	return array;
    }

    /*TO BE COMPLETED IN PART 2*/

    //remove and return the Patient with
    //name s from the queue
    //return null if the Patient isn't in the queue
    public Patient remove(String s) {
	//TO BE COMPLETED
        int prev;
        Patient temp = pHashtable.get(s);
        prev = temp.posInQueue();
        size--;
        if (prev > size) {
            return temp;
        }
        swapElement(temp.posInQueue(), size);
        temp.setPosInQueue(-1);
        pHashtable.remove(s);
        if (hasParent(prev) && array[prev].compareTo(array[parent(prev)]) > 0) {
            heapifyUp(prev);
        }
        else {
            heapifyDown(prev);
        }
        array[size] = null;
        return temp;
    }
    
    //update the emergency level of the Patient
    //with name s to urgency
    public void update(String s, int urgency) {
	//TO BE COMPLETED
        Patient temp = pHashtable.get(s);
        pHashtable.remove(s);
        temp.setUrgency(urgency);
        if (hasParent(temp.posInQueue()) && array[temp.posInQueue()].compareTo(array[parent(temp.posInQueue())]) > 0) {
            heapifyUp(temp.posInQueue());
        }
        else {
            heapifyDown(temp.posInQueue());
        }
        pHashtable.put(temp);
    }
}
    