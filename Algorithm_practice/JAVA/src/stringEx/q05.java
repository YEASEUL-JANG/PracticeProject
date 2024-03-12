package stringEx;

import java.util.Scanner;

public class q05 {
	public String solution(char[] c) {
		int i = 0, j = c.length-1;
		while(i<j) {
			if(!Character.isAlphabetic(c[i])) {
				i++;
			}else if(!Character.isAlphabetic(c[j])){
				j--;
			}else {
			char tmp = c[i];
			c[i]=c[j];
			c[j]=tmp;
			i++;
			j--;
			}
		}String tmp = String.valueOf(c);
		return tmp;
	}
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		char[] c = str.toCharArray();
		q05 m = new q05();
		
		System.out.println(m.solution(c));
	}
}
