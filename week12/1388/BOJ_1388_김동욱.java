package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1388_김동욱 {
	static char[][] board;
	static boolean[][] visited;
	static int answer;
	static int N;
	static int M;
	static int[] dx1 = { 0, 0 };
	static int[] dy1 = { -1, 1 };
	static int[] dx2 = { -1, 1 };
	static int[] dy2 = { 0, 0 };

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

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = 0;
		board = new char[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = temp.charAt(j);
			}
		} // end input
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j])
					continue;

				
				bfs(i, j);

			}
		}
		System.out.println(answer);
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M)
			return false;
		if (visited[x][y]) {
			return false;
		}
		return true;
	}

	public static void bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(i, j));
		visited[i][j] = true;
		while (!queue.isEmpty()) {
			int size = queue.size();
			// System.out.println(queue);
			for (int s = 0; s < size; s++) {
				Point tempPoint = queue.poll();
				for (int d = 0; d < 2; d++) {
					int nx = 0;
					int ny = 0;
					if (board[tempPoint.x][tempPoint.y] == '-') {
						nx = tempPoint.x + dx1[d];
						ny = tempPoint.y + dy1[d];
					} else {
						nx = tempPoint.x + dx2[d];
						ny = tempPoint.y + dy2[d];
					}

					if (isValid(nx, ny)) {
						if ((board[nx][ny] == board[i][j])) {
							queue.add(new Point(nx, ny));
							visited[nx][ny] = true;
						}
					}
				}
			}
		}

		answer++;
	}

}
