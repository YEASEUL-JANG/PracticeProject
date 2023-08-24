package arrayEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class q17 {
	public static void main(String[] args) {
		long beforeTime = System.currentTimeMillis();
		Scanner in= new Scanner(System.in);
		int n = in.nextInt();
		int[] num = new int[n+1];//20이면 20번째 인덱스까지 만들기위해서
		q17 m = new q17();
		System.out.println(m.solution(n,num));
		long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
		System.out.println("시간차이(ms) : "+secDiffTime);
	
	}
	private int solution(int n, int[] num) {
		int number=0;
		for(int i=2;i<=n;i++) {//2일때
			if(num[i]==0) number++;
			for(int j=i; j<=n;j=j+i) {//2의 배수들 모두 1로 변환
				num[j]=1;
			}
		}
		return number;
	}
}