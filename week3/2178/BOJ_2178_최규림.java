package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 미로 탐색
public class BOJ_2178_최규림 {

	static int n, m;
	static int[][] map;

	static int wall = 0;
	static int road = 1;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

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
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n + 1][m + 1];
		for (int r = 1; r <= n; r++) {
			char[] charArr = br.readLine().toCharArray();
			for (int c = 0; c < m; c++) {
				map[r][c + 1] = charArr[c] - '0';
			}
		}

		bfs(1, 1);
		System.out.println(map[n][m]);
	}

	static boolean checkRange(int r, int c) {
		return (r >= 1) && (r <= n) && (c >= 1) && (c <= m);
	}

	static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c));
		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (checkRange(nr, nc) && map[nr][nc] == road) {
					map[nr][nc] = map[now.r][now.c] + 1;
					q.add(new Point(nr, nc));
				}
			}
		}
	}
}
