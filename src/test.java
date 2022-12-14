public class test {
	public static void main(String[] args) {
		String[] bishops={"C6"};
		test t = new test(); 
		System.out.println(t.solution(bishops));
	}
	public int solution(String[] bishops) {
		int answer = 0;
		int[][] arr = new int[8][8];
		
		int i = 0;
		while(i < bishops.length) {
			String tmp = bishops[i];
			String tmp2[] = tmp.split("");
			int x = 0;
			switch(tmp.charAt(0)) {
				case 'A' : x = 0; break;
				case 'B' : x = 1; break;
				case 'C' : x = 2; break;
				case 'D' : x = 3; break;
				case 'E' : x = 4; break;
				case 'F' : x = 5; break;
				case 'G' : x = 6; break;
				case 'H' : x = 7; break;
			}
			int y = Integer.parseInt(tmp2[1]) - 1;
			arr[y][x] = 1;
			int x1 = x, y1 = y;
			int used = 0;
			while(used < 4) {
				x = x1; y = y1;
				while(used == 0 && x > 0 && y > 0) arr[--y][--x] = 1;
				while(used == 1 && x > 0 && y < 7) arr[++y][--x] = 1;
				while(used == 2 && x < 7 && y > 0) arr[--y][++x] = 1;
				while(used == 3 && x < 7 && y < 7) arr[++y][++x] = 1;
				used++;
			}
			i++;
		}
		
		answer = 64;
		for (int j = 0; j < 8; j++) {
			for (int k = 0; k < 8; k++) {
				if(arr[j][k] == 1) answer--;
			}
		}
		return answer;
	 }
    }

