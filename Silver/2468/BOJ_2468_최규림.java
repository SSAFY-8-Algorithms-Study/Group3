package study;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BOJ_2468_최규림 {

	static int n;
	static int board[][];
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	static int check(int[][] temp, int depth) {
		boolean[][] visited = new boolean[n][n];// 방문여부 초기화
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				// 잠긴 지역 방문처리
				if (temp[r][c] <= depth) {
					visited[r][c] = true;
				}
			}
		}

		int result = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				// 잠기지 않은 지역 방문하지 않았더라면
				if (!visited[r][c]) {
					// 해당 안전 지역 전부 방문처리
					bfs(r, c, visited);
					result++;
				}
			}
		}

		return result;
	}

	static void bfs(int r, int c, boolean[][] visited) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { r, c });
		// (r, c)지점을 기준으로 주변(상하좌우)잠기지 않은 지역 전부 방문 처리
		while (!dq.isEmpty()) {
			int[] temp = dq.pollFirst();
			int rr = temp[0];
			int cc = temp[1];
			visited[rr][cc] = true;
			for (int i = 0; i < 4; i++) {
				int nr = rr + dr[i];
				int nc = cc + dc[i];
				if (checkRange(nr, nc)) {
					if (!visited[nr][nc]) {
						dq.add(new int[] { nr, nc });
						visited[nr][nc] = true;
					}
				}
			}
		}

	}

	static boolean checkRange(int r, int c) {
		return ((r >= 0) && (r < n) && (c >= 0) && (c < n));
	}

	public static void main(String[] args) {
		// 안전 영역
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		board = new int[n][n];

		int minValue = 101;
		int maxValue = -1;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				board[r][c] = sc.nextInt();
				minValue = Math.min(board[r][c], minValue);
				maxValue = Math.max(board[r][c], maxValue);
			}
		}

		int answer = 1; // 전지역 안전지점인 경우, 안전지점 = 1
		// 잠길 수 있는 높이들 전부 검사
		for (int depth = minValue; depth <= maxValue; depth++) {
			int[][] temp = board.clone();
			answer = Math.max(check(temp, depth), answer);
		}
		System.out.println(answer);

	}
}
