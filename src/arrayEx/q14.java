package arrayEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class q14 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] nn = new int[n];
		for(int i = 0; i<n;i++) {
			nn[i]=in.nextInt();
		}
		q14 m = new q14();
		System.out.println(m.solution(n,nn));
	}

	private int solution(int n, int[] nn) {
		List<Integer> list = new ArrayList<>();
		int min = Integer.MIN_VALUE;
		for(int i=0;i<n;i++) {
			if(nn[i]>min) {
				list.add(nn[i]);
			}
			if(min<nn[i]) {
				min=nn[i];
			}
		}
		
		return list.size();
	}
}
