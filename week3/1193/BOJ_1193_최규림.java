package week3;

import java.util.Scanner;

// 분수찾기
public class BOJ_1193_최규림 {

	static int[] dr = { -1, 1 };
	static int[] dc = { 1, -1 };
	static boolean up = true;
	static boolean down = false;

	static boolean checkRange(int r, int c) {
		return (r >= 1) && (c >= 1);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int r = 1, c = 1;
		boolean flag = up;
		int idx = 0;

		while ((--x) != 0) {
			int nr = r + dr[idx];
			int nc = c + dc[idx];

			// 범위 확인
			if (checkRange(nr, nc)) {
				r += dr[idx];
				c += dc[idx];
			}
			// 범위 이탈
			else {
				if (flag == up) {
					flag = down;
					idx = 1;
					c += 1;
				} else {
					flag = up;
					idx = 0;
					r += 1;
				}
			}
		}

		System.out.println(r + "/" + c);

	}
}
