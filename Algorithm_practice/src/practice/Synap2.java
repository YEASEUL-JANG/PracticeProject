package practice;
import java.math.BigInteger;

public class Synap2 {
public static void main(String[] args) {
		BigInteger prenum = new BigInteger("1");
		BigInteger num = new BigInteger("1");
		BigInteger bignum = new BigInteger(String.valueOf(Math.pow(10,999)));
		int answer=2;
		while(true) {
			BigInteger curnum = prenum.add(num);
			answer++;
			if(bignum.compareTo(curnum)==1) {
				break;
			}
			prenum = num;
			num= curnum;
		}
		System.out.println(answer);
	}
}
