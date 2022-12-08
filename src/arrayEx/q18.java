package arrayEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class q18 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		String[] tmp = new String[num];
		int[] tmp2 = new int[num];
		for(int i =0; i<num;i++) {
			tmp[i]=in.next();
			tmp2[i]=Integer.parseInt(new StringBuilder(tmp[i]).reverse().toString());
		}
		q18 m = new q18();
		for(Integer integer:m.solution(num, tmp2)) {
			System.out.print(integer+" ");
		}
	}

	private List<Integer> solution(int num, int[] tmp2) {
		List<Integer> answer = new ArrayList<>();
		for(int i=0; i<num;i++) {
			if(isPrime(tmp2[i])) answer.add(tmp2[i]);
		}
		
		return answer;
	}

	private boolean isPrime(int num) {
		if(num==1) return false;
		for(int i=2;i<num;i++) {
			if(num%i==0) return false;
		}
		return true;
	}
}
