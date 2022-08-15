package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//벽 부수고 이동하기
public class BOJ_2206_최규림 {

	static int n, m; // 배열 크기
	static int[][] map;
	static boolean[][][] visited; // 2가지 경로 나눠서 방문여부 확인하기 위해 3차원 배열로 선언

	static int loed = 1; // 이동 가능 경로
	static int wall = Integer.MAX_VALUE; // 벽
	static int answer = Integer.MAX_VALUE;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Point {
		int r, c, cnt, crushed; // 좌표, 이동 횟수, 벽 부순 이력

		Point(int r, int c, int cnt, int crushed) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.crushed = crushed;
		}
	}

	// 범위 확인
	static boolean checkRange(int r, int c) {
		return (r >= 1) && (r <= n) && (c >= 1) && (c <= m);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		map = new int[n + 1][m + 1];

		// 0: 벽을 안부수고 이동한 경로, 1: 벽을 부수고 이동한 경로
		visited = new boolean[n + 1][m + 1][2];

		for (int r = 1; r <= n; r++) {
			char[] cArr = br.readLine().toCharArray();
			for (int c = 1; c <= m; c++) {
				map[r][c] = (cArr[c - 1] == '1') ? wall : 0;
			}
		}

		bfs();
		System.out.println((answer == Integer.MAX_VALUE) ? -1 : answer);
	}

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(1, 1, 1, 0));
		visited[1][1][0] = true; // 출발지점, 벽 안부수고 이동

		while (!q.isEmpty()) {
			Point now = q.poll();
			if (now.r == n && now.c == m) {
				answer = now.cnt;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				// 범위 벗어난 경우
				if (!checkRange(nr, nc)) {
					continue;
				}

				// 이전에 같은 방식으로 방문한 경우(부수고 이동 or 그냥 이동) -> 같은 방식인지 구분해줘야 함
				if (visited[nr][nc][now.crushed]) {
					continue;
				}

				// 벽을 만났는데 이전에 벽은 부순 적이 있는 경우
				if (map[nr][nc] == wall && now.crushed == 1) {
					continue;
				}

				// 벽을 만난 경우, 벽을 부수고 이동
				if (map[nr][nc] == wall) {
					q.add(new Point(nr, nc, now.cnt + 1, 1));
					visited[nr][nc][1] = true;
				} else {
					// (nr, nc) 이동하기 전 now에서 벽을 부수고 이동했는지 안했는지 모르므로
					// 0 이 아닌, 이전의 now.crused 그대로 사용
					q.add(new Point(nr, nc, now.cnt + 1, now.crushed));
					visited[nr][nc][now.crushed] = true;
				}
			}

		}

	}

}
