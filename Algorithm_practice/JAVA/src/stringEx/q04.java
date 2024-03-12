package stringEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class q04 {
	
	public static List<String> solution(String[] str, int a) {
		List<String> list = new ArrayList<>();
		int i =0;
		while(i<a) {
			char[] c = str[i].toCharArray();//문자열을 문자배열로 변환 a,p,p,l,e
			int j=0, l=c.length-1;
			
			while(j<l) {//문자열 뒤집기 공식
				char tmp = c[j];
				c[j]=c[l];
				c[l]=tmp;
				j++;
				l--;
			}
			String tmp = String.valueOf(c);//.valueOf(c) : 배열c를 스트링화 시켜줌 
			list.add(tmp);
			i++;
			}
		return list;
	}
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();
		String[] str = new String[a];
		for(int i=0;i<a;i++) {
			str[i]=scan.next();
		}
		q04 m = new q04();
		for(String x:m.solution(str, a)) {
			System.out.println(x);
		}
		
	}
}
