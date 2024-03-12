package practice;

public class Euler2 {
public static void main(String[] args) {
	int a=1,b=1,c=1;
	int answer=0;
	while(a<1000) {
		for(b=a+1;b<1000;b++) {
			for(c=b+1;c<1000;c++) {
				
				if(a+b+c==1000 && (a*a)+(b*b)==c*c ) {
					answer = a*b*c;
					break;
				}
				
			}
		}
	a++;
	}
	System.out.println(answer);
}
}
