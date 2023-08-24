package practice;
import java.util.Arrays;

public class test2 {
	public static void main(String[] args) {
		int n=5;
		int[][] quests= {{1,3},{1,4},{3,5},{5,4}};
		test2 t = new test2(); 
		System.out.println(t.solution(n,quests));
	}

	public String solution(int n, int[][] quests) {
		int[] answer = new int[n];
		String arr = "";
		int a = quests.length;//4
		for(int i=0;i<n;i++) {
			answer[i]=i+1;
			arr+=i;
		}
		for(int i=0;i<a;i++) {//4행반복
			int tmp = 0;
				if(arr.indexOf(quests[i][0])>arr.indexOf(quests[i][1])) {
					//자리를 바꿈
					tmp=answer[arr.indexOf(quests[i][0])];
					answer[arr.indexOf(quests[i][0])]=answer[arr.indexOf(quests[i][1])];
					answer[arr.indexOf(quests[i][1])]=tmp;
			}
		}
		return Arrays.toString(answer);
	}
	
}
