package stact_Queue;

import java.util.Scanner;
import java.util.Stack;

public class q40 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		q40 m =  new q40();
		String tmp = in.next();
		System.out.println(m.solution(tmp));
	}

	private int solution(String tmp) {
		int answer=0;
		Stack<Character> stack = new Stack<>();
		for(int i=0;i<tmp.length();i++) {
			if(tmp.charAt(i)=='(') {
				stack.push(tmp.charAt(i));
			}else {//')'이면
				if(tmp.charAt(i-1)=='(') {
					stack.pop();
					answer+=stack.size();
				}else {//')'이면
					stack.pop();//'(하나 꺼냄(막대기 끝남)
					answer+=1;
				}
			}
		}
		
		return answer;
	}
}
