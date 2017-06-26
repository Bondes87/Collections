package com.shpp.dbondarenko;

import java.util.Arrays;

/**
 * File: Main.java
 * Created by Dmitro Bondarenko on 20.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        BDSPriorityQueue queue = new BDSPriorityQueue();
        System.out.println(queue.size());
        /*System.out.println(queue.add(new Test(2, "Cvan")));
        System.out.println(queue.add(new Test(1, "Basa")));
        System.out.println(queue.add(new Test(1, "Afof")));
        System.out.println(queue.add(new Test(3, "Aola")));
        Test test = new Test(2, "Opla");
        System.out.println(queue.add(test));
        System.out.println(queue.contains(new Test(2, "Opla")));*/
        System.out.println(queue.add("e"));
        System.out.println(queue.add("o"));
        System.out.println(queue.size());
        System.out.println(queue.add("i"));
        System.out.println(queue.add("o"));
        Test test = new Test(2, "Opla");
        System.out.println(queue.add("u"));
        System.out.println(queue.contains("i"));
        System.out.println(queue.size());
        Object[] objects = queue.toArray();
        System.out.println(Arrays.toString(objects));
        queue.clear();
        System.out.println(queue.size());
        queue.clear();
        System.out.println(Arrays.toString(objects));
        // System.out.println(queue.add(1));
       /* System.out.println(queue.add(1));
        System.out.println(queue.add(0));
        System.out.println(queue.add(-3));
        System.out.println(queue.add(4));*/
        //Arrays.sort();

        // System.out.println(queue.peek());
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

    static class Test implements Comparable<Test> {


        private int id;
        private String name;

        Test(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int compareTo(Test o) {
            return Integer.compare(id, o.id);
        }

        @Override
        public String toString() {
            return "Test{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


}
