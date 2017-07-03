package com.shpp.dbondarenko;

/**
 * File: Main.java
 * Created by Dmitro Bondarenko on 20.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        BDSHashMap<String, Integer> map = new BDSHashMap<>();
        //System.out.println(map.put("k", 1));
        System.out.println(map.put("w", 7));
        System.out.println(map.put("u", 8));
       /* System.out.println(map.put("dffdfdf", 3));
        System.out.println(map.put("dfgdsgfgdf", 4));
        System.out.println(map.put("k", 4));
        System.out.println(map.put(null, null));*/
        System.out.println(map.size());
        System.out.println(map);
        BDSHashMap<String, Integer> map1 = (BDSHashMap<String, Integer>) map.clone();
        System.out.println(map1.size());
        System.out.println(map1);
       /* HashMap<Integer,String>hashMap = new HashMap<>();
        for (Map.Entry<Integer, String> entry: hashMap.entrySet()){
            entry.
        }*/
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
