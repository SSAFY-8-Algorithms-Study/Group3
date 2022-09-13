package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 창영이와 퇴근
public class BOJ_22116_최규림 {

	static int N, answer;
	static int map[][], arr[][];
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	static class Point implements Comparable<Point> {
		int r, c, value;

		Point(int r, int c, int value) {
			this.r = r;
			this.c = c;
			this.value = value;
		}

		@Override
		public int compareTo(Point o) {
			return this.value - o.value;
		}
	}

	static PriorityQueue<Point> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		arr = new int[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			Arrays.fill(arr[r], Integer.MAX_VALUE);
		}

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		if (N == 1) {
			System.out.println(0);
		} else {
			pq.add(new Point(1, 2, Math.abs(map[1][1] - map[1][2])));
			pq.add(new Point(2, 1, Math.abs(map[1][1] - map[2][1])));
			dijkstra();
			System.out.println(answer);
		}
	}

	static void dijkstra() {
		while (!pq.isEmpty()) {
			Point now = pq.poll();
			if (arr[now.r][now.c] != Integer.MAX_VALUE)
				continue;
			arr[now.r][now.c] = now.value;
			answer = Math.max(answer, now.value);

			if (now.r == N && now.c == N) {
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (!checkRange(nr, nc) || arr[nr][nc] != Integer.MAX_VALUE)
					continue;

				pq.add(new Point(nr, nc, Math.abs(map[now.r][now.c] - map[nr][nc])));
			}

		}
	}

	static boolean checkRange(int r, int c) {
		return (r >= 1 && r <= N && c >= 1 && c <= N);
	}

}
