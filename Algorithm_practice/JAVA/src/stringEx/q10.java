package stringEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class q10 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.next();
		char t = in.next().charAt(0);	
		q10 m = new q10();
		for(int x : m.solution(str, t)) {
			System.out.print(x+" ");
		}
	}

	private int[] solution(String str, char t) {
		String answer="";
		int result[] = new int[str.length()];
		int p=1000;
		//왼쪽부터 검사
		for(int i=0; i<str.length(); i++) {
			//타겟 문자와 문자열.charAt(i)가 같다면 타겟문자와의 거리는 0, 타겟 인덱스는 i가 된다.
			if(str.charAt(i)== t) {
				p = 0;
				result[i] = p;
			}else {//같지 않다면 절대값으로 바꿔주는 Math.abs()를 사용해 결과값에 targetindex-i를 넣어준다.
				p++;
				result[i]=p;
			}
		}
		p=1000;//p값 초기화
		//오른쪽부터 검사
		for(int i=str.length()-1; i>=0;i--) {
			if(str.charAt(i)== t) {
				//타겟 인덱스만 새롭게 지정
				p=0;
			}else {
				p++;
				result[i] = Math.min(result[i], p);
			}
		}
		return result;
	}
}
