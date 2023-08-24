package arrayEx;

import java.util.Scanner;

public class q20 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int[] list = new int[num];
		for(int i=0;i<num;i++) {
			list[i]=in.nextInt();
		}
		q20 m =  new q20();
		for(Integer integer: m.solution(num, list)) {
			System.out.print(integer+" ");
		}
	}
 //87 89 100 92 76
	private int[] solution(int num, int[] list) {
		int[] answer = new int[num];
		int cnt=1;
		for(int i=0;i<num;i++) {
			for(int j=0; j<num;j++) {
				if(list[i]<list[j]) cnt++;//작은 등수만큼 플러스됨
			}
			answer[i]=cnt;
			cnt=1;//다시 초기화
		}
		return answer;
	}
}
