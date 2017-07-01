package com.shpp.dbondarenko;

/**
 * File: Main.java
 * Created by Dmitro Bondarenko on 20.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        /*BDSLinkedList<Integer> list = new BDSLinkedList<>();
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(null);
        list.addLast(7);
        list.addLast(2);
        System.out.println(list.add(9));
        list.addFirst(2);
        list.addFirst(1);
        list.addFirst(null);
        System.out.println(list.add(10));
        list.add(2, 1452);
        list.add(10, 78);
        System.out.println(list.get(2));
        //System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list);
        System.out.println(list.removeLastOccurrence(null));
        System.out.println(list.size());
        System.out.println(list);
        System.out.println(list.size());
        Object[] a = list.toArray();
        System.out.println(Arrays.toString(a));
        System.out.println(a.length);
        BDSLinkedList<Integer> list1 = new BDSLinkedList<>(list);
        list1.add(55);
        list1.add(66);
        list1.add(77);
        list1.add(88);
        list1.add(99);
        System.out.println(list1);
        list1.addAll(5, list);
        System.out.println(list1);
        System.out.println(list);
        System.out.println(list1);
        @SuppressWarnings("unchecked")
        BDSLinkedList<Integer> list2 = (BDSLinkedList<Integer>) list1.clone();
        System.out.println("list2" + list2);*/
        // ArrayList
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
