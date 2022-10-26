package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// 아맞다우산	
public class BOJ_17244_최규림 {

	static int R, C, ans;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int[] result;
	static boolean[] used;

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static Point start, end;
	static ArrayList<Point> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new char[R][];
		ans = Integer.MAX_VALUE;

		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'S') { //출발지 저장
					start = new Point(r, c);
				}

				if (map[r][c] == 'X') { //물건 위치 저장
					list.add(new Point(r, c));
				}

				if (map[r][c] == 'E') { //도착지 저장
					end = new Point(r, c);
				}
			}
		}

		//순열에 필요한 배열들
		result = new int[list.size()];
		used = new boolean[list.size()];

		perm(0);
		System.out.println(ans);

	}

	static void perm(int idx) {
		if (idx == list.size()) {
			// 물건 위치 방문 순서에 따라 소요되는 시간 계산
			int temp = 0;
			Point tempStart = start;
			for (int i = 0; i < list.size(); i++) {
				temp += bfs(tempStart, list.get(result[i]));
				tempStart = list.get(result[i]);
			}
			temp += bfs(tempStart, end);

			ans = Math.min(ans, temp);
			return;
		}

		for (int i = 0; i < list.size(); i++) {
			if (used[i])
				continue;

			used[i] = true;
			result[idx] = i;
			perm(idx + 1);
			used[i] = false;

		}
	}

	static int bfs(Point point, Point target) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(point);
		visited = new boolean[R][C];
		visited[point.r][point.c] = true;
		int time = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			time++;
			for (int x = 0; x < size; x++) {
				Point now = q.poll();

				for (int i = 0; i < 4; i++) {
					int nr = now.r + dr[i];
					int nc = now.c + dc[i];

					if (!checkRange(nr, nc) || map[nr][nc] == '#' || visited[nr][nc]) {
						continue;
					}

					if (nr == target.r && nc == target.c) {
						return time;
					}

					q.add(new Point(nr, nc));
					visited[nr][nc] = true;
				}

			}
		}

		return -1;
	}

	static boolean checkRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}
