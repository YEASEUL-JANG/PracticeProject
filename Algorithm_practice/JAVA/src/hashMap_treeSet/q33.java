package hashMap_treeSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class q33 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		q33 m = new q33();
		int n = in.nextInt();
		int k=in.nextInt();
		int[] arr = new int[n];
		for(int a=0;a<n;a++) {
			arr[a]=in.nextInt();
		}
		for(Integer i:m.solution(n,k,arr)) {
			System.out.print(i+" ");
		}
	}

	private List<Integer> solution(int n, int k, int[] arr) {//n=7,k=4
		List<Integer> list=new ArrayList<>();
		Map<Integer,Integer> map = new HashMap<>();
		//map에 맨 앞 20,12,20,10 을 먼저 집어넣음
		for(int i=0;i<k;i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
		}
		list.add(map.size());
		for(int p=k;p<n;p++) {//p=4,5,6
			map.put(arr[p-k],map.get(arr[p-k])-1);//제일 앞에것을 -1
			if(map.get(arr[p-k])==0) map.remove(arr[p-k]);
			map.put(arr[p], map.getOrDefault(arr[p], 0)+1);
			list.add(map.size());
		}
		return list;
	}
}
