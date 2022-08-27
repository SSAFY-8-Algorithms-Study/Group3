package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// 연구소 3
public class BOJ_17142_최규림 {

	static int N, M, answer;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int binCnt; // 빈 공간 개수 -> 모든 빈 칸 못 퍼뜨리는 경우 확인할 변수

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	// virus 위치 저장 리스트
	static ArrayList<Point> virusList = new ArrayList<>();
	static boolean[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		answer = Integer.MAX_VALUE;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				// 바이러스 위치 리스트에 저장
				if (map[r][c] == 2) {
					virusList.add(new Point(r, c));
				}
				// 빈 공간 개수
				else if (map[r][c] == 0) {
					binCnt++;
				}
			}
		}

		selected = new boolean[virusList.size()];
		comb(0, 0);

		// answer가 갱신된 적이 없다면 -> 바이러스를 다 퍼뜨린 경우가 없다면
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	static int bfs(int[][] temp) {
		int cnt = binCnt; // 빈 공간 개수 확인
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < virusList.size(); i++) {
			Point p = virusList.get(i);
			// 선택된 바이러스인 경우
			if (selected[i]) {
				q.add(p);
				temp[p.r][p.c] = 0; // 0으로 변환 -> 해당 위치에서 퍼지면서 시간 계산하기 위함
				visited[p.r][p.c] = true;
			}
			// 선택되지 않은 바이러스는 벽 취급 -> 비활성 바이러스
			else {
				temp[p.r][p.c] = 9;
			}
		}

		int result = 0;
		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				// 활성 조건
				if (checkRange(nr, nc) && !visited[nr][nc]) {

					// 비활성 바이러스
					if (temp[nr][nc] == 9) {
						visited[nr][nc] = true;
						temp[nr][nc] = temp[now.r][now.c] + 1;
						// 비활성 바이러스는 활성 취급은 안하기 때문에 result 갱신에서 제외
//						result = Math.max(result, temp[nr][nc]);
						q.add(new Point(nr, nc));
					}

					// 활성 이동
					else if (temp[nr][nc] == 0) {
						cnt--; // 빈 공간 개수 차감
						visited[nr][nc] = true;
						temp[nr][nc] = temp[now.r][now.c] + 1;
						result = Math.max(result, temp[nr][nc]);
						q.add(new Point(nr, nc));
					}
				}
			}
		}

		// 빈 공간 전부 퍼뜨리지 못한 경우
		if (cnt > 0) {
			return -1;
		} else {
			return result;
		}

	}

	static void comb(int idx, int cnt) {
		if (cnt == M) {

			int[][] copy = deepcopy(map);
			int result = bfs(copy);
			if (result != -1) {
				answer = Math.min(answer, result);
			}
			return;
		}

		if (idx == virusList.size()) {
			return;
		}

		selected[idx] = true;
		comb(idx + 1, cnt + 1);
		selected[idx] = false;
		comb(idx + 1, cnt);
	}

	static int[][] deepcopy(int[][] origin) {
		int[][] copy = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				copy[r][c] = origin[r][c];
			}
		}
		return copy;
	}

	static boolean checkRange(int r, int c) {
		return (r >= 0) && (r < N) && (c >= 0) && (c < N);
	}
}
