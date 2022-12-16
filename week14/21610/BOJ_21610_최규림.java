package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 마법사 상어와 비바라기
public class BOJ_21610_최규림 {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	static int[] dr = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dc = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	static int[] ddr = { -1, -1, 1, 1 };
	static int[] ddc = { -1, 1, -1, 1 };

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static Queue<Point> cQ = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		init();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			visited = new boolean[N + 1][N + 1];
			rain(d, s); // 1,2,3,4
			makeCloud(); // 5
		}

		System.out.println(calc());
	}

	static void rain(int d, int s) {
		Queue<Point> tempQ = new ArrayDeque<>();

		// 3.
		while (!cQ.isEmpty()) {
			Point now = cQ.poll();
			Point nxt = move(now, d, s); // 1.
			map[nxt.r][nxt.c]++; // 2.
			visited[nxt.r][nxt.c] = true;
			tempQ.add(nxt);
		}

		// 4.
		while (!tempQ.isEmpty()) {
			Point p = tempQ.poll();
			int cnt = 0;

			for (int i = 0; i < 4; i++) {
				int nr = p.r + ddr[i];
				int nc = p.c + ddc[i];

				if (checkRange(nr, nc) && map[nr][nc] > 0) {
					cnt++;
				}
			}

			map[p.r][p.c] += cnt;
		}

	}

	static void makeCloud() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (map[r][c] >= 2 && !visited[r][c]) {
					cQ.add(new Point(r, c));
					map[r][c] -= 2;
				}
			}
		}
	}

	static Point move(Point now, int d, int s) {
		int nr = now.r + dr[d] * s;
		int nc = now.c + dc[d] * s;

		if (checkRange(nr, nc)) {
			return new Point(nr, nc);
		}

		if (nr < 1) {
			while (!checkRange(nr)) {
				nr += N;
			}
		} else if (nr > N) {
			while (!checkRange(nr)) {
				nr -= N;
			}
		}

		if (nc < 1) {
			while (!checkRange(nc)) {
				nc += N;
			}
		} else if (nc > N) {
			while (!checkRange(nc)) {
				nc -= N;
			}
		}
		return new Point(nr, nc);
	}

	static int calc() {
		int result = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				result += map[r][c];
			}
		}
		return result;
	}

	static void print() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	static boolean checkRange(int i) {
		return i >= 1 && i <= N;
	}

	static boolean checkRange(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= N;
	}

	static void init() {
		cQ.add(new Point(N, 1));
		cQ.add(new Point(N, 2));
		cQ.add(new Point(N - 1, 1));
		cQ.add(new Point(N - 1, 2));
	}

}
