package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_김동욱 {
	
	static int N;
	static int M;
	static int[][]board;
	static boolean[][] visited;
	static Queue<Point> queue;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int min = Integer.MAX_VALUE;
	public static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static boolean isValid(int x,int y) {
		if(x>=N|| x <0 || y>=M || y<0 )
			return false;
		if(visited[x][y])
			return false;
		return true;
	}
	public static void bfs(Point p) {
		visited[p.x][p.y] =true;
		queue.add(p);
		
		while(!queue.isEmpty()) {
			Point tempPoint = queue.poll(); // Queue 에서 꺼냄
			int x = tempPoint.x;
			int y = tempPoint.y;
			for(int d=0;d<dx.length;d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(isValid(nx,ny)&& board[nx][ny]==1) {
					
					visited[nx][ny]=true;
					board[nx][ny] = board[x][y]+1;
					queue.add(new Point(nx,ny));
				}
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String temp="";
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board= new int[N][M];
		visited= new boolean[N][M];
		queue = new LinkedList<Point>();
		for(int i=0;i<N;i++) {
			temp = br.readLine();
			for(int j=0;j<M;j++) {
				board[i][j]= temp.charAt(j)-'0';
			}
		} // end input
		 // searchPath(0,0,1);
		bfs(new Point(0,0));
		System.out.println(board[N-1][M-1]); // 도착점 출력
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.printf("%d ",board[i][j]);
//			}
//			System.out.println();
//		}
	}
 
	/*
	public static void searchPath(int x , int y,int count) { // DFS 로 짰더니 시간초과 
		if(x==N-1 && y == M-1) { // 탐색이 다끝나면
			if(min>count)
				min=count;
			return;
		}
		if(!isValid(x,y)) return;
		
		if(isValid(x,y)) {
			for(int d=0;d<dx.length;d++) {
				int nx = x+dx[d];
				int ny= y+dy[d];
				if(!isValid(nx,ny) || board[nx][ny]==0) continue;
				
				visited[x][y]=true;
				searchPath(nx,ny,count+1);
				visited[x][y]=false;
				//System.out.println(nx+" "+ny+" "+count);
				
			}
			return;
		}

	}*/
}
