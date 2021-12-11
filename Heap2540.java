import java.util.Arrays;
import java.util.PriorityQueue;

public class Heap2540 {
   
    private String[] heap;
    private int n=0;
    
    public Heap2540(String[ ] a) {    	
     // define the constructor for a heap from an array
     
        heap=new String[256];

        // heap = heapSort2540(a);
        // n = a.length;
        // insert(a[0]);
        // heapSortJava(a);
        for(String c : a){

            insert(c);
            
        }


        // for(int k = n/2; k>= 1; k--){
        //     insert(a[k]);
        //     swim(k);
        // }
        // System.out.printf("hello");
    }
  
    public Heap2540() {   
    	heap=new String[256];
    }
  
    public String removeMax() {
    	String max=heap[1]  ;
        swap(1, n);
        heap[n] = null;
        n--;
        sink(1);       
	return max;
    }

    public void insert(String x) {
        if(n + 1 >= heap.length-1){
            // CAPACITY *= 2;
            String[] newArray = Arrays.copyOf(heap, heap.length*2);
            // n *= 2;
            heap = newArray;
        }
        n++;
        heap[n] = x;
        swim(n);
    }

    private void swim(int k) {
     // add swim

        while(k > 1 && less(k/2,k)){
            swap(k/2,k);
            k = k/2;
        }
    }
    
    private void swap(int i, int j) {
        String temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void sink(int k) {
     // add definition here
        // while(k > 1 && less(k/2, k)){
        //     swap(k/2, k);
        //     k = k/2;
        // }

        while(2*k <= n){
            int j = 2*k;
            if(j < n && less(j+1,j)) j++;

            if(!less(j, k)) break;

            swap(k, j);
            k = j;
        }

    }

    //compares heap[i] with heap[j]
    //returns true if heap[i] is before heap[j]
    private boolean less(int i, int j){
        return heap[i].compareTo(heap[j]) < 0;
    }

    public static void heapSortJava(String [] a) {
        PriorityQueue <String> pq=new PriorityQueue <String >();
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

    public void print()
    {
        for (int i = 0; i <= n / 2; i++) {
            System.out.print(
                " PARENT : " + heap[i]
                + " LEFT CHILD : " + heap[2 * i + 1]
                + " RIGHT CHILD :" + heap[2 * i + 2]);
            System.out.println();
        }
    }
 
}
