package study;

import java.util.Scanner;

public class BOJ_1100_최규림 {
	static String[] board = new String[8];

	static int check(int r) {

		int cnt = 0;
		// 짝수번째 행에서 짝수번째 열의 값만 비교
		if (r % 2 == 0) {
			for (int i = 0; i < 8; i += 2) {
				if (board[r].charAt(i) == 'F') {
					cnt++;
				}
			}
		}
		// 홀수번째 행에서 홀수번째 열의 값만 비교
		else {
			for (int i = 1; i < 8; i += 2) {
				if (board[r].charAt(i) == 'F') {
					cnt++;
				}
			}
		}
		return cnt;

	}

	public static void main(String[] args) {
		// 하얀 칸
		Scanner sc = new Scanner(System.in);
		for (int r = 0; r < 8; r++) {
			board[r] = sc.next();
		}

		int answer = 0;
		for (int r = 0; r < 8; r++) {
			answer += check(r);
		}
		System.out.println(answer);
	}
}
