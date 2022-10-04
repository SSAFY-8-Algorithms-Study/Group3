package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//아기 상어2
public class BOJ_17086_최규림 {

	static int N, M;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };
	static int answer;
	static boolean[][] visited;

	static class Point {
		int r, c, d;

		Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0) {
					bfs(r, c);
				}
			}
		}

		System.out.println(answer);
	}

	static void bfs(int r, int c) {
		visited = new boolean[N][M];
		visited[r][c] = true;
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(r, c, 0));

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int i = 0; i < 8; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (!checkRange(nr, nc) || visited[nr][nc])
					continue;

				if (map[nr][nc] == 1) {
					answer = Math.max(answer, now.d + 1);
					return;
				}

				visited[nr][nc] = true;
				q.add(new Point(nr, nc, now.d + 1));
			}
		}
	}

	static boolean checkRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
