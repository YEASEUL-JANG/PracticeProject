package stact_Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class q41 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		q41 m = new q41();
		System.out.println(m.solution(n,k));
	}

	private int solution(int n, int k) {
		int answer=0;
		int cnt=0;
		int p=0;
		Queue<Integer> q = new LinkedList<>();
		for(int i=1;i<=n;i++) q.offer(i);//1부터 8까지 집어넣음
		while(q.size()>1) {
			cnt++;
			if(cnt==k) {
				q.poll();//빼내기만 함	
				cnt=0;
			}else {
			p=q.poll();//제일 앞에값을 빼내서
			q.offer(p);//제일 뒤에 집어넣음
			}
		}
		answer=q.poll();
		return answer;
	}
}
