package arrayEx;

import java.util.Scanner;

public class q15 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int[] a = new int[num];
		int[] b = new int[num];
		for(int i=0;i<num;i++) {
			a[i] = in.nextInt();
		}
		for(int i=0;i<num;i++) {
			b[i] = in.nextInt();
		}
		q15 m = new q15();
		for(char c:m.solution(num, a, b) ) {
			System.out.println(c);
		}
	}

	private char[] solution(int num, int[] a, int[] b) {
		char[] answer = new char[num];
		for(int i=0;i<num;i++) {
			if(a[i]==b[i]) answer[i]='D';//a가 비기는 경우
			//a가 이기는경우 3가지
			else if(a[i]==1 && b[i]==3) answer[i]='A';
			else if(a[i]==2 && b[i]==1) answer[i]='A';
			else if(a[i]==3 && b[i]==2) answer[i]='A';
			//나머지는 b가 이기는 경우
			else answer[i]='B';
		}
		return answer;
	}
}
