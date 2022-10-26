package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17244_김아영 {

	static int N, M, startx, starty, endx, endy;
	static char map[][];
	static boolean visit[][];
	static int path[];
	static List<Point> list;
	static boolean pathVisit[];
	static int direct[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int result = Integer.MAX_VALUE;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int distance(int sx, int sy, int ex, int ey) {
		visit = new boolean[N + 1][M + 1];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(sx, sy));
		visit[sx][sy] = true;
		int cnt = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Point now = q.poll();
				if (now.x == ex && now.y == ey) {
					return cnt;
				}
				for (int d = 0; d < 4; d++) {
					int dx = now.x + direct[d][0];
					int dy = now.y + direct[d][1];
					if (dx < 0 || dy < 0 || dx >= N || dy >= M || visit[dx][dy] || map[dx][dy] == '#')
						continue;
					visit[dx][dy] = true;
					q.add(new Point(dx, dy));
				}
			}
			cnt++;
		}
		return cnt;
	}

	static void dfs(int level, int size) {

		if (level == size) {
			int sum = 0;
			sum = sum + distance(startx, starty, list.get(path[0]).x, list.get(path[0]).y);
			for (int i = 1; i < size; i++) {
				sum = sum + distance(list.get(path[i - 1]).x, list.get(path[i - 1]).y, list.get(path[i]).x,
						list.get(path[i]).y);
			}
			sum = sum + distance(list.get(path[size - 1]).x, list.get(path[size - 1]).y, endx, endy);
			result = Math.min(sum, result);
			return;
		}

		for (int i = 0; i < size; i++) {
			if (!pathVisit[i]) {
				pathVisit[i] = true;
				path[level] = i;
				dfs(level + 1, size);
				pathVisit[i] = false;
				path[level] = 0;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N + 1][M + 1];
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'S') {
					startx = i;
					starty = j;
				} else if (map[i][j] == 'E') {
					endx = i;
					endy = j;
				} else if (map[i][j] == 'X') {
					list.add(new Point(i, j));
				}
			}
		}
		path = new int[list.size() + 1];
		pathVisit = new boolean[list.size() + 1];
		// input

//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).x + " " + list.get(i).y);
//		}
		if (list.size() == 0) {
			System.out.println(distance(startx, starty, endx, endy));
			return;
		}
		dfs(0, list.size());
		System.out.println(result);
	}

}
