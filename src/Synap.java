import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Synap {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long a = in.nextLong();
		long b = in.nextLong();
		Synap m = new Synap();
		System.out.println(m.solution(a,b));
	}

	private long solution(long a, long b) {
		int j=0;
		List<Long> list = new ArrayList<>();
		List<Long> list2 = new ArrayList<>();
		list.add(1L);
		list.add(2L);
		for(int i =2;i<100;i++) {
			long p=list.get(i-2)+list.get(i-1);
			list.add(p);
			if(p>=a&&p<=b) {
				list2.add(p);
				System.out.println(p);
			}
		}
		j=list2.size();
		long answer=0;
		for(int i=0;i<j;i++) {
			answer += list2.get(i);
		}
		return answer;
	}
}
