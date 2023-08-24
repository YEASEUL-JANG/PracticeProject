package twopointersEx;

import java.util.Arrays;
import java.util.Scanner;

public class q25 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int[] aa = new int[a];
		for(int i=0;i<a;i++) {
			aa[i]=in.nextInt();
		}
		int b = in.nextInt();
		int[] bb = new int[b];
		for(int i=0;i<b;i++) {
			bb[i]=in.nextInt();
		}
		q25 m = new q25();
		for(Integer integer: m.solution(a,aa,b,bb)) {
			System.out.print(integer+" ");
		}
	}

	private int[] solution(int a, int[] aa, int b, int[] bb) {
		int[] answer=new int[a+b];
		
		System.arraycopy(aa, 0, answer, 0, a);
		System.arraycopy(bb, 0, answer, a, b);
		Arrays.sort(answer);
		
		return answer;
	}
}
