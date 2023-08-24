package practice;
import java.util.Scanner;

public class Synap{
	long[] memo = new long[100];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long a = in.nextLong();
		long b = in.nextLong();
		Synap main = new Synap();
		System.out.println(main.solution(a,b));
	}
	public long solution(long a, long b) {
		long answer = 0;
		int n=2;
		while(fivo(n)<=b) {
			if(fivo(n)>=a && fivo(n)<=b) {
				answer+=fivo(n);
			}
			n++;
		}
		return answer;
	}
	public long fivo(int n) {
		if(n==0) return 1;
		else if(n==1) return 2;
		else if(memo[n] != 0) return memo[n];
		else return memo[n]=fivo(n-2)+fivo(n-1);
	}
}