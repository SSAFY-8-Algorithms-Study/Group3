package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 컴백홈
public class BOJ_1189_최규림 {

	static int R, C, K, answer;
	static char[][] board;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new char[R][C];
		visited = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			char[] cArr = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				board[r][c] = cArr[c];
			}
		}

		visited[R - 1][0] = true;
		dfs(R - 1, 0, 1);
		System.out.println(answer);
	}

	static void dfs(int r, int c, int cnt) {
		if (r == 0 && c == C - 1 && board[r][c] == '.') {
			if (cnt == K) {
				answer++;
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (checkRange(nr, nc) && !visited[nr][nc] && board[nr][nc] == '.') {
				visited[nr][nc] = true;
				dfs(nr, nc, cnt + 1);
				visited[nr][nc] = false;
			}

		}
	}

	static boolean checkRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}
