import java.util.AbstractMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HashMapTest {

  public static AbstractMap.SimpleEntry<String, Integer> countHash(String[] tokens) {
    HashMap2540<String, Integer> map = new HashMap2540<String, Integer>();
    int len = tokens.length;
    for (int i = 0; i < len; i++) {
      String token = tokens[i];
      Integer freq = map.get(token);
      if (freq == null)
        map.put(token, 1);
      else
        map.put(token, freq + 1);
    }

    int max = 0;
    String maxWord = "";
    for (String k : map.keys())
      if (map.get(k) > max) {
        max = map.get(k);
        maxWord = k;
      }
    return new AbstractMap.SimpleEntry<String, Integer>(maxWord, max);
  }

  // uses Buffered reader & stringbuilder for tokenizaiton
  // O(n)
  static String[] readText(String PATH) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(PATH));

    StringBuilder test = new StringBuilder();

    // String text =" ";
    String line = " ";
    while ((line = br.readLine()) != null) {
      test.append(" " + line.trim());
    }
    br.close();

    // test.trimToSize();
    String tokens[] = test.toString().trim().split("[^a-zA-Z]+");
    // .split("[^a-zA-Z]+") ;

    return tokens;
  }

  // used in A3_Comp2540_Fall2021
  // uses doubling dynamic stack implemtation
  // Tokenization of file using scanner, file, and delimiters. Also pareses
  // strings as lower case
  // uses Arrays.copyOf() and may create arrays larger than number of tokens
  // o(n^2)
  static String[] readLowCase(String PATH) throws Exception {

    // static String [] readText(String PATH) throws Exception {
    Scanner scanner = new Scanner(new File(PATH)).useDelimiter("[^a-zA-Z]+");
    // String[] ret = new String[];
    Stack2540Array stack = new Stack2540Array();
    while (scanner.hasNext()) {
      stack.push(scanner.next().toLowerCase());
    }
    scanner.close();
    return stack.copyAsArray();

  }

  // sorts an array as into a maxHeap
  // uses Priority queues
  // o(n^2)
  public static String[] heapSortJava(String[] a) {
    PriorityQueue<String> pq = new PriorityQueue<String>();
    int n = a.length;
    String[] result = new String[n];
    for (int i = 0; i < n; i++) {
      pq.add(a[i]);
    }

    for (int i = 0; i < n; i++) {
      result[i] = pq.remove();
    }

    return result;
  }

  public static String[] heapSort2540(String[] a) {
    Heap2540 pq = new Heap2540(a);
    int n = a.length;
    String[] result = new String[n];
    for (int i = 0; i < n; i++) {
      result[i] = pq.removeMax();
    }
    return result;
  }

  // public static String [] reverse(String[] tokens) throws Exception{
  // // Scanner scanner = new Scanner(new
  // File(filename)).useDelimiter("[^a-zA-Z]+");
  // Stack2540Array stack = new Stack2540Array();

  // // String temp;
  // for(String c : tokens){
  // stack.push(c);
  // }
  // String [] rev = new String[stack.size()];

  // // StringBuilder test = new StringBuilder();
  // int i = 0;
  // while(!stack.isEmpty())
  // {
  // rev[i] = stack.pop();
  // }

  // return rev;
  // }

  public static void main(String[] args) throws Exception {
    String PATH = "dblp";
    // String[] METHODS = { "MERGE", "" };
    String[] DATASETS = { "200", "400", "800", "1600", "3200", "6400", "10000", "20000", "40000", "80000", "160000",
        "320000", "640000", "1280000", "2560000" };// , "10k","100k"}; //, "5k", "10k", "100k", "1m", "" };

    String[] tokens;
    long startTime = 1;
    long endTime = 1;

    PrintWriter writer = new PrintWriter("newFile.txt");
    String OUTPUT = "\tHeap2540\t";
    writer.println(OUTPUT);
    System.out.println(OUTPUT);

    for (int j = 0; j < 13; j++) {
      // run the experiments using different methods

      // initialization
      String currentDataset = PATH + DATASETS[j] + ".txt";
 
      // output
      OUTPUT = DATASETS[j] + "\t";
      writer.print(OUTPUT);
      System.out.print(OUTPUT);

      for (int i = 0; i < 2; i++) {

        if (i == 0) {
          // initialization
          tokens = readLowCase(currentDataset);
          
          // time start
          startTime = System.currentTimeMillis();

          AbstractMap.SimpleEntry<String, Integer> test = countHash(tokens);

          endTime = System.currentTimeMillis();
          // time end

          // output
          OUTPUT = (endTime - startTime) + "\t";
          writer.print(OUTPUT);
          System.out.print(OUTPUT);
        } 
        // else if (i == 1) {

        //   // initialization
        //   tokens = readLowCase(currentDataset);

        //   // time start
        //   startTime = System.currentTimeMillis();

          

        //   endTime = System.currentTimeMillis();
        //   // time end

        //   // output
        //   OUTPUT = (endTime - startTime) + "\t";
        //   writer.print(OUTPUT);
        //   System.out.print(OUTPUT);
        // }

      }
      OUTPUT = "\n";
      writer.print(OUTPUT);
      System.out.print(OUTPUT);
      // String time = String.format("%12d", endTime - startTime);

    }

    writer.close();
  }
}
