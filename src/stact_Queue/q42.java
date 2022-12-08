package stact_Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class q42 {
	 public static void main(String[] args){
	 Scanner in=new Scanner(System.in);
	 String p = in.next();
	 String list = in.next();
	 q42 m = new q42();
	 System.out.println(m.solution(p,list));
	 }

	private String solution(String p, String list) {
		String answer="NO";
		Queue<Character> q = new LinkedList<>();
		for(char a: list.toCharArray()) {
			q.offer(a);//CBDAGE 순으로 집어넣음
		}
		Queue<Character> q2 = new LinkedList<>();
		for(char a: p.toCharArray()) {
			q2.offer(a);//CBA 순으로 집어넣음
		}
		for(char a: list.toCharArray()) {
			if(q2.peek()==a) {
				q.offer(q.poll());//꺼내서 뒤로집어넣음
				q2.offer(q2.poll());//꺼내서 뒤로집어넣음
			}else {
				q.poll();//꺼냄
			}
		}
		if(q.equals(q2)) answer="YES";
		return answer;
	}
}
