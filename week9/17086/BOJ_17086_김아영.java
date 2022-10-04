package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17086_김아영 {

	static int N, M;
	static int map[][];
	static boolean visit[][];
	static int direct[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };
	static int result;
	static List<Point> arr;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		int cnt = 0;
		for (int i = 0; i < arr.size(); i++) {
			q.add(new Point(arr.get(i).x, arr.get(i).y));
		}

		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Point now = q.poll();
				for (int d = 0; d < 8; d++) {
					int dx = now.x + direct[d][0];
					int dy = now.y + direct[d][1];
					if (dx < 0 || dy < 0 || dx >= N || dy >= M)
						continue;
					if (visit[dx][dy])
						continue;
					visit[dx][dy] = true;
					q.add(new Point(dx, dy));
				}
			}
			cnt++;
		}
		System.out.println(cnt - 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		arr = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					visit[i][j] = true;
					arr.add(new Point(i, j));
				}
			}
		} // input end

		
		bfs();
	}
}
