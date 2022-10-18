package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


// 새로운 게임 2
public class BOJ_17837_최규림 {
	static int N, K, ans;
	static int[] dr = { 0, 0, 0, -1, 1 };
	static int[] dc = { 0, 1, -1, 0, 0 };

	static int[][] map;
	static Deque<Integer>[][] qMap;
	static Deque<Integer> q, remainQ, moveQ;

	static class Point {
		int r, c, d;

		Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static Point[] horses;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		horses = new Point[K];
		qMap = new ArrayDeque[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				qMap[r][c] = new ArrayDeque<>();
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			horses[i] = new Point(r, c, d);
			qMap[r][c].add(i);
		}

		for (int x = 0; x < 1001; x++) {
			ans++;
			for (int i = 0; i < K; i++) {
				move(i);

				if (isSuccess(horses[i].r, horses[i].c)) {
					System.out.println(ans);
					return;
				}
			}
		}
		System.out.println(-1);
	}

	static void move(int idx) {
		int r = horses[idx].r;
		int c = horses[idx].c;
		q = qMap[r][c];
		remainQ = new ArrayDeque<>();
		moveQ = new ArrayDeque<>();

		boolean flag = false;
		while (!q.isEmpty()) {
			int target = q.pollFirst();
			if (target == idx) {
				flag = true;
			}

			if (!flag) {
				remainQ.add(target);
			} else {
				moveQ.add(target);
			}
		}
		qMap[r][c] = remainQ;

		int nr = r + dr[horses[idx].d];
		int nc = c + dc[horses[idx].d];

		// blue
		if (!checkRange(nr, nc) || map[nr][nc] == 2) {
			horses[idx].d = changeDir(horses[idx].d);
			nr = r + dr[horses[idx].d];
			nc = c + dc[horses[idx].d];

			BLUE(r, c, nr, nc);

		}
		// red
		else if (map[nr][nc] == 1) {
			RED(nr, nc);
		}
		// white
		else {
			WHITE(nr, nc);
		}

	}

	static void WHITE(int nr, int nc) {
		while (!moveQ.isEmpty()) {
			int target = moveQ.pollFirst();
			qMap[nr][nc].add(target);
			horses[target].r = nr;
			horses[target].c = nc;
		}
	}

	static void RED(int nr, int nc) {
		while (!moveQ.isEmpty()) {
			int target = moveQ.pollLast();
			qMap[nr][nc].add(target);
			horses[target].r = nr;
			horses[target].c = nc;
		}
	}

	static void BLUE(int r, int c, int nr, int nc) {
		// blue
		if (!checkRange(nr, nc) || map[nr][nc] == 2) {
			while (!moveQ.isEmpty()) {
				int target = moveQ.pollFirst();
				qMap[r][c].add(target);
			}
		} else {
			// red
			if (map[nr][nc] == 1) {
				RED(nr, nc);

			}
			// white
			else {
				WHITE(nr, nc);
			}

		}
	}

	static boolean isSuccess(int r, int c) {
		if (qMap[r][c].size() >= 4) {
			return true;
		}
		return false;
	}

	static boolean checkRange(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= N;
	}

	static int changeDir(int d) {
		switch (d) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		}
		return -1;
	}
}
