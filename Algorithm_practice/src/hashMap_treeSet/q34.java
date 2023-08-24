package hashMap_treeSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class q34 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.next();//a
		String t = in.next();//b
		q34 m = new q34();
		System.out.println(m.solution(str,t));
	}

	private int solution(String str, String t) {
		int answer=0;
		int L = t.length()-1; //t길이보다 한개 줄인 수
		Map<Character,Integer> map = new HashMap<>();
		Map<Character,Integer> map2 = new HashMap<>();
		//map2에 abc를 집어넣는다.
		for(char x: t.toCharArray()) map2.put(x, map2.getOrDefault(x, 0)+1);
		//map에 str문자열 맨앞문자를 L개만큼만 집어넣는다.
		for(int i=0;i<L;i++) map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0)+1);
		int lt=0;
		for(int rt=L;rt<str.length();rt++) {
			map.put(str.charAt(rt), map.getOrDefault(str.charAt(rt),0)+1);
			if(map.equals(map2)) answer++;//map과 map2 비교
			map.put(str.charAt(lt), map.get(str.charAt(lt))-1);
			if(map.get(str.charAt(lt))==0) map.remove(str.charAt(lt));
			lt++;
		}
		return answer;
	}
}

