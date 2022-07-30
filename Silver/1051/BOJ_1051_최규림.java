package study;

import java.util.Scanner;

public class BOJ_1051_최규림 {

	static int n, m;
	static int[][] board;

	static boolean checkRange(int r, int c) {
		return (r < n && c < m);
	}

	static boolean checkSq(int r, int c, int l, int value) {
		return (board[r + l][c] == value) && (board[r][c + l] == value) && (board[r + l][c + l] == value);
	}

	static int check(int r, int c) {
		int line = 1;
		int result = 0;
		while (checkRange(r + line, c + line)) {
			if (checkSq(r, c, line, board[r][c])) {
				result = Math.max(result, line);
			}
			line++;
		}
		return result + 1; // 현재 위치도 길이에 포함(+1)
	}

	public static void main(String[] args) {
		// 숫자 정사각형
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		board = new int[n][m];
		for (int r = 0; r < n; r++) {
			String str = sc.next();
			for (int c = 0; c < m; c++) {
				board[r][c] = str.charAt(c) - '0';
			}
		}

		int answer = 1;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				answer = Math.max(answer, check(r, c));
			}
		}
		System.out.println(answer * answer);
	}
}
