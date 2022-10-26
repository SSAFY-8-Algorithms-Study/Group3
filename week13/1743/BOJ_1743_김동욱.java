package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1743_김동욱 {
	static int N;
	static int M;
	static int K;
	static int[][] board;
	static boolean[][] visited;
	static int answer;
	static Point[] pointArray;
	
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	
	public static class Point{
		int x,y;
		public Point() {}
		public Point(int x, int y) {
			this.x=x;
			this.y =y;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		
	}
	public static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("____________");
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		visited = new boolean[N][M];
		board = new int[N][M];
		pointArray = new Point[K];
		answer=0;
		for(int i=0;i<K;i++) {
			int tempX = sc.nextInt()-1;
			int tempY = sc.nextInt()-1;
			board[tempX][tempY]= 1;
			pointArray[i] = new Point(tempX,tempY);
		}
		//print();
		for(Point p : pointArray) {
			if(visited[p.x][p.y]) continue;
			bfs(p);
			//System.out.println(p+" "+ answer);
		}
		System.out.println(answer);
	}
	public static void bfs(Point p) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(p);
		visited[p.x][p.y]=true;
		int count=1;
		while(!queue.isEmpty()) {
			Point tempP = queue.poll();
			for(int d=0;d<4;d++) {
				int nx = tempP.x+dx[d];
				int ny = tempP.y+dy[d];
				if(isValid(nx, ny) && board[nx][ny]==1) {
					queue.add(new Point(nx,ny));
					visited[nx][ny]=true;
					count++;
				}
			}
		}
		answer= Math.max(answer, count);
		
	}
	public static boolean isValid(int x, int y) {
		if(x<0 ||  x>=N || y<0 || y>=M)
			return false;
		if(visited[x][y])
			return false;
		return true;
	}
	public static void dfs(Point p,int count) {
		visited[p.x][p.y]=true;
		
		for(int d=0;d<4;d++) {
			int nx = p.x+dx[d];
			int ny = p.y+dy[d];
			if(isValid(nx, ny) && board[nx][ny]==1) {
				dfs(new Point(nx,ny),count+1);
			}
		}
		answer= Math.max(answer, count);
		return;
	}
}
