package arrayEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class q13 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int p = in.nextInt();
		int[] arr = new int[p];
		for(int i=0;i<p;i++) {
			arr[i]=in.nextInt();
		}
		q13 m = new q13();
		for(Integer integer: m.solution(p, arr)) {
			System.out.print(integer+" ");
		}
	}

	private List<Integer> solution(int p, int[] arr) {
		List<Integer> list = new ArrayList<>();
		int min = Integer.MIN_VALUE;
		for(int i=0; i<p;i++) {
			if( arr[i] > min ) {
				list.add(arr[i]);
			}
			min=arr[i];
		}
		return list;
	}
}
