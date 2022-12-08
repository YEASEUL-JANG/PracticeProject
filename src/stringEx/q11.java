package stringEx;

import java.util.Scanner;

public class q11 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.next()+" ";//마지막 문자가 저장될 수 있도록 문자열 빈칸 하나를 더 준다.
		q11 m = new q11();
		System.out.println(m.solution(str));
	}

	private String solution(String str) {
		String answer="";
		
		int p = 1;
		for(int i=0;i<str.length()-1;i++) {
			if(str.charAt(i)==str.charAt(i+1)) {//앞문자와 같을때
				if(p==1) {//p가 초기화 상태일때만
				answer+=str.charAt(i);
				}
				p++;
			}else {//앞 문자와 다를때
				if(p>1) {//p가 1보다 클때만
				answer+=p;
				}else {//p가 초기화상태이면 기록
					answer+=str.charAt(i);
				}
				p=1;//p값 초기화
			}
		}
		return answer;
	}
}
