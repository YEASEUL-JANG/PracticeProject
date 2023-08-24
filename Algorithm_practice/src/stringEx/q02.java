package stringEx;

import java.util.Scanner;

public class q02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		q02 t = new q02();
		System.out.println(t.Solution(str));
		
	}

	public String Solution(String str) {
			String answer = "";
			/*char[] a = str.toCharArray();
			
			for(int i=0; i<a.length; i++) {
				if(Character.isUpperCase(a[i])) {
					answer += Character.toLowerCase(a[i]);
				}else {
					answer += Character.toUpperCase(a[i]);
				}*/
			
			//향상된 for문
			for(char a:str.toCharArray()) {
				if(Character.isUpperCase(a)) {
					answer+=Character.toLowerCase(a);
				}else {
					answer+=Character.toUpperCase(a);
				}
			}
		return answer;
	}

}
