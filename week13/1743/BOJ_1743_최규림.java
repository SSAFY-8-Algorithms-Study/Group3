package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 음식물 피하기
public class BOJ_1743_최규림 {

	static int R, C, K, ans;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] map, visited;

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new boolean[R + 1][C + 1];
		visited = new boolean[R + 1][C + 1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = true;
		}

		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (map[r][c] && !visited[r][c]) {
					int result = bfs(r, c);
					ans = Math.max(ans, result);
				}
			}
		}
		System.out.println(ans);
	}

	static int bfs(int r, int c) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(r, c));
		visited[r][c] = true;
		int result = 1;

		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (!checkRange(nr, nc))
					continue;

				if (!visited[nr][nc] && map[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new Point(nr, nc));
					result++;
				}
			}
		}
		return result;
	}

	static boolean checkRange(int r, int c) {
		return r >= 1 && r <= R && c >= 1 && c <= C;
	}
}
