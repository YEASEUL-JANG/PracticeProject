package stact_Queue;

import java.util.Scanner;
import java.util.Stack;

public class q39 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		q39 m = new q39();
		String tmp = in.next();
		System.out.println(m.solution(tmp));
	}

	private int solution(String tmp) {
		int answer=0;
		int p = 0;
		int j=0, k=0;
		Stack<Integer> stack = new Stack<>();
		for(char a: tmp.toCharArray()) {
			if(Character.isDigit(a)) {//a가 숫자이면
				stack.push(a-'0');
			}else if(a=='+'){//숫자가 아니면
				j=stack.pop();//먼저 꺼냄
				k=stack.pop();//그 다음 꺼냄
				p=k+j;
				stack.push(p);
			}else if(a=='-'){//숫자가 아니면
				j=stack.pop();//먼저 꺼냄
				k=stack.pop();//그 다음 꺼냄
				p=k-j;
				stack.push(p);
			}else if(a=='*'){//숫자가 아니면
				j=stack.pop();//먼저 꺼냄
				k=stack.pop();//그 다음 꺼냄
				p=k*j;
				stack.push(p);
			}else {
				j=stack.pop();;//먼저 꺼냄
				k=stack.pop();;//그 다음 꺼냄
				p=k/j;
				stack.push(p);
			}
		}answer=stack.pop();
		return answer;
	}
}
