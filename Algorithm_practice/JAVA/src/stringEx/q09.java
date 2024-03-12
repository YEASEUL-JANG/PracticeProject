package stringEx;

import java.util.Scanner;

public class q09 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.next();
		q09 m = new q09();
		System.out.println(m.solution(str));
	}

	private int solution(String str) {
		String answer="";
		char[] a = str.toCharArray();
		//배열로 하나씩 돌면서 숫자이면 answer에 저장한다.
		for(char i : a) {
			if(Character.isDigit(i)) {
				answer+=i;
			}
		}
		return Integer.parseInt(answer);
	}
}
