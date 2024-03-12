package twopointersEx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class q26 {
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
		q26 m = new q26();
		for(Integer i : m.solution(a,aa,b,bb)) {
			System.out.print(i+" ");
		}
	}

	private List<Integer> solution(int a, int[] aa, int b, int[] bb) {
		List<Integer> list = new ArrayList<>();
		
		Arrays.sort(aa);
		Arrays.sort(bb);//두 배열 오름차순으로 정렬
		
		int p1=0, p2=0;
		while(p1<a && p2<b) {
			if(aa[p1]==bb[p2]) {//두 요소가 같으면
				list.add(aa[p1++]);
				p2++;
			}else if(aa[p1]<bb[p2]) {//한쪽이 더 큰경우
				p1++;//작은값의 요소만 +
			}else {//반대의 경우
				p2++;
			}
		}
		return list;
	}
}
