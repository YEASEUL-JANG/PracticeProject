package sorting_searching;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n= in.nextInt();//n개의 마구간
		int c=in.nextInt();//c마리
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=in.nextInt();//n개의 마구간의 좌표배열
		}
		Main main = new Main();
		System.out.println(main.solution(n,c,arr));
	}

	private int solution(int n, int c, int[] arr) {
		int answer=0;
		//가장 가까운 두 말의 거리를 lt와 rt로 두고 결정알고리즘 돌림
		Arrays.sort(arr);//arr을 오름차순으로 정렬
		int lt=1, rt=arr[n-1];// 최소거리를 1로두고 최대거리는 arr의 배열중 가장 큰 값으로 지정
		while(lt<=rt) {
			int mid=(lt+rt)/2;//가장 가까운 두 말의 거리
			if(count(arr,mid)>=c) {//넘어온 카운트값이 c마리보다 크거나 같으면
				answer=mid;//mid가 정답
				lt=mid+1;//더 나은 유효값을 찾기 위해 lt를 오른쪽으로 늘린다.
			}else {
				rt=mid-1;//rt를 mid 왼쪽으로 줄인다.
			}
		}
		return answer;
	}
	private int count(int[] arr, int mid) {
		int cnt=1;//배치한 말의 마리수
		int ep=arr[0];//배치한 좌표(제일 왼쪽자리에 한마리 미리 배치)
		for(int i=1;i<arr.length;i++) {
			if(arr[i]-ep>=mid) {//우리가 정한 두 말사이의 거리(mid)보다 크거나 같으면 
				cnt++;//말을 배치한다.
				ep=arr[i];//배치한곳부터 또 카운트시작
			}//mid값 보다 작으면 카운트하지 않는다.
		}
		return cnt;
	}
}
