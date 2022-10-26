package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1743_김아영 {

	static int N, M, K;
	static boolean visit[][];
	static int map[][];
	static int result;
	static List<Point> list;
	static int direct[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visit[x][y] = true;
		int cnt = 1;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Point now = q.poll();
				for (int i = 0; i < 4; i++) {
					int dx = now.x + direct[i][0];
					int dy = now.y + direct[i][1];
					if (dx < 0 || dy < 0 || dx >= N || dy >= M)
						continue;
					if (visit[dx][dy])
						continue;
					if (map[dx][dy] == 0)
						continue;
					visit[dx][dy] = true;
					cnt++;
					q.add(new Point(dx, dy));
				}
			}

		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visit = new boolean[N][M];
		map = new int[N][M];
		list = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.add(new Point(a - 1, b - 1));
			map[a - 1][b - 1] = 1;
		}

		for (int i = 0; i < list.size(); i++) {
			if (visit[list.get(i).x][list.get(i).y])
				continue;
			result = Math.max(result, bfs(list.get(i).x, list.get(i).y));
		}
		System.out.println(result);

	}
}
