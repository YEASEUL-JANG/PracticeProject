package com.practice.java.thread_ex;

public class Thread2 implements Runnable{
    public void run(){
        System.out.println("Thread2 is running..");
        for(int i=51;i<100;i++){
            System.out.print(i+" ");
        }
        System.out.println("Thread2 is over.");
    }
}
