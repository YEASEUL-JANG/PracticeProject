package twopointersEx;

import java.util.Scanner;

import arrayEx.q24;

public class q30 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=in.nextInt();
		}
		q30 m = new q30();
		System.out.println(m.solution(n,k,arr));
	}

	private int solution(int n, int k, int[] arr) {
		int answer=0;
		//lt는 왼쪽 포인터, rt는 오른쪽으로 이동하는 포인터 두개를 준비
		int lt=0;
		for(int rt=0;rt<n;rt++) {//rt가 오른쪽으로 하나씩 이동하면서 
			if(arr[rt]==0) {//값이 0이면 k값 -1
				k--;
			}
			while(k<0) {//2개를 쓰고난 후 또 0이 나온상태 : lt를 오른쪽으로 이동시켜서 0 다음으로 고정시킴.
				if(arr[lt]==0)k++;//arr[lt]의 값이 0이면 k+
				lt++;
			}
			answer=Math.max(answer, rt-lt+1);
		}
		return answer;
	}
}
