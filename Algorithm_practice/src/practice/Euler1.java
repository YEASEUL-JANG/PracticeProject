package practice;

import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Euler1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long a = in.nextLong();
		Euler1 main = new Euler1();
		System.out.println(main.solution(a));
	}

	private long solution(long a) {
		long answer=0;
		long i=2l;
		TreeSet<Long> set = new TreeSet<>(Collections.reverseOrder());//내림차순으로 중복없이 값을 정렬
		while(i<=a) {
			if(a%i==0) {
				set.add(i);
				a/=i;
			}else {
				i++;
			}
		}
		answer= set.first();
		return answer;
	}
}
