package com.shpp.dbondarenko;

/**
 * File: Main.java
 * Created by Dmitro Bondarenko on 20.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        BDSStack stack = new BDSStack();
        System.out.println(stack.push(1));
        System.out.println(stack.peak());
        System.out.println(stack.push(2));
        System.out.println(stack.peak());
        System.out.println(stack.push(5));
        System.out.println(stack.peak());
        System.out.println(stack.push(6));
        System.out.println(stack.peak());
        System.out.println(stack.push(8));
        System.out.println(stack.peak());
        System.out.println(stack.push(9));
        System.out.println(stack.empty());
        System.out.println(stack.search(0));
        System.out.println(stack.pop());

    }


}
