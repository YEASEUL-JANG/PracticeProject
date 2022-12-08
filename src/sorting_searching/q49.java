package sorting_searching;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class q49 {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int n = in.nextInt();
		int[] list = new int[n];
		for(int i=0;i<n;i++) {
			list[i]=in.nextInt();
		}
		q49 m = new q49();
		for(int i : m.solution(n, list)) {
			System.out.print(i+" ");
		}
	}

	private Queue<Integer> solution(int n, int[] list) {
		Queue<Integer> q = new LinkedList<>();
		int[] tmp = new int[n];
		tmp=list.clone();//배열복사방법1.
		//배열복사방법2. System.arraycopy(list, 0, tmp, 0, n);
		Arrays.sort(tmp);//오름차순으로 정렬
		for(int i=0;i<n;i++) {
			if(list[i]!=tmp[i]) q.offer(i+1);
		}
		return q;
	}
}
