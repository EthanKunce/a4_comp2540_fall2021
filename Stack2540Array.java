import java.io.*;
import java.util.*;

public class Stack2540Array{

    private int CAPACITY = 256;
    private int top;
    private String[] stack;


    public Stack2540Array(int size){
        stack = new String[size];
        top = -1;
    }

    public Stack2540Array(){

        stack = new String[CAPACITY];
        top = -1;
    }

    public int size() { return top + 1; }

    public boolean isEmpty () { return (top == -1); }

    public String[] copyAsArray(){
        // String[] ret = Arrays.copyOf(stack, stack.length);
        int j=0;
        for(String s : stack)
        {
            if(s != null) j++;
        }
        return Arrays.copyOf(stack, j);
    }

    public String top(){

        if (top == -1){
            return null;
        }

        return stack[top];
    }

    public int getSize(){
        return top;
    }

    public void push(String element) {
        if(++top >= stack.length-1){
            resize();
        }
        stack[top] = element;
    }

    public void push(char element) {
        // top++;
        if(++top >= stack.length-1){
            resize();
        }
        stack[top] = element+"";
    }

    public void resize(){
        if(top >= stack.length-1){
            // CAPACITY *= 2;
            String[] newArray = Arrays.copyOf(stack, stack.length*2);
            stack = newArray;
        }
    }

    public String pop(){
        // String temp;
        if(top == -1)
        {
            return null;
        }

        String temp = stack[top];
        stack[top--] = null;
        // top--;
        return temp;
    }

    // public static boolean isMatched(String expression){
        
    // }

    
}