package practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Synap3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		Synap3 main = new Synap3();
		System.out.println(main.solution(a));
	}

	private String solution(int a) {
		String answer="";
		Map<Integer,Integer> map = new HashMap<>();
		int i = 2;
		while(i<=a) {
			if(a%i==0) {
				map.put(i, map.getOrDefault(i,0)+1);
				a=a/i;
			}else {
				i++;
			}
		}
		for(Integer p: map.keySet()) {
			answer+=p;
			answer+="^";
			answer+=map.get(p);
			answer+="*";
		}
		int size = answer.length();
		String tmp = "";
		if(answer.charAt(size-1)=='*') {
			tmp = answer.substring(0,size-1);
		}
		return tmp;
	}
}
