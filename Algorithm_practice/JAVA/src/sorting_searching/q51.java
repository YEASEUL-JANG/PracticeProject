package sorting_searching;

import java.util.Arrays;
import java.util.Scanner;

public class q51 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=in.nextInt();
		}
		q51 main = new q51();
		System.out.println(main.solution(n,m,arr));
	}

	private int solution(int n, int m, int[] arr) {
		int answer=0;
		Arrays.sort(arr);//오름차순으로 정렬
		int lt=0, rt=n-1, mid=0;
		for(int i=lt;i<=rt;i++) {
			mid=(i+rt)/2;
			if(arr[mid]==m) answer=mid+1;
			else if(arr[mid]<m)lt=mid+1;
			else if(arr[mid]>m)rt=mid-1;
		}
		return answer;
	}
}
