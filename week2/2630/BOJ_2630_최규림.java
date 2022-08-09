package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//색종이 만들기
public class BOJ_2630_최규림 {

	static int[] color; // white, blue 저장 배열
	static int[][] paper; // 색종이 저장 배열

	static void calc(int r, int c, int n) {
		// 1장인 경우
		if (n == 1) {
			color[paper[r][c]]++;
			return;
		}

		// (r, c)의 카드 색 -> 해당 범위에서 p와 다르면 -> 자르기
		int p = paper[r][c];
		boolean flag = true;
		for (int i = r; i < r + n; i++) {
			// 해당 범위에서 이미 색종이 색이 불일치하면 더이상 반복문 필요X
			if (!flag)
				break;

			for (int j = c; j < c + n; j++) {
				// 색종이 색 불일치
				if (paper[i][j] != p) {
					flag = false;
					n /= 2;

					// 범위 수정 후 재귀
					calc(r, c, n);
					calc(r + n, c, n);
					calc(r, c + n, n);
					calc(r + n, c + n, n);
					break;
				}
			}
		}

		if (flag) {
			color[p]++;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		color = new int[2]; // 0:white, 1:blue
		paper = new int[n + 1][n + 1];
		for (int r = 1; r <= n; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= n; c++) {
				paper[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		calc(1, 1, n);
		System.out.println(color[0]); // white
		System.out.println(color[1]); // blue
	}

}
