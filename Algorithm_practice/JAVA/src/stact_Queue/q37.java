package stact_Queue;

import java.util.Scanner;
import java.util.Stack;

public class q37 {
	public static void main(String[] args) {
		q37 m = new q37();
		Scanner in = new Scanner(System.in);
		String tmp = in.next();
		System.out.println(m.solution(tmp));
	}

	private String solution(String tmp) {
		String answer="";
		Stack<Character> stack = new Stack<>();
		for(int i=0;i<tmp.length();i++) {
			if(tmp.charAt(i)==')') {
				while(stack.pop()!='(');//시작 괄호까지 꺼내고 멈춘다.
			}else{
				stack.push(tmp.charAt(i));//닫는 괄호가 아닌 요소들은 다 push
			}
		}
		for( int i=0;i<stack.size();i++) {
			answer+=stack.get(i);//스택의 요소를 가져온다.
		}
		
		return answer;
	}
}
