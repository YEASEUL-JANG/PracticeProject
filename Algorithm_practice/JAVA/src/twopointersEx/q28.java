package twopointersEx;

import java.util.Scanner;

public class q28 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		q28 main = new q28();
		int n = in.nextInt();//n개의 수열
		int m = in.nextInt();//합이 m
		int[] nm = new int[n];
		for(int i=0;i<n;i++) {
			nm[i]=in.nextInt();
		}
		System.out.println(main.solution(n,m,nm));
	}

	private int solution(int n, int m, int[] nm) {
		int answer=0;
		int sum=0;
		int p1=0,p2=0;
		while(p1<n&&p2<n) {
			sum+=nm[p2];
			if(sum==m) {
				answer++;
				sum=0;
				p1++;
				p2=p1;
			}else if(sum<m) {
				p2++;
			}else {//sum>m
				p1++;
				p2=p1;
				sum=0;
			}
		}
		return answer;
	}
}
