package twopointersEx;

import java.util.Scanner;

public class q27 {
	public static void main(String[] args){
	    Scanner in=new Scanner(System.in);
	    int a = in.nextInt();//일 수
	    int b = in.nextInt();// 연속된 날짜
	    int[] aa= new int[a];
	    for(int i=0;i<a;i++) {
	    	aa[i]=in.nextInt();
	    }
	    q27 m = new q27();
	    System.out.println(m.solution(a,b,aa));
	}

	private int solution(int a, int b, int[] aa) {
		int answer=0, sum=0;
		for(int i=0;i<b;i++) {
			sum+=aa[i];
		}//처음 연속값의 합을 sum에 저장해놓는다
		answer=sum;
		for(int i=b;i<a;i++) {
			sum=sum+aa[i]-aa[i-b];
			answer=Math.max(answer, sum);
		}
		return answer;
	}
}
