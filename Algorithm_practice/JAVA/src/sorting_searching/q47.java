package sorting_searching;

import java.util.*;

public class q47 {
	 public static void main(String[] args){
     Scanner in=new Scanner(System.in);
	 int size = in.nextInt();//캐시의 크기(칸수)
	 int n = in.nextInt();//작업의 개수
	 int[] list = new int[n];
	 for(int i=0;i<n;i++) {//작업순서대로 배열에 담음
		 list[i]=in.nextInt();
	 }
	 q47 m = new q47();
	 for(int i: m.solution(size,n,list)) {
		 System.out.print(i+" ");
	 	}
	 }

	public int[] solution(int size, int n, int[] arr) {
		int[] cache= new int[size];
		for(int p:arr) {
			int idx=-1;
			for(int i=0;i<size;i++) if(cache[i]==p) idx=i;//answer에 중복값이 있으면 idx에 해당 위치값저장
			if(idx==-1) {//answer에 중복값이 없으면
				for(int i=size-1;i>=1;i--) {//5번이면 4번부터 1번까지만
					cache[i]=cache[i-1];//하나씩 뒤로 밀림
				}
			}else {//중복값이 있으면 중복지점부터 시작한다.
				for(int j=idx;j>=1;j--) {
					cache[j]=cache[j-1];//하나씩 뒤로 밀림
				}
			}
			cache[0]=p;//제일앞에 집어넣음.
		}
		return cache;
	}
}
