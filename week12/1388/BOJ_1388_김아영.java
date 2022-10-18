package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1388_김아영 {

	static int N, M;
	static char map[][];
	static int result;
	static boolean visit[][];

	static void right(int x, int y) {

		int dx = x;
		while (true) {
			int dy = y + 1;
			if (dx < 0 || dy < 0 || dx >= N || dy >= M)
				break;
			if (map[dx][dy] != '-')
				break;
			if (visit[dx][dy])
				continue;
			visit[dx][dy] = true;
			y = dy;
		}
		result++;
	}

	static void down(int x, int y) {

		while (true) {
			int dx = x + 1;
			int dy = y;
			if (dx < 0 || dy < 0 || dx >= N || dy >= M)
				break;
			if (map[dx][dy] != '|')
				break;
			if (visit[dx][dy])
				continue;
			visit[dx][dy] = true;
			x = dx;
		}
		result++;
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		} // input

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '-' && !visit[i][j]) {right(i, j);}
				else if (map[i][j] == '|' && !visit[i][j]) {down(i, j);}
			}
		}
		System.out.println(result);
	}

}
