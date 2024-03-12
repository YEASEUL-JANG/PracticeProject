package stringEx;

import java.util.Scanner;

public class q01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		char a = scan.next().charAt(0);
		q01 t = new q01();
		System.out.println(t.Solution(str,a));
		
	}

	public int Solution(String str, char a) {
			int answer = 0;
			str = str.toUpperCase();
			a = Character.toUpperCase(a);
			
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i)==a) answer++;
			}
		return answer;
	}

}
