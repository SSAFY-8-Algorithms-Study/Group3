package boj;

import java.util.Scanner;

public class BOJ_10162_김동욱 {
	static int T;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		int A=0;
		int B=0;
		int C=0;
		A  = T/300;
		T%=300;
		B = T/60;
		T%=60;
		C = T/10;
		T%=10;
		
		if(T!=0) {
			System.out.println(-1); return;
		}
		System.out.printf("%d %d %d",A,B,C);
	}

}
