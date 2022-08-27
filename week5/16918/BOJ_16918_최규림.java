package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16918_최규림 {

	static int R, C, N;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static StringBuilder sb = new StringBuilder();

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static Queue<Point> bombQ;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		bombQ = new ArrayDeque<>();

		for (int r = 0; r < R; r++) {
			char[] cArr = br.readLine().toCharArray();
			map[r] = cArr;
			for (int c = 0; c < C; c++) {
				if (cArr[c] == 'O') {
					bombQ.add(new Point(r, c));
				}
			}
		}

		if (N == 1) {
			print();
			return;
		}

		// 'O'으로 가득 채움
		insert();

		// 짝수번째는 폭탄 설치
		if (N % 2 == 0) {
			print();
			return;
		}

		// 첫 번째와 짝수번째를 제외하면 2가지 case만 반복 -> 3초마다 폭발하기 때문
		if ((N - 3) % 4 == 0) {
			bomb();
			print();
		} else if ((N - 5) % 4 == 0) {
			bomb();
			insert();
			bomb();
			print();
		}

	}

	static void insert() {
		for (int r = 0; r < R; r++) {
			Arrays.fill(map[r], 'O');
		}
	}

	static void bomb() {
		while (!bombQ.isEmpty()) {
			Point p = bombQ.poll();
			map[p.r][p.c] = '.';
			for (int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];

				if (checkRange(nr, nc)) {
					map[nr][nc] = '.';
				}
			}
		}

		// 폭탄 터지고 남은 좌표에 폭탄 설치
		bombQ = new ArrayDeque<>();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'O') {
					bombQ.add(new Point(r, c));
				}
			}
		}
	}

	static boolean checkRange(int r, int c) {
		return (r >= 0) && (r < R) && (c >= 0) && (c < C);
	}

	static void print() {
		for (int r = 0; r < R; r++) {
			for (int c = -0; c < C; c++) {
				sb.append(map[r][c]);
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
}
