package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_22116_김아영 {

	static int N, result;
	static int[][] map;
	static boolean visit[][];

	static int direct[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Point implements Comparable<Point> {
		int i, j, cnt;

		public Point(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			return (this.cnt - o.cnt);
		}

	}

	static void bfs() {

		PriorityQueue<Point> q = new PriorityQueue<>();

		q.add(new Point(0, 0, 0));

		while (!q.isEmpty()) {
			Point now = q.poll();
			visit[now.i][now.j] = true;
			result = Math.max(result, now.cnt);
			if (now.i == N - 1 && now.j == N - 1) {
				System.out.println(result);
				return;
			}

			for (int d = 0; d < 4; d++) {
				int dx = now.i + direct[d][0];
				int dy = now.j + direct[d][1];

				if (dx < 0 || dx >= N || dy < 0 || dy >= N)
					continue;
				if (visit[dx][dy])
					continue;
				q.add(new Point(dx, dy, Math.abs(map[now.i][now.j] - map[dx][dy])));
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();
		// print();

	}

	static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(visit[i]));
		}
	}

}
