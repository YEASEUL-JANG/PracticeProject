package com.practice.java.thread_ex;

public class Thread1 extends Thread{


    public void run(){
        System.out.println("Thread1 is running..");
        for(int i=1;i<50;i++){
            try {
            System.out.print(i+" ");

            if(i == 12){
//                Thread.yield(); //다른 스레드에게 양보한다.
                Thread.sleep(3000); //3초간 스레드를 일시중지시킨다.
            }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread1 is over.");
    }
}
