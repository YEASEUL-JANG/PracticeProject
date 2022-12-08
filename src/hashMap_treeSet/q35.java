package hashMap_treeSet;

import java.util.*;

public class q35 {
	public static void main(String[] args){
	    Scanner in=new Scanner(System.in);
	    int n = in.nextInt();//n장
	    int k = in.nextInt();//k번째로 큰 수
	    q35 m = new q35();
	    int[] arr = new int[n];
	    for(int i=0;i<n;i++) {
	    	arr[i]=in.nextInt();
	    }
	    System.out.println(m.solution(n,k,arr));
}

	private int solution(int n, int k, int[] arr) {
		int answer=-1;
		TreeSet<Integer> Tset = new TreeSet<>(Collections.reverseOrder());
		//3개의 수를 뽑는 방법 : 3중 for문
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {//중복값을 허용안하므로 i+1부터 시작한다.
				for(int h=j+1;h<n;h++) {
					Tset.add(arr[i]+arr[j]+arr[h]);//값추가
				}
			}
		}
		int cnt=0;
		for(int x: Tset) {
			cnt++;
			if(cnt==k) return x;
		}
		return answer;
	}
}