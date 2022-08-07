package boj;

import java.util.Scanner;

public class BOJ_2468_김동욱 {
	static int boardSize;
	static int count;
	static boolean[][] visited;
	static int[][] board;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static boolean checkVisited(int x,int y) {
		
		if(x>=boardSize || x <0 || y >=boardSize || y<0) {
			return false;
		}
		if(visited[x][y])
			return false;
		return true;
	}
	public static int safeArea(int x, int y, int depth) {
		visited[x][y]= true;
		for(int i=0;i<dx.length;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
		if(nx<0|| nx >=boardSize || ny < 0 || ny>=boardSize) {
			continue;
		}
		if(visited[nx][ny]) {
			continue;
		}
		if(board[nx][ny] >depth) {
			safeArea(nx,ny,depth);
		}
		}
		return 1;
	}
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		boardSize = sc.nextInt();
		board = new int[boardSize][boardSize];
		//boolean noSafeArea = true;
		
		int max =0;
		int maxHeight =0; // board 안 에서 가장 수위가 높은 지역
		
		
		for(int i=0;i<boardSize;i++)
		{
			for(int j=0;j<boardSize;j++) {
				board[i][j]= Integer.parseInt(sc.next());
				maxHeight = Math.max(maxHeight, board[i][j]);
			}
		}
		for(int i=0;i<maxHeight;i++) {
			visited = new boolean[boardSize][boardSize];
			count = 0;
			for(int j=0;j<boardSize;j++) {
				for(int k=0;k<boardSize;k++) {
					if(checkVisited(j,k)) {
						if(board[j][k] > i) {
							count += safeArea(j,k,i);
						}
					}
				}
			}
			
				max = Math.max(max, count);
		}
		System.out.println(max);
		
	}

}
