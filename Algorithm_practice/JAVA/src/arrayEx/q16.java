package arrayEx;

import java.util.Scanner;

public class q16 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		q16 m = new q16();
		for(Integer integer: m.solution(num)) {
			System.out.print(integer+" ");
		}
	}

	private int[] solution(int num) {
		int[] answer= new int[num];
		answer[0]=1;
		answer[1]=1;
		for(int i=2;i<num;i++) {
			answer[i]=answer[i-2]+answer[i-1];
		}
		return answer;
	}
}
