package com.practice.java;

import com.practice.java.thread_ex.Callabletask;
import com.practice.java.thread_ex.Thread1;
import com.practice.java.thread_ex.Thread2;
import com.practice.java.thread_ex.Thread3;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.concurrent.*;

@SpringBootApplication
public class JavaApplication {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Thread1 task1 = new Thread1();
		Thread2 task2 = new Thread2();
		Thread task2thread = new Thread(task2);
		Thread3 task3 = new Thread3();

		//thread 시
//		task1.start();
//		task2thread.start();
//		task3.start();

		//join test
//		task1.join();
//		task2thread.join();
//		task3.start();//task1, task2가이 다 끝나야 task3이 시작한다.

		//ExecutorService 실행
		//newSingleThreadExecutor로 실행시키면 한번에 하나의 스레드만 실행한다.
//		ExecutorService executorService = Executors.newSingleThreadExecutor();
//		executorService.execute(new Thread1());
//		executorService.execute(new Thread(task2));
//		executorService.execute(new Thread3());
//
//		executorService.shutdown();

		//newFixedThreadPool(실행 스레드 수) : 어느지점에서든 수 만큼만 스레드를 실행시킨다.
//		ExecutorService executorPool = Executors.newFixedThreadPool(2);
//		executorPool.execute(new Thread1());
//		executorPool.execute(new Thread(task2));
//		executorPool.execute(new Thread3());
//
//		executorPool.shutdown();

		//Callble 인터페이스를 사용해서 결과값 리턴받는 법
//		 ExecutorService executorPool = Executors.newFixedThreadPool(1);
//		 //submit을 사용해서여 Callabletask을 스레드풀에 제출하고 이것은 Future 객체를 반환한다.
//		Future<String> fs = executorPool.submit(new Callabletask("world!"));
//		//get메서드로 작업 결과를 받아올 수 있다.
//		String message = fs.get();
//		System.out.println(message);
//		executorPool.shutdown();

		//3개의 스레드를 한꺼번에 넘기기 (invokeAll)
		ExecutorService executorPool = Executors.newFixedThreadPool(3);
		List<Callabletask> tasks = List.of(
				new Callabletask("123"), new Callabletask("456"), new Callabletask("789"));
		//invokeAll : 주어진 모든 작업을 동시에 실행하고 모두 완료 된 후 결과를 반환한다.
		List<Future<String>> lists = executorPool.invokeAll(tasks);
		//get메서드로 작업 결과를 받아올 수 있다.
		for(Future<String> fs : lists){
			System.out.println(fs.get());
		}
		executorPool.shutdown();

	}
}