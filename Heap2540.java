import java.util.PriorityQueue;

public class Heap2540 {
   
    private String[] heap;
    private int n=0;
    
    public Heap2540(String[ ] a) {    	
     // define the constructor for a heap from an array
    }
  
    public Heap2540() {   
    	heap=new String[128];
    }
  
    public String removeMax() {
    	String max=heap[1];
        swap(1, n);
        n--;
        sink(1);       
	return max;
    }

    public void insert(String x) {
        // add resize 
        n++;
        heap[n] = x;
        swim(n);
    }

    private void swim(int k) {
     // add swim 
    }
    
    private void swap(int i, int j) {
        String temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void sink(int k) {
     // add definition here
        // while(compareTo(heap[i], ))

    }

   
    private boolean less(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    public static void heapSortJava(String [] a) {
        PriorityQueue <String > pq=new PriorityQueue <String >();
        int n = a.length;
       
        for (int i=0; i < n; i++) {
            pq.add(a[i]);
        }

        for (int i=0; i < n; i++) {
            a[i] = pq.remove ();
        }
    }

    public static String [] heapSort2540(String [] a) {
        Heap2540 pq = new Heap2540(a);
        int n = a.length;
        String [] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = pq.removeMax ();
        }
        return result;
        }
}
