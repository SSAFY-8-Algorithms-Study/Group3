package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5427_김아영 {

	static int N, M;
	static char map[][];
	static boolean fireVisit[][];
	static boolean visit[][];
	static int direct[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int startx, starty;
	static List<Point> list;

	static boolean check(int x, int y) {
		if (x == 0 || y == 0 || x == N - 1 || y == M - 1)
			return true;
		return false;
	}

	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(startx, starty));
		visit[startx][starty] = true;
		Queue<Point> fire = new LinkedList<>();
		for (int i = 0; i < list.size(); i++) {
			fire.add(new Point(list.get(i).x, list.get(i).y));
			fireVisit[list.get(i).x][list.get(i).y] = true;
		} // q 입력

		int cnt = 0;
		while (!q.isEmpty()) {
			cnt++;
			int fireSize = fire.size();
			for (int s = 0; s < fireSize; s++) {
				Point now = fire.poll();
				for (int i = 0; i < 4; i++) {
					int dx = now.x + direct[i][0];
					int dy = now.y + direct[i][1];
					if (dx < 0 || dy < 0 || dx >= N || dy >= M)
						continue;
					if (fireVisit[dx][dy] || map[dx][dy] == '#')
						continue;
					fireVisit[dx][dy] = true;
					fire.add(new Point(dx, dy));
				}
			}
			// 불 끝
			int qSize = q.size();
			for (int s = 0; s < qSize; s++) {
				Point now = q.poll();
				if (check(now.x, now.y)) {
					System.out.println(cnt);
					return;
				}
				for (int i = 0; i < 4; i++) {
					int dx = now.x + direct[i][0];
					int dy = now.y + direct[i][1];
					if (dx < 0 || dy < 0 || dx >= N || dy >= M)
						continue;
					if (fireVisit[dx][dy] || visit[dx][dy] || map[dx][dy] == '*' || map[dx][dy] == '#')
						continue;
					visit[dx][dy] = true;
					q.add(new Point(dx, dy));
				}
			}

		}

		System.out.println("IMPOSSIBLE");

	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visit = new boolean[N][M];
			fireVisit = new boolean[N][M];
			list = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == '@') {
						startx = i;
						starty = j;
					} else if (map[i][j] == '*') {
						list.add(new Point(i, j));
					}
				}
			} // input end
			bfs();

		}

	}

}
