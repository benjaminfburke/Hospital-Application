import java.util.ArrayList;
public class PHashtable {
    private ArrayList[] table;
    private int size = 0;
    private int cap;
    
    //set the table size to the first 
    //prime number p >= capacity
    public PHashtable (int capacity) {
	
        cap = capacity;
        if (!isPrime(cap)) {
            cap = getNextPrime(cap);
        }
        this.table = new ArrayList[cap];
    }

    //return the Patient with the given name 
    //or null if the Patient is not in the table
    public Patient get(String name) {
	
        Patient temp;
        for (int i = 0; i < table.length; i++) {
            if(table[i] != null) {
                for (int j = 0; j < table[i].size(); j++) {
                    temp = (Patient) table[i].get(j);
                    if (temp.name().equals(name)) {
                        return temp;
                    }
                }
            }
        }
        return null;
    }

    //put Patient p into the table
    public void put(Patient p) {
	
        int hash;
        if (p.name().hashCode() % cap < 0) {
            hash = p.name().hashCode() % cap;
            hash = hash + cap;
        }
        else {
            hash = p.name().hashCode() % cap;
        }
        if (table[hash] != null && table[hash].contains(p)) {
            return;
        }
        else {
            if (table[hash] == null) {
                table[hash] = new ArrayList();
            }
            table[hash].add(p);
            size++;
        }
    }

    //remove and return the Patient with the given name
    //from the table
    //return null if Patient doesn't exist
    public Patient remove(String name) {
	
        Patient temp;
        for (int i = 0; i < table.length; i++) {
            if(table[i] != null) {
                for (int j = 0; j < table[i].size(); j++) {
                    temp = (Patient) table[i].get(j);
                    if (temp.name() == name) {
                        table[i].remove(j);
                        size--;
                        return temp;
                    }
                }
            }
        }
        return null;
    }	    

    //return the number of Patients in the table
    public int size() {
	
        return size;
    }

    //returns the underlying structure for testing
    public ArrayList<Patient>[] getArray() {
	return table;
    }
    
    //get the next prime number p >= num
    private int getNextPrime(int num) {
    if (num == 2 || num == 3)
        return num;
    int rem = num % 6;
    switch (rem) {
        case 0:
        case 4:
            num++;
            break;
        case 2:
            num += 3;
            break;
        case 3:
            num += 2;
            break;
    }
    while (!isPrime(num)) {
        if (num % 6 == 5) {
            num += 2;
        } else {
            num += 4;
           }
        }
        return num;
    }


    //determines if a number > 3 is prime
    private boolean isPrime(int num) {
        if(num % 2 == 0){
            return false;
        }
        
	int x = 3;
	for(int i = x; i < num; i+=2){
	    if(num % i == 0){
		    return false;
        }
    }
	return true;
    }
}
      

