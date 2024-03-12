package stact_Queue;

import java.util.Scanner;
import java.util.Stack;

public class q36 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String tmp = in.next();
		q36 m = new q36();
		System.out.println(m.solution(tmp));
	}

	private String solution(String tmp) {
		String answer="YES";
		Stack<Character> stack = new Stack<>();
		for(int i=0;i<tmp.length();i++) {
			if(tmp.charAt(i)=='(') {
				stack.push(tmp.charAt(i));//스택에 값을 집어넣음
			}else if(tmp.charAt(i)==')') {
				if(stack.isEmpty()) return "NO";//닫는 괄호가 많은상황
				stack.pop();//가장 최근에 들어간 요소를 삭제함
			}
		}
		if(!stack.isEmpty()) return "NO";//여는 괄호가 많은 상황
		return answer;
	}
}
