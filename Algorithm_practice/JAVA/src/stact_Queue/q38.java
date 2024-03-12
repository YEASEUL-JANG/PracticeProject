package stact_Queue;

import java.util.Scanner;
import java.util.Stack;

public class q38 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();//n
		int[][] board = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				board[i][j]=in.nextInt();//1-5까지 2차원배열 입력
			}
		}
		int m = in.nextInt();
		int[] moves=new int[m];
		for(int i=0;i<m;i++) {
			moves[i]=in.nextInt();
		}
		q38 main = new q38();
		System.out.println(main.solution(n,board,m,moves));
		
	}

	private int solution(int n, int[][] board, int m, int[] moves) {
		int answer=0;
		Stack<Integer> stack = new Stack<>();
		for(int pos : moves) {//열고정
			for(int j=1;j<=n;j++) {//행
				if(board[j][pos]!=0) {
					if(!stack.isEmpty() && board[j][pos]==stack.peek()) {
						answer+=2;
						stack.pop();
					}else {
					stack.push(board[j][pos]);//스택에 쌓고
					}
					board[j][pos]=0;// 0으로 만든다.
					break;//빠져나온다.
				}
			}
		}
		return answer;
	}
}
