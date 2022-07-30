package study;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BOJ_2573_최규림 {

	static int n, m;
	static int[][] board;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static boolean checkRange(int r, int c) {
		return ((r >= 0) && (r < n) && (c >= 0) && (c < m));
	}

	static void bfs(int r, int c, boolean[][] visited) {
		Deque<int[]> dq = new ArrayDeque<int[]>();
		dq.add(new int[] { r, c });
		while (!dq.isEmpty()) {
			int[] temp = dq.poll();
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

	// 덩어리 개수 찾는 함수
	static int calcCnt() {
		boolean[][] visited = new boolean[n][m];
		int result = 0;
		// 바다인 지역 방문처리
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (board[r][c] == 0) {
					visited[r][c] = true;
				}
			}
		}
		// 덩어리 개수
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if(!visited[r][c]) {
					bfs(r, c, visited);
					result++;					
				}
			}
		}
		return result;
	}

	//1년 빙하 녹는 함수
	static void melt() {
		Deque<int[]> dq = new ArrayDeque<>();
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				//바다가 아닌 경우
				if (board[r][c] != 0) {
					int cnt = 0;
					for (int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						if (checkRange(nr, nc)) {
							//상하좌우 바다인 지역 개수 확인
							if (board[nr][nc] == 0) {
								cnt++;
							}
						}
					}			
					//녹는 지역 정보 누적
					dq.add(new int[] { r, c, cnt });
				}
			}
		}

		//녹는 경우, 한번에 연산
		while (!dq.isEmpty()) {
			int[] temp = dq.poll();
			int r = temp[0];
			int c = temp[1];
			int cnt = temp[2];
			board[r][c] -= cnt;
			board[r][c] = Math.max(board[r][c], 0);
		}
	}

	public static void main(String[] args) {
		// 빙산
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		board = new int[n][m];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				board[r][c] = sc.nextInt();
			}
		}

		int answer = 0;
		//빙산 덩어리가 분리되거나, 분리되지 않고 한번에 녹은 경우가 나올때까지 반복
		while (calcCnt() == 1) {
			melt();
			answer++;			
		}		
		
		//빙하가 나뉘지 않고 한번에 녹은 경우
		if (calcCnt() == 0) {			
			answer = 0;
		}
		System.out.println(answer);
	}
}
