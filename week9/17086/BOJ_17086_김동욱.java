package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17086_kimdongwook {
	static int N;
	static int M;
	static int[][] board;
	static int[][] tempBoard;
	static Queue<Point> queue;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int answer;

	public static class Point {
		int x;
		int y;

		public Point() {
		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

	}

	public static boolean isValid(int x, int y, boolean visited[][]) {
		if (x < 0 || x >= N || y < 0 || y >= M)
			return false;
		if (visited[x][y])
			return false;
		return true;
	}

	

	public static int bfs(Point p) {
		
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		int dist=1;
		queue.add(p);
		
		visited[p.x][p.y] = true;
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			for (int s = 0; s < size; s++) {
				Point tempPoint = queue.poll();
				for (int d = 0; d < 8; d++) {
					int nx = tempPoint.x + dx[d];
					int ny = tempPoint.y + dy[d];
					if (isValid(nx, ny, visited)) {
						if (board[nx][ny] == 1) {
							
							return dist;
						} else {
							queue.add(new Point(nx, ny));
							visited[nx][ny] = true;
						}
					}
				}
			}
			dist++;
		}
		return 0;
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		board = new int[N][M];
		tempBoard = new int[N][M];
		answer = 0;
		queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = sc.nextInt();
			}
		} // end input
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j]==1)continue;
				answer=Math.max(bfs(new Point(i, j)), answer);
			}
		}

		System.out.println(answer);
	}

}
