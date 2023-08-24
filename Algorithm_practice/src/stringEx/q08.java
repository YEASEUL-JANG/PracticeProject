package stringEx;

import java.util.Scanner;

public class q08 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		q08 m = new q08();
		System.out.println(m.solution(str));
	}

	private String solution(String str) {
		String answer="NO";
		String str1=str.toUpperCase().replaceAll("[^A-Z]", "");
		String str2= new StringBuilder(str1).reverse().toString();
		if(str1.equalsIgnoreCase(str2)) {
			answer="YES";
		}
		return answer;
	}
}
