package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 양치기 꿍
public class BOJ_3187_최규림 {
	static int R, C, wolfCnt, sheepCnt;
	static char[][] map;
	static boolean[][] visited;
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
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			char[] ch = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				map[r][c] = ch[c];
			}
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (!visited[r][c] && (map[r][c] == 'v' || map[r][c] == 'k')) {
					bfs(r, c);
				}
			}
		}

		System.out.println(sheepCnt + " " + wolfCnt);
	}

	static void bfs(int startR, int startC) {
		int wolf = 0;
		int sheep = 0;
		if (map[startR][startC] == 'v') {
			wolf++;
		} else {
			sheep++;
		}

		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(startR, startC));
		visited[startR][startC] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (checkRange(nr, nc) && !visited[nr][nc] && map[nr][nc] != '#') {
					q.add(new Point(nr, nc));
					visited[nr][nc] = true;
					if (map[nr][nc] == 'v') {
						wolf++;
					} else if (map[nr][nc] == 'k') {
						sheep++;
					}
				}
			}
		}

		if (sheep > wolf) {
			sheepCnt += sheep;
		} else {
			wolfCnt += wolf;
		}

	}

	static boolean checkRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}
