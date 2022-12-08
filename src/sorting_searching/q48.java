package sorting_searching;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class q48 {
	public static void main(String[] args){
	    Scanner in=new Scanner(System.in);
	    int n = in.nextInt();
	    int[] arr = new int[n];
	    for(int i=0;i<n;i++) {
	    	arr[i]=in.nextInt();
	    }
	    q48 m = new q48();
	    System.out.println(m.solution(n,arr));
	}

	private char solution(int n, int[] arr) {
		char answer='U';
		Queue<Integer> q = new LinkedList<>();
		for(int i:arr) {
			if(!q.contains(i)) q.offer(i);
			else {
				return 'D';
			}
		}
		return answer;
	}
}
