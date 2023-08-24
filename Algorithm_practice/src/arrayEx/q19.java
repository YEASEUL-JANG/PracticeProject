package arrayEx;

import java.util.Scanner;

public class q19 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int[] list = new int[num];
		for(int i=0; i<num; i++) {
			list[i]=in.nextInt();
		}
		q19 m = new q19();
		int answer = 0;
		for(Integer integer: m.solution(num, list)) {
			answer += integer;
		}
		System.out.println(answer);
	}

	private int[] solution(int num, int[] list) {
		int[] answer = new int[num];
		int tmp = 0;
		for(int i=0;i<num;i++) {
			if(list[i]==1) {
				tmp++;
				answer[i]=tmp;
			}else {
				tmp=0;
			}
		}
		return answer;
	}
}
