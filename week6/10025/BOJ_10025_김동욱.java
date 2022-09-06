package boj;

import java.util.Scanner;

public class BOJ_10025_김동욱 {
	static int N;
	static int K;
	static int[] board;
	static int answer = -1;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		 N= sc.nextInt();
		 K= sc.nextInt();
		 board = new int[1000001];
		 for(int i=0;i<N;i++) {
			 int a = sc.nextInt();
			 int b = sc.nextInt();
			 board[b]= a;
		 }
		 
		 int temp=0;
		 int window = K*2+1;
		 for(int i=0;i<board.length;i++) {
			 if(i>=window) {
				 temp-=board[i-window];
			 }
			 temp+=board[i];
			answer=  Math.max(temp, answer);
		 }
		 System.out.println(answer);
	}

}
