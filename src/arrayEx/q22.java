package arrayEx;

import java.util.Scanner;

public class q22 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int p = in.nextInt();
		q22 m = new q22();
		int[][] nn = new int[p+2][p+2];
		for(int i =1;i<=p;i++) {
			for(int j=1;j<=p;j++) {
				nn[i][j]=in.nextInt();
			}
		}
		System.out.println(m.solution(p,nn));
	}

	private int solution(int p, int[][] nn) {
		int answer=0;
		for(int i=1;i<=p;i++) {
			for(int j=1;j<=p;j++) {
				if(nn[i][j-1]<nn[i][j]&&nn[i-1][j]<nn[i][j]&&
						nn[i][j+1]<nn[i][j]&&nn[i+1][j]<nn[i][j]) {
					answer++;
				}
			}
		}
		return answer;
	}
}
