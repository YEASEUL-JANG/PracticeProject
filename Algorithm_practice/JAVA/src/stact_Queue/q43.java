package stact_Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class q43 {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[] list = new int[n];
		for(int i=0;i<n;i++) {
			list[i]=in.nextInt();
		}
		q43 main = new q43();
		System.out.println(main.solution(n,m,list));
	}
	class Person{//큐에 인덱스 처리를 위한 Person 객체생성
		int id;
		int priority;
		public Person(int id, int priority) {
			this.id = id;
			this.priority = priority;
		}
	}
	private int solution(int n, int m, int[] list) {
		int answer=0;
		int mm = list[m];//m번째 환자의 위험도
		Queue<Person> q = new LinkedList<>();
		for(int i=0;i<n;i++) {
			q.offer(new Person(i, list[i]));//큐에 (순서,중요도) 객체를 차례로 넣음
		}
		while(!q.isEmpty()) {//큐안에 사람이 남아있는 한
			Person tmp = q.poll();//제일 앞의 요소를 꺼내서 Person 객체에 저장
			for(Person x:q) {
				if(x.priority>tmp.priority){//그 다음요소가 꺼냈던 제일 앞의 요소보다 크면
					q.offer(tmp);//맨뒤로 집어넣음
					tmp=null;
					break;//나가서 그 다음 첫번째 사람 확인
				}//크지 않으면 그냥 지나감
			}//inner for/한바퀴 돌고 여기까지 오면 제일앞이 중요도가 제일 크단 얘기
			if(tmp!=null) {
			answer++;//진료받음(성공)
			if(tmp.id==m) return answer;
			}
		}//while
		return answer;
	}
}
