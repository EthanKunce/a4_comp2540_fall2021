import java.util.LinkedList;
import java.util.Random;



public class HashMap2540<Key, Value> {
	private static final int INIT_CAPACITY = 17;
	private int n; // number of elements
	private int m; // hash table size
	private int prime;
	private long scale, shift;
	private LinkedListForHash2540<Key, Value>[] st; 
	// array of linked-list.
	
	public HashMap2540() {
		this(INIT_CAPACITY);
	}

	// used in resize.
	public HashMap2540(int m) {
		this(m, 41);
	}

	public HashMap2540(int m, int p){
		// prime = p;
		this.m = m;
		// Random rand = new Random();
		// scale = rand.nextInt(prime-1) +2;
		// shift = rand.nextInt(prime);
		st = (LinkedListForHash2540<Key, Value>[]) new LinkedListForHash2540[m];
		for (int i = 0; i < m; i++)
			st[i] = new LinkedListForHash2540<Key, Value>();
	}

	// resize the hash table to have the given number of chains,
	// rehashing all of the keys
	private void resize(int n) {
		HashMap2540<Key, Value> temp = new HashMap2540<Key, Value>(n);
		for (int i = 0; i < m; i++) {
			for (Key key : this.st[i].keys()) {
				temp.put(key, this.st[i].get(key));
			}
		}
		this.m = temp.m;
		this.n = temp.n;
		this.st = temp.st;
	}

	// hash value between 0 and m-1
	private int standardHash(Key key) {
		int hash = 7;
		String k = (String) key; //here we assume keys are strings.
		int base=31;
		for (int i = 0; i < k.length(); i++)
			//calculate hashcode (h1) using polynomial method: 
			// hash=base^{n-1} key[0]+base^{n-2} key[1] +....+key[n-1]
			hash=(int)Math.round((Math.pow(31,k.length()-i-1)))*k.charAt(i)+hash;
			
		//Implement h2 using division method
		hash=Math.abs(hash) % m;
		return hash;
	}

	private int myhash(Key key) {

		int base = 33;
		String k = (String) key;
		int hash = 0;
		hash ^= key.hashCode();
		for(int i = 0; i < k.length(); i++){
			hash = (int) (hash * base) + k.charAt(i);
		}
		hash = (hash << 5) | (hash >>> 27);
		hash = Math.abs(hash); //% base;
		//compression
		hash %= m ;
		return hash;
	}

	public Value get(Key key) {
		int i = myhash(key);
		return st[i].get(key);
	}

	public void put(Key key, Value val) {
		// double table size if average linked list size is 10. Note that the resize stratege is different from the Java implementation as discussed in the book. 
		// if number of elelements is greater than or equal to 10 * size of hashtable 
		if (n >= 10 * m)
			resize(3 * m);
		// Put your code here. 
		// 1) get the hash value of the key i. 
		// 2) then put (key, value) in the i-th linkedList implemented in LinkedListForHash254
		// 3) you need to handle the case whether n need to be increased depending on whether the key is already there. 
	
		int i = myhash(key);
		if(this.get(key) != null){
			st[i].put(key, val);
			return;
		}
		n++;
	
		st[i].put(key, val);
	}

	
	public void putT(Key key, Value val) {
		// double table size if average linked list size is 10. Note that the resize stratege is different from the Java implementation as discussed in the book. 
		if (n >= 10 * m)
			resize(2 * m);
		// Put your code here. 
		// 1) get the hash value of the key i. 
		// 2) then put (key, value) in the i-th linkedList implemented in LinkedListForHash254
		// 3) you need to handle the case whether n need to be increased depending on whether the key is already there. 
		double startTime = System.nanoTime();
		int i = myhash(key);
		double endTime = System.nanoTime();
		System.out.printf("%f\n", (endTime - startTime));
		if(this.get(key) != null){
			st[i].put(key, val);
			return;
		}
		n++;
		st[i].put(key, val);

	}	

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		int i = myhash(key);
		if (st[i].get(key)!=null)
			n--;
		st[i].delete(key);

		// halve table size if average length of list <= 2
		if (m > INIT_CAPACITY && n <= 2 * m)
			resize(m / 2);
	}

	public LinkedList<Key> keys() {
		LinkedList<Key> queue = new LinkedList<Key>();
		for (int i = 0; i < m; i++) {
			for (Key key : st[i].keys())
				queue.add(key);
		}
		return queue;
	}
}
						     
