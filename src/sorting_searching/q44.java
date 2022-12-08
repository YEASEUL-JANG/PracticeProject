package sorting_searching;

import java.util.Scanner;

public class q44 {
	public static void main(String[] args){
	    Scanner in=new Scanner(System.in);
	    int n = in.nextInt();
	    int[] arr = new int[n];
	    for(int i=0;i<n;i++) {
	    	arr[i]=in.nextInt();
	    }
	    q44 m = new q44();
	    for(Integer i: m.solution(n, arr)) {
	    	System.out.print(i+" ");
	    }
	}

	private int[] solution(int n, int[] arr) {
		int idx=0;
		for(int i=0;i<n-1;i++) {
			idx=i;//인덱스 초기화
			for(int j=i+1;j<n;j++) {
				if(arr[idx]>arr[j]) {
					idx=j;//for문을 돌면서 가장 작은값의 인덱스를 저장
				}//if
			}//inner for
			int tmp = arr[i];//바꿀값 저장
			arr[i]=arr[idx];//작은수랑 바꿈
			arr[idx]=tmp;
		}
		return arr;
	}
}
