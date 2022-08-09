package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

//벽 부수고 이동하기
public class BOJ_2206_최규림 {

	static int n, m;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int wall = Integer.MAX_VALUE;
	static int[][] map;
	static Deque<int[]> q = new ArrayDeque<>();

	static boolean checkRange(int r, int c) {
		return (r >= 0) && (r < n) && (c >= 0) && (c < m);
	}

	static boolean checkBlock(int r, int c) {
		int check = 0;
		// 상하좌우 탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (!(checkRange(nr, nc) && map[nr][nc] != wall)) {
				check++;
			}
		}
		// 4방향 모두 이동 불가하면, 해당 벽 부술 필요 없음 -> block true
		return (check == 4) ? true : false;
	}

	static int bfs() {
		Map<Integer, HashSet<Integer>> visited = new HashMap<>();
		Deque<int[]> tempQ = new ArrayDeque<>();
		tempQ.add(new int[] { 0, 0, 1 });
		for (int i = 0; i < n; i++) {
			visited.put(i, new HashSet<Integer>());
		}
		visited.get(0).add(0);
		visited.put(0, visited.get(0));

		while (!tempQ.isEmpty()) {
			int[] target = tempQ.pollFirst();
			int r = target[0];
			int c = target[1];
			int dist = target[2];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (checkRange(nr, nc) && map[nr][nc] != wall && !visited.get(nr).contains(nc)) {
					visited.get(nr).add(nc);
					visited.put(nr, visited.get(nr));
					tempQ.add(new int[] { nr, nc, dist + 1 });
				}

				if (nr == n - 1 && nc == m - 1) {
					return dist + 1;
				}
			}

		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int r = 0; r < n; r++) {
			String str = br.readLine();
			for (int c = 0; c < m; c++) {
				// 벽인 경우
				if (str.charAt(c) == '1') {
					map[r][c] = wall;

					// 부수는 의미가 있는 벽인지 확인
					if (!checkBlock(r, c)) {
						q.add(new int[] { r, c });
					}
				}
				// 벽이 아닌 경우
				else {
					map[r][c] = 0;
				}
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < q.size(); i++) {
			int[] target = q.pollFirst();
			// 벽 부숨
			map[target[0]][target[1]] = 0;

			int result = bfs();
			if (result != -1) {
				answer = Math.min(answer, result);
			}

			// 벽 고침
			map[target[0]][target[1]] = wall;
		}
		answer = (answer == Integer.MAX_VALUE) ? -1 : answer;
		System.out.println(answer);
	}

}
