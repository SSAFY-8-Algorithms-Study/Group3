package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17142 {

	static int N, M;
	static boolean visit[][];
	static int map[][];
	static int path[];
	static List<Point> arr;
	static int MIN = Integer.MAX_VALUE;
	static int result = -1;
	static int direct[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int empty;
	
	
	static class Point {
		int x, y, cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

	}

	static int[][] deepcopy(int origin[][]) {
		int copy[][] = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = map[i][j];
			}
		}

		return copy;

	}

	static void bfs() {
		int copy[][] = new int[N][N];
		copy = deepcopy(map);
		Queue<Point> q = new ArrayDeque<>();
		visit = new boolean[N][N];
		
		for (int i = 0; i < M; i++) {
			q.add(new Point(arr.get(path[i]).x, arr.get(path[i]).y, 0));
			visit[arr.get(path[i]).x][arr.get(path[i]).y] = true;
		}

		int emptycnt = 0;
		int time = 0;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			int qtime = cur.cnt;
			for (int d = 0; d < 4; d++) {
				int dx = cur.x + direct[d][0];
				int dy = cur.y + direct[d][1];
				if (dx < 0 || dy < 0 || dx >= N || dy >= N)
					continue;
				if (visit[dx][dy])continue;
		
				if (copy[dx][dy] == 1)continue;
				if (copy[dx][dy] == 0) {
					emptycnt++;
					time = qtime + 1;
				}
				visit[dx][dy] = true;
				q.add(new Point(dx, dy, qtime + 1));
			}

		}
		
		if (emptycnt == empty) {
			MIN = Math.min(MIN, time);
		}
	}


	static void dfs(int level, int start) {

		if (level == M) {
			// System.out.println(Arrays.toString(path));
			bfs();
			return;
		}

		for (int i = start; i < arr.size(); i++) {
			path[level] = i;
			dfs(level + 1, i + 1);
			path[level] = 0;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visit = new boolean[N][N];
		arr = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					arr.add(new Point(i, j, 0));
				}
				else if (map[i][j] == 0) {
					empty++;
				}
			}
		}
		// 여기까지 입력
		path = new int[M];
		dfs(0, 0);
		if (MIN == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else System.out.println(MIN);
	}

}
