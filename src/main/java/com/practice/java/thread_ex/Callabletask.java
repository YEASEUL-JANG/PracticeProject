package com.practice.java.thread_ex;

import java.util.concurrent.Callable;
//Callable 인터페이스를 구현하여 ExecutorService를 실행하면 작업의 결과를 반환받을 수 있다.
public class Callabletask implements Callable<String> {
    private String name = "";

    public Callabletask(String name) {
        this.name = name;
    }
    @Override
    public String call() throws Exception {
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        //Thread.sleep(1000);
        return "Hello "+name;
    }
}
