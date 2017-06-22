package com.shpp.dbondarenko;

/**
 * File: Main.java
 * Created by Dmitro Bondarenko on 20.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        BDSQueue queue = new BDSQueue();
        System.out.println(queue.add(1));
        System.out.println(queue.add(2));
        System.out.println(queue.element());
        System.out.println(queue.pool());
        System.out.println(queue.remove());
        System.out.println(queue.pool());
        System.out.println(queue.remove());
        System.out.println(queue.pool());
       /* BDSStack stack = new BDSStack();
        System.out.println(stack.push(1));
        System.out.println(stack.push(2));
        System.out.println(stack.push(3));
        System.out.println(stack.push(4));
        System.out.println(stack.push(5));
        System.out.println(stack.push(6));
        System.out.println(stack.push(7));
        System.out.println(stack.push(8));
        System.out.println(stack.push(9));
        System.out.println(stack.push(10));
        System.out.println(stack.push(11));
        System.out.println(stack.push(12));
        System.out.println(stack.push(13));
        System.out.println(stack.push(14));
        System.out.println(stack.push(15));
        System.out.println("search: " + stack.search(15));*/
        // System.out.println(stack.peek());
        //  System.out.println(stack.pop());
        // System.out.println(stack.peek());
        // System.out.println(stack.search(1));
        // System.out.println(stack.peek());
      /*   System.out.println(stack.push("gfdg"));
        /*System.out.println(stack.peek());
        System.out.println(stack.push(5));
        System.out.println(stack.peek());
        System.out.println(stack.push(6));
        System.out.println(stack.peek());
        System.out.println(stack.push(8));
        System.out.println(stack.peek());
        System.out.println(stack.push(9));
        System.out.println(stack.empty());
        System.out.println(stack.search(0));
        System.out.println(stack.pop());*/

    }


}
