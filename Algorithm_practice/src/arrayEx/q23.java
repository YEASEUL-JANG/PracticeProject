package arrayEx;

import java.util.Scanner;

public class q23 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int p = in.nextInt();
		q23 m = new q23();
		int[][] nn = new int[p+1][6];
		for(int i=1;i<=p;i++) {
			for(int j=1;j<=5;j++) {
				nn[i][j]=in.nextInt();
			}
		}
		System.out.println(m.solution(p,nn));
	}

	private int solution(int p, int[][] nn) {
		int count;
		int answer=0;
		int min = Integer.MIN_VALUE;
		for(int i=1;i<=p;i++) {//i번 학생
			count=0;
			for(int j=1;j<=p;j++) {//j번 학생
				for(int k=1;k<=5;k++) {//학년변수
					if(nn[i][k]==nn[j][k]) {//i번 학생의 k학년의 반과 j번 학생의 k학년의 반이 같으면
						count++;
						break;//i학생과 j학생을 비교할 때 카운팅은 한번만 되어야 하기 때문에 카운트가 됐으면 break로 빠져나옴.
					}//if
				}//for
			}//for
			if(count>min) {
				answer=i;
				min=count;
			}
		}//for
		return answer;
	}
}
