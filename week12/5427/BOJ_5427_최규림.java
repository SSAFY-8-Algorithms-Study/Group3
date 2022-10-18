package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 불
public class BOJ_5427_최규림 {
	static int TC, C, R, time;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static boolean[][] visited;

	static Queue<Point> q;
	static Queue<Point> fireQ;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			map = new char[R][];
			visited = new boolean[R][C];

			q = new ArrayDeque<>();
			fireQ = new ArrayDeque<>();
			time = 0;

			for (int r = 0; r < R; r++) {
				map[r] = br.readLine().toCharArray();
				for (int c = 0; c < C; c++) {
					// 상근이
					if (map[r][c] == '@') {
						visited[r][c] = true;
						q.add(new Point(r, c));
					}
					// 불
					if (map[r][c] == '*') {
						fireQ.add(new Point(r, c));
					}
				}
			}

			boolean flag = false;
			while (!q.isEmpty()) {
				fireBFS();
				time++;
				if (sangGeunBFS()) {
					flag = true;
					break;
				}
//				print();				
			}
			if (flag) {
				System.out.println(time);
			} else {
				System.out.println("IMPOSSIBLE");
			}

		}

	}

	static void fireBFS() {
		int size = fireQ.size();
		for (int x = 0; x < size; x++) {
			Point fire = fireQ.poll();
			for (int i = 0; i < 4; i++) {
				int nr = fire.r + dr[i];
				int nc = fire.c + dc[i];

				// 범위 이탈
				if (!checkRange(nr, nc))
					continue;
				// 벽 or 이미 불
				if (map[nr][nc] == '#' || map[nr][nc] == '*')
					continue;

				map[nr][nc] = '*';
				fireQ.add(new Point(nr, nc));
			}
		}
	}

	static boolean sangGeunBFS() {
		int size = q.size();
		for (int x = 0; x < size; x++) {
			Point now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				// 범위 이탈
				if (!checkRange(nr, nc)) {
					return true;
				}

				// 이동 가능 범위
				if (map[nr][nc] == '.' && !visited[nr][nc]) {
					q.add(new Point(nr, nc));
					map[nr][nc] = '@';
					visited[nr][nc] = true;
				}

			}
		}
		return false;
	}

	static boolean checkRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	static void print() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
