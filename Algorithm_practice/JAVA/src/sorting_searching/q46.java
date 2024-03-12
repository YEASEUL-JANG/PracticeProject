package sorting_searching;

import java.util.Scanner;

public class q46 {
	public static void main(String[] args){
	    Scanner in=new Scanner(System.in);
	    int n = in.nextInt();
	    int[] arr = new int[n];
	    for(int i=0;i<n;i++) {
	    	arr[i]=in.nextInt();
	    }
	    q46 m = new q46();
	    for(Integer i: m.solution(n, arr)) {
	    	System.out.print(i+" ");
	    }
	}

	private int[] solution(int n, int[] arr) {
		for(int i=1;i<n;i++) {
			int tmp = arr[i], j;
			for(j=i-1;j>=0;j--) {
				if(tmp<arr[j]) {//i값의 왼쪽요소j가 i값보다 크면 
					arr[j+1]=arr[j]; //j값 뒤로 밀림(j값 연속중복 상태)
				}else break;//j값이 tmp보다 크지 않으면 j--멈춤
			}
			arr[j+1]=tmp;//j값 바로 뒤에 tmp 삽입
		}//항상 j for문이 멈춘 뒷편에 tmp를 삽입하면 된다.
		return arr;
	}
}
