package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2447_김동욱 {
	static int N;
	static char board[][];
	
	public static void star(int x, int y, int size) {
		if (size ==1) {
			board[x][y]= '*';
			return;
		}
		int tr= size/3;
		int six= 2*tr;
		star(x,y,tr);
		star(x,y+tr,tr);
		star(x,y+six,tr);
		
		star(x+tr,y,tr);
		
		star(x+tr,y+six,tr);
		
		star(x+six,y,tr);
		star(x+six,y+tr,tr);
		star(x+six,y+six,tr);
		return;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		board = new char[N][N];
		for(int i=0;i<N;i++) {
			Arrays.fill(board[i],' ');
		}
		star(0,0,N);
		StringBuilder sb =new StringBuilder();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				//System.out.printf("%c",board[i][j]);
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}