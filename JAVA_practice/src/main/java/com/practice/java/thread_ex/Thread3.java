package com.practice.java.thread_ex;

public class Thread3 extends Thread{
    public void run(){
        System.out.println("Thread3 is running..");
        for(int i=100;i<150;i++){
            System.out.print(i+" ");
        }
        System.out.println("Thread3 is over.");
    }
}
