package arrayEx;

import java.util.Scanner;

public class q24 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();//학생수
		int m = in.nextInt();//테스트횟수
		int[][] nm = new int[m+1][n+1];
		for(int i=1;i<=m;i++) {
			for(int j=1;j<=n;j++) {
				nm[i][j]=in.nextInt();
			}
		}
		q24 main = new q24();
		System.out.println(main.solution(n,m,nm));
	}

	private int solution(int n, int m, int[][] nm) {
		int answer=0;
		//비교할 학생수(2명)을 i학생과 j학생으로 정해놓고 비교한다.
		for(int i=1;i<=n;i++) {//i학생의 번호(멘토)
			for(int j=1;j<=n;j++) {//j학생의 번호(멘티), 여기까지가 조합할 수 있는 모든 경우의 수
				int cnt=0;
				for(int k=1;k<=m;k++) {//테스트횟수
					int pi=0,pj=0;
					for(int s=1;s<=n;s++) {//등수
						if(nm[k][s]==i) pi=s;//k번테스트의 s등이 i와 같으면, i의 등수(pi)는 s
						if(nm[k][s]==j) pj=s;//k번테스트의 s등이 j와 같으면, j의 등수(pi)는 s
					}
					if(pi<pj) cnt++;//멘토(i)등수가 멘티(j)등수보다 낮으면 카운트
				}
				if(cnt==m)answer++;//카운트된 값이 테스트횟수와 동일하면
			}
		}
		return answer;
	}
}
