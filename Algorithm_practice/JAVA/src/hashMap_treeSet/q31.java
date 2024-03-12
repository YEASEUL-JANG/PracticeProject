package hashMap_treeSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class q31 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String str = in.next();
		
		q31 m = new q31();
		System.out.println(m.solution(n,str));
	}

	private char solution(int n, String str) {
		char answer=' ';
		int min=Integer.MIN_VALUE;
		Map<Character,Integer> map = new HashMap<>();
		for(char a: str.toCharArray()) {
			map.put(a,map.getOrDefault(a,0)+1);
		}
		for(char a: map.keySet()){
			if(map.get(a)>min) {
				min=map.get(a);
				answer=a;
			}
		}
		return answer;
	}
}
