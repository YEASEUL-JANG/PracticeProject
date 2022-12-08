package stringEx;

import java.util.Scanner;

public class q03 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		q03 t = new q03();
		System.out.println(t.solution(str));
	}

	public String solution(String str) {
		String answer="";
		/*int m = Integer.MIN_VALUE;//가장 작은 정수 설정
		String[] word = str.split(" ");
		for(String a:word) {
			int c = a.length();
			//최대값 구하기 알고리즘
			if(c>m) {//기존 길이보다 더 긴 단어가 오면 참
				m=c;
				answer = a;
			}*/
		
		//indexOf와 substring 활용하기
		int pos;
		int m = Integer.MIN_VALUE;
		//문자열에서 공백이 오면 자릿수를 반환함. 공백이 없으면 -1 출력
		while((pos=str.indexOf(' ')) != -1) {
			String tmp = str.substring(0,pos);//공백 전까지 문자열을 자름
			int len = tmp.length();
			if(len>m) {
				m=len;
				answer=tmp;
			}
			str=str.substring(pos+1);//공백 후부터 끝까지로 문자열을 대체함.
		}
		if(str.length()>m) answer = str; //마지막 단어 처리
		return answer;
	}
}
