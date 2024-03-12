package sorting_searching;

import java.util.Scanner;

public class q45 {
	public static void main(String[] args){
	    Scanner in=new Scanner(System.in);
	    int n = in.nextInt();
	    int[] arr = new int[n];
	    for(int i=0;i<n;i++) {
	    	arr[i]=in.nextInt();
	    }
	    q45 m = new q45();
	    for(Integer i: m.solution(n, arr)) {
	    	System.out.print(i+" ");
	    }
	}

	private int[] solution(int n, int[] arr) {
		int tmp=0;
		for(int i=1;i<=n-1;i++) { //n-1번만 돌면 된다.
			for(int j=0;j<n-i;j++) {
				tmp=0;
				if(arr[j]>arr[j+1]) {
					tmp=arr[j+1];//tmp에 작은값을 담고
					arr[j+1]=arr[j];//작은값에 큰값 삽입
					arr[j]=tmp;//큰값에 작은값 대입
				}
			}
		}
		return arr;
	}
}
