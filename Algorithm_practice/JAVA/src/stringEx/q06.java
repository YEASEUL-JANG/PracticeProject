package stringEx;

import java.util.Scanner;

public class q06 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		q06 m = new q06();
		System.out.println(m.solution(str));

	}
	public String solution(String str) {
		String answer = "";
		for(int i=0;i<str.length();i++) {
			//중복되는 문자는 자신의 인덱스값과 str.indexOf(str.charAt(i)) 값이 다르다.
			if(str.indexOf(str.charAt(i))==i) { //같다면 중복이 아님
				answer +=str.charAt(i);
			}
		}
		return answer;
	}
}
