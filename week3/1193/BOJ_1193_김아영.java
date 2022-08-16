package week3;

import java.util.Scanner;

public class BOJ_1193_김아영 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int cnt = 0;
		int up = 0;
		while (true) {
			up++;
			cnt = cnt + up;
			if (cnt >= N) break;
			
		}
		if (up % 2 == 0) {
			System.out.println((up-(cnt - N))+"/"+((cnt-N)+1));
		}
		else {
			System.out.println(((cnt-N)+1)+"/"+(up-(cnt - N)));
		}		
	}
}
