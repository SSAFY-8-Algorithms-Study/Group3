package week4;

import java.util.Scanner;

// 별 찍기 - 10
public class BOJ_2447_최규림 {

	static int N;
	static char[][] map;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new char[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = '*';
			}
		}

		delete(0, 0, N / 3);
		print();

	}

	static void delete(int sr, int sc, int len) {
		if (len == 0) {
			return;
		}

		int startR = sr + len, startC = sc + len;
		for (int r = startR; r < startR + len; r++) {
			for (int c = startC; c < startC + len; c++) {
				map[r][c] = ' ';
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				delete(sr + len * i, sc + len * j, len / 3);
			}
		}
	}

	static void print() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sb.append(map[r][c]);
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
}
