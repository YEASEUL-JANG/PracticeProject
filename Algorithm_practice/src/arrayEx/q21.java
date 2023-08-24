package arrayEx;

import java.util.Scanner;

public class q21 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int p = in.nextInt();
		q21 m = new q21();
		int[][] nn = new int[p][p];
		for(int i =0;i<p;i++) {
			for(int j=0;j<p;j++) {
				nn[i][j]=in.nextInt();
			}
		}
		System.out.println(m.solution(p,nn));
	}

	private int solution(int p, int[][] nn) {
		int sum1;//행의 합
		int sum2;//열의 합
		int sum3=0;//대각선의 합(>방향)
		int sum4=0;//대각선의 합(<방향)
		int answer = Integer.MIN_VALUE;//최솟값
		for(int i=0;i<p;i++) {//p번 반복
			sum1=0; sum2=0;//초기화
			for(int j=0;j<p;j++) {
				sum1+=nn[i][j];//행의 합
				sum2+=nn[j][i];//열의 합
			}
			answer=Math.max(answer, sum1);//큰값으로 갱신
			answer=Math.max(answer, sum2);//큰값으로 갱신
			sum3+=nn[i][i];
			sum4+=nn[i][p-i-1];
		}//for문
		answer=Math.max(answer, sum3);//큰값으로 갱신
		answer=Math.max(answer, sum4);//큰값으로 갱신
		return answer;
	}
}
