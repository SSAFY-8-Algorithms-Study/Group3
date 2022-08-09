package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

//색종이
public class BOJ_2563_최규림 {

	static int n; // 색종이 수
	static boolean[][] board; // 도화지 내의 색종이 여부 배열

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		board = new boolean[100][100];
		int answer = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int nc = Integer.parseInt(st.nextToken());
			int nr = Integer.parseInt(st.nextToken());

			// 해당 크기만큼 도화지에 색종이 부착
			for (int r = nr; r < nr + 10; r++) {
				for (int c = nc; c < nc + 10; c++) {
					// 처음 색종이 붙이는 경우 덧셈처리
					if (!board[r][c]) {
						answer++;
					}
					board[r][c] = true;
				}
			}
		}
		System.out.println(answer);

	}

}