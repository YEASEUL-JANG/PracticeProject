package stringEx;

import java.util.Scanner;

public class q07 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.next();
		q07 m = new q07();
		System.out.println(m.solution(str));
	}

	private String solution(String str) {
		String answer="NO";
		//글자 뒤집기 공식
		String tmp = new StringBuilder(str).reverse().toString();
		if(str.equalsIgnoreCase(tmp)) answer="YES";
		return answer;
	}
}
