package twopointersEx;

import java.util.Scanner;

public class q29 {
	public static void main(String[] args){
	    Scanner in=new Scanner(System.in);
	    int n = in.nextInt();
	    q29 m = new q29();
	    System.out.println(m.solution(n));
	}

	private int solution(int n) {
		int answer = 0, cnt=1;
		n--;
		while(n>0) {
			cnt++;//cnt는 연속된 숫자의 갯수
			n=n-cnt;
			//n에서 1부터 연속된 숫자들을 뺀 수가 cnt값으로 나눠 떨어지면 해당 몫을 더한 값이 답이되기 때문
			if(n%cnt==0) {
				answer++;
			}
		}
		return answer;
	}
}
