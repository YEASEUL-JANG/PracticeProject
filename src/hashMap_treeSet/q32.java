package hashMap_treeSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class q32 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String a = in.next();
		String b = in.next();
		q32 m = new q32();
		System.out.println(m.solution(a,b));
	}

	public String solution(String a, String b) {
		String answer="";
		Map<Character,Integer> map = new HashMap<>();
		for(int i=0;i<a.length();i++) {
			map.put(a.charAt(i), map.getOrDefault(a.charAt(i), 0)+1);
		}
		//문자열 b를 확인하면서 map에 해당문자로된 키가 없거나 value가 0이면 NO
		for(char p: b.toCharArray()) {
			if(!map.containsKey(p) || map.get(p)==0) return "NO";
			map.put(p,map.get(p)-1);
		}
		
		return answer;
	}
}
