package sorting_searching;

import java.util.Arrays;
import java.util.Scanner;

public class q52 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=in.nextInt();
		}
		q52 main = new q52();
		System.out.println(main.solution(n,m,arr));
	}
	
	private int solution(int n, int m, int[] arr) {
		int answer=0;
		int lt=Arrays.stream(arr).max().getAsInt();//arr배열의 최대값
		int rt=Arrays.stream(arr).sum();//arr배열의 총합
		while(lt<=rt) {
			int mid=(lt+rt)/2;
			if(count(arr,mid)<=m) {
				answer=mid;
				rt=mid-1;
			}else {
				lt=mid+1;
			}
		}
		return answer;
	}

	public int count(int[] arr, int mid) {
		int cnt=1;//음반 한장
		int sum=0;//한장의 음반안에 녹음된 용량
		for(int x:arr) {
			if(sum+x>mid) {//한장 용량을 넘어가면
				cnt++;//다음장에
				sum=x;//값을넣음
			}else {sum+=x;}
		}
		return cnt;
	}
}
