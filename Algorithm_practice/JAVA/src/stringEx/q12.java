package stringEx;

import java.util.Scanner;

public class q12 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int p = in.nextInt();
		String str = in.next();
		q12 m = new q12();
		System.out.println(m.solution(p,str));
	}

	private String solution(int p, String str) {
		String answer="";
		//str을 이진수로 변환
		String str1=str.replace("#", "1").replace("*","0");//2진수로 변환
		String str2="";//7개씩 나눈 문자열을 담을 변수
		int in=0;
		 for(int i=0; i<p; i++) { //문자갯수만큼 반복한다.
			 str2=str1.substring(7*i,7*(i+1)); //문자열을 7개씩 나누고
			 in=Integer.parseInt(str2,2);//나눈문자열을 2진수에서 10진수로 변환
			 answer+=(char)in;//숫자->문자
			 }
		 
		return answer;
	}
}
