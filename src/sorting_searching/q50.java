package sorting_searching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Point implements Comparable<Point>{
	public int x,y;
	Point(int x, int y){
		this.x=x;
		this.y=y;
	}
	@Override
	public int compareTo(Point o) {//정렬기준을 잡아줌
		if(this.x==o.x) return this.y-o.y;//오름차순으로 정렬(this.y<o.y)
		else return this.x-o.x;
	}
}


public class q50 {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<Point> arr = new ArrayList<>();
		q50 m = new q50();
		for(int i=0;i<n;i++) {
			int x=in.nextInt();
			int y=in.nextInt();
			arr.add(new Point(x,y));
		}
		Collections.sort(arr);//compareTo의 정렬기준에 의해 정렬된다.(x값이 같으면 y기준으로 오름차순, 아니면 x기준 오름차순)
		for(Point o:arr) System.out.println(o.x+" "+o.y);
		}
	}

