package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// 경로 찾기
public class BOJ_11403_최규림 {
	static int N;
	static int[][] map;
	static ArrayList<ArrayList<Integer>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(new ArrayList<>());
		}

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					list.get(r).add(c);
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (isConnected(r, c)) {
					map[r][c] = 1;
				} else {
					map[r][c] = 0;
				}
			}
		}

		print();
	}

	static boolean isConnected(int from, int to) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(from);
		boolean[] visited = new boolean[N];

		while (!q.isEmpty()) {
			int now = q.poll();

			for (int next : list.get(now)) {
				if (next == to) {
					return true;
				}

				if (!visited[next]) {
					visited[next] = true;
					q.add(next);
				}
			}
		}

		return false;
	}

	static void print() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

}
