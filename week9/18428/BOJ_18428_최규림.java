package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 감시 피하기
public class BOJ_18428_최규림 {

	static int N;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean flag;

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static List<Point> teacherList = new ArrayList<>();
	static List<Point> blockList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		flag = false;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = st.nextToken().charAt(0);

				if (map[r][c] == 'T') {
					teacherList.add(new Point(r, c));
				}
			}
		}

		teacherCheck();

		// 학생, 선생님 붙어있는 경우, 무조건 NO
		if (flag) {
			System.out.println("NO");
		} else {

			int blockCnt = blockList.size();
			System.out.println(blockCnt <= 3 ? "YES" : "NO");
		}
	}

	static void teacherCheck() {
		for (Point p : teacherList) {
			// 상하좌우
			for (int i = 0; i < 4; i++) {
				int r = p.r;
				int c = p.c;

				int nr = r + dr[i];
				int nc = c + dc[i];
				while (checkRange(nr, nc)) {
					if (map[nr][nc] == 'T' || map[nr][nc] == 'O') {
						break;
					}

					if (map[nr][nc] == 'S') {
						// 학생, 선생님이 붙어있는 경우, 무조건 NO
						if (map[nr - dr[i]][nc - dc[i]] == 'T') {
							flag = true;
							break;
						} else if (map[nr - dr[i]][nc - dc[i]] == 'X') {
							map[nr - dr[i]][nc - dc[i]] = 'O';
							blockList.add(new Point(nr - dr[i], nc - dc[i]));
							break;
						}
					}

					nr += dr[i];
					nc += dc[i];
				}
			}
		}
	}

	static boolean checkRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
