package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파이프 옮기기1
public class BOJ_17070_최규림 {

	static int N, answer;
	static int[][] map;

	static class Point {
		int r1, c1, r2, c2, d;

		Point(int r1, int c1, int r2, int c2, int d) {
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 0: 가로, 1:세로, 2:대각선
		Point point = new Point(1, 1, 1, 2, 0);
		dfs(point);
		System.out.println(answer);
	}

	static void dfs(Point p) {
		if (isConnected(p.r1, p.c1) || isConnected(p.r2, p.c2)) {
			answer++;
			return;
		}

		// 가로
		if (p.d == 0) {
			// 가로
			if (checkRange(p.r2, p.c2 + 1) && map[p.r2][p.c2 + 1] == 0) {
				dfs(new Point(p.r2, p.c2, p.r2, p.c2 + 1, 0));
			}
			// 대각선
			if (checkRange(p.r2, p.c2 + 1) && checkRange(p.r2 + 1, p.c2)) {
				if (map[p.r2][p.c2 + 1] == 0 && map[p.r2 + 1][p.c2] == 0 && map[p.r2 + 1][p.c2 + 1] == 0) {
					dfs(new Point(p.r2, p.c2, p.r2 + 1, p.c2 + 1, 2));
				}
			}
		}
		// 세로
		else if (p.d == 1) {
			// 세로
			if (checkRange(p.r2 + 1, p.c2) && map[p.r2 + 1][p.c2] == 0) {
				dfs(new Point(p.r2, p.c2, p.r2 + 1, p.c2, 1));
			}
			// 대각선
			if (checkRange(p.r2, p.c2 + 1) && checkRange(p.r2 + 1, p.c2)) {
				if (map[p.r2][p.c2 + 1] == 0 && map[p.r2 + 1][p.c2] == 0 && map[p.r2 + 1][p.c2 + 1] == 0) {
					dfs(new Point(p.r2, p.c2, p.r2 + 1, p.c2 + 1, 2));
				}
			}
		}
		// 대각선
		else {
			// 가로
			if (checkRange(p.r2, p.c2 + 1) && map[p.r2][p.c2 + 1] == 0) {
				dfs(new Point(p.r2, p.c2, p.r2, p.c2 + 1, 0));
			}
			// 세로
			if (checkRange(p.r2 + 1, p.c2) && map[p.r2 + 1][p.c2] == 0) {
				dfs(new Point(p.r2, p.c2, p.r2 + 1, p.c2, 1));
			}
			// 대각선
			if (checkRange(p.r2, p.c2 + 1) && checkRange(p.r2 + 1, p.c2)) {
				if (map[p.r2][p.c2 + 1] == 0 && map[p.r2 + 1][p.c2] == 0 && map[p.r2 + 1][p.c2 + 1] == 0) {
					dfs(new Point(p.r2, p.c2, p.r2 + 1, p.c2 + 1, 2));
				}
			}
		}
	}

	static boolean checkRange(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= N;
	}

	static boolean isConnected(int r, int c) {
		return r == N && c == N;
	}
}
