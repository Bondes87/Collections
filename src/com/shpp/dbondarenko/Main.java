package com.shpp.dbondarenko;

import java.util.Arrays;
import java.util.Comparator;

/**
 * File: Main.java
 * Created by Dmitro Bondarenko on 20.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        BDSArrayList<Integer> arrayList = new BDSArrayList<>();
        System.out.println(arrayList.add(1));
        System.out.println(arrayList.add(2));
        System.out.println(arrayList.add(3));
        System.out.println(arrayList.add(4));
        System.out.println(arrayList.add(5));
        System.out.println(arrayList.add(2));
        System.out.println(arrayList.add(7));
        Integer integer = new Integer(10);
        System.out.println(arrayList.remove(integer));
        Object[] integers = arrayList.toArray();
        System.out.println(Arrays.toString(integers));
        arrayList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        Object[] integers1 = arrayList.toArray();
        System.out.println(Arrays.toString(integers1));

        /*Test test = new Test(5,"e");
        System.out.println(arrayList.add(test));
        System.out.println(arrayList.add(new Test(1,"a")));
        System.out.println(arrayList.add(new Test(2,"b")));
        System.out.println(arrayList.add(new Test(3,"c")));
        System.out.println(arrayList.add(new Test(4,"d")));
        System.out.println(arrayList.remove(test));*/

        //arrayList.add(5, 55);
        /*System.out.println(arrayList.add(8));
        System.out.println(arrayList.add(9));
        System.out.println(arrayList.add(10));
        System.out.println(arrayList.add(11));
        System.out.println(arrayList.add(null));
        System.out.println(arrayList.add(2));
        System.out.println(arrayList.add(14));
        System.out.println(arrayList.add(15));
        System.out.println(arrayList.set(8, 78));
        //arrayList.add(11, 55);
        System.out.println(arrayList.add(16));
        System.out.println(arrayList.add(17));
        System.out.println(arrayList.add(18));
        System.out.println(arrayList.size());
        System.out.println(arrayList.contains(null));
        System.out.println(arrayList.indexOf(5896));
        System.out.println(arrayList.lastIndexOf(175447));
        arrayList.clear();

        System.out.println(arrayList.size());*/
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
