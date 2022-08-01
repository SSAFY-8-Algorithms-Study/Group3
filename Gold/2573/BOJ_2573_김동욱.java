package boj;

import java.util.Scanner;

public class BOJ_2573_김동욱 {
	static int N;
	static int M;
	static int[][] board;
	static boolean[][] visited;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static boolean checkVisited(int x, int y) {
		if(x<0||x>=M || y<0|| y>=N) {
			return false;
		}
		if(visited[x][y])
			return false;
		return true;
	}
	public static int iceberg(int x, int y) {
		visited[x][y]= true;
		if(board[x][y]==0) {
			return 0;
		}
		for(int i=0;i<dx.length;i++) {
			if(board[x][y]<=0) {
				return 1;
			}
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0|| nx >=M || ny < 0 || ny>=N) {
				continue;
			}
			if(visited[nx][ny]) {
				continue;
			}
			if(board[nx][ny]==0) {
				board[x][y]--;
			}
		}
		return 1;
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		board = new int[N][M];
		//int iceberg[][] = new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				board[i][j] = Integer.parseInt(sc.next());
			}
		}
	sc.close();	
	}

}
